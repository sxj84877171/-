package com.soarsky.car;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.ui.forget.ForgetParam;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/8.
 */

public class ForgetPwdModel implements BaseModel{


//    public Observable<ForgetParam> forgetPwd(String username , String oldPwd, String newPwd){
//
//
//        if(username.equals("sucess")){
//            return Observable.create(new Observable.OnSubscribe<ForgetParam>(){
//                @Override
//                public void call(Subscriber<? super ForgetParam> subscriber) {
//                    subscriber.onNext(new ForgetParam("0","新的密码已发送的您手机上"));
//                }
//            });
//        }else{
//
//            return Observable.create(new Observable.OnSubscribe<ForgetParam>(){
//                @Override
//                public void call(Subscriber<? super ForgetParam> subscriber) {
//                    subscriber.onNext(new ForgetParam("1","用户名和手机或省份证号不匹配"));
//                }
//            });
//
//        }
//    }

}
