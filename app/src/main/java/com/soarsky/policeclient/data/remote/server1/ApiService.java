package com.soarsky.policeclient.data.remote.server1;

import com.soarsky.policeclient.bean.AccidentBean;
import com.soarsky.policeclient.bean.CarDetailsDataBean;
import com.soarsky.policeclient.bean.CheckParam;
import com.soarsky.policeclient.bean.CheckVersionDataBean;
import com.soarsky.policeclient.bean.DetailsViolationCount;
import com.soarsky.policeclient.bean.ForgetPwdDataBean;
import com.soarsky.policeclient.bean.LoginDataBean;
import com.soarsky.policeclient.bean.RecordAuditDataBean;
import com.soarsky.policeclient.bean.RecordViolationDataBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.bean.ViolationDataBean;
import com.soarsky.policeclient.data.local.db.bean.BlackCar;
import com.soarsky.policeclient.ui.accident.serverbean.AccidentAnalysis;
import com.soarsky.policeclient.ui.accident.serverbean.AccidentItem;
import com.soarsky.policeclient.ui.accident.serverbean.AccidentReasonDataBean;
import com.soarsky.policeclient.ui.accident.serverbean.GuzhangBean;
import com.soarsky.policeclient.ui.accident.serverbean.WeizhangBean;
import com.soarsky.policeclient.ui.violation.UploadFile;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;


/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/26<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为网络请求接口<br>
 */

public interface ApiService {

    /**
     * 登陆
     */
    @POST("app/jwtlogin")
    public Observable<ResponseDataBean<LoginDataBean>> login(@Query("username") String username, @Query("password") String password);

    /**
     * 修改密码
     */
    @POST("app/jwtModPwd")
    public Observable<ResponseDataBean> modPassword(@Query("username") String username, @Query("password") String idNo, @Query("newPassword") String phone);


    /**
     * 登陆
     */
    @POST("app/jwtlogin")
    public Observable<ResponseDataBean<LoginDataBean>> login(@QueryMap Map<String, String> map);

    /**
     * 上传违章信息
     */
    @POST("app/UploadIlleagal")
    public Observable<ResponseDataBean<ViolationDataBean>> sendViolation(@QueryMap Map<String, String> map);


    //http://192.168.100.16:8080/gtw/app/modifyIlleagal?id=&fileUrl=
    @POST("app/modifyIlleagal")
    public Observable<ResponseDataBean<Void>> modifyIlleagal(@Query("id") int id, @Query("fileUrl") String fileUrl);
    /**
     * 获取黑名单
     */
    @POST("app/getBlackCar")
    public Observable<ResponseDataBean<List<BlackCar>>> getBlackCar(@Query("time") String time);

    /**
     * 忘记密码
     */
    @POST("app/getJwtBackPwd?")
    public Observable<ResponseDataBean<ForgetPwdDataBean>> forgetPassword(@Query("username") String username, @Query("idNo)") String idNo, @Query("phone") String phone);

    /**
     * 获取车辆详情
     */
    @POST("app/getCarIlleagaRecords")
    public Observable<ResponseDataBean<CarDetailsDataBean>> getCarDetails(@Query("carnum") String carnum);

    /**
     * 上传稽查信息 多条信息用“，”隔开
     */
    @POST("app/UploadInspect")
    public Observable<ResponseDataBean<CheckParam>> uploadInspect(@QueryMap Map<String, String> map);

    /**
     * 获取违章历史记录1
     */
    @POST("app/getAuditRecords")
    public Observable<ResponseDataBean<List<RecordViolationDataBean>>> getRecordViolationParam(@Query("type") String type, @Query("carnum") String carnum, @Query("page") String page, @Query("place") String place, @Query("stime") String stime, @Query("etime") String etime, @Query("opuser") String opuser);

    /**
     * 获取稽查历史记录接口
     */
    @POST("app/getAuditRecords")
    public Observable<ResponseDataBean<List<RecordAuditDataBean>>> getRecordAuditParam(@Query("type") String type, @Query("carnum") String carnum, @Query("page") String page, @Query("opuser") String opuser);

    /**
     * 获取违章历史记录
     */
    @POST("app/getAuditRecords")
    public Observable<ResponseDataBean<DetailsViolationCount>> getAuditRecords(@Query("type") String type, @Query("carnum") String carnum, @Query("page") String page, @Query("place") String place, @Query("stime") String stime,
                                                             @Query("etime") String etime);

    /**
     * 获取最新版本号
     */
    @POST("app/getVersions")
    public Observable<ResponseDataBean<CheckVersionDataBean>> checkVersion(@Query("type") String ype);

    /**
     *  获取文件
     * @return
     */
    @Streaming
    @GET
    public Call<ResponseBody> loadFile(@Url String url);

    /**
     * 获取事故分析列表
     */
    @POST("app/getAccidentList")
    public Observable<ResponseDataBean<List<AccidentBean>>> getAccidentList();

    /**
     * 根据服务器返回的id获取每一条事故数据
     * @param id
     * @return
     */
    @POST("app/getAccident")
    public Observable<ResponseDataBean<AccidentItem>> getAccidentListItem(@Query("aid") String id);

    /**
     * 获取事故原因列表
     * @return
     */
    @POST("app/getReasonList")
    public Observable<ResponseDataBean<List<AccidentReasonDataBean>>> getReasonList();

    /**
     * 发送故障数据到服务器
     * @param carnum 车牌号
     * @param stime 时间
     * @param remark 备注
     * @return
     */
    @POST("app/uploadFault")
    public Observable<ResponseDataBean<GuzhangBean>> sendGuzhang(@Query("carnum") String carnum , @Query("stime") String stime, @Query("remark") String remark);

    /**
     * 提交事故分析数据到服务器
     * @param map 事故分析map对象
     * @return
     */
    @POST("app/uploadAccidentAnalysis")
    public Observable<ResponseDataBean<AccidentAnalysis>> uploadAccidentAnalysis(@QueryMap Map<String, String> map);

    /**
     * 上传所有事故数据到服务器
     * @param aanalysis 事故分析id
     * @param rid 故障id
     * @param stime 事故时间
     * @param location 事故地点
     * @param reason 事故原因
     * @param imageUrl 多张图片通过“，”拼接的字符串
     * @param remark 备注
     * @return
     */
    @POST("app/uploadAccident")
    public Observable<ResponseDataBean> uploadAccident(@Query("aanalysis") String aanalysis , @Query("rid") String rid, @Query("stime") String stime, @Query("location") String location, @Query("reason") String reason, @Query("imageUrl") String imageUrl, @Query("remark") String remark);

    @POST("app/updateAccident")
    public Observable<ResponseDataBean> updateAccident(@Query("aid") String aid,@Query("aanalysis") String aanalysis , @Query("rid") String rid, @Query("stime") String stime, @Query("location") String location, @Query("reason") String reason, @Query("imageUrl") String imageUrl, @Query("remark") String remark);


    /**
     * 根据车牌号从服务器查询违章数据
     * @param carnum
     * @return
     */
    @POST("app/getIlleagaInfoByCarnum")
    public  Observable<ResponseDataBean<WeizhangBean>> WeizhangQuery(@Query("carnum")String carnum);

    /**
     * http://192.168.100.52:8080/gtw_new/app/dealBlack?carnum=&ptype=&username=
     * @param carnum
     * @return
     */
    @POST("app/dealBlack")
    public  Observable<ResponseDataBean<Void>> dealBlack(@Query("carnum")String carnum,@Query("ptype")String ptype,@Query("username")String username);

    /**
     * 上传图片
     * @param photo 图片封装的数据
     * @return 上传图片结果回调试
     */
    @Multipart
    @POST("FileCenter/file/upload.do")
    public Observable<UploadFile> uploadFile(@Part MultipartBody.Part photo);


}
