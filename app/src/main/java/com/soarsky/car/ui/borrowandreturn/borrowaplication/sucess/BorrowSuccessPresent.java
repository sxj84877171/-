package com.soarsky.car.ui.borrowandreturn.borrowaplication.sucess;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.DetailsInfo;
import com.soarsky.car.bean.ResponseDataBean;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/12/14<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  借车方借车成功页面P层<br>
 */


public class BorrowSuccessPresent extends BasePresenter<BorrowSuccessModel,BorrowSuccessView> {
    @Override
    public void onStart() {

    }

    /**
     * 查询借车详细信息
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
            }

            @Override
            public void onNext(ResponseDataBean<DetailsInfo> detailParm) {
                mView.stopProgess();
                if (SUCCESS_FLAG.equals(detailParm.getStatus())){
                    mView.showSucess(detailParm);
                }else {
                    mView.showFail();
                }
            }
        });
    }
}
