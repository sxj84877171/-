package com.soarsky.policeclient.ui.check;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.ui.details.DetailsActivity;
import com.soarsky.policeclient.server.CheckService;
import com.soarsky.policeclient.server.design.IScan;

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
 * 该类为  稽查界面<br>
 */

public class CheckActivity extends BaseActivity<CheckPresent, CheckModel> implements
        CheckView, View.OnClickListener {
    /**
     * 返回按钮
     */
    private RelativeLayout listIcon;

    /**
     * 稽查车辆列表GridView
     */
    private GridView gv_check;
    /**
     * 附近车辆
     */
    private TextView fujincar;
    /**
     * 适配器
     */
    private CheckCarAdapter myAdapter = null;
    /**
     * 车辆的集合
     */
    private List<Car> carList;
    /**
     * 车牌号
     */
    private String str;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onStart() {
        Intent intent = new Intent(this, CheckService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
    }

    @Override
    protected void onStop() {
        unbindService(serviceConnection);
        super.onStop();// ATTENTION: This was auto-generated to implement the App Indexing API.
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            IScan scan = (IScan) ((CheckService.LocalBinder) service).getService();
            mModel.setScan(scan);
            mPresenter.showListen();
            mPresenter.listen();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mModel.setScan(null);
        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.activity_check;
    }

    @Override
    public void initView() {
        gv_check = (GridView) findViewById(R.id.gv_check);
        listIcon = (RelativeLayout) findViewById(R.id.listIcon);
        listIcon.setOnClickListener(this);

        fujincar = (TextView) findViewById(R.id.fujincar);
        fujincar.setOnClickListener(this);
        //给稽查界面车辆添加点击事件
        gv_check.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int posiion, long id) {

               Intent intent=new Intent();
               intent.setClass(CheckActivity.this, DetailsActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("carNum", carList.get(posiion).getCarNum());//给intent添加额外数据，key为“carNum”,key值为"Intent Demo"
                startActivity(intent);
            }
        });
    }

    @Override
    public void showProgess() {

    }

    @Override
    public void stopProgess() {

    }

    @Override
    public void addCar(List<Car> car) {
        myAdapter.addCar(car);
    }

    @Override
    public void removeCar(List<Car> car) {
        myAdapter.removeCar(car);
    }

    @Override
    public void showList(List<Car> list) {
        carList = list;

        //给gridview填充数据
        myAdapter = new CheckCarAdapter(list, CheckActivity.this);
        gv_check.setAdapter(myAdapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.listIcon:
                finish();//关闭页面
                break;

        }
    }

}
