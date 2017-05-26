package com.soarsky.car.ui.family.familynum;

import android.util.Log;

import com.ivt.bluetooth.ibridge.BluetoothIBridgeDevice;
import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.FamilyNumSendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.server.design.OnCarScanedListener;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

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
 * 程序功能：亲情设置present <br>
 * 该类为 FamilyNumPresent <br>
 */

public class FamilyNumPresent extends BasePresenter<FamilyNumModel,FamilyNumView>{

    @Override
    public void onStart() {

        mModel.setContext(context);
    }

    /**
     * 亲情号码设置
     * @param param 传入亲情号信息参数
     */
    public void setFirendPhone(FamilyNumSendParam param){

        mView.showProgess();
        mModel.setFirendPhone(param).subscribe(new Subscriber<ResponseDataBean>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.showError();

            }

            @Override
            public void onNext(ResponseDataBean param) {

                mView.stopProgess();
                mView.showSuccess(param);

            }
        });

    }

    /***
     * 插入亲情号码表数据
     * @param familyNum 亲情号信息参数
     */
    public void insertFamilyNum(FamilyNum familyNum){
        mView.showProgess();
        mRxManager.add(mModel.insertFamilyNum(familyNum).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FamilyNum>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.insertFail();
            }

            @Override
            public void onNext(FamilyNum familyNum) {
                mView.stopProgess();
                Log.d("TAA","++success"+familyNum.toJson());
                mView.insertSuccess();

            }
        }));
    }

    /**
     * 根据车牌号获取列表
     * @param carnum 车牌号
     */
    public void getFamilyNumList(String carnum){

        mView.showProgess();
        mRxManager.add(mModel.getFamilyNumList(carnum).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<List<FamilyNum>>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
            }

            @Override
            public void onNext(List<FamilyNum> familyNa) {
                mView.stopProgess();
            }
        }));
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

            @Override
            public void newBlueToothScan(BluetoothIBridgeDevice dervice) {

            }
        });


    }
    /**
     * 设置服务
     * @param confirmDriverService 服务
     */
    public void  setConfirmDriverService(ConfirmDriverService confirmDriverService){
        mModel.setConfirmDriverService(confirmDriverService);
    }


    /**
     * 申请3
     * @param car 车
     * @param type 类别
     */
    public void  sendApply(Car car,int type){
        mView.showProgess();
        mModel.connectCar(car,type);
    }

    /**
     * 设置扫描
     * @param scan 扫描
     */
    public void  setScan(IScan scan){
        mModel.setScan(scan);
    }

    /**
     * 设置手动或自动
     * @param isAuto true -自动 false - 手动
     */
    public  void setIsAuto(boolean isAuto){
        mModel.setIsAuto(isAuto);
    }


}
