package com.soarsky.car.ui.blindterm.validation;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.BlindTermValidSendParam;
import com.soarsky.car.bean.LicensePwdBean;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

import rx.Observable;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：验证model <br>
 * 该类为 :设置查询密码 <br>
 */


public class BlindTermValidationModel implements BaseModel{

    /**
     * 设置查询密码
     * @param param 参数
     * @return ResponseDataBean<LicensePwdBean>
     */
    public Observable<ResponseDataBean<LicensePwdBean>> setQueryPwd(BlindTermValidSendParam param){
        return api.setQueryPwd(param.getUsername(),param.getQueryPwd()).compose(RxSchedulers.<ResponseDataBean<LicensePwdBean>>io_main());
    }

}
