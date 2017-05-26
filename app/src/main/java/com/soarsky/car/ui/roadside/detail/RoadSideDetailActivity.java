package com.soarsky.car.ui.roadside.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.roadside.rescue.RescueParam;
import com.soarsky.car.uitl.ConstUtils;
import com.soarsky.car.uitl.TimeUtils;

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
 * 程序功能：订单详情展示<br>
 * 该类为  订单详情界面<br>
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
    /**
     * 申请救援的参数
     */
    private RescueParam param;
    /**
     * 电话布局
     */
    private RelativeLayout iv_phone;
    /**
     * 联系电话的布局
     */
    private RelativeLayout roadSideOrderTelLay;

    /**
     * 服务项目
     */
    private TextView roadSideOrderType;
    /**
     * 商家电话
     */
    private RelativeLayout rl_busessPhone;

    @Override
    public int getLayoutId() {
        return R.layout.activity_road_side_detail3;
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

        roadSideOrderType = (TextView) findViewById(R.id.roadSideOrderType);
        rl_busessPhone = (RelativeLayout) findViewById(R.id.rl_busessPhone);
        rl_busessPhone.setOnClickListener(this);


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
                roadSideOrderType.setText(param.getType());
                String time = param.getStartTime();
                String reachTime = param.getReachTime();
//                long reach_time = Long.parseLong(reachTime) * 60 * 1000;
                long reach_time = TimeUtils.getIntervalTime(time,reachTime,ConstUtils.MSEC);
//                SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//                Date date = date = sDateFormat.parse(time);
//                String arriveTime = TimeUtils.milliseconds2String(date.getTime() + reach_time);
                roadSideOrderTimeTv.setText("" + reachTime);
//                roadSideDetailTimeTv.setText(arriveTime.substring(arriveTime.length()-8,arriveTime.length()-3));
                roadSideOrderTelTv.setText(param.getSeverPhone());
//                mDay = TimeUtils.milliseconds2Unit(( reach_time), ConstUtils.DAY);
//                mHour = TimeUtils.milliseconds2Unit(( reach_time), ConstUtils.HOUR);
                mMin = TimeUtils.milliseconds2Unit((reach_time), ConstUtils.MIN);
                mSecond = reach_time - mMin*60*1000;

                startRun();




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
            case R.id.roadSideDetalCloseTv:
                finish();
                break;
            case R.id.rl_busessPhone:
                call(param.getSeverPhone());
                break;
        }
    }

    /**
     * 调用拨号功能
     * @param phone 电话号码
     */
    private void call(String phone) {
        Intent intent=new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+phone));
        startActivity(intent);
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            if(mMin >= 0) {
                mSecond = 59;
            }else {
                mSecond = 0;
            }
            if (mMin < 0) {
               mMin = 0;
               return;
            }
        }
    }

    /**
     * 天
     */
    private long mDay = 0;
    /**
     * 小时
     */
    private long mHour = 0;
    /**
     * 分
     */
    private long mMin = 0;
    /**
     * 秒
     */
    private long mSecond = 0;
    /**
     * 是否开始
     */
    private boolean isRun = true;

    private Handler timeHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1) {
                computeTime();

                if (mMin==0&&mSecond==0) {

                    roadSideDetailTimeTv.setText("00"+":"+"00");
                    isRun = false;
                }else {
                    String minute = mMin < 10 ? "0" + mMin : mMin + "";
                    String second = mSecond < 10 ? "0" + mSecond : mSecond + "";
                    roadSideDetailTimeTv.setText(minute + ":" + second);
                }
            }
        }
    };

    /**
     * 开启倒计时
     */
    private void startRun() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (isRun) {
                    try {
                        Thread.sleep(1000); // sleep 1000ms
                        Message message = Message.obtain();
                        message.what = 1;
                        timeHandler.sendMessage(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
