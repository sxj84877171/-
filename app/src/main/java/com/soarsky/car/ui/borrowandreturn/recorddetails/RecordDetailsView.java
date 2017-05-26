package com.soarsky.car.ui.borrowandreturn.recorddetails;

import com.soarsky.car.base.BaseView;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/12
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 记录详情视图层
 */

public interface RecordDetailsView extends BaseView {
    public void showSuccess(DetailsParm detailParm);
    public void showFail(DetailsParm detailParm);
    public void showError(Throwable e);
}
