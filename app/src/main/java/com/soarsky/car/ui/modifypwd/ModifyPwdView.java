package com.soarsky.car.ui.modifypwd;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.ModifyPwdInfo;
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
 * 程序功能：修改密码View<br>
 * 该类为 ModifyPwdView<br>
 */

public interface ModifyPwdView extends BaseView{

    /**
     * 修改成功回调
     * @param param 参数
     */
    public void showData(ResponseDataBean<ModifyPwdInfo> param);

    /**
     * 修改失败回调
     */
    public void showonError();

}
