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

public class DatePicker extends LinearLayout {
    private Calendar calendar = Calendar.getInstance(); //������
    private WheelView newDays;
    private ArrayList<DateObject> dateList ;
    private OnChangeListener onChangeListener; //onChangeListener
    private final int MARGIN_RIGHT = 20;
    private DateObject dateObject ;		//日期数据对象
    //Constructors
    public DatePicker(Context context) {
        super(context);
        init(context);
    }

    public DatePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化
     * @param context
     */
    private void init(Context context){
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        dateList = new ArrayList<DateObject>();
        for (int i = 0; i < 7; i++) {
            dateObject = new DateObject(year, month, day+i, week+i);
            dateList.add(dateObject);
        }
        newDays = new WheelView(context);
        LayoutParams newDays_param = new LayoutParams(300,LayoutParams.WRAP_CONTENT);
        newDays_param.setMargins(0, 0, MARGIN_RIGHT, 0);
        newDays.setLayoutParams(newDays_param);
        newDays.setAdapter(new StringWheelAdapter(dateList, 7));
        newDays.setVisibleItems(3);
        newDays.setCyclic(true);
        newDays.addChangingListener(onDaysChangedListener);
        addView(newDays);

    }

    /**
     * 滑动改变监听器
     */
    private OnWheelChangedListener onDaysChangedListener = new OnWheelChangedListener(){
        @Override
        public void onChanged(WheelView mins, int oldValue, int newValue) {
            calendar.set(Calendar.DAY_OF_MONTH, newValue + 1);
            change();
        }
    };

    /**
     * 滑动改变监听器回调的接口
     */
    public interface OnChangeListener {
        void onChange(int year, int month, int day, int day_of_week);
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
            onChangeListener.onChange(
                    dateList.get(newDays.getCurrentItem()).getYear(),
                    dateList.get(newDays.getCurrentItem()).getMonth(),
                    dateList.get(newDays.getCurrentItem()).getDay(),
                    dateList.get(newDays.getCurrentItem()).getWeek());
        }
    }


    /**
     * 根据day_of_week得到汉字星期
     * @return
     */
    public static String getDayOfWeekCN(int day_of_week){
        String result = null;
        switch(day_of_week){
            case 1:
                result = "星期日";
                break;
            case 2:
                result = "星期一";
                break;
            case 3:
                result = "星期二";
                break;
            case 4:
                result = "星期三";
                break;
            case 5:
                result = "星期四";
                break;
            case 6:
                result = "星期五";
                break;
            case 7:
                result = "星期六";
                break;
            default:
                break;
        }
        return result;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
