package com.soarsky.car.ui.family.familynumconfirm;

import android.app.Activity;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.FamilyNumConfirmSendParam;
import com.soarsky.car.bean.FamilyNumSendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.data.local.db.FamilyNumDb;

import java.util.List;

import rx.Observable;

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
 * 程序功能：亲情号码更改model <br>
 * 该类为 FamilyNumConfirmModel <br>
 */

public class FamilyNumConfirmModel implements BaseModel{
    /**
     * 上下文本
     */
    public Activity context;

    /**
     * 亲情表封装接口的对象
     */
    private FamilyNumDb familyNumDb;

    /**
     * 设置上下文本
     * @param context
     */
    public void setContext(Activity context){

        this.context = context;
        familyNumDb = new FamilyNumDb(context);
        familyNumDb.getFamilyNumDao();
        familyNumDb.getFamilyNumQuery();

    }

    /**
     * 更改亲情号码
     * @param param 更改亲情号参数
     * @return ResponseDataBean 返回的参数
     */
    public Observable<ResponseDataBean> updateFirendPhone(FamilyNumConfirmSendParam param){

        return api.updateFirendPhone(param.getUsername(),param.getCarnum(),param.getPhone(),param.getNewPhone()).
                compose(RxSchedulers.<ResponseDataBean>io_main());

    }

    /**
     * 亲情号码的设置
     * @param param 设置亲情号参数
     * @return ResponseDataBean 返回的参数
     */
    public Observable<ResponseDataBean> setFirendPhone(FamilyNumSendParam param){
        return api.setFirendPhone(param.getUsername(),param.getCarnum(),param.getPhone1(),param.getPhone2(),param.getPhone3()).compose(RxSchedulers.<ResponseDataBean>io_main());
    }

    /***
     * 修改亲情号码表中的数据
     * @param familyNum 亲情号的信息参数
     */
    public Observable<FamilyNum> updateFamilyNumData(FamilyNum familyNum){
       return familyNumDb.updateData(familyNum);
    }

    /***
     * 根据车牌号与手机号码查询亲情号码数据
     * @param carnum 车牌号
     * @param phone 手机号
     * @return List<FamilyNum> 亲情号列表
     */
    public Observable<List<FamilyNum>> getFamilyNumListByCarNumAndPhone(String carnum,String phone){

        return familyNumDb.getFamilyNumListByCarNumAndPhone(carnum,phone);

    }

    /***
     * 插入亲情号码数据
     * @param familyNum 亲情号信息参数
     * @return FamilyNum 插入记录
     */
    public Observable<FamilyNum> insertFamilyNum(FamilyNum familyNum){
        return familyNumDb.insertData(familyNum);
    }



}
