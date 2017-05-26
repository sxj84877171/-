package com.soarsky.car.ui.license.validation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.DriveLicenseInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.license.DrivLicenseActivity;
import com.soarsky.car.uitl.ToastUtil;

//import cn.smssdk.EventHandler;
//import cn.smssdk.SMSSDK;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：驾驶证验证<br>
 * 该类为 LicenseValidationActivity<br>
 */

public class LicenseValidationActivity extends BaseActivity<LicenseValidationPresent,LicenseValidationModel> implements LicenseValidationView,View.OnClickListener{

    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * applcation
     */
    private App app;
    /**
     * 该类的key
     */
    public final static String TAG = "LicenseValidationActivity";
    /**
     *  姓名
     */
    private EditText licenseValidNameEt;
    /**
     * 身份证号
     */
    private EditText licenseValidCardEt;
    /**
     * 手机号
     */
    private EditText licenseValidPhoneEt;
    /**
     * 发送验证码
     */
    private Button licenseValidPhoneBtn;
    /**
     * 验证码
     */
    private EditText licenseValidCodeEt;
    /**
     * 确定
     */
    private Button licenseSureBtn;
    /**
     * 身份证
     */
    private String licenseValidCard;
    /**
     * 姓名
     */
    private String licenseValidName;
    /**
     * 电话
     */
    private String licenseValidPhone;
    /**
     * 验证码
     */
    private String licenseValidCode ="";
    /**
     * 验证倒计时
     */
    private TimeCount timeCount;
    /**
     * flag
     */
    private int flag = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_license_validation;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.licensetitle));

        licenseValidNameEt = (EditText) findViewById(R.id.licenseValidNameEt);
        licenseValidCardEt = (EditText) findViewById(R.id.licenseValidCardEt);
        licenseValidPhoneEt = (EditText) findViewById(R.id.licenseValidPhoneEt);
        licenseValidCodeEt = (EditText) findViewById(R.id.licenseValidCodeEt);

        licenseValidPhoneBtn = (Button) findViewById(R.id.licenseValidPhoneBtn);
        licenseValidPhoneBtn.setOnClickListener(this);

        licenseSureBtn = (Button) findViewById(R.id.licenseSureBtn);
        licenseSureBtn.setOnClickListener(this);

        initData();
    }

    public void initData(){
//        SMSSDK.registerEventHandler(eh); //注册短信回调
        licenseValidNameEt.setText(""+app.getCommonParam().getUserName());
        licenseValidCardEt.setText(""+app.getCommonParam().getIdNo());
        licenseValidPhoneEt.setText(""+app.getCommonParam().getOwerPhone());
        licenseValidCodeEt.setText(""+licenseValidCode);

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
            case R.id.licenseSureBtn:


                licenseValidCard = licenseValidCardEt.getText().toString().trim();
                licenseValidName = licenseValidNameEt.getText().toString().trim();
                licenseValidPhone = licenseValidPhoneEt.getText().toString().trim();
                licenseValidCode = licenseValidCodeEt.getText().toString().trim();
                if(TextUtils.isEmpty(licenseValidCard) || TextUtils.isEmpty(licenseValidName) || TextUtils.isEmpty(licenseValidPhone)
                        || TextUtils.isEmpty(licenseValidCode)){
                    ToastUtil.show(this,R.string.licensevalidEmpty);
                }else{
                    verificSucess();
//                    SMSSDK.submitVerificationCode("86", licenseValidPhoneEt.getText().toString().trim(),licenseValidCodeEt.getText().toString().trim());

                }

                break;
            case R.id.licenseValidPhoneBtn:
//                SMSSDK.getSupportedCountries();
//                SMSSDK.getVerificationCode("86",licenseValidPhoneEt.getText().toString());

                timeCount = new TimeCount(60000, 1000,licenseValidPhoneBtn,this);
                timeCount.start();

                break;
        }

    }





//    EventHandler eh=new EventHandler(){
//
//        @Override
//        public void afterEvent(int event, int result, Object data) {
//
//            if (result == SMSSDK.RESULT_COMPLETE) {
//                //回调完成
//                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//
//                           verificSucess();
//
//
//                    //提交验证码成功
//                }else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
//                    //获取验证码成功
//                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){
//                    //返回支持发送验证码的国家列表
//                }
//            }else{
//
//                if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//
//
//                    verificFail();
//
//                    //提交验证码成功
//                }
//                ((Throwable)data).printStackTrace();
//            }
//        }
//    };


    /**
     *手机验证成功
     */
    private void  verificSucess(){
        DriveLicenseInfo info = new DriveLicenseInfo();
                    info.setIdNo(licenseValidCard);
                    info.setPhone(licenseValidPhone);
                    info.setVerCode(licenseValidCode);
                    Intent i = new Intent();
                    i.setClass(this, DrivLicenseActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("info", info);
                    i.putExtras(b);
                    startActivity(i);
                    finish();

    }
    /**
     *手机验证失败
     */
    private void  verificFail(){

        runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LicenseValidationActivity.this,"验证码错误，请输入正确的验证码！",Toast.LENGTH_LONG).show();
                }
            });

    }

    /**
     * 获取验证码成功
     * @param bean 验证码信息
     */
    @Override
    public void sendSmsSuccess(ResponseDataBean<Void> bean) {

    }

    /**
     * 获取验证码失败
     */
    @Override
    public void sendSmsFail() {

    }
}
