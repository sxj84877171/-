package com.soarsky.policeclient.ui.accident.selectCar;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.ui.accident.DataNoSerializable;
import com.soarsky.policeclient.ui.accident.add.AccidentBottomDialog;
import com.soarsky.policeclient.ui.accident.add.AccidentBottomDialogListener;
import com.soarsky.policeclient.uitl.CarTypeUtils;
import com.soarsky.policeclient.uitl.ToastUtil;
import com.soarsky.policeclient.uitl.ValidatorUtils;
import com.soarsky.policeclient.server.bluetooth.Blue;

import java.util.ArrayList;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/19<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为输入车牌号，根据车牌号连接智能终端，读取智能终端数据<br>
 */
public class EnterCarNumActivity extends AppCompatActivity {

    /**
     * 返回按钮
     */
    private RelativeLayout back;
    /**
     * 界面标题
     */
    private TextView headerTv;

    /**
     * 输入的车牌号
     */
    private EditText carNum;


    /**
     * 扫描到的车辆列表
     */
    private ArrayList<Car> scanCarList = new ArrayList<>();
    /**
     * 确认按钮
     */
    private Button ok;

    /**
     * 选择车辆类型
     */
    private LinearLayout typeLl;
    /**
     * 车辆类型
     */
    private TextView type;
    /**
     * 选择车辆类型
     */
    private AccidentBottomDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_car_num);
        typeLl = (LinearLayout) findViewById(R.id.typeLl);
        type = (TextView) findViewById(R.id.type);
        back = (RelativeLayout) findViewById(R.id.listIconLay);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
                finish();
            }
        });
        headerTv = (TextView) findViewById(R.id.headerTv);
        headerTv.setText("增加事故车辆");
        ok = (Button) findViewById(R.id.ok);
        blueSaoMiao();
        carNum = (EditText) findViewById(R.id.carNum);
        typeLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSelectCarType();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ValidatorUtils.validatorCarNo(carNum.getText().toString())){
                    ToastUtil.show(EnterCarNumActivity.this, "请输入合法的车牌号");
                }else {
                    doMain();
                }
            }
        });
    }

    private void blueSaoMiao(){
        Blue blue = Blue.getInstance(this);
        blue.setOnBlueScan(onBlueScan);
        blue.startDiscovery();
    }
    private Blue.OnBlueScan onBlueScan = new Blue.OnBlueScan() {


        @Override
        public void onBlueScan(Car car) {
            if(!scanCarList.contains(car)){
                scanCarList.add(car);
            }
        }
    };
    @Override
    public void onBackPressed() {
        back();
        super.onBackPressed();
    }
    private void back() {
        Blue.getInstance(this).stopDiscovery();
    }
    /**
     * 把输入的车牌号与扫描到的车牌号进行比对看是否存在
     */
    private void doMain(){
        String ssid = carNum.getText().toString();
        boolean has = false;
        for (Car car:scanCarList) {
            if(car.getBlueName().contains(ssid)){
                has = true;
                Intent in = new Intent();
                DataNoSerializable.getDataNoSerializable().setCar(car);
                EnterCarNumActivity.this.setResult(Activity.RESULT_OK,in);
                finish();
                break;
            }
        }
        if(!has){
            if(type.getText()==null || type.getText().equals("")){
                ToastUtil.show(this,"请选择车辆类型");
                typeLl.setVisibility(View.VISIBLE);
                showSelectCarType();
            }else {
                Intent in = new Intent();
                Car car = new Car();
                car.setCarNum(carNum.getText().toString());
                car.setType(type.getText().toString());
                DataNoSerializable.getDataNoSerializable().setCar(car);
                EnterCarNumActivity.this.setResult(Activity.RESULT_OK,in);
                finish();
            }

        }
    }


    /**
     * 显示选择车辆类型对话框
     */
    private void showSelectCarType(){
        if(dialog==null){
            WindowManager windowManager = getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            int width = display.getWidth();
            int height = display.getHeight();
            AccidentBottomDialog.Builder builder = new AccidentBottomDialog.Builder(this, width, height / 3, new AccidentBottomDialogListener(){
                @Override
                public void confirmClick(String o) {
                    type.setText(o);
                    dialog.dismiss();
                }
            }, CarTypeUtils.getCarTypeList(),"请选择车辆类型");

            dialog = builder.create();
            dialog.show();
        }else {
            dialog.show();
        }


    }

}
