package com.soarsky.car.data.local.db;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.data.local.db.bean.Tborrow;
import com.soarsky.car.data.local.db.dao.DaoSession;
import com.soarsky.car.data.local.db.dao.TborrowDao;
import com.soarsky.car.uitl.WifiHotUtils;

import java.util.List;

/**
 * Created by 何明辉 on 2016/12/2.
 */

public class BorrowDb {
    private DaoSession daoSession;
    private Context context;

    public BorrowDb(Context context) {
        this.context=context;
        daoSession = ((App) context.getApplicationContext()).getDaoSession();
    }


    private static BorrowDb single = null;

    //静态工厂方法
    public static BorrowDb getInstance(Context context) {
        if (single == null) {
            single = new BorrowDb(context);
        }
        return single;
    }

    /**
     * 根据条件查询
     *
     * @param carNum   车牌号
     * @param borrower 借车着姓名
     * @return
     */
    public List<Tborrow> getTBorrlist(String carNum, String borrower) {

        return daoSession.getTborrowDao().queryBuilder().where(TborrowDao.Properties.Borrower.eq(borrower),
                TborrowDao.Properties.Carnum.eq(WifiHotUtils.getInstance().getCarNum(carNum)), TborrowDao.Properties.Status.eq(0)).list();

    }








}
