package com.soarsky.policeclient.ui.login;

import android.app.Activity;

import com.soarsky.policeclient.Constants;
import com.soarsky.policeclient.base.BaseModel;
import com.soarsky.policeclient.bean.CheckVersionDataBean;
import com.soarsky.policeclient.bean.LoginDataBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.remote.server1.ApiM;
import com.soarsky.policeclient.helper.RxSchedulers;
import com.soarsky.policeclient.uitl.LogUtil;
import com.soarsky.policeclient.uitl.SpUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
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
 * 该类为  登录Model <br>
 */

public class LoginModel implements BaseModel {

    /**
     * 上下文
     */
    public Activity context;


    public void SetContext(Activity context){
        this.context = context;
    }

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public Observable<ResponseDataBean<LoginDataBean>> login(String username, String password){
        LogUtil.i(username + ":" + password);
        System.out.println(username + ":" + password);
        return ApiM.getInstance().service.login(username,password).compose(RxSchedulers.<ResponseDataBean<LoginDataBean>>io_main());

    }

    /**
     * 保存用户名和密码到文件本地
     * @param username 用户名
     * @param password 密码
     */
    public void saveUsernameAndPassword(String username,String password){
        SpUtil.save(Constants.CONS_USERNAME,username);
    }

    /**
     * 获取新的版本号
     * @param type ：0:ios 1:andorid 2:警务通
     * @return
     */
    public Observable<ResponseDataBean<CheckVersionDataBean>> checkVersion(String type){
        return ApiM.getInstance().service.checkVersion(type).compose(RxSchedulers.<ResponseDataBean<CheckVersionDataBean>>io_main());
    }

    /**
     * 从服务器下载文件
     * @param url 文件地址
     * @return
     */
    public Call<ResponseBody> loadFile(String url){

        return ApiM.getInstance().service.loadFile(url);
//        try {
//            return ApiM.getInstance().service.loadFile(url);
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return null;
    }

}
