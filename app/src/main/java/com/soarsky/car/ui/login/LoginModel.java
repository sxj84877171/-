package com.soarsky.car.ui.login;

import android.app.Activity;

import com.soarsky.car.App;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.data.local.db.dao.DaoSession;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.ui.family.FamilyNumDb;
import com.soarsky.car.ui.license.DriviLicenseParam;
import com.soarsky.car.ui.register.RegisterParam;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.SpUtil;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

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

public class LoginModel implements BaseModel{

    public Activity context;
    private App app;
    private ConfirmDriverService confirmDriverService;


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
     * @param username
     * @param password
     * @return
     */
    public Observable<LoginParam> login(String username, String password){
        return Api.getInstance().service.login(username,password).compose(RxSchedulers.<LoginParam>io_main());
    }

    /***
     * 获取所有操作车牌
     * @param phone
     * @return
     */
    public Observable<CarNumParam> getAllCarnum(String phone){
        return Api.getInstance().service.getAllCarnum(phone).compose(RxSchedulers.<CarNumParam>io_main());
    }

    /**
     * 获取我的驾驶证
     * @param idNo
     * @param phone
     * @param verCode
     * @return
     */
    public Observable<DriviLicenseParam> getElecDriver(String idNo, String phone, String verCode){
        return Api.getInstance().service.getElecDriver(idNo,phone,verCode).compose(RxSchedulers.<DriviLicenseParam>io_main());
    }


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
     * 开始连接工作
     */

    public  void  startConnectWork(){
        confirmDriverService.startWork();
    }





}
