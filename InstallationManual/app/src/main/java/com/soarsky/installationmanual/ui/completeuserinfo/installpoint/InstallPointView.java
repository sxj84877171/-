package com.soarsky.installationmanual.ui.completeuserinfo.installpoint;

import com.soarsky.installationmanual.base.BaseView;
import com.soarsky.installationmanual.bean.InPoBean;
import com.soarsky.installationmanual.bean.VeAdBean;

import java.util.List;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 选择安装点View<br>
 */

public interface InstallPointView extends BaseView {
    /**
     * 将获取到的安装点列表显示在界面上
     * @param list 获取到的安装点列表
     */
    void showSuccess(List<InPoBean> list);

}
