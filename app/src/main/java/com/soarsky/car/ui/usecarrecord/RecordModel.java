package com.soarsky.car.ui.usecarrecord;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.FaultRecordDataBean;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.UseCarRecordParam;
import com.soarsky.car.helper.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2017/5/10
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class RecordModel implements BaseModel {
    /**
     * 获取故障列表
     */
    public Observable<ResponseDataBean<List<FaultRecordDataBean>>> getFault(String carnum){
        return api.getFault(carnum).compose(RxSchedulers.<ResponseDataBean<List<FaultRecordDataBean>>>io_main());
    }

    /**
     * 获取用车记录
     */
    public Observable<ResponseDataBean<List<UseCarRecordParam>>> getCarRecoredsList(String carnum){
        return api.getCarRecoredsList(carnum).compose(RxSchedulers.<ResponseDataBean<List<UseCarRecordParam>>>io_main());
    }
}
