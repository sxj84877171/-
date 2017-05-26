package com.soarsky.car.ui.trivelrecord.carlist;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ivt.bluetooth.ibridge.BluetoothIBridgeDevice;
import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.OnCarScanedListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2017/3/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  行车记录报警界面<br>
 */

public class CarListActivity extends BaseActivity implements View.OnClickListener{

    private ConfirmDriverService confirmDriverService;

    private List<Car> carList;

    private CarlistAdapter carListAdapter;

    private ListView listView;

    private TextView carNumTv;


    private LinearLayout carNumLL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_list;
    }

    @Override
    public void initView() {
        listView= (ListView) findViewById(R.id.carlist_listView);
        findViewById(R.id.carList_blackTv).setOnClickListener(this);
        carNumTv= (TextView) findViewById(R.id.carList_carNum);
        carNumLL= (LinearLayout) findViewById(R.id.carList_carNumrl);


        initData();
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }


    private void initData(){
        carListAdapter=new CarlistAdapter(this,carList);
        listView.setAdapter(carListAdapter);

        if(TextUtils.isEmpty(App.getApp().getCarNum())){
            carNumLL.setVisibility(View.GONE);
        }else{
            carNumTv.setText(App.getApp().getCarNum());
        }
        initGPS();
    }


    @Override
    protected void onPause() {
        super.onPause();
        mContext.unbindService(serviceConnection);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent serviceIntent=new Intent(this, ConfirmDriverService.class);
        mContext.bindService(serviceIntent,
                serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            confirmDriverService=((ConfirmDriverService.LocalBinder) service).getService();
            setScanListener();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };



    private  void setScanListener(){
        confirmDriverService.setCarScanedListener(new OnCarScanedListener() {
            @Override
            public void newCarScannedList(List<Car> list) {
                carList=new ArrayList<Car>(list);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        carListAdapter.setCarList(carList);
                    }
                });
            }

            @Override
            public void newBlueToothScan(BluetoothIBridgeDevice dervice) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.carList_blackTv:
                finish();
            break;
        }
    }





        private void initGPS() {
            LocationManager locationManager = (LocationManager) this
                    .getSystemService(Context.LOCATION_SERVICE);
            // 判断GPS模块是否开启，如果没有则开启
            if (!locationManager
                    .isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setMessage("为了您的乘车安全，需要请您手动打开gps");
                dialog.setPositiveButton("确定",
                        new android.content.DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                // 转到手机设置界面，用户设置GPS
                                Intent intent = new Intent(
                                        Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivityForResult(intent, 0); // 设置完成后返回到原来的界面

                            }
                        });
                dialog.setNeutralButton("取消", new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        arg0.dismiss();
                    }
                });
                dialog.show();
            }
        }}
