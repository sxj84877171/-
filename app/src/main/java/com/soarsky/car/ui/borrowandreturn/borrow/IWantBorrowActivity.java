package com.soarsky.car.ui.borrowandreturn.borrow;

import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.borrowandreturn.borrow.picker.DatePicker;
import com.soarsky.car.ui.borrowandreturn.borrow.picker.TimePicker;
import com.soarsky.car.uitl.ToastUtil;

import java.util.Calendar;

/**
 * picker
 * 作者： 王松清
 * 时间： 2016/11/28
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：借车申请页面
 * 该类为
 */

public class IWantBorrowActivity extends BaseActivity<IWantBorrowPresent,IWantBorrowMedol> implements IWantBorrowView,View.OnClickListener{
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回
     */
    private ImageView backView;
    /**
     * 选择开始时间
     */
    private ImageView iv_start_down;
    /**
     * 开始时间
     */
    private EditText et_startTime;
    /**
     * 选择结束时间
     */
    private ImageView iv_end_down;
    /**
     * 结束时间
     */
    private EditText et_endTime;
    /**
     * 借用车牌号
     */
    private EditText et_carNum;
    private Button bt_borrow;
    private ImageView iv_delete_phoneNum;
    /**
     * 车主手机号
     */
    private EditText et_phoneNum;
    private Calendar calendar;
    private DatePicker dp_test;
    private TimePicker tp_test;
    private TextView tv_ok,tv_cancel;	//确定、取消button
    private PopupWindow pw;
    private String selectDate,selectTime;
    //选择时间与当前时间，用于判断用户选择的是否是以前的时间
    private int currentHour,currentMinute,currentDay,selectHour,selectMinute,selectDay,selectSecond;
    private LinearLayout ll_all;
    private App app;
    private String carnum;
    private String phone;
    private String stime;
    private String etime;
    private String borrowPhone;

    /**
     * 页面要展示的布局
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.i_want_borrow;
    }

    /**
     * 初始化控件
     */
    @Override
    public void initView() {

        app = (App) getApplication();
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(R.string.i_want_borrow_car);

        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);

        et_startTime = (EditText) findViewById(R.id.et_startTime);
        iv_start_down = (ImageView) findViewById(R.id.iv_start_down);
        iv_start_down.setOnClickListener(this);

        et_endTime = (EditText) findViewById(R.id.et_endTime);
        iv_end_down = (ImageView) findViewById(R.id.iv_end_down);
        iv_end_down.setOnClickListener(this);

        bt_borrow = (Button) findViewById(R.id.bt_borrow);
        bt_borrow.setOnClickListener(this);

        iv_delete_phoneNum = (ImageView) findViewById(R.id.iv_delete_phoneNum);
        iv_delete_phoneNum.setOnClickListener(this);

        et_phoneNum = (EditText) findViewById(R.id.et_phoneNum);
        et_carNum = (EditText) findViewById(R.id.et_carNum);
        ll_all = (LinearLayout) findViewById(R.id.ll_all);
        calendar = Calendar.getInstance();

    }


    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 重写点击事件的方法
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backView:
                finish();
            break;
            case R.id.iv_start_down:
                View view1 = View.inflate(IWantBorrowActivity.this, R.layout.dialog_select_time, null);
                selectDate = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-"
                        + calendar.get(Calendar.DAY_OF_MONTH) + " ";
                //选择时间与当前时间的初始化，用于判断用户选择的是否是以前的时间，如果是，弹出toss提示不能选择过去的时间
                selectDay = currentDay = calendar.get(Calendar.DAY_OF_MONTH);
                selectMinute = currentMinute = calendar.get(Calendar.MINUTE);
                selectHour = currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                selectSecond=calendar.get(Calendar.SECOND);
                selectTime = currentHour + ":" + ((currentMinute < 10)?("0"+currentMinute):currentMinute) + ":"+ ((selectSecond < 10)?("0"+selectSecond):selectSecond) + "";
                dp_test = (DatePicker)view1.findViewById(R.id.dp_test);
                tp_test = (TimePicker)view1.findViewById(R.id.tp_test);
                tv_ok = (TextView) view1.findViewById(R.id.tv_ok);
                tv_cancel = (TextView) view1.findViewById(R.id.tv_cancel);
                //设置滑动改变监听器
                dp_test.setOnChangeListener(dp_onchanghelistener);
                tp_test.setOnChangeListener(tp_onchanghelistener);
                pw = new PopupWindow(view1, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
//				//设置这2个使得点击pop以外区域可以去除pop
//				pw.setOutsideTouchable(true);
//				pw.setBackgroundDrawable(new BitmapDrawable());

                //出现在布局底端
                pw.showAtLocation(ll_all, 0, 0,  Gravity.END);

                //点击确定
                tv_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        Toast.makeText(getApplicationContext(), selectDate+selectTime, Toast.LENGTH_SHORT).show();
                        et_startTime.setText(selectDate+selectTime);
                        pw.dismiss();


//                        if(selectDay == currentDay ){	//在当前日期情况下可能出现选中过去时间的情况
//
//                            if(selectHour < currentHour){
//                                Toast.makeText(getApplicationContext(), R.string.time_select, Toast.LENGTH_SHORT).show();
//                            }else if( (selectHour == currentHour) && (selectMinute < currentMinute) ){
//                                Toast.makeText(getApplicationContext(), R.string.time_select, Toast.LENGTH_SHORT).show();
//                            }else{
//                                Toast.makeText(getApplicationContext(), selectDate+selectTime, Toast.LENGTH_SHORT).show();
//                                et_startTime.setText(selectDate+selectTime);
//                                pw.dismiss();
//                            }
//                        }else{
//                            Toast.makeText(getApplicationContext(), selectDate+selectTime, Toast.LENGTH_SHORT).show();
//                            et_startTime.setText(selectDate+selectTime);
//                            pw.dismiss();
//                        }
                    }
                });

                //点击取消
                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        pw.dismiss();
                    }
                });
                break;
            case R.id.iv_end_down:
                View v = View.inflate(IWantBorrowActivity.this, R.layout.dialog_select_time, null);
                selectDate = calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-"
                        + calendar.get(Calendar.DAY_OF_MONTH) + " ";
                //选择时间与当前时间的初始化，用于判断用户选择的是否是以前的时间，如果是，弹出toss提示不能选择过去的时间
                selectDay = currentDay = calendar.get(Calendar.DAY_OF_MONTH);
                selectMinute = currentMinute = calendar.get(Calendar.MINUTE);
                selectHour = currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                selectSecond=calendar.get(Calendar.SECOND);
                selectTime = currentHour + ":" + ((currentMinute < 10)?("0"+currentMinute):currentMinute) + ":"+ ((selectSecond < 10)?("0"+selectSecond):selectSecond) + "";
                dp_test = (DatePicker)v.findViewById(R.id.dp_test);
                tp_test = (TimePicker)v.findViewById(R.id.tp_test);
                tv_ok = (TextView) v.findViewById(R.id.tv_ok);
                tv_cancel = (TextView) v.findViewById(R.id.tv_cancel);
                //设置滑动改变监听器
                dp_test.setOnChangeListener(dp_onchanghelistener);
                tp_test.setOnChangeListener(tp_onchanghelistener);
                pw = new PopupWindow(v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
//				//设置这2个使得点击pop以外区域可以去除pop
//				pw.setOutsideTouchable(true);
//				pw.setBackgroundDrawable(new BitmapDrawable());

                //出现在布局底端
                pw.showAtLocation(ll_all, 0, 0,  Gravity.END);

                //点击确定
                tv_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        if(selectDay == currentDay ){	//在当前日期情况下可能出现选中过去时间的情况
                            if(selectHour < currentHour){
                                Toast.makeText(getApplicationContext(), R.string.time_select, Toast.LENGTH_SHORT).show();
                            }else if( (selectHour == currentHour) && (selectMinute < currentMinute) ){
                                Toast.makeText(getApplicationContext(), R.string.time_select, Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), selectDate+selectTime, Toast.LENGTH_SHORT).show();
                                et_endTime.setText(selectDate+selectTime);
                                pw.dismiss();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), selectDate+selectTime, Toast.LENGTH_SHORT).show();
                            et_endTime.setText(selectDate+selectTime);
                            pw.dismiss();
                        }
                    }
                });

                //点击取消
                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        pw.dismiss();
                    }
                });
                break;
            case R.id.bt_borrow:

                carnum = et_carNum.getText().toString();
                phone = et_phoneNum.getText().toString();
                stime = et_startTime.getText().toString();
                etime = et_endTime.getText().toString();
                borrowPhone = app.getCommonParam().getOwerPhone();



                if(TextUtils.isEmpty(carnum)){
                    ToastUtil.show(app,getString(R.string.empty_carnum));
                }
                else if(TextUtils.isEmpty(phone)){
                    ToastUtil.show(app,getString(R.string.empty_owner_pnum));
                }
                else if(TextUtils.isEmpty(stime)){
                    ToastUtil.show(app,getString(R.string.empty_start_time));
                }
                else if(TextUtils.isEmpty(etime)){
                    ToastUtil.show(app,getString(R.string.empty_end_time));
                }else  if(mPresenter.isFamilyNum(carnum)){
                    ToastUtil.show(app,getString(R.string.just_use));
                }else{  mPresenter.check(carnum,phone);}


                break;
            case R.id.iv_delete_phoneNum:
                et_phoneNum.getText().clear();

                break;
        }
    }
    DatePicker.OnChangeListener dp_onchanghelistener = new DatePicker.OnChangeListener() {
        @Override
        public void onChange(int year, int month, int day, int day_of_week) {
            selectDay = day;
            selectDate = year + "-" + month + "-" + day+" "  ;
        }
    };
    TimePicker.OnChangeListener tp_onchanghelistener = new TimePicker.OnChangeListener() {
        @Override
        public void onChange(int hour, int munite) {
            selectTime = hour + "点" + ((munite < 10)?("0"+munite):munite) + "分";
        }

        //        @Override
//        public void onChange(int hour/*, int minute*/) {
//            selectTime = hour + "点" + ((minute < 10)?("0"+minute):minute) + "分";
//            selectHour = hour;
//            // selectMinute = minute;
//        }
    };

    /**
     * 借车成功后触发的方法
     * @param borrowParm
     */
    @Override
    public void showSuccess(BorrowParm borrowParm) {
        Intent intent=new Intent();
        intent.setClass(IWantBorrowActivity.this, SubmitedAplicationActivity.class);//从一个activity跳转到另一个activity
        intent.putExtra("carNum",carnum);
        intent.putExtra("ownerPhone",phone);
        intent.putExtra("startTime",stime);
        intent.putExtra("endTime",etime);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError() {

    }

    /**
     * 车牌号、手机号校验失败后触发
     * @param checkBorrowCar
     */
    @Override
    public void checkFail(CheckBorrowCar checkBorrowCar) {
        ToastUtil.show(IWantBorrowActivity.this,checkBorrowCar.getMessage());
    }
    /**
     * 借车失败后触发的方法
     * @param borrowParm
     */
    @Override
    public void showFail(BorrowParm borrowParm) {
        ToastUtil.show(IWantBorrowActivity.this,borrowParm.getMessage());
    }

    /**
     * 车牌号、手机号校验成功后触发
     * @param checkBorrowCar
     */
    @Override
    public void checkSuccess(CheckBorrowCar checkBorrowCar) {
        
        mPresenter.borrowCar(carnum, phone, stime, etime, borrowPhone);
       
    }
}
