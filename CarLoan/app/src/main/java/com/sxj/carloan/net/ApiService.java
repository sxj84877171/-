package com.sxj.carloan.net;

import com.sxj.carloan.bean.FuncResponseBean;
import com.sxj.carloan.bean.ServerBean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
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
    @POST("AjaxService.ashx?method=UserLogin")
    public Observable<LoginBack> UserLogin(@Query("login_name") String login_name, @Query("user_pass") String user_pass);

    //PageWork
    @POST("AjaxService.ashx?method=PageWork&sql=* from t_case")
    public Observable<ServerBean> PageWork(@Query("pageSize") String pageSize, @Query("offset") String offset, @Query("if_desc") String if_desc);

    @POST("AjaxService.ashx?method=InsertTable&table_name=t_case")
    public Observable<ServerBean> InsertTable(@QueryMap Map<String, String> map);

    @POST("AjaxService.ashx?method=InsertTable&table_name=t_case")
    public Observable<ServerBean> InsertTable(@QueryMap ServerBean.RowsBean bean);

    @POST("AjaxService.ashx?method=InsertTableApp&table_name=t_case")
    public Observable<FuncResponseBean> InsertTable(@Query("case_type_id") String case_type_id
            , @Query("cust_name_tmp") String cust_name_tmp, @Query("cust_sex") String cust_sex
            , @Query("cust_iden") String cust_iden, @Query("cust_marriage_id") String cust_marriage_id
            , @Query("cust_mobile") String cust_mobile, @Query("if_gcr_id") String if_gcr_id
            , @Query("room_type_id") String room_type_id, @Query("cust_address") String cust_address
            , @Query("home_visit_date") String home_visit_date, @Query("car_type") String car_type
            , @Query("deal_price") String deal_price, @Query("credit_years") String credit_years
            , @Query("installment_type_id") String installment_type_id, @Query("user_id_ywy") String user_id_ywy
            , @Query("date_ywy") String date_ywy, @Query("case_state_id ") String case_state_id
    );


    @POST("AjaxService.ashx?method=UpdateTable&table_name=t_case")
    public Observable<ServerBean> UpdateTable(@QueryMap Map<String, String> map);

    @POST("AjaxService.ashx?method=UpdateTableApp&table_name=t_case")
    public Observable<FuncResponseBean> UpdateTable(@Query("id") String id, @Query("case_type_id") String case_type_id
            , @Query("cust_name_tmp") String cust_name_tmp, @Query("cust_sex") String cust_sex
            , @Query("cust_iden") String cust_iden, @Query("cust_marriage_id") String cust_marriage_id
            , @Query("cust_mobile") String cust_mobile, @Query("if_gcr_id") String if_gcr_id
            , @Query("room_type_id") String room_type_id, @Query("cust_address") String cust_address
            , @Query("home_visit_date") String home_visit_date, @Query("car_type") String car_type
            , @Query("deal_price") String deal_price, @Query("credit_years") String credit_years
            , @Query("installment_type_id") String installment_type_id, @Query("user_id_ywy") String user_id_ywy
            , @Query("date_ywy") String date_ywy, @Query("case_state_id ") String case_state_id);


    @Multipart
    @POST("AjaxService.ashx?method=bUpLoadImage")
    public Call<ResponseBody> updateLoadImageFile(@Query("file_name") String filename, @Part() MultipartBody.Part file);

    @Multipart
    @POST("AjaxService.ashx?method=bUpLoadImage")
    Observable<FuncResponseBean> uploadImageFile(@Query("file_name") String filename, @Part() MultipartBody.Part file);
}
