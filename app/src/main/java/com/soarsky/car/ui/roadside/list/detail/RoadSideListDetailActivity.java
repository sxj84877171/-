package com.soarsky.car.ui.roadside.list.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideListOrderInfo;
import com.soarsky.car.uitl.TimeUtils;
import com.soarsky.car.uitl.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：列表订单详情<br>
 * 该类为 RoadSideListDetailActivity<br>
 */


public class RoadSideListDetailActivity extends BaseActivity<RoadSideListDetailPresent,RoadSideListDetailModel> implements RoadSideListDetailView,View.OnClickListener{

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
     * 订单取消与完成的icon
     */
    private ImageView roadSideListDetailCancleView;
    /**
     * 订单取消or完成的状态
     */
    private TextView roadSideListDetailCancleTv;
    /**
     * id
     */
    private String id ="";
    /**
     * 状态
     */
    private String status ="";


    @Override
    public int getLayoutId() {
        return R.layout.activity_road_side_list_detail;
    }

    @Override
    public void initView() {

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.roadsidedetail));

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

        roadSideOrderTelLay = (RelativeLayout) findViewById(R.id.roadSideOrderTelLay);
        roadSideOrderTelLay.setOnClickListener(this);
        roadSideOrderTelLay.setVisibility(View.GONE);

        roadSideListDetailCancleView = (ImageView) findViewById(R.id.roadSideListDetailCancleView);
        roadSideListDetailCancleTv = (TextView) findViewById(R.id.roadSideListDetailCancleTv);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("id");
        status = getIntent().getStringExtra("status");

        if("2".equals(status)){
            roadSideListDetailCancleView.setImageResource(R.mipmap.order_finish);
            roadSideListDetailCancleTv.setText(getString(R.string.roadsideorderfinsh));
        }else {
            roadSideListDetailCancleView.setImageResource(R.mipmap.order_cancle);
            roadSideListDetailCancleTv.setText(getString(R.string.roadsideordernofinish));
        }

        mPresenter.getRescueById(id);

    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 获取故障救援详细信息成功
     * @param roadSideListOrderParam 参数
     */
    @Override
    public void getRescueByIdSuccess(ResponseDataBean<RoadSideListOrderInfo> roadSideListOrderParam) {

        if(roadSideListOrderParam != null){
            if(Constants.REQUEST_SUCCESS.equals(roadSideListOrderParam.getStatus())){
                try {
                    roadSideSeverDistanceTv.setText(roadSideListOrderParam.getData().getDistance() == null ? "" : roadSideListOrderParam.getData().getDistance());
                    roadSideOrderFeeTv.setText(roadSideListOrderParam.getData().getCost() == null ? "0" : roadSideListOrderParam.getData().getCost());
                    String time = roadSideListOrderParam.getData().getCreateTime();
                    String reachTime = roadSideListOrderParam.getData().getReachTime();
                    long reach_time = Long.parseLong(reachTime) * 60 * 1000;
                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date date = date = sDateFormat.parse(time);
                    String arriveTime = TimeUtils.milliseconds2String(date.getTime() + reach_time);
                    roadSideOrderTimeTv.setText(""+arriveTime);
                    roadSideOrderTelTv.setText(roadSideListOrderParam.getData().getCompanyPhone());
                    roadSideOrderCarNumTv.setText(roadSideListOrderParam.getData().getCarNumber());
                    roadSideOrderPositionTv.setText(roadSideListOrderParam.getData().getAddress());
                    roadSideCarTypeTv.setText(roadSideListOrderParam.getData().getCarType());
                    roadSideOrderPhoneTv.setText(roadSideListOrderParam.getData().getPhone());
                }catch (ParseException e) {
                    e.printStackTrace();
                }
            }else {
                //将后台的message转换成我们自己的内容显示给用户               王松清
                ToastUtil.show(this,R.string.get_data_fail);
            }
        }
    }

    /**
     * 获取故障救援详细信息失败
     */
    @Override
    public void getRescueByIdFail() {

        ToastUtil.show(this,R.string.throwable);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
        }
    }
}
