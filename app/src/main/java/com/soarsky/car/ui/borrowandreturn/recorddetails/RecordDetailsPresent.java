package com.soarsky.car.ui.borrowandreturn.recorddetails;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.DetailsInfo;
import com.soarsky.car.bean.ResponseDataBean;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/12<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 记录详情P层<br>
 */

public class RecordDetailsPresent extends BasePresenter<RecordDetailsMedol,RecordDetailsView> {
    @Override
    public void onStart() {

    }

    /**
     * 获取记录详情
     * @param bId 记录id
     */
    public void getDetails(Integer bId){


        mView.showProgess();
        mModel.detail(bId).subscribe(new Subscriber<ResponseDataBean<DetailsInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError(e);
            }

            @Override
            public void onNext(ResponseDataBean<DetailsInfo> detailParm) {
                mView.stopProgess();
                if (SUCCESS_FLAG.equals(detailParm.getStatus())){
                    mView.showSuccess(detailParm);
                }else {
                    mView.showFail(detailParm);
                }
            }
        });
    }
}
