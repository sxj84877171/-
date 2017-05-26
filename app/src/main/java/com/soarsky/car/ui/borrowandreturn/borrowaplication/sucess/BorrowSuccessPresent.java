package com.soarsky.car.ui.borrowandreturn.borrowaplication.sucess;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.ui.borrowandreturn.recorddetails.DetailsParm;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/14
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  借车方借车成功页面P层
 */


public class BorrowSuccessPresent extends BasePresenter<BorrowSuccessModel,BorrowSuccessView> {
    @Override
    public void onStart() {

    }

    public void getDetails(Integer bId){


        mView.showProgess();
        mModel.detail(bId).subscribe(new Subscriber<DetailsParm>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
            }

            @Override
            public void onNext(DetailsParm detailParm) {
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
