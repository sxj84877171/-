package com.soarsky.car.ui.register;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;

import java.util.regex.Pattern;

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
 * 该类为 注册
 */

public class RegisterActivity extends BaseActivity<RegisterPresent, RegisterModel> implements RegisterView, View.OnClickListener {

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

    private ImageView closeView;

    private LinearLayout regroot;

    private LinearLayout closeLay;

    private ImageView gouView;

    private ImageView setCountView;

    private ImageView setCodeView;

    private ImageView isCodeView;

    private ImageView idNumberView;

    private TextView reciptTv;

    private TextView agreeTv;

    private RelativeLayout setCodeLay;

    private RelativeLayout isCodeLay;

    private static boolean IsAgree = false;

    private boolean isCodePassword = false;

    private boolean isPassword = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
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

        gouView.setImageResource(R.mipmap.nogou);

        setCountView = (ImageView) findViewById(R.id.setCountView);
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
    public void showFail(Object o) {
        RegisterParam registerParam= (RegisterParam) o;
        ToastUtil.show(RegisterActivity.this,registerParam.getMessage());
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
