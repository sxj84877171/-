package com.soarsky.car.ui.family.familynum;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.FamilyNumSendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：亲情号设置 <br>
 * 该类为 FamilyNumActivity <br>
 */

public class FamilyNumActivity extends BaseActivity<FamilyNumPresent,FamilyNumModel> implements FamilyNumView,View.OnClickListener{
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 亲情号码一
     */
    private EditText phoneOneEt;
    /**
     * 亲情号码二
     */
    private EditText phoneTwoEt;
    /**
     * 亲情号码三
     */
    private EditText phoneThreeEt;
    /**
     * 确定
     */
    private Button familyNumBtn;
    /**
     * 号码一
     */
    private String phone1 ="";
    /**
     * 号码二
     */
    private String phone2 = "";
    /**
     * 号码三
     */
    private String phone3 = "";
    /**
     * application
     */
    private App app;
    /**
     * 该类的key
     */
    public final static String TAG = "FamilyNumActivity";
    /**
     * service
     */
    private ConfirmDriverService confirmDriverService;

    /**
     * 保存附近的车辆
     */
    private List<Car> carList=new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_family_num;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.familynumtitle));

        phoneOneEt = (EditText) findViewById(R.id.phoneOneEt);
        phoneTwoEt = (EditText) findViewById(R.id.phoneTwoEt);
        phoneThreeEt = (EditText) findViewById(R.id.phoneThreeEt);

        familyNumBtn = (Button) findViewById(R.id.familyNumBtn);
        familyNumBtn.setOnClickListener(this);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SpUtil.init(this);
    }

    /**
     * 亲情号码设置成功
     * @param param 亲情号码设置返回参数
     */
    @Override
    public void showSuccess(ResponseDataBean param) {

        if(param != null){


            if(Constants.REQUEST_SUCCESS.equals(param.getStatus())){


                ToastUtil.show(this,R.string.family_num_tip);
                SpUtil.save("phoneNum",phoneOneEt.getText().toString()+","+phoneTwoEt.getText().toString()+","+phoneThreeEt.getText().toString());

                List<String> list = new ArrayList<String>();
                list.add(phoneOneEt.getText().toString());
                list.add(phoneTwoEt.getText().toString());
                list.add(phoneThreeEt.getText().toString());
                insertFamilyNum(list);

                /**
                 * 同步到终端
                 */
                if(null!=getNearCar()){
                    confirmDriverService.connectCar(getNearCar(),7);
                }

                finish();

            }else{
                ToastUtil.show(this,param.getMessage());
            }

        }
    }

    /**
     * 亲情号码设置失败
     */
    @Override
    public void showError() {

        ToastUtil.show(this,R.string.throwable);
    }

    /**
     * 插入数据成功
     */
    @Override
    public void insertSuccess() {
    }

    /**
     * 插入数据失败
     */
    @Override
    public void insertFail() {

//        ToastUtil.show(this,R.string.familynumupdatewrong);
    }

    /**
     * 附近的车辆
     * @param list 车辆的集合
     */
    @Override
    public void showList(List<Car> list) {
        carList=new ArrayList<>(list);
    }


    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.familyNumBtn:

                if(app.isOnline() == false) {
                    ToastUtil.show(this,R.string.personaltip);
                    return;
                }
                phone1 = phoneOneEt.getText().toString();
                phone2 = phoneTwoEt.getText().toString();
                phone3 = phoneThreeEt.getText().toString();


                if(!TextUtils.isEmpty(phone1)||!TextUtils.isEmpty(phone2) || !TextUtils.isEmpty(phone3)) {

                    //校验手机号
                    if(!TextUtils.isEmpty(phone1)) {
                        if (!isMobile(phone1)) {
                            ToastUtil.show(this, R.string.mobile_wrong);
                            return;
                        }
                    }
                    if(!TextUtils.isEmpty(phone2)) {
                        if (!isMobile(phone2)) {
                            ToastUtil.show(this, R.string.mobile_wrong);
                            return;
                        }
                    }

                    if(!TextUtils.isEmpty(phone3)) {
                        if (!isMobile(phone3)) {
                            ToastUtil.show(this, R.string.mobile_wrong);
                            return;
                        }
                    }

                    String owerPhone = app.getCommonParam().getOwerPhone();

                    if(phone1.equals(owerPhone)||phone2.equals(owerPhone)||phone3.equals(owerPhone)){
                        ToastUtil.show(this,R.string.phone_ower_tip);
                        return;
                    }
                    FamilyNumSendParam p = new FamilyNumSendParam();
                    p.setPhone1(phone1);
                    p.setPhone2(phone2);
                    p.setPhone3(phone3);
                    p.setCarnum(app.getCommonParam().getCarNum());
                    p.setUsername(""+app.getCommonParam().getUserName());
                    mPresenter.setFirendPhone(p);
                }else{

                    ToastUtil.show(this,R.string.modifyisEmpty);
                }


                break;
        }
    }

    /**
     * 校验手机号
     * @param mobile 手机号
     * @return 校验通过返回true，否则返回false
     */
    public  boolean isMobile(String mobile) {
        String REGEX_MOBILE = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 插入数据库
     * @param list 号码集合
     */
    private void insertFamilyNum(List<String> list){
        for (String phone:list){
            FamilyNum familyNum = new FamilyNum();
            familyNum.setPhone(phone);
            familyNum.setUsername(app.getCommonParam().getUserName());
            familyNum.setCarnum(app.getCommonParam().getCarNum());
            familyNum.setIs_owner(0);
            familyNum.setSstate(1);
            familyNum.setTstate(0);
            mPresenter.insertFamilyNum(familyNum);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent serviceIntent = new Intent(this, ConfirmDriverService.class);
        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);
    }


    @Override
    protected void onPause() {
        super.onPause();
        unbindService(serviceConnection);
    }

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            confirmDriverService=((ConfirmDriverService.LocalBinder) service).getService();
            IScan iScan = (IScan) ((ConfirmDriverService.LocalBinder) service).getService();
            mPresenter.setScan(iScan);
            mPresenter.setConfirmDriverService(confirmDriverService);
            mPresenter.listen();



        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
//            confirmDriverService.setAutoConfirmDriverLisener(null);
            confirmDriverService=null;
        }
    };


    /**
     * 判断车子是否在附近
     * @return car 车
     */

    public  Car getNearCar(){


        for (Car  car:
             carList) {
            if(car.getCarNum().equals(App.getApp().getCommonParam().getCarNum())){
                return car;
            }
        }
        return null;
    }


}
