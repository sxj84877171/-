package com.soarsky.policeclient.ui.blacklist;

import android.util.Log;

import com.soarsky.policeclient.Constants;
import com.soarsky.policeclient.base.BasePresenter;
import com.soarsky.policeclient.bean.AccidentBean;
import com.soarsky.policeclient.bean.AccidentDataBean;
import com.soarsky.policeclient.bean.CarDetailsDataBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.local.db.bean.BlackCar;

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

public class BlackListCarDetailsPresent extends BasePresenter<BlackListCarDetailsModel,BlackListCarDetailsView>{

    @Override
    public void onStart() {
    }

    public void dealBlack(String carnum,String ptype,String username){

        mModel.dealBlack(carnum,ptype,username).subscribe(new Subscriber<ResponseDataBean<Void>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError();
            }

            @Override
            public void onNext(ResponseDataBean<Void> accidentBean) {
                if(accidentBean.getStatus().equals(Constants.STATUS)){
                    mView.onSuccess();
                }else {
                    mView.onFail(accidentBean.getMessage());
                }
            }
        });
    }

    /**
     * 调用后台逻辑获取车辆详情
     * @param carNum
     */
    public void getCarDetail(String  carNum){
        mModel.getCarDetails(carNum).subscribe(new Subscriber<ResponseDataBean<CarDetailsDataBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError();
            }

            @Override
            public void onNext(ResponseDataBean<CarDetailsDataBean> detailsCarParam) {

                if (Constants.STATUS.equals(detailsCarParam.getStatus()) ){
                    mView.showData(detailsCarParam.getData());
                }else {
                    mView.onFail(detailsCarParam.getMessage());
                }

            }
        });


    }

}
