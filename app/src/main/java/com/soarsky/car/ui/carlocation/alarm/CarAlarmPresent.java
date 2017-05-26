package com.soarsky.car.ui.carlocation.alarm;

import com.soarsky.car.base.BasePresenter;

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
 * 程序功能：防盗报警present
 * 该类为
 */

public class CarAlarmPresent extends BasePresenter<CarAlarmModel,CarAlarmView>{

    @Override
    public void onStart() {

    }

    /**
     * 获取防盗报警历史记录
     * @param param
     */
    public void getTheftRecords(CarAlarmSendParam param){

        mView.showProgess();
        mModel.getTheftRecords(param).subscribe(new Subscriber<CarAlarmParam>() {
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
            public void onNext(CarAlarmParam carAlarmParam) {

                mView.stopProgess();
                mView.showSuccess(carAlarmParam);
            }
        });

    }
}
