package com.soarsky.policeclient.ui.accident.listandadd;

import com.soarsky.policeclient.base.BaseModel;
import com.soarsky.policeclient.bean.AccidentBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.remote.server1.ApiM;
import com.soarsky.policeclient.helper.RxSchedulers;

import java.util.List;

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
 * 该类为事故分析事故列表Model<br>
 */
public class ListAndAddModel implements BaseModel {

    /**
     * 从服务器获取事故列表
     * @return
     */
    public Observable<ResponseDataBean<List<AccidentBean>>> getAccidentList(){
        return ApiM.getInstance().service.getAccidentList().compose(RxSchedulers.<ResponseDataBean<List<AccidentBean>>>io_main());
    }
}
