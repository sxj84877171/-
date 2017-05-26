package com.soarsky.car.data.local.db;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.data.local.db.bean.DriverRecord;
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
 * 程序功能：
 * 该类为 用车记录数据表操作类
 */


public class DriverRecordDb {


    private DaoSession daoSession;
    private Context context;
    private RxDao<DriverRecord,Long> recordLongRxDao;


    public DriverRecordDb(Context context){
        this.context=context;
        daoSession = ((App) context.getApplicationContext()).getDaoSession();
    }



        private static DriverRecordDb single=null;
        //静态工厂方法
        public static DriverRecordDb getInstance(Context context) {
             if (single == null) {
                 single = new DriverRecordDb(context);
             }
            return single;
        }



    private RxDao<DriverRecord,Long> getRecordLongRxDao(){
        if(recordLongRxDao==null){
            recordLongRxDao=daoSession.getDriverRecordDao().rx();
        }
        return  recordLongRxDao;
    }


    /**
     * 插入用车记录数据
     * @param driverRecord
     */
    public void insertData(DriverRecord driverRecord){
        getRecordLongRxDao().insert(driverRecord).subscribe(new Subscriber<DriverRecord>() {
            @Override
            public void onCompleted() {
                LogUtil.i("插入用车记录成功");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.i("插入用车记录失败");
            }

            @Override
            public void onNext(DriverRecord driverRecord) {


            }
        });

    }

}
