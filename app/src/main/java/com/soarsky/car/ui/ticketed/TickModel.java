package com.soarsky.car.ui.ticketed;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.IlleagalParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：电子罚单<br>
 * 该类为 TickModel<br>
 */

public class TickModel implements BaseModel{

    /**
     * 上传电子罚单
     * @param param
     * @return
     */
    public Observable<ResponseDataBean<IlleagalParam>> appUploadIlleagal(IlleagalSendParam param){
        return api.appUploadIlleagal(param.getCarnum(),param.getIdNo(),param.getStime(),param.getEtime(),
                param.getLon(),param.getLat(),param.getInf(),param.getdType()).compose(RxSchedulers.<ResponseDataBean<IlleagalParam>>io_main());
    }

}
