package com.soarsky.car;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.ui.modifypwd.ModifyPwdParam;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/8.
 */

public class ModifyPwdModel implements BaseModel{

    public Observable<ModifyPwdParam> modifyPwd(String username, String oldpwd, String newpwd){

       if(username.equals("sucess")){
           return Observable.create(new Observable.OnSubscribe<ModifyPwdParam>(){

               @Override
               public void call(Subscriber<? super ModifyPwdParam> subscriber) {
                   subscriber.onNext(new ModifyPwdParam("0","密码修改成功"));
               }
           });
       }else{

           return Observable.create(new Observable.OnSubscribe<ModifyPwdParam>(){

               @Override
               public void call(Subscriber<? super ModifyPwdParam> subscriber) {
                   subscriber.onNext(new ModifyPwdParam("1","原始密码错误"));
               }
           });

       }
    }

}
