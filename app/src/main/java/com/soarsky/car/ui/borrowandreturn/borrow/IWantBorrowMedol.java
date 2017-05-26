package com.soarsky.car.ui.borrowandreturn.borrow;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.ui.family.FamilyNumDb;

import rx.Observable;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/5
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 * 我要借车medol层
 */

public class IWantBorrowMedol implements BaseModel {



    private Context context;
    private App app;
    public void  setContext(Context context){
        this.context=context;
        app= (App) context.getApplicationContext();
    }

    /**
     * 借车申请请求
     * @param carNum 借车车牌号
     * @param phone 车主手机号码
     * @param stime 开始时间
     * @param etime 结束时间
     * @param borrowPhone 借车人手机号码
     * @return 借车申请
     */
    public Observable<BorrowParm> borrow(String carNum, String phone, String stime, String etime, String borrowPhone){
        return Api.getInstance().service.borrow(carNum,phone,stime,etime,borrowPhone).
                compose(RxSchedulers.<BorrowParm>io_main());
    }

    /**
     * 校验车牌号与车主手机号
     * @param carNum 借用车牌号
     * @param phone 车主手机号
     * @return
     */
    public Observable<CheckBorrowCar> check(String carNum, String phone){
        return Api.getInstance().service.check(carNum,phone).compose(RxSchedulers.<CheckBorrowCar>io_main());
    }

    /**
     * 判断是否是亲情号
     * @param carNum
     * @return
     */
    public  boolean isFamilyNum(String carNum){
        return FamilyNumDb.getInstance(context).isFamilyNum(carNum,app.getCommonParam().getOwerPhone());

    }

}
