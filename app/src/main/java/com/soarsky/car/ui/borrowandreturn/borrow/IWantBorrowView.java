package com.soarsky.car.ui.borrowandreturn.borrow;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.CheckBorrowCar;
import com.soarsky.car.bean.ResponseDataBean;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/5<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  我要借车页面视图层<br>
 */

public interface IWantBorrowView extends BaseView {
    /**
     * 借车请求成功
     * @param borrowParm 后台返回的响应参数
     */
    public void showSuccess(ResponseDataBean borrowParm);

    /**
     * 请求失败
     */
    public void showError();

    /**
     * 校验车牌、手机号失败的回调函数
     * @param checkBorrowCar  校验车牌、手机号参数
     */
    public void checkFail(ResponseDataBean<CheckBorrowCar> checkBorrowCar);

    /**
     * 借车请求失败
     * @param borrowParm  后台返回的参数
     */
    public void showFail(ResponseDataBean borrowParm);

    /**
     * 校验车牌、手机号失败的回调函数
     * @param checkBorrowCar 返回的校验车牌、手机号参数
     */
    public void checkSuccess(ResponseDataBean<CheckBorrowCar> checkBorrowCar);
}
