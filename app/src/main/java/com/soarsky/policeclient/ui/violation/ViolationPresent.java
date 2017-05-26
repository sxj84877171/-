package com.soarsky.policeclient.ui.violation;

import android.util.Log;

import com.soarsky.policeclient.base.BasePresenter;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.data.local.db.bean.Violation;
import com.soarsky.policeclient.data.map.design.OnPositioningResponse;
import com.soarsky.policeclient.uitl.LogUtil;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 开电子罚单的Present<br>
 */

public class ViolationPresent extends BasePresenter<ViolationMode,ViolationView> {

    /**
     * 车辆定位响应回调
     */
    private OnPositioningResponse onPositioningResponse = new OnPositioningResponse() {
        @Override
        public void onSuccess(String s) {
            mView.getedPosition(s);
        }

        @Override
        public void onError(int errorCode) {

        }
    };

    @Override
    public void onStart() {
        mModel.setContext(context);
        mModel.startPositioning(onPositioningResponse);
    }



//    public void  upLoadFile(String filePath){
//
//        mModel.upLoadImg(filePath).subscribe(new Subscriber<UploadFile>() {
//            @Override
//            public void onCompleted() {
//                System.out.print("onCompleted()=====================");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.print("onError=====================");
//            }
//
//            @Override
//            public void onNext(UploadFile uploadFile) {
//                System.out.println(uploadFile.toJson());
//            }
//
//
//        });
//    }


    /**
     * 插入电子罚单数据
     * @param violation
     */
    public void  insertViolation(Violation violation){
        mView.showProgess();
        mRxManager.add(mModel.insertViolation(violation).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Violation>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                mView.showFail();
                e.printStackTrace();
            }

            @Override
            public void onNext(Violation violation) {
                mView.stopProgess();
                mView.showSuccess();
                LogUtil.d("TAG","==============================="+violation.toJson());
                
            }
        }));

    }


    public  void  getLocalViolation(){
        mModel.getListViolation().subscribe(new Subscriber<List<Violation>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<Violation> violations) {
                LogUtil.d("TAG","==============================="+violations.size());

            }
        });


    }
    public void showListen() {
        List<Car> list = mModel.getScanedCarList();
        if (list != null) {
            mView.initCarList(list);
        }
    }

    public void initLocation() {

    }
}
