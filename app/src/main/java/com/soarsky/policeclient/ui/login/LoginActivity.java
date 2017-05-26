package com.soarsky.policeclient.ui.login;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.Constants;
import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.bean.CheckVersionDataBean;
import com.soarsky.policeclient.bean.LoginDataBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.ui.home.HomeActivity;
import com.soarsky.policeclient.uitl.DeviceUtils;
import com.soarsky.policeclient.uitl.LogUtil;
import com.soarsky.policeclient.uitl.SpUtil;
import com.soarsky.policeclient.uitl.StringUtils;
import com.soarsky.policeclient.uitl.ToastUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.soarsky.policeclient.base.BasePresenter.CHECK_TYPE;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/17<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  登录界面 <br>
 */

public class LoginActivity extends BaseActivity<LoginPresent, LoginModel> implements LoginView, View.OnClickListener {
    /**
     * 更新的文件
     */
    private File futureStudioIconFile;
    /**
     * 用户名
     */
    private EditText userNameEt;   //用户名
    /**
     * 密码
     */
    private EditText passWordEt;  //密码
    /**
     * 登陆
     */
    private Button loginBtn;  //登陆

    private ProgressDialog progressDialog;
    /**
     * app
     */
    private App app;
    /**
     * 用户名
     */
    private String userName; //用户名
    /**
     * 密码
     */
    private String passWord; //密码

    private static final String TAG = "LoginActivity";

    public static final int SHOW_UPDATE_DIALOG = 1;
    public static final int ERROR = 2;
    private TextView tv_splash_version;
    private ProgressDialog pd;
    /**
     * 版本名称
     */
    private String versionName;
    private String pkName;
    private int versionCode;
    /**
     * 版本路径
     */
    private String path;
    /**
     * 更新apk数据
     */
    private CheckVersionDataBean check;


    @Override
    public int getLayoutId() {
        /*getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG, this);

        if(!StringUtils.isEmpty(SpUtil.get(Constants.CONS_USERNAME))){
            startHomeActivity();
        }
        userNameEt = (EditText) findViewById(R.id.userNameEt);
        passWordEt = (EditText) findViewById(R.id.passWordEt);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);

    }

    @Override
    public void showProgess() {
        showProgress();
    }

    @Override
    public void stopProgess() {
        dismissProgress();
    }

    @Override
    public void showSuccess(Object o) {
        if (o != null) {
            app.setUserName(userName);
            ResponseDataBean<LoginDataBean> param = (ResponseDataBean<LoginDataBean>) o;
            app.setImageUri(param.getData().getImage());
            mPresenter.checkVersion(CHECK_TYPE);

        }

    }

    /**
     * 登录成功跳转到首页
     */
    private void startHomeActivity(){
        Intent i = new Intent();
        i.setClass(LoginActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void showFail() {
        ToastUtil.show(LoginActivity.this, R.string.login_net_error);
    }

    /**
     * 文件下载成功
     * @param call
     * @param response
     */
    @Override
    public void loadSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {
        if(response.isSuccessful()) {
        }
        writeResponseBodyToDisk(response.body());
    }

    /**
     * 文件下载失败
     * @param call
     * @param t
     */
    @Override
    public void loadFail(Call<ResponseBody> call, Throwable t) {
        //ToastUtil.show(LoginActivity.this, R.string.load_fail);
    }

    @Override
    public void showFail(Object o) {
        if (o != null) {
            ResponseDataBean<LoginDataBean> param = (ResponseDataBean<LoginDataBean>) o;
            ToastUtil.show(LoginActivity.this, R.string.login_un_pw_error);
        }
    }

    @Override
    public void checkSuccess(CheckVersionDataBean checkVersion) {
        check = checkVersion;
        path = checkVersion.getFileurl();
        versionName = DeviceUtils.getVersionName(LoginActivity.this);
        if (DeviceUtils.compareVersion(versionName,checkVersion.getVersion())){
            // 显示提示对话
            //showUpdateDialog(getString(R.string.update_version));
        }else {
            //ToastUtil.show(LoginActivity.this,R.string.newest);
            startHomeActivity();

        }
    }

    @Override
    public void checkFail(CheckVersionDataBean checkVersion) {
        //ToastUtil.show(LoginActivity.this, R.string.get_fail+checkVersion.getMessage());
        startHomeActivity();
    }

    @Override
    public void showError(Throwable e) {
        startHomeActivity();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.loginBtn:

                userName = userNameEt.getText().toString().trim();
                passWord = passWordEt.getText().toString().trim();

                if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)) {
                    ToastUtil.show(this, R.string.isEmpty);
                } else {
                    mPresenter.login(userName, passWord);
                }

        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 显示自动更新的对话框
     *
     * @param desc
     *            描述
     */
    protected void showUpdateDialog(String desc) {
        AlertDialog.Builder builder = new Builder(this);
        builder.setCancelable(false);
        builder.setTitle(R.string.update);
        builder.setMessage(desc);
        builder.setPositiveButton(R.string.update_now, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //下载文件
                mPresenter.loadFile(path);


            }
        });
        builder.setNegativeButton(R.string.update_next, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startHomeActivity();
            }

        });
        builder.show();
    }

    /**
     * 将下载的apk文件保存到本地
     * @param body
     */
    private void writeResponseBodyToDisk(final ResponseBody body) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // todo change the file location/name according to your needs
                    futureStudioIconFile = new File("/sdcard/"+check.getName()+".apk");

                    InputStream inputStream = null;
                    OutputStream outputStream = null;

                    try {
                        byte[] fileReader = new byte[4096];

                        long fileSize = body.contentLength();
                        long fileSizeDownloaded = 0;

                        inputStream = body.byteStream();

                        outputStream = new FileOutputStream(futureStudioIconFile);

                        while (true) {
                            int read = inputStream.read(fileReader);

                            if (read == -1) {
                                break;
                            }

                            outputStream.write(fileReader, 0, read);
                            outputStream.flush();
                            fileSizeDownloaded += read;

                            LogUtil.d("TAG", "file download: " + fileSizeDownloaded + " of " + fileSize);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // 覆盖安装apk文件
                                Intent intent = new Intent();
                                intent.setAction("android.intent.action.VIEW");
                                intent.addCategory("android.intent.category.DEFAULT");
                                intent.setDataAndType(
                                        Uri.fromFile(futureStudioIconFile),
                                        "application/vnd.android.package-archive");
                                startActivity(intent);
                            }
                        });
                        return ;
                    } catch (IOException e) {
                        return ;
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }

                        if (outputStream != null) {
                            outputStream.close();
                        }
                    }
                } catch (IOException e) {
                    return ;
                }
            }
        }).start();

    }

}
