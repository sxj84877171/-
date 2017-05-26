package com.soarsky.policeclient.server.biz;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.PowerManager;

import com.soarsky.policeclient.Constants;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.server.bluetooth.Blue;
import com.soarsky.policeclient.server.design.ICheck;
import com.soarsky.policeclient.server.design.IScan;
import com.soarsky.policeclient.server.design.OnCarScanedListener;
import com.soarsky.policeclient.ui.check.CheckDb;
import com.soarsky.policeclient.uitl.BluetoothUtils;
import com.soarsky.policeclient.uitl.RandomUtils;
import com.soarsky.policeclient.uitl.SpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2017/1/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  第六套稽查方案<br>
 *
 * 手机端:<br>
 1. 手机端保持蓝牙一直被发现状态，并且蓝牙名称一直保持为警务通名称。<br>
 2. 手机端一直保持蓝牙搜索和wifi搜素，搜素附近车辆车牌热点信息，然后直接进行黑名单比对。<br>
 3. 手机端wifi发现附近车辆车牌热点，连接该热点，对该车辆发送稽查指令。<br>
 */
public class Check extends HandlerThread implements ICheck {
    /**
     * log tag
     */
    public static final String TAG = Check.class.getSimpleName();
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 调用扫描功能
     */
    private IScan scan;
    /**
     * Handler
     */
    private Handler handler;
    /**
     * 是否在稽查
     */
    private boolean isChecking = false;
    /**
     * 稽查存数据库
     */
    private CheckDb checkDb;
    /**
     * 蓝牙功能类
     */
    private BluetoothAdapter bluetoothAdapter;


    private PowerManager.WakeLock sWakeLock;

    public Check(Context context) {
        this(TAG, Thread.NORM_PRIORITY, context);
        checkDb = new CheckDb(context);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }


    public Check(String name, int priority, Context context) {
        super(name, priority);
        this.mContext = context;
    }
    private  void acquireWakeLock() {
        if (sWakeLock == null) {
            PowerManager pMgr = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);    //保持当前进程持续有效
            sWakeLock = pMgr.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    "com.soarsky.policeclient wakelock.");

        }
        sWakeLock.acquire();
    }
    private void releaseWakeLock() {
        sWakeLock.release();
    }
    @Override
    public void startCheck(boolean changeName) {
        if(bluetoothAdapter.isEnabled() && changeName){
            bluetoothAdapter.setName(Constants.POLICE + RandomUtils.getRandom());
            BluetoothUtils.openDiscoverable();
        }
        SpUtil.save(Constants.CHECK_CAR,"");
        acquireWakeLock();
        start();
        scan = new Scan();
        scan.setOnBlueScan(onBlueScan);
        handler = new Handler(getLooper());
        isChecking = true;
        handler.post(new Runnable() {
            @Override
            public void run() {
                scan.startScan();
            }
        });
    }

    @Override
    public void stopCheck(boolean changeName) {
        SpUtil.save(Constants.CHECK_CAR,"");
        getScanedCarList().clear();
        if(changeName){
            bluetoothAdapter.setName(Build.MODEL);
        }
        scan.stopScan();
        isChecking = false;
        releaseWakeLock();
        Blue.getInstance(mContext).setOnBlueScanFinish(null);
        quit();
    }

    private Blue.OnBlueScan onBlueScan = new Blue.OnBlueScan() {
        @Override
        public void onBlueScan(final Car car) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    scan.addCar(car);
                    insertRecord(car);
                }
            });
        }
    };

    @Override
    public boolean isChecking() {
        return isChecking;
    }

    public void setCarScanedListener(OnCarScanedListener carScanedListener) {
        if (scan != null) {
            scan.setCarScanedListener(carScanedListener);
        }
    }

    /**
     * 获取扫描结果车辆列表
     * @return 扫描结果车辆列表
     */
    public List<Car> getScanedCarList(){
        if(scan!=null){
            return scan.getScanedCarList();
        }else {
            List<Car> cars = new ArrayList<>();
            return cars;
        }
    }


    /**
     * 将扫描到的记录加入到数据库中
     */
    public void  insertRecord(Car car){
        checkDb.inserData(car);
    }
}
