package com.soarsky.car.ui.borrowandreturn.borrow;

import com.soarsky.car.base.BaseView;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/5
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  我要借车页面视图层
 */

public interface IWantBorrowView extends BaseView {

    public void showSuccess(BorrowParm borrowParm);
    public void showError();
    public void checkFail(CheckBorrowCar checkBorrowCar);
    public void showFail(BorrowParm borrowParm);
    public void checkSuccess(CheckBorrowCar checkBorrowCar);
}
