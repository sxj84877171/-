package com.soarsky.policeclient.ui.accident.itemcar;

import com.soarsky.policeclient.base.BaseModel;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.remote.server1.ApiM;
import com.soarsky.policeclient.helper.RxSchedulers;
import com.soarsky.policeclient.ui.accident.serverbean.WeizhangBean;

import rx.Observable;

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
 * 该类为事故分析车辆详情Model<br>
 */
public class AccidentCarDetailModel implements BaseModel {

    /**
     * 根据车牌号从服务器查询该车辆的违章信息
     * @param ssid
     * @return
     */
    public Observable<ResponseDataBean<WeizhangBean>> WeizhangQueryFromServer(String ssid){
        return ApiM.getInstance().service.WeizhangQuery(ssid).compose(RxSchedulers.<ResponseDataBean<WeizhangBean>>io_main());
    }
}
