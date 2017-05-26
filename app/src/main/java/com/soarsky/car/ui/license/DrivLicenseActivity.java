package com.soarsky.car.ui.license;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.license.view.CircleProgress;
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
 * 该类为 我的驾驶证
 */

public class DrivLicenseActivity extends BaseActivity<DriviLicensePresent,DriviLicenseModel> implements DrivilicenseView,View.OnClickListener{

    /**
     * 返回
     */
    private LinearLayout backLay;

    private TextView topicTv;
    /**
     * 累计公里数
     */
    private TextView licenseDistanceTv;
    /**
     * 准驾车型
     */
    private TextView licenseModelTv;
    /**
     * 下一清分日期
     */
    private TextView licenseDateTv;
    /**
     * 累计积分
     */
    private TextView licenseCentTv;
    /**
     * 当前状态
     */
    private TextView licenseStateTv;
    /**
     * 驾驶证
     */
    private LinearLayout licenseCardLay;
    /**
     * 身份证号
     */
    private TextView drivLicenseTv;
    /**
     * 期止日期
     */
    private TextView licenseEndTimeTv;
    /**
     * 累计积分的icon
     */
    private CircleProgress circleProgress;

    private App app;

    private LicenseInfo info;

    public final static String TAG = "DrivLicenseActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_driv_license;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.licensetitle));

        licenseCardLay = (LinearLayout) findViewById(R.id.licenseCardLay);
        licenseCardLay.setOnClickListener(this);

        licenseDistanceTv = (TextView) findViewById(R.id.licenseDistanceTv);
        licenseModelTv = (TextView) findViewById(R.id.licenseModelTv);
        licenseDateTv = (TextView) findViewById(R.id.licenseDateTv);
        licenseCentTv = (TextView) findViewById(R.id.licenseCentTv);
        licenseStateTv = (TextView) findViewById(R.id.licenseStateTv);
        drivLicenseTv = (TextView) findViewById(R.id.drivLicenseTv);
        licenseEndTimeTv = (TextView) findViewById(R.id.licenseEndTimeTv);

        circleProgress = (CircleProgress) findViewById(R.id.circleProgress);

        info = (LicenseInfo)getIntent().getSerializableExtra("info");
    }



    @Override
    protected void onResume() {
        super.onResume();

        DriviLicenseSendParam p = new DriviLicenseSendParam();
        p.setIdNo(app.getCommonParam().getIdNo());
        p.setPhone(app.getCommonParam().getOwerPhone());
        if(info != null){
            p.setVerCode(info.getVerCode());
        }else {
            p.setVerCode(Constants.VerCode);
        }

        mPresenter.getElecDriver(p);
    }

    @Override
    public void showSuccess(DriviLicenseParam param) {

        if(param != null){


            if("0".equals(param.getStatus())) {

                drivLicenseTv.setText(param.getData().getIdcard());
                licenseModelTv.setText(param.getData().getDrivingType());
                licenseDistanceTv.setText(param.getData().getDistance());
                licenseCentTv.setText(param.getData().getScore());
                licenseDateTv.setText(param.getData().getInitData());
                licenseStateTv.setText("" + param.getData().getStatus());
                licenseEndTimeTv.setText("" + param.getData().getValidStartDate());
                int score = 0;
                try {
                    score = Integer.parseInt(param.getData().getScore());

                }catch (Exception e){}
                circleProgress.setProgress(score);
            }else {
                ToastUtil.show(this,param.getMessage());
                finish();
            }
        }
    }

    @Override
    public void showError() {

        ToastUtil.show(this,R.string.error);
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
            case R.id.licenseCardLay:
                break;
        }
    }
}
