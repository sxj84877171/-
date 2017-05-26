package com.soarsky.installationmanual.ui.main.fragment.updatephone;

import com.soarsky.installationmanual.base.BaseModel;
import com.soarsky.installationmanual.bean.ResponseDataBean;
import com.soarsky.installationmanual.data.remote.server1.Api;
import com.soarsky.installationmanual.util.RxSchedulers;

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
 * 该类为 修改手机号Model<br>
 */

public class UpdatePhoneModel implements BaseModel {

    /**
     * 修改手机号
     * @param idNo 身份证号
     * @param phone 电话号码
     * @param newPhone 新电话号码
     * @return
     */
    public Observable<ResponseDataBean<Void>> modifyPhone(String idNo,  String phone, String newPhone){
        return Api.getInstance().service.modifyPhone(idNo,phone,newPhone).compose(RxSchedulers.<ResponseDataBean<Void>>io_main());
    }

}
