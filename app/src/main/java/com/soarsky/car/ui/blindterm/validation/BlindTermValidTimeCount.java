package com.soarsky.car.ui.blindterm.validation;

import android.content.Context;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

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
 * 程序功能：验证码倒计时显示器
 * 该类为 发送验证码倒计时显示器
 */


public class BlindTermValidTimeCount extends CountDownTimer {

    private TextView contentTv;

    private Context mContext;

    /**
     * 其构造函数
     * @param millisInFuture
     * @param countDownInterval
     * @param mContext
     * @param contentTv
     */
    public BlindTermValidTimeCount(long millisInFuture, long countDownInterval,Context mContext,TextView contentTv){
        super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        this.contentTv = contentTv;
        this.mContext = mContext;
    }

    /**
     * 其计时过程
     * @param millisUntilFinished
     */
    @Override
    public void onTick(long millisUntilFinished) {
//        计时过程显示
        contentTv.setClickable(false);
        contentTv.setEnabled(false);
        contentTv.setTextColor(mContext.getResources().getColor(R.color.grey));
        contentTv.setText(millisUntilFinished /1000+"秒");
    }

    /**
     * 计时完成
     */
    @Override
    public void onFinish() {
        contentTv.setText(mContext.getString(R.string.blind_term_send_code));
        contentTv.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        contentTv.setClickable(true);
        contentTv.setEnabled(true);

    }
}
