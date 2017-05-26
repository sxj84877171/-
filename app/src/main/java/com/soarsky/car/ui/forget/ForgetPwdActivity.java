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
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.login.LoginActivity;
import com.soarsky.car.uitl.ToastUtil;

import java.util.regex.Pattern;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 忘记密码
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

    private RelativeLayout forgetRealNameLay;

    private ImageView forgetRealNameView;

    private RelativeLayout forgetIdNumberLay;
    /**
     *  身份证号
     */
    private EditText forgetIdNumberEt;

    private ImageView forgetIdNumberView;
    /**
     * 手机号码
     */
    private EditText mobliephoneEt;

    private Button forgetConfirmBtn;

    private boolean isRealName = true;

    private boolean isIdNumber = true;

    private ProgressDialog progressDialog;

    public final static String TAG = "ForgetPwdActivity";

    private App app;

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

    }

    @Override
    protected String getHeaderTitle() {
        return "找回密码";
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
     * @param param
     */
    @Override
    public void showData(ForgetParam param) {

        if(param != null) {

            if("0".equals(param.getStatus())){
                app.setForgetName(param.getData().getUserName());
                app.setForgetPassword(param.getData().getPassword());
                Intent i = new Intent();
                i.setClass(this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        }else{
            ToastUtil.show(this, param.getMessage());
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
     * 0--真实姓名 1--idno
     * @param type
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
        }
    }

    /***
     * 验证忘记密码的信息
     */
    @Override
    public void verifyForfetPwdInfo() {
        String forgetRealName = forgetRealNameEt.getText().toString();
        String forgetIdNumber = forgetIdNumberEt.getText().toString();
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
        if(TextUtils.isEmpty(forgetRealName) || TextUtils.isEmpty(forgetIdNumber) || TextUtils.isEmpty(mobliephone)){

            ToastUtil.show(this,R.string.forgetisEmpty);
        }else {
            mPresenter.forgetPwd(forgetRealName, forgetIdNumber, mobliephone);
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
                ToastUtil.show(this,"forgetRealNameLay");
                mPresenter.isVisiable(0);
                break;
            case R.id.forgetIdNumberLay:
            case R.id.forgetIdNumberView:
                ToastUtil.show(this,"forgetIdNumberLay");
                mPresenter.isVisiable(1);
                break;
            case R.id.forgetConfirmBtn:
                mPresenter.verifyForfetPwdInfo();
                break;
        }
    }




    /*
   * 正则表达式验证身份证号码
   * */
    public boolean isCard(String s_aStr)
    {
        String reg15 = new String("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)");
        return  Pattern.matches(reg15, s_aStr);
    }
    /**
     * 校验手机号
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public  boolean isMobile(String mobile) {
        String REGEX_MOBILE = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
        return Pattern.matches(REGEX_MOBILE, mobile);
    }
}
