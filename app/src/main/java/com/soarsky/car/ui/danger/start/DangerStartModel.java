package com.soarsky.car.ui.danger.start;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

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
 * 程序功能：获取网络数据
 * 该类为
 */


public class DangerStartModel implements BaseModel {

    /**
     * 获取快赔未读记录数
     */
    public Observable<ResponseDataBean<Integer>> getUnreadFastCount(String carnum){
        return api.getUnreadFastCount(carnum).compose(RxSchedulers.<ResponseDataBean<Integer>>io_main());
    }

}
