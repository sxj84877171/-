package com.soarsky.car.ui.trivelrecord.alarm;

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



public class AlarmActivity extends BaseActivity implements View.OnClickListener{

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
        return R.layout.activity_alarm;
    }

    @Override
    public void initView() {
    findViewById(R.id.riderecord_alram).setOnClickListener(this);
        findViewById(R.id.riderecord_cancle).setOnClickListener(this);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.riderecord_alram:
                if(confirmDriverService!=null){
                    confirmDriverService.trvelAlarm(true);
                }
                finish();
            break;
            case R.id.riderecord_cancle:
                if(confirmDriverService!=null){
                    confirmDriverService.trvelAlarm(false);
                }
                finish();
            break;


        }
    }
}
