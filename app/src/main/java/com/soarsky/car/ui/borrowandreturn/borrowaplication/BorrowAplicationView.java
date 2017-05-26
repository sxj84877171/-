package com.soarsky.car.ui.borrowandreturn.borrowaplication;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.ui.borrowandreturn.ModifyStatusParm;
import com.soarsky.car.ui.borrowandreturn.recorddetails.DetailsParm;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/2
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  收到的借车申请视图层
 */

public interface BorrowAplicationView extends BaseView {
    public void showError();
    public void showSuccess(ModifyStatusParm modifyStatusParm);
    public void showFail(ModifyStatusParm modifyStatusParm);
    public void agree(ModifyStatusParm modifyStatusParm);
    public void showSuccess(DetailsParm detailParm);
    public void showFail(DetailsParm detailParm);
    public void showError(Throwable e);
}
