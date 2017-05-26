package com.soarsky.car.ui.validdriverlistener;

import android.content.Context;

import com.soarsky.car.App;
import com.soarsky.car.base.BaseModel;
import com.soarsky.car.bean.Car;
import com.soarsky.car.data.local.db.BorrowDb;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.data.local.db.bean.Tborrow;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.server.design.OnCarScanedListener;
import com.soarsky.car.server.design.OnConnectListener;
import com.soarsky.car.ui.family.FamilyNumDb;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.WifiHotUtils;

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


    /**
     * 设置自动或者手动
     */
    public void setIsAuto(boolean isAuto){
        if(confirmDriverService != null){
            LogUtil.i("isAuto"+isAuto);
            confirmDriverService.setAuto(isAuto);
        }
    }

    /**
     * 同步亲情号码
     */
    public void uploadDearNum(){


    }

    /**
     * 判断是不是亲情号
     */

    public boolean isDearPhone(String carNum){
        String owerPhone=app.getCommonParam().getOwerPhone();
        if(owerPhone==null){
            return false;
        }
        List<FamilyNum> list= FamilyNumDb.getInstance(context).getFamilyNumList(WifiHotUtils.getInstance().getCarNum(carNum),owerPhone);
        if(list.size()>0){
            return true;
        }
            return false;

    }

    /**
     * 判断是不是借车 返回授权码
     */
    public String getAuthCode(String carNum){
        String borrower=app.getCommonParam().getUserName();
        if(borrower==null){
            return null;
        }
        List<Tborrow>list= BorrowDb.getInstance(context).getTBorrlist(carNum,borrower);
        if(list.size()>0){
            Tborrow tborrow=list.get(0);
            return  tborrow.getAuthcode();
        }

        return  null;
    }

}
