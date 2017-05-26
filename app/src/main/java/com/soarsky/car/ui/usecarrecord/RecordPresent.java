package com.soarsky.car.ui.usecarrecord;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.FaultRecordDataBean;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.UseCarRecordParam;

import java.util.List;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2017/5/10
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class RecordPresent extends BasePresenter<RecordModel,RecordView> {
    @Override
    public void onStart() {

    }

    /**
     * 获取用车记录
     */
    public void getCarRecoredsList(String carnum){

        mView.showProgess();
        mModel.getCarRecoredsList(carnum).subscribe(new Subscriber<ResponseDataBean<List<UseCarRecordParam>>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.getCarRecoredsListFail();
            }

            @Override
            public void onNext(ResponseDataBean<List<UseCarRecordParam>> useCarRecordParam) {
                mView.stopProgess();
                mView.getCarRecoredsListSuccess(useCarRecordParam);
            }
        });
    }

    /**
     * 获取故障列表
     * @param carnum
     */
    public void getFault(String carnum){

        mView.showProgess();
        mModel.getFault(carnum).subscribe(new Subscriber<ResponseDataBean<List<FaultRecordDataBean>>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.getFaultFail();
            }

            @Override
            public void onNext(ResponseDataBean<List<FaultRecordDataBean>> param) {
                mView.stopProgess();
                mView.getFaultSuccess(param);
            }
        });

    }
}
