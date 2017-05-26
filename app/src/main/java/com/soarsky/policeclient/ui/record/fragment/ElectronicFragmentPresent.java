package com.soarsky.policeclient.ui.record.fragment;

import com.soarsky.policeclient.data.local.db.bean.Violation;
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
 * 该类为  历史记录--电子罚单P层<br>
 */

public class ElectronicFragmentPresent {
    /**
     * 参考CheckCarFragmentPresent
     */
    private IElectronicFragmentView view;
    /**
     * 参考CheckCarFragmentPresent
     */
    private RecordModel model;
    /**
     * 参考CheckCarFragmentPresent
     */
    private int index = 1;

    public void setModel(RecordModel model) {
        this.model = model;
    }

    public void setView(IElectronicFragmentView view) {
        this.view = view;
    }

    /**
     * 从数据库获取历史记录
     */
    public void init() {
        //TODO 参数需要维护
        //getRecordViolationParam( index + ":10");


        model.getRecordViolationParamFromDb().subscribe(new Subscriber<List<Violation>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Violation> violations) {
                if (violations != null) {

                    view.showData(violations);
                }


            }
        });

    }

}




    /*public void add(){
        addRecordViolationParam(++index + ":10");
    }*/

    /*public void getRecordViolationParam(String page) {
        model.getRecordViolationParam("", page, "", "", "").subscribe(new Subscriber<RecordViolationParam>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("error","ss");
            }

            @Override
            public void onNext(RecordViolationParam recordViolationParam) {
                if (recordViolationParam.getData() != null) {
                    view.showData(recordViolationParam.getData());
                }
            }
        });

    }*/

    /*public void addRecordViolationParam(String page) {
        model.getRecordViolationParam("", page, "", "", "").subscribe(new Subscriber<RecordViolationParam>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(RecordViolationParam recordViolationParam) {
                if (recordViolationParam != null) {
                    view.addData(recordViolationParam.getData());
                }
            }
        });

    }*/

