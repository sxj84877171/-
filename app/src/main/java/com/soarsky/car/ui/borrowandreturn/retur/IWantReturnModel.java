package com.soarsky.car.ui.borrowandreturn.retur;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.BorrowRecords;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ReturnCarInfo;
import com.soarsky.car.helper.RxSchedulers;

import java.util.List;

import rx.Observable;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/7<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 我要还车页面逻辑层<br>
 */

public class IWantReturnModel implements BaseModel {

    /**
     * 还车
     * @param bId 借车车辆的id
     * @param carnum 车牌号
     * @return 还车的参数
     */
    public Observable<ResponseDataBean<ReturnCarInfo>> returnCar(String bId, String carnum){
        return api.returnCar(bId,carnum).
                compose(RxSchedulers.<ResponseDataBean<ReturnCarInfo>>io_main());
    }

    /**
     * 获取借车与被借车记录
     * @param phone 电话号码
     * @param username 用户名
     * @return 借车记录
     */
    public Observable<ResponseDataBean<List<BorrowRecords>>> record(String phone, String username){
        return api.borrowRecord(phone,username).
                compose(RxSchedulers.<ResponseDataBean<List<BorrowRecords>>>io_main());
    }
}
