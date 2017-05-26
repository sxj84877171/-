package com.soarsky.policeclient.ui.blacklist;

import android.app.Activity;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.base.BaseModel;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.local.db.bean.BlackCar;
import com.soarsky.policeclient.data.local.db.dao.BlackCarDao;
import com.soarsky.policeclient.data.local.db.dao.DaoSession;
import com.soarsky.policeclient.data.remote.server1.ApiM;
import com.soarsky.policeclient.helper.RxSchedulers;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.List;

import rx.Observable;

/**
 * andriod_police_app<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  黑名单列表逻辑层<br>
 */

public class BlackListModel implements BaseModel {
    /**
     * 上下文
     */
    private Activity context;
    /**
     * 数据库操作session
     */
    private DaoSession daoSession;
    /**
     * RxDao
     */
    private RxDao<BlackCar, Long> blackCarDao;
    /**
     * RxQuery
     */
    private RxQuery<BlackCar> blackCarQuery;

    /**
     * @return 获取黑名单
     */
    public Observable<ResponseDataBean<List<BlackCar>>> getBlackCar(String time) {
        return ApiM.getInstance().service.getBlackCar(time).compose(RxSchedulers.<ResponseDataBean<List<BlackCar>>>io_main());

    }

    /**
     * 获取黑名单RxDao
     *
     * @return
     */
    public RxDao<BlackCar, Long> getBlackCarDao() {
        if (blackCarDao == null) {
            blackCarDao = daoSession.getBlackCarDao().rx();
        }
        return blackCarDao;
    }


    public void setContext() {
        daoSession = App.getApp().getDaoSession();
    }


    /**
     * @return 获取黑名单列表
     */
    public RxQuery<BlackCar> getBlackCarQuery() {
        return getBlackCarDao().getDao().queryBuilder().rx();
    }

    public List<BlackCar> getBlackCarQuery1() {
        return getBlackCarDao().getDao().queryBuilder().list();
    }

    /**
     * @return 插入黑名单
     */
    public Observable<BlackCar> insertBlackCar(BlackCar blackCar) {
        return getBlackCarDao().insert(blackCar);

    }


    /**
     * @return 插入黑名单
     */
    public void insertBlackCar(List<BlackCar> blackCar) {

        for (BlackCar blackCar1 : blackCar) {
            if (!blackCar1.getChangetype().equals(0)) {
                /*if("已处理".equals(blackCar1.getReason())){
                    blackCar1.setIsHandle(true);
                }else {
                    blackCar1.setIsHandle(false);
                }*/
                int size = App.getApp().getDaoSession().getBlackCarDao().queryBuilder().where(BlackCarDao.Properties.Id.eq(blackCar1.getId())).list().size();
                if(size==0){
                    App.getApp().getDaoSession().getBlackCarDao().insert(blackCar1);
                }
            } else {
                deleteCountForCarNum(blackCar1.getCarnum());
            }
        }

    }

    /**
     * @return 删除黑名单
     */
    public Observable<Void> delete() {
        return getBlackCarDao().deleteAll();
    }


    /**
     * 根据车牌号删除记录
     */
    private void deleteCountForCarNum(String carNum) {
         /*String sql="delete from BLACK_CAR where CARNUM = '"+carNum+"";
         daoSession.getBlackCarDao().getDatabase().execSQL(sql);*/
        App.getApp().getDaoSession().getBlackCarDao().deleteInTx(App.getApp().getDaoSession().getBlackCarDao().queryBuilder().where(BlackCarDao.Properties.Carnum.eq(carNum)).list());
    }
}
