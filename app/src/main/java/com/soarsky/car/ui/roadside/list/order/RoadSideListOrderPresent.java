package com.soarsky.car.ui.roadside.list.order;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideListOrderInfo;

import rx.Subscriber;

import static com.soarsky.car.Constants.REQUEST_FAIL;
import static com.soarsky.car.Constants.REQUEST_SUCCESS;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：救援申请列表Present
 * 该类为 RoadSideListOrderPresent
 */


public class RoadSideListOrderPresent extends BasePresenter<RoadSideListOrderModel,RoadSideListOrderView>{

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
                mView.showError(e);
            }

            @Override
            public void onNext(ResponseDataBean<RoadSideListOrderInfo> roadSideListOrderParam) {

                mView.stopProgess();
                if (REQUEST_SUCCESS.equals(roadSideListOrderParam.getStatus())){
                    mView.getRescueByIdSuccess(roadSideListOrderParam);
                }else if (REQUEST_FAIL.equals(roadSideListOrderParam.getStatus())){
                    mView.getRescueByIdFail();
                }

            }
        });
    }

    /**
     * 根据id删除记录
     * @param id
     */
    public void delRescueById(String id){
        mView.showProgess();
        mModel.delRescueById(id).subscribe(new Subscriber<ResponseDataBean<Object>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.delRescueByIdFail();
            }

            @Override
            public void onNext(ResponseDataBean<Object> roadSideListOrderDelRescueParam) {

                mView.stopProgess();
                mView.delRescueByIdSuccess(roadSideListOrderDelRescueParam);
            }
        });
    }

    /**
     * 取消订单对话框
     */
    public void showOrderDelete(){
        mView.showOrderDelete();
    }

}
