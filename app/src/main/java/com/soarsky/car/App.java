package com.soarsky.car;

import android.app.Activity;
import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;


import com.baidu.mapapi.SDKInitializer;
import com.google.gson.Gson;
import com.ivt.bluetooth.ibridge.BluetoothIBridgeDevice;
import com.soarsky.car.base.CrashHandler;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.TermDataBean;
import com.soarsky.car.data.local.db.dao.DaoMaster;
import com.soarsky.car.data.local.db.dao.DaoSession;
import com.soarsky.car.ui.home.map.route.set.MapRouteParam;
import com.soarsky.car.uitl.FileTermUtil;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.SpUtil;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;

import org.greenrobot.greendao.database.Database;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * 车主APP<br>
 * 作者： 孙向锦<br>
 * 时间： 2016/12/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * App 全局控制类，完成对应用程序的监控，数据库初始化操作等逻辑<br>
 */
public class App extends Application {

    private static App app;
    public static final boolean ENCRYPTED = false;

    private DaoSession daoSession;

    private CommonParam commonParam;

    private Context mcontext;
    /**
     * 当前定位的城市
     */
    private String city;


    /**
     * 车主当前连接的netId
     */
    private int netId;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    private Activity activity;

    /**
     * 确认驾驶员车辆
     */
    private String carNum="";


    /**
     * 确认驾驶员车辆蓝牙设备
     */
    private BluetoothIBridgeDevice iBridgeDevice;


    /**
     * 确认驾驶员车辆热点
     */
    private String ssID;
    /**
     * 忘记用户
     */
    private String forgetName;
    /**
     * 忘记用户密码返回的
     */
    private String forgetPassword;

    /**
     * 是否在线
     */
    private boolean isOnline = false;
    /**
     * 起始位置实体类
     */
    private MapRouteParam routeParam;

    /**
     * 是否确认驾驶员
     */
    private  boolean isConfirmDriver=false;

    /**
     * 图像的路径
     */
    private String imageUrl = "";
    /**
     * 所有的车辆 其中包含自己、亲情、借车
     */
    private List<LicenseInfo> car_list;

    /**
     *     所有activity集合
     */
    private static HashMap<String, Activity> activityList	= new HashMap<String, Activity>();

    private double latitude;
    private double longitude;
    private float speed;


    /**
     * 终端版本
     */

    private  String terminalVer;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 当前乘坐的车辆
     */
    private String  trivelCarNum;

    private static final String UMENG_APP_KEY = "" ;
//    private static final String UMENT_APP_
    /**
     * 驾驶证信息
     */
    private DriviLicenseParam param;
    /**
     * 行驶证信息
     */
    private CarInfoParam carInfoParam;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SpUtil.init(this);
        SDKInitializer.initialize(getApplicationContext());

        this.userName = SpUtil.get(Constants.CONS_USERNAME);

        String appName = getResources().getString(R.string.app_name);
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? appName + "-db-encrypted" : appName + "-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();

        CrashHandler.getInstance().setCustomCrashHanler(getApplicationContext());


        /*
         * 启动友盟推送及统计初始化功能，注册APPkey，渠道名称，普通统计模式，手机crash报告
         */
        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this,UMENG_APP_KEY,"soarsky", MobclickAgent.EScenarioType.E_UM_ANALYTICS_OEM,true));

       //MobclickAgent.setSecret(this，);

        PushAgent mPushAgent = PushAgent.getInstance(this);
        mPushAgent.setDebugMode(false);
//注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {

            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回device token
                LogUtil.i("my_token",deviceToken);
                Log.i("TAG",deviceToken);
                SpUtil.savePublicKey(getApp(),Constants.UMENG_DEVICE_TOKEN,deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                LogUtil.i("my_tokenFailure",s1);
            }
        });

        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public Notification getNotification(Context context, UMessage msg) {

                switch (msg.builder_id) {


                    case 1:
                        Notification.Builder builder = new Notification.Builder(context);
//                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
//                                R.layout.notification_view);
//                        myNotificationView.setTextViewText(R.id.notification_title, msg.title);
//                        myNotificationView.setTextViewText(R.id.notification_text, msg.text);
//                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon,
//                                getLargeIcon(context, msg));
//                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
//                                getSmallIconId(context, msg));
//                        builder.setContent(myNotificationView)
//                                .setSmallIcon(getSmallIconId(context, msg))
//                                .setTicker(msg.ticker)
//                                .setAutoCancel(true);

                        Log.d("TAG","msg=="+msg.extra);

                        return builder.getNotification();
                    case 0:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        LogUtil.i("TAG","msg=="+msg.text);
                        SpUtil.save(Constants.TermData,msg.text);
                        Gson gson = new Gson();
                        TermDataBean bean = gson.fromJson(msg.text,TermDataBean.class);



                        if(bean != null) FileTermUtil.saveFile(bean);

//                        if(bean.getParams().getPath()!=null){
//                            ApiF.getInstance().getService().loadFile(bean.getParams().getPath()).enqueue(new Callback<ResponseBody>() {
//                                @Override
//                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                    if (response.isSuccessful()) {
//                                        AppUtils.writeResponseBodyToDisk(response.body());
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                                }
//                            });
//                        }

                        Notification.Builder builder1 = new Notification.Builder(context);
                        return builder1.getNotification();
                }
                return null;
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

    }



    /**
     *  增加Activity
     */
    public void addActivity(String key,Activity activity)
    {
        Activity tactivity = activityList.get(key);
        if(tactivity == null)
        {
            activityList.put(key, activity);
        }else
        {
            tactivity.finish();
            activityList.put(key, activity);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 退出系统时，结束掉所有Activity
     */
    public void exit()
    {
        Iterator<String> keys = activityList.keySet().iterator();
        while(keys.hasNext())
        {
            Activity activity = activityList.get(keys.next());
            activity.finish();
        }
        MobclickAgent.onKillProcess(this);
        System.exit(0);
    }

    public static App getApp() {
        return app;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public CommonParam getCommonParam() {
        return commonParam;
    }

    public void setCommonParam(CommonParam commonParam) {
        this.commonParam = commonParam;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public String getForgetName() {
        return forgetName;
    }

    public void setForgetName(String forgetName) {
        this.forgetName = forgetName;
    }

    public String getForgetPassword() {
        return forgetPassword;
    }


    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public void setForgetPassword(String forgetPassword) {
        this.forgetPassword = forgetPassword;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public MapRouteParam getRouteParam() {
        return routeParam;
    }

    public void setRouteParam(MapRouteParam routeParam) {
        this.routeParam = routeParam;
    }
    public int getNetId() {
        return netId;
    }

    public void setNetId(int netId) {
        this.netId = netId;
    }

    public boolean isConfirmDriver() {
        return isConfirmDriver;
    }

    public void setConfirmDriver(boolean confirmDriver) {
        isConfirmDriver = confirmDriver;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<LicenseInfo> getCar_list() {
        return car_list;
    }

    public void setCar_list(List<LicenseInfo> car_list) {
        this.car_list = car_list;
    }


    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


    public String getTrivelCarNum() {
        return trivelCarNum;
    }

    public void setTrivelCarNum(String trivelCarNum) {
        this.trivelCarNum = trivelCarNum;
    }

    public String getSsID() {
        return ssID;
    }

    public void setSsID(String ssID) {
        this.ssID = ssID;
    }

    /**
     * 获取用户名
     * @return 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     * @param userName  用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }


    public BluetoothIBridgeDevice getiBridgeDevice() {
        return iBridgeDevice;
    }

    public void setiBridgeDevice(BluetoothIBridgeDevice iBridgeDevice) {
        this.iBridgeDevice = iBridgeDevice;
    }

    public DriviLicenseParam getParam() {
        return param;
    }

    public void setParam(DriviLicenseParam param) {
        this.param = param;
    }

    public CarInfoParam getCarInfoParam() {
        return carInfoParam;
    }

    public void setCarInfoParam(CarInfoParam carInfoParam) {
        this.carInfoParam = carInfoParam;
    }

    public String getTerminalVer() {
        return terminalVer;
    }

    public void setTerminalVer(String terminalVer) {
        this.terminalVer = terminalVer;
    }
}
