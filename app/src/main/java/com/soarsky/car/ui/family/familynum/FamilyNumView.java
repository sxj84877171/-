package com.soarsky.car.ui.family.familynum;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.ResponseDataBean;

import java.util.List;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：接口  <br>
 * 该类为 FamilyNumView <br>
 */

public interface FamilyNumView extends BaseView{

    /**
     * 设置亲情号码成功
     * @param param 设置亲情号返回的参数
     */
    public void showSuccess(ResponseDataBean param);

    /**
     * 设置亲情号码失败
     */
    public void showError();

    /**
     * 插入数据成功
     */
    public void insertSuccess();

    /**
     * 插入数据失败
     */
    public void insertFail();

    /**
     * 获取附近车牌列表
     * @param list 扫描的车
     */
    public  void  showList(List<Car> list );




}
