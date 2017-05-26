package com.soarsky.car.ui.borrowandreturn.borrowaplication.sucess;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.DetailsInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/12/14<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  借车方借车成功页面逻辑层<br>
 */


public class BorrowSuccessModel implements BaseModel {

    /**
     * 记录详情
     * @param bId  记录id
     * @return  详情信息
     */
    public Observable<ResponseDataBean<DetailsInfo>> detail(Integer bId){
        return api.detail(bId).
                compose(RxSchedulers.<ResponseDataBean<DetailsInfo>>io_main());
    }

}
