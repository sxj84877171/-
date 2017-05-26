package com.soarsky.car.ui.roadside.rescue;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideCarTypeParam;
import com.soarsky.car.bean.RoadSideRescueInfo;

import java.util.List;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/19<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：申请救援present<br>
 * 该类为RoadSideRescuePresent<br>
 */


public class RoadSideRescuePresent extends BasePresenter<RoadSideRescueModel,RoadSideRescueView>{

    @Override
    public void onStart() {

    }
    /**
     * 跳转确认订单界面
     * @param roadSideRescueParam 参数
     */
    public void gotoRoadSideOrder(ResponseDataBean<RoadSideRescueInfo> roadSideRescueParam){
        mView.gotoRoadSideOrder(roadSideRescueParam);
    }
    /**
     * 车辆类型对话框
     * @param _list 集合
     */
    public void showCarTypeDialog(List<String> _list){

        mView.showCarTypeDialog(_list);
    }

    /**
     * 服务对话框
     * @param _list 集合
     */
    public void showSeverDialog(List<String> _list){

        mView.showSeverDialog(_list);
    }

    /**
     * 获取车辆类型
     */
    public void getCarType(){
        mView.showProgess();

        mModel.getCarType().subscribe(new Subscriber<ResponseDataBean<List<RoadSideCarTypeParam>>>() {
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
            public void onNext(ResponseDataBean<List<RoadSideCarTypeParam>> roadSideCarTypeParam) {
                mView.stopProgess();
                mView.getCarTypeSuccess(roadSideCarTypeParam);
            }
        });
    }

    /**
     * 上传救援申请
     * @param param 入参
     */
    public void uploadResouse(RoadSideRescueSendParam param){
        mView.showProgess();
        mModel.uploadResouse(param).subscribe(new Subscriber<ResponseDataBean<RoadSideRescueInfo>>() {
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
            public void onNext(ResponseDataBean<RoadSideRescueInfo> roadSideRescueParam) {
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
