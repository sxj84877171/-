package com.soarsky.car.ui.blindterm.validation;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import com.soarsky.car.R;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：验证码倒计时显示器 <br>
 * 该类为 发送验证码倒计时显示器 <br>
 */


public class BlindTermValidTimeCount extends CountDownTimer {
    /**
     * 计时文本
     */
    private TextView contentTv;
    /**
     * 上下文本
     */
    private Context mContext;

    /**
     * 其构造函数
     * @param millisInFuture  参数依次为总时长
     * @param countDownInterval 计时的时间间隔
     * @param mContext 上下文本
     * @param contentTv 文本框
     */
    public BlindTermValidTimeCount(long millisInFuture, long countDownInterval,Context mContext,TextView contentTv){
        super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        this.contentTv = contentTv;
        this.mContext = mContext;
    }

    /**
     * 其计时过程
     * @param millisUntilFinished 时间间隔
     */
    @Override
    public void onTick(long millisUntilFinished) {
//        计时过程显示
        contentTv.setClickable(false);
        contentTv.setEnabled(false);
        contentTv.setTextColor(mContext.getResources().getColor(R.color.black2));
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.ver_code_edit_d_bg);
        contentTv.setBackgroundDrawable(drawable);
        contentTv.setText(millisUntilFinished /1000+"秒");
    }

    /**
     * 计时完成
     */
    @Override
    public void onFinish() {
        contentTv.setText(mContext.getString(R.string.blind_term_send_code));
        contentTv.setTextColor(mContext.getResources().getColor(R.color.colorAccent));
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.ver_code_edit_bg);
        contentTv.setBackgroundDrawable(drawable);
        contentTv.setClickable(true);
        contentTv.setEnabled(true);

    }
}
