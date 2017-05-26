package com.soarsky.car.ui.borrowandreturn.borrow.picker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * picker
 * 作者： 王松清
 * 时间： 2016/12/6
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class TimePicker extends LinearLayout {
    private Calendar calendar = Calendar.getInstance();
    private WheelView hours, mins,seconds; //Wheel picker
    private OnChangeListener onChangeListener; //onChangeListener
    private final int MARGIN_RIGHT = 15;		//调整文字右端距离
    private ArrayList<DateObject> hourList,minuteList,secondList;
    private DateObject dateObject;		//时间数据对象
    //Constructors
    public TimePicker(Context context) {
        super(context);
        init(context);
    }

    public TimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化
     * @param context
     */
    private void init(Context context){
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second=calendar.get(Calendar.SECOND);
        hourList = new ArrayList<DateObject>();
        minuteList = new ArrayList<DateObject>();
        secondList=new ArrayList<DateObject>();
        for (int i = 0; i < 24; i++) {
            dateObject = new DateObject(hour+i,-1,1);
            hourList.add(dateObject);
        }

        for (int j = 0; j < 60; j++) {
            dateObject = new DateObject(-1,minute+j,2);
            minuteList.add(dateObject);
        }
        for (int j = 0; j < 60; j++) {
            dateObject = new DateObject(-1,second+j,3);
            secondList.add(dateObject);
        }

        //小时选择器
        hours = new WheelView(context);
        LayoutParams lparams_hours = new LayoutParams(80,LayoutParams.WRAP_CONTENT);
        lparams_hours.setMargins(0, 0, MARGIN_RIGHT, 0);
        hours.setLayoutParams(lparams_hours);
        hours.setAdapter(new StringWheelAdapter(hourList, 24));
        hours.setVisibleItems(3);
        hours.setCyclic(true);
        hours.addChangingListener(onHoursChangedListener);
        addView(hours);

        //分钟选择器
        mins = new WheelView(context);
        mins.setLayoutParams(new LayoutParams(80,LayoutParams.WRAP_CONTENT));
        mins.setAdapter(new StringWheelAdapter(minuteList,60));
        mins.setVisibleItems(3);
        mins.setCyclic(true);
        mins.addChangingListener(onMinsChangedListener);
        addView(mins);

        //秒选择器
        seconds = new WheelView(context);
        seconds.setLayoutParams(new LayoutParams(80,LayoutParams.WRAP_CONTENT));
        seconds.setAdapter(new StringWheelAdapter(secondList,60));
        seconds.setVisibleItems(3);
        seconds.setCyclic(true);
        seconds.addChangingListener(onSecondChangedListener);
        addView(seconds);
    }



    //listeners
    private OnWheelChangedListener onHoursChangedListener = new OnWheelChangedListener(){
        @Override
        public void onChanged(WheelView wheel, int oldValue, int newValue) {
            calendar.set(Calendar.HOUR_OF_DAY, newValue);
        }

    };
    private OnWheelChangedListener onMinsChangedListener = new OnWheelChangedListener(){
        @Override
        public void onChanged(WheelView mins, int oldValue, int newValue) {
            calendar.set(Calendar.MINUTE, newValue);
            change();
        }
    };

    private OnWheelChangedListener onSecondChangedListener = new OnWheelChangedListener(){
        @Override
        public void onChanged(WheelView second, int oldValue, int newValue) {
            calendar.set(Calendar.SECOND, newValue);
            change();
        }
    };

    /**
     * 滑动改变监听器回调的接口
     */
    public interface OnChangeListener {
        void onChange(int hour, int munite);
    }

    /**
     * 设置滑动改变监听器
     * @param onChangeListener
     */
    public void setOnChangeListener(OnChangeListener onChangeListener){
        this.onChangeListener = onChangeListener;
    }

    /**
     * 滑动最终调用的方法
     */
    private void change(){
        if(onChangeListener!=null){
            //onChangeListener.onChange(getHourOfDay(),getMinute());
        }
    }


    /**
     * 获取小时
     * @return
     */
    public int getHourOfDay(){
        return hourList.get(hours.getCurrentItem()).getHour();
    }

    /**
     * 获取分钟
     * @return
     */
    public int getMinute(){
        return minuteList.get(mins.getCurrentItem()).getMinute();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
