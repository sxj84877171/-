package com.soarsky.policeclient.ui.blacklist;

import android.util.Log;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.base.BasePresenter;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.local.db.bean.BlackCar;
import com.soarsky.policeclient.data.local.db.bean.Check;
import com.soarsky.policeclient.data.local.db.dao.CheckDao;
import com.soarsky.policeclient.uitl.LogUtil;
import com.soarsky.policeclient.uitl.StringUtils;
import com.soarsky.policeclient.uitl.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * andriod_police_app<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  黑名单列表Present<br>
 */

public class BlackListPresent extends BasePresenter<BlackListModel,BlackListView>{

    @Override
    public void onStart() {
        mModel.setContext();
    }


    /**
     * 获取本地黑名单
     */
    public  void  getBlackCar(){
        LogUtil.d("TAG","getBlackCar===============================");
        mModel.getBlackCarQuery().list().subscribe(new Subscriber<List<BlackCar>>() {
            @Override
            public void onCompleted() {
                LogUtil.d("TAG","onCompleted===============getLocalBlackCar");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<BlackCar> blackCars) {
                List<BlackCar> blackCarsTemp = new ArrayList<BlackCar>();
                List<Check> checks = App.getApp().getDaoSession().getCheckDao().queryBuilder().list();
                for (Check check:checks){
                    if(TimeUtils.date2yyyyMMddString(check.getInspectTime()).equals(TimeUtils.getCurDateyyyy_MM_dd())){
                        for (BlackCar blackCar : blackCars){
                            if(check.getCarnum().equals(blackCar.getCarnum())){
                                blackCarsTemp.add(blackCar);
                            }
                        }
                    }
                }

                mView.showBlackList(blackCarsTemp);

            }
        });

    }





    /**
     * @param list
     * 插入黑名单列表
     */
    public void insertBlackCar2(List<BlackCar> list){

        for (BlackCar blackCar:list
             ) {
            mModel.insertBlackCar(blackCar).subscribe(new Subscriber<BlackCar>() {
                @Override
                public void onCompleted() {
                    LogUtil.d("TAG","insertBlackCar===============onCompleted");
                }
                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    LogUtil.d("TAG","insertBlackCar==============onError"+e.toString());
                }
                @Override
                public void onNext(BlackCar blackCar) {
                    LogUtil.d("TAG","insertBlackCar===============blackCar");
                }
            });
        }



    }





    /**
     * @return
     * 获取本地黑名单
     */
    public  void getLocalBlackCar(){

        mModel.getBlackCarQuery().list().subscribe(new Subscriber<List<BlackCar>>() {
            @Override
            public void onCompleted() {
                LogUtil.d("TAG","onCompleted===============getLocalBlackCar");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<BlackCar> blackCars) {

            }
        });


    }





    /**
     * @return
     * 获取黑名单列表测试方法
     *
     */
    public Observable<ResponseDataBean<List<BlackCar>>> getBlackListtest(){

        return Observable.create(new Observable.OnSubscribe<ResponseDataBean<List<BlackCar>>>() {
            @Override
            public void call(Subscriber<? super ResponseDataBean<List<BlackCar>>> subscriber) {
                ResponseDataBean<List<BlackCar>> param=new ResponseDataBean<List<BlackCar>>();
                param.setMessage("请求成功");
                param.setStatus("0");
                subscriber.onNext(param);
            }
        });

    }





}
