package com.soarsky.car.ui.license.validation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.car.CarActivity;
import com.soarsky.car.ui.license.DrivLicenseActivity;
import com.soarsky.car.ui.license.LicenseInfo;
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
 * 该类为 驾驶证验证
 */

public class LicenseValidationActivity extends BaseActivity<LicenseValidationPresent,LicenseValidationModel> implements LicenseValidationView,View.OnClickListener{

    /**
     * 返回
     */
    private LinearLayout backLay;

    private TextView topicTv;

    private App app;

    public final static String TAG = "LicenseValidationActivity";
    /**
     *  姓名
     */
    private EditText licenseValidNameEt;
    /**
     * 身份证号
     */
    private EditText licenseValidCardEt;
    /**
     * 手机号
     */
    private EditText licenseValidPhoneEt;
    /**
     * 发送验证码
     */
    private Button licenseValidPhoneBtn;
    /**
     * 验证码
     */
    private EditText licenseValidCodeEt;
    /**
     * 确定
     */
    private Button licenseSureBtn;

    private String licenseValidCard;

    private String licenseValidName;

    private String licenseValidPhone;

    private String licenseValidCode ="zer1";
    /**
     * 验证倒计时
     */
    private TimeCount timeCount;

    private int flag = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_license_validation;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.licensetitle));

        licenseValidNameEt = (EditText) findViewById(R.id.licenseValidNameEt);
        licenseValidCardEt = (EditText) findViewById(R.id.licenseValidCardEt);
        licenseValidPhoneEt = (EditText) findViewById(R.id.licenseValidPhoneEt);
        licenseValidCodeEt = (EditText) findViewById(R.id.licenseValidCodeEt);

        licenseValidPhoneBtn = (Button) findViewById(R.id.licenseValidPhoneBtn);
        licenseValidPhoneBtn.setOnClickListener(this);

        licenseSureBtn = (Button) findViewById(R.id.licenseSureBtn);
        licenseSureBtn.setOnClickListener(this);

        initData();
    }

    public void initData(){

        licenseValidNameEt.setText(""+app.getCommonParam().getUserName());
        licenseValidCardEt.setText(""+app.getCommonParam().getIdNo());
        licenseValidPhoneEt.setText(""+app.getCommonParam().getOwerPhone());
        licenseValidCodeEt.setText(""+licenseValidCode);

    }


    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.licenseSureBtn:

                licenseValidCard = licenseValidCardEt.getText().toString().trim();
                licenseValidName = licenseValidNameEt.getText().toString().trim();
                licenseValidPhone = licenseValidPhoneEt.getText().toString().trim();
                licenseValidCode = licenseValidCodeEt.getText().toString().trim();
                if(TextUtils.isEmpty(licenseValidCard) || TextUtils.isEmpty(licenseValidName) || TextUtils.isEmpty(licenseValidPhone)
                        || TextUtils.isEmpty(licenseValidCode)){
                    ToastUtil.show(this,R.string.licensevalidEmpty);
                }else{
                    LicenseInfo info = new LicenseInfo();
                    info.setIdNo(licenseValidCard);
                    info.setPhone(licenseValidPhone);
                    info.setVerCode(licenseValidCode);
                    Intent i = new Intent();
                    i.setClass(this, DrivLicenseActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("info", info);
                    i.putExtras(b);
                    startActivity(i);
                    finish();

                }

                break;
            case R.id.licenseValidPhoneBtn:

                timeCount = new TimeCount(60000, 1000,licenseValidPhoneBtn,this);
                timeCount.start();
                break;
        }

    }

}
