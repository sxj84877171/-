package com.soarsky.car.ui.licenseinformation.cardetails.fragment.faultrecord;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.FaultRecordDataBean;
import com.soarsky.car.bean.ResponseDataBean;

import java.util.List;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：故障记录获取的数据<br>
 * 该类为 故障记录present<br>
 */


public class FaultRecordFragmentPresent extends BasePresenter<FaultRecordFragmentModel,FaultRecordFragmentView>{

    @Override
    public void onStart() {

    }

    /**
     * 获取故障列表
     * @param carnum
     */
    public void getFault(String carnum){

        mView.showProgess();
        mModel.getFault(carnum).subscribe(new Subscriber<ResponseDataBean<List<FaultRecordDataBean>>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.getFaultFail();
            }

            @Override
            public void onNext(ResponseDataBean<List<FaultRecordDataBean>> param) {
                mView.stopProgess();
                mView.getFaultSuccess(param);
            }
        });

    }
}
