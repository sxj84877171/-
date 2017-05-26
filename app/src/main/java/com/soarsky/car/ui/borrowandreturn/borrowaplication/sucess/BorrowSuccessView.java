package com.soarsky.car.ui.borrowandreturn.borrowaplication.sucess;

import com.soarsky.car.base.BaseView;
import com.soarsky.car.ui.borrowandreturn.recorddetails.DetailsParm;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/14
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 借车方借车成功页面view层
 */


public interface BorrowSuccessView extends BaseView{
    void showSucess(DetailsParm detailsParm);
    void showFail();

}
