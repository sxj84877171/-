package com.soarsky.car.ui.borrowandreturn.borrowrecord;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.BorrowRecords;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.Tborrow;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/1<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  借车记录P层<br>
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
     * @param phone 电话号码
     * @param userName 用户名
     */
    public void getRecord(String phone,String userName){
        mView.showProgess();
        mModel.record(phone,userName).subscribe(new Subscriber<ResponseDataBean<List<BorrowRecords>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError();
            }

            @Override
            public void onNext(ResponseDataBean<List<BorrowRecords>> borrowRecordParm) {
                mView.stopProgess();
                if (borrowRecordParm.getStatus().equals(SUCCESS_FLAG)){
                    mView.showSuccess(fill(borrowRecordParm.getData()));
                }else {
                    mView.showFail(borrowRecordParm);
                }

            }
        });

    }

    /**
     * 过滤重复的记录
     * @param list 记录列表
     * @return 记录集合
     */
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
     * @param tborrow 借车记录表
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
