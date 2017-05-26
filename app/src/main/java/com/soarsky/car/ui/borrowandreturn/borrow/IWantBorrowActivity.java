package com.soarsky.car.ui.borrowandreturn.borrow;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.CheckBorrowCar;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.borrowandreturn.wheelview.DateUtils;
import com.soarsky.car.ui.borrowandreturn.wheelview.JudgeDate;
import com.soarsky.car.ui.borrowandreturn.wheelview.ScreenInfo;
import com.soarsky.car.ui.borrowandreturn.wheelview.WheelMain;
import com.soarsky.car.uitl.ToastUtil;
import com.soarsky.car.uitl.ValidatorUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * picker<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/28<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：借车申请页面<br>
 * 该类为<br>
 */

public class IWantBorrowActivity extends BaseActivity<IWantBorrowPresent,IWantBorrowMedol> implements IWantBorrowView,View.OnClickListener{
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回
     */
    private LinearLayout backLay;
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
    /**
     * 借车按钮
     */
    private Button bt_borrow;
    /**
     * 删除手机号码的按钮
     */
    private ImageView iv_delete_phoneNum;
    /**
     * 车主手机号
     */
    private EditText et_phoneNum;

    /**
     * 选择开始时间
     */
    private LinearLayout startTimeLay;
    /**
     * 选择结束时间
     */
    private LinearLayout endTimeLay;
    /**
     * 选择时间的弹框
     */
    private PopupWindow pw;
    /**
     * 日期
     */
    private String selectDate,selectTime;
    /**
     *  选择时间与当前时间，用于判断用户选择的是否是以前的时间
     */
    private int currentHour,currentMinute,currentDay,selectHour,selectMinute,selectDay,selectSecond;
    /**
     * 时间弹框
     */
    private LinearLayout ll_all;
    /**
     * 获取全局变量的实例
     */
    private App app;
    /**
     * 所借车牌号
     */
    private String carnum;
    /**
     * 用户输入的车主手机号
     */
    private String phone;
    /**
     * 开始时间
     */
    private String stime;
    /**
     * 结束时间
     */
    private String etime;
    /**
     * 借车人电话
     */
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

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        et_startTime = (EditText) findViewById(R.id.et_startTime);
        et_startTime.setOnClickListener(this);
        iv_start_down = (ImageView) findViewById(R.id.iv_start_down);
        iv_start_down.setOnClickListener(this);

        et_endTime = (EditText) findViewById(R.id.et_endTime);
        et_endTime.setOnClickListener(this);
        iv_end_down = (ImageView) findViewById(R.id.iv_end_down);
        iv_end_down.setOnClickListener(this);

        bt_borrow = (Button) findViewById(R.id.bt_borrow);
        bt_borrow.setOnClickListener(this);

        iv_delete_phoneNum = (ImageView) findViewById(R.id.iv_delete_phoneNum);
        iv_delete_phoneNum.setOnClickListener(this);

        et_phoneNum = (EditText) findViewById(R.id.et_phoneNum);
        et_carNum = (EditText) findViewById(R.id.et_carNum);

        ll_all = (LinearLayout) findViewById(R.id.ll_all);


        startTimeLay = (LinearLayout) findViewById(R.id.startTimeLay);
        startTimeLay.setOnClickListener(this);

        endTimeLay = (LinearLayout) findViewById(R.id.endTimeLay);
        endTimeLay.setOnClickListener(this);


        et_phoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                iv_delete_phoneNum.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        et_phoneNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b){
                    InputMethodManager inputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }else {}
            }
        });


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
            case R.id.backLay:
                finish();
            break;
            case R.id.iv_start_down:
            case R.id.startTimeLay:
            case R.id.et_startTime:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                showBottoPopupWindow(1);
                break;
            case R.id.iv_end_down:
            case R.id.endTimeLay:
            case R.id.et_endTime:
                InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
                showBottoPopupWindow(2);
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
                }else if (app.getCommonParam().getCarNum() != null && app.getCommonParam().getCarNum().equals(carnum)){
                    ToastUtil.show(app,getString(R.string.appuser_car ));
                } else if(TextUtils.isEmpty(stime)){
                    ToastUtil.show(app,getString(R.string.empty_start_time));
                }
                else if(TextUtils.isEmpty(etime)){
                    ToastUtil.show(app,getString(R.string.empty_end_time));
                }else  if(mPresenter.isFamilyNum(carnum)){
                    ToastUtil.show(app,getString(R.string.just_use));
                } else  if(!ValidatorUtils.validatorCarNum(carnum)){
                    ToastUtil.show(app,getString(R.string.car_num_falut));
                }else if (!ValidatorUtils.validatorPhone(phone)){
                    ToastUtil.show(app,getString(R.string.phone_num_falut));
                }else{ mPresenter.check(carnum ,phone);}

                break;
            case R.id.iv_delete_phoneNum:
                et_phoneNum.getText().clear();
                break;
        }
    }

    /**
     * 借车成功后触发的方法
     * @param borrowParm  服务器的响应参数
     */
    @Override
    public void showSuccess(ResponseDataBean borrowParm) {
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
    public void checkFail(ResponseDataBean<CheckBorrowCar> checkBorrowCar) {
        ToastUtil.show(IWantBorrowActivity.this,getString(R.string.donot_match));
    }
    /**
     * 借车失败后触发的方法
     * @param borrowParm 服务器的响应参数
     */
    @Override
    public void showFail(ResponseDataBean borrowParm) {
        ToastUtil.show(IWantBorrowActivity.this,borrowParm.getMessage());
    }

    /**
     * 车牌号、手机号校验成功后触发
     * @param checkBorrowCar 校验成功的业务参数类
     */
    @Override
    public void checkSuccess(ResponseDataBean<CheckBorrowCar> checkBorrowCar) {
        
        mPresenter.borrowCar(carnum, phone, stime, etime, borrowPhone);
       
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
        View menuView = LayoutInflater.from(IWantBorrowActivity.this).inflate(R.layout.show_popup_time_window,null);
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
        mPopupWindow.setOnDismissListener(new poponDismissListener());
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
                        et_startTime.setText(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm));
                    }else if(type == 2){
                        et_endTime.setText(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm));
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
