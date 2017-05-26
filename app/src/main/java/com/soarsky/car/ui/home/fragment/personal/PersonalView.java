package com.soarsky.car.ui.home.fragment.personal;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.QueryFamilyBean;
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
 * 程序功能：个人中心view<br>
 * 该类为 PersonalView<br>
 */

public interface PersonalView extends BaseView{
    /**
     * 获取亲情数据成功
     * @param param 亲情号
     */
    public void showSuccess(ResponseDataBean<QueryFamilyBean> param);

    /**
     * 获取亲情数据失败
     */
    public void showFail();

    /**
     * 删除所有数据成功
     */
    public void deleteAllSuccess();

    /**
     * 删除所有数据失败
     */
    public void deleteAllFail();
}
