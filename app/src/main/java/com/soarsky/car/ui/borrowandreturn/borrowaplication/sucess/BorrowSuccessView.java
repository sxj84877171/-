package com.soarsky.car.ui.borrowandreturn.borrowaplication.sucess;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.bean.DetailsInfo;
import com.soarsky.car.bean.ResponseDataBean;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/12/14<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 借车方借车成功页面view层<br>
 */


public interface BorrowSuccessView extends BaseView{
    /**
     * 查询借车详细信息成功的回调函数
     * @param detailsParm  详情信息
     */
    void showSucess(ResponseDataBean<DetailsInfo> detailsParm);

    /**
     * 展示借车详细信息失败的回调函数
     */
    void showFail();

}
