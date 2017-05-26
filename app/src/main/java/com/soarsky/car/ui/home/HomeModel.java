package com.soarsky.car.ui.home;

import android.app.Activity;

import com.soarsky.car.App;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.CheckVersionBean;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.dao.DaoSession;
import com.soarsky.car.helper.RxSchedulers;

import okhttp3.ResponseBody;
import retrofit2.Call;
import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/1<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  检测版本更新的逻辑层<br>
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
     * @return 版本信息
     */
    public Observable<ResponseDataBean<CheckVersionBean>> checkVersion(String type){
        return api.checkVersion(type).compose(RxSchedulers.<ResponseDataBean<CheckVersionBean>>io_main());
    }

    /**
     * 下载文件
     * @param url 路径
     * @return 响应体
     */
    public Call<ResponseBody> loadFile(String url){

        return api.loadFile(url);

    }


}
