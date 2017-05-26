package com.soarsky.car.ui.borrowandreturn.recorddetails;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.DetailsInfo;
import com.soarsky.car.bean.ResponseDataBean;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/12<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 记录详情视图层<br>
 */

public interface RecordDetailsView extends BaseView {
    /**
     * 展示记录详情成功的回调函数
     * @param detailParm  记录详情
     */
    public void showSuccess(ResponseDataBean<DetailsInfo> detailParm);

    /**
     * 展示记录详情失败的回调函数
     * @param detailParm 记录详情
     */
    public void showFail(ResponseDataBean<DetailsInfo> detailParm);

    /**
     * 网络请求失败
     * @param e 错误信息
     */
    public void showError(Throwable e);
}
