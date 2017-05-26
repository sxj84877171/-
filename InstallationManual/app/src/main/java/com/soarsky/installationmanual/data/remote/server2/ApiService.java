package com.soarsky.installationmanual.data.remote.server2;



import com.soarsky.installationmanual.bean.UploadFile;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * InstallationManual<br>
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

    @Multipart
    @POST("gtw/app/uploadFastImage")
    public Observable<UploadFile> uploadFile(@Part MultipartBody.Part photo);



    @Multipart
    @POST("FileCenter/file/upload.do")
    public Observable<UploadFile> uploadFile2(@Part MultipartBody.Part photo);

}
