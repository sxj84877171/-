package com.soarsky.car.ui.carcheck.carchecklogin;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.Car;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/1
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  车辆检测逻辑层
 */

public interface CarCheckView extends BaseView{
    /**
     * 扫描结果
     * @param list
     */
    public void showList(List<Car> list);

    /**
     * 与终端通讯成功
     */
    public void onConfirmDriversSucess();

    /**
     * 与终端通讯失败
     */
    public void onConfirmDriversFailed();

    /**
     * 终端返回的故障码
     */
    public void onResult(String resultStr);

}
