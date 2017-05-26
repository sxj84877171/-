package com.soarsky.car.ui.license;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.DriviLicenseParam;
import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.DriviLicenseSendParam;
import com.soarsky.car.bean.DrivingLicenseInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.uitl.SpUtil;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：我的驾驶证Present<br>
 * 该类为 DriviLicensePresent<br>
 */

public class DriviLicensePresent extends BasePresenter<DriviLicenseModel,DrivilicenseView>{

    private App app ;
    @Override
    public void onStart() {
        app = (App) context.getApplication();
        SpUtil.init(context);

        DriviLicenseParam param = new DriviLicenseParam();
        param.setDriviLicenseNum(SpUtil.get(Constants.DriviLicenseNum));
        param.setDrivDistance(SpUtil.get(Constants.DrivDistance));
        param.setClearDate(SpUtil.get(Constants.ClearDate));
        param.setDriviLicenseCent(SpUtil.get(Constants.DriviLicenseCent));
        param.setDriviLicenseDate(SpUtil.get(Constants.DriviLicenseDate));
        param.setDriviLicenseStatus(SpUtil.get(Constants.DriviLicenseStatus));
        param.setDriviType(SpUtil.get(Constants.DriviType));

        app.setParam(param);

        mView.initLicenseData(param);

        DriviLicenseSendParam p = new DriviLicenseSendParam();
        p.setIdNo(app.getCommonParam().getIdNo());
        p.setPhone(app.getCommonParam().getOwerPhone());
        p.setVerCode(Constants.VerCode);
        getElecDriver(p);
    }

    /**
     * 获取我的驾驶证
     * @param param
     */
    public void getElecDriver(DriviLicenseSendParam param){
        mView.showProgess();
        mModel.getElecDriver(param.getIdNo(),param.getPhone(),param.getVerCode()).subscribe(new Subscriber<ResponseDataBean<DrivingLicenseInfo>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.showError();
            }

            @Override
            public void onNext(ResponseDataBean<DrivingLicenseInfo> driviLicenseParam) {

                mView.stopProgess();

                if(driviLicenseParam != null){
                    if(Constants.REQUEST_SUCCESS.equals(driviLicenseParam.getStatus())){

                        SpUtil.save(Constants.DriviType,driviLicenseParam.getData().getDrivingType());
                        SpUtil.save(Constants.DriviLicenseStatus,driviLicenseParam.getData().getStatus());
                        SpUtil.save(Constants.DriviLicenseDate,driviLicenseParam.getData().getValidityPeriod());
                        SpUtil.save(Constants.DriviLicenseCent,driviLicenseParam.getData().getScore());
                        SpUtil.save(Constants.DrivDistance,driviLicenseParam.getData().getDistance());
                        SpUtil.save(Constants.DriviLicenseNum,driviLicenseParam.getData().getIdcard());
                        SpUtil.save(Constants.ClearDate,driviLicenseParam.getData().getClearDate());

                    }
                }

                mView.showSuccess(driviLicenseParam);
            }
        });
    }

}
