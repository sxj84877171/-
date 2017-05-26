package com.soarsky.car.ui.danger.responsibilitydetails.haveobjection;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.FastDissentParam;
import com.soarsky.car.bean.ResponseDataBean;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/23
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public interface HaveObjectionView extends BaseView{

    /**
     * 提交有异议接口成功
     * @param fastDissentParam
     */
    public void fastDissentSuccess(ResponseDataBean<FastDissentParam> fastDissentParam);

    /**
     * 提交有异议接口失败
     */
    public void fastDissentFail();
}
