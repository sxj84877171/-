package com.soarsky.policeclient.ui.record.fragment;

import com.soarsky.policeclient.data.local.db.bean.Check;
import com.soarsky.policeclient.ui.record.RecordModel;

import java.util.List;

import rx.Subscriber;


/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/17<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  历史记录--稽查车辆p层<br>
 */

public class CheckCarFragmentPresent {
    /**
     * 历史记录model
     */
    private RecordModel model;
    /**
     * 历史记录View
     */
    private ICheckCarFragmentView view;

    public void setModel(RecordModel model) {
        this.model = model;
    }

    public void setView(ICheckCarFragmentView view) {
        this.view = view;
    }


    /**
     * 从服务器获取历史记录
     * @param carNum
     * @param pageNum
     */
    public void getRecordViolationParam(String carNum, int pageNum) {
        /*model.getRecordAuditParam(carNum, pageNum).subscribe(new Subscriber<RecordAuditParam>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(RecordAuditParam recordAuditParam) {
                if (recordAuditParam.getData() != null) {
                    view.showData(recordAuditParam.getData());
                }
            }
        });*/
        model.getRecordCheckCarFromDb(pageNum).subscribe(new Subscriber<List<Check>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Check> checks) {
                if (checks != null) {
                    view.showData(checks);
                }
            }
        });
    }
}
