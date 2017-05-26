package com.soarsky.car.ui.carcheck.carchecklogin;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.ui.carcheck.CarFaultActivity;
import com.soarsky.car.ui.carcheck.CarNormalActivity;
import com.soarsky.car.ui.carcheck.CheckFaultActivity;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/11/30
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  车辆检测
 */

public class CarCheckActivity extends BaseActivity<CarCheckPresent,CarCheckModel> implements CarCheckView, View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;

    /**
     * 返回
     */
    private ImageView backView;
    /**
     * 确定
     */
    private Button bt_sure;


    private Boolean isPassword = false;
    /**
     * 密码输入编辑框
     */
    private EditText et_pwd;
    private EditText et_carNum;
    private ImageView iv_pwd;
    private ImageView iv_delete;

    private ConfirmDriverService confirmDriverService;
    Intent serviceIntent;
    private Handler handler=new Handler();

    /**
     * 附近扫描的热点
     */
    private List<Car>carList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_check;
    }


    @Override
    protected void onStart() {
        super.onStart();
        /**
         * 绑定服务
         */
        serviceIntent=new Intent(this, ConfirmDriverService.class);
        bindService(serviceIntent,serviceConnection,BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        unbindService(serviceConnection);
        super.onStop();
    }

    @Override
    public void initView() {
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(R.string.car_check);

        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);

        bt_sure = (Button) findViewById(R.id.bt_sure);
        bt_sure.setOnClickListener(this);

        iv_pwd = (ImageView) findViewById(R.id.iv_pwd);
        iv_pwd.setOnClickListener(this);

        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_carNum = (EditText) findViewById(R.id.et_carNum);

        iv_delete = (ImageView) findViewById(R.id.iv_delete);
        iv_delete.setOnClickListener(this);

    }



    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            confirmDriverService=((ConfirmDriverService.LocalBinder) service).getService();
            IScan iScan=(IScan)((ConfirmDriverService.LocalBinder) service).getService();
            mPresenter.setScan(iScan);
            mPresenter.setConfirmDriverService(confirmDriverService);
            mPresenter.listen();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mPresenter.setScan(null);
            mPresenter.setConfirmDriverService(null);
            confirmDriverService=null;
        }
    };



    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backView:
                finish();
                break;
            case R.id.bt_sure:
               String carNum=et_carNum.getText().toString();
                if(!mPresenter.includeCar(carNum)){
                    startActivity(new Intent(CarCheckActivity.this,CarFaultActivity.class));
                }else{
                    mPresenter.connectCar();
                }



                break;
            case R.id.iv_pwd:
                if(isPassword == false){
                    et_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    iv_pwd.setImageResource(R.mipmap.see_yes);
                    isPassword = true;
                }else{
                    et_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iv_pwd.setImageResource(R.mipmap.see_no);
                    isPassword = false;
                }

                break;
            case R.id.iv_delete:
                et_carNum.getText().clear();
                break;
        }
    }

    @Override
    public void showList(List<Car> list) {
        carList=list;
    }

    @Override
    public void onConfirmDriversSucess() {
        startActivity(new Intent(CarCheckActivity.this,CarNormalActivity.class));
    }

    @Override
    public void onConfirmDriversFailed() {
        startActivity(new Intent(CarCheckActivity.this,CarFaultActivity.class));
    }

    @Override
    public void onResult(String resultStr) {
        Intent intent=new Intent(CarCheckActivity.this,CheckFaultActivity.class);
        intent.putExtra("troubleStr",resultStr);
        startActivity(intent);
    }


}
