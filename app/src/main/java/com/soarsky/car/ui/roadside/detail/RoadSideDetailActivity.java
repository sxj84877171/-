package com.soarsky.car.ui.roadside.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.roadside.rescue.RescueParam;
import com.soarsky.car.uitl.TimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
 * 程序功能：订单详情
 * 该类为
 */


public class RoadSideDetailActivity extends BaseActivity<RoadSideDetailPresent,RoadSideDetailModel> implements RoadSideDetailView,View.OnClickListener{

    /**
     * 关闭
     */
    private TextView roadSideDetalCloseTv;
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
     * 时间间长
     */
    private TextView roadSideDetailTimeTv;

    /**
     * 左边icon
     */
    private LinearLayout leftLay;

    private RescueParam param;

    @Override
    public int getLayoutId() {
        return R.layout.activity_road_side_detail;
    }

    @Override
    public void initView() {

        roadSideDetalCloseTv = (TextView) findViewById(R.id.roadSideDetalCloseTv);
        roadSideDetalCloseTv.setOnClickListener(this);

        roadSideLogoSeverView = (ImageView) findViewById(R.id.roadSideLogoSeverView);
        roadSideOrderSeverTv = (TextView) findViewById(R.id.roadSideOrderSeverTv);
        roadSideSeverDistanceTv = (TextView) findViewById(R.id.roadSideSeverDistanceTv);
        roadSideOrderFeeTv = (TextView) findViewById(R.id.roadSideOrderFeeTv);
        roadSideOrderTimeTv = (TextView) findViewById(R.id.roadSideOrderTimeTv);
        roadSideOrderTelTv = (TextView) findViewById(R.id.roadSideOrderTelTv);
        roadSideOrderCarNumTv = (TextView) findViewById(R.id.roadSideOrderCarNumTv);
        roadSideCarTypeTv = (TextView) findViewById(R.id.roadSideCarTypeTv);
        roadSideOrderPositionTv = (TextView) findViewById(R.id.roadSideOrderPositionTv);
        roadSideOrderPhoneTv = (TextView) findViewById(R.id.roadSideOrderPhoneTv);
        roadSideDetailTimeTv = (TextView) findViewById(R.id.roadSideDetailTimeTv);

        leftLay = (LinearLayout) findViewById(R.id.leftLay);
        leftLay.setVisibility(View.GONE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        param = (RescueParam) getIntent().getSerializableExtra("orderParam");
        if(param != null){
            try {
                roadSideOrderCarNumTv.setText(param.getCarNum());
                roadSideCarTypeTv.setText(param.getCarType());
                roadSideOrderPositionTv.setText(param.getCarPosition());
                roadSideOrderPhoneTv.setText(param.getPhone());
                roadSideSeverDistanceTv.setText(param.getDistance());
                roadSideOrderFeeTv.setText(param.getSeverFee());
                roadSideOrderSeverTv.setText(param.getCompany());
                String time = param.getStartTime();
                String reachTime = param.getReachTime();
                long reach_time = Long.parseLong(reachTime) * 60 * 1000;
                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = date = sDateFormat.parse(time);
                String arriveTime = TimeUtils.milliseconds2String(date.getTime() + reach_time);
                roadSideOrderTimeTv.setText("" + arriveTime);
                roadSideOrderTelTv.setText(param.getSeverPhone());

                roadSideDetailTimeTv.setText(arriveTime.substring(arriveTime.length()-8,arriveTime.length()-3));
            }catch (ParseException e) {
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
            case R.id.roadSideDetalCloseTv:
                finish();
                break;
        }
    }
}
