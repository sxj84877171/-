package com.soarsky.policeclient.ui.accident.listitem;

import com.soarsky.policeclient.base.BaseModel;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.remote.server1.ApiM;
import com.soarsky.policeclient.helper.RxSchedulers;
import com.soarsky.policeclient.ui.accident.serverbean.AccidentItem;

import rx.Observable;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为事故分析每一个事故详情页面Model<br>
 */
public class AccidentListItemModel implements BaseModel {

    /**
     * 根据id从服务器获取该事故数据
     * @param id 从事故列表界面获取的每个事故的id
     * @return
     */
    public Observable<ResponseDataBean<AccidentItem>> getAccidentListItem(String id){
        return ApiM.getInstance().service.getAccidentListItem(id).compose(RxSchedulers.<ResponseDataBean<AccidentItem>>io_main());
    }


}
