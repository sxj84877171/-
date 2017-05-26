package com.soarsky.car.ui.licenseinformation;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.DrivingLicenseInformationDataBean;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.ResponseDataBean;

import java.util.List;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/28<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  行驶证详情页面P层<br>
 */

public class DrivingLicenseInformationPresent extends BasePresenter<DrivingLicenseInformationModel,DrivingLicenseInformationView> {
    @Override
    public void onStart() {

    }

    /**
     * 获取车牌号
     * @param idNo 身份号
     */
    public void getDriverList(String idNo){

        mView.showProgess();

        mModel.getDriverList(idNo).subscribe(new Subscriber<ResponseDataBean<List<DrivingLicenseInformationDataBean>>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.getDriverListFail(e);

            }

            @Override
            public void onNext(ResponseDataBean<List<DrivingLicenseInformationDataBean>> carNumParam) {

                mView.stopProgess();
                if (carNumParam.getStatus().equals(SUCCESS_FLAG)){
                    mView.getDriverListSuccess(carNumParam);
                }else {}


            }
        });

    }

    /**
     * 获取车牌号
     * @param phone 电话号码
     */
    public void getAllCarnum(String phone){

        mView.showProgess();

        mModel.getAllCarnum(phone).subscribe(new Subscriber<ResponseDataBean<List<LicenseInfo>>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.getAllCarnumFail();

            }

            @Override
            public void onNext(ResponseDataBean<List<LicenseInfo>> carNumParam) {

                mView.stopProgess();
                mView.getAllCarnumSuccess(carNumParam);

            }
        });

    }

}
