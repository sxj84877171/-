package com.sxj.carloan;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sxj.carloan.bean.LoginInfo;
import com.sxj.carloan.ui.LoginActivity;

/**
 * Created by admin on 2017/6/25.
 */

public class BaseActivity extends AppCompatActivity {

    App app ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

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

            }
        }
    }

    protected void gotoLogin(){
        Intent intent = new Intent();
        intent.setClass(this,LoginActivity.class);
        startActivity(intent);
    }

    protected void gotoHomepage(){

    }

    protected void gotoRole(){

    }
}
