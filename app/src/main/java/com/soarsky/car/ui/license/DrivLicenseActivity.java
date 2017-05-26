package com.soarsky.car.ui.license;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.DriviLicenseParam;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.DriveLicenseInfo;
import com.soarsky.car.bean.DriviLicenseSendParam;
import com.soarsky.car.bean.DrivingLicenseInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.license.view.CircleProgress;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.TimeUtils;
import com.soarsky.car.uitl.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
 * 程序功能：我的驾驶证<br>
 * 该类为 DrivLicenseActivity<br>
 */

public class DrivLicenseActivity extends BaseActivity<DriviLicensePresent,DriviLicenseModel> implements DrivilicenseView,View.OnClickListener{

    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
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
    /**
     * application
     */
    private App app;
    /**
     * 驾驶证信息
     */
    private DriveLicenseInfo info;
    /**
     * 该类的key
     */
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

        info = (DriveLicenseInfo)getIntent().getSerializableExtra("info");
    }



    @Override
    protected void onResume() {
        super.onResume();

//        DriviLicenseSendParam p = new DriviLicenseSendParam();
//        p.setIdNo(app.getCommonParam().getIdNo());
//        p.setPhone(app.getCommonParam().getOwerPhone());
//        if(info != null){
//            p.setVerCode(info.getVerCode());
//        }else {
//            p.setVerCode(Constants.VerCode);
//        }
//
//        mPresenter.getElecDriver(p);
    }

    /**
     * 获取驾驶证成功
     * @param param
     */
    @Override
    public void showSuccess(ResponseDataBean<DrivingLicenseInfo> param) {

        if(param != null){


            if(Constants.REQUEST_SUCCESS.equals(param.getStatus())) {

                drivLicenseTv.setText(param.getData().getIdcard());
                licenseModelTv.setText(param.getData().getDrivingType());
                licenseDistanceTv.setText(param.getData().getDistance());
                licenseCentTv.setText(param.getData().getScore());
                licenseDateTv.setText(roundCleanDate(param.getData().getClearDate()));
                licenseStateTv.setText("" + param.getData().getStatus());

                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date d = null;
                try {
                    d = sDateFormat.parse(param.getData().getValidStartDate());
                    // 车的年检日期
                    int validityPeriod = 6;
                    try {
                        validityPeriod = Integer.parseInt(param.getData().getValidityPeriod());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Date dd = TimeUtils.addYear(d, validityPeriod);
                    licenseEndTimeTv.setText("" + sDateFormat.format(dd));
                } catch (ParseException e) {
                    e.printStackTrace();
                    licenseEndTimeTv.setText(getString(R.string.nodate));
                }

                int score = 0;
                try {
                    score = Integer.parseInt(param.getData().getScore());

                }catch (Exception e){}
                circleProgress.setProgress(score);
            }else {
                ToastUtil.show(this,getString(R.string.bind_license));
                finish();
            }
        }
    }

    private String roundCleanDate(String src){
        String[] strs = src.split("-");
        StringBuilder sb = new StringBuilder();
        try {
            Date date = new Date();
            if (date.getMonth()+1 > Integer.parseInt(strs[1]) || (date.getMonth()+1 == Integer.parseInt(strs[1])  && date.getDate() > Integer.parseInt(strs[2]))) {
                sb.append(Calendar.getInstance().get(Calendar.YEAR) + 1).append("-");
            } else {
                sb.append(Calendar.getInstance().get(Calendar.YEAR)).append("-");
            }
            sb.append(strs[1]).append("-").append(strs[2]);
        }catch (Exception e){
            LogUtil.e(e);
        }

        return sb.toString();


    }

    /**
     * 获取驾驶证失败
     */
    @Override
    public void showError() {

        ToastUtil.show(this,R.string.throwable);
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

    /**
     * 初始化数据
     * @param param
     */
    @Override
    public void initLicenseData(DriviLicenseParam param) {

        drivLicenseTv.setText(param.getDriviLicenseNum());
        licenseModelTv.setText(param.getDriviType());
        licenseDistanceTv.setText(param.getDrivDistance());
        licenseCentTv.setText(param.getDriviLicenseCent());
        licenseDateTv.setText(roundCleanDate(param.getClearDate()));
        licenseStateTv.setText("" + param.getDriviLicenseStatus());

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sDateFormat.parse(param.getDriviLicenseDate());
            // 车的年检日期
            int validityPeriod = 6;
            try {
                validityPeriod = Integer.parseInt(param.getDriviLicenseDate());
            }catch (Exception e){
                e.printStackTrace();
            }
            Date dd = TimeUtils.addYear(d, validityPeriod);
            licenseEndTimeTv.setText("" + sDateFormat.format(dd));
        } catch (ParseException e) {
            e.printStackTrace();
            licenseEndTimeTv.setText(getString(R.string.nodate));
        }

        int score = 0;
        try {
            score = Integer.parseInt(param.getDriviLicenseCent());

        }catch (Exception e){}
        circleProgress.setProgress(score);
    }
}
