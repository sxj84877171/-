package com.sxj.carloan;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

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
            return info.getToken() != null;
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
}
