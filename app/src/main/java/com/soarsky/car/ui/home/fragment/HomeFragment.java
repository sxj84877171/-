package com.soarsky.car.ui.home.fragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseFragment;
import com.soarsky.car.base.RxBus;
import com.soarsky.car.base.RxManager;
import com.soarsky.car.ui.borrowandreturn.BorrowCarAndReturnActivity;
import com.soarsky.car.ui.carlocation.position.CarPositionActivity;
import com.soarsky.car.ui.danger.start.DangerStartActivity;
import com.soarsky.car.ui.login.LoginActivity;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IAutoConfirmDriverListener;
import com.soarsky.car.ui.roadside.RoadSideActivity;
import com.soarsky.car.ui.validdriverlistener.ConfirmDirverActivity;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.ToastUtil;

import rx.functions.Action1;


/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 首页
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener{

    private String tag="HomeFragment";

    private Context mContext;
    /**
     * 道路救援
     */
    private TextView roadSideTv;
    /**
     * 加油洗车
     */
    private TextView goWashTv;
    /**
     * 自驾游
     */
    private TextView makeDiverTv;
    /**
     * 车友会
     */
    private TextView carFriendTv;
    /**
     * 借车换车
     */
    private TextView borrowCarTv;
    /**
     * 爱车位置
     */
    private TextView carPositionTv;
    /**
     * 请人易车
     */
    private TextView carWalkTv;
    /**
     * 地图导航
     */
    private TextView carMapTv;
    /**
     * 左边的标签
     */
    private TextView leftTv;

    private TextView connectCarNoTv;

    /**
     * 右边的icon
     */
    private ImageView rightImageView;

    private App app;

    private ConfirmDriverService confirmDriverService;

    private static final int RESULT_OK=200;

    private static final int REQUEST_CODE=100;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;


    }


    private RxManager mRxManager = new RxManager();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {

        app = (App) mContext.getApplicationContext();

        roadSideTv = (TextView)view.findViewById(R.id.roadSideTv);
        roadSideTv.setOnClickListener(this);

        goWashTv = (TextView) view.findViewById(R.id.goWashTv);
        goWashTv.setOnClickListener(this);

        makeDiverTv = (TextView) view.findViewById(R.id.makeDiverTv);
        makeDiverTv.setOnClickListener(this);

        carFriendTv = (TextView) view.findViewById(R.id.carFriendTv);
        carFriendTv.setOnClickListener(this);

        borrowCarTv = (TextView) view.findViewById(R.id.borrowCarTv);
        borrowCarTv.setOnClickListener(this);

        carPositionTv = (TextView) view.findViewById(R.id.carPositionTv);
        carPositionTv.setOnClickListener(this);

        carWalkTv = (TextView) view.findViewById(R.id.carWalkTv);
        carWalkTv.setOnClickListener(this);

        carMapTv = (TextView) view.findViewById(R.id.carMapTv);
        carMapTv.setOnClickListener(this);
        connectCarNoTv= (TextView) view.findViewById(R.id.connectcarno);

        addView(R.layout.maintoolbarleft);

        leftTv = (TextView) view.findViewById(R.id.leftTv);
        leftTv.setOnClickListener(this);

        addView(R.layout.maintoolbarright);

    }

    @Override
    protected String getHeaderTitle() {
        return getString(R.string.home_topic);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }


    private void  initView(){
        getActivity().findViewById(R.id.jiashiyuan).setOnClickListener(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  super.onCreateView(inflater, container, savedInstanceState);

        return view ;
    }

    @Override
    public void onStart() {
        super.onStart();

        RxBus.$().event(Constants.DRIVE_CARNUM_ACTION, new Action1<Object>() {
            public void call(Object carNum){
                LogUtil.i(carNum+"");

                if(connectCarNoTv != null){
                    connectCarNoTv.setText(carNum+"");
                }else{
                    ToastUtil.show(mContext,"connectCarNoTv为空");
                }

            }
        });

        Intent serviceIntent=new Intent(mContext, ConfirmDriverService.class);
        mContext.bindService(serviceIntent,serviceConnection,mContext.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        mContext.unbindService(serviceConnection);
        mRxManager.clear();
        super.onStop();
    }

    @Override
    public void onClick(View view) {


        if(app.isOnline() == false) {
            ToastUtil.show(mContext,R.string.personaltip);
            Intent i = new Intent();
            i.setClass(mContext, LoginActivity.class);
            startActivity(i);
            return;
        }


        switch (view.getId()){
            case R.id.carMapTv:
                ToastUtil.show(mContext,"carMapTv");
                break;
            case R.id.carWalkTv:
                ToastUtil.show(mContext,"carWalkTv");
                break;
            case R.id.carPositionTv:
                Intent i = new Intent();
                i.setClass(mContext, CarPositionActivity.class);
                startActivity(i);
                break;
            case R.id.borrowCarTv:
                Intent intent = new Intent();
                intent.setClass(mContext, BorrowCarAndReturnActivity.class);
                startActivity(intent);
                break;
            case R.id.carFriendTv:
                ToastUtil.show(mContext,"carFriendTv");
                break;
            case R.id.makeDiverTv:
                ToastUtil.show(mContext,"makeDiverTv");
                break;
            case R.id.goWashTv:
                ToastUtil.show(mContext,"goWashTv");
                break;
            case R.id.roadSideTv:
                Intent it = new Intent();
                it.setClass(mContext, RoadSideActivity.class);
                startActivity(it);
                break;
            case R.id.leftTv:
                ToastUtil.show(mContext,"leftTv");
                break;
            case R.id.jiashiyuan:
              
                if(app.getCommonParam().getDrivingLicence()!=null) {
                    startActivity(new Intent(mContext, ConfirmDirverActivity.class));
                }else {
                    ToastUtil.show(mContext,R.string.home_license_tip);
                }

                break;
        }
    }


    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            confirmDriverService=((ConfirmDriverService.LocalBinder) service).getService();
            confirmDriverService.setAutoConfirmDriverLisener(listener);



        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            confirmDriverService.setAutoConfirmDriverLisener(null);
            confirmDriverService=null;
        }
    };




    IAutoConfirmDriverListener listener=new IAutoConfirmDriverListener() {
        @Override
        public void onConnectCar(final Car car) {
            //TODO 自动确认驾驶员所确认的车辆
            LogUtil.i("自动确认驾驶员成功"+car.getCarNum());
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    connectCarNoTv.setText(car.getCarNum());
                }
            });



        }
    };


 

}
