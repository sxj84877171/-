package com.soarsky.car.ui.blindterm.validation;

import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.license.validation.TimeCount;
import com.soarsky.car.ui.register.RegisterActivity;
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
 * 程序功能：绑定终端验证功能
 * 该类为 ：绑定终端验证
 */


public class BlindTermValidationActivity extends BaseActivity<BlindTermValidationPresent,BlindTermValidationModel> implements BlindTermValidationView,View.OnClickListener{
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 设置密码
     */
    private EditText setCodeEt;
    /**
     * 确认密码
     */
    private EditText isCodeEt;
    /**
     * 手机号
     */
    private EditText blind_term_phoneEt;
    /**
     * 发送验证码
     */
    private RelativeLayout sendPhoneLay;

    private TextView sendPhoneTv;
    /**
     * 验证码
     */
    private EditText blindTermVerifyEt;
    /**
     * 验证码是否正确
     */
    private RelativeLayout blind_term_valid_deleteLay;

    private ImageView blind_term_valid_deleteView;
    /**
     * 完成
     */
    private Button blindTermValidfinshBtn;

    private ImageView setCodeView;

    private ImageView isCodeView;

    private RelativeLayout setCodeLay;

    private RelativeLayout isCodeLay;

    private boolean isCodePassword = false;

    private boolean isPassword = false;
    /**
     * 验证倒计时
     */
    private BlindTermValidTimeCount timeCount;



    @Override
    public int getLayoutId() {
        return R.layout.activity_blind_term_validation;
    }

    @Override
    public void initView() {
        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.blind_set_password));

        setCodeEt = (EditText) findViewById(R.id.setCodeEt);
        isCodeEt = (EditText) findViewById(R.id.isCodeEt);

        setCodeEt.setOnFocusChangeListener(new BlindTermValidationActivity.mOnFocusChangeListener(setCodeEt));
        isCodeEt.setOnFocusChangeListener(new BlindTermValidationActivity.mOnFocusChangeListener(isCodeEt));

        blind_term_phoneEt = (EditText) findViewById(R.id.blind_term_phoneEt);

        sendPhoneLay = (RelativeLayout) findViewById(R.id.sendPhoneLay);
        sendPhoneLay.setOnClickListener(this);
        sendPhoneTv = (TextView) findViewById(R.id.sendPhoneTv);
        sendPhoneTv.setOnClickListener(this);

        blindTermVerifyEt = (EditText) findViewById(R.id.blindTermVerifyEt);
        blind_term_valid_deleteLay = (RelativeLayout) findViewById(R.id.blind_term_valid_deleteLay);
        blind_term_valid_deleteLay.setOnClickListener(this);

        blind_term_valid_deleteView = (ImageView) findViewById(R.id.blind_term_valid_deleteView);
        blind_term_valid_deleteView.setOnClickListener(this);

        blindTermValidfinshBtn = (Button) findViewById(R.id.blindTermValidfinshBtn);
        blindTermValidfinshBtn.setOnClickListener(this);

        setCodeView = (ImageView) findViewById(R.id.setCodeView);
        setCodeView.setOnClickListener(this);

        isCodeView = (ImageView) findViewById(R.id.isCodeView);
        isCodeView.setOnClickListener(this);

        setCodeLay = (RelativeLayout)findViewById(R.id.setCodeLay);
        setCodeLay.setOnClickListener(this);

        isCodeLay = (RelativeLayout) findViewById(R.id.isCodeLay);
        isCodeLay.setOnClickListener(this);


    }

    @Override
    public void isVisiablePassword(int type) {
        {
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
    }

    /**
     * 验证设置密码是否为空
     */
    @Override
    public void verifyInputInfo() {
        if(verifyData()){
            String setCode = setCodeEt.getText().toString();
            String isCode = isCodeEt.getText().toString();
            String phone = blind_term_phoneEt.getText().toString();

            //校验密码长度
            if (!isPwd(setCode)){
                ToastUtil.show(this,R.string.password_min_length);
                return;
            }

            //校验密码长度
            if (!isPwd(isCode)){
                ToastUtil.show(this,R.string.password_min_length);
                return;
            }

            //校验手机号
            if(!isMobile(phone)){
                ToastUtil.show(this,R.string.mobile_wrong);
                return;
            }



            finish();
        }
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.blindTermValidfinshBtn:

                mPresenter.verifyInputInfo();
                break;
            case R.id.sendPhoneTv:
                //发送验证码
                timeCount = new BlindTermValidTimeCount(60000, 1000,this,sendPhoneTv);
                timeCount.start();
                break;
            case R.id.setCodeView:
            case R.id.setCodeLay:
                mPresenter.isVisiablePassword(0);
                break;
            case R.id.isCodeView:
            case R.id.isCodeLay:
                mPresenter.isVisiablePassword(1);
                break;
        }
    }

    /**
     * 校验填选框是否为空
     * @return
     */
    private boolean verifyData() {

        if (TextUtils.isEmpty(setCodeEt.getText().toString())) {
            ToastUtil.show(this,R.string.blind_code_empty);
            return false;
        } else if (TextUtils.isEmpty(isCodeEt.getText().toString())) {
            ToastUtil.show(this,R.string.blind_iscode_empty);
            return false;
        } else if (TextUtils.isEmpty(blind_term_phoneEt.getText().toString())) {
            ToastUtil.show(this,R.string.blind_phone_empty);
            return false;
        } else if (TextUtils.isEmpty(blindTermVerifyEt.getText().toString())) {
            ToastUtil.show(this,R.string.blind_verity_empty);
            return false;
        }
        return true;
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
                                ToastUtil.show(BlindTermValidationActivity.this, R.string.password_wrong);
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
                                ToastUtil.show(BlindTermValidationActivity.this, R.string.password_wrong);
                            }
                        }
                    }
                    break;


            }

        }
    }

}
