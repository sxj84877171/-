package com.soarsky.car.ui.trivelrecord.autoopen;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.trivelrecord.selectweek.SelectWeekActivity;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;

import java.util.Date;


/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2017/3/6<br><br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  行车记录设置保护时间入口<br>
 */



public class AutoOpenActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 是否开启ImageView
     */
    private ImageView switchTv;
    /**
     * 开始时间TextView
     */
    private TextView startTimeTv;
    /**
     * 结束时间TextView
     */
    private TextView endTimeTv;
    /**
     * 选中日期
     */
    private TextView autoWeekTv;

    /**
     * 是否打开自动乘车 0表示打开1表示未打开
     */
    private int isOpen;
    /**
     * 开始时
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 星期
     */
    private String autoWeek;

    /**
     * 确认
     */
    private ImageView autoConfirmTv;
    /**
     * 当前选择的时间用来判断结束时间是否大于开始时间
     */
    private String selectTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_auto_open;
    }

    @Override
    public void initView() {
        findViewById(R.id.auto_selectWeek_rl).setOnClickListener(this);
        findViewById(R.id.auto_starttime_rl).setOnClickListener(this);
        findViewById(R.id.auto_endtime_rl).setOnClickListener(this);
        findViewById(R.id.auto_confirm).setOnClickListener(this);
        findViewById(R.id.autoOpen_blackView).setOnClickListener(this);
        switchTv = (ImageView) findViewById(R.id.autoOpen_switch);
        switchTv.setOnClickListener(this);
        startTimeTv = (TextView) findViewById(R.id.auto_starttime);
        endTimeTv = (TextView) findViewById(R.id.auto_endtime);
        autoWeekTv = (TextView) findViewById(R.id.auto_week);
        autoConfirmTv = (ImageView) findViewById(R.id.auto_confirm);
        initData();


    }

    /**
     * 初始化数据
     */
    private void initData() {
        if (TextUtils.isEmpty(SpUtil.get(Constants.TRIVELRECORDISOPEN))) {
            isOpen = 1;
        } else {
            isOpen = Integer.parseInt(SpUtil.get(Constants.TRIVELRECORDISOPEN));
        }

        if (isOpen == 0) {
            switchTv.setImageDrawable(getResources().getDrawable(R.mipmap.auto_open));
        } else {
            switchTv.setImageDrawable(getResources().getDrawable(R.mipmap.auto_colse));
        }


        if (TextUtils.isEmpty(SpUtil.get(Constants.TRIVELRECORDSTARTTIME))) {
            startTimeTv.setText("请选择");
        } else {
            startTimeTv.setText(SpUtil.get(Constants.TRIVELRECORDSTARTTIME));
            startTime = SpUtil.get(Constants.TRIVELRECORDSTARTTIME);
        }
        if (TextUtils.isEmpty(SpUtil.get(Constants.TRIVELRECORDENDTIME))) {
            endTimeTv.setText("请选择");
        } else {
            endTimeTv.setText(SpUtil.get(Constants.TRIVELRECORDENDTIME));
            endTime = SpUtil.get(Constants.TRIVELRECORDENDTIME);
        }
        if (TextUtils.isEmpty(SpUtil.get(Constants.AUTOWEEKDAY))) {
            autoWeekTv.setText("");
        } else {

            autoWeek = SpUtil.get(Constants.AUTOWEEKDAY);
            autoWeekTv.setText(getAutoWeekStr(autoWeek));
        }
        notifyView();

    }


    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.auto_selectWeek_rl:
                startActivityForResult(new Intent(this, SelectWeekActivity.class), 101);
                break;
            case R.id.auto_starttime_rl:
                getTime(1);
                break;
            case R.id.auto_endtime_rl:
                getTime(2);
                break;
            case R.id.autoOpen_switch:
                if (isOpen == 1) {
                    isOpen = 0;
                    switchTv.setImageDrawable(getResources().getDrawable(R.mipmap.auto_open));

                } else {
                    isOpen = 1;
                    switchTv.setImageDrawable(getResources().getDrawable(R.mipmap.auto_colse));
                }
                notifyView();
                break;


            case R.id.auto_confirm:
                SpUtil.save(Constants.AUTOWEEKDAY,autoWeek);
                SpUtil.save(Constants.TRIVELRECORDSTARTTIME,startTime);
                SpUtil.save(Constants.TRIVELRECORDENDTIME,endTime);
                SpUtil.save(Constants.TRIVELRECORDISOPEN,isOpen+"");
                finish();

                break;
            case R.id.autoOpen_blackView:
                finish();
                break;


        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 101 && requestCode == 101) {
            autoWeek = (String) data.getExtras().get(Constants.AUTOWEEKDAY);
            autoWeekTv.setText(getAutoWeekStr(autoWeek));
            notifyView();
        }

    }


    /**
     * 获取重复的星期从SelectWeekActivity会返回0123456的字符串将字符串装换成周几
     * @param autoWeek
     * @return
     */
    private String getAutoWeekStr(String autoWeek) {
        StringBuffer sb = new StringBuffer();

        if (autoWeek.contains("0")) {
            sb.append("周日");
        }
        if (autoWeek.contains("1")) {
            sb.append("周一");
        }
        if (autoWeek.contains("2")) {
            sb.append("周二");
        }
        if (autoWeek.contains("3")) {
            sb.append("周三");
        }
        if (autoWeek.contains("4")) {
            sb.append("周四");
        }
        if (autoWeek.contains("5")) {
            sb.append("周五");
        }
        if (autoWeek.contains("6")) {
            sb.append("周六");
        }
        return sb.toString();


    }

    /**
     * 跟新界面
     */
    private void notifyView() {

            if (TextUtils.isEmpty(startTime)) {
                autoConfirmTv.setImageDrawable(getResources().getDrawable(R.mipmap.auto_confirm2));
                autoConfirmTv.setEnabled(false);
            } else if (TextUtils.isEmpty(endTime)) {
                autoConfirmTv.setImageDrawable(getResources().getDrawable(R.mipmap.auto_confirm2));
                autoConfirmTv.setEnabled(false);
            } else if (TextUtils.isEmpty(autoWeek)) {
                autoConfirmTv.setImageDrawable(getResources().getDrawable(R.mipmap.auto_confirm2));
                autoConfirmTv.setEnabled(false);
            } else {
                autoConfirmTv.setImageDrawable(getResources().getDrawable(R.mipmap.auto_confirm1));
                autoConfirmTv.setEnabled(true);
            }


//        }


    }

    /**
     * 设置时间
     * @param type 1表示开始时间 2表示结束时间
     */
    public void getTime(final int type) {

        Date date = new Date();
        final int hour = date.getHours();
        int minutes = date.getMinutes();

        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (minute < 10) {
                    selectTime = hourOfDay + ":0" + minute;
                } else {
                    selectTime = hourOfDay + ":" + minute;
                }

                if (type == 1) {
                    if (compareTime(1, selectTime)) {
                        startTimeTv.setText(selectTime);
                        startTime = selectTime;
                        notifyView();
                    } else {
                        ToastUtil.show(AutoOpenActivity.this, "开始时间不能小于结束时间");
                    }


                } else {
                    if (compareTime(2, selectTime)) {
                        endTimeTv.setText(selectTime);
                        endTime = selectTime;
                        notifyView();
                    } else {
                        ToastUtil.show(AutoOpenActivity.this, "开始时间不能小于结束时间");
                    }
                }
            }
        }, hour, minutes, true).show();

    }


    /**
     * 比较时间大小
     *
     * @param type
     * @param time
     */
    private boolean compareTime(int type, String time) {

        if (type == 1) {
            if (TextUtils.isEmpty(endTime)) {
                return true;
            }
            String[] end = endTime.split(":");
            String[] start = time.split(":");

            int endHours = Integer.parseInt(end[0]);
            int endminutes = Integer.parseInt(end[1]);
            int startHours = Integer.parseInt(start[0]);
            int startminutes = Integer.parseInt(start[1]);

            if (endHours < startHours) {
                return false;
            }
            if (endHours == startHours && endminutes < startminutes) {
                return false;
            }
            return true;
        } else {
            {
                if (TextUtils.isEmpty(startTime)) {
                    return true;
                }
                String[] end = time.split(":");
                String[] start = startTime.split(":");

                int endHours = Integer.parseInt(end[0]);
                int endminutes = Integer.parseInt(end[1]);
                int startHours = Integer.parseInt(start[0]);
                int startminutes = Integer.parseInt(start[1]);

                if (endHours < startHours) {
                    return false;
                }
                if (endHours == startHours && endminutes < startminutes) {
                    return false;
                }
                return true;
            }
        }
    }


}




