package com.soarsky.installationmanual.base;

import android.app.Activity;

/**
 * 安装手册<br>
 * 作者： 孙向锦<br>
 * 时间： 2017/02/09<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * Presenter 基类，负责整个MVP架构P逻辑<br>
 */
public abstract class BasePresenter<M, V> {

    /**
     * 请求数据数据成功标记
     */
    public static final String SUCCESS_FLAG = "0" ;
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
    /**
     * 上下文对象
     */
    public Activity context;
    /**
     * model
     */
    public M mModel;
    /**
     * view
     */
    public V mView;
    public RxManager mRxManager = new RxManager();

    public void setVM(V v, M m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    /**
     * 刚开始创建时调用
     */
    public abstract void onStart();

    /**
     * 清除订阅者和事件信息
     */
    public void onDestroy() {
        mRxManager.clear();
    }



}
