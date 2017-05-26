package com.soarsky.car.ui.roadside.list.detail;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideListOrderInfo;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：列表订单详情present<br>
 * 该类为 RoadSideListDetailPresent<br>
 */


public class RoadSideListDetailPresent extends BasePresenter<RoadSideListDetailModel,RoadSideListDetailView>{

    @Override
    public void onStart() {

    }

    /**
     * 获取故障救援详细信息
     * @param id
     */
    public void getRescueById(String id){
        mView.showProgess();
        mModel.getRescueById(id).subscribe(new Subscriber<ResponseDataBean<RoadSideListOrderInfo>>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.getRescueByIdFail();
            }

            @Override
            public void onNext(ResponseDataBean<RoadSideListOrderInfo> roadSideListOrderParam) {

                mView.stopProgess();
                mView.getRescueByIdSuccess(roadSideListOrderParam);
            }
        });
    }
}
