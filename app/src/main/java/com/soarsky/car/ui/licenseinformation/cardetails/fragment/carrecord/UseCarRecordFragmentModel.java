package com.soarsky.car.ui.licenseinformation.cardetails.fragment.carrecord;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.UseCarRecordParam;
import com.soarsky.car.helper.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：用车记录请求数据<br>
 * 该类为 用车记录model<br>
 */


public class UseCarRecordFragmentModel implements BaseModel{

    /**
     * 获取用车记录
     */
    public Observable<ResponseDataBean<List<UseCarRecordParam>>> getCarRecoredsList(String carnum){
        return api.getCarRecoredsList(carnum).compose(RxSchedulers.<ResponseDataBean<List<UseCarRecordParam>>>io_main());
    }

}
