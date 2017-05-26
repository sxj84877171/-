package com.soarsky.car.uitl;

import android.view.animation.Animation;

/**
 * 车主APP
 * 作者： 孙向锦
 * 时间： 2016/10/10
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 动画控制
 */
public class AnimationUtil {

    /**
     * 动画控制
     * @param aninm
     * @param listener
     */
    public static void setAnimationListener(Animation aninm, final AnimListener listener) {
        aninm.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                listener.onAnimFinish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


    public interface AnimListener {
        void onAnimFinish();
    }
}
