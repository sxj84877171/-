package com.soarsky.car.ui.license.validation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.widget.Button;

import com.soarsky.car.R;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：验证倒计时<br>
 * 该类为 TimeCount<br>
 */

public class TimeCount extends CountDownTimer {
    /**
     * 按钮
     */
    private Button contentBtn;
    /**
     * 上下文本
     */
    private Context mContext;

    /**
     * 其构造函数
     * @param millisInFuture
     * @param countDownInterval
     * @param contentBtn
     * @param mContext
     */
    public TimeCount(long millisInFuture, long countDownInterval,Button contentBtn,Context mContext){
        super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        this.contentBtn = contentBtn;
        this.mContext = mContext;
    }

    @Override
    public void onTick(long millisUntilFinished) {
//        计时过程显示
        contentBtn.setClickable(false);
//        contentBtn.setBackgroundColor(mContext.getResources().getColor(R.color.grey));
        ColorStateList colorStateList = mContext.getResources().getColorStateList(R.color.black2);
        contentBtn.setTextColor(colorStateList);
        contentBtn.setText(millisUntilFinished /1000+"秒");
    }

    @Override
    public void onFinish() {
        contentBtn.setText("重新验证");
        ColorStateList colorStateList = mContext.getResources().getColorStateList(R.color.colorAccent);
        contentBtn.setTextColor(colorStateList);
//        contentBtn.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));
        contentBtn.setClickable(true);
    }
}
