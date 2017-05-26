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

public class ArrayWheelAdapter<T> implements WheelAdapter {
    /** The default items length */
    public static final int DEFAULT_LENGTH = -1;

    // items
    private T items[];

    // length
    private int length;

    /**
     * Constructor
     * @param items the items
     * @param length the max items length
     */
    public ArrayWheelAdapter(T items[], int length) {
        this.items = items;
        this.length = length;
    }

    /**
     * Contructor
     * @param items the items
     */
    public ArrayWheelAdapter(T items[]) {
        this(items, DEFAULT_LENGTH);
    }

    @Override
    public String getItem(int index) {
        if (index >= 0 && index < items.length) {
            return items[index].toString();
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        return items.length;
    }

    @Override
    public int getMaximumLength() {
        return length;
    }
}
