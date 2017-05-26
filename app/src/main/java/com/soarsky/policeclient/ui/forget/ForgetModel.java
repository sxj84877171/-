package com.soarsky.policeclient.ui.forget;

import com.soarsky.policeclient.base.BaseModel;
import com.soarsky.policeclient.bean.ForgetPwdDataBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.remote.server1.ApiM;
import com.soarsky.policeclient.helper.RxSchedulers;

import rx.Observable;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/17<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  已废弃 未使用<br>
 */

public class ForgetModel implements BaseModel {

    public Observable<ResponseDataBean<ForgetPwdDataBean>> forgetPwd(String username, String idNo, String phone){

        return ApiM.getInstance().service.forgetPassword(username, idNo, phone).compose(RxSchedulers.<ResponseDataBean<ForgetPwdDataBean>>io_main());
    }

}
