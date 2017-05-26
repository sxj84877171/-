package com.soarsky.car.ui.borrowandreturn.borrowrecord;

import android.app.Activity;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.local.db.bean.Tborrow;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;

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
 * 该类为  借车记录逻辑层
 */

public class BorrowRecordMedol implements BaseModel {


    public Activity context;

    private BorrowRecordDb borrowRecordDb;

    public void setContext(Activity context){

        this.context = context;
        borrowRecordDb = new BorrowRecordDb(context);
        borrowRecordDb.getTborrowIntegerRxDao();
        borrowRecordDb.getTborrowRxQuery();

    }
    public Observable<BorrowRecordParm> record(String phone, String username){
        return Api.getInstance().service.borrowRecord(phone,username).
                compose(RxSchedulers.<BorrowRecordParm>io_main());
    }

    /**
     * 插入借车记录
     * @param tborrow
     * @return
     */
    public Observable<Tborrow> insertBorrowRecord(Tborrow tborrow){
        return borrowRecordDb.insertData(tborrow);
    }
}
