package com.soarsky.car.ui.register;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.license.validation.TimeCount;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;

import java.util.regex.Pattern;

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
 * 程序功能：注册<br>
 * 该类为 RegisterActivity<br>
 */

public class RegisterActivity extends BaseActivity<RegisterPresent, RegisterModel> implements RegisterView, View.OnClickListener {
    /**
     * 标题
     */
    private TextView titleTv;
    /**
     * 设置账号
     */
    private EditText setCountEt;
    /**
     * 设置密码
     */
    private EditText setCodeEt;
    /**
     * 确认密码
     */
    private EditText isCodeEt;
    /**
     * 手机号码
     */
    private EditText phoneEt;
    /**
     * 真实姓名
     */
    private EditText realNameEt;
    /**
     * 身份证号
     */
    private EditText idNumberEt;
    /**
     * 确定
     */
    private Button finishBtn;
    /**
     * colseView
     */
    private ImageView closeView;
    /**
     * regroot
     */
    private LinearLayout regroot;
    /**
     * closeLay
     */
    private LinearLayout closeLay;
    /**
     * 勾选协议
     */
    private ImageView gouView;

    private RelativeLayout rl_gouView;
    /**
     * 设置账户
     */
    private ImageView setCountView;
    /**
     * 设置密码
     */
    private ImageView setCodeView;
    /**
     * 确认密码
     */
    private ImageView isCodeView;
    /**
     * 身份证view
     */
    private ImageView idNumberView;
    /**
     * reciptTv
     */
    private TextView reciptTv;
    /**
     * 协议
     */
    private TextView agreeTv;
    /**
     * setCodeLay
     */
    private RelativeLayout setCodeLay;
    /**
     * isCodeLay
     */
    private RelativeLayout isCodeLay;
    /**
     * 是否勾选协议
     */
    private static boolean IsAgree = false;
    /**
     * 账户是否可见
     */
    private boolean isCodePassword = false;
    /**
     * 密码是否可见
     */
    private boolean isPassword = false;
    /**
     * 发送验证码电话
     */
    private TextView sendPhoneBtn;
    /**
     * 验证码
     */
    private EditText verCodeEt;
    /**
     * 删除手机号码
     */
    private RelativeLayout verCodeLay;
    /**
     * 验证倒计时
     */
    private TimeCount timeCount;

    /**
     * 删除验证码
     */
    private ImageView check_code_delete;

    /**
     * 删除设置的账号
     */
    private RelativeLayout rl_account_delete;

    /**
     * 删除设置密码
     */
    private RelativeLayout rl_pwd_delete;

    /**
     *删除确认密码
     */
    private RelativeLayout rl_confirm_pwd_delete;

    /**
     * 删除姓名
     */
    private RelativeLayout rl_real_name_delete;

    private ImageView verCodeView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register3;
    }

    @Override
    public void initView() {

        titleTv = (TextView) findViewById(R.id.titleTv);
        titleTv.setText(getString(R.string.registertitle));

        setCountEt = (EditText) findViewById(R.id.setCountEt);
        setCodeEt = (EditText) findViewById(R.id.setCodeEt);
        isCodeEt = (EditText) findViewById(R.id.isCodeEt);
        phoneEt = (EditText) findViewById(R.id.phoneEt);
        realNameEt = (EditText) findViewById(R.id.realNameEt);
        idNumberEt = (EditText) findViewById(R.id.idNumberEt);
        finishBtn = (Button) findViewById(R.id.finishBtn);
        finishBtn.setOnClickListener(this);
        setCodeEt.setOnFocusChangeListener(new mOnFocusChangeListener(setCodeEt));
        isCodeEt.setOnFocusChangeListener(new mOnFocusChangeListener(isCodeEt));
        closeView = (ImageView) findViewById(R.id.closeView);
        closeView.setOnClickListener(this);
        regroot = (LinearLayout) findViewById(R.id.regroot);
        regroot.setOnClickListener(this);
        closeLay = (LinearLayout) findViewById(R.id.closeLay);
        closeLay.setOnClickListener(this);

        gouView = (ImageView) findViewById(R.id.gouView);
        gouView.setOnClickListener(this);

        rl_gouView = (RelativeLayout) findViewById(R.id.rl_gouView);
        rl_gouView.setOnClickListener(this);

        gouView.setImageResource(R.mipmap.nogou);

        //setCountView = (ImageView) findViewById(R.id.setCountView);
        setCodeView = (ImageView) findViewById(R.id.setCodeView);
        setCodeView.setOnClickListener(this);
        isCodeView = (ImageView) findViewById(R.id.isCodeView);
        isCodeView.setOnClickListener(this);
        idNumberView = (ImageView) findViewById(R.id.idNumberView);
        reciptTv = (TextView) findViewById(R.id.reciptTv);
        reciptTv.setOnClickListener(this);
        agreeTv = (TextView) findViewById(R.id.agreeTv);
        agreeTv.setOnClickListener(this);

        setCodeLay = (RelativeLayout)findViewById(R.id.setCodeLay);
        setCodeLay.setOnClickListener(this);

        isCodeLay = (RelativeLayout) findViewById(R.id.isCodeLay);
        isCodeLay.setOnClickListener(this);

        sendPhoneBtn = (TextView) findViewById(R.id.sendPhoneBtn);
        sendPhoneBtn.setOnClickListener(this);

        verCodeEt = (EditText) findViewById(R.id.verCodeEt);

        verCodeLay = (RelativeLayout) findViewById(R.id.verCodeLay);
        verCodeLay.setOnClickListener(this);

        check_code_delete = (ImageView) findViewById(R.id.check_code_delete);
        check_code_delete.setOnClickListener(this);

        rl_account_delete = (RelativeLayout) findViewById(R.id.rl_account_delete);
        rl_account_delete.setOnClickListener(this);

        rl_pwd_delete = (RelativeLayout) findViewById(R.id.rl_pwd_delete);
        rl_pwd_delete.setOnClickListener(this);

        rl_confirm_pwd_delete = (RelativeLayout) findViewById(R.id.rl_confirm_pwd_delete);
        rl_confirm_pwd_delete.setOnClickListener(this);

        rl_real_name_delete = (RelativeLayout) findViewById(R.id.rl_real_name_delete);
        rl_real_name_delete.setOnClickListener(this);

        verCodeView = (ImageView) findViewById(R.id.verCodeView);

        setCountEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                rl_account_delete.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String qtyString = s.toString().trim();
                if (qtyString.length() > 0){
                    rl_account_delete.setVisibility(View.VISIBLE);
                }else {
                    rl_account_delete.setVisibility(View.INVISIBLE);
                }
            }
        });

        setCodeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                rl_pwd_delete.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String qtyString = s.toString().trim();
                if (qtyString.length() > 0){
                    rl_pwd_delete.setVisibility(View.VISIBLE);
                }else {
                    rl_pwd_delete.setVisibility(View.INVISIBLE);
                }
            }
        });

        isCodeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                rl_confirm_pwd_delete.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String qtyString = s.toString().trim();
                if (qtyString.length() > 0){
                    rl_confirm_pwd_delete.setVisibility(View.VISIBLE);
                }else {
                    rl_confirm_pwd_delete.setVisibility(View.INVISIBLE);
                }
            }
        });

        realNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                rl_real_name_delete.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String qtyString = s.toString().trim();
                if (qtyString.length() > 0){
                    rl_real_name_delete.setVisibility(View.VISIBLE);
                }else {
                    rl_real_name_delete.setVisibility(View.INVISIBLE);
                }
            }
        });

        phoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString().trim();
                if (s1.length() > 0){
                    if (isMobile(s1)){
                        verCodeView.setVisibility(View.VISIBLE);
                        verCodeView.setImageResource(R.mipmap.yes);
                    }else {
                        verCodeView.setVisibility(View.VISIBLE);
                        verCodeView.setImageResource(R.mipmap.vercode_icon);
                    }
                }else {
                    verCodeView.setVisibility(View.INVISIBLE);
                }

            }
        });


        idNumberEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString().trim();

                if (str.length() > 0){
                    if (isCard(str)){
                        idNumberView.setVisibility(View.VISIBLE);
                        idNumberView.setImageResource(R.mipmap.yes);
                    }else {
                        idNumberView.setVisibility(View.VISIBLE);
                        idNumberView.setImageResource(R.mipmap.vercode_icon);
                    }
                }else {
                    idNumberView.setVisibility(View.INVISIBLE);
                }

            }
        });

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
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.regroot:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                break;
            case R.id.finishBtn:
                mPresenter.verifyRegister();
                break;
            case R.id.closeView:
            case R.id.closeLay:
                finish();
                break;
            case R.id.rl_gouView:
            case R.id.gouView:
                mPresenter.selectAgree();
                break;
            case R.id.setCodeView:
            case R.id.setCodeLay:
                mPresenter.isVisiablePassword(0);
                break;
            case R.id.isCodeView:
            case R.id.isCodeLay:
                mPresenter.isVisiablePassword(1);
                break;
            case R.id.reciptTv:
            case R.id.agreeTv:
                mPresenter.gotoAgreePage();
                break;
            case R.id.sendPhoneBtn:
                if(TextUtils.isEmpty(phoneEt.getText().toString())|| !isMobile(phoneEt.getText().toString())){
                    ToastUtil.show(this,R.string.forgetphonetip);
                    return;
                }

                timeCount = new TimeCount(60000, 1000,sendPhoneBtn,this);
                timeCount.start();
                mPresenter.sendsms(phoneEt.getText().toString(),"0");



                break;
            case R.id.verCodeLay:
                phoneEt.setText("");
                break;
            case R.id.check_code_delete:
                verCodeEt.getText().clear();
                break;
            case R.id.rl_account_delete:
                setCountEt.getText().clear();
                break;
            case R.id.rl_pwd_delete:
                setCodeEt.getText().clear();
                break;
            case R.id.rl_confirm_pwd_delete:
                isCodeEt.getText().clear();
                break;
            case R.id.rl_real_name_delete:
                realNameEt.getText().clear();
                break;
        }
    }


    class mOnFocusChangeListener implements View.OnFocusChangeListener {

        private View View;

        public mOnFocusChangeListener(View view) {
            this.View = view;
        }

        @Override
        public void onFocusChange(View view, boolean b) {
            switch (view.getId()) {
                case R.id.isCodeEt:
                    if (!b) {
                        String isCodeTest = isCodeEt.getText().toString();
                        String setCodeTest = setCodeEt.getText().toString();
                        if (!TextUtils.isEmpty(setCodeTest) && !TextUtils.isEmpty(isCodeTest)) {
                            if (!isCodeTest.equals(setCodeTest)) {
                                isCodeEt.setText("");
                                ToastUtil.show(RegisterActivity.this, R.string.password_wrong);
                            }
                        }

                    }
                    break;
                case R.id.setCodeEt:
                    if (!b) {
                        String isCodeTest = isCodeEt.getText().toString();
                        String setCodeTest = setCodeEt.getText().toString();
                        if (!TextUtils.isEmpty(isCodeTest) && !TextUtils.isEmpty(setCodeTest)) {
                            if (!isCodeTest.equals(setCodeTest)) {
                                isCodeEt.setText("");
                                ToastUtil.show(RegisterActivity.this, R.string.password_wrong);
                            }
                        }
                    }
                    break;


            }

        }
    }

    /**
     * 验证编辑框
     * @return boolean true 通过反之
     */
    private boolean verifyData() {
        if (TextUtils.isEmpty(setCountEt.getText().toString())) {
            ToastUtil.show(this,R.string.counttip);
            return false;
        } else if (TextUtils.isEmpty(setCodeEt.getText().toString())) {
            ToastUtil.show(this,R.string.codetip);
            return false;
        } else if (TextUtils.isEmpty(isCodeEt.getText().toString())) {
            ToastUtil.show(this,R.string.iscodetip);
            return false;
        } else if (TextUtils.isEmpty(phoneEt.getText().toString())) {
            ToastUtil.show(this,R.string.phonetip);
            return false;
        } else if (TextUtils.isEmpty(realNameEt.getText().toString())) {
            ToastUtil.show(this,R.string.realnametip);
            return false;
        } else if (TextUtils.isEmpty(idNumberEt.getText().toString())) {
            ToastUtil.show(this,R.string.idtip);
            return false;
        }
     return true;
    }

    /**
     * 注册成功回调
     */
    @Override
    public void showSuccess() {
        ToastUtil.show(RegisterActivity.this,R.string.register_ok);
        SpUtil.save(Constants.USERNAME,setCountEt.getText().toString());
        SpUtil.save(Constants.PASSWORD,setCodeEt.getText().toString());
        finish();
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 注册失败回调
     * @param o
     */
    @Override
    public void showFail(ResponseDataBean o) {
        ToastUtil.show(RegisterActivity.this,o.getMessage());
    }

    /**
     * 注册报错回调
     */
    @Override
    public void showonError() {

        ToastUtil.show(RegisterActivity.this,R.string.throwable);
    }

    /**
     *  验证注册信息
     */
    @Override
    public void verifyRegister() {

        if(verifyData()){
//                    finishBtn.setEnabled(false);
            RegisterSendParam param = new RegisterSendParam();
            param.setUsername(setCountEt.getText().toString());

            String passWord = isCodeEt.getText().toString();
            //校验密码长度
            if (!isPwd(passWord)){
                ToastUtil.show(RegisterActivity.this,R.string.password_min_length);
                return;
            }
            param.setPassword(passWord);

            String IdName = idNumberEt.getText().toString();

            String phoneNum = phoneEt.getText().toString();
            //校验手机号
            if(!isMobile(phoneNum)){
                ToastUtil.show(RegisterActivity.this,R.string.mobile_wrong);
                return;
            }
            param.setPhone(phoneNum);

            param.setIdName(realNameEt.getText().toString());

            //校验身份证号
            if (!isCard(IdName)){
                ToastUtil.show(RegisterActivity.this,R.string.id_num_wrong);
                return;
            }


            param.setIdNo(IdName);

            if(TextUtils.isEmpty(verCodeEt.getText().toString())){
                ToastUtil.show(RegisterActivity.this,R.string.code_wrong);
                return;
            }

            param.setCode(verCodeEt.getText().toString());

            if(!IsAgree){
                ToastUtil.show(RegisterActivity.this,R.string.register_gou);
                return;
            }

            mPresenter.registe(param);

        }
    }

    /***
     * 密码的可见性
     * @param type 0--密码 1--确认密码
     */
    @Override
    public void isVisiablePassword(int type) {
        switch (type){
            case 0:
                if(isPassword == false){
                    setCodeEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    setCodeView.setImageResource(R.mipmap.confrim);
                    isPassword = true;
                }else{
                    setCodeEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    setCodeView.setImageResource(R.mipmap.icon);
                    isPassword = false;
                }
                break;
            case 1:
                if(isCodePassword == false){
                    isCodeEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    isCodeView.setImageResource(R.mipmap.confrim);
                    isCodePassword = true;
                }else{
                    isCodeEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isCodeView.setImageResource(R.mipmap.icon);
                    isCodePassword = false;
                }
                break;
        }
    }

    /**
     * 勾选协议
     */
    @Override
    public void selectAgree() {
        if(IsAgree == false){
            gouView.setImageResource(R.mipmap.gou);
            IsAgree = true;
        }else{
            gouView.setImageResource(R.mipmap.nogou);
            IsAgree = false;
        }
    }

    /**
     * 跳转到注册协议界面
     */
    @Override
    public void gotoAgreePage() {
        Uri uri = Uri.parse("http://192.168.100.16:8080/gtw/AppRegistAgree.html");
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }

    /**
     * 验证码成功
     * @param bean 验证信息参数
     */
    @Override
    public void sendSmsSuccess(ResponseDataBean<Void> bean) {



        if(Constants.REQUEST_SUCCESS.equals(bean.getStatus())){

        }else {
            ToastUtil.show(this,bean.getMessage());
        }
    }

    /**
     * 验证码失败
     */
    @Override
    public void sendSmsFail() {

    }

    /*
            * 正则表达式验证身份证号码
            * */
    public boolean isCard(String s_aStr) {
        String reg15 = new String("(^\\d{15}$)|(^\\d{17}([0-9]|X)$)");
        return  Pattern.matches(reg15, s_aStr);
    }
    /**
     * 校验手机号
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public  boolean isMobile(String mobile) {
        String REGEX_MOBILE = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验密码长度，至少为六位
     * @param pwd
     * @return 校验通过返回true，否则返回false
     */
    public  boolean isPwd(String pwd) {
        String REGEX_PWD = "\\S{6,}";
        return Pattern.matches(REGEX_PWD, pwd);
    }
}
