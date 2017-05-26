package com.soarsky.car.ui.borrowandreturn.borrowaplication;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.DetailsInfo;
import com.soarsky.car.bean.ModifyStatusInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.borrowandreturn.borrow.AgreeBorrowActivity;
import com.soarsky.car.ui.borrowandreturn.borrowaplication.refuse.RefuseCauseActivity;
import com.soarsky.car.ui.borrowandreturn.wheelview.DateUtils;
import com.soarsky.car.ui.borrowandreturn.wheelview.JudgeDate;
import com.soarsky.car.ui.borrowandreturn.wheelview.ScreenInfo;
import com.soarsky.car.ui.borrowandreturn.wheelview.WheelMain;
import com.soarsky.car.uitl.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/1<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  收到的借车申请页面<br>
 */

public class BorrowAplicationActivity extends BaseActivity<BorrowAplicationPresent,BorrowAplicationMedol> implements BorrowAplicationView ,View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 车辆类型
     */
    private TextView type;
    /**
     * 借用车牌
     */
    private TextView carNum;
    /**
     * 借车人姓名
     */
    private TextView borrower_name;
    /**
     * 借车人电话
     */
    private TextView phone;
    /**
     * 借车人驾照
     */
    private TextView licen_num;
    /**
     * 开始时间
     */
    private TextView tv_startTime;
    /**
     * 结束时间
     */
    private TextView tv_endTime;
    /**
     * 拒绝借车
     */
    private Button bt_refuse;
    /**
     * 同意借车
     */
    private Button bt_agree;
    /**
     * 借车记录的id
     */
    private Integer bid;
    /**
     * 车牌号
     */
    private String car_num1;
    //选择时间与当前时间，用于判断用户选择的是否是以前的时间
    private int currentHour,currentMinute,currentDay,selectHour,selectMinute,selectDay,selectSecond;
    /**
     * 时间弹框布局
     */
    private LinearLayout ll_all;
    /**
     * 选择开始时间
     */
    private RelativeLayout rl_start_time;
    /**
     * 选择结束时间
     */
    private RelativeLayout rl_end_time;


    /**
     * 返回要展示的布局
     * @return int类型的id
     */
    @Override
    public int getLayoutId() {
        return R.layout.borrow_car_application;
    }

    /**
     * 初始化控件
     */
    @Override
    public void initView() {
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(R.string.borrow_aplication);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        type = (TextView) findViewById(R.id.type);
        carNum = (TextView) findViewById(R.id.carNum);
        borrower_name = (TextView) findViewById(R.id.borrower_name);
        phone = (TextView) findViewById(R.id.phone);
        licen_num = (TextView) findViewById(R.id.licen_num);
        tv_startTime = (TextView) findViewById(R.id.tv_startTime);
        tv_endTime = (TextView) findViewById(R.id.tv_endTime);

        bt_refuse = (Button) findViewById(R.id.bt_refuse);
        bt_refuse.setOnClickListener(this);

        bt_agree = (Button) findViewById(R.id.bt_agree);
        bt_agree.setOnClickListener(this);
        ll_all = (LinearLayout) findViewById(R.id.ll_all);


        rl_start_time = (RelativeLayout) findViewById(R.id.rl_start_time);
        rl_start_time.setOnClickListener(this);

        rl_end_time = (RelativeLayout) findViewById(R.id.rl_end_time);
        rl_end_time.setOnClickListener(this);

    }


    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 点击事件触发的方法
     * @param view 点击的控件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backLay:
                finish();
                break;
            case R.id.bt_refuse:
                Intent i=new Intent();
                i.setClass(BorrowAplicationActivity.this, RefuseCauseActivity.class);//从一个activity跳转到另一个activity
                i.putExtra("Num",car_num1);
                i.putExtra("id",bid);
                i.putExtra("name",App.getApp().getCommonParam().getUserName());

                startActivity(i);
                finish();
                break;
            case R.id.bt_agree:


                mModel.setBid(bid);
                mModel.setCarnum(car_num1);
                mModel.setUsername(App.getApp().getCommonParam().getUserName());
                mPresenter.agree();

                break;
            case R.id.rl_start_time:
                showBottoPopupWindow(1);
                break;
            case R.id.rl_end_time:
                showBottoPopupWindow(2);
                break;
        }
    }

    /**
     * 系统的onStart()方法
     */
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        bid = bundle.getInt("bId");
        mPresenter.getDetails(bid);


    }

    @Override
    public void showError() {

    }

    @Override
    public void showsSuccess(ResponseDataBean<ModifyStatusInfo> modifyStatusParm) {

    }

    @Override
    public void showsFail(ResponseDataBean<ModifyStatusInfo> modifyStatusParm) {
        ToastUtil.show(BorrowAplicationActivity.this,modifyStatusParm.getMessage());
    }

    /**
     * 同意借车申请
     * @param modifyStatusParm  同意借车返回的参数
     */
    @Override
    public void agree(ResponseDataBean<ModifyStatusInfo> modifyStatusParm) {
        Intent intent=new Intent();
        intent.setClass(BorrowAplicationActivity.this, AgreeBorrowActivity.class);
        intent.putExtra("bId",bid);
        startActivity(intent);
        finish();
    }

    /**
     * 借车申请数据的展示
     * @param detailParm 后台返回的申请数据
     */
    @Override
    public void showSuccess(ResponseDataBean<DetailsInfo> detailParm) {
        car_num1 = detailParm.getData().getCarnum();
        type.setText(detailParm.getData().getModel());
        carNum.setText(car_num1);
        borrower_name.setText(detailParm.getData().getAppuser().getUserName());
        phone.setText(detailParm.getData().getAppuser().getPhone());
        licen_num.setText(detailParm.getData().getAppuser().getIdcard());
        tv_startTime.setText(detailParm.getData().getStime());
        tv_endTime.setText(detailParm.getData().getEtime());
    }

    @Override
    public void showFail(ResponseDataBean<DetailsInfo> detailParm) {
        ToastUtil.show(BorrowAplicationActivity.this,detailParm.getMessage());
    }

    @Override
    public void showError(Throwable e) {
        ToastUtil.show(BorrowAplicationActivity.this,e.getMessage());
    }


    /**
     *对话框
     */
    private java.text.DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private WheelMain wheelMainDate;
    private String beginTime;

    public void showBottoPopupWindow(final int type) {
        WindowManager manager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        View menuView = LayoutInflater.from(BorrowAplicationActivity.this).inflate(R.layout.show_popup_time_window,null);
        final PopupWindow mPopupWindow = new PopupWindow(menuView, (int)(width),
                ActionBar.LayoutParams.WRAP_CONTENT);
        ScreenInfo screenInfoDate = new ScreenInfo(this);
        wheelMainDate = new WheelMain(menuView, true);
        wheelMainDate.screenheight = screenInfoDate.getHeight();
        String time = DateUtils.currentMonth().toString();
        Calendar calendar = Calendar.getInstance();
        if (JudgeDate.isDate(time, "yyyy-MM-DD")) {
            try {
                calendar.setTime(new Date(time));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hours = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        wheelMainDate.initDateTimePicker(year, month, day, hours,minute,seconds);
        final String currentTime = wheelMainDate.getTime().toString();
        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.showAtLocation(ll_all, Gravity.BOTTOM, 0, 0);
        mPopupWindow.setOnDismissListener(new BorrowAplicationActivity.poponDismissListener());
        backgroundAlpha(0.6f);
        TextView tv_cancle = (TextView) menuView.findViewById(R.id.tv_cancle);
        TextView tv_ensure = (TextView) menuView.findViewById(R.id.tv_ensure);
        TextView tv_pop_title = (TextView) menuView.findViewById(R.id.tv_pop_title);
        tv_pop_title.setText(getString(R.string.choce_start_time));
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });
        tv_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                beginTime = wheelMainDate.getTime().toString();
                try {
                    Date begin = dateFormat.parse(currentTime);
                    Date end = dateFormat.parse(beginTime);
                    if(type == 1) {
                        tv_startTime.setText(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm));
                    }else if(type == 2){
                        tv_endTime.setText(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
            }
        });
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

    class poponDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }

    }
}
