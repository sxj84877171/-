package com.soarsky.policeclient.ui.record;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.base.BaseModel;
import com.soarsky.policeclient.bean.RecordAuditDataBean;
import com.soarsky.policeclient.bean.RecordViolationDataBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.local.db.bean.Check;
import com.soarsky.policeclient.data.local.db.bean.Violation;
import com.soarsky.policeclient.data.local.db.dao.ViolationDao;
import com.soarsky.policeclient.data.remote.server1.ApiM;
import com.soarsky.policeclient.helper.RxSchedulers;
import com.soarsky.policeclient.ui.record.fragment.CheckCarFragment;

import java.util.List;

import rx.Observable;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  历史记录Model<br>
 */

public class RecordModel implements BaseModel {

    /**
     * @return 获取罚单历史记录
     */
    public Observable<ResponseDataBean<List<RecordViolationDataBean>>> getRecordViolationParam(String carnum, String page, String place, String stime, String etime) {

        return ApiM.getInstance().service.getRecordViolationParam("2", carnum, page, place, stime, etime, App.getApp().getUserName()).compose(RxSchedulers.<ResponseDataBean<List<RecordViolationDataBean>>>io_main());
    }


    /**
     * @return 获取稽查历史记录
     */
    public Observable<ResponseDataBean<List<RecordAuditDataBean>>> getRecordAuditParam(String carnum, String page) {

        return ApiM.getInstance().service.getRecordAuditParam("1", carnum, page,App.getApp().getUserName()).compose(RxSchedulers.<ResponseDataBean<List<RecordAuditDataBean>>>io_main());

    }
    /**
     * @return 获取罚单历史记录
     */

    public Observable<List<Violation>> getRecordViolationParamFromDb(){
        return App.getApp().getDaoSession().getViolationDao().queryBuilder().orderDesc(ViolationDao.Properties.Id).rx().list().compose(RxSchedulers.<List<Violation>>io_main());
    }
    /**
     * @return 获取稽查历史记录
     */
    public Observable<List<Check>> getRecordCheckCarFromDb(int page){
        return App.getApp().getDaoSession().getCheckDao().queryBuilder().offset(page* CheckCarFragment.pageSize).limit(CheckCarFragment.pageSize).rx().list().compose(RxSchedulers.<List<Check>>io_main());
    }
}
