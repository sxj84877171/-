package com.soarsky.car.ui.family.familynumupdate;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.ui.home.fragment.personal.QueryFamilyParam;

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

public interface FamilyNumUpdateView extends BaseView{

    /**
     * 获取所有亲情号码表数据成功
     * @param list
     */
    public void showSuccess(List<FamilyNum> list);

    /***
     * 获取所有亲情号码表失败
     */
    public void showFails();


}
