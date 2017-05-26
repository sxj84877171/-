package com.soarsky.installationmanual.ui.register;

import android.app.Activity;

import com.soarsky.installationmanual.App;
import com.soarsky.installationmanual.Constants;
import com.soarsky.installationmanual.base.BaseModel;
import com.soarsky.installationmanual.bean.LoginInfo;
import com.soarsky.installationmanual.bean.RegisterSendBean;
import com.soarsky.installationmanual.bean.ResponseDataBean;
import com.soarsky.installationmanual.data.remote.server1.Api;
import com.soarsky.installationmanual.util.RxSchedulers;
import com.soarsky.installationmanual.util.SpUtil;

import rx.Observable;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 注册Model<br>
 */

public class RegisterModel implements BaseModel {


    /**
     * 发送短信
     * @param phone 电话号码
     * @return
     */
    public Observable<ResponseDataBean<Void>> sendsms(String phone){
        return Api.getInstance().service.sendsms(phone).compose(RxSchedulers.<ResponseDataBean<Void>>io_main());
    }

    /**
     * 注册
     * @param registerSendBean 注册信息
     * @return
     */
    public Observable<ResponseDataBean<Void>> register(RegisterSendBean registerSendBean){
        return Api.getInstance().service.register(registerSendBean.getRealName(),registerSendBean.getPhone(),registerSendBean.getIdNo(),registerSendBean.getVcode(),registerSendBean.getPassword(),registerSendBean.getConPassword()).compose(RxSchedulers.<ResponseDataBean<Void>>io_main());
    }

    /**
     * 保存用户名到Sp
     * @param username
     */
    public void save(String username){
        SpUtil.save(Constants.USERNAME,username);
    }
}
