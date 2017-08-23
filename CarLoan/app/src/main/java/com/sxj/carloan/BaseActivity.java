package com.sxj.carloan;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sxj.carloan.bean.LoginInfo;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.ui.MainActivity;
import com.sxj.carloan.ui.investigation.DiaoChaYuanWeiFu;
import com.sxj.carloan.util.DateUtil;
import com.sxj.carloan.util.FileUtil;
import com.sxj.carloan.yewuyuan.BaseInfotmaitionCalcActivity;
import com.sxj.carloan.yewuyuan.YeWuMainPage;
import com.sxj.carloan.net.ApiServiceModel;
import com.sxj.carloan.ui.AdminActivity;
import com.sxj.carloan.ui.LoginActivity;
import com.sxj.carloan.ui.OtherRoleActivity;
import com.sxj.carloan.ui.investigation.InvestigationMainActivity;
import com.sxj.carloan.util.FileObject;
import com.sxj.carloan.util.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
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
    private static final int RECEIVE_BOOT_COMPLETED_CODE = 104;
    private static final int ACCESS_FINE_LOCATION = 105;
    private static final int ACCESS_COARSE_LOCATION = 106;


    public static String LOGIN_INFO = "logininfo";

    App app;
    public ApiServiceModel model = new ApiServiceModel();

    private static List<BaseActivity> activityList = new ArrayList<>();

    public ServerBean.RowsBean loan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (App) getApplication();
        loan = ApplicationInfoManager.getInstance().getInfo();
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
            menu.add(1, 200, 200, "退出登录");
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
        loan = ApplicationInfoManager.getInstance().getInfo();
        if (!(this instanceof LoginActivity)) {
            setTitle(getUsername() + ",欢迎您！");
            checkPermission();
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
//        intent.setClass(this, YeWuMainPage.class);
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
       toast("成功");
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
        checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION, ACCESS_FINE_LOCATION);
        checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION, ACCESS_COARSE_LOCATION);
//        checkSelfPermission(Manifest.permission.RECEIVE_BOOT_COMPLETED,RECEIVE_BOOT_COMPLETED_CODE);
    }

    public AlertDialog createAlertDialog(String[] args, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(args, listener);
        return builder.create();
    }

    public Location getLastKnownLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = LocationManager.GPS_PROVIDER;// 指定LocationManager的定位方法
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }
        return locationManager.getLastKnownLocation(provider);// 调用getLastKnownLocation()方法获取当前的位置信息
    }

    public void gotoCalcActivity(ServerBean.RowsBean bean, int from) {
        Intent intent = new Intent(this, BaseInfotmaitionCalcActivity.class);
        intent.putExtra("loan", bean);
        intent.putExtra("ywy", from);
        startActivity(intent);
    }

    public void back(int index) {
        for (int i = index; i > 0; i--) {
            if (activityList.size() - 1 - index + i >= 0) {
                activityList.get(activityList.size() - 1 - index + i).finish();
            }
        }
    }

    /**
     * 文字水印
     *
     * @param pressText 水印文字
     * @param targetImg 目标图片
     * @param fontName  字体名称
     * @param fontStyle 字体样式
     * @param fontSize  字体大小
     * @param x         修正值
     * @param y         修正值
     * @param alpha     透明度
     */
    public File pressText(String pressText, String locationText, String addr,
                          Bitmap targetImg, String fontName, int fontStyle, int color,
                          int fontSize, int x, int y, int alpha) {
        if (null == targetImg) {
            return null;
        }
        try {
            Bitmap bmp = targetImg;
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            Bitmap mbmpTest = Bitmap
                    .createBitmap(width, height, Bitmap.Config.RGB_565);
            Canvas canvasTemp = new Canvas(mbmpTest);
            Typeface font = Typeface.create(fontName, fontStyle);

            Paint p = new Paint();
            canvasTemp.drawBitmap(bmp, 0, 0, p);

            p.setColor(color);
            p.setTypeface(font);
            p.setTextSize(fontSize);
            // p.setAlpha(alpha);
            canvasTemp.drawText(pressText, x, (height - fontSize * 3) + y, p);
            canvasTemp
                    .drawText(locationText, x, (height - fontSize * 2) + y, p);
            canvasTemp.drawText(addr, x, (height - fontSize) + y, p);
            File imageFileDir = FileUtil.getFileFolder();
            if (!imageFileDir.exists()) {
                imageFileDir.mkdirs();
            }
            String filename = DateUtil.getImageDate() + ".jpg";
            File file = new File(imageFileDir, filename);
            OutputStream bos = new FileOutputStream(file);
            bmp.recycle();
            mbmpTest.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            mbmpTest.recycle();
            bos.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void gotoWeiFuXinXi(ServerBean.RowsBean bean) {
        Intent intent = new Intent(this, DiaoChaYuanWeiFu.class);
        intent.putExtra("loan", bean);
        startActivity(intent);
    }

    public double getDoubleByString(String str) {
        double ret = 0;
        if (str != null) {
            try {
                ret = Double.parseDouble(str);
            } catch (Exception ex) {
            }
        }
        return ret;
    }

    @Override
    protected void onPause() {
        super.onPause();
        ApplicationInfoManager.getInstance().setInfo(loan);
    }


}
