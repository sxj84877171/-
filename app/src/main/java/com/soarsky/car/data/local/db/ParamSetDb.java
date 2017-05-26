package com.soarsky.car.data.local.db;

import android.content.Context;
import android.util.Log;

import com.soarsky.car.App;
import com.soarsky.car.data.local.db.bean.ParamSet;
import com.soarsky.car.data.local.db.bean.Trouble;
import com.soarsky.car.data.local.db.dao.DaoSession;
import com.soarsky.car.data.local.db.dao.ParamSetDao;
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


public class ParamSetDb {

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
    private static ParamSetDb single=null;

    /**
     * 单例模式
     * @param context 文本
     * @return FamilyNumDb 封装亲情号码数据操作对象
     */
    public static ParamSetDb getInstance(Context context) {
        if (single == null) {
            single = new ParamSetDb(context);
        }
        return single;
    }

    /**
     * 其构造函数
     * @param context 文本
     */
    public ParamSetDb(Context context){
        this.context = context;
        app= (App) context.getApplicationContext();
        daoSession = ((App)context.getApplicationContext()).getDaoSession();
    }

    /**
     * 删除数据
     */
    public  void  deleteAll(){
        daoSession.getParamSetDao().deleteAll();
    }

    /*
    * 插入数据
    */
    public  void insertData(ParamSet paramSet){
        daoSession.getParamSetDao().rx().insert(paramSet).subscribe(new Subscriber<ParamSet>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                LogUtil.i("插入故障失败："+e.getMessage().toString());
            }

            @Override
            public void onNext(ParamSet paramSet) {
                LogUtil.i("插入信息：");
//                Log.d("TAG","paramSet="+paramSet.getId());
            }
        });
    }

    /**
     * 更新数据
     */
    public  void updateData(ParamSet paramSet){
        daoSession.getParamSetDao().update(paramSet);
    }

    /**
     * 根据条件查询
     * @return
     */
    public List<ParamSet> getParamSetlist(int tstatus){
        return daoSession.getParamSetDao().queryBuilder().where(ParamSetDao.Properties.Tstate.eq(tstatus)).list();
    }

    /**
     * 获取所有的数据
     * @return
     */
    public List<ParamSet> getAllParamSetList(){
        return daoSession.getParamSetDao().queryBuilder().list();
    }

}
