package com.soarsky.car.ui.carlocation.alarm;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.CarAlarmDataBean;
import com.soarsky.car.bean.CarAlarmSendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com  <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：防盗报警model <br>
 * 该类为 CarAlarmModel <br>
 */

public class CarAlarmModel implements BaseModel{

    /**
     * 获取防盗报警历史记录
     * @param param 防盗报警发送的参数
     * @return ResponseDataBean<List<CarAlarmDataBean>> 防盗报警历史记录
     */
    public Observable<ResponseDataBean<List<CarAlarmDataBean>>> getTheftRecords(CarAlarmSendParam param){
        return api.getTheftRecords(param.getCarnum()).compose(RxSchedulers.<ResponseDataBean<List<CarAlarmDataBean>>>io_main());
    }
}
