package com.soarsky.installationmanual.ui.login;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soarsky.installationmanual.App;
import com.soarsky.installationmanual.Constants;
import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.bean.LoginInfo;
import com.soarsky.installationmanual.bean.ResponseDataBean;
import com.soarsky.installationmanual.ui.completeuserinfo.CompleteUserInfoActivity;
import com.soarsky.installationmanual.ui.forgetpwd.ForgetPwdActivity;
import com.soarsky.installationmanual.ui.main.MainActivity;
import com.soarsky.installationmanual.ui.main.fragment.task.backreason.BackReasonActivity;
import com.soarsky.installationmanual.ui.register.RegisterActivity;
import com.soarsky.installationmanual.util.SpUtil;
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
 * 该类为 登陆界面<br>
 */

public class LoginActivity extends BaseActivity<LoginPresent,LoginModel> implements LoginView{
    /**
     * 参考ForgetPwdActivity
     */
    private EditText idCard;
    /**
     * 参考ForgetPwdActivity
     */
    private EditText pwd;
    /**
     * 参考ForgetPwdActivity
     */
    private Button login;
    /**
     * 参考ForgetPwdActivity
     */
    private ImageView idCardImgDel;
    /**
     * 参考ForgetPwdActivity
     */
    private ImageView eye;
    /**
     * 参考ForgetPwdActivity
     */
    private TextView forgetPwd;
    /**
     * 参考ForgetPwdActivity
     */
    private TextView newUser;
    /**
     * 参考ForgetPwdActivity
     */
    private boolean open = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        if(!TextUtils.isEmpty(SpUtil.get(Constants.USERNAME))){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        idCard = (EditText) findViewById(R.id.idCardEt);
        pwd = (EditText) findViewById(R.id.pwdEt);
        login = (Button) findViewById(R.id.loginBtn);
        idCardImgDel = (ImageView) findViewById(R.id.idCardDelImg);
        eye = (ImageView) findViewById(R.id.eye);
        forgetPwd = (TextView) findViewById(R.id.forgetPwd);
        newUser = (TextView) findViewById(R.id.newUser);
        idCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    idCardImgDel.setVisibility(View.GONE);
                }else {
                    idCardImgDel.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        forgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgetPwdActivity.class));
            }
        });
        idCardImgDel.setOnClickListener(new View.OnClickListener() {
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
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(SpUtil.get(Constants.USERNAME))){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    String idCardStr = idCard.getText().toString();
                    String password = pwd.getText().toString();
                    if(TextUtils.isEmpty(idCardStr)){
                        ToastUtil.show(LoginActivity.this,"身份证号不能为空");
                        return;
                    }
                    if(!ValidatorUtils.validatorIdCard(idCardStr)){
                        ToastUtil.show(LoginActivity.this,"身份证号错误");
                        return;
                    }
                    if(TextUtils.isEmpty(password)){
                        ToastUtil.show(LoginActivity.this,"密码不能为空");
                        return;
                    }
                    mPresenter.login(idCardStr,password);
                }

            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void showSuccess() {

        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

}
