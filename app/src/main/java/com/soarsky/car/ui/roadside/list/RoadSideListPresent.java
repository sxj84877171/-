package com.soarsky.car.ui.roadside.list;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideListDataBean;

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
 * 程序功能：救援申请列表present<br>
 * 该类为 RoadSideListPresent<br>
 */


public class RoadSideListPresent extends BasePresenter<RoadSideListModel,RoadSideListView>{

    @Override
    public void onStart() {

    }

    /**
     * 获取故障救援列表
     * @param carnum 车牌
     */
    public void getRescueList(String carnum){
        mView.showProgess();
        mModel.getRescueList(carnum).subscribe(new Subscriber<ResponseDataBean<List<RoadSideListDataBean>>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.getRescueListFail();

            }

            @Override
            public void onNext(ResponseDataBean<List<RoadSideListDataBean>> roadSideListParam) {
                mView.stopProgess();
                mView.getRescueListSuccess(roadSideListParam);
            }
        });
    }


}
