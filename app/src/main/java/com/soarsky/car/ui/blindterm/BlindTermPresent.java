package com.soarsky.car.ui.blindterm;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.ui.car.CarParam;
import com.soarsky.car.ui.car.CarSendParam;

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
 * 程序功能：绑定终端接口的封装
 * 该类为
 */


public class BlindTermPresent extends BasePresenter<BlindTermModel,BlindTermView>{

    @Override
    public void onStart() {

    }

    /**
     * 机动车
     * @param param
     */
    public void getCarInfo(CarSendParam param){

        mView.showProgess();
        mModel.getCarInfo(param).subscribe(new Subscriber<CarParam>() {
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
        });
    }

}
