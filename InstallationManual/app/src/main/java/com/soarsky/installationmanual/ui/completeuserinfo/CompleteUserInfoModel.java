package com.soarsky.installationmanual.ui.completeuserinfo;

import android.app.Activity;

import com.soarsky.installationmanual.App;
import com.soarsky.installationmanual.base.BaseModel;
import com.soarsky.installationmanual.bean.LoginInfo;
import com.soarsky.installationmanual.bean.ResponseDataBean;
import com.soarsky.installationmanual.bean.VeAdBean;
import com.soarsky.installationmanual.data.remote.server1.Api;
import com.soarsky.installationmanual.util.RxSchedulers;
import com.soarsky.installationmanual.util.SpUtil;

import java.util.List;

import retrofit2.http.Query;
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
 * 该类为 完善用户信息Model<br>
 */

public class CompleteUserInfoModel implements BaseModel {
    /**
     * 完善个人资料
     * @param sex 性别
     * @param username 姓名
     * @param itemCode 地区代码
     * @param carId 车管所id
     * @param pointId 安装点id
     * @return
     */
    public Observable<ResponseDataBean<Void>> perfectUser(String sex, String username, String itemCode, String carId, String pointId){
        return Api.getInstance().service.perfectUser(sex,username,itemCode,carId,pointId).compose(RxSchedulers.<ResponseDataBean<Void>>io_main());
    }

}
