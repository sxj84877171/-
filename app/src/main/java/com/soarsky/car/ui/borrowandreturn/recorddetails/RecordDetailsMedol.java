package com.soarsky.car.ui.borrowandreturn.recorddetails;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;

import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/12
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：请求网络
 * 该类为  记录详情逻辑层
 */

public class RecordDetailsMedol implements BaseModel {

    /**
     * 记录详情
     * @param
     * @return
     */
    public Observable<DetailsParm> detail(Integer bId){
        return Api.getInstance().service.detail(bId).
                compose(RxSchedulers.<DetailsParm>io_main());
    }
}
