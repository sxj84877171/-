package com.soarsky.car.server.design;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/12/16<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：确认驾驶员成功回调接口<br>
 * 该类为 与终端通讯服务类<br>
 */


public interface ConfirmDriverSucessCallBack {
    /**
     * 确认驾驶员成功
     */
    void onSucess();

     /**
      * 驾驶员离开
      */
     void oncancle();
}
