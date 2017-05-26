package com.soarsky.car.ui.setting;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.ui.login.LoginActivity;
import com.soarsky.car.ui.modifypwd.ModifyPwdActivity;
import com.soarsky.car.ui.trivelrecord.selectweek.SelectWeekActivity;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import java.util.Date;

import static com.soarsky.car.ConstantsUmeng.EXIT;
import static com.soarsky.car.R.id.iv_switch;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/5/11<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为设置界面<br>
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 返回
     */
    private LinearLayout illegalBackLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 乘车记录时间选择的布局
     */
    private LinearLayout ll_open;

    /**
     * 开启乘车记录的按钮
     */
    private RelativeLayout byCarRecord;

    /**
     * 是否打开自动乘车 0表示打开1表示未打开
     */
    private int isOpen = 0 ;
    private ImageView switchTv;

    /**
     * 修改密码
     */
    private RelativeLayout changePwd;

    /**
     * 安全退出
     */
    private RelativeLayout rl_exit;

    /**
     * 修改手机号
     */
    private RelativeLayout changePhoneNum;

    /**
     * 意见反馈
     */
    private RelativeLayout feedback;

    /**
     * 当前选择的时间用来判断结束时间是否大于开始时间
     */
    private String selectTime;

    /**
     * 开始时
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    private App app;
    /**
     * 结束时间TextView
     */
    private TextView endTimeTv;
    /**
     * 开始时间TextView
     */
    private TextView startTimeTv;

    /**
     * 选中日期
     */
    private TextView autoWeekTv;

    /**
     * 星期
     */
    private String autoWeek;
    private RelativeLayout auto_endtime_rl;

    /**
     * 给个好评
     */
    private RelativeLayout praise;
    @Override
    public int getLayoutId() {
        return R.layout.activity_setting3;
    }

    @Override
    public void initView() {
        app = (App) getApplication();

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.seting));

        illegalBackLay = (LinearLayout) findViewById(R.id.illegalBackLay);
        illegalBackLay.setOnClickListener(this);

        byCarRecord = (RelativeLayout) findViewById(R.id.byCarRecord);
        byCarRecord.setOnClickListener(this);

        switchTv = (ImageView) findViewById(iv_switch);

        changePwd = (RelativeLayout) findViewById(R.id.changePwd);
        changePwd.setOnClickListener(this);

        rl_exit = (RelativeLayout) findViewById(R.id.rl_exit);
        rl_exit.setOnClickListener(this);

        ll_open = (LinearLayout) findViewById(R.id.ll_open);

        changePhoneNum = (RelativeLayout) findViewById(R.id.changePhoneNum);
        changePhoneNum.setOnClickListener(this);

        feedback = (RelativeLayout) findViewById(R.id.feedback);
        feedback.setOnClickListener(this);

        findViewById(R.id.auto_starttime_rl).setOnClickListener(this);
        endTimeTv = (TextView) findViewById(R.id.auto_endtime);
        startTimeTv = (TextView) findViewById(R.id.auto_starttime);

        autoWeekTv = (TextView) findViewById(R.id.auto_week);

        auto_endtime_rl = (RelativeLayout) findViewById(R.id.auto_endtime_rl);
        auto_endtime_rl.setOnClickListener(this);

        findViewById(R.id.auto_selectWeek_rl).setOnClickListener(this);

        praise = (RelativeLayout) findViewById(R.id.praise);
        praise.setOnClickListener(this);

        initData();
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.illegalBackLay:
                finish();
                break;
            case R.id.byCarRecord:
                if (isOpen == 0){
                    SpUtil.save(Constants.TRIVELRECORDISOPEN,isOpen+"");
                    ll_open.setVisibility(View.VISIBLE);
                    switchTv.setImageResource(R.mipmap.switch_open);
                    isOpen = 1;
                }else if(isOpen == 1){
                    SpUtil.save(Constants.TRIVELRECORDISOPEN,isOpen+"");
                    ll_open.setVisibility(View.GONE);
                    switchTv.setImageResource(R.mipmap.switch_close);
                    isOpen = 0;
                }

                break;
            case R.id.changePwd:
                startActivity(new Intent(this, ModifyPwdActivity.class));
                break;
            case R.id.rl_exit:
                MobclickAgent.onEvent(this, EXIT);
                //退出账号
                if (app.isOnline() == true) {

                    new AlertDialog.Builder(mContext).setTitle(mContext.getString(R.string.back_title))
                            .setPositiveButton(mContext.getString(R.string.back_sure), new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“确认”后的操作
                                    app.setOnline(false);
//                                    SpUtil.save(Constants.CONS_USERNAME,"");
                                    SpUtil.saveUserName(SettingActivity.this, "");

                                    App.getApp().setConfirmDriver(false);
                                    //app.exit();
                                    startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                                    MobclickAgent.onProfileSignOff();
                                    stopService(new Intent(SettingActivity.this, ConfirmDriverService.class));
                                    app.getActivity().finish();
                                    finish();


                                }
                            })
                            .setNegativeButton(mContext.getString(R.string.back_cancel), new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“返回”后的操作,这里不设置没有任何操作
                                }
                            }).show();
                }

                break;
            case R.id.changePhoneNum:
                startActivity(new Intent(SettingActivity.this,ModifyPhoneNumActivity.class));
                break;
            case R.id.feedback:
                startActivity(new Intent(SettingActivity.this,FeedBackActivity.class));
                break;
            case R.id.auto_starttime_rl:
                getTime(1);
                SpUtil.save(Constants.TRIVELRECORDSTARTTIME,startTime);
                break;
            case R.id.auto_endtime_rl:
                getTime(2);
                SpUtil.save(Constants.TRIVELRECORDENDTIME,endTime);
                break;
            case R.id.auto_selectWeek_rl:
                startActivityForResult(new Intent(this, SelectWeekActivity.class), 101);

                break;
            case R.id.praise:
                shareAppShop(SettingActivity.this,"com.soarsky.car");
                break;
        }
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
                        //notifyView();
                    } else {
                        ToastUtil.show(SettingActivity.this, "开始时间不能小于结束时间");
                    }


                } else {
                    if (compareTime(2, selectTime)) {
                        endTimeTv.setText(selectTime);
                        endTime = selectTime;
                       // notifyView();
                    } else {
                        ToastUtil.show(SettingActivity.this, "开始时间不能小于结束时间");
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


    /**
     * 初始化数据
     */
    private void initData() {
        if (TextUtils.isEmpty(SpUtil.get(Constants.TRIVELRECORDISOPEN))) {
            isOpen = 1;
            ll_open.setVisibility(View.GONE);
        } else {
            isOpen = Integer.parseInt(SpUtil.get(Constants.TRIVELRECORDISOPEN));
            //ll_open.setVisibility(View.VISIBLE);

            if (isOpen == 0) {
                switchTv.setImageDrawable(getResources().getDrawable(R.mipmap.switch_open));
                ll_open.setVisibility(View.VISIBLE);
                isOpen = 1;
            } else {
                switchTv.setImageDrawable(getResources().getDrawable(R.mipmap.switch_close));
                ll_open.setVisibility(View.GONE);
                isOpen = 0;
            }
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
            autoWeekTv.setText("请选择");
        } else {

            autoWeek = SpUtil.get(Constants.AUTOWEEKDAY);
            autoWeekTv.setText(getAutoWeekStr(autoWeek));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 101 && requestCode == 101) {
            autoWeek = (String) data.getExtras().get(Constants.AUTOWEEKDAY);
            autoWeekTv.setText(getAutoWeekStr(autoWeek));
            //notifyView();
        }
        SpUtil.save(Constants.AUTOWEEKDAY,autoWeek);

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
     * 根据应用包名，跳转到应用市场
     *
     * @param activity    承载跳转的Activity
     * @param packageName 所需下载（评论）的应用包名
     */
    public static void shareAppShop(Activity activity, String packageName) {
        try {
            Uri uri = Uri.parse("market://details?id="+ packageName);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(activity, "您没有安装应用市场", Toast.LENGTH_SHORT).show();
        }
    }
}
