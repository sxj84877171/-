package com.soarsky.car.data.local.db;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.data.local.db.bean.ArticleCollect;
import com.soarsky.car.data.local.db.dao.ArticleCollectDao;
import com.soarsky.car.data.local.db.dao.DaoSession;
import com.soarsky.car.uitl.SpUtil;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/11<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  文章收藏数据库操作<br>
 */

public class ArticleCollectDb {

    private DaoSession daoSession;
    private Context context;
    private RxDao<ArticleCollect,Long> articleCollectIntegerRxDao;
    private RxQuery<ArticleCollect> articleCollectRxQuery;

    private static ArticleCollectDb single = null;
    //静态工厂方法
    private static ArticleCollectDb getInstance(Context context){
        if (single == null){
            single = new ArticleCollectDb(context);
        }
        return single;
    }
    public RxDao<ArticleCollect,Long> getArticleCollectIntegerRxDao(){
        if (articleCollectIntegerRxDao == null){
            return daoSession.getArticleCollectDao().rx();
        }
        return articleCollectIntegerRxDao;
    }

    public RxQuery<ArticleCollect> getArticleCollectRxQuery(){
        if (articleCollectRxQuery == null){
            articleCollectRxQuery = daoSession.getArticleCollectDao().queryBuilder().rx();
        }
        return articleCollectRxQuery;
    }

    public  ArticleCollectDb(Context context){
        this.context=context;
        daoSession = ((App) context.getApplicationContext()).getDaoSession();
    }

    public Observable<ArticleCollect> insertData(ArticleCollect articleCollect){

        return getArticleCollectIntegerRxDao().insert(articleCollect);

    }

    /**
     * 查询所有记录
     * @return
     */
    public Observable<List<ArticleCollect>> getArticleCollectList(){
       return  daoSession.getArticleCollectDao().rx().loadAll();
    }

    public Observable<Void> deleteData(int id){
        List<ArticleCollect> list = App.getApp().getDaoSession().getArticleCollectDao().queryBuilder().
                where(ArticleCollectDao.Properties.Id.eq(id),ArticleCollectDao.Properties.AppUser.eq(SpUtil.get(Constants.USERNAME))).list();
        if(list != null){
            for(ArticleCollect ac : list){
                return App.getApp().getDaoSession().getArticleCollectDao().rx().delete(ac);
            }
        }
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                subscriber.onError(new Exception("no data"));
            }
        });
    }

}
