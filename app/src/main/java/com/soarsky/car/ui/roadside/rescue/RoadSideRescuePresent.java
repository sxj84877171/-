package com.soarsky.car.ui.roadside.rescue;

import com.soarsky.car.base.BasePresenter;

import java.util.List;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/19
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class RoadSideRescuePresent extends BasePresenter<RoadSideRescueModel,RoadSideRescueView>{

    @Override
    public void onStart() {

    }
    /**
     * 跳转确认订单界面
     */
    public void gotoRoadSideOrder(RoadSideRescueParam roadSideRescueParam){
        mView.gotoRoadSideOrder(roadSideRescueParam);
    }
    /**
     * 车辆类型对话框
     */
    public void showCarTypeDialog(List<String> _list){

        mView.showCarTypeDialog(_list);
    }

    /**
     * 服务对话框
     */
    public void showSeverDialog(List<String> _list){

        mView.showSeverDialog(_list);
    }

    /**
     * 获取车辆类型
     */
    public void getCarType(){
        mView.showProgess();

        mModel.getCarType().subscribe(new Subscriber<RoadSideCarTypeParam>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.getCarTypeFail();
            }

            @Override
            public void onNext(RoadSideCarTypeParam roadSideCarTypeParam) {
                mView.stopProgess();
                mView.getCarTypeSuccess(roadSideCarTypeParam);
            }
        });
    }

    /**
     * 上传救援申请
     */
    public void uploadResouse(RoadSideRescueSendParam param){
        mView.showProgess();
        mModel.uploadResouse(param).subscribe(new Subscriber<RoadSideRescueParam>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.uploadResouseFail();
            }

            @Override
            public void onNext(RoadSideRescueParam roadSideRescueParam) {
                mView.stopProgess();
                mView.uploadResouseSuccess(roadSideRescueParam);

            }
        });
    }

    /**
     * 验证数据是否为空
     */
    public boolean verifyData(){
        return mView.verifyData();
    }

}
