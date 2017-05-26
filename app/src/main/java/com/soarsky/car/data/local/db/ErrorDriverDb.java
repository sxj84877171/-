package com.soarsky.car.data.local.db;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.data.local.db.bean.ErrorDriver;
import com.soarsky.car.data.local.db.dao.DaoSession;
import com.soarsky.car.uitl.LogUtil;

import org.greenrobot.greendao.rx.RxDao;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/8
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：异常行驶数据库操作
 * 该类为
 */


public class ErrorDriverDb {

    private DaoSession daoSession;
    private Context context;
    private RxDao<ErrorDriver, Long> driverLongRxDao;


    public ErrorDriverDb(Context context) {
        this.context = context;
        daoSession = ((App) context.getApplicationContext()).getDaoSession();
    }


    private static ErrorDriverDb single = null;

    //静态工厂方法
    public static ErrorDriverDb getInstance(Context context) {
        if (single == null) {
            single = new ErrorDriverDb(context);
        }
        return single;
    }


    private RxDao<ErrorDriver, Long> getDriverLongRxDao() {
        if (driverLongRxDao == null) {
            driverLongRxDao = daoSession.getErrorDriverDao().rx();
        }
        return driverLongRxDao;
    }


    /**
     * 插入异常行驶数据
     *
     * @param errorDriver
     */
    public void insertData(ErrorDriver errorDriver) {
        getDriverLongRxDao().insert(errorDriver).subscribe(new Subscriber<ErrorDriver>() {
            @Override
            public void onCompleted() {
                LogUtil.i("保存异常行驶数据成功");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.i("保存异常行驶数据失败");
            }

            @Override
            public void onNext(ErrorDriver errorDriver) {

            }
        });
    }
}
