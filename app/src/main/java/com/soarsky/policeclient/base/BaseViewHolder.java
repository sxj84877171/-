package com.soarsky.policeclient.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * 警务通<br>
 * 作者： 孙向锦<br>
 * 时间： 2016/12/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 ViewHolder 基类，BaseViewHolder抽象，给MVP架构整合<br>
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public Context mContext;

    public BaseViewHolder(View v) {
        super(v);
        mContext = v.getContext();
        if (((ViewGroup) v).getChildCount() > 0)
            ButterKnife.bind(this, v);
    }

    /**
     * ViewHolder的Type，同时也是它的LayoutId
     *
     * @return
     */
    public abstract int getType();

    /**
     * 绑定ViewHolder
     *
     * @return
     */
    public abstract void onBindViewHolder(View view, T obj);

}
