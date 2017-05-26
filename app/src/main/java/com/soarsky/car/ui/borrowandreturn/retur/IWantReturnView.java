package com.soarsky.car.ui.borrowandreturn.retur;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ReturnCarInfo;
import com.soarsky.car.bean.BorrowRecords;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/7<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  我要还车页面视图层<br>
 */

public interface IWantReturnView extends BaseView {
    /**
     * 网络请求错误
     * @param e 错误信息
     */
    public void showError(Throwable e);

    /**
     * 归还车辆成功的回调函数
     * @param returnCarParm  归还车辆
     */
    public void returnSuccess(ResponseDataBean<ReturnCarInfo> returnCarParm);

    /**
     * 归还车辆失败的回调函数
     * @param returnCarParm 归还车辆
     */
    public void returnFail(ResponseDataBean<ReturnCarInfo> returnCarParm);

    /**
     * 显示借车记录成功的回调函数
     * @param ret 借车记录列表
     */
    public void showSuccess(List<BorrowRecords> ret);

    /**
     * 显示借车记录失败的回调函数
     * @param borrowRecordParm 返回的借车记录参数
     */
    public void showFail(ResponseDataBean<List<BorrowRecords>> borrowRecordParm);
}
