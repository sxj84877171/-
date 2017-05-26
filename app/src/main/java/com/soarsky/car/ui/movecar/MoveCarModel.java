package com.soarsky.car.ui.movecar;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.server.design.OnCarScanedListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.uitl.LogUtil;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  请人移车--附近车辆页面逻辑层<br>
 */

public class MoveCarModel implements BaseModel {
    /**
     * 与终端通讯服务类接口
     */
    private IScan scan ;
    /**
     * 确认驾驶员服务
     */
    private ConfirmDriverService confirmDriverService;
    /**
     * 获取全局变量的实例
     */
    private App app;
    /**
     * 上下文
     */
    private Context context;

    public  void  setContext(Context context){
        this.context=context;
        app= (App) context.getApplicationContext();
    }

    /**
     * 设置扫描监听
     * @param carScanedListener 监听
     */
    public void setCarScanedListener(OnCarScanedListener carScanedListener){
        if(confirmDriverService != null){
            confirmDriverService.setMoveCarScanedListener(carScanedListener);
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
     * @param confirmDriverService 确认驾驶员的服务
     */
    public  void  setConfirmDriverService(ConfirmDriverService confirmDriverService){
        this.confirmDriverService=confirmDriverService;
    }

    /**
     * 连接车辆
     * @param car 车的实例
     * @param type 申请类型
     */
    public  void connectCar(Car car ,int type){
        if(confirmDriverService != null){
            confirmDriverService.connectCar(car,type);
        }
    }


    /**
     * 连接车辆
     * @param car 车的实例
     * @param type 申请类型
     */
    public  void connectCarByBlueTooth(Car car ,int type){
        if(confirmDriverService != null){
            confirmDriverService.byBluetoothSendMeeage(car,type);
        }
    }


    /**
     * 设置扫描
     * @param scan 扫描
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
