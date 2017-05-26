package com.soarsky.policeclient.ui.accident.add;

import com.soarsky.policeclient.base.BaseView;

import java.util.List;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/19<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为新增事故的视图类<br>
 */
public interface AddAccidentView extends BaseView{

    /**
     * 成功
     */
    void onSuccess();
    /**
     * 失败
     */
    void onError();

    /**
     * 获取到位置信息
     * @param s 获取到的位置信息
     */
    void getedPosition(String s);

}
