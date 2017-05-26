package com.soarsky.car.ui.roadside.fee.state;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.illegal.adapter.IllegalInfo;
import com.soarsky.car.ui.roadside.RoadSideActivity;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：支付完成
 * 该类为 RoadSideFeeStateActivity
 */


public class RoadSideFeeStateActivity extends BaseActivity<RoadSideFeeStatePresent,RoadSideFeeStateModel> implements RoadSideFeeStateView,View.OnClickListener{

    /**
     * 完成
     */
    private LinearLayout finishLay;
    /**
     * 是否可见
     */
    private LinearLayout feeLay;
    /**
     * 驾照号
     */
    private TextView stateFeeCarNumTv;
    /**
     * 时间
     */
    private TextView stateFeeTimeTv;
    /**
     * 地点
     */
    private TextView stateFeeAddressTv;
    /**
     * 原因
     */
    private TextView stateFeeReasonTv;
    /**
     * 扣分
     */
    private TextView stateFeeCentTv;
    /**
     * 罚款
     */
    private TextView stateFeeNumTv;
    /**
     * 其标志
     */
    private int flag = -1;
    /**
     * 违章信息
     */
    private IllegalInfo info;


    @Override
    public int getLayoutId() {
        return R.layout.activity_road_side_fee_state;
    }

    @Override
    public void initView() {

        finishLay = (LinearLayout) findViewById(R.id.finishLay);
        finishLay.setOnClickListener(this);

        feeLay = (LinearLayout) findViewById(R.id.feeLay);

        stateFeeCarNumTv = (TextView) findViewById(R.id.stateFeeCarNumTv);
        stateFeeTimeTv = (TextView) findViewById(R.id.stateFeeTimeTv);
        stateFeeAddressTv = (TextView) findViewById(R.id.stateFeeAddressTv);
        stateFeeReasonTv = (TextView) findViewById(R.id.stateFeeReasonTv);
        stateFeeCentTv = (TextView) findViewById(R.id.stateFeeCentTv);
        stateFeeNumTv = (TextView) findViewById(R.id.stateFeeNumTv);

    }



    @Override
    protected String getHeaderTitle() {
        return null;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag = getIntent().getIntExtra("flag",0);
        info = (IllegalInfo) getIntent().getSerializableExtra("info");
        if(info != null){
            stateFeeCarNumTv.setText(info.getDriverNo()== null?"":info.getDriverNo());
            stateFeeTimeTv.setText(info.getTime()== null?"":info.getTime());
            stateFeeAddressTv.setText(info.getAddress() == null?"":info.getAddress());
            stateFeeReasonTv.setText(info.getIllegalReason()== null?"":info.getIllegalReason());
            stateFeeCentTv.setText(info.getCent() == null?"":info.getCent());
            stateFeeNumTv.setText(info.getFee()== null?"":info.getFee());
        }
        if(flag ==1){

        }else {
            feeLay.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.finishLay:
                mPresenter.finishFee();
                break;
        }
    }

    /**
     * 完成支付回调
     */
    @Override
    public void finishFee() {
        Intent intent = new Intent(this,RoadSideActivity.class);
        startActivity(intent);
        finish();
    }
}
