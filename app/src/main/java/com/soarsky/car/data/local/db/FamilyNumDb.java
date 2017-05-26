package com.soarsky.car.data.local.db;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.data.local.db.dao.DaoSession;
import com.soarsky.car.data.local.db.dao.FamilyNumDao;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：封装亲情号码数据操作 <br>
 * 该类为 FamilyNumDb <br>
 */

public class FamilyNumDb {
    /**
     * daoSession
     */
    private DaoSession daoSession;
    /**
     * 上下文本
     */
    private Context context;
    /**
     * rxdao
     */
    private RxDao<FamilyNum,Long> familyNumDao;
    /**
     * rxQuery
     */
    private RxQuery<FamilyNum> familyNumQuery;
    /**
     * application
     */
    private App app;

    /**
     * 单例
     */
      private static FamilyNumDb single=null;

    /**
     * 单例模式
     * @param context 文本
     * @return FamilyNumDb 封装亲情号码数据操作对象
     */
      public static FamilyNumDb getInstance(Context context) {
           if (single == null) {
               single = new FamilyNumDb(context);
           }
          return single;
      }

    /**
     * 其构造函数
     * @param context 文本
     */
    public FamilyNumDb(Context context){
        this.context = context;
        app= (App) context.getApplicationContext();
        daoSession = ((App)context.getApplicationContext()).getDaoSession();
    }

    /**
     * 获取RxDao
     * @return  RxDao<FamilyNum,Long>
     */
    public RxDao<FamilyNum,Long> getFamilyNumDao(){
        if(familyNumDao == null){
            familyNumDao = daoSession.getFamilyNumDao().rx();
        }
        return familyNumDao;
    }

    /**
     * 获取RxQuery
     * @return  RxQuery<FamilyNum>
     */
    public RxQuery<FamilyNum> getFamilyNumQuery(){
        if(familyNumQuery == null){
            familyNumQuery = daoSession.getFamilyNumDao().queryBuilder().rx();
        }
        return familyNumQuery;
    }

    /**
     * 保存亲情号码
     * @param familyNum 亲情号
     * @return Observable<FamilyNum>
     */
    public Observable<FamilyNum> insertData(FamilyNum familyNum){
        return getFamilyNumDao().insert(familyNum);
    }

    /**
     * 判断是否有未同步的亲情号
     * @return Observable<List<FamilyNum>>
     */
    public boolean isNuSyncTerminal(){

        if(null!=App.getApp().getCarNum()&&getNuTerminal().size()>0){
            return  true;
        }
        return false;
    }

    /**
     * 查询未同步的终端的亲情号码的数据
     * @return Observable<List<FamilyNum>> 亲情列表
     */
    public List<FamilyNum> getNuTerminal(){

//        if(null!=App.getApp().getCommonParam().getCarNum()){
//            return daoSession.getFamilyNumDao().queryBuilder().where(FamilyNumDao.Properties.Tstate.eq(0), FamilyNumDao.Properties.Carnum.eq(App.getApp().getCarNum())).distinct().list();
//        }
//        return  new ArrayList<>();

        if(null!=App.getApp().getCommonParam().getCarNum()){
         return   getFamilyNumDao().getDao().queryBuilder().where(FamilyNumDao.Properties.Tstate.eq(0), FamilyNumDao.Properties.Carnum.eq(App.getApp().getCarNum())).distinct().list();
        }
        return  new ArrayList<>();
    }


    /**
     * 查询要同步的终端的亲情号码的数据
     * @return List<FamilyNum> 亲情列表
     */
    public List<FamilyNum> getNuSyncTerminal(){

        if(null!=App.getApp().getCommonParam().getCarNum()){
            return daoSession.getFamilyNumDao().queryBuilder().where(FamilyNumDao.Properties.Carnum.eq(App.getApp().getCommonParam().getCarNum())).distinct().list();
        }
        return  new ArrayList<>();

    }







    /**
     * 根据车牌号查询亲情号码的数据
     * @param carnum 车牌
     * @return Observable<List<FamilyNum>> 亲情列表
     */
    public Observable<List<FamilyNum>> getFamilyNumList(String carnum){

        return daoSession.getFamilyNumDao().queryBuilder().where(FamilyNumDao.Properties.Carnum.eq(carnum)).distinct().rx().list();
    }

    /**
     * 根据车牌号查询亲情号码的数据
     * @param carnum 车牌
     * @return List<FamilyNum> 亲情列表
     */
    public List<FamilyNum> getFamilyNumList2(String carnum){

        return daoSession.getFamilyNumDao().queryBuilder().where(FamilyNumDao.Properties.Carnum.eq(carnum)).distinct().list();
    }

    /**
     * 根据车牌号和手机号码查询数据
     * @param carnum 车牌
     * @param phone 电话
     * @return List<FamilyNum> 亲情列表
     */
    public List<FamilyNum> getFamilyNumList(String carnum,String phone){
     return daoSession.getFamilyNumDao().queryBuilder().where(FamilyNumDao.Properties.Carnum.eq(carnum),FamilyNumDao.Properties.Phone.eq(phone)).list();
    }


    /**
     * 判断是否是亲情号
     * @param carnum 车牌
     * @param phone 号码
     * @return boolean true - 存在 反之 不存在
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
     * @param familyNum 亲情号
     * @return   Observable<FamilyNum>
     */
    public Observable<FamilyNum> updateData(FamilyNum familyNum){

        return getFamilyNumDao().update(familyNum);


    }


    /**
     * 更改亲情号码状态
     */
    public void  updateData(){
         daoSession.getFamilyNumDao().getDatabase().execSQL("UPDATE FAMILY_NUM SET TSTATE = 1 WHERE CARNUM = '"+app.getCommonParam().getCarNum()+"'");

    }

    /***
     *删除所有数据
     * @return Observable<Void>
     */
    public Observable<Void> deleteAll(){
        return getFamilyNumDao().deleteAll();
    }

    /***
     * 根据车牌号与手机号码查询亲情号码数据
     * @param carnum 车牌号
     * @param phone 电话号码
     * @return List<FamilyNum> 亲情列表
     */
    public Observable<List<FamilyNum>> getFamilyNumListByCarNumAndPhone(String carnum,String phone){

        return daoSession.getFamilyNumDao().queryBuilder().
                where(FamilyNumDao.Properties.Carnum.eq(carnum),FamilyNumDao.Properties.Phone.eq(phone)).distinct().rx().list();
    }

    /***
     * 查询所有的亲情号码数据
     * @return <List<FamilyNum> 亲情列表
     */
    public Observable<List<FamilyNum>> getAllFamilyNumList(){
        return daoSession.getFamilyNumDao().queryBuilder().distinct().rx().list();
    }

    /***
     * 查询所有的亲情号码数据
     * @return List<FamilyNum> 亲情列表
     */
    public List<FamilyNum> getFamilyNumList(){
        return daoSession.getFamilyNumDao().queryBuilder().list();
    }

    /**
     * 更改亲情号码
     */
    public void  updateData(String newPhone,String oldPhone){
        daoSession.getFamilyNumDao().getDatabase().execSQL("UPDATE FAMILY_NUM SET PHONE = '"+newPhone+"'+ WHERE PHONE = '"+oldPhone+"'");

    }

}
