package com.soarsky.car.ui.licenseinformation.cardetails.fragment.faultrecord;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.FaultRecordDataBean;
import com.soarsky.car.bean.ResponseDataBean;

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
 * 程序功能：故障记录回调接口<br>
 * 该类为 故障记录view<br>
 */


public interface FaultRecordFragmentView extends BaseView{

    /**
     * 获取故障列表成功
     * @param param
     */
    public void getFaultSuccess(ResponseDataBean<List<FaultRecordDataBean>> param);

    /**
     * 获取故障列表失败
     */
    public void getFaultFail();

}
