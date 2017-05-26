package com.soarsky.car.ui.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.CommonParam;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseFragment;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.CheckVersionBean;
import com.soarsky.car.bean.DrivingLicenseInfo;
import com.soarsky.car.bean.DrivingLicenseInformationDataBean;
import com.soarsky.car.bean.QueryFamilyBean;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.TerminalInfoParam;
import com.soarsky.car.bean.UploadFile;
import com.soarsky.car.bean.ViolationDealInfo;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.design.IAutoConfirmDriverListener;
import com.soarsky.car.server.wifi.WifiTransfer;
import com.soarsky.car.ui.carlocation.lovecar.CarLocationActivity;
import com.soarsky.car.ui.danger.start.DangerStartActivity;
import com.soarsky.car.ui.home.main.MainModel;
import com.soarsky.car.ui.home.main.MainPresent;
import com.soarsky.car.ui.home.main.MainView;
import com.soarsky.car.ui.home.map.MainMapActivity;
import com.soarsky.car.ui.illegal.IllegalManageActivity;
import com.soarsky.car.ui.movecar.MoveCarActivity;
import com.soarsky.car.ui.roadside.rescue.RoadSideRescueActivity;
import com.soarsky.car.ui.ticketed.TicketUpLoad;
import com.soarsky.car.ui.trivelrecord.riderecordstart.RideRecordStratActivity;
import com.soarsky.car.ui.usecarrecord.UseCarRecordActivity;
import com.soarsky.car.ui.validdriverlistener.ConfirmDirverActivity;
import com.soarsky.car.uitl.DeviceUtils;
import com.soarsky.car.uitl.LogUtil;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static com.soarsky.car.ConstantsUmeng.DANGER_START;
import static com.soarsky.car.ConstantsUmeng.ILLEGAL_MANAGE;
import static com.soarsky.car.ConstantsUmeng.I_AM_DRIVER;
import static com.soarsky.car.ConstantsUmeng.MAP_POSITION;
import static com.soarsky.car.ConstantsUmeng.MAP_SEARCH_ICON;
import static com.soarsky.car.ConstantsUmeng.MOVE_CAR;
import static com.soarsky.car.ConstantsUmeng.RIDE_RECORD;
import static com.soarsky.car.ConstantsUmeng.ROADSIDE_RESCUE;



/**
 * Andriod_Car_App
 * 作者： 魏凯
 * 时间： 2017/5/8
 * 公司：长沙硕铠电子科技有限公司
 * Email：weikai@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */
public class NewMainFragment extends BaseFragment<MainPresent, MainModel> implements MainView, View.OnClickListener {
    /**
     * 爱车位置
     */
    private RelativeLayout aicheweizhiRl;
    /**
     * 违章管理
     */
    private RelativeLayout weizhangguanliRl;

    /**
     * 我是驾驶员
     */
    private RelativeLayout woshijiaoshiyuanRl;

    /**
     * 常量--绑定驾驶证
     */
    private static final int CONFRIM_DRIVER_CARNUM = 3;

    /**
     * 确认驾驶员服务
     */
    Intent serviceIntent;

    /**
     * 实体类
     */
    private ResponseDataBean<CheckVersionBean> check;

    /**
     * 版本号路径路径
     */
    private String versionPath;

    /**
     * file
     */
    private File futureStudioIconFile;

    /**
     * 车牌号
     */
    private TextView tv_car_num;

    private App app;

    private TextView driverCarnum;
    private ConfirmDriverService confirmDriverService;
    private int[] imgs = { R.drawable.baojing, R.drawable.jiaotongweifa, R.drawable.che, R.drawable.guzhang, R.drawable.jiechehuanche, R.drawable.daohang};
    private String[] texts = { "出现报警", "乘车记录", "请人移车", "故障救援","用车记录", "地图导航" };
    private GridView gridView;
    private String carNum;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_new_main;
    }

    @Override
    public void initView(View view) {
        app = (App) getActivity().getApplication();
        aicheweizhiRl = (RelativeLayout) view.findViewById(R.id.aicheweizhiRl);
        aicheweizhiRl.setOnClickListener(this);

        weizhangguanliRl = (RelativeLayout) view.findViewById(R.id.weizhangguanliRl);
        weizhangguanliRl.setOnClickListener(this);

        woshijiaoshiyuanRl = (RelativeLayout) view.findViewById(R.id.woshijiaoshiyuanRl);
        woshijiaoshiyuanRl.setOnClickListener(this);

        driverCarnum = (TextView) view.findViewById(R.id.driverCarnum);

        tv_car_num = (TextView) view.findViewById(R.id.tv_car_num);

        gridView = (GridView) view.findViewById(R.id.gridView);
        List<Map<String, Object>> lisItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imgs.length; i++) {
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("img", imgs[i]);
            listem.put("text", texts[i]);
            lisItems.add(listem);
        }
        gridView.setAdapter(new SimpleAdapter(getActivity(),lisItems,R.layout.home_gridview_item,new String[] { "img", "text" },
                new int[] {R.id.img,R.id.text}));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (0 == position){
                    MobclickAgent.onEvent(getActivity(), DANGER_START);
                    startActivity(new Intent(getActivity(), DangerStartActivity.class));
                }else if (1 == position){
                    MobclickAgent.onEvent(getActivity(), RIDE_RECORD);
                    startActivity(new Intent(getActivity(), RideRecordStratActivity.class));
                } else if (2 == position){
                    MobclickAgent.onEvent(getActivity(), MOVE_CAR);
                    startActivity(new Intent(getActivity(), MoveCarActivity.class));
                } else if (3 == position){
                    MobclickAgent.onEvent(getActivity(), ROADSIDE_RESCUE);
                    Intent it = new Intent();
                    it.setClass(getActivity(), RoadSideRescueActivity.class);
                    startActivity(it);
                } else if (4 == position){
                    startActivity(new Intent(getActivity(), UseCarRecordActivity.class));
                    /*MobclickAgent.onEvent(getActivity(), BORROW_RETURN_CAR);
                    if (SpUtil.get(Constants.LICESE_NUM) != null && !SpUtil.get(Constants.LICESE_NUM).equals("")) {
                        Log.d("TAG", "num ==" + SpUtil.get(Constants.LICESE_NUM));
                        startActivity(new Intent(getActivity(), BorrowCarAndReturnActivity.class));
                    } else {
                        new AlertDialog.Builder(getActivity()).setTitle(R.string.borrow_car_title)
                                .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // 点击“返回”后的操作,这里不设置没有任何操作
                                    }
                                }).show();
                    }*/
                } else if (5 == position){
                    MobclickAgent.onEvent(getActivity(), MAP_SEARCH_ICON);
                    startActivity(new Intent(getActivity(), MainMapActivity.class));
                }
            }
        });
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.aicheweizhiRl:
                MobclickAgent.onEvent(getActivity(), MAP_POSITION);

                if (SpUtil.get(Constants.CAR_NUM) != null && !SpUtil.get(Constants.CAR_NUM).equals("")) {
                    Intent intent = new Intent();
                    intent.putExtra("carNum",carNum);
                    intent.setClass(getActivity(),CarLocationActivity.class);
                    startActivity(intent);
                } else {
                    new AlertDialog.Builder(getActivity()).setTitle(R.string.car_confirm_title)
                            .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“返回”后的操作,这里不设置没有任何操作
                                }
                            }).show();
                }

                break;
            case R.id.weizhangguanliRl:
                MobclickAgent.onEvent(getActivity(), ILLEGAL_MANAGE);
                //违章管理
                startActivity(new Intent(getActivity(), IllegalManageActivity.class));
                break;
            case R.id.woshijiaoshiyuanRl:
                MobclickAgent.onEvent(getActivity(), I_AM_DRIVER);
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
                        confirmDriverService.setAuto(true);
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
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    driverCarnum.setText(car.getCarNum());
                }
            });
        }

        @Override
        public void onDriverOffLine() {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    driverCarnum.setText(getString(R.string.i_am_driver));
                }
            });
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (serviceIntent != null) {
            LogUtil.i(getString(R.string.stop_service));
            getActivity().stopService(serviceIntent);
            serviceIntent=null;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        /**
         * 绑定服务
         */
        startService();
        tv_car_num.setText(app.getCommonParam().getCarNum());

    }

    private void startService() {
        serviceIntent = new Intent(getActivity(), ConfirmDriverService.class);
        mContext.startService(serviceIntent);
        mContext.bindService(serviceIntent,
                serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void showFamilySuccess(ResponseDataBean<QueryFamilyBean> param) {

    }

    @Override
    public void showFamilyFail() {

    }

    @Override
    public void deleteAllSuccess() {

    }

    @Override
    public void deleteAllFail() {

    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void showSuccess(ResponseDataBean<ViolationDealInfo> violationDealParam) {

    }

    @Override
    public void showLicenseSuccess(ResponseDataBean<DrivingLicenseInfo> param) {

    }

    @Override
    public void showLicenseError() {

    }

    @Override
    public void getTerminalInfoSuccess(int index, ResponseDataBean<TerminalInfoParam> taerminalInfoParm) {

    }

    @Override
    public void getTerminalInfoFail() {

    }

    @Override
    public void uploadImgSuccess(UploadFile uploadFile) {

    }

    @Override
    public void uploadImgFail() {

    }

    @Override
    public void modifyAppImageSuccess(ResponseDataBean<String> stringResponseDataBean) {

    }

    @Override
    public void modifyAppImageFail() {

    }

    @Override
    public void connectSuccess(WifiTransfer transfer, Socket socket) {

    }

    @Override
    public void connectFail() {

    }

    @Override
    public void checkSuccess(ResponseDataBean<CheckVersionBean> checkVersion) {
        check = checkVersion;
        versionPath = checkVersion.getData().getFileurl();
        String versionName = DeviceUtils.getVersionName(getActivity());
        if (DeviceUtils.compareVersion(versionName, checkVersion.getData().getVersion())) {
            showUpdateDialog(getString(R.string.update_version));
        }
    }

    @Override
    public void checkFail(ResponseDataBean<CheckVersionBean> checkVersion) {

    }

    @Override
    public void loadSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {
        if (response.isSuccessful()) {
            writeResponseBodyToDisk(response.body());
        }
    }

    @Override
    public void loadFail(Call<ResponseBody> call, Throwable t) {
        ToastUtil.show(getActivity(), R.string.load_fail);
    }

    @Override
    public void initViewData(CommonParam commonParam) {

    }

    @Override
    public void getDriverListSuccess(ResponseDataBean<List<DrivingLicenseInformationDataBean>> carNumParam) {
        if (carNumParam != null) {
            if (Constants.REQUEST_SUCCESS.equals(carNumParam.getStatus())) {
                try {
                    carNum = carNumParam.getData().get(0).getPlateno();
                    tv_car_num.setText(carNumParam.getData().get(0).getPlateno());
                    if (carNumParam.getData().get(0).getRegisterdate() != null) {
                        if (!(TextUtils.isEmpty(carNumParam.getData().get(0).getRegisterdate()))) {
                            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            Date d = sDateFormat.parse(carNumParam.getData().get(0).getRegisterdate());
                            // 车的年检日期
                            Date dd = TimeUtils.addYear(d, 1);
                            long ddd = dd.getTime() - new Date().getTime();
                            //date.setText("" + roundCleanDay(TimeUtils.milliseconds2Unit(ddd, ConstUtils.DAY)) + "天");
                        } else {
                            //date.setText(getString(R.string.nodate));
                        }
                    } else {
                        //date.setText(getString(R.string.nodate));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * 显示自动更新的对话框
     *
     * @param desc 描述
     */
    protected void showUpdateDialog(String desc) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity());
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

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.show(getActivity(), R.string.load_success);
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


}
