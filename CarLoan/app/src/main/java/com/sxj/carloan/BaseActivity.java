package com.sxj.carloan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.sxj.carloan.bean.LoginInfo;
import com.sxj.carloan.ui.LoginActivity;
import com.sxj.carloan.ui.MainActivity;

/**
 * Created by admin on 2017/6/25.
 */

public class BaseActivity extends AppCompatActivity {

    App app ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (App)getApplication();
    }

    protected boolean isLogin(){
        LoginInfo info = app.getLoginInfo();
        if(info != null){
//            return info.getToken() != null;
            return info.getUsername() != null;
        }
        return false;
    }

    protected String getUsername(){
        LoginInfo info = app.getLoginInfo();
        if(info != null){
            return info.getUsername();
        }
        return null;
    }

    protected LoginInfo getLoginInfo(){
        return  app.getLoginInfo();
    }

    protected void saveUserInfo(LoginInfo info){
        app.setLoginInfo(info);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!isLogin()){
            if(!this.getClass().equals(LoginActivity.class)){
//                gotoLogin();
            }
        }
    }

    protected void gotoLogin(){
        Intent intent = new Intent();
        intent.setClass(this,LoginActivity.class);
        startActivity(intent);
    }

    protected void gotoHomepage(){
        Intent intent = new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
    }

    protected void gotoRole(){

    }





    public <T extends View> T getViewById(int id){
        return (T)findViewById(id);
    }

    public void success(){
        Toast.makeText(this,"成功",Toast.LENGTH_LONG).show();
    }


    public void getPermissionFail(){
        Toast.makeText(this,"获取权限失败",Toast.LENGTH_LONG).show();
    }

    private ProgressDialog progressDialog;

    public void showProcess(){
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(false);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    public void dismiss(){
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

    public Activity getActivity(){
        return this;
    }
}
