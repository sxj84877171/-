package com.soarsky.car.ui.danger.responsibility;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ResponsibilityListDataBean;
import com.soarsky.car.helper.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class ResponsibilityListModel implements BaseModel {

    /**
     * 获取快赔列表
     * @param carnum 车牌号
     * @return ResponseDataBean集合
     */
    public Observable<ResponseDataBean<List<ResponsibilityListDataBean>>> getFastList(String carnum){
        return api.getFastList(carnum).compose(RxSchedulers.<ResponseDataBean<List<ResponsibilityListDataBean>>>io_main());
    }


    /**
     * 修改未读状态
     * @param carnum 车牌号
     * @param id id
     * @return ResponseDataBean对象
     */

    public Observable<ResponseDataBean<String>> modifyUnreadFast(String carnum, int id){
        return api.modifyUnreadFast(carnum,id).compose(RxSchedulers.<ResponseDataBean<String>>io_main());
    }

}
