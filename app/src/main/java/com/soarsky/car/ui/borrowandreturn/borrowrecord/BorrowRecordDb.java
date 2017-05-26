package com.soarsky.car.ui.borrowandreturn.borrowrecord;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.data.local.db.bean.Tborrow;
import com.soarsky.car.data.local.db.dao.DaoSession;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：借车记录数据库操作<br>
 * 该类为<br>
 */

public class BorrowRecordDb {
    private DaoSession daoSession;
    private Context context;
    private RxDao<Tborrow,Integer> tborrowIntegerRxDao;
    private RxQuery<Tborrow> tborrowRxQuery;

    private static BorrowRecordDb single = null;
    //静态工厂方法
    private static BorrowRecordDb getInstance(Context context){
        if (single == null){
            single = new BorrowRecordDb(context);
        }
        return single;
    }

    public BorrowRecordDb(Context context){
        this.context = context;
        daoSession = ((App)context.getApplicationContext()).getDaoSession();
    }

    public RxDao<Tborrow,Integer> getTborrowIntegerRxDao(){
        if (tborrowIntegerRxDao == null){
            tborrowIntegerRxDao = daoSession.getTborrowDao().rx();
        }
        return tborrowIntegerRxDao;
    }

    public RxQuery<Tborrow> getTborrowRxQuery(){
        if (tborrowRxQuery == null){
            tborrowRxQuery = daoSession.getTborrowDao().queryBuilder().rx();
        }
        return tborrowRxQuery;
    }

    /**
     * 保存借车记录
     * @param tborrow
     * @return 借车表
     */
    public Observable<Tborrow> insertData(Tborrow tborrow){
        return getTborrowIntegerRxDao().insert(tborrow);
    }
}
