package com.soarsky.car.data.remote.server1;

import com.soarsky.car.bean.SimpleImg;
import com.soarsky.car.bean.UploadFast;
import com.soarsky.car.ui.borrowandreturn.ModifyStatusParm;
import com.soarsky.car.ui.borrowandreturn.borrow.BorrowParm;
import com.soarsky.car.ui.borrowandreturn.borrow.CheckBorrowCar;
import com.soarsky.car.ui.borrowandreturn.borrowrecord.BorrowRecordParm;
import com.soarsky.car.ui.borrowandreturn.recorddetails.DetailsParm;
import com.soarsky.car.ui.borrowandreturn.retur.ReturnCarParm;
import com.soarsky.car.ui.car.CarParam;
import com.soarsky.car.ui.carlocation.alarm.CarAlarmParam;
import com.soarsky.car.ui.carlocation.lovecar.CarLocationBean;
import com.soarsky.car.ui.danger.compen.CompenstateParam;
import com.soarsky.car.ui.danger.responsibility.ResponsibilityListParam;
import com.soarsky.car.ui.danger.responsibilitydetails.haveobjection.FastDissentParam;
import com.soarsky.car.ui.danger.responsibilitydetails.ResponsibilityDetailsParam;
import com.soarsky.car.ui.danger.start.DangerStartParam;
import com.soarsky.car.ui.drivrecord.map.PositionData;
import com.soarsky.car.ui.family.familynum.FamilyNumParam;
import com.soarsky.car.ui.family.familynumconfirm.FamilyNumConfirmParam;
import com.soarsky.car.ui.forget.ForgetParam;
import com.soarsky.car.ui.home.fragment.personal.QueryFamilyParam;
import com.soarsky.car.ui.home.view.CheckVersion;
import com.soarsky.car.ui.license.DriviLicenseParam;
import com.soarsky.car.ui.license.pwd.LicensePwdParam;
import com.soarsky.car.ui.login.CarNumParam;
import com.soarsky.car.ui.login.LoginParam;
import com.soarsky.car.ui.modifypwd.ModifyPwdParam;
import com.soarsky.car.ui.register.RegisterParam;
import com.soarsky.car.ui.roadside.list.RoadSideListParam;
import com.soarsky.car.ui.roadside.list.order.RoadSideListOrderDelRescueParam;
import com.soarsky.car.ui.roadside.list.order.RoadSideListOrderParam;
import com.soarsky.car.ui.roadside.rescue.RoadSideCarTypeParam;
import com.soarsky.car.ui.roadside.rescue.RoadSideRescueParam;
import com.soarsky.car.ui.ticketed.IlleagalParam;
import com.soarsky.car.ui.violationdeal.ViolationDealParam;
import com.soarsky.car.ui.violationquery.ViolationQueryParam;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
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

  /**
   * 登陆
   * @param username
   * @param password
   * @return LoginParam
     */
  @GET("gtw/app/applogin")
  public Observable<LoginParam> login(@Query("username") String username, @Query("password") String password);

  /**
   * 获取所有操作车牌
   * @param phone
     * @return
     */
  @POST("gtw/app/getAllCarnum")
  public Observable<CarNumParam> getAllCarnum(@Query("phone") String phone);

  /**
   * 注册
   * @param username
   * @param password
   * @param phone
   * @param idNo
   * @param idName
     * @return  RegisterParam
     */

  @POST("gtw/app/appRegister")
  public  Observable<RegisterParam> register(@Query("username") String username, @Query("password") String password,@Query("phone") String phone,
                                              @Query("idNo") String idNo,@Query("idName") String idName);

  /**
   * 忘记密码
   * @param username
   * @param idNo
   * @param phone
     * @return ForgetParam
     */

  @POST("gtw/app/getBackPwd")
  public  Observable<ForgetParam> forgetPassword(@Query("username")String username, @Query("idNo") String idNo, @Query("phone") String phone);

  /**
   * 修改密码
   * @param username
   * @param oldpassword
   * @param newpassword
     * @return ModifyPwdParam
     */

  @POST("gtw/app/modPwd")
  public  Observable<ModifyPwdParam> modifyPassword(@Query("username")String username, @Query("password") String oldpassword, @Query("newPassword") String newpassword);

  /**
   * 获取违章查询信息
   * @param carnum
   * @return ViolationQueryParam
     */

  @POST("gtw/app/getIlleagaInfo")
  public  Observable<ViolationQueryParam> violationQuery(@Query("carnum")String carnum);

  /**
   * 获取违章处理信息
   * @param carnum
   * @return ViolationDealParam
     */

  @POST("gtw/app/getIlleagaInfo")
  public  Observable<ViolationDealParam> violationDeal(@Query("carnum")String carnum);

  /**
   *  获取我的机动车
   * @param carnum
   * @return CarParam
     */

  @POST("gtw/app/getCarInfo")
  public Observable<CarParam> getCarInfo(@Query("carnum")String carnum);

  /**
   * 设置亲情号码
   * @param username
   * @param carnum
   * @param phone1
   * @param phone2
   * @param phone3
     * @return FamilyNumParam
     */

  @POST("gtw/app/setFirendPhone")
  public Observable<FamilyNumParam> setFirendPhone(@Query("username")String username,@Query("carnum")String carnum,
                                                   @Query("phone1")String phone1,@Query("phone2")String phone2,@Query("phone3")String phone3);

  /**
   * 修改亲情号码
   * @param username
   * @param carnum
   * @param phone
   * @param newPhone
     * @return FamilyNumConfirmParam
     */

  @POST("gtw/app/updateFirendPhone")
  public Observable<FamilyNumConfirmParam> updateFirendPhone(@Query("username")String username,@Query("carnum")String carnum,
                                                             @Query("phone")String phone,@Query("newPhone")String newPhone);

  /****
   * 获取亲情号码
   * @param username
   * @param carnum
   * @param phone
     * @return
     */
  @POST("gtw/app/queryFirendPhone")
  public Observable<QueryFamilyParam> queryFirendPhone(@Query("username")String username,@Query("carnum")String carnum,@Query("phone")String phone);

  /**
   * 获取我的驾驶证
   * @param idNo
   * @param phone
   * @param verCode
     * @return DriviLicenseParam
     */

  @POST("gtw/app/getElecDriver")
  public Observable<DriviLicenseParam> getElecDriver(@Query("idNo")String idNo,@Query("phone")String phone,@Query("verCode")String verCode);

  /**
   * 设置查询密码
   * @param username
   * @param queryPwd
   * @return LicensePwdParam
     */

  @POST("gtw/app/setQueryPwd")
  public Observable<LicensePwdParam> setQueryPwd(@Query("username")String username,@Query("queryPwd")String queryPwd);

  /**
   * 获取防盗报警历史记录
   * @param carnum
   * @return
     */
  @POST("gtw/app/getTheftRecords")
  public Observable<CarAlarmParam> getTheftRecords(@Query("carnum")String carnum);

  /**
   *获取行驶记录
   * @param username
   * @param password
   * @param carnum
   * @param stime
     * @return
     */
  @GET("gtw/app/getDriverData")
  public Observable<PositionData> getDriverData(@Query("username") String username, @Query("queryPwd") String password,@Query("carnum") String carnum,@Query("stime") String stime);

  /**
   * 获取车辆当前位置
   * @param carnum
     * @return
     */
  @GET("gtw/app/getCarLocation")
  public Observable<CarLocationBean> getCarLocation(@Query("carnum")String carnum);

  /***
   * 上传违章信息
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
  @POST("gtw/app/appUploadIlleagal")
  public Observable<IlleagalParam> appUploadIlleagal(@Query("carnum")String carnum,@Query("idNo")String idNo,@Query("stime")String stime
                                                     ,@Query("etime")String etime,@Query("lon")String lon,@Query("lat")String lat,
                                                     @Query("inf")String inf,@Query("dType")String dType);



  @POST("gtw/app/appUploadIlleagal")
  public Observable<IlleagalParam> appUploadIlleagal(@QueryMap Map<String, String> map);


    /**
     * 借车
     * @param carNum
     * @param phoneNum
     * @param startTime
     * @param endTime
     * @param borrowPhone
     * @return
     */
  @GET("gtw/app/borrowCar")
  public Observable<BorrowParm> borrow(@Query("carnum") String carNum, @Query("phone") String phoneNum,
                                       @Query("stime") String startTime, @Query("etime") String endTime,
                                       @Query("borrowPhone") String borrowPhone);

  /**
   * 检验车牌和手机是否存在
   * @param carNum
   * @param phoneNum
   * @return
     */
    @GET("gtw/app/checkBorrowCar")
public Observable<CheckBorrowCar> check(@Query("carnum") String carNum, @Query("phone") String phoneNum);


  /**
   * 还车
   * @param bId
   * @param carNum
   * @return
     */
  @GET("gtw/app/returnCar")
  public  Observable<ReturnCarParm> returnCar(@Query("bid") String bId,@Query("carnum") String carNum);


  /**
   * 获取借车与被借车记录
   * @param phone
   * @param userName 车主手机号
   * @return
     */
  @GET("gtw/app/queryBorrowList")
  public Observable<BorrowRecordParm> borrowRecord(@Query("phone") String phone,@Query("username") String userName);

  /**
   *车主操作status 1代表审核通过 4 代表作废(拒绝)
   * @param carnum
   * @param bid
   * @param status
   * @param remark
   * @param username
     * @return
     */
  @GET("gtw/app/modifyCarStatus")
  public Observable<ModifyStatusParm> modifyCarStatus(@Query("carnum")String carnum, @Query("bid")Integer bid,
                                                      @Query("status")String status, @Query("remark")String remark,
                                                      @Query("username")String username);

  /**
   * 查询借车详细信息
   * @param bId
   * @return
     */
  @GET("gtw/app/queryBorrowRecords")
  public Observable<DetailsParm> detail(@Query("bid")Integer bId);

  /**
   * 获取最新版本号
   */
  @POST("gtw/app/getVersions")
  public Observable<CheckVersion> checkVersion(@Query("type") String ype);

  /**
   *
   * @return
   */
  @Streaming
  @GET
  public Call<ResponseBody> loadFile(@Url String url);

  /**
   * 上传快赔信息
   */
  @POST("gtw/app/uploadFastDamager")
  public Observable<CompenstateParam> uploadFastDamager(@Query("stime") String stime, @Query("location") String location, @Query("fcarnum") String fcarnum,
                                                        @Query("fidNo") String fidNo, @Query("faffirm") String faffirm, @Query("scarnum") String scarnum,
                                                        @Query("sidNo") String sidNo, @Query("saffirm") String saffirm);

  /**
   * 获取快赔未读记录数
   */
  @POST("gtw/app/getUnreadFastCount")
  public Observable<DangerStartParam> getUnreadFastCount(@Query("carnum") String carnum);

   /**
   * 获取快赔列表
   */
  @POST("gtw/app/getFastList")
  public Observable<ResponsibilityListParam> getFastList(@Query("carnum") String carnum);

  /**
   * 单条快赔详细信息
   */
  @POST("gtw/app/getFastInfo")
  public Observable<ResponsibilityDetailsParam> getFastInfo(@Query("id") String id);


  /**
   * 上传快照
   * @param map
   * @return
     */
  @POST("gtw/app/uploadFastImage")
  public Observable<UploadFast> appUploadFastImage(@QueryMap Map<String, String> map);


  /**
   * 获取样照列表

   * @return
   */
  @POST("gtw/app/getFastColumn")
  public Observable<SimpleImg> getSimpleImage();

  /**
   * 提交有异议接口
   */
  @POST("gtw/app/fastDissent")
  public Observable<FastDissentParam> fastDissent(@Query("id") String id,@Query("carnum") String carnum,@Query("reason") String reason,@Query("faffirm") String faffirm,
                                                  @Query("saffirm") String saffirm);

  /**
   *获取车辆类型
   */
  @POST("gtw/app/getCarType")
  public Observable<RoadSideCarTypeParam> getCarType();

  /**
   *上传救援申请
   */
  @POST("gtw/app/uploadResouse")
  public Observable<RoadSideRescueParam> uploadResouse(@Query("carnum") String carnnum,@Query("ctype") String ctype,@Query("location") String location
                                                       ,@Query("phone") String phone,@Query("serverItem") String serverItem);

  /**
   * 获取故障救援列表
   */
  @POST("gtw/app/getRescueList")
  public Observable<RoadSideListParam> getRescueList(@Query("carnum") String carnum);

  /**
   * 获取故障救援详细信息
   */
  @POST("gtw/app/getRescueById")
  public Observable<RoadSideListOrderParam> getRescueById(@Query("id") String id);

  /**
   * 取消故障救援记录
   */
  @POST("gtw/app/delRescueById")
  public Observable<RoadSideListOrderDelRescueParam> delRescueById(@Query("id") String id);


}
