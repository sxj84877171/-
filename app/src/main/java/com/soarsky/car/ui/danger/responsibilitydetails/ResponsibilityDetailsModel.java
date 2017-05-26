package com.soarsky.car.ui.danger.responsibilitydetails;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.ui.danger.responsibilitydetails.haveobjection.FastDissentParam;

import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class ResponsibilityDetailsModel implements BaseModel {

    /**
     * 获取单条快赔详细信息
     * @param id
     * @return
     */
    public Observable<ResponsibilityDetailsParam> getFastInfo(String id){
        return Api.getInstance().service.getFastInfo(id).compose(RxSchedulers.<ResponsibilityDetailsParam>io_main());
    }



}
