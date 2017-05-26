package com.soarsky.policeclient.ui.check;

import android.content.Context;
import android.net.wifi.ScanResult;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.data.local.db.bean.BlackCar;
import com.soarsky.policeclient.data.local.db.bean.Check;
import com.soarsky.policeclient.data.local.db.dao.BlackCarDao;
import com.soarsky.policeclient.data.local.db.dao.CheckDao;
import com.soarsky.policeclient.data.local.db.dao.DaoSession;
import com.soarsky.policeclient.uitl.CarUtil;
import com.soarsky.policeclient.uitl.LogUtil;
import com.soarsky.policeclient.uitl.ValidatorUtils;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Subscriber;

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
 * 该类为  稽查数据库操作<br>
 */

public class CheckDb {
    /**
     * 上下文
     */
    private Context context;
    /**
     * 操作数据库的session
     */
    private DaoSession daoSession;
    /**
     * 数据库的RxQuery
     */
    private RxQuery<Check> checkQuery;
    /**
     * 数据库的RxDao
     */
    private RxDao<Check, Long> checkDao;

    public CheckDb(Context context) {
        this.context = context;
        daoSession = ((App) context.getApplicationContext()).getDaoSession();
    }


    /**
     * 查询稽查记录表里面车牌号为carNum的记录
     */
    public List<Check> getCheckQuery(String carNum) {

        return daoSession.getCheckDao().queryBuilder().where(CheckDao.Properties.Carnum.eq(carNum), CheckDao.Properties.InspectTime.between(new Date().getTime() - 24 * 60 * 60 * 1000, new Date().getTime())).list();

    }


    /**
     * 查询稽查记录表里面车牌号为carNum的记录
     */
    public List<BlackCar> getBlackCarList(String carNum) {
        return daoSession.getBlackCarDao().queryBuilder().where(BlackCarDao.Properties.Carnum.eq(carNum)).list();

    }


    /**
     * 获取checkDao对象
     */
    public RxDao<Check, Long> getCheckDao() {
        if (checkDao == null) {
            checkDao = daoSession.getCheckDao().rx();
        }
        return checkDao;
    }

    /**
     * 判断是否存在车牌号为carNum的记录
     *
     * @param carNum
     * @return
     */
    public boolean hasRecord(String carNum) {
        List<Check> checks = getCheckQuery(carNum);
        if (null == checks) {
            return false;
        }

        if (checks.size() > 0) {
            return true;
        }
        return false;
    }


    /**
     * 判断是否是黑名单
     *
     * @param carNum
     * @return
     */
    public boolean isBackList(String carNum) {
        List<BlackCar> list = getBlackCarList(carNum);
        if (null == list) {
            return false;
        }

        if (list.size() > 0) {
            return true;
        }
        return false;
    }


    /**
     * 插入稽查数据
     */
    public void inserData(Car car) {
                String carNum = car.getCarNum();
                if (!hasRecord(carNum)) {
                    Check check = new Check();
                    //判断是不是黑名单
                    if (isBackList(carNum)) {
                        check.setIsBlackList("1");
                    } else {
                        check.setIsBlackList("0");
                    }
                    check.setCarnum(carNum);
                    check.setStatus("0");
                    check.setInspectTime(new Date());
                    check.setOpuser(App.getApp().getUserName());
                    App.getApp().getDaoSession().insert(check);
                }


        }




}
