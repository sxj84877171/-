package com.soarsky.installationmanual.ui.completeuserinfo.location;

import android.app.Activity;

import com.soarsky.installationmanual.App;
import com.soarsky.installationmanual.base.BaseModel;
import com.soarsky.installationmanual.bean.AreaItemBean;
import com.soarsky.installationmanual.bean.LoginInfo;
import com.soarsky.installationmanual.bean.ResponseDataBean;
import com.soarsky.installationmanual.data.remote.server1.Api;
import com.soarsky.installationmanual.util.RxSchedulers;
import com.soarsky.installationmanual.util.SpUtil;

import java.util.List;

import rx.Observable;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 选择地区Model<br>
 */

public class AreaModel implements BaseModel {
    /**
     * 获取所有省份信息
     * @param code 发送给后台的标识码
     * @param flag 发送给后台的标识码
     */
    public Observable<ResponseDataBean<List<AreaItemBean>>> getAera(final String code,final String flag){
        return Api.getInstance().service.getAera(code,flag).compose(RxSchedulers.<ResponseDataBean<List<AreaItemBean>>>io_main());
    }

}
