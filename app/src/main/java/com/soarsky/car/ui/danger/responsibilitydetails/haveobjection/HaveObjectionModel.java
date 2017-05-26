package com.soarsky.car.ui.danger.responsibilitydetails.haveobjection;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.FastDissentParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/23
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class HaveObjectionModel implements BaseModel{

    /**
     * 提交有异议接口
     */
    public Observable<ResponseDataBean<FastDissentParam>> fastDissent(FastDissentSendParam param){
        return api.fastDissent(param.getId(),param.getCarnum(),param.getReason(),param.getFaffirm(),param.getSaffirm()).compose(RxSchedulers.<ResponseDataBean<FastDissentParam>>io_main());
    }
}
