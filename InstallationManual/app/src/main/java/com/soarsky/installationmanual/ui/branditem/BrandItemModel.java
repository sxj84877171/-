package com.soarsky.installationmanual.ui.branditem;

import com.soarsky.installationmanual.base.BaseModel;
import com.soarsky.installationmanual.bean.AreaItemBean;
import com.soarsky.installationmanual.bean.BrandBean;
import com.soarsky.installationmanual.bean.BrandItemBean;
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
 * 该类为 品牌下的子系列Model<br>
 */

public class BrandItemModel implements BaseModel {
    /**
     * 根据品牌获取对应的子系列列表
     * @param brand 品牌
     * @return
     */
    public Observable<ResponseDataBean<List<BrandItemBean>>> getBrandItem(String brand){
        return Api.getInstance().service.getBrandItem(brand).compose(RxSchedulers.<ResponseDataBean<List<BrandItemBean>>>io_main());
    }

}
