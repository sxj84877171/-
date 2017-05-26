package com.soarsky.car.ui.login;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.soarsky.car.ConstantsUmeng;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.LoginInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.ui.forget.ForgetPwdActivity;
import com.soarsky.car.ui.main.NewMainActivity;
import com.soarsky.car.ui.register.RegisterActivity;
import com.soarsky.car.uitl.KeyboardUtils;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.StringUtils;
import com.soarsky.car.uitl.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import static com.soarsky.car.ConstantsUmeng.FORGET_PWD;
import static com.soarsky.car.ConstantsUmeng.LOGIN_BTN;
import static com.soarsky.car.ConstantsUmeng.REGISTER_BTN;


/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：登陆<br>
 * 该类为 LoginActivity<br>
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
    private TextView registerBtn;
    /**
     * 忘记密码
     */
    private TextView forgetTv;
    /**
     * 忘记密码lay
     */
    private LinearLayout forgetLay;
    /**
     * colseView
     */
    private ImageView closeView;
    /**
     * rootView
     */
    private LinearLayout rootView;

    /**
     * iconlay
     */
    private RelativeLayout loginIconLay;
    /**
     * iconView
     */
    private ImageView loginIconView;
    /**
     * 密码是否可见
     */
    private boolean isPassword = false;
    /**
     * application
     */
    private App app;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 进度条
     */
    private ProgressDialog progressDialog;
    /**
     * 该类的key
     */
    public final static String TAG = "LoginActivity";
    /**
     * service
     */
    private ConfirmDriverService confirmDriverService;

    /**
     * 删除账号
     */
    private RelativeLayout rl_delete_account;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login3;
    }

    @Override
    public void initView() {
        app = (App)getApplication();
        app.addActivity(TAG,this);
        if(!StringUtils.isEmpty(SpUtil.getUserName(this))){
            SpUtil.init(this);
            startHomeActivity();
        }

        loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);

        UserNameEt = (EditText) findViewById(R.id.UserNameEt);
        PassWordEt = (EditText) findViewById(R.id.PassWordEt);

        registerBtn = (TextView) findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(this);

        forgetTv = (TextView) findViewById(R.id.forgetTv);
        forgetTv.setOnClickListener(this);

        /*closeView = (ImageView) findViewById(R.id.closeView);
        closeView.setOnClickListener(this);*/

        rootView = (LinearLayout) findViewById(R.id.rootView);
        rootView.setOnClickListener(this);



        forgetLay = (LinearLayout) findViewById(R.id.forgetLay);
        forgetLay.setOnClickListener(this);

        loginIconLay  = (RelativeLayout) findViewById(R.id.loginIconLay);
        loginIconLay.setOnClickListener(this);

        loginIconView = (ImageView) findViewById(R.id.loginIconView);
        loginIconView.setOnClickListener(this);

        rl_delete_account = (RelativeLayout) findViewById(R.id.rl_delete_account);
        rl_delete_account.setOnClickListener(this);

        UserNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                rl_delete_account.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String qtyString = s.toString().trim();
                if (qtyString.length() > 0){
                    rl_delete_account.setVisibility(View.VISIBLE);
                }else {
                    rl_delete_account.setVisibility(View.INVISIBLE);
                }
            }
        });

        /*findViewById(R.id.closeView).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String uname = UserNameEt.getText().toString();
                if(!TextUtils.isEmpty(uname)){
                    SharedPreferences sp = getSharedPreferences(uname+ SpUtil.SUFFIX,Context.MODE_PRIVATE);
                    if(!"".equals(sp.getString(Constants.USERNAME,""))){
                        SpUtil.saveUserName(LoginActivity.this,uname);
                        SpUtil.init(LoginActivity.this);
                        startHomeActivity();
                    }
                    return true;
                }
                return false;
            }
        });*/

    }
    /**
     * 登录成功跳转到首页
     */
    private void startHomeActivity(){
        Intent i = new Intent();
        i.setClass(LoginActivity.this, NewMainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobclickAgent.onEvent(LoginActivity.this, ConstantsUmeng.OPEN_LOGIN);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        Intent serviceIntent=new Intent(this, ConfirmDriverService.class);
//        bindService(serviceIntent,serviceConnection,BIND_AUTO_CREATE);
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
                MobclickAgent.onEvent(LoginActivity.this,LOGIN_BTN);
                mPresenter.verifyLoginInfo(UserNameEt,PassWordEt);
                KeyboardUtils.hideSoftInput(this);
                break;
            case R.id.registerBtn:
                MobclickAgent.onEvent(LoginActivity.this,REGISTER_BTN);
                mPresenter.register();
//                CRC16 crc16 = new CRC16();
//                UserNameEt.setText("crc="+crc16.crEncodeToBytes("201704016".getBytes()));

                break;
            case R.id.forgetTv:
            case R.id.forgetLay:
                MobclickAgent.onEvent(LoginActivity.this,FORGET_PWD);
                mPresenter.gotoForgetPage();
                break;

            case R.id.loginIconLay:
            case R.id.loginIconView:
                mPresenter.isVisiablePassword();
                break;

            case R.id.rl_delete_account:
                UserNameEt.getText().clear();
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

    }

    @Override
    public void initUsernameAndPassword(String username, String password) {
        UserNameEt.setText(username);
        PassWordEt.setText(password);
    }

    /***
     * 登陆成功的回调
     * @param param 返回参数
     */
    @Override
    public void showSuccess(LoginInfo param) {

        CommonParam commonParam = new CommonParam();
        commonParam.setUserName(userName);
        commonParam.setPassWord(passWord);
        commonParam.setIdNo(param.getIdcard());
        commonParam.setIsBind(param.getIsBind());
        commonParam.setOwerPhone(param.getPhone());
        commonParam.setQueryPwd(param.getQueryPwd());
        commonParam.setRealName(param.getName());

        app.setOnline(true);
        app.setImageUrl(param.getImage());
        app.setCommonParam(commonParam);
        SpUtil.saveUserName(this,userName);
        SpUtil.init(this);

        SpUtil.save(Constants.USERNAME,userName);
        SpUtil.save(Constants.PASSWORD,passWord);
        SpUtil.save(Constants.IDNO,param.getIdcard());
        SpUtil.save(Constants.IS_BIND,param.getIsBind());
        SpUtil.save(Constants.OWNER_PHONE,param.getPhone());
        SpUtil.save(Constants.QUERY_PWD,param.getQueryPwd());
        SpUtil.save(Constants.REAL_NAME,param.getName());
        SpUtil.save(Constants.IMAGE_URL,param.getImage());

//        mPresenter.startConnectWork();
        //登录成功，页面跳转，无需提示
        /*ToastUtil.show(LoginActivity.this, R.string.loginvicetory);*/
        Intent i = new Intent();
        i.setClass(LoginActivity.this, NewMainActivity.class);
        startActivity(i);



    }


    /**
     * 跳转忘记密码界面回调
     */
    @Override
    public void gotoForgetPage() {
        Intent in = new Intent();
        in.setClass(LoginActivity.this, ForgetPwdActivity.class);
        startActivity(in);

    }

    @Override
    protected String getHeaderTitle() {
        return getString(R.string.logintitle);
    }

    /***
     * 登陆失败的回调
     * @param o 失败参数
     */
    public void showFail(ResponseDataBean<LoginInfo> o) {
        if(o != null) {
            ToastUtil.show(LoginActivity.this, o.getMessage());
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
     * @param userEt 用户名
     * @param passWordEt 密码
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
    public void showProgess() {

       super.showProgess();

    }

    @Override
    public void stopProgess() {

        super.stopProgess();

    }


    @Override
    protected void onStop() {
//        unbindService(serviceConnection);
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
