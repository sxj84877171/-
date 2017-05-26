package com.soarsky.car.ui.borrowandreturn.borrowrecord;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.data.local.db.bean.Tborrow;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/1
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  借车记录P层
 */

public class BorrowRecordPresent extends BasePresenter<BorrowRecordMedol,BorrowRecordView> {

    public static final String REFUSE_STATE = "4" ;

    private String owenPhoneNum ;

    public void setOwenPhoneNum(String owenPhoneNum) {
        this.owenPhoneNum = owenPhoneNum;
    }

    @Override
    public void onStart() {

    }

    /**
     * 根据车牌号，用户名获取借车记录
     * @param phone
     * @param userName
     */
    public void getRecord(String phone,String userName){
        mView.showProgess();
        mModel.record(phone,userName).subscribe(new Subscriber<BorrowRecordParm>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError();
            }

            @Override
            public void onNext(BorrowRecordParm borrowRecordParm) {
                mView.stopProgess();
                if (borrowRecordParm.getStatus().equals(SUCCESS_FLAG)){
                    mView.showSuccess(fill(borrowRecordParm.getData()));
                }else {
                    mView.showFail(borrowRecordParm);
                }

            }
        });

    }


    public List<BorrowRecords> fill(List<BorrowRecords> list){
        List<BorrowRecords> ret = new ArrayList<>();

        if(list != null){
            for(BorrowRecords borrowRecords:list){
                if(!(REFUSE_STATE.equals(borrowRecords.getStatus()) && borrowRecords.getOwner().equals(owenPhoneNum))){
                    ret.add(borrowRecords);
                }
            }
        }

        return ret;
    }

    /**
     * 插入借车记录表数据
     * @param tborrow
     */
    public void insertBorrowRecord(Tborrow tborrow){
        mView.showProgess();
        mRxManager.add(mModel.insertBorrowRecord(tborrow).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Tborrow>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.insertFail();
            }

            @Override
            public void onNext(Tborrow tborrow) {
                mView.stopProgess();
                //Log.d("TAA","++success"+tborrow.toJson());
                mView.insertSuccess();
            }
        }));
    }
}
