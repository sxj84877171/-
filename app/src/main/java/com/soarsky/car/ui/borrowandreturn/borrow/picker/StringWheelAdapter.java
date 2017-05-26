package com.soarsky.car.ui.borrowandreturn.borrow.picker;

import java.util.ArrayList;

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
public class StringWheelAdapter implements WheelAdapter {
    /** The default items length */
    public static final int DEFAULT_LENGTH = -1;

    // items
    private ArrayList<DateObject> list;

    // length
    private int length;

    /**
     * Constructor
     * @param
     * @param length the max items length
     */
    public StringWheelAdapter(ArrayList<DateObject> list, int length) {
        this.list = list;
        this.length = length;
    }


    @Override
    public String getItem(int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index).getListItem();
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        return list.size();
    }

    @Override
    public int getMaximumLength() {
        return length;
    }
}
