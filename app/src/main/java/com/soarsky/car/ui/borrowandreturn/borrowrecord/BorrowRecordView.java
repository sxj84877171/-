package com.soarsky.car.ui.borrowandreturn.borrowrecord;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.BorrowRecords;
import com.soarsky.car.bean.ResponseDataBean;

import java.util.List;

/**
 * 车主APP<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/1<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  借车记录视图层<br>
 */

public interface BorrowRecordView extends BaseView {
    /**
     * 借车记录显示成功回调函数
     * @param list 借车记录的集合
     */
    public void showSuccess(List<BorrowRecords> list);

    /**
     * 借车记录显示失败回调函数
     * @param borrowRecordParm 借车记录
     */
    public void showFail(ResponseDataBean<List<BorrowRecords>> borrowRecordParm);

    /**
     * 网络请求错误回调函数
     */
    public void showError();
    /**
     * 插入数据操作失败
     */
    public void insertFail();

    /**
     * 插入数据操作成功
     */
    public void insertSuccess();
}
