package com.soarsky.car.ui.violationdeal;

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
 * 程序功能：
 * 该类为
 */

public class ViolationDealPresent extends BasePresenter<ViolationDealModel,ViolationDealView>{

    @Override
    public void onStart() {

    }

    /**
     * 获取违章处理信息
     * @param param
     */
    public void violationDeal(ViolationDealSendParam param){

        mView.showProgess();
        mModel.violationDeal(param).subscribe(new Subscriber<ViolationDealParam>() {
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
            public void onNext(ViolationDealParam violationDealParam) {
                mView.stopProgess();
                mView.showSuccess(violationDealParam);
            }
        });

    }

}
