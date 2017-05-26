package com.soarsky.car.ui.carchange;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.ui.login.CarNumParam;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：切换车辆的present
 * 该类为
 */


public class CarChangePresent extends BasePresenter<CarChangeModel,CarChangeView>{

    @Override
    public void onStart() {

    }

    /**
     * 获取车牌号
     * @param phone
     */
    public void getAllCarnum(String phone){

        mView.showProgess();

        mModel.getAllCarnum(phone).subscribe(new Subscriber<CarNumParam>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.getAllCarnumFail();

            }

            @Override
            public void onNext(CarNumParam carNumParam) {

                mView.stopProgess();
                mView.getAllCarnumSuccess(carNumParam);

            }
        });

    }
}
