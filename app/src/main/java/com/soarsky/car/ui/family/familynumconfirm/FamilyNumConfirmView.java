package com.soarsky.car.ui.family.familynumconfirm;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.ui.family.familynum.FamilyNumParam;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public interface FamilyNumConfirmView extends BaseView{

    public void showSuccess(FamilyNumConfirmParam p);

    public void showError();

    public void getFamilyNumListByCarNumAndPhoneSuccess(List<FamilyNum> familyNa);

    public void getFamilyNumListByCarNumAndPhoneFail();

    /**
     * 修改亲情表成功
     * @param familyNum
     */
    public void updateFamilyNumSuccess(FamilyNum familyNum);

    /**
     * 修改亲情表失败
     */
    public void updateFamilyNumFail();

    /***
     * 与后台设置亲情号成功
     * @param familyNumParam
     */
    public void setFamilyNumSuccess(FamilyNumParam familyNumParam);

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
