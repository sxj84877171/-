package com.soarsky.car.ui.borrowandreturn.retur;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.ui.borrowandreturn.borrowrecord.BorrowRecordParm;
import com.soarsky.car.ui.borrowandreturn.borrowrecord.BorrowRecords;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/7
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 我要还车页面P层
 */

public class IWantReturnPresent extends BasePresenter<IWantReturnModel,IWantReturnView> {

    private String owerNum ;

    public void setOwerNum(String owerNum) {
        this.owerNum = owerNum;
    }

    public static final String BORROWED_FLAG = "1" ;

    @Override
    public void onStart() {

    }
    public void returnCar(String bId,String carnum){
        mView.showProgess();
        mModel.returnCar(bId,carnum).subscribe(new Subscriber<ReturnCarParm>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError(e);
            }

            @Override
            public void onNext(ReturnCarParm returnCarParm) {
                mView.stopProgess();
                if (returnCarParm.getStatus().equals("0")){
                    mView.returnSuccess(returnCarParm);
                }else {
                    mView.returnFail(returnCarParm);
                }
            }
        });
    }


    public void record(String phone, String username){
        mView.showProgess();
        mModel.record(phone,username).subscribe(new Subscriber<BorrowRecordParm>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError(e);
            }

            @Override
            public void onNext(BorrowRecordParm borrowRecordParm) {
                mView.stopProgess();
                if (SUCCESS_FLAG.equals(borrowRecordParm.getStatus())){
                    List<BorrowRecords> ret = fillBorrowedCar(borrowRecordParm.getData());
                    mView.showSuccess(ret);
                }else {
                    mView.showFail(borrowRecordParm);
                }
            }
        });
    }

    /**
     * 从借车记录中获取借车人电话和自己电话相同的 并且记录状态为申请通过的记录信息
     * @param list 借车所有记录
     * @return  机车为可归还状态
     */
    public List<BorrowRecords> fillBorrowedCar(List<BorrowRecords> list){
        List<BorrowRecords> ret = new ArrayList<>();

        if(list != null){
            for(BorrowRecords borrowRecords: list){
                if(BORROWED_FLAG.equals(borrowRecords.getStatus()) && owerNum.equals(borrowRecords.getBorrow())){
                    ret.add(borrowRecords);
                }
            }
        }

        return  ret;
    }
}
