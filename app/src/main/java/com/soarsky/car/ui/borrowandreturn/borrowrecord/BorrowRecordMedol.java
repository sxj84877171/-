package com.soarsky.car.ui.borrowandreturn.borrowrecord;

import android.app.Activity;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.BorrowRecords;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.Tborrow;
import com.soarsky.car.helper.RxSchedulers;

import java.util.List;

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
 * 该类为  借车记录逻辑层<br>
 */

public class BorrowRecordMedol implements BaseModel {

    /**
     * 上下文
     */
    public Activity context;
    /**
     * 数据库操作对象
     */
    private BorrowRecordDb borrowRecordDb;

    /**
     * 初始化上下文、数据库操作对象
     * @param context 上下文
     */
    public void setContext(Activity context){

        this.context = context;
        borrowRecordDb = new BorrowRecordDb(context);
        borrowRecordDb.getTborrowIntegerRxDao();
        borrowRecordDb.getTborrowRxQuery();
    }

    public Observable<ResponseDataBean<List<BorrowRecords>>> record(String phone, String username){
        return api.borrowRecord(phone,username).
                compose(RxSchedulers.<ResponseDataBean<List<BorrowRecords>>>io_main());
    }

    /**
     * 插入借车记录
     * @param tborrow 记录
     * @return 记录表
     */
    public Observable<Tborrow> insertBorrowRecord(Tborrow tborrow){
        return borrowRecordDb.insertData(tborrow);
    }
}
