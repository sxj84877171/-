package com.soarsky.car.base;

import android.app.Activity;

/**
 * 车主APP
 * 作者： 孙向锦
 * 时间： 2016/12/6
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * Presenter 基类，负责整个MVP架构P逻辑
 */
public abstract class BasePresenter<M, V> {

    /**
     * 请求数据数据成功标记
     */
    public static final String SUCCESS_FLAG = "200" ;
    /**
     * 检测最新版本号标志
     */
    public static final String CHECK_VERSION = "1" ;
    /**
     * 闪灯找车无法连接标志
     */
    public static final String CONNET_FAIL = "1" ;
    /**
     * 闪灯找车没有sim卡标志
     */
    public static final String NO_SIM = "2" ;

    public Activity context;
    public M mModel;
    public V mView;
    public RxManager mRxManager = new RxManager();

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    public abstract void onStart();

    public void onDestroy() {
        mRxManager.clear();
    }



}
