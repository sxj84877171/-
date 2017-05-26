package com.soarsky.car.ui.family.familynumupdate;

import com.soarsky.car.base.BaseView;
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
 * 程序功能：更改亲情号列表接口<br>
 * 该类为 FamilyNumUpdateView <br>
 */

public interface FamilyNumUpdateView extends BaseView{

    /**
     * 获取所有亲情号码表数据成功
     * @param list 亲情号列表
     */
    public void showSuccess(List<FamilyNum> list);

    /***
     * 获取所有亲情号码表失败
     */
    public void showFails();


}
