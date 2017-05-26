package com.soarsky.car.ui.borrowandreturn.borrowaplication;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.ui.borrowandreturn.ModifyStatusParm;
import com.soarsky.car.ui.borrowandreturn.recorddetails.DetailsParm;

import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/2
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  收到的借车申请逻辑层
 */

public class BorrowAplicationMedol implements BaseModel {
    public static final String REFUSE_STATUS = "4" ;
    public static final String AGREE_STATUS = "1" ;

    /**
     * 车牌号码
     */
    private String carnum ;

    /**
     *
     */
    private Integer bid ;

    /**
     *
     */
    private String username ;

    /**
     * 设置bid
     * @param bid
     */
    public void setBid(Integer bid) {
        this.bid = bid;
    }

    /**
     * 设置车牌号码
     * @param carnum
     */
    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    /**
     * 设置用户名
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 同意借车
     * @return
     */
    public Observable<ModifyStatusParm> agree(){
        return Api.getInstance().service.modifyCarStatus(carnum,bid,AGREE_STATUS,null,username)
                .compose(RxSchedulers.<ModifyStatusParm>io_main());
    }
    /**
     * 拒绝借车
     * @param mark
     * @return
     */
    public Observable<ModifyStatusParm> refuse(String mark){
        return Api.getInstance().service.modifyCarStatus(carnum,bid,REFUSE_STATUS,mark,username).
                compose(RxSchedulers.<ModifyStatusParm>io_main());
    }

    /**
     * 记录详情
     * @param
     * @return
     */
    public Observable<DetailsParm> detail(Integer bId){
        return Api.getInstance().service.detail(bId).
                compose(RxSchedulers.<DetailsParm>io_main());
    }

}
