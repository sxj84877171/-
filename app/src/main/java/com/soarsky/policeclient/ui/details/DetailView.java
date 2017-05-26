package com.soarsky.policeclient.ui.details;

import com.soarsky.policeclient.base.BaseView;
import com.soarsky.policeclient.bean.CarDetailsDataBean;

/**
 * andriod_police_app<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 车辆详情页面View<br>
 */
public interface DetailView extends BaseView {

    /**
     * 参考其他view类
     * @param detailsCarParam
     */
    public void showData(CarDetailsDataBean detailsCarParam);
    /**
     * 参考其他view类
     * @param msg
     */
    public void showFail(String msg);

}
