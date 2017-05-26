package com.soarsky.car.ui.home.fragment.personal;

import android.app.Activity;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.QueryFamilyBean;
import com.soarsky.car.bean.QueryFamilySendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.data.remote.server1.ApiM;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.data.local.db.FamilyNumDb;

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
 * 程序功能：个人中心model<br>
 * 该类为 PersonalModel<br>
 */

public class PersonalModel implements BaseModel{
    /**
     * 上下文本
     */
    public Activity context;

    /**
     * 亲情表封装操作的对象
     */
    private FamilyNumDb familyNumDb;

    /**
     * 设置文本
     * @param context 文本
     */
    public void setContext(Activity context){

        this.context = context;
        familyNumDb = new FamilyNumDb(context);
        familyNumDb.getFamilyNumDao();
        familyNumDb.getFamilyNumQuery();

    }

    /***
     * 获取亲情号码
     * @param param 亲情号参数
     * @return  ResponseDataBean<QueryFamilyBean> 返回参数
     */
    public Observable<ResponseDataBean<QueryFamilyBean>> queryFirendPhone(QueryFamilySendParam param){
        return ApiM.getInstance().service.queryFirendPhone(param.getUsername(),param.getCarnum(),param.getPhone()).compose(RxSchedulers.<ResponseDataBean<QueryFamilyBean>>io_main());
    }

    /***
     * 插入亲情号码数据
     * @param familyNum 亲情号
     * @return Observable<FamilyNum> 记录
     */
    public Observable<FamilyNum> insertFamilyNum(FamilyNum familyNum){
        return familyNumDb.insertData(familyNum);
    }

    /***
     * 清空亲情号表的所有数据
     * @return Observable<Void>
     */
    public Observable<Void> deleteAll(){
        return familyNumDb.deleteAll();
    }

}
