package com.soarsky.car.ui.illegal.query.set;

import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.LicensePwdBean;
import com.soarsky.car.bean.LicensePwdSendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.uitl.ToastUtil;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：设置查询密码<br>
 * 该类为 IllegalQuerySetActivity<br>
 */


public class IllegalQuerySetActivity extends BaseActivity<IllegalQuerySetPresent,IllegalQuerySetModel> implements IllegalQuerySetView,View.OnClickListener{

    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 完成
     */
    private Button illegalSetFinishBtn;
    /**
     * 设置密码
     */
    private EditText setCodeEt;
    /**
     * 确认密码
     */
    private EditText isCodeEt;
    /**
     * 设置密码view
     */
    private ImageView setCodeView;
    /**
     * 确认密码view
     */
    private ImageView isCodeView;
    /**
     * 设置密码Lay
     */
    private RelativeLayout setCodeLay;
    /**
     * 确认密码Lay
     */
    private RelativeLayout isCodeLay;
    /**
     * 确认密码boolean
     */
    private boolean isCodePassword = false;
    /**
     * 设置密码boolean
     */
    private boolean isPassword = false;
    /**
     * application
     */
    private App app;
    /**
     * 该类的key
     */
    private static final String TAG ="IllegalQuerySetActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_illegal_query_set;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.illegal_query_set));

        illegalSetFinishBtn = (Button) findViewById(R.id.illegalSetFinishBtn);
        illegalSetFinishBtn.setOnClickListener(this);


        setCodeEt = (EditText) findViewById(R.id.setCodeEt);
        isCodeEt = (EditText) findViewById(R.id.isCodeEt);
        setCodeEt.setOnFocusChangeListener(new IllegalQuerySetActivity.mOnFocusChangeListener(setCodeEt));
        isCodeEt.setOnFocusChangeListener(new IllegalQuerySetActivity.mOnFocusChangeListener(isCodeEt));

        setCodeView = (ImageView) findViewById(R.id.setCodeView);
        setCodeView.setOnClickListener(this);
        isCodeView = (ImageView) findViewById(R.id.isCodeView);
        isCodeView.setOnClickListener(this);
        setCodeLay = (RelativeLayout) findViewById(R.id.setCodeLay);
        setCodeLay.setOnClickListener(this);

        isCodeLay = (RelativeLayout) findViewById(R.id.isCodeLay);
        isCodeLay.setOnClickListener(this);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }
    /**
     * 设置查询密码成功
     * @param licensePwdParam 违章信息
     */
    @Override
    public void showIllegalSetSuccess(ResponseDataBean<LicensePwdBean> licensePwdParam) {

        if(licensePwdParam != null){
            if (Constants.REQUEST_SUCCESS.equals(licensePwdParam.getStatus())){

                finish();

            }else {
                ToastUtil.show(this,licensePwdParam.getMessage());
            }
        }
    }
    /**
     * 设置查询密码失败
     */
    @Override
    public void showIllegalSetFail() {

        ToastUtil.show(this,R.string.throwable);
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

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.illegalSetFinishBtn:

                String setCode = setCodeEt.getText().toString();
                String isCode = isCodeEt.getText().toString();
                if (TextUtils.isEmpty(setCode)|| TextUtils.isEmpty(isCode)){
                    ToastUtil.show(this,R.string.illegal_query_set_empty);
                }else {
                    if(setCode.equals(isCode)) {
                        LicensePwdSendParam param = new LicensePwdSendParam();
                        param.setUsername(app.getCommonParam().getUserName());
                        param.setQueryPwd(setCode);
                        mPresenter.setQueryPwd(param);
                    }else {
                        isCodeEt.setText("");
                        ToastUtil.show(IllegalQuerySetActivity.this, R.string.password_wrong);
                    }
                }
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
                                ToastUtil.show(IllegalQuerySetActivity.this, R.string.password_wrong);
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
                                ToastUtil.show(IllegalQuerySetActivity.this, R.string.password_wrong);
                            }
                        }
                    }
                    break;


            }

        }
    }

}
