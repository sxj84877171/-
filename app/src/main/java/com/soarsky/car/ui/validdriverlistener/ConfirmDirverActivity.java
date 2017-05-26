package com.soarsky.car.ui.validdriverlistener;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.base.RxBus;
import com.soarsky.car.base.RxManager;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Andriod_Car_App<br>
 * 作者：何明辉<br>
 * 时间： 2016/11/17.<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：确认驾驶员界面<br>
 * 该类为 ui界面<br>
 */


public class ConfirmDirverActivity extends BaseActivity<ConfirmDriverPresent, ConfirmDriverMode> implements ConfirmDriverView, View.OnClickListener {
    /**
     * 车辆列表显示控件
     */
    private ListView listView;
    /**
     * 附近的车辆
     */
    private List<Car> carList = new ArrayList<>();
    /**
     * Service
     */
    private ConfirmDriverService confirmDriverService;
    /**
     * listview  Adapter
     */
    private WifiListAdapter wifiListAdapter;
    /**
     * 选中的车辆
     */
    private Car car;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回
     */
    private ImageView backView;
    /**
     * 确认按钮
     */
    private Button bt_confirm;
    private App app;
    /**
     * 申请类型
     */
    private int applyType=3;

    /**
     * 提示框
     */
    ConfirmNoDialog.Builder builder;

    /**
     * 是否显示失败的dialog
     */
    private boolean isShowDialog=true;
    @Override
    public int getLayoutId() {
        return R.layout.activity_confirmdriver;
    }

    @Override
    public void initView() {
        app = (App) getApplicationContext();
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(R.string.i_am_dirver);

        bt_confirm = (Button) findViewById(R.id.bt_confirm);
        bt_confirm.setOnClickListener(this);

        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.wifi_driver);
        wifiListAdapter = new WifiListAdapter(this, carList);
        listView.setAdapter(wifiListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mPresenter.applytest(carList.get(position));
                car = carList.get(position);
                wifiListAdapter.setSelectedPosition(position);
                wifiListAdapter.notifyDataSetInvalidated();

            }
        });

    }


    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent serviceIntent = new Intent(this, ConfirmDriverService.class);
        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);
    }





    @Override
    protected void onPause() {
        unbindService(serviceConnection);
        rxManager.clear();
        super.onPause();
    }

    @Override
    protected void onStop() {

        super.onStop();


    }


    /**
     * ServiceConnection 用来绑定Service
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            confirmDriverService = ((ConfirmDriverService.LocalBinder) service).getService();
            IScan iScan = (IScan) ((ConfirmDriverService.LocalBinder) service).getService();
            mPresenter.setScan(iScan);
            mPresenter.setConfirmDriverService(confirmDriverService);
            mPresenter.listen();
            mPresenter.setIsAuto(false);
            confirmDriverService.clearDervice();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.i("onServiceDisconnected");
            mPresenter.setIsAuto(true);
            mPresenter.setScan(null);
            mPresenter.setConfirmDriverService(null);
            confirmDriverService = null;
        }
    };


    @Override
    public void showList(List<Car> list) {
        carList = list;
        wifiListAdapter.setCarList(list);
    }


    private RxManager rxManager = new RxManager();
    @Override
    public void onConfirmDriversSucess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                RxBus.$().onKeyEvent(Constants.DRIVE_CARNUM_ACTION,car.getCarNum());
                ToastUtil.show(ConfirmDirverActivity.this, "确认驾驶员成功！" + car.getCarNum());
                app.setCarNum(car.getCarNum());
                Intent intent=getIntent();
                intent.putExtra("carNum",car.getCarNum());
                setResult(RESULT_OK,intent);
                isShowDialog=false;
                finish();

            }
        });

    }

    @Override
    public void onConfirmDriversFailed() {


        if(car!=null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    resultFailed();
                }
            });
        }
    }

    /**
     * 确认驾驶员失败后续操作
     */
    private  void  resultFailed(){
        ToastUtil.show(this, "确认驾驶员失败！");
        if(!isShowDialog){
            return;
        }

        if(builder==null){
        builder= new ConfirmNoDialog.Builder(this);
        }
        builder.setTitle(R.string.dialog_ti);
        builder.setPositiveButton(R.string.zaici, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //设置你的操作事项
            }
        });

        builder.setNegativeButton(R.string.again,
                new android.content.DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.byBluetoothConnet(car,applyType);
                        dialog.dismiss();

                    }
                });

        builder.create().show();


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backView:

                isShowDialog=false;
                finish();
                break;


            case R.id.bt_confirm:
                /**
                 * 判断是否是亲情号码  还是借车
                 */
                if (car != null) {
                    car.setCarType(app.getCommonParam().getDrivingType());
                    car.setDriverNum(app.getCommonParam().getIdNo());
                    car.setPhoneNum(app.getCommonParam().getOwerPhone());
//                    mPresenter.byBluetoothConnet(car,3);

                    LogUtil.i("我自己的车："+app.getCommonParam().getCarNum());
                    int connectType=mPresenter.connectType(car.getCarNum());
                    switch (connectType){
                        case 0:
                            applyType=3;
                            mPresenter.byBluetoothConnet(car,3);
//                            mPresenter.sendApply(car,3);
                            LogUtil.i("申请3");
                        break;
                        case 1:
                            applyType=3;
                            mPresenter.byBluetoothConnet(car,3);
//                            mPresenter.sendApply(car,3);
                            LogUtil.i("申请3");
                            break;
                        case 2:
//                            String authCode = mPresenter.getAuthCode(car.getCarNum());
////                            car.setAuthNum(authCode);
                            applyType=4;
                            mPresenter.byBluetoothConnet(car,4);
//                            mPresenter.sendApply(car,4);
                            LogUtil.i("申请4");
                            break;
                        case 3:
                            applyType=5;
                            mPresenter.byBluetoothConnet(car,5);
//                            mPresenter.sendApply(car,5);
                            LogUtil.i("申请5");
                            break;
                    }


                } else {
                    ToastUtil.show(ConfirmDirverActivity.this, "请选择您要确认的车辆");
                }

                break;

        }

    }






}
