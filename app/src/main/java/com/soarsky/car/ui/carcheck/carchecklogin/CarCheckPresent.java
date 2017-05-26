package com.soarsky.car.ui.carcheck.carchecklogin;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.server.design.OnCarScanedListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.WifiHotUtils;

import java.util.ArrayList;
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
 * 该类为  车辆检测P层
 */

public class CarCheckPresent extends BasePresenter<CarCheckModel, CarCheckView> {
    /**
     * 附近扫描的热点
     */
    private List<Car> carList = new ArrayList<>();
    /**
     * 查询故障的车辆
     */
    private Car connectCar;

    /**
     * 连接终端的次数
     */
    private int index = 0;

    /**
     * 故障
     */
    private List<String > toubleList=new ArrayList<>();
    @Override
    public void onStart() {

    }

    /**
     * 设置监听
     */
    public void listen() {

        mModel.setCarScanedListener(new OnCarScanedListener() {
            @Override
            public void newCarScannedList(final List<Car> list) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        carList = list;
                    }
                });
            }
        });

        mModel.setConnetListener(new OnConnectListener() {
            @Override
            public void onSuccess() {
                mView.onConfirmDriversSucess();
                mView.stopProgess();
            }

            @Override
            public void onFailed(String result) {
                mView.onConfirmDriversFailed();
                    mView.stopProgess();
//                index++;
//                if (index > 2) {
//                    mView.onConfirmDriversFailed();
//                    mView.stopProgess();
//                }else{
//                    connectCar();
//                }

            }

            @Override
            public void onResult(Object o) {
                String  restutStr= (String) o;

                mView.onResult(restutStr);
                mView.stopProgess();
            }
        });
    }

    /**
     * 设置服务
     *
     * @param confirmDriverService
     */
    public void setConfirmDriverService(ConfirmDriverService confirmDriverService) {
        mModel.setConfirmDriverService(confirmDriverService);
    }


    /**
     * 连接车辆
     */
    public void connectCar() {

        mView.showProgess();
        mModel.connectCar(connectCar, 8);
    }


    public void setScan(IScan scan) {
        mModel.setScan(scan);
    }

    /**
     * 判断车辆是否在附近
     */
    public boolean includeCar(String carNum) {
        LogUtil.i("carList.seze:"+carList.size());

        for (Car car : carList) {
            if (WifiHotUtils.getInstance().getCarNum(car.getCarNum()).equals(carNum)) {
                connectCar = car;
                return true;
            }
        }
        return false;
    }





}
