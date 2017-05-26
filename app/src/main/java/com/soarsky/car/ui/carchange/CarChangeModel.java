package com.soarsky.car.ui.carchange;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.DrivingLicenseInformationDataBean;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.TerminalInfoParam;
import com.soarsky.car.helper.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：切换车牌的model<br>
 * 该类为 CarChangeModel <br>
 */


public class CarChangeModel implements BaseModel{

    /***
     * 获取所有操作车牌
     * @param phone 车主号码
     * @return ResponseDataBean<List<LicenseInfo> 驾驶信息参数
     */
    public Observable<ResponseDataBean<List<LicenseInfo>>> getAllCarnum(String phone){
        return api.getAllCarnum(phone).compose(RxSchedulers.<ResponseDataBean<List<LicenseInfo>>>io_main());
    }

    /**
     * 根据车牌号获取终端信息
     * @param carnum 车牌号
     * @return ResponseDataBean<TerminalInfoParam>  终端信息
     */
    public Observable<ResponseDataBean<TerminalInfoParam>> getTerminalInfo(String carnum){
        return api.getTerminalInfo(carnum).compose(RxSchedulers.<ResponseDataBean<TerminalInfoParam>>io_main());
    }

    /***
     * 获取所有操作车牌
     * @param idNo 身份证
     * @return Observable<ResponseDataBean<List<DrivingLicenseInformationDataBean>>> 返回参数
     */
    public Observable<ResponseDataBean<List<DrivingLicenseInformationDataBean>>> getDriverList(String idNo){
        return api.getDriverList(idNo).compose(RxSchedulers.<ResponseDataBean<List<DrivingLicenseInformationDataBean>>>io_main());
    }

}
