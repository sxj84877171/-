package com.soarsky.car.ui.borrowandreturn.retur;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.ui.borrowandreturn.borrowrecord.BorrowRecordParm;

import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/7
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 我要还车页面逻辑层
 */

public class IWantReturnModel implements BaseModel {

    /**
     * 还车
     */
    public Observable<ReturnCarParm> returnCar(String bId,String carnum){
        return Api.getInstance().service.returnCar(bId,carnum).
                compose(RxSchedulers.<ReturnCarParm>io_main());
    }
    public Observable<BorrowRecordParm> record(String phone, String username){
        return Api.getInstance().service.borrowRecord(phone,username).
                compose(RxSchedulers.<BorrowRecordParm>io_main());
    }
}
