package com.soarsky.installationmanual.base;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

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
 * 用于管理RxBus的事件和Rxjava相关代码的生命周期处理<br>
 */
public class RxManager {

    /**
     * RxBus 单例
     */
    public RxBus mRxBus = RxBus.$();

    /**
     * 管理观察源
     */
    private Map<String, Observable<?>> mObservables = new HashMap<>();

    /**
     * 管理订阅者者
     */
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    /**
     * 注册事件
     *
     * @param eventName 事件名
     * @param action1   对应事件的Action
     */
    public void on(String eventName, Action1<Object> action1) {
        Observable<?> mObservable = mRxBus.register(eventName);
        mObservables.put(eventName, mObservable);
        mCompositeSubscription.add(mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }));
    }

    /**
     * 添加订阅者
     *
     * @param m
     */
    public void add(Subscription m) {
        mCompositeSubscription.add(m);
    }

    /**
     * 清除订阅者和事件信息
     */
    public void clear() {
        mCompositeSubscription.unsubscribe();// 取消订阅
        for (Map.Entry<String, Observable<?>> entry : mObservables.entrySet())
            mRxBus.unregister(entry.getKey(), entry.getValue());// 移除观察
    }

    /**
     * 提交发生的某种事件，并且把发生的参数传入
     *
     * @param tag     事件标记
     * @param content 事件发生内容
     */
    public void post(Object tag, Object content) {
        mRxBus.post(tag, content);
    }
}
