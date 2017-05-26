package com.soarsky.car.ui.family;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.data.local.db.dao.DaoSession;
import com.soarsky.car.data.local.db.dao.FamilyNumDao;
import com.soarsky.car.uitl.LogUtil;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：封装亲情号码数据操作
 * 该类为 亲情号码表数据操作
 */

public class FamilyNumDb {

    private DaoSession daoSession;

    private Context context;

    private RxDao<FamilyNum,Long> familyNumDao;

    private RxQuery<FamilyNum> familyNumQuery;

    private App app;


      private static FamilyNumDb single=null;
      //静态工厂方法
      public static FamilyNumDb getInstance(Context context) {
           if (single == null) {
               single = new FamilyNumDb(context);
           }
          return single;
      }

    public FamilyNumDb(Context context){
        this.context = context;
        app= (App) context.getApplicationContext();
        daoSession = ((App)context.getApplicationContext()).getDaoSession();
    }

    public RxDao<FamilyNum,Long> getFamilyNumDao(){
        if(familyNumDao == null){
            familyNumDao = daoSession.getFamilyNumDao().rx();
        }
        return familyNumDao;
    }

    public RxQuery<FamilyNum> getFamilyNumQuery(){
        if(familyNumQuery == null){
            familyNumQuery = daoSession.getFamilyNumDao().queryBuilder().rx();
        }
        return familyNumQuery;
    }

    /**
     * 保存亲情号码
     * @param familyNum
     */
    public Observable<FamilyNum> insertData(FamilyNum familyNum){
        return getFamilyNumDao().insert(familyNum);
    }

    /**
     * 查询未同步的后台的亲情号码的数据
     * @return
     */
    public Observable<List<FamilyNum>> getNuService(){
        return daoSession.getFamilyNumDao().queryBuilder().where(FamilyNumDao.Properties.Sstate.eq(0)).distinct().rx().list();
    }

    /**
     * 查询未同步的终端的亲情号码的数据
     * @return
     */
    public Observable<List<FamilyNum>> getNuTerminal(){

        return daoSession.getFamilyNumDao().queryBuilder().where(FamilyNumDao.Properties.Tstate.eq(0)).distinct().rx().list();
    }


    /**
     * 查询未同步的终端的亲情号码的数据
     * @return
     */
    public List<FamilyNum> getNuSyncTerminal(){

        return daoSession.getFamilyNumDao().queryBuilder().where(FamilyNumDao.Properties.Tstate.eq(0)).distinct().list();
    }


    /**
     * 根据车牌号查询亲情号码的数据
     * @return
     */




    /**
     * 根据车牌号查询亲情号码的数据
     * @return
     */
    public Observable<List<FamilyNum>> getFamilyNumList(String carnum){

        return daoSession.getFamilyNumDao().queryBuilder().where(FamilyNumDao.Properties.Carnum.eq(carnum)).distinct().rx().list();
    }

    /**
     * 根据车牌号查询亲情号码的数据
     * @return
     */
    public List<FamilyNum> getFamilyNumList2(String carnum){

        return daoSession.getFamilyNumDao().queryBuilder().where(FamilyNumDao.Properties.Carnum.eq(carnum)).distinct().list();
    }

    /**
     * 根据车牌号和手机号码查询数据
     * @return
     */
    public List<FamilyNum> getFamilyNumList(String carnum,String phone){
     return daoSession.getFamilyNumDao().queryBuilder().where(FamilyNumDao.Properties.Carnum.eq(carnum),FamilyNumDao.Properties.Phone.eq(phone)).list();
    }


    /**
     * 判断是否是亲情号
     * @param carnum
     * @param phone
     * @return
     */
    public boolean isFamilyNum(String carnum,String phone){


        List<FamilyNum> list=getFamilyNumList(carnum,phone);
        if(list!=null&&list.size()>0){
            return  true;

        }
            return false;


    }


    /**
     * 更改数据
     * @param familyNum
     */
    public Observable<FamilyNum> updateData(FamilyNum familyNum){

        return getFamilyNumDao().update(familyNum);


    }


    /**
     * 更改亲情号码状态
     * @param
     */
    public void  updateData(){
         daoSession.getFamilyNumDao().getDatabase().execSQL("UPDATE FAMILY_NUM SET TSTATE = 1 WHERE CARNUM = '"+app.getCommonParam().getCarNum()+"'");

    }

    /***
     *删除所有数据
     * @return
     */
    public Observable<Void> deleteAll(){
        return getFamilyNumDao().deleteAll();
    }

    /***
     * 根据车牌号与手机号码查询亲情号码数据
     * @param carnum
     * @param phone
     * @return
     */
    public Observable<List<FamilyNum>> getFamilyNumListByCarNumAndPhone(String carnum,String phone){

        return daoSession.getFamilyNumDao().queryBuilder().
                where(FamilyNumDao.Properties.Carnum.eq(carnum),FamilyNumDao.Properties.Phone.eq(phone)).distinct().rx().list();
    }

    /***
     * 查询所有的亲情号码数据
     * @return
     */
    public Observable<List<FamilyNum>> getAllFamilyNumList(){
        return daoSession.getFamilyNumDao().queryBuilder().distinct().rx().list();
    }

    /***
     * 查询所有的亲情号码数据
     * @return
     */
    public List<FamilyNum> getFamilyNumList(){
        return daoSession.getFamilyNumDao().queryBuilder().list();
    }

}
