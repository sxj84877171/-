package com.soarsky.car.ui.drivrecord;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.LicensePwdBean;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.drivrecord.map.DrivingRecordMapActivity;
import com.soarsky.car.bean.LicensePwdSendParam;
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
 * 程序功能：行驶记录
 * 该类为 DrivRecordActivity
 */

public class DrivRecordActivity extends BaseActivity<DrivRecordPresent,DrivRecordModel> implements DrivRecordView,View.OnClickListener{

    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 检测智能终端是否含有sim,没有则可见
     */
    private RelativeLayout simLay;

    /**
     * 展示身份验证
     */
    private LinearLayout verifyLay;
    /**
     * 车牌号
     */
    private EditText drivRecordNumberEt;
    /**
     * 密码
     */
    private EditText drivRecordPwdEt;
    /**
     * 确定
     */
    private Button drivRecordSureBtn;
    /**
     * 应用
     */
    private App app;
    /**
     * 该类的key
     */
    private static final String TAG ="DrivRecordActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_driv_record;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.driv_record));

        verifyLay = (LinearLayout) findViewById(R.id.verifyLay);
        drivRecordNumberEt = (EditText)findViewById(R.id.drivRecordNumberEt);
        drivRecordPwdEt = (EditText) findViewById(R.id.drivRecordPwdEt);

        drivRecordSureBtn = (Button) findViewById(R.id.drivRecordSureBtn);
        drivRecordSureBtn.setOnClickListener(this);


        if(app.getCommonParam().getCarNum()!=null){
            drivRecordNumberEt.setText(""+app.getCommonParam().getCarNum());
            drivRecordNumberEt.setEnabled(false);
        }


    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.drivRecordSureBtn:
                String recordNum = drivRecordNumberEt.getText().toString();
                String recordPwd = drivRecordPwdEt.getText().toString();
                if(TextUtils.isEmpty(recordNum)||TextUtils.isEmpty(recordPwd)){

                    ToastUtil.show(this,R.string.modifyisEmpty);

                }else {
                    LicensePwdSendParam licensePwdSendParam = new LicensePwdSendParam();
                    licensePwdSendParam.setUsername(App.getApp().getCommonParam().getUserName());
                    licensePwdSendParam.setQueryPwd(drivRecordPwdEt.getText().toString());
                    mPresenter.setQueryPwd(licensePwdSendParam);
                }
                break;
            case R.id.backLay:
                finish();
                break;
        }
    }

    /**
     * 设置查询密码成功
     * @param licensePwdParam  查询密码信息参数
     */
    @Override
    public void onSuccess(ResponseDataBean<LicensePwdBean> licensePwdParam) {
        if(licensePwdParam != null) {
            if(Constants.REQUEST_SUCCESS.equals(licensePwdParam.getStatus())) {
                Intent i;
                App.getApp().getCommonParam().setQueryPwd(drivRecordPwdEt.getText().toString());
                i = new Intent(DrivRecordActivity.this, DrivingRecordMapActivity.class);
                startActivity(i);
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
    public void onError() {
        ToastUtil.show(this, R.string.throwable);
    }
}
