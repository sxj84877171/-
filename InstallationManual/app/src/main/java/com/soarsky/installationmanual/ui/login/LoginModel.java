package com.soarsky.installationmanual.ui.login;

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
 * 该类为 登陆Model<br>
 */

public class LoginModel implements BaseModel {

    public Activity context;
    private App app;



    public String getUsername(){
        return SpUtil.get("userName");
    }

    public String getPassword(){
        return SpUtil.get("passWord");
    }

    public void setContext(Activity context){
        this.context = context;
        app= (App) context.getApplication();

    }



    /**
     * 登陆请求
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public Observable<ResponseDataBean<LoginInfo>> login(String username, String password){
        return Api.getInstance().service.login(username,password).compose(RxSchedulers.<ResponseDataBean<LoginInfo>>io_main());
    }

    /**
     * 保存用户名
     * @param username 用户名
     */
    public void saveUsernameAndPassword(String username){
        SpUtil.save(Constants.USERNAME,username);
    }

    /**
     * 保存用户数据
     * @param loginInfo
     */
    public void saveLoginInfo(LoginInfo loginInfo){
        SpUtil.save(Constants.NAME,loginInfo.getRealname());
        SpUtil.save(Constants.PHONE,loginInfo.getPhone());
    }
}
