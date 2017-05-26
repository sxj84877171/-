package com.soarsky.car.ui.validdriverlistener;

import com.ivt.bluetooth.ibridge.BluetoothIBridgeDevice;
import com.soarsky.car.Constants;
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
 * 作者：何明辉<br>
 * 时间： 2016/11/15.<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为确认驾驶员P层<br>
 */

public class ConfirmDriverPresent extends BasePresenter<ConfirmDriverMode,ConfirmDriverView> {

    private  List <Car> dervicelist=new ArrayList<>();

    @Override
    public void onStart() {
        mModel.setContext(context);
    }

    /**
     * 设置扫描监听
     */
    public  void  listen(){
        mModel.setCarScanedListener(new OnCarScanedListener() {
            @Override
            public void newCarScannedList(final List<Car> list) {
//                context.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                       mView.showList(list);
//                    }
//                });
            }

            @Override
            public void newBlueToothScan(BluetoothIBridgeDevice dervice) {
                LogUtil.i("发现新设备2"+dervice.getDeviceName());
            synchronized (dervicelist){

                if(Integer.parseInt(dervice.getDeviceName().substring(0,1))>1&& !ConfirmDriverUtils.deviceExisted(dervicelist,dervice)){
                    Car  car =new Car(dervice);
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


    }


    /**
     * 与终端连接监听
     */
    private void  connectListen(){
        mModel.setConnetListener(new OnConnectListener() {
            @Override
            public void onSuccess() {
                LogUtil.i("确认员成功");
                if(mView!=null){
                    mView.onConfirmDriversSucess();
                    mView.stopProgess();
                }

            }

            @Override
            public void onFailed(String result) {
                LogUtil.i("确认员失败"+result);
                if(mView!=null){
                    mView.onConfirmDriversFailed();
                    mView.stopProgess();
                }

            }

            @Override
            public void onResult(Object o) {

            }
        });
    }




    /**
     * 设置服务
     * @param confirmDriverService
     */
    public void  setConfirmDriverService(ConfirmDriverService confirmDriverService){
        mModel.setConfirmDriverService(confirmDriverService);
    }


    /**
     * 发送连接申请
     * @param car  车
     * @param type  申请类型
     */
    public void  sendApply(Car car,int type){
        connectListen();
        mView.showProgess();
        mModel.connectCar(car,type);
    }


    /**
     * 判断连接类型
     * @param carNum 车牌号
     * @return
     */
    public  int connectType(String carNum){
        return mModel.getConnectType(carNum);
    }


    /**
     * 获取借车的授权码
     * @param carNum 车牌号
     * @return
     */
    public String getAuthCode(String carNum){
        return mModel.getAuthCode(carNum);
    }


    /**
     *  设置scan对象
     * @param scan
     */
    public void  setScan(IScan scan){
        mModel.setScan(scan);
    }

    /**
     * 设置自动或者手动连接
     * @param isAuto TRUE 自动  false  手动
     */
    public  void setIsAuto(boolean isAuto){
        mModel.setIsAuto(isAuto);
    }


    /**
     * 通过蓝牙连接终端
     * @param car
     * @param applyType
     */
    public void byBluetoothConnet(Car  car ,int applyType){
          connectListen();
         mView.showProgess();
         mModel.byBluetoothConnet(car ,applyType);
    }

}
