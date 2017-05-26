package com.soarsky.car.ui.login;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.CommonParam;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.ui.forget.ForgetPwdActivity;
import com.soarsky.car.ui.home.HomeActivity;
import com.soarsky.car.ui.license.DriviLicenseParam;
import com.soarsky.car.ui.license.DriviLicenseSendParam;
import com.soarsky.car.ui.register.RegisterActivity;
import com.soarsky.car.uitl.KeyboardUtils;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;


/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 登陆
 */

public class LoginActivity extends BaseActivity<LoginPresent,LoginModel> implements LoginView,View.OnClickListener{

    /**
     * 登陆按钮
     */
    private Button loginBtn;
    /**
     * 用户名
     */
    private EditText UserNameEt;
    /**
     * 密码
     */
    private EditText PassWordEt;
    /**
     * 注册
     */
    private Button registerBtn;
    /**
     * 忘记密码
     */
    private TextView forgetTv;

    private LinearLayout forgetLay;

    private ImageView closeView;

    private LinearLayout rootView;
    /**
     * 关闭
     */
    private LinearLayout closeLay;

    private RelativeLayout loginIconLay;

    private ImageView loginIconView;

    private boolean isPassword = false;

    private App app;

    private String userName;

    private String passWord;

    private ProgressDialog progressDialog;

    public final static String TAG = "LoginActivity";

    private ConfirmDriverService confirmDriverService;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);
        SpUtil.init(app);

        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);

        UserNameEt = (EditText) findViewById(R.id.UserNameEt);
        PassWordEt = (EditText) findViewById(R.id.PassWordEt);


        registerBtn = (Button) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(this);

        forgetTv = (TextView) findViewById(R.id.forgetTv);
        forgetTv.setOnClickListener(this);

        closeView = (ImageView) findViewById(R.id.closeView);
        closeView.setOnClickListener(this);

        rootView = (LinearLayout) findViewById(R.id.rootView);
        rootView.setOnClickListener(this);

        closeLay = (LinearLayout) findViewById(R.id.closeLay);
        closeLay.setOnClickListener(this);

        forgetLay = (LinearLayout) findViewById(R.id.forgetLay);
        forgetLay.setOnClickListener(this);

        loginIconLay  = (RelativeLayout) findViewById(R.id.loginIconLay);
        loginIconLay.setOnClickListener(this);

        loginIconView = (ImageView) findViewById(R.id.loginIconView);
        loginIconView.setOnClickListener(this);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent serviceIntent=new Intent(this, ConfirmDriverService.class);
        bindService(serviceIntent,serviceConnection,BIND_AUTO_CREATE);
        setData();
    }

    /**
     * 忘记密码操作成功回调函数
     */
    public void setData(){

        if (app.getForgetName() != null && app.getForgetPassword() != null) {
            UserNameEt.setText(app.getForgetName());
            PassWordEt.setText(app.getForgetPassword());
        }

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.rootView:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                break;
            case R.id.loginBtn:
                mPresenter.verifyLoginInfo(UserNameEt,PassWordEt);
                KeyboardUtils.hideSoftInput(this);
                break;
            case R.id.registerBtn:
                mPresenter.register();
                break;
            case R.id.forgetTv:
            case R.id.forgetLay:
                mPresenter.gotoForgetPage();
                break;
            case R.id.closeView:
            case R.id.closeLay:
                finish();
                break;
            case R.id.loginIconLay:
            case R.id.loginIconView:
                mPresenter.isVisiablePassword();
                break;

        }
    }

    /**
     * 跳转注册界面的回调
     */
    @Override
    public void gotoRegisterrPage(){
        Intent i = new Intent();
        i.setClass(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void initUsernameAndPassword(String username, String password) {
        UserNameEt.setText(username);
        PassWordEt.setText(password);
    }

    /***
     * 登陆成功的回调
     * @param param
     */
    @Override
    public void showSuccess(LoginParam param) {

        CommonParam commonParam = new CommonParam();
        commonParam.setUserName(userName);
        commonParam.setPassWord(passWord);
        commonParam.setIdNo(param.getData().getIdcard());
        commonParam.setIsBind(param.getData().getIsBind());
        commonParam.setOwerPhone(param.getData().getPhone());
        commonParam.setQueryPwd(param.getData().getQueryPwd());
        SpUtil.save(Constants.USERNAME,userName);
        SpUtil.save(Constants.PASSWORD,passWord);
        app.setOnline(true);
        app.setCommonParam(commonParam);
        mPresenter.getAllCarnum(app.getCommonParam().getOwerPhone());

        ToastUtil.show(LoginActivity.this, R.string.loginvicetory);
        Intent i = new Intent();
        i.setClass(LoginActivity.this, HomeActivity.class);
        startActivity(i);
        finish();

    }

    /***
     * 获取车牌号成功的回调
     * @param param
     */
    @Override
    public void getAllCarnumSuccess(CarNumParam param) {

        if(param!=null) {
            if ("0".equals(param.getStatus())) {
                if (param.getData().size() > 0) {
                    app.getCommonParam().setCarNum(param.getData().get(0).getPlateno());
                    app.getCommonParam().setRealName(userName);
                    app.getCommonParam().setAuthCode(param.getData().get(0).getVin());
                }

            }
            DriviLicenseSendParam p = new DriviLicenseSendParam();
            p.setPhone(app.getCommonParam().getOwerPhone());
            p.setIdNo(app.getCommonParam().getIdNo());
            p.setVerCode(Constants.VerCode);
            mPresenter.getElecDriver(p);

        }
    }

    /**
     * 获取车牌号失败的提示
     */
    @Override
    public void getAllCarnumFail() {

//        ToastUtil.show(this,R.string.throwable);

    }

    /**
     * 跳转忘记密码界面回调
     */
    @Override
    public void gotoForgetPage() {
        Intent in = new Intent();
        in.setClass(LoginActivity.this, ForgetPwdActivity.class);
        startActivity(in);
        finish();
    }

    @Override
    protected String getHeaderTitle() {
        return getString(R.string.logintitle);
    }

    /***
     * 登陆失败的回调
     * @param o
     */
    public void showFail(Object o) {
        if(o != null) {
            LoginParam loginParam = (LoginParam) o;
            ToastUtil.show(LoginActivity.this, loginParam.getMessage());

        }else{
//            ToastUtil.show(this,R.string.error);
        }
    }

    /**
     * 登陆报错的回调
     */
    @Override
    public void showonError() {
        ToastUtil.show(this,R.string.throwable);
    }
    /**
     * 验证登陆信息是否为空
     * @param userEt
     * @param passWordEt
     */
    @Override
    public void verifyLoginInfo(EditText userEt, EditText passWordEt) {
        userName = userEt.getText().toString();
        passWord = passWordEt.getText().toString();

        if(TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord) ){
            ToastUtil.show(LoginActivity.this,R.string.isEmpty);
        }else{
            mPresenter.login(userName,passWord);
        }
    }

    /**
     * 密码的可见性
     */
    @Override
    public void isVisiablePassword() {
        if(isPassword == false){
            PassWordEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            loginIconView.setImageResource(R.mipmap.confrim);
            isPassword = true;
        }else{
            PassWordEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            loginIconView.setImageResource(R.mipmap.icon);
            isPassword = false;
        }
    }

    @Override
    public void showLicenseSuccess(DriviLicenseParam param) {

        if(param !=null) {
            if("0".equals(param.getStatus())){
                app.getCommonParam().setDrivingLicence(param.getData().getNum());
                app.getCommonParam().setDrivingType(param.getData().getDrivingType());
                mPresenter.startConnectWork();
            }
        }

//        ToastUtil.show(LoginActivity.this, R.string.loginvicetory);
//        Intent i = new Intent();
//        i.setClass(LoginActivity.this, HomeActivity.class);
//        startActivity(i);
    }

    @Override
    public void showLicenseError() {

//        ToastUtil.show(this,R.string.throwable);
    }

    @Override
    public void showProgess() {

       super.showProgess();

    }

    @Override
    public void stopProgess() {

        super.stopProgess();

    }


    @Override
    protected void onStop() {
        unbindService(serviceConnection);
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    /**
     * 绑定连接车辆服务
     */
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            confirmDriverService=((ConfirmDriverService.LocalBinder) service).getService();
            mPresenter.setConfirmDriverService(confirmDriverService);


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            confirmDriverService=null;
        }
    };



}
