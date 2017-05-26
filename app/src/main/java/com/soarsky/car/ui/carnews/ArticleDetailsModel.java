package com.soarsky.car.ui.carnews;

import android.app.Activity;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.AutomotiveInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.ArticleCollectDb;
import com.soarsky.car.data.local.db.bean.ArticleCollect;
import com.soarsky.car.helper.RxSchedulers;

import java.util.List;

import rx.Observable;

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
 * 该类为  文章详情请求服务器逻辑层<br>
 */

public class ArticleDetailsModel implements BaseModel {
    /**
     * 上下文
     */
    private Activity context;
    /**
     * 数据库操作对象
     */
    private ArticleCollectDb articleCollectDb;
    /**
     * 数据库操作
     */
    public void setContext(Activity context){
        this.context = context;
        articleCollectDb = new ArticleCollectDb(context);
        articleCollectDb.getArticleCollectIntegerRxDao();
        articleCollectDb.getArticleCollectRxQuery();
    };
    /**
     * 文章详情
     * @param aid 每条资讯的id
     * @return 汽车资讯实体类信息
     */
    public Observable<ResponseDataBean<AutomotiveInfo>> getAutomotiveInfo(int aid){
        return api.getAutomotiveInfo(aid).compose(RxSchedulers.<ResponseDataBean<AutomotiveInfo>>io_main());

    }

    /**
     * 插入收藏记录
     * @param articleCollect  要插进数据库的文章实体
     * @return 收藏的文章
     */
    public Observable<ArticleCollect> insertCollectRecord(ArticleCollect articleCollect){
        return articleCollectDb.insertData(articleCollect).compose(RxSchedulers.<ArticleCollect>io_main());
    }


    /**
     * 收藏页面取消关注
     * @param id 收藏文章的id
     * @return Void
     */
    public Observable<Void> delete(int id){
        return articleCollectDb.deleteData(id).compose(RxSchedulers.<Void>io_main());
    }

    /**
     * 查询所有收藏的记录
     * @return 查询到的收藏的文章的集合
     */
    public Observable<List<ArticleCollect>> getCollectList(){
        return articleCollectDb.getArticleCollectList().compose(RxSchedulers.<List<ArticleCollect>>io_main());
    }
}
