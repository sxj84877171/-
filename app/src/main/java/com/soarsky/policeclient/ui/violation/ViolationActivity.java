package com.soarsky.policeclient.ui.violation;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.data.local.db.bean.Violation;
import com.soarsky.policeclient.data.local.db.dao.ViolationDao;
import com.soarsky.policeclient.server.design.OnMessageResponseListener;
import com.soarsky.policeclient.uitl.CarUtil;
import com.soarsky.policeclient.uitl.LogUtil;
import com.soarsky.policeclient.uitl.ToastUtil;
import com.soarsky.policeclient.server.CheckService;
import com.soarsky.policeclient.server.bluetooth.Blue;
import com.soarsky.policeclient.server.design.ICheck;
import com.soarsky.policeclient.server.design.IElTicket;
import com.soarsky.policeclient.server.design.IScan;
import com.soarsky.policeclient.server.design.OnConnectListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 开电子罚单界面<br>
 */

public class ViolationActivity extends BaseActivity<ViolationPresent, ViolationMode> implements ViolationView, View.OnClickListener, RemovePictureListener {

    /**
     * 退出
     */
    private RelativeLayout violationBackLay; //退出

    private ImageView violationBackView;
    /**
     * 违章类型
     */
    private LinearLayout violationTypeLay;  //违章类型
    /**
     * 违章类型
     */
    private TextView violationTypeTv;
    /**
     * 违章车牌
     */
    private LinearLayout violationlicenseLay;  //违章车牌
    /**
     * 违章车牌
     */
    private TextView violationlicenseTv;
    /**
     * 违章扣分
     */
    private LinearLayout violationpointLay;  //违章扣分
    /**
     * 违章扣分
     */
    private TextView violationpointEt;
    /**
     * 违章金额
     */
    private LinearLayout violationMoneyLay; //违章金额
    /**
     * 违章金额
     */
    private EditText violationMoneyEt;
    /**
     * 违章位置
     */
    private LinearLayout violationPositionLay; //违章位置
    /**
     * 违章位置
     */
    private TextView violationPositionTv;
    /**
     * 违章时间
     */
    private LinearLayout violationTimeLay; //违章时间
    /**
     * 违章时间
     */
    private TextView violationTimeTv;
    /**
     * 提交
     */
    private Button commitBtn;  //提交
    /**
     * 照相
     */
    private ImageView addPhotoView;  //照相
    /**
     * 违章代码，目前先写死 之后会从数据库中把对应关系初始化
     */
    public static final String IllegalCode = "00";  //违章代码，目前先写死 之后会从数据库中把对应关系初始化
    /**
     * 违章等级，目前先写死
     */
    public static final int LevelOfViolation = 1;             //违章等级，目前先写死
    /**
     * 违章信息
     */
    public static final String IllegalInformation = "违章停车";
    /**
     * 横向滚动布局
     */
    private ViolationHorizontalScrollView mHorizontalScrollView;
    /**
     * 适配器
     */
    private HorizontalScrollViewAdapter mAdapter;  //适配器
    /**
     * 图片地址数据
     */
    private List<String> images = new ArrayList<>();
    /**
     * 车牌号
     */
    private List<String> list = new ArrayList<>();  //车牌号
    /**
     * 日期
     */
    private Date date;
    /**
     * 显示车牌号对话框
     */
    private ViolationBottomDialog.Builder builder;   //显示车牌号对话框

    private ViolationBottomDialog.Builder carNumBuilder;
    /**
     * 显示地址的对话框
     */
    private ViolationAddressDialog.Builder addressbuilder;  //显示地址的对话框
    /**
     * 开电子罚单功能接口
     */
    private IElTicket iElTicket;

    private List<Car> cars = new ArrayList<>();
    /**
     * 稽查功能接口
     */
    private ICheck iCheck;

    /**
     * App
     */
    private App app;  //
    /**
     * PopupWindow
     */
    private PopupWindow mPopWindow;
    /**
     * 选择的车辆
     */
    private Car car = new Car();
    /**
     * Log tag
     */
    private static final String TAG = "ViolationActivity";
    public static ViolationActivity mactivity;



    @Override
    public int getLayoutId() {
        return R.layout.activity_violation;
    }
    /**
     * 对话框
     */
    private Dialog dialog;

    private String selectCarNum;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mactivity = this;
        Intent intent = new Intent(this, CheckService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        //showProgress();
        blueSaoMiao();
        //wifiSaoMiao();
    }

    private void blueSaoMiao(){
        Blue blue = Blue.getInstance(this);
        blue.setOnBlueScan(onBlueScan);
        blue.setOnConnectListener(onConnectListener);
        blue.startDiscovery();
    }


    private Blue.OnBlueScan onBlueScan = new Blue.OnBlueScan() {


        @Override
        public void onBlueScan(Car car) {
            if(!list.contains(car.getCarNum())){
                list.add(car.getCarNum());
                cars.add(car);
                if(carNumBuilder!=null){
                    carNumBuilder.setData(list);
                }
            }
        }
    };

    @Override
    protected void onDestroy() {

        unbindService(serviceConnection);
        super.onDestroy();
    }
    /**
     * 消息响应的监听
     */
    private OnMessageResponseListener onMessageResponseListener = new OnMessageResponseListener() {
        @Override
        public void onSuccess(final String s) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dismissProgress();
                    Toast.makeText(ViolationActivity.this, R.string.send_success, Toast.LENGTH_SHORT).show();
                    LogUtil.e("驾照号:"+s);
                    insertViolationData(s);
                }
            });
        }

        @Override
        public void onFail(final String s) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dismissProgress();
                    Toast.makeText(ViolationActivity.this, s, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
    /**
     * 绑定服务
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iCheck = (ICheck) ((CheckService.LocalBinder) service).getService();
            isChecking = iCheck.isChecking();
            iElTicket = (IElTicket) ((CheckService.LocalBinder) service).getService();
            IScan iScan = (IScan) ((CheckService.LocalBinder) service).getService();
            iCheck.stopCheck(false);
            mModel.setScan(iScan);
            //mPresenter.showListen();
            iElTicket.setOnConnectListener(onConnectListener);
            iElTicket.setOnResponseListener(onMessageResponseListener);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mModel.setScan(null);
        }
    };


    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG, this);
        mPresenter.initLocation();
        violationBackLay = (RelativeLayout) findViewById(R.id.violationBackLay);
        violationBackLay.setOnClickListener(this);

        violationTypeLay = (LinearLayout) findViewById(R.id.violationTypeLay);
        violationTypeLay.setOnClickListener(this);
        violationTypeTv = (TextView) findViewById(R.id.violationTypeTv);

        violationlicenseLay = (LinearLayout) findViewById(R.id.violationlicenseLay);
        violationlicenseLay.setOnClickListener(this);
        violationlicenseTv = (TextView) findViewById(R.id.violationlicenseTv);

        violationMoneyLay = (LinearLayout) findViewById(R.id.violationMoneyLay);
        violationMoneyLay.setOnClickListener(this);
        violationMoneyEt = (EditText) findViewById(R.id.violationMoneyEt);

        violationPositionLay = (LinearLayout) findViewById(R.id.violationPositionLay);
        violationPositionLay.setOnClickListener(this);
        violationPositionTv = (TextView) findViewById(R.id.violationPositionTv);

        violationTimeLay = (LinearLayout) findViewById(R.id.violationTimeLay);
        violationTimeLay.setOnClickListener(this);
        violationTimeTv = (TextView) findViewById(R.id.violationTimeTv);

        violationpointEt = (TextView) findViewById(R.id.violationpointEt);
        violationpointLay = (LinearLayout) findViewById(R.id.violationpointLay);


        commitBtn = (Button) findViewById(R.id.commitBtn);
        commitBtn.setOnClickListener(this);

        addPhotoView = (ImageView) findViewById(R.id.addPhotoView);
        addPhotoView.setOnClickListener(this);

        mHorizontalScrollView = (ViolationHorizontalScrollView) findViewById(R.id.id_horizontalScrollView);

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = new Date();
        String dateS = sDateFormat.format(date);
        violationTimeTv.setText(dateS);
        violationpointLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> _list = new ArrayList<String>();
                _list.add("0分");
                _list.add("1分");
                _list.add("2分");
                _list.add("3分");
                _list.add("4分");
                _list.add("5分");
                _list.add("6分");
                _list.add("7分");
                _list.add("8分");
                _list.add("9分");
                _list.add("10分");
                _list.add("11分");
                _list.add("12分");
                WindowManager windowManager = getWindowManager();
                Display display = windowManager.getDefaultDisplay();
                int width = display.getWidth();
                int height = display.getHeight();

                builder = new ViolationBottomDialog.Builder(ViolationActivity.this, width, height / 3, new ViolationBottomDialogListener() {
                    @Override
                    public void confirmClick(Object s) {
                        violationpointEt.setText((String) s);

                        dialog.dismiss();
                    }
                }, _list, 2);

                dialog = builder.create();
                dialog.show();
            }
        });

    }

    /**
     * 显示地址对话框
     */
    public void showAddressDialog() {
        addressbuilder = new ViolationAddressDialog.Builder(this, new ViolationAddressDialogListener() {

            @Override
            public void clickConfirm(String str) {
//                ToastUtil.show(ViolationActivity.this, "地址为" + str);
                violationPositionTv.setText(str);
                dialog.dismiss();
            }
        });
        if (violationPositionTv != null) {
            if (violationPositionTv.getText() != null) {
                addressbuilder.setAddress(violationPositionTv.getText().toString());
            }
        }

        dialog = addressbuilder.create();
        dialog.show();
    }
    /**
     * 选择违章类型对话框
     */
    public void showTypeBottomDialog() {

        List<String> _list = new ArrayList<String>();
        _list.add(IllegalCode + IllegalInformation);

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        builder = new ViolationBottomDialog.Builder(this, width, height / 3, new ViolationBottomDialogListener() {
            @Override
            public void confirmClick(Object s) {
                violationTypeTv.setText((String) s);

                dialog.dismiss();
            }
        }, _list, 2);

        dialog = builder.create();
        dialog.show();
    }
    /**
     * 选择违章车牌号对话框
     */
    public void showBottomDialog() {

        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        carNumBuilder = new ViolationBottomDialog.Builder(this, width, height / 3, violationBottomDialogListener
                , list, 1);
        carNumBuilder.setOnClickListener(onClickListener);
        dialog = carNumBuilder.create();
        dialog.show();

    }

    private View loadingImageView;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loadingImageView = v;
            loadingImageView.setEnabled(false);
            Animation loadingAnimation = AnimationUtils.loadAnimation(ViolationActivity.this, R.anim.loading);
            loadingImageView.startAnimation(loadingAnimation);
            //wifiSaoMiao();
            loadingImageView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    clearAnim();
                }
            },3000);
            Blue.getInstance(ViolationActivity.this).startDiscovery();
        }
    };



    /**
     * 车牌号对话框回调函数
     */
    public ViolationBottomDialogListener violationBottomDialogListener = new ViolationBottomDialogListener() {
        @Override
        public void confirmClick(Object o) {
            selectCarNum = (String) o;
            violationlicenseTv.setText(CarUtil.fromSsidToCarNum(selectCarNum));
            dialog.dismiss();
        }
    };

    /**
     * 删除图片时刷新页面
     *
     * @param position
     */
    @Override
    public void removePicture(int position) {
        File file = new File(images.get(position));//文件路径
        file.delete();
        images.remove(position);
        mHorizontalScrollView.initDatas(mAdapter);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.commitBtn:

                if (TextUtils.isEmpty(violationTypeTv.getText().toString()) || TextUtils.isEmpty(violationlicenseTv.getText().toString()) ||
                        TextUtils.isEmpty(violationMoneyEt.getText().toString()) || TextUtils.isEmpty(violationPositionTv.getText().toString()) || TextUtils.isEmpty(violationpointEt.getText().toString())) {

                    ToastUtil.show(this, R.string.violationerrorimfo);
                } else {

//                    insertViolationData("430681199106012619");
                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String dateS = sDateFormat.format(date);
                    List<Violation> violationList = App.getApp().getDaoSession().getViolationDao().queryBuilder().where(ViolationDao.Properties.Carnum.eq(violationlicenseTv.getText())).list();
                    for (Violation violation:violationList){
                        Date date = violation.getStime();
                        String dateS1 = sDateFormat.format(date);
                        if(dateS.equals(dateS1)){
                            ToastUtil.show(this, "罚单已经存在");
                            return;
                        }
                    }
                    showProgress();
                    connectCar();

                }

                break;
            case R.id.violationBackLay:

                new AlertDialog.Builder(mContext).setTitle(R.string.exit_edit)
                        .setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“确认”后的操作
                                back();
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“返回”后的操作,这里不设置没有任何操作
                            }
                        }).show();

                break;
            case R.id.addPhotoView:

                showPopupWindow();
                break;
            case R.id.violationlicenseLay:

                showBottomDialog();
                break;
            case R.id.violationPositionLay:
                showAddressDialog();
                break;
            case R.id.violationTypeLay:
                showTypeBottomDialog();
                break;
            case R.id.pop_camera:
                // 利用系统自带的相机应用:拍照
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
                break;
            case R.id.pop_chose:
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, 2);

                break;
            case R.id.pop_cancel:
                mPopWindow.dismiss();
                break;
        }

    }

    private void connectCar(){
        for (Car car: cars) {
            if(selectCarNum.equals(car.getCarNum())){
                this.car = car;
                iElTicket.connect(car);
            }
        }
    }

    /**
     * 选择照片弹框
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(ViolationActivity.this).inflate(R.layout.layout_popupwindow, null);
        mPopWindow = new PopupWindow(contentView,
                WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setTouchable(true);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        mPopWindow.getContentView().setFocusableInTouchMode(true);
        mPopWindow.getContentView().setFocusable(true);
        mPopWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (mPopWindow != null && mPopWindow.isShowing()) {
                        mPopWindow.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        //设置各个控件的点击响应
        TextView tv1 = (TextView) contentView.findViewById(R.id.pop_camera);
        TextView tv2 = (TextView) contentView.findViewById(R.id.pop_chose);
        TextView tv3 = (TextView) contentView.findViewById(R.id.pop_cancel);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(ViolationActivity.this).inflate(R.layout.activity_violation, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }

    /**
     * 开电子罚单
     */
    private void openElTicket() {
        ViolationLicenseParam violationLicenseParam = new ViolationLicenseParam();
        violationLicenseParam.setSsid(car.getBlueName());
        violationLicenseParam.setMoney(violationMoneyEt.getText().toString());
        violationLicenseParam.setPoint(violationpointEt.getText().toString().replace("分",""));
        violationLicenseParam.setType(violationTypeTv.getText().toString().substring(0,2));
        violationLicenseParam.setPosition(violationPositionTv.getText().toString());
        violationLicenseParam.setTime(date);
        iElTicket.openElTicket(violationLicenseParam);
    }
    /**
     * 连接监听
     */
    private OnConnectListener onConnectListener = new OnConnectListener() {
        @Override
        public void onSuccess() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //Toast.makeText(ViolationActivity.this, R.string.connet_success, Toast.LENGTH_SHORT).show();
                    openElTicket();
                }
            });
        }

        @Override
        public void onFailed() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    dismissProgress();
                    Toast.makeText(ViolationActivity.this, R.string.connet_fail, Toast.LENGTH_SHORT).show();
                }
            });
        }
    };
    /**
     * 插入电子罚单的数据
     */
    public void insertViolationData(String s) {
        try {
            Violation violation = new Violation();
            int money = Integer.parseInt(TextUtils.isEmpty(violationMoneyEt.getText().toString()) ? "0" : violationMoneyEt.getText().toString());
            violation.setMoney(money);
            violation.setIno(IllegalCode);
            violation.setDrivers(s);
            String fen = violationpointEt.getText().toString().replace("分","");
            int cent = TextUtils.isEmpty(fen) ? 0 : Integer.parseInt(fen);
            violation.setCent(cent);
            violation.setAddress(violationPositionTv.getText().toString());
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sDateFormat.parse(violationTimeTv.getText().toString());
            violation.setStime(date);
            violation.setCarnum(TextUtils.isEmpty(violationlicenseTv.getText().toString()) ? "000000" : violationlicenseTv.getText().toString());
//            violation.setCarnum("888888");
            violation.setInf(IllegalInformation);
            violation.setFlagId(date.toString() + violationlicenseTv.getText().toString());
            violation.setOpuser(app.getUserName());
            violation.setGrade(String.valueOf(LevelOfViolation));
            StringBuilder localUrl = new StringBuilder("");
            for (String url : images) {
                //localUrl = url.toString() + ",";
                localUrl.append(url.toString());
                localUrl.append(",");
            }
            if (localUrl.length() > 0) {
                if (localUrl.substring(localUrl.length() - 1).equals(",")) {
                    localUrl.deleteCharAt(localUrl.length() - 1);
                }
            }
            violation.setLocalimgurlurl(localUrl.toString());
            mPresenter.insertViolation(violation);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void showProgess() {

    }

    @Override
    public void stopProgess() {

    }
    /**
     * 回调照相后返回的相片
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        mPopWindow.dismiss();
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1:
                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                        LogUtil.i("TestFile",
                                "SD card is not avaiable/writeable right now.");
                        return;
                    }
                    String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
//            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式

                    FileOutputStream b = null;
                    //
                    File file = new File("/sdcard/myImage/");
                    file.mkdirs();// 创建文件夹
                    String fileName = "/sdcard/myImage/" + name;

                    try {
                        b = new FileOutputStream(fileName);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
                        images.add(fileName);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            b.flush();
                            b.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    showImages();
                    break;
                case 2:

                    String sdStatus1 = Environment.getExternalStorageState();
                    if (!sdStatus1.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                        LogUtil.i("TestFile",
                                "SD card is not avaiable/writeable right now.");
                        return;
                    }
                    String name1 = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
//            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
                    Uri uri = data.getData();

                    FileOutputStream b1 = null;
                    //
                    File file1 = new File("/sdcard/myImage/");
                    file1.mkdirs();// 创建文件夹
                    String fileName1 = "/sdcard/myImage/" + name1;

                    try {
                        b1 = new FileOutputStream(fileName1);
                        Bitmap bit = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                        bit.compress(Bitmap.CompressFormat.JPEG, 100, b1);// 把数据写入文件
                        images.add(fileName1);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            b1.flush();
                            b1.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    showImages();

                    break;
            }

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showImages();
    }

    /**
     * 在横向布局中显示出图片
     */
    public void showImages() {

        //images = ViolationUtil.getImagePathFromSD("/sdcard/myImage/");
        mAdapter = new HorizontalScrollViewAdapter(this, images, this);
        //添加滚动回调
        mHorizontalScrollView
                .setCurrentImageChangeListener(new ViolationHorizontalScrollView.CurrentImageChangeListener() {
                    @Override
                    public void onCurrentImgChanged(int position,
                                                    View viewIndicator) {
//                        mImg.setImageResource(mDatas.get(position));

//                        viewIndicator.setBackgroundColor(Color
//                                .parseColor("#AA024DA4"));

                    }
                });
        //添加点击回调
        mHorizontalScrollView.setOnItemClickListener(new ViolationHorizontalScrollView.OnItemClickListener() {

            @Override
            public void onClick(View view, int position) {
//                mImg.setImageResource(mDatas.get(position));
//                Log.d("TAG", "TAG=" + images.get(position).toString());
//                view.setBackgroundColor(Color.parseColor("#AA024DA4"));
            }
        });
        //设置适配器
        mHorizontalScrollView.initDatas(mAdapter);


    }
    /**
     * 是否在稽查
     */
    private boolean isChecking;

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(mContext).setTitle(R.string.exit_edit)
                .setPositiveButton(R.string.exit, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        back();
                        finish();
                    }
                })
                .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                    }
                }).show();

    }

    /**
     * 返回处理
     */
    private void back() {
        Blue.getInstance(this).stopDiscovery();
        if (iCheck != null && isChecking) {
            iCheck.startCheck(false);
        }
    }

    @Override
    public void initCarList(List<Car> _list) {
        list = new ArrayList<>();
        for (Car car : _list) {

            list.add(car.getCarNum());
        }
    }


    @Override
    public void showSuccess() {
        new ViolationUpload(this);
        /*ToastUtil.show(this, R.string.commitvictory);*/
        new AlertDialog.Builder(this).setTitle(R.string.dzi)
                .setPositiveButton(R.string.dialog_text, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作
                        back();
                        ViolationActivity.this.finish();

                    }
                })
                .setNegativeButton(R.string.contunue, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“返回”后的操作,这里不设置没有任何操作
                        //WifiUtil.getInstance().disconnectWifi();
                    }
                }).show();
    }

    @Override
    public void showFail() {

        ToastUtil.show(this, R.string.commitfail);
    }

    @Override
    public void getedPosition(String s) {
        violationPositionTv.setText(s);
    }

    private void clearAnim(){
        if(loadingImageView!=null){
            loadingImageView.setEnabled(true);
            loadingImageView.clearAnimation();
        }
        if(carNumBuilder!=null){
            carNumBuilder.setData(list);
        }
    }

}




