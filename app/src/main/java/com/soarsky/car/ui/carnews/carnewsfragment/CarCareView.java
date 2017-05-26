package com.soarsky.car.ui.carnews.carnewsfragment;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.AutomotiveInfo;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/11<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  汽车资讯的视图层<br>
 */

public interface CarCareView extends BaseView {
    /**
     * 获取汽车资讯列表失败的接口回调
     * @param e 错误信息
     */
    public void showError(Throwable e);

    /**
     * 获取汽车资讯列表成功的接口回调
     * @param listResponseDataBean  资讯集合
     */
    public void showSuccess(ResponseDataBean<List<AutomotiveInfo>> listResponseDataBean);
}
