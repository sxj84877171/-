package com.soarsky.car.ui.newfamilynum;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.QueryFamilyBean;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.FamilyNum;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/5/12
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public interface NewFamilyNumView extends BaseView{

    /**
     * 获取本地数据库亲情号的数据
     */
    public void getLocalFamilyNumData(List<FamilyNum> familyNa);

    public void showFamilyFail();

    public void showFamilySuccess(ResponseDataBean<QueryFamilyBean> param);

}
