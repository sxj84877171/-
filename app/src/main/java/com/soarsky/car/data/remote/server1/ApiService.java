package com.soarsky.car.data.remote.server1;

import com.soarsky.car.bean.AutomotiveInfo;
import com.soarsky.car.bean.BlindTermQueryPwdInfo;
import com.soarsky.car.bean.BorrowRecords;
import com.soarsky.car.bean.CarAlarmDataBean;
import com.soarsky.car.bean.CarInfo;
import com.soarsky.car.bean.CarLocationBean;
import com.soarsky.car.bean.CheckBorrowCar;
import com.soarsky.car.bean.CheckVersionBean;
import com.soarsky.car.bean.CompenstateParam;
import com.soarsky.car.bean.DetailsInfo;
import com.soarsky.car.bean.DrivingLicenseInfo;
import com.soarsky.car.bean.DrivingLicenseInformationDataBean;
import com.soarsky.car.bean.FastDissentParam;
import com.soarsky.car.bean.FaultRecordDataBean;
import com.soarsky.car.bean.IlleagalParam;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.LicensePwdBean;
import com.soarsky.car.bean.LoginInfo;
import com.soarsky.car.bean.ModifyPwdInfo;
import com.soarsky.car.bean.ModifyStatusInfo;
import com.soarsky.car.bean.ParamSetting;
import com.soarsky.car.bean.PositionInfo;
import com.soarsky.car.bean.QueryFamilyBean;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ResponsibilityListDataBean;
import com.soarsky.car.bean.ReturnCarInfo;
import com.soarsky.car.bean.RoadSideCarTypeParam;
import com.soarsky.car.bean.RoadSideListDataBean;
import com.soarsky.car.bean.RoadSideListOrderInfo;
import com.soarsky.car.bean.RoadSideRescueInfo;
import com.soarsky.car.bean.SimpleImgBean;
import com.soarsky.car.bean.SoundParamDataBean;
import com.soarsky.car.bean.TerminalInfoParam;
import com.soarsky.car.bean.TravelRecords;
import com.soarsky.car.bean.TroubleInfo;
import com.soarsky.car.bean.UploadFile;
import com.soarsky.car.bean.UseCarRecordParam;
import com.soarsky.car.bean.ViolationDealInfo;
import com.soarsky.car.bean.ViolationQueryInfo;

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

    /**
     * 登陆
     *
     * @param username
     * @param password
     * @return LoginParam
     */
    @GET("app/applogin")
    public Observable<ResponseDataBean<LoginInfo>> login(@Query("username") String username, @Query("password") String password);

    /**
     * 获取所有操作车牌
     *
     * @param phone
     * @return
     */
    @POST("app/getAllCarnum")
    public Observable<ResponseDataBean<List<LicenseInfo>>> getAllCarnum(@Query("phone") String phone);

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param phone
     * @param idNo
     * @param idName
     * @return RegisterParam
     */

    @POST("app/appRegister")
    public Observable<ResponseDataBean> register(@Query("username") String username, @Query("password") String password, @Query("phone") String phone,
                                                 @Query("idNo") String idNo, @Query("idName") String idName, @Query("code") String code);

    /**
     * 忘记密码
     *
     * @param username
     * @param idNo
     * @param phone
     * @return ForgetParam ForgetPwdInfo
     */

    @POST("app/getBackPwd")
    public Observable<ResponseDataBean<String>> forgetPassword(@Query("username") String username, @Query("realName") String realname,@Query("idNo") String idNo, @Query("phone") String phone,@Query("code") String code);

    /**
     * 修改密码
     *
     * @param username
     * @param oldpassword
     * @param newpassword
     * @return ModifyPwdParam
     */

    @POST("app/modPwd")
    public Observable<ResponseDataBean<ModifyPwdInfo>> modifyPassword(@Query("username") String username, @Query("password") String oldpassword, @Query("newPassword") String newpassword);

    /**
     * 获取违章查询信息
     *
     * @param carnum
     * @return ViolationQueryParam
     */

    @POST("app/getIlleagaInfoByCarnum")
    public Observable<ResponseDataBean<ViolationQueryInfo>> violationQuery(@Query("carnum") String carnum);

    /**
     * 获取违章处理信息
     *
     * @param carnum
     * @return ViolationDealParam
     */

    @POST("app/getIlleagaInfoByCarnum")
    public Observable<ResponseDataBean<ViolationDealInfo>> violationDeal(@Query("carnum") String carnum);

    /**
     * 获取我的机动车
     *
     * @param carnum
     * @return CarParam
     */

    @POST("app/getCarInfo")
    public Observable<ResponseDataBean<CarInfo>> getCarInfo(@Query("carnum") String carnum);

    /**
     * 设置亲情号码
     *
     * @param username
     * @param carnum
     * @param phone1
     * @param phone2
     * @param phone3
     * @return FamilyNumParam
     */

    @POST("app/setFirendPhone")
    public Observable<ResponseDataBean> setFirendPhone(@Query("username") String username, @Query("carnum") String carnum,
                                                       @Query("phone1") String phone1, @Query("phone2") String phone2, @Query("phone3") String phone3);

    /**
     * 修改亲情号码
     *
     * @param username
     * @param carnum
     * @param phone
     * @param newPhone
     * @return FamilyNumConfirmParam
     */

    @POST("app/updateFirendPhone")
    public Observable<ResponseDataBean> updateFirendPhone(@Query("username") String username, @Query("carnum") String carnum,
                                                          @Query("phone") String phone, @Query("newPhone") String newPhone);

    /****
     * 获取亲情号码
     *
     * @param username
     * @param carnum
     * @param phone
     * @return
     */
    @POST("app/queryFirendPhone")
    public Observable<ResponseDataBean<QueryFamilyBean>> queryFirendPhone(@Query("username") String username, @Query("carnum") String carnum, @Query("phone") String phone);

    /**
     * 获取我的驾驶证
     *
     * @param idNo
     * @param phone
     * @param verCode
     * @return DriviLicenseParam
     */

    @POST("app/getElecDriver")
    public Observable<ResponseDataBean<DrivingLicenseInfo>> getElecDriver(@Query("idNo") String idNo, @Query("phone") String phone, @Query("verCode") String verCode);

    /**
     * 设置查询密码
     *
     * @param username
     * @param queryPwd
     * @return LicensePwdParam
     */

    @POST("app/setQueryPwd")
    public Observable<ResponseDataBean<LicensePwdBean>> setQueryPwd(@Query("username") String username, @Query("queryPwd") String queryPwd);

    /**
     * 获取防盗报警历史记录
     *
     * @param carnum
     * @return
     */
    @POST("app/getTheftRecords")
    public Observable<ResponseDataBean<List<CarAlarmDataBean>>> getTheftRecords(@Query("carnum") String carnum);

    /**
     * 获取行驶记录
     *
     * @param username
     * @param password
     * @param carnum
     * @param stime
     * @return
     */
    @GET("app/getDriverData")
    public Observable<ResponseDataBean<List<PositionInfo>>> getDriverData(@Query("username") String username, @Query("queryPwd") String password, @Query("carnum") String carnum, @Query("stime") String stime);

    /**
     * 获取车辆当前位置
     *
     * @param carnum
     * @return
     */
    @GET("app/getCarLocation")
    public Observable<ResponseDataBean<CarLocationBean>> getCarLocation(@Query("carnum") String carnum);

    /***
     * 上传违章信息
     *
     * @param carnum
     * @param idNo
     * @param stime
     * @param etime
     * @param lon
     * @param lat
     * @param inf
     * @param dType
     * @return
     */
    @POST("app/appUploadIlleagal")
    public Observable<ResponseDataBean<IlleagalParam>> appUploadIlleagal(@Query("carnum") String carnum, @Query("idNo") String idNo, @Query("stime") String stime
            , @Query("etime") String etime, @Query("lon") String lon, @Query("lat") String lat,
                                                                         @Query("inf") String inf, @Query("dType") String dType);


    /**
     *
     * @param map
     * @return
     */
    @POST("app/appUploadIlleagal")
    public Observable<ResponseDataBean<IlleagalParam>> appUploadIlleagal(@QueryMap Map<String, String> map);


    /**
     * 借车
     *
     * @param carNum
     * @param phoneNum
     * @param startTime
     * @param endTime
     * @param borrowPhone
     * @return
     */
    @GET("app/borrowCar")
    public Observable<ResponseDataBean> borrow(@Query("carnum") String carNum, @Query("phone") String phoneNum,
                                               @Query("stime") String startTime, @Query("etime") String endTime,
                                               @Query("borrowPhone") String borrowPhone);

    /**
     * 检验车牌和手机是否存在
     *
     * @param carNum
     * @param phoneNum
     * @return
     */
    @GET("app/checkBorrowCar")
    public Observable<ResponseDataBean<CheckBorrowCar>> check(@Query("carnum") String carNum, @Query("phone") String phoneNum);


    /**
     * 还车
     *
     * @param bId
     * @param carNum
     * @return
     */
    @GET("app/returnCar")
    public Observable<ResponseDataBean<ReturnCarInfo>> returnCar(@Query("bid") String bId, @Query("carnum") String carNum);


    /**
     * 获取借车与被借车记录
     *
     * @param phone
     * @param userName 车主手机号
     * @return
     */
    @GET("app/queryBorrowList")
    public Observable<ResponseDataBean<List<BorrowRecords>>> borrowRecord(@Query("phone") String phone, @Query("username") String userName);

    /**
     * 车主操作status 1代表审核通过 4 代表作废(拒绝)
     *
     * @param carnum
     * @param bid
     * @param status
     * @param remark
     * @param username
     * @return
     */
    @GET("app/modifyCarStatus")
    public Observable<ResponseDataBean<ModifyStatusInfo>> modifyCarStatus(@Query("carnum") String carnum, @Query("bid") Integer bid,
                                                                          @Query("status") String status, @Query("remark") String remark,
                                                                          @Query("username") String username);

    /**
     * 查询借车详细信息
     *
     * @param bId
     * @return
     */
    @GET("app/queryBorrowRecords")
    public Observable<ResponseDataBean<DetailsInfo>> detail(@Query("bid") Integer bId);

    /**
     * 获取最新版本号
     */
    @POST("app/getVersions")
    public Observable<ResponseDataBean<CheckVersionBean>> checkVersion(@Query("type") String ype);

    /**
     * @return
     */
    @Streaming
    @GET
    public Call<ResponseBody> loadFile(@Url String url);

    /**
     * 上传快赔信息
     * @param stime
     * @param location
     * @param fcarnum
     * @param fidNo
     * @param faffirm
     * @param scarnum
     * @param sidNo
     * @param saffirm
     * @return
     */
    @POST("app/uploadFastDamager")
    public Observable<ResponseDataBean<CompenstateParam>> uploadFastDamager(@Query("stime") String stime, @Query("location") String location, @Query("fcarnum") String fcarnum,
                                                                            @Query("fidNo") String fidNo, @Query("faffirm") String faffirm, @Query("scarnum") String scarnum,
                                                                            @Query("sidNo") String sidNo, @Query("saffirm") String saffirm);

    /**
     * 获取快赔未读记录数
     */
    @POST("app/getUnreadFastCount")
    public Observable<ResponseDataBean<Integer>> getUnreadFastCount(@Query("carnum") String carnum);


    /**
     * 修改快赔记录状态
     */
    @POST("app/modifyFastStatus")
    public Observable<ResponseDataBean<String>> modifyUnreadFast(@Query("carnum") String carnum, @Query("id") int id);

    /**
     * 获取快赔列表
     */
    @POST("app/getFastList")
    public Observable<ResponseDataBean<List<ResponsibilityListDataBean>>> getFastList(@Query("carnum") String carnum);

    /**
     * 单条快赔详细信息
     */
    @POST("app/getFastInfo")
    public Observable<ResponseDataBean<ResponsibilityListDataBean>> getFastInfo(@Query("id") String id);


    /**
     * 上传快照
     *
     * @param map
     * @return
     */
    @POST("app/uploadFastImage")
    public Observable<ResponseDataBean> appUploadFastImage(@QueryMap Map<String, String> map);


    /**
     * 获取样照列表
     *
     * @return
     */
    @POST("app/getFastColumn")
    public Observable<ResponseDataBean<List<SimpleImgBean>>> getSimpleImage();

    /**
     * 提交有异议接口
     */
    @POST("app/fastDissent")
    public Observable<ResponseDataBean<FastDissentParam>> fastDissent(@Query("id") String id, @Query("carnum") String carnum, @Query("reason") String reason, @Query("faffirm") String faffirm,
                                                                      @Query("saffirm") String saffirm);

    /**
     * 获取车辆类型
     */
    @POST("app/getCarType")
    public Observable<ResponseDataBean<List<RoadSideCarTypeParam>>> getCarType();

    /**
     * 上传救援申请
     */
    @POST("app/uploadResouse")
    public Observable<ResponseDataBean<RoadSideRescueInfo>> uploadResouse(@Query("carnum") String carnnum, @Query("ctype") String ctype, @Query("location") String location
            , @Query("phone") String phone, @Query("serverItem") String serverItem);

    /**
     * 获取故障救援列表
     */
    @POST("app/getRescueList")
    public Observable<ResponseDataBean<List<RoadSideListDataBean>>> getRescueList(@Query("carnum") String carnum);

    /**
     * 获取故障救援详细信息
     */
    @POST("app/getRescueById")
    public Observable<ResponseDataBean<RoadSideListOrderInfo>> getRescueById(@Query("id") String id);

    /**
     * 取消故障救援记录
     */
    @POST("app/delRescueById")
    public Observable<ResponseDataBean<Object>> delRescueById(@Query("id") String id);

    /**
     * 上传车辆故障信息
     */
    @POST("app/uploadFault")
    public Observable<ResponseDataBean<TroubleInfo>> sendTrouble(@Query("carnum") String carnum, @Query("stime") String stime, @Query("remark") String remark);

    /**
     * 根据驾驶证获取违章信息
     */
    @POST("app/getIlleagaInfoByIdNo")
    public Observable<ResponseDataBean<ViolationDealInfo>> getIlleagaInfoByIdNo(@Query("idNo") String idNo);

    /**
     * 闪灯找车
     *
     * @param carnum
     * @param lat
     * @return
     */
    @POST("app/findCarByLight")
    public Observable<ResponseDataBean<String>> flashLight(@Query("carnum") String carnum, @Query("lon") String on, @Query("lat") String lat);

    /**
     * 行驶证信息
     *
     * @param idNo
     * @return
     */
    @POST("app/getDriverList")
    public Observable<ResponseDataBean<List<DrivingLicenseInformationDataBean>>> getDriverList(@Query("idNo") String idNo);

 /**
   * 获取违章信息
   */
  @POST("app/getIlleagaInfo")
  public Observable<ResponseDataBean<Object>> getIlleagaInfo(@Query("carnum") String carnum,@Query("qwt") String qwt);

    /**
     * 获取用车记录
     */
    @POST("app/getCarRecoredsList")
    public Observable<ResponseDataBean<List<UseCarRecordParam>>> getCarRecoredsList(@Query("carnum") String carnum);

    /**
     * 获取故障列表
     */
    @POST("app/getFault")
    public Observable<ResponseDataBean<List<FaultRecordDataBean>>> getFault(@Query("carnum") String carnum);

    /**
     * 获取汽车资讯列表
     *
     * @param type
     * @return
     */
    @POST("app/getAutomotiveList")
    public Observable<ResponseDataBean<List<AutomotiveInfo>>> getAutomotiveList(@Query("type") int type);

    /**
     * 根据关键字搜索资讯
     *
     * @param type
     * @param keyword
     * @return
     */
    @POST("app/getAutomotiveList")
    public Observable<ResponseDataBean<List<AutomotiveInfo>>> getAutomotiveList(@Query("type") int type, @Query("keyword") String keyword);

    /**
     * 获取汽车资讯详细信息
     *
     * @param aid
     * @return
     */
    @POST("app/getAutomotiveInfo")
    public Observable<ResponseDataBean<AutomotiveInfo>> getAutomotiveInfo(@Query("aid") int aid);

    /**
     * 根据车牌号获取终端信息
     */
    @POST("app/getTerminalInfo")
    public Observable<ResponseDataBean<TerminalInfoParam>> getTerminalInfo(@Query("carnum") String carnum);

    /**
     * 绑定智能终端
     * @param carnum
     * @param username
     * @param vCode
     * @param phone
     * @param code
     * @param ptype
     * @return
     */
    @POST("app/bindIllegal")
    public Observable<ResponseDataBean<String>> bindIllegal(@Query("carnum") String carnum, @Query("username") String username, @Query("vCode") String vCode,@Query("phone") String phone,
                                                            @Query("code") String code,@Query("ptype") String ptype);

    /**
     * 绑定智能终端
     * @param carnum
     * @param phone
     * @param vin
     * @param vcode
     * @param username
     * @return
     */
    @POST("app/bindTermianl")
    public Observable<ResponseDataBean<String>> bindTermianl(@Query("carnum") String carnum,@Query("phone") String phone,@Query("vin") String vin,@Query("vcode") String vcode,@Query("username") String username);


    /**
     * 获取查询密码
     *
     * @param username
     * @return
     */
    @POST("app/getQueryPwd")
    public Observable<ResponseDataBean<BlindTermQueryPwdInfo>> getQueryPwd(@Query("username") String username);

    @POST("app/modifyAppImage")
    public Observable<ResponseDataBean<String>> modifyAppImage(@Query("imageUrl") String imageUrl, @Query("username") String username);

    /**
     * 上传乘车离车记录
     * flag 0:乘车 1 离车 ptype 号牌类型 0-3
     * http://192.168.100.16:8080/app/uploadCarRecord?username=&stime=
     &ptype=&carnum=&flag=
     */
    @POST("app/uploadCarRecord")
    public Observable<ResponseDataBean<TravelRecords>> uploadCarRecord(@Query("username") String username, @Query("stime") String stime, @Query("ptype") String ptype, @Query("carnum") String carnum, @Query("flag") String flag);

    /**
     * 乘车报警上传
     * atime 报警时间 utime 上传时间
     http://192.168.100.16:8080/app/uploadCarAlarm?username=&utime=
     &ptype=&carnum=&atime=&lon=&lat=
     */
    @POST("app/uploadCarAlarm")
    public Observable<ResponseDataBean<Void>> uploadCarAlarm(@Query("username") String username,@Query("utime") String utime,
                                                       @Query("ptype") String ptype,@Query("carnum") String carnum,
                                                       @Query("atime") String atime,@Query("lon") String lon,@Query("lat") String lat);

    /**
     * 上传违停撤销申请
     * @param carnum
     * @param ptype
     * @param rtime
     * @param stime
     * @param imageUrl
     * @return
     */
    @POST("app/uploadViolationAdvice")
    public Observable<ResponseDataBean<String>> uploadViolationAdvice(@Query("carnum") String carnum,@Query("ptype") String ptype,@Query("rtime") String rtime,
                                                              @Query("stime") String stime,@Query("imageUrl") String imageUrl);



    /**
     * 短信验证
     * @param phone
     * @param ctype  0:伴君行注册 1驾照绑定App 2 终端绑定App
     * @return
     */
    @POST("app/sendsms")
    public Observable<ResponseDataBean<Void>> sendsms(@Query("phone") String phone,@Query("ctype") String ctype);


    @Multipart
    @POST("app/uploadFastImage")
    public Observable<UploadFile> uploadFile(@Part MultipartBody.Part photo);



    @Multipart
    @POST("FileCenter/file/upload.do")
    public Observable<UploadFile> uploadFile2(@Part MultipartBody.Part photo);

    /**
     * 获取智能终端所有语音文本
     * @return  语音内参数
     */
    @POST("app/getAllText")
    public Observable<ResponseDataBean<List<SoundParamDataBean>>> voiceText();

    /**
     * 获取所有参数设置
     * @return
     */
    @POST("app/getAllParameter")
    public Observable<ResponseDataBean<List<ParamSetting>>> param();

    /**
     * 违章劝离超过十分钟记违章
     * @param id 违章id
     * @return
     */
    @POST("app/appViolationAdvice")
    public Observable<ResponseDataBean<Void>> appViolationAdvice(@Query("id") String id);

    /**
     * 询问版本是否能升级
     * @param terminalid
     * @param ver
     * @return
     */
    @POST("app/queryVer")
    public Observable<ResponseDataBean<String>> queryVer(@Query("terminalid") String terminalid,@Query("ver") String ver);

    /**
     * 升级结果告知网关
     * @param terminalid
     * @param result
     * @param err
     * @param ver
     * @param taskId
     * @return
     */
    @POST("app/terminalUpdate")
    public Observable<ResponseDataBean<String>> terminalUpdate(@Query("terminalid") String terminalid,@Query("result") String result,
                                                               @Query("err") String err,@Query("ver") String ver,@Query("taskId") String taskId);

    /**
     * 询问网关是否有升级任务
     * @param carnum
     * @param deviceTocken 设备token
     * @param type 手机类型 1--Android 0--IOS
     * @return
     */
    @POST("app/queryUpdateTask")
    public Observable<ResponseDataBean<String>> queryUpdateTask(@Query("carnum") String carnum,@Query("deviceTocken") String deviceTocken,
                                                                @Query("type") String type);


    @POST("app/getIllegalParking")
    public Observable<ResponseDataBean<ViolationDealInfo>> getIllegalParking(@Query("carnum") String carnum,@Query("platetype") String platetype);
}
