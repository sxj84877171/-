package com.soarsky.car.ui.trivelrecord.riderecordstart;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.ui.trivelrecord.autoopen.AutoOpenActivity;
import com.soarsky.car.ui.trivelrecord.carlist.CarListActivity;
import com.umeng.analytics.MobclickAgent;

import static com.soarsky.car.ConstantsUmeng.RIDERECORD_OPEN;
import static com.soarsky.car.ConstantsUmeng.RIDERECORD_QUIT;
import static com.soarsky.car.ConstantsUmeng.RIDERECORD_RIDE;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2017/3/6
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  行车记录开始页面<br>
 */


public class RideRecordStratActivity extends BaseActivity implements View.OnClickListener{
    /**
     * Service
     */
    private ConfirmDriverService confirmDriverService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_ride_record_strat;
    }

    @Override
    public void initView() {
        findViewById(R.id.riderecord_ride).setOnClickListener(this);
        findViewById(R.id.riderecord_quit).setOnClickListener(this);
        findViewById(R.id.riderecord_black).setOnClickListener(this);
        findViewById(R.id.riderecord_autoopen).setOnClickListener(this);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.riderecord_ride:
                MobclickAgent.onEvent(RideRecordStratActivity.this,RIDERECORD_RIDE);
            if(confirmDriverService!=null){
                confirmDriverService.startTrvelRecord(true);
            }
                startActivity(new Intent(this, CarListActivity.class));
                break;
            case R.id.riderecord_quit:
                MobclickAgent.onEvent(RideRecordStratActivity.this,RIDERECORD_QUIT);
                confirmDriverService.startTrvelRecord(false);
                finish();
                break;
            case R.id.riderecord_autoopen:
                MobclickAgent.onEvent(RideRecordStratActivity.this,RIDERECORD_OPEN);
                startActivity(new Intent(this, AutoOpenActivity.class));
                break;
            case R.id.riderecord_black:
                finish();
                break;

        }

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


    /**
     * ServiceConnection 用来绑定服务的
     */
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            confirmDriverService=((ConfirmDriverService.LocalBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };





}
