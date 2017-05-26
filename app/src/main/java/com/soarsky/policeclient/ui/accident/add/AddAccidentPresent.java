package com.soarsky.policeclient.ui.accident.add;

import android.util.Log;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.base.BasePresenter;
import com.soarsky.policeclient.bean.AccidentDataBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.map.design.OnPositioningResponse;
import com.soarsky.policeclient.ui.accident.serverbean.AccidentReasonDataBean;
import com.soarsky.policeclient.uitl.LogUtil;

import java.util.List;

import rx.Subscriber;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/19<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为新增事故的Present<br>
 */
public class AddAccidentPresent extends BasePresenter<AddAccidentModel,AddAccidentView> {

    /**
     * 开启定位
     */
    @Override
    public void onStart() {
        mModel.startPositioning(onPositioningResponse);
    }

    /**
     * 定位的回调
     */
    private OnPositioningResponse onPositioningResponse = new OnPositioningResponse() {
        @Override
        public void onSuccess(String s) {
            mView.getedPosition(s);
        }

        @Override
        public void onError(int errorCode) {
            LogUtil.e("weikai",errorCode+"");
        }
    };

    /*public void getAccidentList(String username){

        mModel.getAccidentList(username).subscribe(new Subscriber<AddAccidentBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.onError();
            }

            @Override
            public void onNext(AddAccidentBean accidentBean) {
                if (!accidentBean.getData().isEmpty()){
                    mView.showData(accidentBean.getData());
                }else {
                    mView.onNoData();
                }
            }
        });
    }*/
    public void sendGuzhang(String carNum,String stime,String remark){

    }

    /**
     * 获取事故列表，调用Model层
     */
    public void getReasonList(){
        AddAccidentModel addAccidentModel = new AddAccidentModel();
        addAccidentModel.getReasonList().subscribe(new Subscriber<ResponseDataBean<List<AccidentReasonDataBean>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("ss",e.getMessage());
            }

            @Override
            public void onNext(ResponseDataBean<List<AccidentReasonDataBean>> accidentReason) {
                if(accidentReason.getData()!=null){
                    List<AccidentReasonDataBean> dataBeens = accidentReason.getData();
                    for (AccidentReasonDataBean dataBean:dataBeens) {
                        com.soarsky.policeclient.data.local.db.bean.accident.AccidentReason reason = new com.soarsky.policeclient.data.local.db.bean.accident.AccidentReason();
                        reason.setId((long) dataBean.getId());
                        reason.setReason(dataBean.getReason());
                        App.getApp().getDaoSession().getAccidentReasonDao().insertOrReplace(reason);
                    }
                }
            }
        });
    }




    /*
        http://192.168.100.16:8080/gtw/app/uploadAccident?aanalysis=2&rid=1&stime=2016-12-21 10:00:04&location=麓谷大道&reason=违章&imageUrl=2222&remark=
     */
    public void sendShigu(AccidentDataBean.AccidentItemBean accidentItemBean){

    }

}
