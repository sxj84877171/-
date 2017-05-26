package com.soarsky.car.ui.car;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.base.RxManager;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/8
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：机动车present
 * 该类为
 */

public class CarPresent extends BasePresenter<CarModel,CarView>{

    @Override
    public void onStart() {

    }

    /**
     * 机动车
     * @param param
     */
    public void getCarInfo(CarSendParam param){

        mView.showProgess();
        mRxManager.add(mModel.getCarInfo(param).subscribe(new Subscriber<CarParam>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.showError();
            }

            @Override
            public void onNext(CarParam param) {

                mView.stopProgess();
                mView.showSuccess(param);
            }
        }));
    }

}
