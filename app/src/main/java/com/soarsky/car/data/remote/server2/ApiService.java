package com.soarsky.car.data.remote.server2;

import com.soarsky.car.bean.UploadFile;

import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 车主APP
 * 作者： 孙向锦
 * 时间： 8/18/2016
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：后台服务接口声明
 */
public interface ApiService {

    @Multipart
    @POST("gtw/app/uploadFastImage")
    public Observable<UploadFile> uploadFile(@Part MultipartBody.Part photo);



    @Multipart
    @POST("FileCenter/file/upload.do")
    public Observable<UploadFile> uploadFile2(@Part MultipartBody.Part photo);

}
