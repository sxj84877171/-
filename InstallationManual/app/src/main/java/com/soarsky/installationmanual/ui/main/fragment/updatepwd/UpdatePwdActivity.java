package com.soarsky.installationmanual.ui.main.fragment.updatepwd;

import android.content.Intent;
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

import com.soarsky.installationmanual.Constants;
import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.ui.main.MainActivity;
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
 * 该类为 修改密码界面<br>
 */

public class UpdatePwdActivity extends BaseActivity<UpdatePwdPresent,UpdatePwdModel> implements UpdatePwdView {
    private Button okBtn;
    private ImageView eye1;
    private ImageView eye2;
    private ImageView eye3;
    private EditText jiu;
    private EditText xin;
    private EditText que;

    private boolean open2 = true;
    private boolean open1 = true;
    private boolean open3 = true;
    @Override
    public int getLayoutId() {
        return R.layout.activity_update_pwd;
    }

    @Override
    public void initView() {
        okBtn = (Button) findViewById(R.id.okBtn);
        eye1 = (ImageView) findViewById(R.id.eye1);
        eye2 = (ImageView) findViewById(R.id.eye2);
        eye3 = (ImageView) findViewById(R.id.eye3);
        jiu = (EditText) findViewById(R.id.jiuEt);
        xin = (EditText) findViewById(R.id.xinEt);
        que = (EditText) findViewById(R.id.queEt);

        eye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(open1){
                    open1 = false;
                    eye1.setImageResource(R.drawable.see_y);
                    jiu.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    open1 = true;
                    eye1.setImageResource(R.drawable.see_n);
                    jiu.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        jiu.addTextChangedListener(new TextWatcher() {
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
        eye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(open2){
                    open2 = false;
                    eye1.setImageResource(R.drawable.see_y);
                    xin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    open2 = true;
                    eye2.setImageResource(R.drawable.see_n);
                    xin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        xin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    eye2.setVisibility(View.GONE);
                }else {
                    eye2.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        eye3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(open3){
                    open3 = false;
                    eye3.setImageResource(R.drawable.see_y);
                    que.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else {
                    open3 = true;
                    eye3.setImageResource(R.drawable.see_n);
                    que.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        que.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 0){
                    eye3.setVisibility(View.GONE);
                }else {
                    eye3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jiuStr = jiu.getText().toString();
                String xinStr = xin.getText().toString();
                String queStr = que.getText().toString();

                if(TextUtils.isEmpty(jiuStr)){
                    ToastUtil.show(UpdatePwdActivity.this,"旧密码不能为空");
                    return;
                }
                if(TextUtils.isEmpty(xinStr)){
                    ToastUtil.show(UpdatePwdActivity.this,"新密码不能为空");
                    return;
                }
                if(TextUtils.isEmpty(queStr)){
                    ToastUtil.show(UpdatePwdActivity.this,"确认新密码不能为空");
                    return;
                }else {
                    if(!queStr.equals(queStr)){
                        ToastUtil.show(UpdatePwdActivity.this,"两次输入的密码不一致");
                        return;
                    }
                }
                mPresenter.modifyPwd(SpUtil.get(Constants.USERNAME),jiuStr,xinStr);
            }
        });
    }

    @Override
    protected String getHeaderTitle() {
        return "密码修改";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void showSuccess() {
        ToastUtil.show(UpdatePwdActivity.this,"修改成功");
        finish();
    }


}
