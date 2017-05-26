package com.soarsky.car.ui.violationquery;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ViolationQueryInfo;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：获取违章查询列表present<br>
 * 该类为ViolationQueryPresent<br>
 */

public class ViolationQueryPresent extends BasePresenter<ViolationQueryModel,ViolationQueryView>{

    @Override
    public void onStart() {

    }

    /**
     * 获取违章查询列表
     * @param param 违章请求参数
     */
    public void violationQuery(ViolationSendParam param){

        mView.showProgess();
        mModel.violationQuery(param).subscribe(new Subscriber<ResponseDataBean<ViolationQueryInfo>>() {
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
            public void onNext(ResponseDataBean<ViolationQueryInfo> param) {

                mView.stopProgess();

                mView.showSuccess(param.getData());

            }
        });

    }

}
