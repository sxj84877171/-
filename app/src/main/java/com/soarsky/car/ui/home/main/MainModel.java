package com.soarsky.car.ui.home.main;

import android.app.Activity;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.CheckVersionBean;
import com.soarsky.car.bean.DrivingLicenseInfo;
import com.soarsky.car.bean.DrivingLicenseInformationDataBean;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.QueryFamilyBean;
import com.soarsky.car.bean.QueryFamilySendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.TerminalInfoParam;
import com.soarsky.car.bean.UploadFile;
import com.soarsky.car.bean.ViolationDealInfo;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.ui.family.FamilyNumDb;
import com.soarsky.car.ui.violationdeal.ViolationDealSendParam;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/1<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  检测版本更新的逻辑层<br>
 */
public class MainModel implements BaseModel {

    public Activity context;


    private FamilyNumDb familyNumDb;

    private ConfirmDriverService confirmDriverService;

    /**
     * 设置文本
     * @param context 文本
     */
    public void setContext(Activity context){

        this.context = context;
        familyNumDb = new FamilyNumDb(context);
        familyNumDb.getFamilyNumDao();
        familyNumDb.getFamilyNumQuery();

    }

    /**
     * 设置服务
     * @param confirmDriverService 确认驾驶员服务
     */
    public  void  setConfirmDriverService(ConfirmDriverService confirmDriverService){
        this.confirmDriverService=confirmDriverService;
    }

    /**
     * 设置连接监听
     * @param listener 连接监听
     */
    public void  setVolumeListener(OnVolumeListener listener){
        if(confirmDriverService != null){
//            confirmDriverService.setVolumeListener(listener);
        }
    }

    /**
     * 连接车辆
     * @param car
     * @param type 申请类型
     */
    public  void connectCar(Car car , int type){
        if(confirmDriverService != null){
            confirmDriverService.connectCar(car,type);
        }
    }


    /**
     * 发送离车请求
     */

    public void sendDriverLeave(){
        if(confirmDriverService != null){
            Car car=new Car();
            car.setDevice(App.getApp().getiBridgeDevice());
            confirmDriverService.byBluetoothSendMeeage(car, Constants.DRIVER_LEAVE);
        }
    }



    /***
     * 获取亲情号码
     * @param param 参数
     * @return ResponseDataBean<QueryFamilyBean> 返回结果参数
     */
    public Observable<ResponseDataBean<QueryFamilyBean>> queryFirendPhone(QueryFamilySendParam param){
        return api.queryFirendPhone(param.getUsername(),param.getCarnum(),param.getPhone()).compose(RxSchedulers.<ResponseDataBean<QueryFamilyBean>>io_main());
    }

    /***
     * 插入亲情号码数据
     * @param familyNum 亲情号
     * @return Observable<FamilyNum> 其结果
     */
    public Observable<FamilyNum> insertFamilyNum(FamilyNum familyNum){
        return familyNumDb.insertData(familyNum);
    }

    /***
     * 清空亲情号表的所有数据
     * @return Observable<Void>
     */
    public Observable<Void> deleteAll(){
        return familyNumDb.deleteAll();
    }

    /**
     * 请求违章处理请求
     * @param param 参数
     * @return Observable<ResponseDataBean<ViolationDealInfo>>返回参数
     */
    public Observable<ResponseDataBean<ViolationDealInfo>> violationDeal(ViolationDealSendParam param){
        return api.violationDeal(param.getCarnum()).compose(RxSchedulers.<ResponseDataBean<ViolationDealInfo>>io_main());
    }

    /***
     * 获取所有操作车牌
     * @param phone 号码
     * @return ResponseDataBean<List<LicenseInfo>> 返回参数
     */
    public Observable<ResponseDataBean<List<LicenseInfo>>> getAllCarnum(String phone){
        return api.getAllCarnum(phone).compose(RxSchedulers.<ResponseDataBean<List<LicenseInfo>>>io_main());
    }

    /**
     * 获取我的驾驶证
     * @param idNo 身份证
     * @param phone 号码
     * @param verCode 验证码
     * @return ResponseDataBean<DrivingLicenseInfo> 返回参数
     */
    public Observable<ResponseDataBean<DrivingLicenseInfo>> getElecDriver(String idNo, String phone, String verCode){
        return api.getElecDriver(idNo,phone,verCode).compose(RxSchedulers.<ResponseDataBean<DrivingLicenseInfo>>io_main());
    }

    /**
     * 根据车牌号获取终端信息
     * @param carnum 车牌
     * @return  ResponseDataBean<TerminalInfoParam>  返回结果参数
     */
    public Observable<ResponseDataBean<TerminalInfoParam>> getTerminalInfo(final String carnum,final int index){
        return api.getTerminalInfo(carnum).compose(RxSchedulers.<ResponseDataBean<TerminalInfoParam>>io_main());
    }

    /**
     * 图片上传
     * @param imgPath 路径
     * Observable<UploadFile> 返回信息参数
     */
    public Observable<UploadFile> upLoadImg(String imgPath) {


        File file = new File(imgPath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("imageUrl", file.getName(), requestFile);
        return api.uploadFile2(body).compose(RxSchedulers.<UploadFile>io_main());

    }

    /**
     * 上传图片信息接口
     * @param imageUrl 路径
     * @param username 用户名
     * @return Observable<ResponseDataBean<String>> 结果参数
     */
    public Observable<ResponseDataBean<String>> modifyAppImage(String imageUrl,String username){
        return api.modifyAppImage(imageUrl,username).compose(RxSchedulers.<ResponseDataBean<String>>io_main());
    }

    /***
     * 获取所有操作车牌
     * @param idNo 身份证
     * @return Observable<ResponseDataBean<List<DrivingLicenseInformationDataBean>>> 返回参数
     */
    public Observable<ResponseDataBean<List<DrivingLicenseInformationDataBean>>> getDriverList(String idNo){
        return api.getDriverList(idNo).compose(RxSchedulers.<ResponseDataBean<List<DrivingLicenseInformationDataBean>>>io_main());
    }


    /**
     * 检测版本号的参数类型
     * @param type ��0:ios 1:andorid 2:���
     * @return 版本信息
     */
    public Observable<ResponseDataBean<CheckVersionBean>> checkVersion(String type){
        return api.checkVersion(type).compose(RxSchedulers.<ResponseDataBean<CheckVersionBean>>io_main());
    }

    /**
     * 下载文件
     * @param url 路径
     * @return 响应体
     */
    public Call<ResponseBody> loadFile(String url){

        return api.loadFile(url);

    }

    /**
     * 询问网关是否有升级任务
     * @param carnum
     * @param deviceTocken
     * @param type
     * @return
     */
    public Observable<ResponseDataBean<String>> queryUpdateTask(String carnum,String deviceTocken,String type){

        return api.queryUpdateTask(carnum,deviceTocken,type).compose(RxSchedulers.<ResponseDataBean<String>>io_main());
    }

}
