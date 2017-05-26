package com.soarsky.policeclient.ui.record;

import android.util.Log;

import com.soarsky.policeclient.base.BasePresenter;
import com.soarsky.policeclient.bean.RecordViolationDataBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.uitl.LogUtil;

import java.util.List;

import rx.Subscriber;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  历史记录Present<br>
 */

public class RecordPresent extends BasePresenter<RecordModel,RecordView>{

    @Override
    public void onStart() {

    }
    public void showRecord(RecordSendParam param){

        LogUtil.d("TAG","showRecord ===");

        mModel.getRecordViolationParam(param.getCarnum(),param.getPage(),param.getPlace(),param.getStime(),param.getEtime()).subscribe(new Subscriber<ResponseDataBean<List<RecordViolationDataBean>>>() {
            @Override
            public void onCompleted() {
                LogUtil.d("TAG","onCompleted ===");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.d("TAG","onError ===");
            }

            @Override
            public void onNext(ResponseDataBean<List<RecordViolationDataBean>> recordViolationParam) {
                LogUtil.d("TAG","onNext ===");
                mView.showSuccess(recordViolationParam.getData());
            }
        });

    }
}
