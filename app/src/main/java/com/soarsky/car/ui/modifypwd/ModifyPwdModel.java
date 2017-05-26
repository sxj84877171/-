package com.soarsky.car.ui.modifypwd;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.remote.server1.Api;
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
 * 程序功能：
 * 该类为
 */

public class ModifyPwdModel implements BaseModel{

    /**
     * 修改密码请求
     * @param username
     * @param oldpwd
     * @param newpwd
     * @return
     */
    public Observable<ModifyPwdParam> modifyPwd(String username,String oldpwd,String newpwd){

        return Api.getInstance().service.modifyPassword(username,oldpwd,newpwd).compose(RxSchedulers.<ModifyPwdParam>io_main());
    }

}
