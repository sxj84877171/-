package com.soarsky.car.ui.illegal.fragment.driver;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ViolationDealInfo;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/28<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：根据驾驶证获取违章信息present<br>
 * 该类为 IllegalDriverFragmentPresent<br>
 */


public class IllegalDriverFragmentPresent extends BasePresenter<IllegalDriverFragmentModel,IllegalDriverFragmentView>{

    @Override
    public void onStart() {

    }

    /**
     * 获取违章处理信息
     * @param idNo 身份证
     */
    public void getIlleagaInfoByIdNo(String idNo){

        mView.showProgess();
        mModel.getIlleagaInfoByIdNo(idNo).subscribe(new Subscriber<ResponseDataBean<ViolationDealInfo>>() {
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
            public void onNext(ResponseDataBean<ViolationDealInfo> violationDealParam) {
                mView.stopProgess();
                mView.showSuccess(violationDealParam);
            }
        });

    }

    /**
     * 违章劝离超过十分钟申请
     * @param id 违章id
     */
    public void appViolationAdvice(String id){

        mView.showProgess();
        mModel.appViolationAdvice(id).subscribe(new Subscriber<ResponseDataBean<Void>>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.appViolationAdviceFail();
            }

            @Override
            public void onNext(ResponseDataBean<Void> bean) {

                mView.stopProgess();
                mView.appViolationAdviceSuccess(bean);
            }
        });

    }

}
