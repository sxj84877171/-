package com.soarsky.car.ui.violationdeal;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.ViolationDealInfo;

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
 * 程序功能：违章处理接口<br>
 * 该类为 ViolationDealView<br>
 */

public interface ViolationDealView extends BaseView{
    /**
     * 获取违章成功
     * @param param 获取违章信息
     */
    public void showSuccess(ViolationDealInfo param);

    /**
     * 获取违章失败
     */
    public void showError();

}
