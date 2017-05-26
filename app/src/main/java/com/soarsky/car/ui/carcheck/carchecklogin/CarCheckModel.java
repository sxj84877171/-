package com.soarsky.car.ui.carcheck.carchecklogin;

import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.server.design.OnCarScanedListener;
import com.soarsky.car.server.design.OnConnectListener;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/1
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  车辆检测逻辑层
 */

public class CarCheckModel implements BaseModel {

    private IScan scan ;
    private ConfirmDriverService confirmDriverService;

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





}
