package com.soarsky.car.ui.movecar;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IScan;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/30<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  请人移车，附近车辆页面<br>
 */

public class MoveCarActivity extends BaseActivity<MoveCarPresent,MoveCarModel> implements MoveCarView, View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回按钮
     */
    private ImageView backView;
    /**
     * 返回按钮
     */
 	private LinearLayout backLay;
    /**
     * 附近车辆列表
     */
    private ListView listView;
    /**
     * 附近车辆列表数据适配器
     */
    private MoveCarAdapter adapter;
    /**
     * 确定按钮
     */
    private Button button;

    /**
     * 车的集合
     */
    private List<Car> listCar=new ArrayList<>();

    /**
     * 确认驾驶员服务
     */
    private ConfirmDriverService confirmDriverService;
    /**
     * 车辆实例
     */
    private Car  car;
    /**
     * 是否选中
     */
    private boolean isSelected = true;
    /**
     * 服务是否开启
     */
    private  boolean  isAlive=true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_move_car;
    }

    @Override
    public void initView() {
        topicTv  = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.local_car_list));

        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);
        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);







        listView = (ListView) findViewById(R.id.listView);
        adapter = new MoveCarAdapter(this);
        adapter.setData(listCar);

         listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (isSelected){
                    adapter.setSelectedPosition(i);
                    isSelected = false;
                    button.setVisibility(View.VISIBLE);
                    button.setBackgroundColor(Color.parseColor("#5f95ff"));
                }else {
                    adapter.setSelectedPosition(-1);
                    isSelected = true;
                    button.setBackgroundColor(Color.parseColor("#3c3c3c"));
                }

                adapter.notifyDataSetInvalidated();
                car=listCar.get(i);

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent serviceIntent = new Intent(this, ConfirmDriverService.class);
        bindService(serviceIntent, serviceConnection, BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
        isAlive=false;
        LogUtil.i("onstop:   "+isAlive);
        mPresenter.setScan(null);
        mPresenter.setConfirmDriverService(null);
        confirmDriverService = null;
        mPresenter.setIsAuto(true);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backView:
            case R.id.backLay:
                finish();
                break;
            case R.id.button:
                if (adapter.getSelectedPosition() == -1){
                    ToastUtil.show(MoveCarActivity.this,getString(R.string.choose_car));
                }else {
                   if(car!=null){
                       car.setPhoneNum(App.getApp().getCommonParam().getOwerPhone());
                       mPresenter.sendApply(car, Constants.MOVE_CAR);
                   }


                }

                break;
        }
    }


    /**
     * 开启服务时进行的操作，扫描车辆
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
            mPresenter.setActivityAlive(true);
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN

                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    public void showList(List<Car> list) {
        listCar=new ArrayList<>(list);

        adapter.setData(listCar);

    }

    @Override
    public void showToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.show(MoveCarActivity.this,message);
            }
        });


    }

    @Override
    public void requestFail() {
        if(isAlive){
            LogUtil.i("isAlive:   "+isAlive);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog dialog = new Dialog(MoveCarActivity.this);
                View view1 = View.inflate(MoveCarActivity.this,R.layout.dialog_move_car,null);
                TextView textView1 = (TextView) view1.findViewById(R.id.message1);
                TextView textView2 = (TextView) view1.findViewById(R.id.message2);
                textView1.setText(getString(R.string.no_sim_card));
                textView2.setText(getString(R.string.no_tong_card));
                Button positiveButton = (Button) view1.findViewById(R.id.positiveButton);
                dialog.setContentView(view1);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        }




    }

    @Override
    public void noticeSuccess() {






        if(isAlive) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final Dialog dialog = new Dialog(MoveCarActivity.this);
                    View view1 = View.inflate(MoveCarActivity.this, R.layout.dialog_move_car, null);
                    ImageView title = (ImageView) view1.findViewById(R.id.title);
                    TextView textView1 = (TextView) view1.findViewById(R.id.message1);
                    TextView textView2 = (TextView) view1.findViewById(R.id.message2);
                    title.setImageResource(R.mipmap.notice);
                    textView1.setText(getString(R.string.notice_success));
                    textView2.setText(getString(R.string.notice_success_msg));
                    Button positiveButton = (Button) view1.findViewById(R.id.positiveButton);
                    dialog.setContentView(view1);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        }

    }

    @Override
    public void noticeFail() {
        if (isAlive) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final Dialog dialog = new Dialog(MoveCarActivity.this);
                    View view1 = View.inflate(MoveCarActivity.this, R.layout.dialog_move_car, null);
                    TextView textView1 = (TextView) view1.findViewById(R.id.message1);
                    TextView textView2 = (TextView) view1.findViewById(R.id.message2);
                    textView1.setText(getString(R.string.no_sim_card));
                    textView2.setText(getString(R.string.no_tong_card));
                    Button positiveButton = (Button) view1.findViewById(R.id.positiveButton);
                    dialog.setContentView(view1);
                    positiveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });


            }

        }
    }



