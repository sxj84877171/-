package com.soarsky.car.ui.borrowandreturn.borrowaplication;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.DetailsInfo;
import com.soarsky.car.bean.ModifyStatusInfo;
import com.soarsky.car.bean.ResponseDataBean;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/2<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  收到的借车申请P层<br>
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
        mModel.agree().subscribe(new Subscriber<ResponseDataBean<ModifyStatusInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError();
            }

            @Override
            public void onNext(ResponseDataBean<ModifyStatusInfo> modifyStatusParm) {
              if (modifyStatusParm.getStatus().equals(SUCCESS_FLAG)){
                  mView.stopProgess();
                  mView.agree(modifyStatusParm);
              } else {
                  mView.stopProgess();
                  mView.showsFail(modifyStatusParm);
              }
            }
        });

    }

    /**
     * 拒绝借车
     * @param mark 拒绝原因
     */
    public void refuse(String mark){
        mView.showProgess();
        mModel.refuse(mark).subscribe(new Subscriber<ResponseDataBean<ModifyStatusInfo>>() {
            @Override
            public void onCompleted() {


            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError();
            }

            @Override
            public void onNext(ResponseDataBean<ModifyStatusInfo> modifyStatusParm) {
                mView.stopProgess();
                if (modifyStatusParm.getStatus().equals(SUCCESS_FLAG)){
                    mView.showsSuccess(modifyStatusParm);
                }else {
                    mView.showsFail(modifyStatusParm);
                }

            }
        });
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
