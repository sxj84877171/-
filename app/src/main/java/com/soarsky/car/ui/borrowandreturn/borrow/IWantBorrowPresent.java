package com.soarsky.car.ui.borrowandreturn.borrow;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.CheckBorrowCar;
import com.soarsky.car.bean.ResponseDataBean;

import rx.Subscriber;


/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/5<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  我要借车页面P调用逻辑层<br>
 */

public class IWantBorrowPresent extends BasePresenter<IWantBorrowMedol,IWantBorrowView> {


    @Override
    public void onStart() {
        mModel.setContext(context);
    }

    /**
     * 借车申请请求
     * @param carNum 借车车牌号
     * @param phoneNum 车主手机号码
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param borrowPhone 借车人手机号码
     * @return 借车申请
     */
    public void borrowCar(String carNum,String phoneNum,String startTime,String endTime,String borrowPhone){
       mView.showProgess();
       mModel.borrow(carNum,phoneNum,startTime,endTime,borrowPhone).subscribe(new Subscriber<ResponseDataBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError();

            }

            @Override
            public void onNext(ResponseDataBean borrowParm) {
                mView.stopProgess();
                if (SUCCESS_FLAG.equals(borrowParm.getStatus())){
                    mView.showSuccess(borrowParm);
                }else {
                    mView.showFail(borrowParm);

                }
            }
        });

    }

    /**
     * 校验车辆信息
     * @param carNum 车牌号码
     * @param phone 车主手机号码
     */
    public void check(String carNum,String phone){
        mView.showProgess();
        mModel.check(carNum,phone).subscribe(new Subscriber<ResponseDataBean<CheckBorrowCar>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError();

            }

            @Override
            public void onNext(ResponseDataBean<CheckBorrowCar> checkBorrowCar) {
                mView.stopProgess();
                if (checkBorrowCar.getStatus().equals(SUCCESS_FLAG)){
                    mView.checkSuccess(checkBorrowCar);
                }else {
                    mView.checkFail(checkBorrowCar);
                }
            }
        });
    }

    /**
     * 判断是否是亲情号
     * @param carNum
     * @return 返回boolean类型值 true表示是亲情号码
     */
    public boolean isFamilyNum(String carNum){
        return  mModel.isFamilyNum(carNum);
    }


}
