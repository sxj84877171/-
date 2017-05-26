package com.soarsky.car.ui.borrowandreturn.retur;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.ui.borrowandreturn.borrowrecord.BorrowRecordParm;
import com.soarsky.car.ui.borrowandreturn.borrowrecord.BorrowRecords;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/7
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  我要还车页面视图层
 */

public interface IWantReturnView extends BaseView {
    public void showError(Throwable e);
    public void returnSuccess(ReturnCarParm returnCarParm);
    public void returnFail(ReturnCarParm returnCarParm);
    public void showSuccess(List<BorrowRecords> ret);
    public void showFail(BorrowRecordParm borrowRecordParm);
}
