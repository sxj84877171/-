package com.soarsky.car.ui.roadside.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.roadside.detail.RoadSideDetailActivity;
import com.soarsky.car.ui.roadside.rescue.RescueParam;
import com.soarsky.car.uitl.TimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：确认订单<br>
 * 该类为 确认界面<br>
 */


public class RoadSideOrderActivity extends BaseActivity<RoadSideOrderPresent,RoadSideOrderModel> implements RoadSideOrderView,View.OnClickListener{

    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 服务图标
     */
    private ImageView roadSideLogoSeverView;
    /**
     * 服务名称
     */
    private TextView roadSideOrderSeverTv;
    /**
     * 距离
     */
    private TextView roadSideSeverDistanceTv;
    /**
     * 服务费用
     */
    private TextView roadSideOrderFeeTv;
    /**
     * 预约达到时间
     */
    private TextView roadSideOrderTimeTv;
    /**
     * 商家电话
     */
    private TextView roadSideOrderTelTv;
    /**
     * 车牌号
     */
    private TextView roadSideOrderCarNumTv;
    /**
     * 车牌类型
     */
    private TextView roadSideCarTypeTv;
    /**
     * 救援位置
     */
    private TextView roadSideOrderPositionTv;
    /**
     * 联系电话
     */
    private TextView roadSideOrderPhoneTv;
    /**
     * roadSideOrderTelLay
     */
    private RelativeLayout roadSideOrderTelLay;
    /**
     * 提交
     */
    private Button roadSideOrderCommitBtn;
    /**
     * 救援参数
     */
    private RescueParam param;

    /**
     * 服务项目
     */
    private TextView roadSideOrderType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_road_side_order3;
    }

    @Override
    public void initView() {

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.roadsideorder));
        roadSideLogoSeverView = (ImageView) findViewById(R.id.roadSideLogoSeverView);
        roadSideOrderSeverTv = (TextView) findViewById(R.id.roadSideOrderSeverTv);
        roadSideSeverDistanceTv = (TextView) findViewById(R.id.roadSideSeverDistanceTv);
        roadSideOrderFeeTv = (TextView) findViewById(R.id.roadSideOrderFeeTv);
        roadSideOrderTimeTv = (TextView) findViewById(R.id.roadSideOrderTimeTv);
        roadSideOrderTelTv = (TextView) findViewById(R.id.roadSideOrderTelTv);
        roadSideOrderCarNumTv = (TextView) findViewById(R.id.roadSideOrderCarNumTv);
        roadSideOrderPositionTv = (TextView) findViewById(R.id.roadSideOrderPositionTv);
        roadSideCarTypeTv = (TextView) findViewById(R.id.roadSideCarTypeTv);
        roadSideOrderPhoneTv = (TextView) findViewById(R.id.roadSideOrderPhoneTv);
        roadSideOrderCommitBtn = (Button) findViewById(R.id.roadSideOrderCommitBtn);
        roadSideOrderCommitBtn.setOnClickListener(this);

        roadSideOrderType = (TextView) findViewById(R.id.roadSideOrderType);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        param = (RescueParam) getIntent().getSerializableExtra("rescueParam");
        if(param != null){
            try {
                roadSideOrderCarNumTv.setText(param.getCarNum());
                roadSideOrderPositionTv.setText(param.getCarPosition());
                roadSideCarTypeTv.setText(param.getCarType());
                roadSideOrderPhoneTv.setText(param.getPhone());
                roadSideSeverDistanceTv.setText(param.getDistance());
                roadSideOrderFeeTv.setText(param.getSeverFee());
                roadSideOrderSeverTv.setText(param.getCompany());
                roadSideOrderType.setText(param.getType());
                String time = param.getStartTime();
                String reachTime = param.getReachTime();
//                long reach_time = Long.parseLong(reachTime)*60*1000;
//                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                Date date = sDateFormat.parse(time);
//                String arriveTime = TimeUtils.milliseconds2String(date.getTime()+reach_time);
                roadSideOrderTimeTv.setText(""+reachTime);
                roadSideOrderTelTv.setText(param.getSeverPhone());
            }catch (Exception e) {
                e.printStackTrace();
            }
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
            case R.id.roadSideOrderCommitBtn:
                mPresenter.gotopage();
                break;
        }
    }

    /**
     * 跳转RoadSideDetailActivity
     */
    @Override
    public void gotopage() {

        Intent i = new Intent();
        i.setClass(this, RoadSideDetailActivity.class);
        i.putExtra("orderParam",param);
        startActivity(i);
        finish();

    }
}
