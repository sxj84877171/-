
package com.soarsky.car.ui.home.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;

import com.soarsky.car.App;
import com.soarsky.car.CommonParam;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.CheckVersionBean;
import com.soarsky.car.bean.DrivingLicenseInfo;
import com.soarsky.car.bean.DrivingLicenseInformationDataBean;
import com.soarsky.car.bean.FamilyNumIlistBean;
import com.soarsky.car.bean.QueryFamilyBean;
import com.soarsky.car.bean.QueryFamilySendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.TerminalInfoParam;
import com.soarsky.car.bean.UploadFile;
import com.soarsky.car.bean.ViolationDealIlist;
import com.soarsky.car.bean.ViolationDealInfo;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.cmd.CmdManage;
import com.soarsky.car.server.design.IAutoConfirmDriverListener;
import com.soarsky.car.server.design.ICommand;
import com.soarsky.car.server.wifi.Scan;
import com.soarsky.car.server.wifi.WifiTransfer;
import com.soarsky.car.ui.borrowandreturn.BorrowCarAndReturnActivity;
import com.soarsky.car.ui.callphone.PermissionCheck;
import com.soarsky.car.ui.carchange.CarChangeActivity;
import com.soarsky.car.ui.carlocation.lovecar.CarLocationActivity;
import com.soarsky.car.ui.danger.start.DangerStartActivity;
import com.soarsky.car.ui.family.familynum.FamilyNumActivity;
import com.soarsky.car.ui.family.familynumupdate.FamilyNumUpdateActivity;
import com.soarsky.car.ui.flashlight.FlashLightActivity;
import com.soarsky.car.ui.home.map.MainMapActivity;
import com.soarsky.car.ui.home.map.search.MapSearchActivity;
import com.soarsky.car.ui.illegal.IllegalManageActivity;
import com.soarsky.car.ui.license.DrivLicenseActivity;
import com.soarsky.car.ui.licenseinformation.DrivingLicenseInformationActivity;
import com.soarsky.car.ui.login.LoginActivity;
import com.soarsky.car.ui.modifypwd.ModifyPwdActivity;
import com.soarsky.car.ui.movecar.MoveCarActivity;
import com.soarsky.car.ui.roadside.RoadSideActivity;
import com.soarsky.car.ui.ticketed.TicketUpLoad;
import com.soarsky.car.ui.trivelrecord.riderecordstart.RideRecordStratActivity;
import com.soarsky.car.ui.validdriverlistener.ConfirmDirverActivity;
import com.soarsky.car.uitl.AppUtils;
import com.soarsky.car.uitl.ConstUtils;
import com.soarsky.car.uitl.DeviceUtils;
import com.soarsky.car.uitl.ImageUtil;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.SPUtils;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.TimeUtils;
import com.soarsky.car.uitl.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Response;

import static com.soarsky.car.ConstantsUmeng.BORROW_RETURN_CAR;
import static com.soarsky.car.ConstantsUmeng.CAR_NEWS;
import static com.soarsky.car.ConstantsUmeng.CAR_POSITION;
import static com.soarsky.car.ConstantsUmeng.DANGER_START;
import static com.soarsky.car.ConstantsUmeng.DRIVER_CARD;
import static com.soarsky.car.ConstantsUmeng.EXIT;
import static com.soarsky.car.ConstantsUmeng.FAMILY_NUM;
import static com.soarsky.car.ConstantsUmeng.FLASH_LIGHT;
import static com.soarsky.car.ConstantsUmeng.ID_CARD;
import static com.soarsky.car.ConstantsUmeng.ILLEGAL_MANAGE;
import static com.soarsky.car.ConstantsUmeng.I_AM_DRIVER;
import static com.soarsky.car.ConstantsUmeng.LEFT_BAR;
import static com.soarsky.car.ConstantsUmeng.MAP_POSITION;
import static com.soarsky.car.ConstantsUmeng.MAP_SEARCH;
import static com.soarsky.car.ConstantsUmeng.MAP_SEARCH_ICON;
import static com.soarsky.car.ConstantsUmeng.MOVE_CAR;
import static com.soarsky.car.ConstantsUmeng.PICTURE;
import static com.soarsky.car.ConstantsUmeng.PWD_SETTING;
import static com.soarsky.car.ConstantsUmeng.RIDE_RECORD;
import static com.soarsky.car.ConstantsUmeng.RIGHT_BAR;
import static com.soarsky.car.ConstantsUmeng.ROADSIDE_RESCUE;
import static com.soarsky.car.ConstantsUmeng.SERVICE;
import static com.soarsky.car.ConstantsUmeng.SWITCH_CAR;
import static com.soarsky.car.ConstantsUmeng.VOICE_SETTING;

public class MainActivity extends BaseActivity<MainPresent, MainModel> implements MainView, View.OnClickListener, BDLocationListener,EasyPermissions.PermissionCallbacks {
    /**
     * 常量--从相册选择照片
     */
    protected static final int CHOOSE_PICTURE = 0;
    /**
     * 常量--拍照
     */
    protected static final int TAKE_PICTURE = 1;
    /**
     * 常量--剪裁后显示图片
     */
    private static final int CROP_SMALL_PICTURE = 2;
    /**
     * 常量--绑定驾驶证
     */
    private static final int CONFRIM_DRIVER_CARNUM = 3;
    /**
     * 拍照时，指定的照片的存储路径
     */
    protected static Uri tempUri;
    private long exitTime = 0;
    private ConfirmDriverService confirmDriverService;
    /**
     * 实体类
     */
    private ResponseDataBean<CheckVersionBean> check;
    /**
     * 版本号路径路径
     */
    private String versionPath;
    /**
     * 左图标
     */
    private ImageView leftBarImage;
    /**
     * 右图标
     */
    private ImageView rightBarImage;
    /**
     * 根Layout
     */
    private DrawerLayout drawerLayout;
    /**
     * 地图图层
     */
    private MapView mainMapView;
    /**
     * 地图
     */
    private BaiduMap baiduMap;
    /**
     * 地图搜索布局
     */
    private LinearLayout mainMapSearchLay;
    /**
     * 搜索图标
     */
    private ImageView mainSearchView;
    /**
     * 搜索编辑框
     */
    private EditText mainSearchEt;
    /**
     * 情况布局
     */
    private LinearLayout mainCaseLay;
    /**
     * 情况文本
     */
    private TextView mainCaseTv;
    /**
     * 请人移车
     */
    private RelativeLayout mainMoveCarLay;
    /**
     * 爱车位置
     */
    private RelativeLayout mainCarPositionLay;
    /**
     * 借车还车
     */
    private RelativeLayout mainBorrowCarLay;
    /**
     * 出险报警
     */
    private RelativeLayout mainDangerAlarmLay;
    /**
     * 道路救援
     */
    private RelativeLayout mainFaultRescueLay;
    /**
     * 汽车资讯
     */
    private RelativeLayout mainCarInfoLay;
    /**
     * 我是驾驶员
     */
    private TextView mainDriverTv;
    /**
     * 弹出两个按钮布局
     */
    private LinearLayout mainPositionLay;
    /**
     * 闪灯找车
     */
    private TextView mainSearchCarTv;
    /**
     * 地图定位
     */
    private TextView mainMapPositionTv;
    /**
     * 声明LocationClient类
     */
    private LocationClient mLocationClient = null;
    /**
     * 是否首次定位
     */
    private boolean isFirstLoc = true;
    /**
     * 违章管理
     */
    private RelativeLayout rl_lllegal;
    /**
     * 驾驶证
     */
    private RelativeLayout id_card;
    /**
     * 行驶证
     */
    private RelativeLayout driver_card;
    /**
     * 车牌号
     */
    private TextView tv_car_num;
    /**
     * 行驶证年检到期
     */
    private TextView date;
    /**
     * 驾驶证号
     */
    private TextView tv_id_card_num;
    /**
     * 驾驶证年检到期
     */
    private TextView tv_year_date;

    private TextView year_date;
    /**
     * 退出账号
     */
    private RelativeLayout rl_exit;
    /**
     * 设置
     */
    private RelativeLayout rl_setting;
    /**
     * 客服
     */
    private RelativeLayout rl_service;
    /**
     * 用户头像
     */
    private ImageView cover_user_photo;
    private RelativeLayout rl_picture;
    private App app;
    /**
     * 音量设置
     */
    private RelativeLayout rl_voice_setting;
    /**
     * 亲情号设置
     */
    private RelativeLayout rl_phone_setting;

    /**
     * 确认驾驶员服务
     */
    Intent serviceIntent;
    /**
     * 是否进行音量设置
     */
    private boolean isSettingVoice = false;
    private LinearLayout linearLayout1;
    private LinearLayout linearLayout2;

    /**
     * 声音设置滑块
     */
    private VerticalSeekBar voiceSeekBar;
    /**
     * 切换车辆
     */
    private RelativeLayout rl_switch_car;
    /**
     * 违章管理未读条数
     */
    private TextView num_bar;

    private final static String TAG = "MainActivity";
    private LinearLayout drawerlayout;

    private Marker marker;

    private RelativeLayout mainMapRootLay;

    private RelativeLayout mainMapIconLay;
    /**
     * 当前城市
     */
    private String city = "";


    private TextView driverCarnum;
    /**
     * 用于拍照的标志位，来区分首页刷新
     */
    private boolean photo_flag = true;

    /**
     * file
     */
    private File futureStudioIconFile;

    private Car car;

    private SPUtils spUtils;

    private TextView title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        app = (App) getApplication();
        app.addActivity(TAG, this);
        SpUtil.init(this);
        if (TextUtils.isEmpty(SpUtil.get("volume"))) {
            SpUtil.save("volume", "5");
        }
        showSettingPermissionDialog();

        leftBarImage = (ImageView) findViewById(R.id.leftBarImage);
        rightBarImage = (ImageView) findViewById(R.id.rightBarImage);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        driverCarnum = (TextView) findViewById(R.id.mainDriverTv);
        leftBarImage.setOnClickListener(this);
        rightBarImage.setOnClickListener(this);

        mainMapView = (MapView) findViewById(R.id.mainMapView);
        mainMapView.setOnClickListener(this);

        mainMapSearchLay = (LinearLayout) findViewById(R.id.mainMapSearchLay);
        mainMapSearchLay.setOnClickListener(this);
        mainSearchView = (ImageView) findViewById(R.id.mainSearchView);
        mainSearchEt = (EditText) findViewById(R.id.mainSearchEt);
        mainSearchEt.setOnClickListener(this);

        mainCaseLay = (LinearLayout) findViewById(R.id.mainCaseLay);
        mainCaseTv = (TextView) findViewById(R.id.mainCaseTv);

        mainMoveCarLay = (RelativeLayout) findViewById(R.id.mainMoveCarLay);
        mainMoveCarLay.setOnClickListener(this);

        mainCarPositionLay = (RelativeLayout) findViewById(R.id.mainCarPositionLay);
        mainCarPositionLay.setOnClickListener(this);
        mainBorrowCarLay = (RelativeLayout) findViewById(R.id.mainBorrowCarLay);
        mainBorrowCarLay.setOnClickListener(this);

        mainDangerAlarmLay = (RelativeLayout) findViewById(R.id.mainDangerAlarmLay);
        mainDangerAlarmLay.setOnClickListener(this);

        mainFaultRescueLay = (RelativeLayout) findViewById(R.id.mainFaultRescueLay);
        mainFaultRescueLay.setOnClickListener(this);

        mainCarInfoLay = (RelativeLayout) findViewById(R.id.mainCarInfoLay);
        mainCarInfoLay.setOnClickListener(this);

        mainDriverTv = (TextView) findViewById(R.id.mainDriverTv);
        mainDriverTv.setOnClickListener(this);

        mainPositionLay = (LinearLayout) findViewById(R.id.mainPositionLay);
        mainSearchCarTv = (TextView) findViewById(R.id.mainSearchCarTv);
        mainSearchCarTv.setOnClickListener(this);

        mainMapPositionTv = (TextView) findViewById(R.id.mainMapPositionTv);
        mainMapPositionTv.setOnClickListener(this);

        tv_car_num = (TextView) findViewById(R.id.tv_car_num);
        date = (TextView) findViewById(R.id.date);
        tv_year_date = (TextView) findViewById(R.id.tv_year_date);
        year_date = (TextView) findViewById(R.id.year_date);

        driver_card = (RelativeLayout) findViewById(R.id.driver_card);
        driver_card.setOnClickListener(this);

        id_card = (RelativeLayout) findViewById(R.id.id_card);
        id_card.setOnClickListener(this);

        tv_id_card_num = (TextView) findViewById(R.id.tv_id_card_num);

        rl_lllegal = (RelativeLayout) findViewById(R.id.rl_lllegal);
        rl_lllegal.setOnClickListener(this);

        rl_exit = (RelativeLayout) findViewById(R.id.rl_exit);
        rl_exit.setOnClickListener(this);

        rl_setting = (RelativeLayout) findViewById(R.id.rl_setting);
        rl_setting.setOnClickListener(this);

        rl_service = (RelativeLayout) findViewById(R.id.rl_service);
        rl_service.setOnClickListener(this);

        cover_user_photo = (ImageView) findViewById(R.id.cover_user_photo);
        rl_picture = (RelativeLayout) findViewById(R.id.rl_picture);
        rl_picture.setOnClickListener(this);

        rl_voice_setting = (RelativeLayout) findViewById(R.id.rl_voice_setting);
        rl_voice_setting.setOnClickListener(this);

        rl_phone_setting = (RelativeLayout) findViewById(R.id.rl_phone_setting);
        rl_phone_setting.setOnClickListener(this);


        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        linearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);

        rl_switch_car = (RelativeLayout) findViewById(R.id.rl_switch_car);
        rl_switch_car.setOnClickListener(this);
        voiceSeekBar = (VerticalSeekBar) findViewById(R.id.voice_SeekBar);
        voiceSeekBar.setProgress(Integer.parseInt(SpUtil.get("volume")));


        drawerlayout = (LinearLayout) findViewById(R.id.ll);
        drawerlayout.setOnClickListener(this);

        num_bar = (TextView) findViewById(R.id.num_bar);

        mainMapRootLay = (RelativeLayout) findViewById(R.id.mainMapRootLay);
        mainMapRootLay.setOnClickListener(this);

        mainMapIconLay = (RelativeLayout) findViewById(R.id.mainMapIconLay);
        mainMapIconLay.setOnClickListener(this);

        findViewById(R.id.rl_trivelrecord).setOnClickListener(this);

        title = (TextView) findViewById(R.id.titleTv);
        title.setText(getString(R.string.main_title) + " " + AppUtils.getVersion(this));

    }

    /**
     * 在点击首页屏幕的时候判断两个悬浮球是否显示，显示就隐藏
     *
     * @param ev 事件
     * @return 是否拦截事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                if (mainPositionLay.getVisibility() == View.VISIBLE) {
                    mainPositionLay.setVisibility(View.GONE);
                    mainMapSearchLay.setVisibility(View.VISIBLE);
                    mainCaseLay.setVisibility(View.GONE);
                    //return true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baiduMap = mainMapView.getMap();
        baiduMap.setMyLocationEnabled(true);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(this);
        initLocation();
        mLocationClient.start();
        // 设置为一般地图
        baiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        if (SpUtil.get(Constants.IMAGE_URL) != null && !("".equals(SpUtil.get(Constants.IMAGE_URL)))) {
            ImageUtil.loadImg(cover_user_photo, SpUtil.get(Constants.IMAGE_URL));
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        // 不需要每次回到页面就重新获取这些信息。只需要应用程序打开时，更新一次，修改by Elvis
//        mPresenter.initData();
        mPresenter.changeCarText(tv_car_num,tv_id_card_num);

        /**
         * 绑定服务
         */
        startService();

        Scan.getInstance(this);

        mainMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainMapView.onPause();
        unbindService(serviceConnection);
    }


    @Override
    protected void onDestroy() {
        if (serviceIntent != null) {
            LogUtil.i(getString(R.string.stop_service));
            stopService(serviceIntent);
            serviceIntent=null;
        }
        //退出时销毁定位
        mLocationClient.stop();
        baiduMap.setMyLocationEnabled(false);
        super.onDestroy();
        mainMapView.onDestroy();
        mainMapView = null;
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    private Handler myHandler = new Handler();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftBarImage:
                MobclickAgent.onEvent(MainActivity.this, LEFT_BAR);
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.rightBarImage:
                MobclickAgent.onEvent(MainActivity.this, RIGHT_BAR);
                drawerLayout.openDrawer(Gravity.RIGHT);
                break;
            case R.id.mainDriverTv:
                MobclickAgent.onEvent(MainActivity.this, I_AM_DRIVER);
                if (SpUtil.get(Constants.LICESE_NUM) != null && !SpUtil.get(Constants.LICESE_NUM).equals("")) {
                    if (App.getApp().isConfirmDriver()) {
                        exitConfirmDriver();
                    } else {
                        startActivityForResult(new Intent(mContext, ConfirmDirverActivity.class), CONFRIM_DRIVER_CARNUM);
                    }
                } else {
                    ToastUtil.show(mContext, R.string.home_license_tip);
                }
                break;
            case R.id.mainMoveCarLay:
                MobclickAgent.onEvent(MainActivity.this, MOVE_CAR);
                startActivity(new Intent(mContext, MoveCarActivity.class));
                break;
            case R.id.mainCarPositionLay:
                MobclickAgent.onEvent(MainActivity.this, CAR_POSITION);
                if (mainPositionLay.getVisibility() == View.GONE) {
                    mainPositionLay.setVisibility(View.VISIBLE);
                    mainMapSearchLay.setVisibility(View.GONE);
                    mainCaseLay.setVisibility(View.VISIBLE);
                    if (TextUtils.isEmpty(SpUtil.get("TROUBLESTR"))) {
                        mainCaseTv.setText(getString(R.string.car_is_good));
                    } else {
                        mainCaseTv.setText(SpUtil.get("TROUBLESTR"));
                    }

                } else {
                    mainPositionLay.setVisibility(View.GONE);
                    mainMapSearchLay.setVisibility(View.VISIBLE);
                    mainCaseLay.setVisibility(View.GONE);
                }
                break;
            case R.id.mainBorrowCarLay:
                MobclickAgent.onEvent(MainActivity.this, BORROW_RETURN_CAR);
                if (SpUtil.get(Constants.LICESE_NUM) != null && !SpUtil.get(Constants.LICESE_NUM).equals("")) {
                    Log.d("TAG", "num ==" + SpUtil.get(Constants.LICESE_NUM));
                    startActivity(new Intent(MainActivity.this, BorrowCarAndReturnActivity.class));
                } else {
                    new AlertDialog.Builder(mContext).setTitle(R.string.borrow_car_title)
                            .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“返回”后的操作,这里不设置没有任何操作
                                }
                            }).show();
                }
                break;
            case R.id.mainDangerAlarmLay:
                MobclickAgent.onEvent(MainActivity.this, DANGER_START);
                startActivity(new Intent(MainActivity.this, DangerStartActivity.class));
                break;
            case R.id.mainFaultRescueLay:
                MobclickAgent.onEvent(MainActivity.this, ROADSIDE_RESCUE);
                Intent it = new Intent();
                it.setClass(this, RoadSideActivity.class);
                startActivity(it);
                break;
            case R.id.mainCarInfoLay:
                MobclickAgent.onEvent(MainActivity.this, CAR_NEWS);
                break;

            case R.id.driver_card:
                MobclickAgent.onEvent(MainActivity.this, DRIVER_CARD);
                //行驶证详情
                startActivity(new Intent(MainActivity.this, DrivingLicenseInformationActivity.class));
                break;
            case R.id.id_card:
                MobclickAgent.onEvent(MainActivity.this, ID_CARD);
                //驾驶证详情

                if (SpUtil.get(Constants.LICESE_NUM) != null && !SpUtil.get(Constants.LICESE_NUM).equals("")) {

                    startActivity(new Intent(MainActivity.this, DrivLicenseActivity.class));
//                startActivity(new Intent(MainActivity.this, LicenseValidationActivity.class));
                } else {
                    new AlertDialog.Builder(mContext).setTitle(R.string.driver_confirm_title)
                            .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“返回”后的操作,这里不设置没有任何操作
                                }
                            }).show();
                }

                break;
            case R.id.rl_lllegal:
                MobclickAgent.onEvent(MainActivity.this, ILLEGAL_MANAGE);
                //违章管理
                startActivity(new Intent(MainActivity.this, IllegalManageActivity.class));
                break;
            case R.id.rl_exit:
                MobclickAgent.onEvent(MainActivity.this, EXIT);
                //退出账号
                if (app.isOnline() == true) {

                    new AlertDialog.Builder(mContext).setTitle(mContext.getString(R.string.back_title))
                            .setPositiveButton(mContext.getString(R.string.back_sure), new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“确认”后的操作
                                    app.setOnline(false);
//                                    SpUtil.save(Constants.CONS_USERNAME,"");
                                    SpUtil.saveUserName(MainActivity.this, "");

                                    App.getApp().setConfirmDriver(false);
                                    //app.exit();

                                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                    MobclickAgent.onProfileSignOff();
                                    finish();

                                }
                            })
                            .setNegativeButton(mContext.getString(R.string.back_cancel), new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“返回”后的操作,这里不设置没有任何操作
                                }
                            }).show();
                }
                break;
            case R.id.rl_setting:
                MobclickAgent.onEvent(MainActivity.this, PWD_SETTING);
                //密码设置
                Intent in = new Intent();
                in.setClass(this, ModifyPwdActivity.class);
                startActivity(in);
                break;
            case R.id.rl_service:
                MobclickAgent.onEvent(MainActivity.this, SERVICE);
                //客服

                break;
            case R.id.rl_picture:
                MobclickAgent.onEvent(MainActivity.this, PICTURE);
                showChoosePicDialog();
                break;
            case R.id.mainSearchCarTv:
                MobclickAgent.onEvent(MainActivity.this, FLASH_LIGHT);
                if (SpUtil.get(Constants.CAR_NUM) != null && !SpUtil.get(Constants.CAR_NUM).equals("")) {
                    startActivity(new Intent(this, FlashLightActivity.class));
                } else {
                    new AlertDialog.Builder(mContext).setTitle(R.string.car_confirm_title)
                            .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“返回”后的操作,这里不设置没有任何操作
                                }
                            }).show();
                }
                break;
            case R.id.mainMapPositionTv:
                MobclickAgent.onEvent(MainActivity.this, MAP_POSITION);

                if (SpUtil.get(Constants.CAR_NUM) != null && !SpUtil.get(Constants.CAR_NUM).equals("")) {
                    startActivity(new Intent(this, CarLocationActivity.class));
                } else {
                    new AlertDialog.Builder(mContext).setTitle(R.string.car_confirm_title)
                            .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“返回”后的操作,这里不设置没有任何操作
                                }
                            }).show();
                }

                break;
            case R.id.rl_voice_setting://音量设置
                MobclickAgent.onEvent(MainActivity.this, VOICE_SETTING);
                if (app.isConfirmDriver()) {
                    if (isSettingVoice) {
                        linearLayout1.setVisibility(View.GONE);
                        linearLayout2.setVisibility(View.VISIBLE);
                        isSettingVoice = false;
                      /*
                        car = new Car();
                        car.setSsid(app.getSsID());
                        car.setCarNum(app.getCarNum());
//                  链接car
                        mPresenter.sendApply(car, 14);
                        */

                    } else {
                        linearLayout1.setVisibility(View.VISIBLE);
                        linearLayout2.setVisibility(View.GONE);
                        isSettingVoice = true;
                    }


                    voiceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                            if (progress < 3) {
                                seekBar.setProgress(3);
                                ToastUtil.show(MainActivity.this, getString(R.string.voice_no_smaller));
                            }
                            voice = progress;
                            SpUtil.save("volume", String.valueOf(voice));
                            myHandler.removeCallbacks(excuSendVolume);
                            myHandler.postDelayed(excuSendVolume, 500);
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {
                            LogUtil.i("volume" + voice);
                        }
                    });


                } else {
                    new AlertDialog.Builder(mContext).setTitle(R.string.main_confirm_title)
                            .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“返回”后的操作,这里不设置没有任何操作
                                }
                            }).show();
                }


                break;
            case R.id.rl_phone_setting:
                //亲情号设置
                MobclickAgent.onEvent(MainActivity.this, FAMILY_NUM);
                if (SpUtil.get(Constants.CAR_NUM) != null && !SpUtil.get(Constants.CAR_NUM).equals("")) {
                    mPresenter.deleteAll();
                } else {
                    new AlertDialog.Builder(mContext).setTitle(R.string.family_confirm_title)
                            .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“返回”后的操作,这里不设置没有任何操作
                                }
                            }).show();
                }
                break;
            case R.id.rl_switch_car:
                MobclickAgent.onEvent(MainActivity.this, SWITCH_CAR);
                startActivity(new Intent(this, CarChangeActivity.class));
                break;
            case R.id.drawerlayout:
                break;
            case R.id.mainMapRootLay:
                break;
            case R.id.mainMapIconLay:
                MobclickAgent.onEvent(MainActivity.this, MAP_SEARCH_ICON);
                startActivity(new Intent(this, MainMapActivity.class));
                break;
            case R.id.mainSearchEt:
            case R.id.mainMapSearchLay:
                MobclickAgent.onEvent(MainActivity.this, MAP_SEARCH);
                Intent i = new Intent(this, MapSearchActivity.class);
                startActivity(i);
                break;
            case R.id.rl_trivelrecord:
                MobclickAgent.onEvent(MainActivity.this, RIDE_RECORD);
                startActivity(new Intent(this, RideRecordStratActivity.class));
                break;
        }
    }


    private Runnable excuSendVolume = new Runnable() {
        @Override
        public void run() {
            confirmDriverService.setVolume();
        }
    };

    /**
     * 显示修改头像的对话框
     */
    protected void showChoosePicDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.set_pic));
        String[] items = {getString(R.string.choice_pic), getString(R.string.took_pic)};
        builder.setNegativeButton(getString(R.string.set_cancel), null);
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case CHOOSE_PICTURE: // 选择本地照片
                        photo_flag = false;
                        Intent i = new Intent(
                                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(Intent.createChooser(i, "选择图片"), CHOOSE_PICTURE);
                        break;
                    case TAKE_PICTURE: // 拍照
                        photo_flag = false;
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        tempUri = Uri.fromFile(new File(Environment
                                .getExternalStorageDirectory(), "image.jpg"));
                        // 指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                        startActivityForResult(openCameraIntent, TAKE_PICTURE);


                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.i("onActivityResult");
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:

                    startPhotoZoom(tempUri); // 开始对图片进行裁剪处理

                    break;
                case CHOOSE_PICTURE:
                    //photo = Utils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理

                    break;
                case CROP_SMALL_PICTURE:
                    LogUtil.i("CROP_SMALL_PICTURE");
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
                case CONFRIM_DRIVER_CARNUM:
                    if (data != null) {
                        String carNum = data.getStringExtra("carNum");
                        driverCarnum.setText(carNum);
                    }
                    LogUtil.i("onActivityResult-----------------------------------------");
                    break;
            }
        }
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
        }
        tempUri = uri;
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // 设置裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 86);
        intent.putExtra("outputY", 86);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     *
     * @param
     * @param
     */
    protected void setImageToView(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            //photo = Utils.toRoundBitmap(photo, tempUri); // 这个时候的图片已经被处理成圆形的了
            cover_user_photo.setImageBitmap(photo);
            uploadPic(photo);
        }
    }

    /**
     * 上传头像至服务器
     *
     * @param bitmap
     */
    private void uploadPic(Bitmap bitmap) {
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        String imagePath = ImageUtil.saveFile(bitmap, "image.jpg");

        if (imagePath != null) {
            // 拿着imagePath上传了
            mPresenter.uploadImg(imagePath);
        }
    }

    /**
     * 图片上传成功
     *
     * @param uploadFile
     */
    @Override
    public void uploadImgSuccess(UploadFile uploadFile) {

        if (uploadFile != null) {
//Constants.REQUEST_SUCCESS
            if (Constants.REQUEST_SUCCESS.equals(uploadFile.getCode())) {
                if (uploadFile.getData().size() > 0) {
                    app.setImageUrl(uploadFile.getData().get(0).getFileUrl());
                    SpUtil.save(Constants.IMAGE_URL, uploadFile.getData().get(0).getFileUrl());
                    mPresenter.modifyAppImage(uploadFile.getData().get(0).getFileUrl(), app.getCommonParam().getUserName());
                }

            } else {
                ToastUtil.show(this, uploadFile.getMsg());
            }
        }
    }

    /**
     * 图片上传失败
     */
    @Override
    public void uploadImgFail() {
        ToastUtil.show(this, R.string.no_connet_internet);
    }

    @Override
    public void modifyAppImageSuccess(ResponseDataBean<String> stringResponseDataBean) {

        if (stringResponseDataBean != null) {
            if (Constants.REQUEST_SUCCESS.equals(stringResponseDataBean.getStatus())) {
                photo_flag = true;
            }
        }
    }

    @Override
    public void modifyAppImageFail() {

    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {


        if (bdLocation == null || mainMapView == null)
            return;
        if (isFirstLoc) {
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius()).direction(100).latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude()).build();
            city = bdLocation.getCity();
            app.setCity(city);
            mainSearchEt.setText(bdLocation.getAddrStr());
            LatLng ll = new LatLng(bdLocation.getLatitude(),
                    bdLocation.getLongitude());
            //设置地图中心点以及缩放级别
            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(ll, 16);
            baiduMap.animateMapStatus(u);
            //设置定位数据
            baiduMap.setMyLocationData(locData);
            baiduMap.clear();
            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.drawable.carlocation);
            //构建MarkerOption，用于在地图上添加Marker
            OverlayOptions option = new MarkerOptions()
                    .position(ll)
                    .icon(bitmap)
                    .zIndex(9);

            //在地图上添加Marker，并显示
            marker = (Marker) baiduMap.addOverlay(option);
            isFirstLoc = false;
        }
        App.getApp().setLatitude(bdLocation.getLatitude());
        App.getApp().setLongitude(bdLocation.getLongitude());
        App.getApp().setSpeed(bdLocation.getSpeed());

        if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
        }
    }

    /**
     * 配置定位SDK参数
     */
    private void initLocation() {

        LocationClientOption option = new LocationClientOption();
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);

        option.setOpenGps(true);
        //可选，默认gcj02，设置返回的定位结果坐标系
        option.setCoorType("bd09ll");
        int span = 1000;
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setScanSpan(span);
        //可选，设置是否需要地址信息，默认不需要
        option.setIsNeedAddress(true);
        //可选，默认false,设置是否使用gps
        option.setOpenGps(true);
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIsNeedLocationPoiList(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIgnoreKillProcess(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        option.setEnableSimulateGps(false);
        mLocationClient.setLocOption(option);
    }

    private void startService() {
        serviceIntent = new Intent(this, ConfirmDriverService.class);
        mContext.startService(serviceIntent);
        mContext.bindService(serviceIntent,
                serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 获取亲情号成功
     *
     * @param param 返回亲情号的参数
     */
    @Override
    public void showFamilySuccess(ResponseDataBean<QueryFamilyBean> param) {
        if (param != null) {
            if (Constants.REQUEST_SUCCESS.equals(param.getStatus())) {
                if (param.getData().getIlist().size() > 0) {
                    //插入数据库
                    insertFamilyNum(param.getData().getIlist());
                    List<FamilyNumIlistBean> _list = new ArrayList<FamilyNumIlistBean>();
//                  剔除车主号码
                    for (FamilyNumIlistBean bean : param.getData().getIlist()) {
                        if (bean.getIsOwner() == 0) {
                            _list.add(bean);
                        }
                    }
                    if (_list.size() > 0) {
                        Intent i = new Intent();
                        i.setClass(this, FamilyNumUpdateActivity.class);
                        startActivity(i);
                    } else {
                        Intent it = new Intent();
                        it.setClass(this, FamilyNumActivity.class);
                        startActivity(it);
                    }
                } else {
                    Intent it = new Intent();
                    it.setClass(this, FamilyNumActivity.class);
                    startActivity(it);
                }
            } else {
                ToastUtil.show(this, param.getMessage());
                Intent it = new Intent();
                it.setClass(this, FamilyNumActivity.class);
                startActivity(it);
            }
        }
    }

    /**
     * 获取亲情号失败
     */
    @Override
    public void showFamilyFail() {
        ToastUtil.show(this, R.string.throwable);
    }

    /**
     * 清空亲情号表的所有数据成功
     */
    @Override
    public void deleteAllSuccess() {
        QueryFamilySendParam p = new QueryFamilySendParam();
        p.setPhone(app.getCommonParam().getOwerPhone());
        p.setUsername(app.getCommonParam().getUserName());
        p.setCarnum(app.getCommonParam().getCarNum());
        mPresenter.queryFirendPhone(p);
    }

    /**
     * 清空亲情号表的所有数据失败
     */
    @Override
    public void deleteAllFail() {
        ToastUtil.show(this, R.string.familynum_change_fail);
    }

    /**
     * 获取未读违章记录条数失败
     *
     * @param e 错误
     */
    @Override
    public void showError(Throwable e) {
        //不需要toastmessage,打印日志记录便可  王松清
        //ToastUtil.show(this,e.getMessage());
        LogUtil.i("MainActivity", e.getMessage());
    }

    /**
     * 获取未读违章记录条数成功
     *
     * @param violationDealParam 违章参数
     */
    @Override
    public void showSuccess(ResponseDataBean<ViolationDealInfo> violationDealParam) {
        if (violationDealParam != null) {
            if (violationDealParam.getStatus().equals(SUCCESS_FLAG)) {
//                if (violationDealParam.getData().getDealCount() == 0) {
//                    num_bar.setVisibility(View.GONE);
//                } else {
//                    num_bar.setVisibility(View.VISIBLE);
////                    num_bar.setText("" + violationDealParam.getData().getDealCount());
//                    mPresenter.showNotification(this, violationDealParam.getData().getDealCount());
//                }

                List<ViolationDealIlist> list = violationDealParam.getData().getIlist();
                if(list != null){

                    if(IllegalUtils.JudgeNotIllegal(list)>0){
                        num_bar.setVisibility(View.VISIBLE);
                        mPresenter.showNotification(this, IllegalUtils.JudgeNotIllegal(list));
                    }else {
                        num_bar.setVisibility(View.GONE);
                    }
                }
            }
        }
    }

    /**
     * 插入数据
     *
     * @param list 数据源
     */
    private void insertFamilyNum(List<FamilyNumIlistBean> list) {
        for (FamilyNumIlistBean bean : list) {
            FamilyNum familyNum = new FamilyNum();
            familyNum.setPhone(bean.getPhone());
            familyNum.setUsername(app.getCommonParam().getUserName());
            familyNum.setCarnum(app.getCommonParam().getCarNum());
            familyNum.setIs_owner(bean.getIsOwner());
            familyNum.setSstate(1);
            familyNum.setTstate(0);
            mPresenter.insertFamilyNum(familyNum);
        }
    }


    /**
     * 获取驾驶证成功
     *
     * @param param 参数
     */
    @Override
    public void showLicenseSuccess(ResponseDataBean<DrivingLicenseInfo> param) {
        tv_id_card_num.setText(app.getCommonParam().getDrivingLicence());
        try {
            if (app.getCommonParam().getRegisterDriverDate() != null) {
                if (!(TextUtils.isEmpty(app.getCommonParam().getRegisterDriverDate()))) {
                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = sDateFormat.parse(app.getCommonParam().getRegisterDriverDate());
                    // 车的年检日期
                    Date dd = TimeUtils.addYear(d, 1);
                    long ddd = dd.getTime() - new Date().getTime();
                    year_date.setText("" + roundCleanDay(TimeUtils.milliseconds2Unit(ddd, ConstUtils.DAY)) + "天");
                } else {
                    year_date.setText(getString(R.string.nodate));
                }
            } else {
                year_date.setText(getString(R.string.nodate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private long roundCleanDay(long day) {
        while (day < 0) {
            day += 365;
        }

        while (day >= 365) {
            day -= 365;
        }

        return day;
    }

    @Override
    public void getDriverListSuccess(ResponseDataBean<List<DrivingLicenseInformationDataBean>> carNumParam) {
        if (carNumParam != null) {
            if (Constants.REQUEST_SUCCESS.equals(carNumParam.getStatus())) {
                try {
                    tv_car_num.setText(carNumParam.getData().get(0).getPlateno());
                    if (carNumParam.getData().get(0).getRegisterdate() != null) {
                        if (!(TextUtils.isEmpty(carNumParam.getData().get(0).getRegisterdate()))) {
                            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date d = sDateFormat.parse(carNumParam.getData().get(0).getRegisterdate());
                            // 车的年检日期
                            Date dd = TimeUtils.addYear(d, 1);
                            long ddd = dd.getTime() - new Date().getTime();
                            date.setText("" + roundCleanDay(TimeUtils.milliseconds2Unit(ddd, ConstUtils.DAY)) + "天");
                        } else {
                            date.setText(getString(R.string.nodate));
                        }
                    } else {
                        date.setText(getString(R.string.nodate));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * 获取驾驶证失败
     */
    @Override
    public void showLicenseError() {

    }

    @Override
    public void getTerminalInfoSuccess(int index, ResponseDataBean<TerminalInfoParam> terminalInfoParam) {

        if (terminalInfoParam != null) {
            if (Constants.REQUEST_SUCCESS.equals(terminalInfoParam.getStatus())) {
                app.getCar_list().get(index).setAuthcode(terminalInfoParam.getData().getWarrant());
            }
        }

    }

    @Override
    public void getTerminalInfoFail() {

    }


    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            confirmDriverService = ((ConfirmDriverService.LocalBinder) service).getService();
            mPresenter.setConfirmDriverService(confirmDriverService);
            confirmDriverService.setAutoConfirmDriverLisener(listener);
            confirmDriverService.startWork();
            confirmDriverService.setAuto(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            confirmDriverService.setAutoConfirmDriverLisener(null);
            confirmDriverService = null;
        }
    };


    IAutoConfirmDriverListener listener = new IAutoConfirmDriverListener() {
        @Override
        public void onConnectCar(final Car car) {
            //TODO 自动确认驾驶员所确认的车辆
            LogUtil.i("自动确认驾驶员成功" + car.getCarNum());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    driverCarnum.setText(car.getCarNum());
                }
            });
        }

        @Override
        public void onDriverOffLine() {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    driverCarnum.setText(getString(R.string.i_am_driver));
                }
            });
        }
    };

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            drawerLayout.closeDrawer(Gravity.RIGHT);
        } else if (drawerLayout.isDrawerOpen(Gravity.LEFT)) {
            drawerLayout.closeDrawer(Gravity.LEFT);
        } else {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    }


    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            ToastUtil.show(this, getString(R.string.press_agin_to_exit));
            exitTime = System.currentTimeMillis();
        } else {
            app.exit();
            finish();
            System.exit(0);
        }
    }


    private void showSettingPermissionDialog() {
//
//        if(Build.VERSION.SDK_INT<=Build.VERSION_CODES.KITKAT){
//          return;
//        }

        if (!PermissionCheck.HasACTION_USAGE_ACCESS_SETTINGSPermission(this)) {
            new AlertDialog.Builder(mContext).setMessage(getString(R.string.open_uses_permission))
                    .setPositiveButton(getString(R.string.familynumbersure), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 点击“确认”后的操作
                            startACTION_USAGE_ACCESS_SETTINGSPermissionActivity();
                        }
                    })
                    .setNegativeButton(mContext.getString(R.string.back_cancel), new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 点击“返回”后的操作,这里不设置没有任何操作
                        }
                    }).show();
        }

    }


    /**
     * 打开权限界面
     */
    public void startACTION_USAGE_ACCESS_SETTINGSPermissionActivity() {
        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        //intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);    //通过尝试这个flag符合
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }{
            ToastUtil.show(this,R.string.un_get_permission);
        }
    }


    /**
     * 检测版本号成功的回调函数
     *
     * @param checkVersion 版本检测业务参数
     */
    @Override
    public void checkSuccess(ResponseDataBean<CheckVersionBean> checkVersion) {
        check = checkVersion;
        versionPath = checkVersion.getData().getFileurl();
        String versionName = DeviceUtils.getVersionName(MainActivity.this);
        if (DeviceUtils.compareVersion(versionName, checkVersion.getData().getVersion())) {
            showUpdateDialog(getString(R.string.update_version));
        }
    }

    @Override
    public void checkFail(ResponseDataBean<CheckVersionBean> checkVersion) {

    }

    /**
     * 安装文件下载成功
     *
     * @param call
     * @param response
     */
    @Override
    public void loadSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            writeResponseBodyToDisk(response.body());
        }
    }

    @Override
    public void loadFail(Call<ResponseBody> call, Throwable t) {
        ToastUtil.show(MainActivity.this, R.string.load_fail);
    }

    /**
     * 显示自动更新的对话框
     *
     * @param desc 描述
     */
    protected void showUpdateDialog(String desc) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle(R.string.update);
        builder.setMessage(desc.toString());
        builder.setPositiveButton(R.string.update_now, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //下载文件
                mPresenter.loadFile(versionPath);

            }
        });
        builder.setNegativeButton(R.string.update_next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });
        builder.show();
    }

    private void writeResponseBodyToDisk(final ResponseBody body) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // todo change the file location/name according to your needs
                    futureStudioIconFile = new File("/sdcard/" + check.getData().getName() + ".apk");
                    InputStream inputStream = null;
                    OutputStream outputStream = null;
                    try {
                        byte[] fileReader = new byte[4096];
                        long fileSize = body.contentLength();
                        long fileSizeDownloaded = 0;
                        inputStream = body.byteStream();
                        outputStream = new FileOutputStream(futureStudioIconFile);
                        while (true) {
                            int read = inputStream.read(fileReader);
                            if (read == -1) {
                                break;
                            }
                            outputStream.write(fileReader, 0, read);
                            outputStream.flush();
                            fileSizeDownloaded += read;
                            Log.d("TAG", "file download: " + fileSizeDownloaded + " of " + fileSize);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.show(MainActivity.this, R.string.load_success);
                                // 覆盖安装apk文件
                                Intent intent = new Intent();
                                intent.setAction("android.intent.action.VIEW");
                                intent.addCategory("android.intent.category.DEFAULT");
                                intent.setDataAndType(
                                        Uri.fromFile(futureStudioIconFile),
                                        "application/vnd.android.package-archive");
                                startActivity(intent);
                            }
                        });
                        return;
                    } catch (IOException e) {
                        return;
                    } finally {
                        if (inputStream != null) {
                            inputStream.close();
                        }

                        if (outputStream != null) {
                            outputStream.close();
                        }
                    }
                } catch (IOException e) {
                    return;
                }
            }
        }).start();

    }

    int voice = 3;

    /**
     * 链接car成功
     */
    @Override
    public void connectSuccess(final WifiTransfer transfer, Socket socket) {

        if (transfer != null) {
            if (car != null) {
                ICommand cmd = CmdManage.getInstance().setVolume(voice + "", car);
                transfer.sendCmd(cmd);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d("TAAG", "send ....");

                voiceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (progress < 3) {
                            seekBar.setProgress(3);
                            ToastUtil.show(MainActivity.this, getString(R.string.voice_no_smaller));

                        }
                        voice = progress;
                        SpUtil.save("volume", String.valueOf(voice));
                        mPresenter.sendApply(car, 14);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        Log.d("TAG", "voice==" + voice);
                    }
                });

            }
        }

    }

    /**
     * 链接car失败
     */
    @Override
    public void connectFail() {

    }

    @Override
    public void initViewData(CommonParam commonParam) {

        tv_car_num.setText(commonParam.getCarNum());
        tv_id_card_num.setText(commonParam.getDrivingLicence());
        try {
            if (commonParam.getRegisterCarDate() != null) {
                if (!(TextUtils.isEmpty(commonParam.getRegisterCarDate()))) {
                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = sDateFormat.parse(commonParam.getRegisterCarDate());
                    // 车的年检日期
                    Date dd = TimeUtils.addYear(d, 1);
                    long ddd = dd.getTime() - new Date().getTime();
                    date.setText("" + roundCleanDay(TimeUtils.milliseconds2Unit(ddd, ConstUtils.DAY)) + "天");
                } else {
                    date.setText(getString(R.string.nodate));
                }
            } else {
                date.setText(getString(R.string.nodate));
            }


            if (commonParam.getRegisterDriverDate() != null) {
                if (!(TextUtils.isEmpty(commonParam.getRegisterDriverDate()))) {
                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = sDateFormat.parse(commonParam.getRegisterDriverDate());
                    // 车的年检日期
                    Date dd = TimeUtils.addYear(d, 1);
                    long ddd = dd.getTime() - new Date().getTime();
                    year_date.setText("" + roundCleanDay(TimeUtils.milliseconds2Unit(ddd, ConstUtils.DAY)) + "天");
                } else {
                    year_date.setText(getString(R.string.nodate));
                }
            } else {
                year_date.setText(getString(R.string.nodate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    /**
     * 退出驾驶员
     */

    private void exitConfirmDriver() {
        new AlertDialog.Builder(mContext).setMessage("确定退出驾驶员!")
                .setPositiveButton(getString(R.string.familynumbersure), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        mPresenter.sendDriverLeave();
                        App.getApp().setConfirmDriver(false);
                        driverCarnum.setText(getString(R.string.i_am_driver));

                        TicketUpLoad ticketUpLoad=new TicketUpLoad(mContext);
                        ticketUpLoad.uploadData();


                    }
                })
                .setNegativeButton(mContext.getString(R.string.back_cancel), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
}
