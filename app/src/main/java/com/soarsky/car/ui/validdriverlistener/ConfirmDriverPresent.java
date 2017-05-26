package com.soarsky.car.ui.validdriverlistener;

import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.server.design.OnCarScanedListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.uitl.LogUtil;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者：何明辉
 * 时间： 2016/11/15.
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class ConfirmDriverPresent extends BasePresenter<ConfirmDriverMode,ConfirmDriverView> {
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
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       mView.showList(list);
                    }
                });
            }
        });

        mModel.setConnetListener(new OnConnectListener() {
            @Override
            public void onSuccess() {
                LogUtil.i("确认员成功");
                mView.onConfirmDriversSucess();
                mView.stopProgess();
            }

            @Override
            public void onFailed(String result) {
                LogUtil.i("确认员失败"+result);
                mView.onConfirmDriversFailed();
                mView.stopProgess();
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
     * 申请3
     */
    public void  sendApply(Car car,int type){
        mView.showProgess();
        mModel.connectCar(car,type);
    }

    /**
     * 申请4
     */
    public void  applyFour(Car car){
        mModel.connectCar(car,4);
        mView.showProgess();
    }

    /**
     * 申请5
     * @param car
     */
    public void applyFive(Car car){
        mModel.connectCar(car,5);
        mView.showProgess();
    }

    /**
     * 是否是亲情号
     * @param carNum
     * @return
     */
    public  boolean isDearPhone(String carNum){
        return mModel.isDearPhone(carNum);
    }

    /**
     * 判断是不会借车
     */

    public String  getAuthCode(String carNum){
        return mModel.getAuthCode(carNum);

    }



    /**
     * 申请3
     */
    public void  applytest(Car car){
        mModel.connectCar(car,9);
    }

    /**
     * 设置手动或自动
     */
    public  void setIsAuto(boolean isAuto){
        mModel.setIsAuto(isAuto);
    }

    /**
     * 同步亲情号码
     */
    public void uploadDearNum(){
        mModel.uploadDearNum();
    }

    public void  setScan(IScan scan){
        mModel.setScan(scan);
    }


}
