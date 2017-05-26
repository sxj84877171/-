package com.soarsky.car.ui.carchange;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.TerminalInfoParam;

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
 * 程序功能：切换车辆的回调接口 <br>
 * 该类为 CarChangeView <br>
 */


public interface CarChangeView extends BaseView{

    /**
     * 获取车牌号成功回调
     * @param param 车牌信息
     */
    public void getAllCarnumSuccess(ResponseDataBean<List<LicenseInfo>> param);

    /***
     * 获取车牌号报错的回调
     */
    public void getAllCarnumFail();

    /**
     *根据车牌号获取终端信息成功
     * @param terminalInfoParam 终端信息
     */
    public void getTerminalInfoSuccess(ResponseDataBean<TerminalInfoParam> terminalInfoParam);
    /**
     *根据车牌号获取终端信息失败
     */
    public void getTerminalInfoFail();

}
