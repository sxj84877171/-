package com.soarsky.car.ui.movecar;

import com.ivt.bluetooth.ibridge.BluetoothIBridgeDevice;
import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.server.design.OnCarScanedListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.uitl.ConfirmDriverUtils;
import com.soarsky.car.uitl.LogUtil;

import java.util.ArrayList;
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
 * 该类为    请人移车--附近车辆页面P层<br>
 */

public class MoveCarPresent extends BasePresenter<MoveCarModel,MoveCarView> {

    private  boolean activityAlive=true;
    private  List <Car> dervicelist=new ArrayList<>();

    @Override
    public void onStart() {
        mModel.setContext(context);
    }

    /**
     * 设置监听
     */
    public  void  listen(){
        mModel.setCarScanedListener(new OnCarScanedListener() {
            @Override
            public void newCarScannedList(final List<Car> list) {
//                context.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mView.showList(list);
//                    }
//                });
            }

            @Override
            public void newBlueToothScan(BluetoothIBridgeDevice dervice) {
                LogUtil.i("扫描到新设备"+dervice.getDeviceName());

                if( !ConfirmDriverUtils.deviceExisted(dervicelist,dervice)){

               synchronized (dervicelist){
                Car  car =new Car();
                car.setDevice(dervice);
                car.setCarNum(dervice.getDeviceName().substring(3,10));
                car.setCarType(dervice.getDeviceName().substring(1,3));
                car.setCarStatus(dervice.getDeviceName().substring(0,1));
                dervicelist.add(car);
               }
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.showList(dervicelist);
                    }
                });
                }


            }
        });

        mModel.setConnetListener(new OnConnectListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailed(String result) {
                mView.stopProgess();
                //Todo wifi连接失败
                mView.requestFail();
            }

            @Override
            public void onResult(Object o) {
                    mView.stopProgess();
                    String type = (String) o;
                    if ("0".equals(type)) {
                        mView.noticeSuccess();
                    } else if (CONNET_FAIL.equals(type)) {
                        mView.noticeFail();
                    } else if (NO_SIM.equals(type)) {
                        mView.noticeFail();
                    }
                }
        });

    }




    /**
     * 设置服务
     * @param confirmDriverService  确认驾驶员的服务
     */
    public void  setConfirmDriverService(ConfirmDriverService confirmDriverService){
        mModel.setConfirmDriverService(confirmDriverService);
    }


    /**
     * 申请3
     */
    public void  sendApply(Car car,int type){
        mView.showProgess();
        mModel.connectCarByBlueTooth(car,type);
    }



    /**
     * 设置手动或自动
     */
    public  void setIsAuto(boolean isAuto){
        mModel.setIsAuto(isAuto);
    }

    public void setScan(IScan scan){
        mModel.setScan(scan);
    }


    public void setActivityAlive(boolean activityAlive) {
        this.activityAlive = activityAlive;
    }
}
