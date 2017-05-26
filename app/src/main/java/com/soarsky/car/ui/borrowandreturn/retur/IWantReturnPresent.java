package com.soarsky.car.ui.borrowandreturn.retur;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ReturnCarInfo;
import com.soarsky.car.bean.BorrowRecords;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/7<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 我要还车页面P层<br>
 */

public class IWantReturnPresent extends BasePresenter<IWantReturnModel,IWantReturnView> {
    /**
     * 自己电话
     */
    private String owerNum ;

    public void setOwerNum(String owerNum) {
        this.owerNum = owerNum;
    }

    /**
     * 记录状态
     */
    public static final String BORROWED_FLAG = "1" ;

    @Override
    public void onStart() {

    }

    /**
     * 归还车辆
     * @param bId 借车车辆的id
     * @param carnum 车牌号
     */
    public void returnCar(String bId,String carnum){
        mView.showProgess();
        mModel.returnCar(bId,carnum).subscribe(new Subscriber<ResponseDataBean<ReturnCarInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError(e);
            }

            @Override
            public void onNext(ResponseDataBean<ReturnCarInfo> returnCarParm) {
                mView.stopProgess();
                if (returnCarParm.getStatus().equals(SUCCESS_FLAG)){
                    mView.returnSuccess(returnCarParm);
                }else {
                    mView.returnFail(returnCarParm);
                }
            }
        });
    }

    /**
     * 获取借车与被借车记录
     * @param phone  电话号码
     * @param username 用户名
     */
    public void record(String phone, String username){
        mView.showProgess();
        mModel.record(phone,username).subscribe(new Subscriber<ResponseDataBean<List<BorrowRecords>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError(e);
            }

            @Override
            public void onNext(ResponseDataBean<List<BorrowRecords>> borrowRecordParm) {
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
