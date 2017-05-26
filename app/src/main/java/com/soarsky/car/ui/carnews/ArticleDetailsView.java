package com.soarsky.car.ui.carnews;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.AutomotiveInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.ArticleCollect;

import java.util.List;

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
 * 该类为文章详情请求服务器视图层<br>
 */

public interface ArticleDetailsView extends BaseView{
    /**
     * 服务器请求失败
     * @param e 异常参数
     */
    public void showError(Throwable e);

    /**
     * 请求成功
     * @param automotiveInfoResponseDataBean 汽车资讯的实体类
     */
    public void showSuccess(ResponseDataBean<AutomotiveInfo> automotiveInfoResponseDataBean);
    /**
     * 向数据库插入一条数据失败
     */
    public void insertFail();

    /**
     * 向数据库插入一条数据成功
     */
    public void insertSuccess();

    /**
     * 从数据库删除收藏文章成功
     */
    void deleteSuccess();

    /**
     * 从数据库查询收藏文章成功
     * @param articleCollect 查询到的文章
     */
    void querySuccess(List<ArticleCollect> articleCollect);
}
