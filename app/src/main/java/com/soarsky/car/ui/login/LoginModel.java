package com.soarsky.car.ui.login;

import android.app.Activity;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.DrivingLicenseInfo;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.LoginInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.uitl.SpUtil;

import java.util.List;

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
 * 程序功能：登录Model<br>
 * 该类为 LoginModel<br>
 */

public class LoginModel implements BaseModel{
    /**
     * 上下文本
     */
    public Activity context;
    /**
     * application
     */
    private App app;
    /**
     * service
     */
    private ConfirmDriverService confirmDriverService;

    /**
     * 获取用户
     * @return username 用户名
     */
    public String getUsername(){
        return SpUtil.get("userName");
    }

    /**
     * 获取密码
     * @return 密码
     */
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
     * @return ResponseDataBean<LoginInfo> 登录结果
     */
    public Observable<ResponseDataBean<LoginInfo>> login(String username, String password){
        return api.login(username,password).compose(RxSchedulers.<ResponseDataBean<LoginInfo>>io_main());
    }

    /***
     * 获取所有操作车牌
     * @param phone 手机
     * @return  ResponseDataBean<List<LicenseInfo>> 详情信息
     */
    public Observable<ResponseDataBean<List<LicenseInfo>>> getAllCarnum(String phone){
        return api.getAllCarnum(phone).compose(RxSchedulers.<ResponseDataBean<List<LicenseInfo>>>io_main());
    }

    /**
     * 获取我的驾驶证
     * @param idNo 身份证
     * @param phone 电话
     * @param verCode 验证码
     * @return ResponseDataBean<DrivingLicenseInfo> 信息
     */
    public Observable<ResponseDataBean<DrivingLicenseInfo>> getElecDriver(String idNo, String phone, String verCode){
        return api.getElecDriver(idNo, phone, verCode).compose(RxSchedulers.<ResponseDataBean<DrivingLicenseInfo>>io_main());
    }

    /**
     * 是否绑定驾照
     * @return boolean true 绑定反之
     */
    public  boolean isBindDrivingLicence(){
        if(app.getCommonParam().getDrivingLicence()!=null){
            return  true;
        }
        return  false;
    }


    /**
     * 设置服务
     * @param confirmDriverService
     */
    public void  setConfirmDriverService(ConfirmDriverService confirmDriverService){
        this.confirmDriverService=confirmDriverService;

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
     * 开始连接工作
     */

    public  void  startConnectWork(){
        confirmDriverService.startWork();
    }



}
