
package com.soarsky.policeclient.ui.accident.itemcar;

import com.soarsky.policeclient.base.BaseView;
import com.soarsky.policeclient.bean.AccidentDataBean;

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
 * 该类为事故分析车辆详情视图<br>
 */
public interface AccidentCarDetailView extends BaseView{

    /**
     * 从服务器获取违章数据失败的回调
     */
    void onError();
    /**
     * 服务器没有违章数据的回调
     */
    void onNoData();
    /**
     * 从服务器成功获取到违章数据显示在界面的回调
     */
    void showData(List<AccidentDataBean.AccidentItemBean.AccidentCarBean.WeizhangItem> weizhangItems);

}
