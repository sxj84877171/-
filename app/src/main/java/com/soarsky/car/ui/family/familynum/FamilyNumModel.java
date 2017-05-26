package com.soarsky.car.ui.family.familynum;

import android.app.Activity;
import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.FamilyNumSendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.server.design.OnCarScanedListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.ui.family.FamilyNumDb;
import com.soarsky.car.uitl.LogUtil;

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
 * 程序功能：亲情号码设置model <br>
 * 该类为 FamilyNumModel <br>
 */

public class FamilyNumModel implements BaseModel{


    /**
     * 亲情表封装对象
     */
    private FamilyNumDb familyNumDb;
    /**
     * 扫描接口
     */
    private IScan scan ;
    /**
     * service
     */
    private ConfirmDriverService confirmDriverService;
    /**
     * appliction
     */
    private App app;
    /**
     * 上下文本
     */
    private Context context;

    /**
     * 设置文本
     * @param context 文本
     */
    public void setContext(Activity context){

        this.context = context;
        familyNumDb = new FamilyNumDb(context);
        familyNumDb.getFamilyNumDao();
        familyNumDb.getFamilyNumQuery();
        app= (App) context.getApplicationContext();

    }

    /**
     * 亲情号码的设置
     * @param param 设置亲情号传入的参数
     * @return ResponseDataBean 设置亲情号返回结果的参数
     */
    public Observable<ResponseDataBean> setFirendPhone(FamilyNumSendParam param){
        return api.setFirendPhone(param.getUsername(),param.getCarnum(),param.getPhone1(),param.getPhone2(),param.getPhone3()).compose(RxSchedulers.<ResponseDataBean>io_main());
    }

    /***
     * 插入亲情号码数据
     * @param familyNum 亲情号的实体类
     * @return FamilyNum 返回的记录
     */
    public Observable<FamilyNum> insertFamilyNum(FamilyNum familyNum){
        return familyNumDb.insertData(familyNum);
    }

    /**
     * 根据车牌号获取列表
     * @param carnum 车牌号
     * @return List<FamilyNum> 返回亲情号列表集合
     */
    public Observable<List<FamilyNum>> getFamilyNumList(String carnum){
        return familyNumDb.getFamilyNumList(carnum);
    }






    /**
     * 设置扫描监听
     * @param
     */
    public void setCarScanedListener(OnCarScanedListener carScanedListener){
        if(scan != null){
            scan.setCarScanedListener(carScanedListener);
        }
    }

    /**
     * 获得扫描结果
     * @return
     */
    public List<Car> getScanedCarList(){
        if(scan != null){
            return scan.getScanedCarList();
        }
        return null;
    }

    /**
     * 设置连接监听
     * @param connetListener
     */
    public void  setConnetListener(OnConnectListener connetListener){
        if(confirmDriverService != null){
            confirmDriverService.setConnectListener(connetListener);
        }
    }

    /**
     * 设置服务
     * @param confirmDriverService
     */
    public  void  setConfirmDriverService(ConfirmDriverService confirmDriverService){
        this.confirmDriverService=confirmDriverService;
    }

    /**
     * 连接车辆
     * @param car
     * @param type 申请类型
     */
    public  void connectCar(Car car ,int type){
        if(confirmDriverService != null){
            confirmDriverService.connectCar(car,type);
        }
    }

    /**
     * 设置扫描
     * @param scan
     */
    public void setScan(IScan scan) {
        this.scan = scan;
    }


    /**
     * 设置自动或者手动
     */
    public void setIsAuto(boolean isAuto){
        if(confirmDriverService != null){
            LogUtil.i("isAuto"+isAuto);
            confirmDriverService.setAuto(isAuto);
        }
    }

}
