package com.soarsky.car.ui.family.familynumconfirm;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.FamilyNum;

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
 * 程序功能：更改亲情号接口 <br>
 * 该类为 FamilyNumConfirmView <br>
 */

public interface FamilyNumConfirmView extends BaseView{
    /**
     * 与后台更改成功
     * @param p 更改返回的参数
     */
    public void showSuccess(ResponseDataBean p);

    /**
     * 与后台更改失败
     */
    public void showError();

    /**
     * 获取本地数据的亲情数据成功
     * @param familyNa 亲情号列表
     */
    public void getFamilyNumListByCarNumAndPhoneSuccess(List<FamilyNum> familyNa);

    /**
     * 获取本地数据亲情数据失败
     */
    public void getFamilyNumListByCarNumAndPhoneFail();

    /**
     * 修改亲情表成功
     * @param familyNum 亲情号参数
     */
    public void updateFamilyNumSuccess(FamilyNum familyNum);

    /**
     * 修改亲情表失败
     */
    public void updateFamilyNumFail();

    /***
     * 与后台设置亲情号成功
     * @param familyNumParam 设置返回的参数
     */
    public void setFamilyNumSuccess(ResponseDataBean familyNumParam);

    /**
     * 与后台设置亲情号失败
     */
    public void setFamilyNumFail();

    /**
     * 插入数据成功
     */
    public void insertFamilyNumSuccess();

    /***
     * 插入数据失败
     */
    public void insertFamilyNumFail();

}
