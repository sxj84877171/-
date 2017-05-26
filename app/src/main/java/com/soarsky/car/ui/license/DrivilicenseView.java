package com.soarsky.car.ui.license;

import com.soarsky.car.DriviLicenseParam;
import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.DrivingLicenseInfo;
import com.soarsky.car.bean.ResponseDataBean;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：我的驾驶证View<br>
 * 该类为 DrivilicenseView<br>
 */

public interface DrivilicenseView extends BaseView{
    /**
     * 获取驾驶证成功
     * @param param
     */
    public void showSuccess(ResponseDataBean<DrivingLicenseInfo> param);

    /**
     * 获取驾驶证失败
     */
    public void showError();

    /**
     * 初始化数据
     * @param param
     */
    public void initLicenseData(DriviLicenseParam param);

}
