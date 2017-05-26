package com.soarsky.car.ui.validdriverlistener;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.server.design.OnCarScanedListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.uitl.LogUtil;

import java.util.List;


/**
 * Andriod_Car_App <br/>
 * 作者：何明辉<br>
 * 时间： 2016/11/15.<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 确认驾驶员activity对应的model<br>
 */

public class ConfirmDriverMode implements BaseModel{
    private IScan scan ;
    private ConfirmDriverService confirmDriverService;
    private App app;
    private Context context;

    public  void  setContext(Context context){
        this.context=context;
        app= (App) context.getApplicationContext();
    }
    /**
     * 设置扫描监听
     * @param carScanedListener  扫描监听
     */
    public void setCarScanedListener(OnCarScanedListener carScanedListener){
        if(scan != null){
            scan.setCarScanedListener(carScanedListener);
        }
    }

    /**
     * 设置连接监听
     * @param connetListener 连接监听
     */
    public void  setConnetListener(OnConnectListener connetListener){
        if(confirmDriverService != null){
            confirmDriverService.setConnectListener(connetListener);
        }
    }

    /**
     * 设置服务
     * @param confirmDriverService 确认驾驶员服务
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


    /***
     *  设置自动还是手动
     * @param isAuto TRUE 自动 false 手动
     */
    public void setIsAuto(boolean isAuto){
        if(confirmDriverService != null){
            LogUtil.i("isAuto"+isAuto);
            confirmDriverService.setAuto(isAuto);
        }
    }

    /**
     * 通过蓝牙连接终端
     * @param car
     * @param applyType
     */
    public void byBluetoothConnet(Car  car ,int applyType){

        if(confirmDriverService != null){
            confirmDriverService.byBluetoothSendMeeage(car,applyType);
        }
    }

    /**
     *
     * @param carNum 车牌号
     * @return 0 车主 1亲情号 2借车 3都不是
     */

    public int getConnectType(String carNum){
        List<LicenseInfo> car_list=app.getCar_list();
        if(null==car_list){
            return 3;
        }
        for(int i=0;i<car_list.size();i++){
            if(carNum.equals(car_list.get(i).getPlateno())){
               return car_list.get(i).getSign();
            }
        }
            return 3;

    }


    /**
     * 获取借车的授权码
     * @param carNum
     * @return
     */
    public String getAuthCode(String carNum){
        List<LicenseInfo> car_list=app.getCar_list();
        if(null==car_list){
            return "";
        }

        for(int i=0;i<car_list.size();i++){
            if(carNum.equals(car_list.get(i).getPlateno())){
                return car_list.get(i).getAuthcode();
            }
        }

        return  "";
    }


}
