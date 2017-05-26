package com.soarsky.car.ui.movecar;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.Car;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  请人移车--附近车辆页面视图层<br>
 */

public interface MoveCarView extends BaseView{

    /**
     * 扫描到的车辆列表的回调函数
     * @param list 车辆列表
     */
    public void showList(List<Car> list);

    /**
     * 显示给用户的消息的回调
     * @param message 消息内容
     */
    public  void  showToast(String  message);

    /**
     * 请求失败
     */
    public void requestFail();

    /**
     * 通知车主移车信息发送成功的回调
     */
    public void noticeSuccess();
    /**
     * 通知车主移车信息发送失败的回调
     */
    public void noticeFail();

}
