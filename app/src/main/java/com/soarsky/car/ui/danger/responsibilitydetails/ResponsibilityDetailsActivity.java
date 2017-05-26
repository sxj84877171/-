package com.soarsky.car.ui.danger.responsibilitydetails;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.danger.responsibilitydetails.haveobjection.HaveObjectionActivity;
import com.soarsky.car.uitl.ToastUtil;

import java.io.Serializable;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */



public class ResponsibilityDetailsActivity extends BaseActivity<ResponsibilityDetailsPresent,ResponsibilityDetailsModel> implements ResponsibilityDetailsView, View.OnClickListener {

    private TextView titleTv;
    private ImageView backView;
    private Button have_objection;
    private Button no_objection;

    private LinearLayout backLay;
    /**
     * 事故时间
     */
    private TextView dangerTimeTv;

    /**
     *事故位置
     */
    private TextView dangerPositionTv;
    /**
     * 车牌号码
     */
    private TextView dangerCarNumTv;
    /**
     * 驾驶证号
     */
    private TextView dangerDriverTv;
    /**
     * 责任划分
     */
    private TextView dangerResponseTv;
    /**
     * 对方车牌号
     */
    private TextView otherDangerCarNumTv;
    /**
     * 对方驾驶证号
     */
    private TextView otherDangerDriverTv;
    /**
     * 对方责任划分
     */
    private TextView otherDangerResponseTv;
    /**
     * 有异议
     */
    private Button dissentBtn;
    /**
     * 无异议
     */
    private Button assentBtn;

    private String id;



    @Override
    public int getLayoutId() {
        return R.layout.activity_dangerbasemessage;
    }

    @Override
    public void initView() {
        titleTv= (TextView) findViewById(R.id.topicTv);
        titleTv.setText(getString(R.string.rending));

        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);
        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        dangerTimeTv = (TextView) findViewById(R.id.dangerTimeTv);
        dangerPositionTv =(TextView) findViewById(R.id.dangerPositionTv);
        dangerCarNumTv = (TextView) findViewById(R.id.dangerCarNumTv);
        dangerDriverTv = (TextView) findViewById(R.id.dangerDriverTv);
        dangerResponseTv = (TextView) findViewById(R.id.dangerResponseTv);
        otherDangerCarNumTv = (TextView) findViewById(R.id.otherDangerCarNumTv);
        otherDangerDriverTv = (TextView) findViewById(R.id.otherDangerDriverTv);
        otherDangerResponseTv = (TextView) findViewById(R.id.otherDangerResponseTv);

        dissentBtn = (Button) findViewById(R.id.dissentBtn);
        dissentBtn.setOnClickListener(this);

        assentBtn = (Button) findViewById(R.id.assentBtn);
        assentBtn.setOnClickListener(this);


        no_objection = (Button) findViewById(R.id.assentBtn);
        no_objection.setOnClickListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getIntent().getStringExtra("id");
        mPresenter.getFastInfo(id);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 获取单条快赔详细信息成功
     * @param responsibilityDetailsParam
     */
    @Override
    public void getFastInfoSuccess(ResponsibilityDetailsParam responsibilityDetailsParam) {

        if (responsibilityDetailsParam != null){
            if ("0".equals(responsibilityDetailsParam.getStatus())){


                dangerTimeTv.setText(responsibilityDetailsParam.getData().getOccurredTime());
                dangerPositionTv.setText(responsibilityDetailsParam.getData().getPosition());
                dangerCarNumTv.setText(responsibilityDetailsParam.getData().getFirstCar());
                dangerDriverTv.setText(responsibilityDetailsParam.getData().getFirstLicense());
                String affirm ="";
                if(responsibilityDetailsParam.getData().getFirstAffirm().equals("1")){
                    affirm = "全责";
                }else if(responsibilityDetailsParam.getData().getFirstAffirm().equals("2")){
                    affirm = "同责";
                }else {
                    affirm = "无责";
                }
                dangerResponseTv.setText(affirm);
                otherDangerCarNumTv.setText(responsibilityDetailsParam.getData().getSecondCar());
                otherDangerDriverTv.setText(responsibilityDetailsParam.getData().getSecondLicense());
                String otherAffirm ="";
                if (responsibilityDetailsParam.getData().getSecondAffirm().equals("1")){
                    otherAffirm ="全责";
                }else if(responsibilityDetailsParam.getData().getSecondAffirm().equals("2")){
                    otherAffirm ="同责";
                }else {
                    otherAffirm ="无责";
                }
                otherDangerResponseTv.setText(otherAffirm);

            }else {
                ToastUtil.show(this,responsibilityDetailsParam.getMessage());
            }
        }
    }
    /**
     * 获取单条快赔详细信息失败
     */
    @Override
    public void getFastInfoFail() {

        ToastUtil.show(this,R.string.throwable);
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.dissentBtn:
                ResponsibilityParam p = new ResponsibilityParam();
                p.setCarnum(dangerCarNumTv.getText().toString());
                p.setDriverLicense(dangerDriverTv.getText().toString());
                p.setOthercarnum(otherDangerCarNumTv.getText().toString());
                p.setOtherdriverLicense(otherDangerDriverTv.getText().toString());
                p.setId(id);
                Intent i = new Intent(ResponsibilityDetailsActivity.this,HaveObjectionActivity.class);
                i.putExtra("param",(Serializable)p);
 				startActivity(i);
                break;
            case R.id.assentBtn:
                final Dialog dialog = new Dialog(this);
                View view1 = View.inflate(ResponsibilityDetailsActivity.this,R.layout.dialog_no_objection,null);
                Button positiveButton = (Button) view1.findViewById(R.id.positiveButton);
                dialog.setContentView(view1);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                    }
                });
                dialog.show();

                break;
        }
    }
}
