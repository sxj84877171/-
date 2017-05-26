package com.soarsky.car.ui.roadside.list.order;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideListOrderInfo;
import com.soarsky.car.ui.roadside.fee.RoadSideFeeActivity;
import com.soarsky.car.uitl.ToastUtil;

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
 * 程序功能：救援列表确认订单
 * 该类为 RoadSideListOrderActivity
 */


public class RoadSideListOrderActivity extends BaseActivity<RoadSideListOrderPresent,RoadSideListOrderModel> implements RoadSideListOrderView,View.OnClickListener{


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
     * 取消订单
     */
    private Button roadSideOrderListCancleBtn;
    /**
     *支付
     */
    private Button roadSideOrderListFeeBtn;

    /**
     * id
     */
    private String id ="";
    /**
     *对话框
     */
    private Dialog dialog;
    /**
     * builder
     */
    private RoadSideListOrderDialog.Builder builder;

    /**
     * 服务项目
     */
    private TextView roadSideOrderType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_road_side_list_order;
    }

    @Override
    public void initView() {

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.roadsideorder));

//        roadSideOrderTelLay = (RelativeLayout) findViewById(R.id.roadSideOrderTelLay);
//        roadSideOrderTelLay.setVisibility(View.GONE);
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


        roadSideOrderListCancleBtn = (Button) findViewById(R.id.roadSideOrderListCancleBtn);
        roadSideOrderListCancleBtn.setOnClickListener(this);

        roadSideOrderListFeeBtn = (Button) findViewById(R.id.roadSideOrderListFeeBtn);
        roadSideOrderListFeeBtn.setOnClickListener(this);


        roadSideOrderTelTv.setOnClickListener(this);

        roadSideOrderType = (TextView) findViewById(R.id.roadSideOrderType);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        id = getIntent().getStringExtra("id");

        mPresenter.getRescueById(id);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 获取故障救援详细信息成功
     * @param roadSideListOrderParam 故障救援详细信息
     */
    @Override
    public void getRescueByIdSuccess(ResponseDataBean<RoadSideListOrderInfo> roadSideListOrderParam) {

        if(roadSideListOrderParam != null){
            if (Constants.REQUEST_SUCCESS.equals(roadSideListOrderParam.getStatus())){
                try {
                    roadSideSeverDistanceTv.setText(roadSideListOrderParam.getData().getDistance() == null?"":roadSideListOrderParam.getData().getDistance());
                    roadSideOrderFeeTv.setText(roadSideListOrderParam.getData().getCost() == null?"0":roadSideListOrderParam.getData().getCost());
                    String time = roadSideListOrderParam.getData().getCreateTime();
                    String reachTime = roadSideListOrderParam.getData().getReachTime();
                    // 后台返回的时间格式改为yyyy-MM-dd hh:mm:ss 无需转换    2017/04/01   修改人：王松清
                    /*long reach_time = Long.parseLong(reachTime)*60*1000;
                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date date= sDateFormat.parse(time);
                    String arriveTime = TimeUtils.milliseconds2String(date.getTime()+reach_time);
                    roadSideOrderTimeTv.setText(""+arriveTime);*/
                    roadSideOrderTimeTv.setText(""+reachTime);
                    roadSideOrderTelTv.setText(roadSideListOrderParam.getData().getCompanyPhone());
                    roadSideOrderCarNumTv.setText(roadSideListOrderParam.getData().getCarNumber());
                    roadSideOrderPositionTv.setText(roadSideListOrderParam.getData().getAddress());
                    roadSideCarTypeTv.setText(roadSideListOrderParam.getData().getCarType());
                    roadSideOrderPhoneTv.setText(roadSideListOrderParam.getData().getPhone());
                    roadSideOrderType.setText(roadSideListOrderParam.getData().getServiceItems());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                ToastUtil.show(this,roadSideListOrderParam.getMessage());
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

    /**
     * 取消故障救援记录成功
     * @param roadSideListOrderDelRescueParam 故障救援记录
     */
    @Override
    public void delRescueByIdSuccess(ResponseDataBean<Object> roadSideListOrderDelRescueParam) {

        if (roadSideListOrderDelRescueParam != null){
            dialog.dismiss();
            if(Constants.REQUEST_SUCCESS.equals(roadSideListOrderDelRescueParam.getStatus())){

                finish();
            }else {
                //将后台的message转换成我们自己的内容显示给用户               王松清
                ToastUtil.show(this,R.string.cancel_record_fail);
            }
        }


    }

    /**
     * 取消故障救援记录失败
     */
    @Override
    public void delRescueByIdFail() {
        ToastUtil.show(this,R.string.get_data_fail);

    }

    /**
     * 取消订单对话框
     */
    @Override
    public void showOrderDelete() {

        builder = new RoadSideListOrderDialog.Builder(this, new RoadSideListOrderListener() {
            @Override
            public void selectedOnClick(int type) {

                if(type == 0){
                    dialog.dismiss();
                }else {
                    mPresenter.delRescueById(id);
                }
            }
        });

        dialog = builder.create();
        dialog.show();
    }

    @Override
    public void showError(Throwable e) {
        ToastUtil.show(this,R.string.throwable);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.roadSideOrderListCancleBtn:

                mPresenter.showOrderDelete();
                break;
            case R.id.roadSideOrderListFeeBtn:
                Intent i = new Intent();
                i.setClass(this, RoadSideFeeActivity.class);
                startActivity(i);
                break;
            case R.id.roadSideOrderTelTv:
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+roadSideOrderTelTv.getText().toString()));
                startActivity(intent);
                break;

        }
    }
}
