package com.soarsky.car.ui.borrowandreturn.borrow;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.CheckBorrowCar;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.data.local.db.FamilyNumDb;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/5<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为<br>
 * 我要借车medol层<br>
 */

public class IWantBorrowMedol implements BaseModel {


    /**
     * 上下文
     */
    private Context context;
    /**
     * 全局App
     */
    private App app;

    /**
     * 初始话全局变量
     * @param context 上下文
     */
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
    public Observable<ResponseDataBean> borrow(String carNum, String phone, String stime, String etime, String borrowPhone){
        return api.borrow(carNum,phone,stime,etime,borrowPhone).
                compose(RxSchedulers.<ResponseDataBean>io_main());
    }

    /**
     * 校验车牌号与车主手机号
     * @param carNum 借用车牌号
     * @param phone 车主手机号
     * @return 校验参数类
     */
    public Observable<ResponseDataBean<CheckBorrowCar>> check(String carNum, String phone){
        return api.check(carNum,phone).compose(RxSchedulers.<ResponseDataBean<CheckBorrowCar>>io_main());
    }

    /**
     * 判断是否是亲情号
     * @param carNum
     * @return 返回boolean类型值 true表示是亲情号码
    */
    public  boolean isFamilyNum(String carNum){
        return FamilyNumDb.getInstance(context).isFamilyNum(carNum,app.getCommonParam().getOwerPhone());

    }

}
