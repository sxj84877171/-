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
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： Elvis
 * 时间： 2017/4/5
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class ApiServiceImpl implements ApiService{
    /**
     * 登陆
     *
     * @param username  用户名
     * @param password  密码
     * @return LoginParam LoginParam
     */
    @Override
    public Observable<ResponseDataBean<LoginInfo>> login(@Query("username") String username, @Query("password") String password) {
        return ApiM.getInstance().getService().login(username,password);
    }

    /**
     * 获取所有操作车牌
     *
     * @param phone  手机号码
     * @return  对应的行驶证信息参数
     */
    @Override
    public Observable<ResponseDataBean<List<LicenseInfo>>> getAllCarnum(@Query("phone") String phone) {
        return ApiM.getInstance().getService().getAllCarnum(phone);
    }

    /**
     * 注册
     *
     * @param username
     * @param password
     * @param phone
     * @param idNo
     * @param idName
     * @param code
     * @return RegisterParam
     */
    @Override
    public Observable<ResponseDataBean> register(@Query("username") String username, @Query("password") String password, @Query("phone") String phone, @Query("idNo") String idNo, @Query("idName") String idName, @Query("code") String code) {
        return ApiM.getInstance().service.register(username, password, phone, idNo, idName, code);
    }

    /**
     * 忘记密码
     *
     * @param username
     * @param idNo
     * @param phone
     * @param code
     * @return ForgetParam ForgetPwdInfo
     */
    @Override
    public Observable<ResponseDataBean<String>> forgetPassword(@Query("username") String username, @Query("realName") String realname,@Query("idNo") String idNo, @Query("phone") String phone, @Query("code") String code) {
        return ApiM.getInstance().service.forgetPassword(username,realname, idNo, phone, code);
    }

    /**
     * 修改密码
     *
     * @param username
     * @param oldpassword
     * @param newpassword
     * @return ModifyPwdParam
     */
    @Override
    public Observable<ResponseDataBean<ModifyPwdInfo>> modifyPassword(@Query("username") String username, @Query("password") String oldpassword, @Query("newPassword") String newpassword) {
        return ApiM.getInstance().service.modifyPassword(username, oldpassword, newpassword);
    }

    /**
     * 获取违章查询信息
     *
     * @param carnum
     * @return ViolationQueryParam
     */
    @Override
    public Observable<ResponseDataBean<ViolationQueryInfo>> violationQuery(@Query("carnum") String carnum) {
        return ApiM.getInstance().service.violationQuery(carnum);
    }

    /**
     * 获取违章处理信息
     *
     * @param carnum
     * @return ViolationDealParam
     */
    @Override
    public Observable<ResponseDataBean<ViolationDealInfo>> violationDeal(@Query("carnum") String carnum) {
        return ApiM.getInstance().service.violationDeal(carnum);
    }

    /**
     * 获取我的机动车
     *
     * @param carnum  车牌号
     * @return CarParam
     */
    @Override
    public Observable<ResponseDataBean<CarInfo>> getCarInfo(@Query("carnum") String carnum) {
        return ApiM.getInstance().service.getCarInfo(carnum);
    }

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
    @Override
    public Observable<ResponseDataBean> setFirendPhone(@Query("username") String username, @Query("carnum") String carnum, @Query("phone1") String phone1, @Query("phone2") String phone2, @Query("phone3") String phone3) {
        return ApiM.getInstance().service.setFirendPhone(username, carnum, phone1, phone2, phone3);
    }

    /**
     * 修改亲情号码
     *
     * @param username
     * @param carnum
     * @param phone
     * @param newPhone
     * @return FamilyNumConfirmParam
     */
    @Override
    public Observable<ResponseDataBean> updateFirendPhone(@Query("username") String username, @Query("carnum") String carnum, @Query("phone") String phone, @Query("newPhone") String newPhone) {
        return ApiM.getInstance().service.updateFirendPhone(username, carnum, phone, newPhone);
    }

    /****
     * 获取亲情号码
     *
     * @param username
     * @param carnum
     * @param phone
     * @return
     */
    @Override
    public Observable<ResponseDataBean<QueryFamilyBean>> queryFirendPhone(@Query("username") String username, @Query("carnum") String carnum, @Query("phone") String phone) {
        return ApiM.getInstance().service.queryFirendPhone(username, carnum, phone);
    }

    /**
     * 获取我的驾驶证
     *
     * @param idNo 身份证号
     * @param phone  电话号码
     * @param verCode  验证码
     * @return DriviLicenseParam ResponseDataBean<DrivingLicenseInfo> 信息
     */
    @Override
    public Observable<ResponseDataBean<DrivingLicenseInfo>> getElecDriver(@Query("idNo") String idNo, @Query("phone") String phone, @Query("verCode") String verCode) {
        return ApiM.getInstance().service.getElecDriver(idNo,phone,verCode);
    }

    /**
     * 设置查询密码
     *
     * @param username  用户名
     * @param queryPwd 查询密码
     * @return ResponseDataBean<LicensePwdBean>
     */
    @Override
    public Observable<ResponseDataBean<LicensePwdBean>> setQueryPwd(@Query("username") String username, @Query("queryPwd") String queryPwd) {
        return ApiM.getInstance().service.setQueryPwd(username, queryPwd);
    }

    /**
     * 获取防盗报警历史记录
     *
     * @param carnum
     * @return
     */
    @Override
    public Observable<ResponseDataBean<List<CarAlarmDataBean>>> getTheftRecords(@Query("carnum") String carnum) {
        return ApiM.getInstance().service.getTheftRecords(carnum);
    }

    /**
     * 获取行驶记录
     *
     * @param username
     * @param password
     * @param carnum
     * @param stime
     * @return
     */
    @Override
    public Observable<ResponseDataBean<List<PositionInfo>>> getDriverData(@Query("username") String username, @Query("queryPwd") String password, @Query("carnum") String carnum, @Query("stime") String stime) {
        return ApiM.getInstance().service.getDriverData(username, password, carnum, stime);
    }

    /**
     * 获取车辆当前位置
     *
     * @param carnum
     * @return
     */
    @Override
    public Observable<ResponseDataBean<CarLocationBean>> getCarLocation(@Query("carnum") String carnum) {
        return ApiM.getInstance().service.getCarLocation(carnum);
    }

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
    @Override
    public Observable<ResponseDataBean<IlleagalParam>> appUploadIlleagal(@Query("carnum") String carnum, @Query("idNo") String idNo, @Query("stime") String stime, @Query("etime") String etime, @Query("lon") String lon, @Query("lat") String lat, @Query("inf") String inf, @Query("dType") String dType) {
        return ApiM.getInstance().service.appUploadIlleagal(carnum, idNo, stime, etime, lon, lat, inf, dType);
    }

    /**
     * @param map
     * @return
     */
    @Override
    public Observable<ResponseDataBean<IlleagalParam>> appUploadIlleagal(@QueryMap Map<String, String> map) {
        return ApiM.getInstance().service.appUploadIlleagal(map);
    }

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
    @Override
    public Observable<ResponseDataBean> borrow(@Query("carnum") String carNum, @Query("phone") String phoneNum, @Query("stime") String startTime, @Query("etime") String endTime, @Query("borrowPhone") String borrowPhone) {
        return ApiM.getInstance().service.borrow(carNum, phoneNum, startTime, endTime, borrowPhone);
    }

    /**
     * 检验车牌和手机是否存在
     *
     * @param carNum
     * @param phoneNum
     * @return
     */
    @Override
    public Observable<ResponseDataBean<CheckBorrowCar>> check(@Query("carnum") String carNum, @Query("phone") String phoneNum) {
        return ApiM.getInstance().service.check(carNum, phoneNum);
    }

    /**
     * 还车
     *
     * @param bId
     * @param carNum
     * @return
     */
    @Override
    public Observable<ResponseDataBean<ReturnCarInfo>> returnCar(@Query("bid") String bId, @Query("carnum") String carNum) {
        return ApiM.getInstance().service.returnCar(bId, carNum);
    }

    /**
     * 获取借车与被借车记录
     *
     * @param phone
     * @param userName 车主手机号
     * @return
     */
    @Override
    public Observable<ResponseDataBean<List<BorrowRecords>>> borrowRecord(@Query("phone") String phone, @Query("username") String userName) {
        return ApiM.getInstance().service.borrowRecord(phone, userName);
    }

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
    @Override
    public Observable<ResponseDataBean<ModifyStatusInfo>> modifyCarStatus(@Query("carnum") String carnum, @Query("bid") Integer bid, @Query("status") String status, @Query("remark") String remark, @Query("username") String username) {
        return ApiM.getInstance().service.modifyCarStatus(carnum, bid, status, remark, username);
    }

    /**
     * 查询借车详细信息
     *
     * @param bId
     * @return
     */
    @Override
    public Observable<ResponseDataBean<DetailsInfo>> detail(@Query("bid") Integer bId) {
        return ApiM.getInstance().service.detail(bId);
    }

    /**
     * 获取最新版本号
     *
     * @param ype
     */
    @Override
    public Observable<ResponseDataBean<CheckVersionBean>> checkVersion(@Query("type") String ype) {
        return ApiM.getInstance().service.checkVersion(ype);
    }

    /**
     * @param url
     * @return
     */
    @Override
    public Call<ResponseBody> loadFile(@Url String url) {
        return ApiM.getInstance().service.loadFile(url);
    }

    /**
     * 上传快赔信息
     *
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
    @Override
    public Observable<ResponseDataBean<CompenstateParam>> uploadFastDamager(@Query("stime") String stime, @Query("location") String location, @Query("fcarnum") String fcarnum, @Query("fidNo") String fidNo, @Query("faffirm") String faffirm, @Query("scarnum") String scarnum, @Query("sidNo") String sidNo, @Query("saffirm") String saffirm) {
        return ApiM.getInstance().service.uploadFastDamager(stime, location, fcarnum, fidNo, faffirm, scarnum, sidNo, saffirm);
    }

    /**
     * 获取快赔未读记录数
     *
     * @param carnum
     */
    @Override
    public Observable<ResponseDataBean<Integer>> getUnreadFastCount(@Query("carnum") String carnum) {
        return ApiM.getInstance().service.getUnreadFastCount(carnum);
    }

    /**
     * 修改快赔记录状态
     *
     * @param carnum
     * @param id
     */
    @Override
    public Observable<ResponseDataBean<String>> modifyUnreadFast(@Query("carnum") String carnum, @Query("id") int id) {
        return ApiM.getInstance().service.modifyUnreadFast(carnum, id);
    }

    /**
     * 获取快赔列表
     *
     * @param carnum
     */
    @Override
    public Observable<ResponseDataBean<List<ResponsibilityListDataBean>>> getFastList(@Query("carnum") String carnum) {
        return ApiM.getInstance().service.getFastList(carnum);
    }

    /**
     * 单条快赔详细信息
     *
     * @param id
     */
    @Override
    public Observable<ResponseDataBean<ResponsibilityListDataBean>> getFastInfo(@Query("id") String id) {
        return ApiM.getInstance().service.getFastInfo(id);
    }

    /**
     * 上传快照
     *
     * @param map
     * @return
     */
    @Override
    public Observable<ResponseDataBean> appUploadFastImage(@QueryMap Map<String, String> map) {
        return ApiM.getInstance().service.appUploadFastImage(map);
    }

    /**
     * 获取样照列表
     *
     * @return
     */
    @Override
    public Observable<ResponseDataBean<List<SimpleImgBean>>> getSimpleImage() {
        return ApiM.getInstance().service.getSimpleImage();
    }

    /**
     * 提交有异议接口
     *
     * @param id
     * @param carnum
     * @param reason
     * @param faffirm
     * @param saffirm
     */
    @Override
    public Observable<ResponseDataBean<FastDissentParam>> fastDissent(@Query("id") String id, @Query("carnum") String carnum, @Query("reason") String reason, @Query("faffirm") String faffirm, @Query("saffirm") String saffirm) {
        return ApiM.getInstance().service.fastDissent(id, carnum, reason, faffirm, saffirm);
    }

    /**
     * 获取车辆类型
     */
    @Override
    public Observable<ResponseDataBean<List<RoadSideCarTypeParam>>> getCarType() {
        return ApiM.getInstance().service.getCarType();
    }

    /**
     * 上传救援申请
     *
     * @param carnnum
     * @param ctype
     * @param location
     * @param phone
     * @param serverItem
     */
    @Override
    public Observable<ResponseDataBean<RoadSideRescueInfo>> uploadResouse(@Query("carnum") String carnnum, @Query("ctype") String ctype, @Query("location") String location, @Query("phone") String phone, @Query("serverItem") String serverItem) {
        return ApiM.getInstance().service.uploadResouse(carnnum, ctype, location, phone, serverItem);
    }

    /**
     * 获取故障救援列表
     *
     * @param carnum
     */
    @Override
    public Observable<ResponseDataBean<List<RoadSideListDataBean>>> getRescueList(@Query("carnum") String carnum) {
        return ApiM.getInstance().service.getRescueList(carnum);
    }

    /**
     * 获取故障救援详细信息
     *
     * @param id
     */
    @Override
    public Observable<ResponseDataBean<RoadSideListOrderInfo>> getRescueById(@Query("id") String id) {
        return ApiM.getInstance().service.getRescueById(id);
    }

    /**
     * 取消故障救援记录
     *
     * @param id
     */
    @Override
    public Observable<ResponseDataBean<Object>> delRescueById(@Query("id") String id) {
        return ApiM.getInstance().service.delRescueById(id);
    }

    /**
     * 上传车辆故障信息
     *
     * @param carnum
     * @param stime
     * @param remark
     */
    @Override
    public Observable<ResponseDataBean<TroubleInfo>> sendTrouble(@Query("carnum") String carnum, @Query("stime") String stime, @Query("remark") String remark) {
        return ApiM.getInstance().service.sendTrouble(carnum,stime,remark);
    }

    /**
     * 根据驾驶证获取违章信息
     *
     * @param idNo
     */
    @Override
    public Observable<ResponseDataBean<ViolationDealInfo>> getIlleagaInfoByIdNo(@Query("idNo") String idNo) {
        return ApiM.getInstance().service.getIlleagaInfoByIdNo(idNo);
    }

    /**
     * 闪灯找车
     *
     * @param carnum
     * @param on
     * @param lat    @return
     */
    @Override
    public Observable<ResponseDataBean<String>> flashLight(@Query("carnum") String carnum, @Query("lon") String on, @Query("lat") String lat) {
        return ApiG.getInstance().service.flashLight(carnum,on,lat);
    }

    /**
     * 行驶证信息
     *
     * @param idNo
     * @return
     */
    @Override
    public Observable<ResponseDataBean<List<DrivingLicenseInformationDataBean>>> getDriverList(@Query("idNo") String idNo) {
        return ApiM.getInstance().service.getDriverList(idNo);
    }

    /**
     * 获取违章信息
     *
     * @param carnum
     * @param qwt
     */
    @Override
    public Observable<ResponseDataBean<Object>> getIlleagaInfo(@Query("carnum") String carnum, @Query("qwt") String qwt) {
        return ApiM.getInstance().service.getIlleagaInfo(carnum,qwt);
    }

    /**
     * 获取用车记录
     *
     * @param carnum
     */
    @Override
    public Observable<ResponseDataBean<List<UseCarRecordParam>>> getCarRecoredsList(@Query("carnum") String carnum) {
        return ApiM.getInstance().service.getCarRecoredsList(carnum);
    }

    /**
     * 获取故障列表
     *
     * @param carnum
     */
    @Override
    public Observable<ResponseDataBean<List<FaultRecordDataBean>>> getFault(@Query("carnum") String carnum) {
        return ApiM.getInstance().service.getFault(carnum);
    }

    /**
     * 获取汽车资讯列表
     *
     * @param type
     * @return
     */
    @Override
    public Observable<ResponseDataBean<List<AutomotiveInfo>>> getAutomotiveList(@Query("type") int type) {
        return ApiM.getInstance().service.getAutomotiveList(type);
    }

    /**
     * 根据关键字搜索资讯
     *
     * @param type
     * @param keyword
     * @return
     */
    @Override
    public Observable<ResponseDataBean<List<AutomotiveInfo>>> getAutomotiveList(@Query("type") int type, @Query("keyword") String keyword) {
        return ApiM.getInstance().service.getAutomotiveList(type, keyword);
    }

    /**
     * 获取汽车资讯详细信息
     *
     * @param aid
     * @return
     */
    @Override
    public Observable<ResponseDataBean<AutomotiveInfo>> getAutomotiveInfo(@Query("aid") int aid) {
        return ApiM.getInstance().service.getAutomotiveInfo(aid);
    }

    /**
     * 根据车牌号获取终端信息
     *
     * @param carnum
     */
    @Override
    public Observable<ResponseDataBean<TerminalInfoParam>> getTerminalInfo(@Query("carnum") String carnum) {
        return ApiM.getInstance().service.getTerminalInfo(carnum);
    }

    /**
     * 绑定智能终端
     *
     * @param carnum 车牌号
     * @param username 用户名
     * @param vCode 验证码
     * @param phone 手机号码
     * @param code
     * @param ptype
     * @return
     */
    @Override
    public Observable<ResponseDataBean<String>> bindIllegal(@Query("carnum") String carnum, @Query("username") String username, @Query("vCode") String vCode, @Query("phone") String phone, @Query("code") String code, @Query("ptype") String ptype) {
        return ApiM.getInstance().service.bindIllegal(carnum, username, vCode, phone, code, ptype);
    }

    /**
     * 获取查询密码
     *
     * @param username
     * @return
     */
    @Override
    public Observable<ResponseDataBean<BlindTermQueryPwdInfo>> getQueryPwd(@Query("username") String username) {
        return null;
    }

    @Override
    public Observable<ResponseDataBean<String>> modifyAppImage(@Query("imageUrl") String imageUrl, @Query("username") String username) {
        return ApiM.getInstance().service.modifyAppImage(imageUrl, username);
    }

    /**
     * 上传乘车离车记录
     * flag 0:乘车 1 离车 ptype 号牌类型 0-3
     * http://192.168.100.16:8080/app/uploadCarRecord?username=&stime=
     * &ptype=&carnum=&flag=
     *
     * @param username
     * @param stime
     * @param ptype
     * @param carnum
     * @param flag
     */
    @Override
    public Observable<ResponseDataBean<TravelRecords>> uploadCarRecord(@Query("username") String username, @Query("stime") String stime, @Query("ptype") String ptype, @Query("carnum") String carnum, @Query("flag") String flag) {
        return null;
    }

    /**
     * 乘车报警上传
     * atime 报警时间 utime 上传时间
     * http://192.168.100.16:8080/app/uploadCarAlarm?username=&utime=
     * &ptype=&carnum=&atime=&lon=&lat=
     *
     * @param username
     * @param utime
     * @param ptype
     * @param carnum
     * @param atime
     * @param lon
     * @param lat
     */
    @Override
    public Observable<ResponseDataBean<Void>> uploadCarAlarm(@Query("username") String username, @Query("utime") String utime, @Query("ptype") String ptype, @Query("carnum") String carnum, @Query("atime") String atime, @Query("lon") String lon, @Query("lat") String lat) {
        return null;
    }

    /**
     * 上传违停撤销申请
     *
     * @param carnum
     * @param ptype
     * @param rtime
     * @param stime
     * @param imageUrl
     * @return
     */
    @Override
    public Observable<ResponseDataBean<String>> uploadViolationAdvice(@Query("carnum") String carnum, @Query("ptype") String ptype, @Query("rtime") String rtime, @Query("stime") String stime, @Query("imageUrl") String imageUrl) {
        return ApiM.getInstance().service.uploadViolationAdvice(carnum, ptype, rtime, stime, imageUrl);
    }

    /**
     * 短信验证
     *
     * @param phone
     * @param ctype 0:伴君行注册 1驾照绑定App 2 终端绑定App
     * @return
     */
    @Override
    public Observable<ResponseDataBean<Void>> sendsms(@Query("phone") String phone, @Query("ctype") String ctype) {
        return ApiM.getInstance().service.sendsms(phone, ctype);
    }

    @Override
    public Observable<UploadFile> uploadFile(@Part MultipartBody.Part photo) {
        return ApiF.getInstance().getService().uploadFile(photo);
    }

    @Override
    public Observable<UploadFile> uploadFile2(@Part MultipartBody.Part photo) {
        return ApiF.getInstance().getService().uploadFile2(photo);
    }

    /**
     * 获取智能终端所有语音文本
     * @return
     */
    @Override
    public Observable<ResponseDataBean<List<SoundParamDataBean>>> voiceText() {
        return ApiM.getInstance().service.voiceText();
    }

    /**
     * 获取所有参数设置
     * @return
     */
    @Override
    public Observable<ResponseDataBean<List<ParamSetting>>> param() {
        return ApiM.getInstance().service.param();
    }

    /**
     * 违章劝离超过十分钟
     * @param id 违章id
     * @return
     */
    @Override
    public Observable<ResponseDataBean<Void>> appViolationAdvice(@Query("id") String id) {
        return ApiM.getInstance().service.appViolationAdvice(id);
    }

    /**
     * 绑定智能终端
     * @param carnum
     * @param phone
     * @param vin
     * @param vcode
     * @param username
     * @return
     */
    @Override
    public Observable<ResponseDataBean<String>> bindTermianl(@Query("carnum") String carnum, @Query("phone") String phone, @Query("vin") String vin, @Query("vcode") String vcode, @Query("username") String username) {
        return ApiM.getInstance().service.bindTermianl(carnum,phone,vin,vcode,username);
    }

    @Override
    public Observable<ResponseDataBean<String>> queryVer(@Query("carnum") String carnum, @Query("ver") String ver) {
        return ApiG.getInstance().service.queryVer(carnum,ver);
    }

    @Override
    public Observable<ResponseDataBean<String>> terminalUpdate(@Query("carnum") String carnum, @Query("result") String result, @Query("err") String err, @Query("ver") String ver, @Query("taskId") String taskId) {
        return ApiG.getInstance().service.terminalUpdate(carnum,result,err,ver,taskId);
    }

    @Override
    public Observable<ResponseDataBean<String>> queryUpdateTask(@Query("carnum") String carnum, @Query("deviceTocken") String deviceTocken, @Query("type") String type) {
        return ApiG.getInstance().service.queryUpdateTask(carnum,deviceTocken,type);
    }

    @Override
    public Observable<ResponseDataBean<ViolationDealInfo>> getIllegalParking(@Query("carnum") String carnum, @Query("platetype") String platetype) {
        return ApiM.getInstance().service.getIllegalParking(carnum,platetype);
    }
}
