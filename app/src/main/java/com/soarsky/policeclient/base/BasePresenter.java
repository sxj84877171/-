package com.soarsky.policeclient.base;

import android.app.Activity;

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
 * 抽象Present类，完成对Model，View的管理<br>
 * 利用RxManager来管理Rx中的方法<br>
 */
public abstract class BasePresenter<M, V> {
    public static final String SUCCESS_FLAG = "0" ;
    public static final String CHECK_TYPE = "2" ;
    public Activity context;
    public M mModel;
    public V mView;
    public RxManager mRxManager = new RxManager();

    /**
     * MVP架构，设置VM视图，模型
     * @param v 视图模型
     * @param m Model模型
     */
    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    /**
     * 程序启动需要初始化逻辑
     */
    public abstract void onStart();

    /**
     * 清除内存对象，防止事件返回，界面消失。
     */
    public void onDestroy() {
        mRxManager.clear();
    }

}
