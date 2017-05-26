package com.soarsky.policeclient.ui.details;

import com.soarsky.policeclient.Constants;
import com.soarsky.policeclient.base.BasePresenter;
import com.soarsky.policeclient.bean.CarDetailsDataBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.ui.modify.ModifyMode;

import rx.Subscriber;

/**
 * andriod_police_app<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 车辆详情Present<br>
 */

public class DetailPresent extends BasePresenter<DetailModel,DetailView> {
    private ModifyMode model;
    private String carNum;

    public void setModel(ModifyMode model) {
        this.model = model;
    }
    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    @Override
    public void onStart()   {
    }

    /**
     * 调用后台逻辑获取车辆详情
     * @param carNum
     */
    public void getCarDetail(String  carNum){
        mModel.getCarDetails(carNum).subscribe(new Subscriber<ResponseDataBean<CarDetailsDataBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseDataBean<CarDetailsDataBean> detailsCarParam) {

                if (Constants.STATUS.equals(detailsCarParam.getStatus()) ){
                    mView.showData(detailsCarParam.getData());
                }else {
                    mView.showFail(detailsCarParam.getMessage());
                }

            }
        });


    }



}
