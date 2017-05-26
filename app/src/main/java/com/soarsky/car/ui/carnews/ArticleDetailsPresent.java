package com.soarsky.car.ui.carnews;

import android.util.Log;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.AutomotiveInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.ArticleCollect;

import java.util.List;

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
 * 该类为 文章详情请求服务器P层<br>
 */

public class ArticleDetailsPresent extends BasePresenter<ArticleDetailsModel,ArticleDetailsView> {
    @Override
    public void onStart() {
        mModel.setContext(context);
    }

    /**
     * 获取文章详情
     * @param aid
     */
    public void getArticle(int aid){
        mModel.getAutomotiveInfo(aid).subscribe(new Subscriber<ResponseDataBean<AutomotiveInfo>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

                mView.showError(e);
            }

            @Override
            public void onNext(ResponseDataBean<AutomotiveInfo> automotiveInfoResponseDataBean) {

                if (SUCCESS_FLAG.equals(automotiveInfoResponseDataBean.getStatus())){
                    mView.showSuccess(automotiveInfoResponseDataBean);
                }
            }
        });
    }

    /**
     * 将收藏的文章插入数据库
     * @param articleCollect 数据库收藏文章的参数信息
     */
    public void insertData(ArticleCollect articleCollect){

        mModel.insertCollectRecord(articleCollect).subscribe(new Subscriber<ArticleCollect>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                mView.insertFail();
            }

            @Override
            public void onNext(ArticleCollect articleCollect) {

               mView.insertSuccess();
            }
    });
    }

    /**
     * 删除一条记录
     * @param id 收藏列表文章的id
     */
    public void delete(int id){
        mModel.delete(id).subscribe(new Subscriber<Void>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("ss","ss");
            }

            @Override
            public void onNext(Void aVoid) {
                mView.deleteSuccess();
            }
        });
    }

    /**
     * 查询数据库
     */
    public void qurey(){
        mView.showProgess();
        mModel.getCollectList().subscribe(new Subscriber<List<ArticleCollect>>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
            }

            @Override
            public void onNext(List<ArticleCollect> articleCollect) {
                mView.stopProgess();
                mView.querySuccess(articleCollect);
            }
        });
    }
}
