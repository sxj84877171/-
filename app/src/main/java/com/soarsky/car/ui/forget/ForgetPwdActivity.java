package com.soarsky.car.ui.forget;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.license.validation.TimeCount;
import com.soarsky.car.ui.login.LoginActivity;
import com.soarsky.car.uitl.ToastUtil;

import java.util.regex.Pattern;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：忘记密码<br>
 * 该类为 ForgetPwdActivity<br>
 */

public class ForgetPwdActivity extends BaseActivity<ForgetPwdPresent,ForgetPwdModel> implements ForgetPwdView,View.OnClickListener{
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 真实姓名
     */
    private EditText forgetRealNameEt;
    /**
     * 真实姓名布局
     */
    private RelativeLayout forgetRealNameLay;
    /**
     * 真实姓名View
     */
    private ImageView forgetRealNameView;
    /**
     * 身份证号布局
     */
    private RelativeLayout forgetIdNumberLay;
    /**
     *  身份证号
     */
    private EditText forgetIdNumberEt;
    /**
     * 身份证号的view
     */
    private ImageView forgetIdNumberView;
    /**
     * 手机号码
     */
    private EditText mobliephoneEt;
    /**
     * 确认
     */
    private Button forgetConfirmBtn;
    /**
     * 真实姓名是否可见的标志
     */
    private boolean isRealName = true;
    /**
     * 身份证号是否可见的标志
     */
    private boolean isIdNumber = true;
    /**
     * 进度条
     */
    private ProgressDialog progressDialog;
    /**
     * 该类的key
     */
    public final static String TAG = "ForgetPwdActivity";
    /**
     * application
     */
    private App app;
    /**
     * 验证码按钮
     */
    private Button forgetSendPhoneBtn;
    /**
     * 验证码
     */
    private EditText forgetVerCodeEt;
    /**
     * 验证倒计时
     */
    private TimeCount timeCount;
    /**
     * 账号
     */
    private EditText forgetNameEt;
    /**
     * 账号布局
     */
    private RelativeLayout forgetNameLay;
    /**
     * 账号view
     */
    private ImageView forgetNameView;
    /**
     * 账号是否可见
     */
    private boolean isName = true;
    @Override
    public int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        forgetRealNameEt = (EditText) findViewById(R.id.forgetRealNameEt);
        forgetRealNameLay = (RelativeLayout) findViewById(R.id.forgetRealNameLay);
        forgetRealNameLay.setOnClickListener(this);
        forgetRealNameView = (ImageView) findViewById(R.id.forgetRealNameView);
        forgetRealNameView.setOnClickListener(this);

        forgetIdNumberLay = (RelativeLayout) findViewById(R.id.forgetIdNumberLay);
        forgetIdNumberLay.setOnClickListener(this);
        forgetIdNumberEt = (EditText) findViewById(R.id.forgetIdNumberEt);
        forgetIdNumberView = (ImageView) findViewById(R.id.forgetIdNumberView);
        forgetIdNumberView.setOnClickListener(this);

        mobliephoneEt = (EditText) findViewById(R.id.mobliephoneEt);

        forgetConfirmBtn = (Button) findViewById(R.id.forgetConfirmBtn);
        forgetConfirmBtn.setOnClickListener(this);

        forgetSendPhoneBtn = (Button) findViewById(R.id.forgetSendPhoneBtn);
        forgetSendPhoneBtn.setOnClickListener(this);

        forgetVerCodeEt = (EditText) findViewById(R.id.forgetVerCodeEt);

        forgetNameEt = (EditText) findViewById(R.id.forgetNameEt);
        forgetNameLay = (RelativeLayout) findViewById(R.id.forgetNameLay);
        forgetNameLay.setOnClickListener(this);
        forgetNameView = (ImageView) findViewById(R.id.forgetNameView);
        forgetNameView.setOnClickListener(this);

    }

    @Override
    protected String getHeaderTitle() {
        return getString(R.string.find_pwd);
    }

    @Override
    public void showProgess() {
        super.showProgess();
    }

    @Override
    public void stopProgess() {

        super.stopProgess();

    }

    /**
     * 忘记密码回调成功
     * @param param 回调结果参数
     */
    @Override
    public void showData(ResponseDataBean<String> param) {

        if(param != null) {

            if(Constants.REQUEST_SUCCESS.equals(param.getStatus())){
//                app.setForgetName(param.getData().getUserName());
//                app.setForgetPassword(param.getData().getPassword());
                Intent i = new Intent();
                i.setClass(this,LoginActivity.class);
                startActivity(i);
                finish();
            }else {
                ToastUtil.show(this, getString(R.string.right_msg));
            }
        }
    }

    /**
     * 忘记密码回调报错
     */
    @Override
    public void showonError() {
        ToastUtil.show(this,R.string.throwable);
    }

    /**
     * 是否可见的回调
     * @param type 0--真实姓名 1--idno
     */
    @Override
    public void isVisiable(int type) {

        switch (type){
            case 0:
                if(isRealName == true){
                    forgetRealNameEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    forgetRealNameView.setImageResource(R.mipmap.icon);
                    isRealName = false;
                }else {
                    forgetRealNameEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    forgetRealNameView.setImageResource(R.mipmap.confrim);
                    isRealName = true;
                }
                break;
            case 1:
                if(isIdNumber == true){
                    forgetIdNumberEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    forgetIdNumberView.setImageResource(R.mipmap.icon);
                    isIdNumber = false;
                }else{
                    forgetIdNumberEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    forgetIdNumberView.setImageResource(R.mipmap.confrim);
                    isIdNumber = true;
                }
                break;
            case 2:
                if(isName == true){

                    forgetNameEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    forgetNameView.setImageResource(R.mipmap.icon);
                    isName = false;
                }else {
                    forgetNameEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    forgetNameView.setImageResource(R.mipmap.confrim);
                    isName = true;
                }
                break;
        }
    }

    /***
     * 验证忘记密码的信息
     */
    @Override
    public void verifyForfetPwdInfo() {
        String forgetRealName = forgetRealNameEt.getText().toString();
        String forgetIdNumber = forgetIdNumberEt.getText().toString();
        String code = forgetVerCodeEt.getText().toString();
        String name = forgetNameEt.getText().toString();
        //校验身份证号
        if (!isCard(forgetIdNumber)){
            ToastUtil.show(ForgetPwdActivity.this,R.string.forgetidwrong);
            return;
        }
        String mobliephone = mobliephoneEt.getText().toString();

        //校验手机号
        if(!isMobile(mobliephone)){
            ToastUtil.show(ForgetPwdActivity.this,R.string.forgetphonewrong);
            return;
        }
        if(TextUtils.isEmpty(forgetRealName) || TextUtils.isEmpty(forgetIdNumber) || TextUtils.isEmpty(mobliephone) || TextUtils.isEmpty(code)|| TextUtils.isEmpty(name)){

            ToastUtil.show(this,R.string.forgetisEmpty);
        }else {
            mPresenter.forgetPwd(name,forgetRealName, forgetIdNumber, mobliephone,code);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.forgetRealNameLay:
            case R.id.forgetRealNameView:
                mPresenter.isVisiable(0);
                break;
            case R.id.forgetIdNumberLay:
            case R.id.forgetIdNumberView:
                mPresenter.isVisiable(1);
                break;
            case R.id.forgetConfirmBtn:
                mPresenter.verifyForfetPwdInfo();
                break;
            case R.id.forgetSendPhoneBtn:
                if(TextUtils.isEmpty(mobliephoneEt.getText().toString())){
                    ToastUtil.show(this,R.string.phonetip);
                    return;
                }
                timeCount = new TimeCount(60000, 1000,forgetSendPhoneBtn,this);
                timeCount.start();

                mPresenter.sendsms(mobliephoneEt.getText().toString(),"3");
                break;
            case R.id.forgetNameLay:
            case R.id.forgetNameView:
                mPresenter.isVisiable(2);
                break;
        }
    }

    /**
     * 获取验证码成功
     * @param bean 验证码信息
     */
    @Override
    public void sendSmsSuccess(ResponseDataBean<Void> bean) {

        if(Constants.REQUEST_SUCCESS.equals(bean.getStatus())){

        }else {
            ToastUtil.show(this,bean.getMessage());
        }
    }

    /**
     * 获取验证码失败
     */
    @Override
    public void sendSmsFail() {

    }

    /*
       * 正则表达式验证身份证号码
       * @param  s_aStr 内容
       * */
    public boolean isCard(String s_aStr)
    {
        String reg15 = new String("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)");
        return  Pattern.matches(reg15, s_aStr);
    }
    /**
     * 校验手机号
     * @param mobile 号码
     * @return 校验通过返回true，否则返回false
     */
    public  boolean isMobile(String mobile) {
        String REGEX_MOBILE = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
}
