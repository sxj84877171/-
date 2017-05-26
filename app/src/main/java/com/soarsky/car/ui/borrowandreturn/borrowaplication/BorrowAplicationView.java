package com.soarsky.car.ui.borrowandreturn.borrowaplication;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.DetailsInfo;
import com.soarsky.car.bean.ModifyStatusInfo;
import com.soarsky.car.bean.ResponseDataBean;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/2<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  收到的借车申请视图层<br>
 */

public interface BorrowAplicationView extends BaseView {
    /**
     * 请求错误
     */
    public void showError();

    /**
     * 修改借车车辆状态成功
     * @param modifyStatusParm 返回修改借车车辆状态的参数
     */
    public void showsSuccess(ResponseDataBean<ModifyStatusInfo> modifyStatusParm);

    /**
     * 修改借车车辆状态失败
     * @param modifyStatusParm 返回修改借车车辆状态的参数
     */
    public void showsFail(ResponseDataBean<ModifyStatusInfo> modifyStatusParm);

    /**
     * 同意借车请求成功的回调函数
     * @param modifyStatusParm  改借车车辆状态的参数信息
     */
    public void agree(ResponseDataBean<ModifyStatusInfo> modifyStatusParm);

    /**
     * 申请详情显示成功的回调函数
     * @param detailParm  申请详情
     */
    public void showSuccess(ResponseDataBean<DetailsInfo> detailParm);

    /**
     * 申请详情显示失败的回调函数
     * @param detailParm 申请详情
     */
    public void showFail(ResponseDataBean<DetailsInfo> detailParm);

    /**
     * 网络请求失败的回调函数
     * @param e 错误信息
     */
    public void showError(Throwable e);
}
