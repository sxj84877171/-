package com.soarsky.car.ui.illegal.electronic;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.CarInfo;
import com.soarsky.car.bean.ResponseDataBean;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/29<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：电子告知回调接口<br>
 * 该类为 IllegalElectronicView<br>
 */


public interface IllegalElectronicView extends BaseView{

    /**
     * 获取机动车成功
     * @param param 参数
     */
    public void showCarSuccess(ResponseDataBean<CarInfo> param);

    /**
     * 获取机动车失败
     */
    public void showCarError();

}
