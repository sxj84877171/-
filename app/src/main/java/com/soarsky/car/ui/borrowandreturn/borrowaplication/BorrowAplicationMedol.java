package com.soarsky.car.ui.borrowandreturn.borrowaplication;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.DetailsInfo;
import com.soarsky.car.bean.ModifyStatusInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/2<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  收到的借车申请逻辑层<br>
 */

public class BorrowAplicationMedol implements BaseModel {
    /**
     * 拒绝借车的标识
     */
    public static final String REFUSE_STATUS = "4" ;
    /**
     * 同意借车的标识
     */
    public static final String AGREE_STATUS = "1" ;

    /**
     * 车牌号码
     */
    private String carnum ;

    /**
     *记录id
     */
    private Integer bid ;

    /**
     *用户名
     */
    private String username ;

    /**
     * 设置bid
     * @param bid 借车申请的id
     */
    public void setBid(Integer bid) {
        this.bid = bid;
    }

    /**
     * 设置车牌号码
     * @param carnum 车牌号
     */
    public void setCarnum(String carnum) {
        this.carnum = carnum;
    }

    /**
     * 设置用户名
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 同意借车
     * @return 返回修改借车车辆状态的参数类
     */
    public Observable<ResponseDataBean<ModifyStatusInfo>> agree(){
        return api.modifyCarStatus(carnum,bid,AGREE_STATUS,null,username)
                .compose(RxSchedulers.<ResponseDataBean<ModifyStatusInfo>>io_main());
    }
    /**
     * 拒绝借车
     * @param mark 拒绝原因
     * @return  修改借车车辆状态的参数类
     */
    public Observable<ResponseDataBean<ModifyStatusInfo>> refuse(String mark){
        return api.modifyCarStatus(carnum,bid,REFUSE_STATUS,mark,username).
                compose(RxSchedulers.<ResponseDataBean<ModifyStatusInfo>>io_main());
    }

    /**
     * 记录详情
     * @param bId 记录id
     * @return 详情信息
     */
    public Observable<ResponseDataBean<DetailsInfo>> detail(Integer bId){
        return api.detail(bId).
                compose(RxSchedulers.<ResponseDataBean<DetailsInfo>>io_main());
    }

}
