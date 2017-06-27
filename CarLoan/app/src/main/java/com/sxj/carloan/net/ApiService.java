package com.sxj.carloan.net;

import com.sxj.carloan.bean.ServerBean;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 车主APP<br>
 * 作者： 孙向锦<br>
 * 时间： 8/18/2016<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：后台服务接口声明<br>
 */
public interface ApiService {

    //AjaxService.ashx?method=UserLogin&login_name=admin&user_pass=456123
    @POST("AjaxService.ashx")
    public Observable<LoginBack> UserLogin(@Query("method") String method, @Query("login_name") String login_name, @Query("user_pass") String user_pass);


    //PageWork
    @POST("AjaxService.ashx")
    public Observable<ServerBean> PageWork(@Query("method") String method, @Query("sql") String sql, @Query("pageSize") String pageSize, @Query("if_desc") String if_desc);
}
