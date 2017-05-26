package com.soarsky.car.ui.usecarrecord;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.FaultRecordDataBean;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.UseCarRecordParam;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2017/5/10
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public interface RecordView extends BaseView {

    /**
     * 获取用车记录成功
     */
    public void getCarRecoredsListSuccess(ResponseDataBean<List<UseCarRecordParam>> useCarRecordParam);

    /**
     * 获取用车记录失败
     */
    public void getCarRecoredsListFail();

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
