package com.soarsky.car.data.local.db;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.data.local.db.bean.Trouble;
import com.soarsky.car.data.local.db.dao.DaoSession;
import com.soarsky.car.data.local.db.dao.TroubleDao;
import com.soarsky.car.uitl.LogUtil;

import java.util.List;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/29
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class TroubleDb {

    private DaoSession daoSession;
    private Context context;

    public  TroubleDb(Context context){
        this.context=context;
        daoSession=((App)context.getApplicationContext()).getDaoSession();
    }


        private static TroubleDb single=null;
        //静态工厂方法   
        public static TroubleDb getInstance(Context context) {
             if (single == null) {    
                 single = new TroubleDb(context);
             }    
            return single;  
        }


    /**
     * 获取未同步到服务器上的数据
     */
    public  List<Trouble>  getUnSync(){

        return daoSession.getTroubleDao().queryBuilder().where(TroubleDao.Properties.Status.eq("0")).list();
    }


    /**
     * 更新数据
     */

    public  void updateData(Trouble trouble){
         daoSession.getTroubleDao().update(trouble);
    }


    /**
     * 删除数据
     */

    public  void  deleteAll(){
        daoSession.getTroubleDao().deleteAll();
    }


    /**
     * 插入数据
     */
    public   void insertData(Trouble trouble){
        daoSession.getTroubleDao().rx().insert(trouble).subscribe(new Subscriber<Trouble>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.i("插入故障失败："+e.getMessage().toString());
            }

            @Override
            public void onNext(Trouble trouble) {
                LogUtil.i("插入故障信息：");
            }
        });
    }




    /**
     * 查询数据
     */
    public  void  getTroubleList(String carNum){
        daoSession.getTroubleDao().queryBuilder().where(TroubleDao.Properties.CarNum.eq(carNum)).rx().list().subscribe(new Subscriber<List<Trouble>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Trouble> troubles) {
                LogUtil.i("查到故障信息："+troubles.size());

            }
        });
    }

}
