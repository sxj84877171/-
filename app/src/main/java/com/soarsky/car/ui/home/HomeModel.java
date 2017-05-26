package com.soarsky.car.ui.home;

import android.app.Activity;

import com.soarsky.car.App;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.local.db.dao.DaoSession;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.ui.home.view.CheckVersion;

import okhttp3.ResponseBody;
import retrofit2.Call;
import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/1
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  检测版本更新的逻辑层
 */
public class HomeModel implements BaseModel {

    private Activity context;
    private DaoSession daoSession;


    public void setContext(Activity context) {
        this.context = context;
        daoSession = ((App) context.getApplication()).getDaoSession();
    }


    public void backUserName(String userName){

    }

    public void isDriver(){

    }
    /**
     * 检测版本号的参数类型
     * @param type ��0:ios 1:andorid 2:���
     * @return
     */
    public Observable<CheckVersion> checkVersion(String type){
        return Api.getInstance().service.checkVersion(type).compose(RxSchedulers.<CheckVersion>io_main());
    }

    public Call<ResponseBody> loadFile(String url){

        return Api.getInstance().service.loadFile(url);

    }

}
