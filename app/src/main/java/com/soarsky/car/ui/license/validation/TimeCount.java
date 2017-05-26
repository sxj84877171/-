package com.soarsky.car.ui.license.validation;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;

import com.soarsky.car.R;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 验证倒计时
 */

public class TimeCount extends CountDownTimer {

    private Button contentBtn;

    private Context mContext;

    public TimeCount(long millisInFuture, long countDownInterval,Button contentBtn,Context mContext){
        super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        this.contentBtn = contentBtn;
        this.mContext = mContext;
    }

    @Override
    public void onTick(long millisUntilFinished) {
//        计时过程显示
        contentBtn.setClickable(false);
        contentBtn.setBackgroundColor(mContext.getResources().getColor(R.color.grey));
        contentBtn.setText(millisUntilFinished /1000+"秒");
    }

    @Override
    public void onFinish() {
        contentBtn.setText("重新验证");
        contentBtn.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
        contentBtn.setClickable(true);
    }
}
