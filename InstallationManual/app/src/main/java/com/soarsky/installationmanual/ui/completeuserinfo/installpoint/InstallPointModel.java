package com.soarsky.installationmanual.ui.completeuserinfo.installpoint;

import com.soarsky.installationmanual.base.BaseModel;
import com.soarsky.installationmanual.bean.InPoBean;
import com.soarsky.installationmanual.bean.ResponseDataBean;
import com.soarsky.installationmanual.bean.VeAdBean;
import com.soarsky.installationmanual.data.remote.server1.Api;
import com.soarsky.installationmanual.util.RxSchedulers;

import java.util.List;

import rx.Observable;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 选择安装点Model<br>
 */

public class InstallPointModel implements BaseModel {
    /**
     * 获取安装点
     * @param code 城市code
     * @return
     */
    public Observable<ResponseDataBean<List<InPoBean>>> getInstallPoint(final String code){
        return Api.getInstance().service.getInstallPoint(code).compose(RxSchedulers.<ResponseDataBean<List<InPoBean>>>io_main());
    }

}
