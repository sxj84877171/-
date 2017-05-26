package com.soarsky.policeclient.ui.accident.add;

import com.soarsky.policeclient.base.BaseModel;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.map.baidu.BaiduPositioning;
import com.soarsky.policeclient.data.map.design.OnPositioningResponse;
import com.soarsky.policeclient.data.map.design.Positioning;
import com.soarsky.policeclient.data.remote.server1.ApiM;
import com.soarsky.policeclient.ui.accident.serverbean.AccidentReasonDataBean;

import java.util.List;

import rx.Observable;
import rx.schedulers.Schedulers;

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
 * 该类为新增事故Model<br>
 */
public class AddAccidentModel implements BaseModel {

    /*public Observable<AddAccidentBean> getAccidentList(String username){
        return ApiM.getInstance().service.getAccidentList(username).compose(RxSchedulers.<AddAccidentBean>io_main());
    }*/

    /**
     * 开启定位功能
     * @param onPositioningResponse 定位回调接口
     */
    public void startPositioning(OnPositioningResponse onPositioningResponse) {
        Positioning positioning = new BaiduPositioning();
        positioning.startPositioning(onPositioningResponse);
    }

    /**
     * 从数据库获取事故原因列表
     * @return
     */
    public Observable<ResponseDataBean<List<AccidentReasonDataBean>>> getReasonList(){
        return ApiM.getInstance().service.getReasonList().subscribeOn(Schedulers.io());
    }


    //@Query("aanalysis") String aanalysis ,@Query("rid") int rid,@Query("stime") String stime,@Query("location") String location,@Query("reason") String reason
    /*public Observable<AccidentDataBean.AccidentItemBean> sendShigu(AccidentDataBean.AccidentItemBean guzhangItem){
        return ApiM.getInstance().service.sendShigu(guzhangItem.).compose(RxSchedulers.<AccidentReason>io_main());
    }*/

}
