package com.soarsky.installationmanual.data.remote.server1;



import com.soarsky.installationmanual.bean.AreaItemBean;
import com.soarsky.installationmanual.bean.BrandBean;
import com.soarsky.installationmanual.bean.BrandItemBean;
import com.soarsky.installationmanual.bean.BrandItemNextBean;
import com.soarsky.installationmanual.bean.InPoBean;
import com.soarsky.installationmanual.bean.LoginInfo;
import com.soarsky.installationmanual.bean.ResponseDataBean;
import com.soarsky.installationmanual.bean.VeAdBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
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

    /**
     * 登陆
     * @param username 用户名
     * @param password 密码
     * @return LoginParam
     */
    @GET("login")
    public Observable<ResponseDataBean<LoginInfo>> login(@Query("username") String username, @Query("password") String password);

    /**
     * 发送短信
     * @param phone 电话号码
     * @return
     */
    @GET("sendsms")
    public Observable<ResponseDataBean<Void>> sendsms(@Query("phone") String phone);

    /**
     * 获取地区列表
     * @param code 状态码
     * @param flag 标识
     * @return
     */
    @GET("getAera")
    public Observable<ResponseDataBean<List<AreaItemBean>>> getAera(@Query("code") String code,@Query("flag") String flag);
    //http://192.168.100.52:8080/skm/installApp/register?realName=zse&phone=18665878926&idNo=430624198806184431&vcode=437852&password=123456&conPassword=123456

    /**
     * 注册
     * @param realName 真实姓名
     * @param phone 电话
     * @param idNo 身份证号
     * @param vcode 验证码
     * @param password 密码
     * @param conPassword 确认密码
     * @return
     */
    @GET("register")
    public Observable<ResponseDataBean<Void>> register(@Query("realName") String realName,@Query("phone") String phone,@Query("idNo") String idNo,@Query("vcode") String vcode,@Query("password") String password,@Query("conPassword") String conPassword);
    //http://192.168.100.52:8080/skm/installApp/getBackPwd?vcode=437852&phone=18665878926&idNo=430624198806184431&password=111&conPassword=111

    /**
     * 找回密码
     * @param phone 电话
     * @param idNo 身份证号
     * @param vcode 验证码
     * @param password 密码
     * @param conPassword 确认密码
     * @return
     */
    @GET("getBackPwd")
    public Observable<ResponseDataBean<Void>> getBackPwd(@Query("phone") String phone,@Query("idNo") String idNo,@Query("vcode") String vcode,@Query("password") String password,@Query("conPassword") String conPassword);

    /**
     * 获取车管所
     * @param code 状态码
     * @return
     */
    @GET("getManageCar")
    public Observable<ResponseDataBean<List<VeAdBean>>> getManageCar(@Query("code") String code);

    /**
     * 获取安装点
     * @param code 状态码
     * @return
     */
    @GET("getInstallPoint")
    public Observable<ResponseDataBean<List<InPoBean>>> getInstallPoint(@Query("code") String code);

    //http://192.168.100.52:8080/skm/installApp/perfectUser?sex=&username=&itemCode=&carId=&pointId=

    /**
     * 完善个人资料
     * @param sex 性别
     * @param username 姓名
     * @param itemCode 地区代码
     * @param carId 车管所id
     * @param pointId 安装点id
     * @return
     */
    @GET("perfectUser")
    public Observable<ResponseDataBean<Void>> perfectUser(@Query("sex") String sex,@Query("username") String username,@Query("itemCode") String itemCode,@Query("carId") String carId,@Query("pointId") String pointId);

    /**
     * 修改手机号
     * @param idNo 身份证号
     * @param phone 电话号码
     * @param newPhone 新电话号码
     * @return
     */
    @GET("modifyPhone")
    public Observable<ResponseDataBean<Void>> modifyPhone(@Query("idNo") String idNo,@Query("phone") String phone,@Query("newPhone") String newPhone);

    /**
     * 修改密码
     * @param idNo 身份证号
     * @param password 密码
     * @param conPassword 确认密码
     * @return
     */
    @GET("modifyPwd")
    public Observable<ResponseDataBean<Void>> modifyPwd(@Query("idNo") String idNo,@Query("password") String password,@Query("conPassword") String conPassword);

    /**
     * 获取所有车辆品牌
     * @return
     */
    @GET("getBrands")
    public Observable<ResponseDataBean<List<BrandBean>>> getBrands();

    /**
     * 获取该车辆品牌下面所对应的子系列
     * @param brand 车辆品牌
     * @return
     */
    @GET("getAudis")
    public Observable<ResponseDataBean<List<BrandItemBean>>> getBrandItem(@Query("brand") String brand);

    /**
     * 获取该车辆品牌该子系列下的款式
     * @param brand 车辆品牌
     * @param audi 车辆品牌下的子系列
     * @return
     */
    @GET("getAllParameter")
    public Observable<ResponseDataBean<List<BrandItemNextBean>>> getBrandListNext(@Query("brand") String brand, @Query("audi") String audi);

}
