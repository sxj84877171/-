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
import com.sxj.carloan.net.ApiServiceModel;
import com.sxj.carloan.ui.InfomationActivity;
import com.sxj.carloan.ui.LoginActivity;
import com.sxj.carloan.ui.MainActivity;
import com.sxj.carloan.ui.investigation.InvestigationMainActivity;
import com.sxj.carloan.util.FileObject;
import com.sxj.carloan.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/6/25.
 */

public class BaseActivity extends AppCompatActivity {

    public static String LOGIN_INFO = "logininfo";

    App app;
    public ApiServiceModel model = new ApiServiceModel();

    private static List<BaseActivity> activityList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (App) getApplication();
        activityList.add(this);
    }

    @Override
    protected void onDestroy() {
        activityList.remove(this);
        super.onDestroy();
    }

    protected boolean isLogin() {
        LoginInfo info = app.getLoginInfo();
        if (info != null) {
//            return info.getToken() != null;
            return info.getUsername() != null;
        }
        return false;
    }

    protected String getUsername() {
        LoginInfo info = app.getLoginInfo();
        if (info != null) {
            return info.getUsername();
        }
        return null;
    }

    protected LoginInfo getLoginInfo() {
        return app.getLoginInfo();
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        if(! (this instanceof LoginActivity)){
            menu.add(1, 200, 200, "退出登录");
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        LogUtil.i("item " + item.getItemId());
        if (item.getItemId() == 200) {
            FileObject.cleanFile(LOGIN_INFO);
            for(BaseActivity activity : activityList){
                if(activity != null) {
                    activity.finish();
                }
            }
            gotoLogin();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void saveUserInfo(LoginInfo info) {
        app.setLoginInfo(info);
        FileObject.saveObject(LOGIN_INFO, info);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void gotoLogin() {
        Intent intent = new Intent();
        intent.setClass(this, LoginActivity.class);
        startActivity(intent);
    }

    protected void goMain() {
        if (getLoginInfo().isDcy()) {
            gotoInverstigation();
            return;
        } else if (getLoginInfo().isYwy()) {
            gotoHomepage();
            return;
        }

        gotoHomepage();
    }

    protected void gotoHomepage() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }

    protected void gotoRole() {

    }

    protected void gotoInverstigation() {
        Intent intent = new Intent();
        intent.setClass(this, InvestigationMainActivity.class);
        startActivity(intent);
    }


    public <T extends View> T getViewById(int id) {
        return (T) findViewById(id);
    }

    public void success() {
        Toast.makeText(this, "成功", Toast.LENGTH_LONG).show();
    }


    public void getPermissionFail() {
        Toast.makeText(this, "获取权限失败", Toast.LENGTH_LONG).show();
    }

    private ProgressDialog progressDialog;

    public void showProcess() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(false);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
        }
        progressDialog.show();
    }

    public void dismiss() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    public Activity getActivity() {
        return this;
    }
}
