package com.soarsky.car.ui.licenseinformation;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.DrivingLicenseInformationDataBean;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.ResponseDataBean;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/28<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  行驶证详情页面视图层<br>
 */

public interface DrivingLicenseInformationView extends BaseView {
    /**
     * 网络错误
     * @param e 错误信息
     */
    public void getDriverListFail(Throwable e);

    /**
     * 获取车牌号
     * @param carNumParam 车牌号
     */
    public void getDriverListSuccess(ResponseDataBean<List<DrivingLicenseInformationDataBean>> carNumParam);

    /**
     * 获取车牌号成功回调
     * @param param  行驶证信息
     */
    public void getAllCarnumSuccess(ResponseDataBean<List<LicenseInfo>> param);

    /***
     * 获取车牌号报错的回调
     */
    public void getAllCarnumFail();

}
