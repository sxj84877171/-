package com.soarsky.car.ui.licenseinformation.cardetails.fragment.carrecord;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.UseCarRecordParam;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：用车记录后台响应接口<br>
 * 该类为 用车记录view<br>
 */


public interface UseCarRecordFragmentView extends BaseView{

    /**
     * 获取用车记录成功
     */
    public void getCarRecoredsListSuccess(ResponseDataBean<List<UseCarRecordParam>> useCarRecordParam);

    /**
     * 获取用车记录失败
     */
    public void getCarRecoredsListFail();

}
