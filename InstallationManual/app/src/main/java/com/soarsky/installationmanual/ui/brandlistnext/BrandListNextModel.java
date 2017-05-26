package com.soarsky.installationmanual.ui.brandlistnext;

import com.soarsky.installationmanual.base.BaseModel;
import com.soarsky.installationmanual.bean.AreaItemBean;
import com.soarsky.installationmanual.bean.BrandItemNextBean;
import com.soarsky.installationmanual.bean.ResponseDataBean;
import com.soarsky.installationmanual.data.remote.server1.Api;
import com.soarsky.installationmanual.util.RxSchedulers;

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
 * 该类为 品牌系列下的款式Model<br>
 */

public class BrandListNextModel implements BaseModel {
    /**
     * 获取品牌系列下的款式
     * @param code 传给后台的标识
     * @param flag 传给后台的标识
     * @return
     */
    public Observable<ResponseDataBean<List<BrandItemNextBean>>> getBrandListNext(final String code, final String flag){
        return Api.getInstance().service.getBrandListNext(code,flag).compose(RxSchedulers.<ResponseDataBean<List<BrandItemNextBean>>>io_main());
    }

}
