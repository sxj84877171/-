package com.soarsky.policeclient.ui.violation;

import com.soarsky.policeclient.base.BaseView;
import com.soarsky.policeclient.bean.Car;

import java.util.List;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 开电子罚单对应的View<br>
 */

public interface ViolationView extends BaseView{
    /**
     * 参见其他View
     * @param list
     */
    public void initCarList(List<Car> list);
    /**
     * 参见其他View
     */
    public void showSuccess();

    /**
     * 参见其他View
     */
    public void showFail();
    /**
     * 参见其他View
     */
    void getedPosition(String s);

}
