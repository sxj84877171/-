package com.soarsky.installationmanual.ui.forgetpwd;

import android.app.Activity;

import com.soarsky.installationmanual.App;
import com.soarsky.installationmanual.Constants;
import com.soarsky.installationmanual.base.BaseModel;
import com.soarsky.installationmanual.bean.LoginInfo;
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
 * 该类为 找回密码Model<br>
 */

public class ForgetPwdModel implements BaseModel {
    /**
     * 发送短信
     * @param phone 电话号码
     * @return
     */
    public Observable<ResponseDataBean<Void>> sendsms(String phone){
        return Api.getInstance().service.sendsms(phone).compose(RxSchedulers.<ResponseDataBean<Void>>io_main());
    }
    /**
     * 找回密码
     * @param phone 电话
     * @param idCard 身份证号
     * @param yanzhengma 验证码
     * @param pwd 密码
     * @param pwdAgain 确认密码
     * @return
     */
    public Observable<ResponseDataBean<Void>> forgetPwd(final String phone,final String yanzhengma,final String pwd,final String pwdAgain,final String idCard){
        return Api.getInstance().service.getBackPwd(phone,idCard,yanzhengma,pwd,pwdAgain).compose(RxSchedulers.<ResponseDataBean<Void>>io_main());
    }

    /**
     * 保存用户名到Sp中
     * @param username 用户名
     */
    public void saveUsernameAndPassword(String username){
        SpUtil.save(Constants.USERNAME,username);
    }
}
