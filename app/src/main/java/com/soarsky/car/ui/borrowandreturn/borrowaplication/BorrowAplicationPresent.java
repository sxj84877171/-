package com.soarsky.car.ui.borrowandreturn.borrowaplication;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.ui.borrowandreturn.ModifyStatusParm;
import com.soarsky.car.ui.borrowandreturn.recorddetails.DetailsParm;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/2
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  收到的借车申请P层
 */

public class BorrowAplicationPresent extends BasePresenter<BorrowAplicationMedol,BorrowAplicationView> {
    @Override
    public void onStart() {

    }

    /**
     * 同意借车
     */
    public void agree(){
        mView.showProgess();
        mModel.agree().subscribe(new Subscriber<ModifyStatusParm>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError();
            }

            @Override
            public void onNext(ModifyStatusParm modifyStatusParm) {
              if (modifyStatusParm.getStatus().equals(SUCCESS_FLAG)){
                  mView.stopProgess();
                  mView.agree(modifyStatusParm);
              } else {
                  mView.stopProgess();
                  mView.showFail(modifyStatusParm);
              }
            }
        });

    }

    /**
     * 拒绝借车
     * @param mark
     */
    public void refuse(String mark){
        mView.showProgess();
        mModel.refuse(mark).subscribe(new Subscriber<ModifyStatusParm>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError();
            }

            @Override
            public void onNext(ModifyStatusParm modifyStatusParm) {
                mView.stopProgess();
                if (modifyStatusParm.getStatus().equals(SUCCESS_FLAG)){
                    mView.showSuccess(modifyStatusParm);
                }else {
                    mView.showFail(modifyStatusParm);
                }

            }
        });
    }

    /**
     * 获取记录详情
     * @param bId
     */
    public void getDetails(Integer bId){


        mView.showProgess();
        mModel.detail(bId).subscribe(new Subscriber<DetailsParm>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError(e);
            }

            @Override
            public void onNext(DetailsParm detailParm) {
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
