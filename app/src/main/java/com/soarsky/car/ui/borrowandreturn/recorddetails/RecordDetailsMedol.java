package com.soarsky.car.ui.borrowandreturn.recorddetails;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.DetailsInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/12<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：请求网络<br>
 * 该类为  记录详情逻辑层<br>
 */

public class RecordDetailsMedol implements BaseModel {

    /**
     * 记录详情
     * @param bId 记录id
     * @return  借车记录
     */
    public Observable<ResponseDataBean<DetailsInfo>> detail(Integer bId){
        return api.detail(bId).
                compose(RxSchedulers.<ResponseDataBean<DetailsInfo>>io_main());
    }
}
