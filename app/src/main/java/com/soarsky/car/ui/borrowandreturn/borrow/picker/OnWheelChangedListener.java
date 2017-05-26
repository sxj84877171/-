package com.soarsky.car.ui.borrowandreturn.borrow.picker;

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
public interface OnWheelChangedListener {
    /**
     * Callback method to be invoked when current item changed
     * @param wheel the wheel view whose state has changed
     * @param oldValue the old value of current item
     * @param newValue the new value of current item
     */
    void onChanged(WheelView wheel, int oldValue, int newValue);
}
