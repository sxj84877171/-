package com.soarsky.car.ui.drivrecord;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.LicensePwdBean;
import com.soarsky.car.bean.LicensePwdSendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：行驶记录model
 * 该类为 DrivRecordModel
 */

public class DrivRecordModel implements BaseModel{
    /**
     * 设置查询密码
     * @param param 设置查询密码发送参数
     * @return ResponseDataBean<LicensePwdBean> 返回结果参数
     */
    public Observable<ResponseDataBean<LicensePwdBean>> setQueryPwd(LicensePwdSendParam param){

        return api.setQueryPwd(param.getUsername(),param.getQueryPwd()).compose(RxSchedulers.<ResponseDataBean<LicensePwdBean>>io_main());
    }
}
