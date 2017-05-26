package com.soarsky.installationmanual.ui.forgetpwd;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;

import com.soarsky.installationmanual.ui.main.MainActivity;
import com.soarsky.installationmanual.ui.register.RegisterActivity;
import com.soarsky.installationmanual.util.ToastUtil;
import com.soarsky.installationmanual.util.ValidatorUtils;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 找回密码界面<br>
 */

public class ForgetPwdActivity extends BaseActivity<ForgetPwdPresent,ForgetPwdModel> implements ForgetPwdView {
    /**
     * 身份证号删除
     */
    private ImageView idCardDel;
    /**
     * 确认按钮
     */
    private Button okBtn;
    /**
     * 密码眼
     */
    private ImageView eye;
    /**
     * 电话输入框
     */
    private EditText phone;
    /**
     * 验证码输入框
     */
    private EditText yanzhengma;
    /**
     * 密码输入框
     */
    private EditText pwd;
    private Button huoquyanzhengmaBtn;
    /**
     * 获取验证码按钮
     */
    private EditText idCard;

    /**
     * 是否可见
     */
    private boolean open = true;
    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void initView() {
        okBtn = (Button) findViewById(R.id.okBtn);
        eye = (ImageView) findViewById(R.id.eye);
        phone = (EditText) findViewById(R.id.phoneEt);
        yanzhengma = (EditText) findViewById(R.id.yanzhengmaEt);
        idCardDel = (ImageView) findViewById(R.id.idCardDelImg);
        idCard = (EditText) findViewById(R.id.idCardEt);
        pwd = (EditText) findViewById(R.id.pwd);
        huoquyanzhengmaBtn = (Button) findViewById(R.id.huoquyanzhengmaBtn);


        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(open){
                    open = false;
                    eye.setImageResource(R.drawable.see_y);
                    pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    open = true;
                    eye.setImageResource(R.drawable.see_n);
                    pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        idCardDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idCard.setText("");
            }
        });

        pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    eye.setVisibility(View.GONE);
                }else {
                    eye.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        huoquyanzhengmaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneStr = phone.getText().toString();
                if(TextUtils.isEmpty(phoneStr)){
                    ToastUtil.show(ForgetPwdActivity.this,"手机号不能为空");
                    return;
                }
                if(!ValidatorUtils.validatorPhone(phoneStr)){
                    ToastUtil.show(ForgetPwdActivity.this,"手机号错误");
                    return;
                }

                mPresenter.sendsms(phoneStr);

            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idCardStr = idCard.getText().toString();
                String password = pwd.getText().toString();
                String phoneStr = phone.getText().toString();
                String yanZhengMaStr = yanzhengma.getText().toString();
                if(TextUtils.isEmpty(idCardStr)){
                    ToastUtil.show(ForgetPwdActivity.this,"身份证号不能为空");
                    return;
                }
                if(!ValidatorUtils.validatorIdCard(idCardStr)){
                    ToastUtil.show(ForgetPwdActivity.this,"身份证号错误");
                    return;
                }
                if(TextUtils.isEmpty(phoneStr)){
                    ToastUtil.show(ForgetPwdActivity.this,"手机号不能为空");
                    return;
                }
                if(!ValidatorUtils.validatorPhone(phoneStr)){
                    ToastUtil.show(ForgetPwdActivity.this,"手机号错误");
                    return;
                }
                if(TextUtils.isEmpty(yanZhengMaStr)){
                    ToastUtil.show(ForgetPwdActivity.this,"验证码不能为空");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    ToastUtil.show(ForgetPwdActivity.this,"密码不能为空");
                    return;
                }

                mPresenter.forgetPwd(phoneStr,yanZhengMaStr,password,password,idCardStr);
            }
        });
    }

    @Override
    protected String getHeaderTitle() {
        return "找回密码";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void showSuccess() {
        ToastUtil.show(ForgetPwdActivity.this,"设置成功");
        startActivity(new Intent(ForgetPwdActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void sendSmsSuccess() {
        ToastUtil.show(ForgetPwdActivity.this,"短信发送成功，请耐心等待");
        huoquyanzhengmaBtn.setEnabled(false);
        huoquyanzhengmaBtn.setBackgroundColor(getResources().getColor(R.color.huoquyanzhengmabg));
        CountDownTimer countDownTimer = new CountDownTimer(60*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                huoquyanzhengmaBtn.setText("已发送(" + millisUntilFinished/1000 + ")");
            }

            @Override
            public void onFinish() {
                huoquyanzhengmaBtn.setBackgroundResource(R.drawable.chongxinfasong);
                huoquyanzhengmaBtn.setText("");
                huoquyanzhengmaBtn.setEnabled(true);

            }
        }.start();
    }

}
