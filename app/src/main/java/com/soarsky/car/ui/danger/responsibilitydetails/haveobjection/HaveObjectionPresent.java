package com.soarsky.car.ui.danger.responsibilitydetails.haveobjection;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.FastDissentParam;
import com.soarsky.car.bean.ResponseDataBean;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/23
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class HaveObjectionPresent extends BasePresenter<HaveObjectionModel,HaveObjectionView>{

    @Override
    public void onStart() {

    }

    /**
     * 提交有异议接口
     */
    public void fastDissent(FastDissentSendParam param){
        mView.showProgess();
        mModel.fastDissent(param).subscribe(new Subscriber<ResponseDataBean<FastDissentParam>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.fastDissentFail();
            }

            @Override
            public void onNext(ResponseDataBean<FastDissentParam> fastDissentParam) {
                mView.stopProgess();
                mView.fastDissentSuccess(fastDissentParam);
            }
        });
    }

}
