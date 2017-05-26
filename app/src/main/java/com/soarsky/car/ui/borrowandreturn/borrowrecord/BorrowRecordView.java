package com.soarsky.car.ui.borrowandreturn.borrowrecord;

import com.soarsky.car.base.BaseView;

import java.util.List;

/**
 * 车主APP
 * 作者： 王松清
 * 时间： 2016/12/1
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  借车记录视图层
 */

public interface BorrowRecordView extends BaseView {
    public void showSuccess(List<BorrowRecords> list);
    public void showFail(BorrowRecordParm borrowRecordParm);
    public void showError();
    public void insertFail();
    public void insertSuccess();
}
