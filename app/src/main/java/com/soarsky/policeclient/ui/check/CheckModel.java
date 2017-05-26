package com.soarsky.policeclient.ui.check;

import com.soarsky.policeclient.base.BaseModel;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.bean.CheckParam;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.remote.server1.ApiM;
import com.soarsky.policeclient.helper.RxSchedulers;
import com.soarsky.policeclient.server.design.IScan;
import com.soarsky.policeclient.server.design.OnCarScanedListener;

import java.util.List;
import java.util.Map;

import rx.Observable;

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
 * 该类为  稽查Model<br>
 */

public class CheckModel implements BaseModel {
    /**
     * 扫描操作
     */
    private IScan scan ;

    /**
     * 设置车辆扫描监听
     * @param carScanedListener 车辆扫描监听
     */
    void setCarScanedListener(OnCarScanedListener carScanedListener){
        if(scan != null){
            scan.setCarScanedListener(carScanedListener);
        }
    }

    /**
     * 获取扫描到的车辆列表
     * @return 扫描到的车辆列表
     */
    public List<Car> getScanedCarList(){
        if(scan != null){
            return scan.getScanedCarList();
        }
        return null;
    }

    public void setScan(IScan scan) {
        this.scan = scan;
    }


    public Observable<ResponseDataBean<CheckParam>> uploadInspect(Map<String ,String> map){
        return ApiM.getInstance().service.uploadInspect(map).compose(RxSchedulers.<ResponseDataBean<CheckParam>>io_main());
    }




}
