package com.soarsky.car.ui.roadside.fee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.roadside.RoadSideView;
import com.soarsky.car.ui.roadside.fee.state.RoadSideFeeStateActivity;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：支付方式
 * 该类为
 */


public class RoadSideFeeActivity extends BaseActivity<RoadSideFeePresent,RoadSideFeeModel> implements RoadSideFeeView,View.OnClickListener{

    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 支付宝
     */
    private LinearLayout roadSideAlipayLay;
    /**
     * 支付宝勾选
     */
    private ImageView alipayView;
    /**
     * 微信
     */
    private LinearLayout roadSideWeChatLay;
    /**
     * 微信勾选
     */
    private ImageView weChatView;
    /**
     *银联
     */
    private LinearLayout roadSideUnionPayLay;
    /**
     * 银联勾选
     */
    private ImageView unionPayView;
    /**
     *确认支付
     */
    private Button roadSidePayBtn;

    private int feeWay = 0;



    @Override
    public int getLayoutId() {
        return R.layout.activity_road_side_fee;
    }

    @Override
    public void initView() {

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.roadsideway));

        roadSideAlipayLay = (LinearLayout) findViewById(R.id.roadSideAlipayLay);
        roadSideAlipayLay.setOnClickListener(this);

        roadSideWeChatLay = (LinearLayout) findViewById(R.id.roadSideWeChatLay);
        roadSideWeChatLay.setOnClickListener(this);

        roadSideUnionPayLay = (LinearLayout) findViewById(R.id.roadSideUnionPayLay);
        roadSideUnionPayLay.setOnClickListener(this);

        alipayView = (ImageView) findViewById(R.id.alipayView);
        weChatView = (ImageView) findViewById(R.id.weChatView);
        unionPayView = (ImageView) findViewById(R.id.unionPayView);

        roadSidePayBtn = (Button) findViewById(R.id.roadSidePayBtn);
        roadSidePayBtn.setOnClickListener(this);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.switchFeeWay(feeWay);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 选择支付方式
     * @param way
     */
    @Override
    public void selectFeeWay(int way) {

        switch (way){
            case 0:
                Intent i =new Intent();
                i.setClass(this,RoadSideFeeStateActivity.class);
                i.putExtra("way",0);
                startActivity(i);
                break;
            case 1:
                Intent in =new Intent();
                in.setClass(this,RoadSideFeeStateActivity.class);
                in.putExtra("way",1);
                startActivity(in);
                break;
            case 2:
                Intent it =new Intent();
                it.setClass(this,RoadSideFeeStateActivity.class);
                it.putExtra("way",2);
                startActivity(it);
                break;

        }

    }

    @Override
    public void switchFeeWay(int i) {
        switch (i){
            case 0:
                alipayView.setImageResource(R.mipmap.fee_gou_d);
                weChatView.setImageResource(R.mipmap.fee_gou_u);
                unionPayView.setImageResource(R.mipmap.fee_gou_u);
                break;
            case 1:
                alipayView.setImageResource(R.mipmap.fee_gou_u);
                weChatView.setImageResource(R.mipmap.fee_gou_d);
                unionPayView.setImageResource(R.mipmap.fee_gou_u);
                break;
            case 2:
                alipayView.setImageResource(R.mipmap.fee_gou_u);
                weChatView.setImageResource(R.mipmap.fee_gou_u);
                unionPayView.setImageResource(R.mipmap.fee_gou_d);
                break;
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.roadSideAlipayLay:
                feeWay = 0;
                mPresenter.switchFeeWay(feeWay);
                break;
            case R.id.roadSideWeChatLay:
                feeWay = 1;
                mPresenter.switchFeeWay(feeWay);
                break;
            case R.id.roadSideUnionPayLay:
                feeWay = 2;
                mPresenter.switchFeeWay(feeWay);
                break;
            case R.id.roadSidePayBtn:
                mPresenter.selectFeeWay(feeWay);
                break;
        }
    }
}
