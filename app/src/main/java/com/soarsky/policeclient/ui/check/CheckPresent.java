package com.soarsky.policeclient.ui.check;

import com.soarsky.policeclient.base.BasePresenter;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.server.design.OnCarScanedListener;

import java.util.List;

/**
 * andriod_police_app<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  稽查Present<br>
 */

public class CheckPresent extends BasePresenter<CheckModel, CheckView> {
    @Override
    public void onStart() {
//        showListen();
        //listen();
    }


    /**
     * 设置监听
     */
    public void listen() {
        mModel.setCarScanedListener(new OnCarScanedListener() {
            @Override
            public void newCarScanned(final List<Car> car) {
                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.addCar(car);
                    }
                });

            }
        });
    }

    /**
     * 获取扫描到的车辆列表
     */
    public void showListen() {
        List<Car> list = mModel.getScanedCarList();
        if (list != null) {
            mView.showList(list);
        }
    }

}
