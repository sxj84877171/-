package com.soarsky.car.ui.danger.responsibilitydetails;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ResponsibilityListDataBean;
import com.soarsky.car.ui.danger.responsibilitydetails.haveobjection.HaveObjectionActivity;
import com.soarsky.car.uitl.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import java.io.Serializable;

import static com.soarsky.car.ConstantsUmeng.HAVE_OBJECTION;
import static com.soarsky.car.ConstantsUmeng.NO_OBJECTION;

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
 * 该类为 责任认定界面
 */



public class ResponsibilityDetailsActivity extends BaseActivity<ResponsibilityDetailsPresent,ResponsibilityDetailsModel> implements ResponsibilityDetailsView, View.OnClickListener {
    /**
     * 标题
     */
    private TextView titleTv;
    /**
     * 返回
     */
    private LinearLayout backLay;
    private ImageView backView;


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
    /**
     * 状态
     */
    private  String  status;
    private App app;

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



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (App) getApplication();


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
    public void getFastInfoSuccess(ResponseDataBean<ResponsibilityListDataBean> responsibilityDetailsParam) {

        if (responsibilityDetailsParam != null){

            if (Constants.REQUEST_SUCCESS.equals(responsibilityDetailsParam.getStatus())){
                if (app.getCommonParam().getCarNum().equals(responsibilityDetailsParam.getData().getFirstCar())){

                dangerTimeTv.setText(responsibilityDetailsParam.getData().getOccurredTime());
                dangerPositionTv.setText(responsibilityDetailsParam.getData().getPosition());
                dangerCarNumTv.setText(responsibilityDetailsParam.getData().getFirstCar());
                dangerDriverTv.setText(responsibilityDetailsParam.getData().getFirstLicense());
                String affirm ="";


                if(TextUtils.isEmpty(responsibilityDetailsParam.getData().getFirstFinalAffirm())){
                    if(responsibilityDetailsParam.getData().getFirstAffirm().equals(Constants.ALL_DUTY)){
                        affirm = getString(R.string.all_duty);
                    }else if(responsibilityDetailsParam.getData().getFirstAffirm().equals(Constants.SAME_DUTY)){
                        affirm = getString(R.string.same_duty);
                    }else {
                        affirm = getString(R.string.no_duty);
                    }
                }else{
                    if(responsibilityDetailsParam.getData().getFirstFinalAffirm().equals(Constants.ALL_DUTY)){
                        affirm = getString(R.string.all_duty);
                    }else if(responsibilityDetailsParam.getData().getFirstFinalAffirm().equals(Constants.SAME_DUTY)){
                        affirm = getString(R.string.same_duty);
                    }else {
                        affirm = getString(R.string.no_duty);
                    }
                }



                dangerResponseTv.setText(affirm);
                otherDangerCarNumTv.setText(responsibilityDetailsParam.getData().getSecondCar());
                otherDangerDriverTv.setText(responsibilityDetailsParam.getData().getSecondLicense());
                String otherAffirm ="";

                if(TextUtils.isEmpty(responsibilityDetailsParam.getData().getFirstFinalAffirm())) {
                    if (responsibilityDetailsParam.getData().getSecondAffirm().equals(Constants.ALL_DUTY)) {
                        otherAffirm = getString(R.string.all_duty);
                    } else if (responsibilityDetailsParam.getData().getSecondAffirm().equals(Constants.SAME_DUTY)) {
                        otherAffirm = getString(R.string.same_duty);
                    } else {
                        otherAffirm = getString(R.string.no_duty);
                    }
                }else{
                    if (responsibilityDetailsParam.getData().getSecondFinalAffirm().equals(Constants.ALL_DUTY)) {
                        otherAffirm = getString(R.string.all_duty);
                    } else if (responsibilityDetailsParam.getData().getSecondFinalAffirm().equals(Constants.SAME_DUTY)) {
                        otherAffirm = getString(R.string.same_duty);
                    } else {
                        otherAffirm = getString(R.string.no_duty);
                    }
                }
                otherDangerResponseTv.setText(otherAffirm);
                status=responsibilityDetailsParam.getData().getStatus();

                /**
                 * 知道到状态为已处理或已复审的时候才显示异议提交按钮
                 */
                if(status.equals(Constants.ALL_DUTY)||status.equals("4")){
                    dissentBtn.setVisibility(View.VISIBLE);
                    assentBtn.setVisibility(View.VISIBLE);
                }

                }else {
                    //firstCar != 本方车辆
                    dangerTimeTv.setText(responsibilityDetailsParam.getData().getOccurredTime());
                    dangerPositionTv.setText(responsibilityDetailsParam.getData().getPosition());
                    dangerCarNumTv.setText(responsibilityDetailsParam.getData().getSecondCar());
                    dangerDriverTv.setText(responsibilityDetailsParam.getData().getSecondLicense());
                    String affirm ="";


                    if(TextUtils.isEmpty(responsibilityDetailsParam.getData().getFirstFinalAffirm())){
                        if(responsibilityDetailsParam.getData().getSecondAffirm().equals(Constants.ALL_DUTY)){
                            affirm = getString(R.string.all_duty);
                        }else if(responsibilityDetailsParam.getData().getSecondAffirm().equals(Constants.SAME_DUTY)){
                            affirm = getString(R.string.same_duty);
                        }else {
                            affirm = getString(R.string.no_duty);
                        }
                    }else{
                        if(responsibilityDetailsParam.getData().getSecondFinalAffirm().equals(Constants.ALL_DUTY)){
                            affirm = getString(R.string.all_duty);
                        }else if(responsibilityDetailsParam.getData().getSecondFinalAffirm().equals(Constants.SAME_DUTY)){
                            affirm = getString(R.string.same_duty);
                        }else {
                            affirm = getString(R.string.no_duty);
                        }
                    }



                    dangerResponseTv.setText(affirm);
                    otherDangerCarNumTv.setText(responsibilityDetailsParam.getData().getFirstCar());
                    otherDangerDriverTv.setText(responsibilityDetailsParam.getData().getFirstLicense());
                    String otherAffirm ="";

                    if(TextUtils.isEmpty(responsibilityDetailsParam.getData().getFirstFinalAffirm())) {
                        if (responsibilityDetailsParam.getData().getFirstAffirm().equals(Constants.ALL_DUTY)) {
                            otherAffirm = getString(R.string.all_duty);
                        } else if (responsibilityDetailsParam.getData().getFirstAffirm().equals(Constants.SAME_DUTY)) {
                            otherAffirm = getString(R.string.same_duty);
                        } else {
                            otherAffirm = getString(R.string.no_duty);
                        }
                    }else{
                        if (responsibilityDetailsParam.getData().getFirstFinalAffirm().equals(Constants.ALL_DUTY)) {
                            otherAffirm = getString(R.string.all_duty);
                        } else if (responsibilityDetailsParam.getData().getFirstFinalAffirm().equals(Constants.SAME_DUTY)) {
                            otherAffirm = getString(R.string.same_duty);
                        } else {
                            otherAffirm = getString(R.string.no_duty);
                        }
                    }
                    otherDangerResponseTv.setText(otherAffirm);
                    status=responsibilityDetailsParam.getData().getStatus();

                    /**
                     * 知道到状态为已处理或已复审的时候才显示异议提交按钮
                     */
                    if(status.equals(Constants.ALL_DUTY)||status.equals("4")){
                        dissentBtn.setVisibility(View.VISIBLE);
                        assentBtn.setVisibility(View.VISIBLE);
                    }
                }
            }else {
                //将后台的message转换成我们自己的内容显示给用户               王松清
                ToastUtil.show(this,R.string.get_data_fail);
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
                if(status.equals(Constants.ALL_DUTY)){
                    MobclickAgent.onEvent(ResponsibilityDetailsActivity.this,HAVE_OBJECTION);
                    ResponsibilityParam p = new ResponsibilityParam();
                    p.setCarnum(dangerCarNumTv.getText().toString());
                    p.setDriverLicense(dangerDriverTv.getText().toString());
                    p.setOthercarnum(otherDangerCarNumTv.getText().toString());
                    p.setOtherdriverLicense(otherDangerDriverTv.getText().toString());
                    p.setId(id);
                    Intent i = new Intent(ResponsibilityDetailsActivity.this,HaveObjectionActivity.class);
                    i.putExtra("param",(Serializable)p);
                    startActivity(i);
                }else{
                    MobclickAgent.onEvent(ResponsibilityDetailsActivity.this,NO_OBJECTION);
                    final Dialog dialog = new Dialog(this);
                    View view1 = View.inflate(ResponsibilityDetailsActivity.this,R.layout.dialog_again_objection,null);
                    Button positiveButton = (Button) view1.findViewById(R.id.positiveButton);
                    dialog.setContentView(view1);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                }
                break;
            case R.id.assentBtn:
                MobclickAgent.onEvent(ResponsibilityDetailsActivity.this,NO_OBJECTION);
                final Dialog dialog2 = new Dialog(this);
                View view2 = View.inflate(ResponsibilityDetailsActivity.this,R.layout.dialog_no_objection,null);
                Button positiveButton2 = (Button) view2.findViewById(R.id.positiveButton);
                dialog2.setContentView(view2);
                positiveButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog2.dismiss();
                    }
                });
                dialog2.show();

                break;
        }
    }
}
