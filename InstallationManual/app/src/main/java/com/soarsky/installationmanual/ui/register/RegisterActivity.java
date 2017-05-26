package com.soarsky.installationmanual.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.bean.LoginInfo;
import com.soarsky.installationmanual.bean.RegisterSendBean;
import com.soarsky.installationmanual.bean.ResponseDataBean;
import com.soarsky.installationmanual.ui.completeuserinfo.CompleteUserInfoActivity;
import com.soarsky.installationmanual.ui.login.LoginActivity;
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
 * 该类为 注册界面<br>
 */

public class RegisterActivity extends BaseActivity<RegisterPresent,RegisterModel> implements RegisterView{

    private Button regBtn;
    private ImageView idCardDel;
    private ImageView eye;
    private ImageView eye1;
    private EditText name;
    private EditText idCard;
    private EditText phone;
    private EditText yanzhengma;
    private EditText pwd;
    private EditText pwdAgain;
    private Button huoquyanzhengmaBtn;
    private ImageView state;

    private boolean stateBool;
    private boolean open = true;
    private boolean open1 = true;
    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        regBtn = (Button) findViewById(R.id.regBtn);
        idCardDel = (ImageView) findViewById(R.id.idCardDelImg);
        eye = (ImageView) findViewById(R.id.eye);
        eye1 = (ImageView) findViewById(R.id.eye1);
        name = (EditText) findViewById(R.id.nameEt);
        idCard = (EditText) findViewById(R.id.idCardEt);
        phone = (EditText) findViewById(R.id.phoneEt);
        yanzhengma = (EditText) findViewById(R.id.yanzhengmaEt);
        pwd = (EditText) findViewById(R.id.pwd);
        pwdAgain = (EditText) findViewById(R.id.pwdAgainEt);
        huoquyanzhengmaBtn = (Button) findViewById(R.id.huoquyanzhengmaBtn);
        state = (ImageView) findViewById(R.id.state);

        state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stateBool){
                    stateBool = false;
                    state.setImageResource(R.drawable.gouxuanno);
                }else {
                    stateBool = true;
                    state.setImageResource(R.drawable.gouxuanyes);
                }
            }
        });

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

        eye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(open1){
                    open1 = false;
                    eye1.setImageResource(R.drawable.see_y);
                    pwdAgain.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    open1 = true;
                    eye1.setImageResource(R.drawable.see_n);
                    pwdAgain.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
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

        pwdAgain.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    eye1.setVisibility(View.GONE);
                }else {
                    eye1.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        idCardDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idCard.setText("");
            }
        });

        idCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    idCardDel.setVisibility(View.GONE);
                }else {
                    idCardDel.setVisibility(View.VISIBLE);
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
                    ToastUtil.show(RegisterActivity.this,"手机号不能为空");
                    return;
                }
                if(!ValidatorUtils.validatorPhone(phoneStr)){
                    ToastUtil.show(RegisterActivity.this,"手机号错误");
                    return;
                }
                mPresenter.sendsms(phoneStr);

            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idCardStr = idCard.getText().toString();
                String password = pwd.getText().toString();
                String pwdAgainStr = pwdAgain.getText().toString();
                String nameStr = name.getText().toString();
                String phoneStr = phone.getText().toString();
                String yanZhengMaStr = yanzhengma.getText().toString();
                if(TextUtils.isEmpty(nameStr)){
                    ToastUtil.show(RegisterActivity.this,"姓名不能为空");
                    return;
                }
                if(TextUtils.isEmpty(idCardStr)){
                    ToastUtil.show(RegisterActivity.this,"身份证号不能为空");
                    return;
                }
                if(!ValidatorUtils.validatorIdCard(idCardStr)){
                    ToastUtil.show(RegisterActivity.this,"身份证号错误");
                    return;
                }
                if(TextUtils.isEmpty(phoneStr)){
                    ToastUtil.show(RegisterActivity.this,"手机号不能为空");
                    return;
                }
                if(!ValidatorUtils.validatorPhone(phoneStr)){
                    ToastUtil.show(RegisterActivity.this,"手机号错误");
                    return;
                }
                if(TextUtils.isEmpty(yanZhengMaStr)){
                    ToastUtil.show(RegisterActivity.this,"验证码不能为空");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    ToastUtil.show(RegisterActivity.this,"密码不能为空");
                    return;
                }else {
                    if(!password.equals(pwdAgainStr)){
                        ToastUtil.show(RegisterActivity.this,"两次输入的密码不一致");
                        return;
                    }
                }
                if(!stateBool){
                    ToastUtil.show(RegisterActivity.this,"请勾选接受GTW系统");
                    return;
                }
                RegisterSendBean registerSendBean  = new RegisterSendBean();
                registerSendBean.setRealName(nameStr);
                registerSendBean.setIdNo(idCardStr);
                registerSendBean.setPhone(phoneStr);
                registerSendBean.setVcode(yanZhengMaStr);
                registerSendBean.setPassword(password);
                registerSendBean.setConPassword(pwdAgainStr);
                mPresenter.register(registerSendBean);
            }
        });
    }

    @Override
    protected String getHeaderTitle() {
        return "注册";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void showSuccess() {
        Intent intent = new Intent(RegisterActivity.this, CompleteUserInfoActivity.class);
        intent.putExtra("name",name.getText().toString());
        String sex = null;
        if(Integer.parseInt(idCard.getText().toString().charAt(16)+"")%2==0){
            sex = "女";
        }else {
            sex = "男";
        }
        intent.putExtra("sex",sex);
        intent.putExtra("phone",phone.getText().toString());
        intent.putExtra("idCard",idCard.getText().toString());
        intent.putExtra("type","1");
        startActivity(intent);
    }

    @Override
    public void sendSmsSuccess() {
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
        ToastUtil.show(RegisterActivity.this,"短信发送成功，请耐心等待");
    }

}
