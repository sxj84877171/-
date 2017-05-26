package com.soarsky.car.ui.carchange;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.DrivingLicenseInformationDataBean;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.TerminalInfoParam;

import java.util.List;

import rx.Subscriber;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：切换车辆的present <br>
 * 该类为 CarChangePresent <br>
 */


public class CarChangePresent extends BasePresenter<CarChangeModel,CarChangeView>{

    @Override
    public void onStart() {

    }

    /**
     * 获取车牌号
     * @param phone 车主号码
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

    /**
     * 根据车牌号获取终端信息
     * @param carnum 车牌号
     */
    public void getTerminalInfo(String carnum){

        mView.showProgess();
        mModel.getTerminalInfo(carnum).subscribe(new Subscriber<ResponseDataBean<TerminalInfoParam>>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.getTerminalInfoFail();
            }

            @Override
            public void onNext(ResponseDataBean<TerminalInfoParam> terminalInfoParam) {
                mView.stopProgess();
                mView.getTerminalInfoSuccess(terminalInfoParam);
            }
        });
    }

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
            }

            @Override
            public void onNext(ResponseDataBean<List<DrivingLicenseInformationDataBean>> listResponseDataBean) {
                mView.stopProgess();

            }
        });
    }


}
