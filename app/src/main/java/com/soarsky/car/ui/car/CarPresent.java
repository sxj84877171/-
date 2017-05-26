package com.soarsky.car.ui.car;

import com.soarsky.car.App;
import com.soarsky.car.CarInfoParam;
import com.soarsky.car.Constants;
import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.CarInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.uitl.SpUtil;

import retrofit2.http.PATCH;
import rx.Subscriber;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/8 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：机动车present <br>
 * 该类为 CarPresent <br>
 */

public class CarPresent extends BasePresenter<CarModel,CarView>{

    private App app ;
    @Override
    public void onStart() {
        app = (App) context.getApplication();
        SpUtil.init(context);

        CarInfoParam param = new CarInfoParam();
        param.setCarNum(SpUtil.get(Constants.CarInfoNum));
        param.setCarColor(SpUtil.get(Constants.CarInfoColor));
        param.setCarDate(SpUtil.get(Constants.CarInfoDate));
        param.setCarDriviStatus(SpUtil.get(Constants.CarInfoDriviStatus));
        param.setCarEngineNo(SpUtil.get(Constants.CarInfoEngineNo));
        param.setCarIdenty(SpUtil.get(Constants.CarInfoIdenty));
        param.setCarType(SpUtil.get(Constants.CarInfoType));
        param.setOwerName(SpUtil.get(Constants.OwerName));
        param.setTelePhone(SpUtil.get(Constants.TelePhone));
        app.setCarInfoParam(param);

        mView.initCarData(param);

        CarSendParam p = new CarSendParam();
        p.setCarnum(app.getCommonParam().getCarNum());
        getCarInfo(p);

    }

    /**
     * 机动车
     * @param param 参数
     */
    public void getCarInfo(CarSendParam param){

        mView.showProgess();
        mRxManager.add(mModel.getCarInfo(param).subscribe(new Subscriber<ResponseDataBean<CarInfo>>() {
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
            public void onNext(ResponseDataBean<CarInfo> param) {

                mView.stopProgess();
                if(param != null){

                    SpUtil.save(Constants.CarInfoNum,param.getData().getPlateno());
                    SpUtil.save(Constants.CarInfoType,param.getData().getVehicletype());
                    SpUtil.save(Constants.CarInfoIdenty,param.getData().getVin());
                    SpUtil.save(Constants.CarInfoDriviStatus,param.getData().getStatus());
                    SpUtil.save(Constants.CarInfoEngineNo,param.getData().getEnginno());
                    SpUtil.save(Constants.CarInfoDate,param.getData().getIssuedate());
                    SpUtil.save(Constants.CarInfoColor,param.getData().getColor());
                    SpUtil.save(Constants.OwerName,param.getData().getName());
                    SpUtil.save(Constants.TelePhone,param.getData().getPhone());
                }

                mView.showSuccess(param);
            }
        }));
    }

}
