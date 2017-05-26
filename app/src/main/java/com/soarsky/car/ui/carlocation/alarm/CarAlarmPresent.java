package com.soarsky.car.ui.carlocation.alarm;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.CarAlarmDataBean;
import com.soarsky.car.bean.CarAlarmSendParam;
import com.soarsky.car.bean.ResponseDataBean;

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
 * 程序功能：防盗报警present <br>
 * 该类为 CarAlarmPresent <br>
 */

public class CarAlarmPresent extends BasePresenter<CarAlarmModel,CarAlarmView>{

    @Override
    public void onStart() {

    }

    /**
     * 获取防盗报警历史记录
     * @param param 防盗报警发送的参数
     */
    public void getTheftRecords(CarAlarmSendParam param){

        mView.showProgess();
        mModel.getTheftRecords(param).subscribe(new Subscriber<ResponseDataBean<List<CarAlarmDataBean>>>() {
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
            public void onNext(ResponseDataBean<List<CarAlarmDataBean>> carAlarmParam) {

                mView.stopProgess();
                mView.showSuccess(carAlarmParam);
            }
        });

    }
}
