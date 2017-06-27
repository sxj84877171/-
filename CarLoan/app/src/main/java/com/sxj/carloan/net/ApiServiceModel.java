package com.sxj.carloan.net;

import com.sxj.carloan.bean.ServerBean;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * CarLoan
 * 作者： Elvis
 * 时间： 2017/6/26
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class ApiServiceModel {

    public static <T> Observable.Transformer<T, T> io_main() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public Observable<LoginBack> UserLogin(String username,String password){
        return Api.getInstance().getService().UserLogin("UserLogin",username,password).compose(ApiServiceModel.<LoginBack>io_main()).map(new Func1<LoginBack, LoginBack>() {
            @Override
            public LoginBack call(LoginBack o) {
                // save
                return o;
            }
        });
    }

    public Observable<ServerBean> PageWork(String sql,String pageSize,String desc){
        return Api.getInstance().getService().PageWork("PageWork",sql,pageSize,desc).compose(ApiServiceModel.<ServerBean>io_main()).map(new Func1<ServerBean, ServerBean>() {
            @Override
            public ServerBean call(ServerBean o) {
                // save
                return o;
            }
        });
    }
}
