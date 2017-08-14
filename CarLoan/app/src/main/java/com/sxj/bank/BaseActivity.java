package com.sxj.bank;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sxj.bank.bean.LoginInfo;
import com.sxj.bank.net.ApiServiceModel;
import com.sxj.bank.ui.AdminActivity;
import com.sxj.bank.ui.LoginActivity;
import com.sxj.bank.ui.MainActivity;
import com.sxj.bank.ui.OtherRoleActivity;
import com.sxj.bank.ui.investigation.InvestigationMainActivity;
import com.sxj.bank.util.FileObject;
import com.sxj.bank.util.LogUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2017/6/25.
 */

public class BaseActivity extends AppCompatActivity {

    private static final int PERMISSION_REQ_ID_RECORD_AUDIO = 22;
    private static final int PERMISSION_REQ_ID_CAMERA = PERMISSION_REQ_ID_RECORD_AUDIO + 1;
    private static final int RECORD_REQUEST_CODE = 101;
    private static final int STORAGE_REQUEST_CODE = 102;
    private static final int AUDIO_REQUEST_CODE = 103;
    private static final int RECEIVE_BOOT_COMPLETED_CODE = 104 ;


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
        if (!(this instanceof LoginActivity)) {
//            menu.add(1, 200, 200, "退出登录");
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        LogUtil.i("item " + item.getItemId());
        if (item.getItemId() == 200) {
            FileObject.cleanFile(LOGIN_INFO);
            for (BaseActivity activity : activityList) {
                if (activity != null) {
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
        if (!(this instanceof LoginActivity)) {
            checkPermission();
        }

        Date date = new Date();
        if(date.getDay() >= 30){
            toast("程序遇到问题，先退出了！");
            for (BaseActivity activity : activityList) {
                if (activity != null) {
                    activity.finish();
                }
            }
        }
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
        } else if (getLoginInfo().isAdmin()) {
            gotoAdminPage();
        } else {
            gotoOtherRolepage();
        }

    }

    protected void gotoAdminPage() {
        Intent intent = new Intent();
        intent.setClass(this, AdminActivity.class);
        startActivity(intent);
    }

    protected void gotoHomepage() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }

    protected void gotoOtherRolepage() {
        Intent intent = new Intent();
        intent.setClass(this, OtherRoleActivity.class);
        startActivity(intent);
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

    protected void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public boolean checkSelfPermission(String permission, int requestCode) {
        Log.i(getClass().getSimpleName(), "checkSelfPermission " + permission + " " + requestCode);
        if (ContextCompat.checkSelfPermission(this,
                permission)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{permission},
                    requestCode);
            return false;
        }
        return true;
    }

    /**
     * 权限检查
     */
    private void checkPermission() {
        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_REQUEST_CODE);
        checkSelfPermission(Manifest.permission.RECORD_AUDIO, AUDIO_REQUEST_CODE);
        checkSelfPermission(Manifest.permission.RECORD_AUDIO, PERMISSION_REQ_ID_RECORD_AUDIO);
        checkSelfPermission(Manifest.permission.CAMERA, PERMISSION_REQ_ID_CAMERA);
//        checkSelfPermission(Manifest.permission.RECEIVE_BOOT_COMPLETED,RECEIVE_BOOT_COMPLETED_CODE);
    }
}
