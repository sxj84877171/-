package com.soarsky.car.ui.modifypwd;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.ModifyPwdInfo;
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
 * 程序功能：修改密码model<br>
 * 该类为 ModifyPwdModel<br>
 */

public class ModifyPwdModel implements BaseModel{

    /**
     * 修改密码请求
     * @param username 用户名
     * @param oldpwd 以前密码
     * @param newpwd 新密码
     * @return <ResponseDataBean<ModifyPwdInfo>> 结果
     */
    public Observable<ResponseDataBean<ModifyPwdInfo>> modifyPwd(String username, String oldpwd, String newpwd){

        return api.modifyPassword(username,oldpwd,newpwd).compose(RxSchedulers.<ResponseDataBean<ModifyPwdInfo>>io_main());
    }

}
