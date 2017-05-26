package com.soarsky.installationmanual.ui.register;

import android.widget.EditText;

import com.soarsky.installationmanual.base.BaseView;
import com.soarsky.installationmanual.bean.LoginInfo;
import com.soarsky.installationmanual.bean.ResponseDataBean;


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
 * 该类为 注册View<br>
 */

public interface RegisterView extends BaseView {
    /**
     * 发送信息成功
     */
    void sendSmsSuccess();

}
