package com.soarsky.car.ui.illegal.electronic;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.CarInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.car.CarSendParam;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/29<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：电子告知present<br>
 * 该类为 IllegalElectronicPresent<br>
 */


public class IllegalElectronicPresent extends BasePresenter<IllegalElectronicModel,IllegalElectronicView>{

    @Override
    public void onStart() {

    }

    /**
     * 机动车
     * @param param 参数
     */
    public void getCarInfo(CarSendParam param){

        mView.showProgess();
        mRxManager.add(mModel.getCarInfo(param).subscribe(new Subscriber<ResponseDataBean<CarInfo>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.showCarError();
            }

            @Override
            public void onNext(ResponseDataBean<CarInfo> param) {

                mView.stopProgess();
                mView.showCarSuccess(param);
            }
        }));
    }

}
