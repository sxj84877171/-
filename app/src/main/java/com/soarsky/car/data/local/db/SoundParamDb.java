package com.soarsky.car.data.local.db;

import android.content.Context;
import android.util.Log;

import com.soarsky.car.App;
import com.soarsky.car.data.local.db.bean.ParamSet;
import com.soarsky.car.data.local.db.bean.SoundParam;
import com.soarsky.car.data.local.db.dao.DaoSession;
import com.soarsky.car.data.local.db.dao.ParamSetDao;
import com.soarsky.car.data.local.db.dao.SoundParamDao;
import com.soarsky.car.uitl.LogUtil;

import java.util.List;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/4/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class SoundParamDb {

    /**
     * daoSession
     */
    private DaoSession daoSession;
    /**
     * 上下文本
     */
    private Context context;

    /**
     * application
     */
    private App app;

    /**
     * 单例
     */
    private static SoundParamDb single=null;

    /**
     * 单例模式
     * @param context 文本
     * @return FamilyNumDb 封装亲情号码数据操作对象
     */
    public static SoundParamDb getInstance(Context context) {
        if (single == null) {
            single = new SoundParamDb(context);
        }
        return single;
    }

    /**
     * 其构造函数
     * @param context 文本
     */
    public SoundParamDb(Context context){
        this.context = context;
        app= (App) context.getApplicationContext();
        daoSession = ((App)context.getApplicationContext()).getDaoSession();
    }

    /**
     * 删除数据
     */
    public  void  deleteAll(){
        daoSession.getSoundParamDao().deleteAll();
    }

    /*
    * 插入数据
    */
    public  void insertData(SoundParam soundParam){
        daoSession.getSoundParamDao().rx().insert(soundParam).subscribe(new Subscriber<SoundParam>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                LogUtil.i("插入失败："+e.getMessage().toString());
            }

            @Override
            public void onNext(SoundParam soundParam) {

                LogUtil.i("插入信息：");
                Log.d("TAG","paramSet="+soundParam.getId());
            }
        });
    }

    /**
     * 更新数据
     */
    public  void updateData(SoundParam param){
        daoSession.getSoundParamDao().update(param);
    }

    /**
     * 根据条件查询
     * @return
     */
    public List<SoundParam> getSoundParamlist(int tstatus){
        return daoSession.getSoundParamDao().queryBuilder().where(SoundParamDao.Properties.Tstate.eq(tstatus)).list();
    }

    /**
     * 获取所有数据
     * @return
     */
    public List<SoundParam> getAllSoundParamlist(){
        return daoSession.getSoundParamDao().queryBuilder().list();
    }

}
