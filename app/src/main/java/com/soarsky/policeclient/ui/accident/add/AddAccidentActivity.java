package com.soarsky.policeclient.ui.accident.add;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.IBinder;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.soarsky.policeclient.App;
import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.data.local.db.bean.accident.Accident;
import com.soarsky.policeclient.bean.AccidentDataBean;
import com.soarsky.policeclient.data.local.db.bean.accident.AccidentReason;
import com.soarsky.policeclient.ui.accident.AccidentDialogUtil;
import com.soarsky.policeclient.ui.accident.DataNoSerializable;
import com.soarsky.policeclient.ui.accident.SelectCarListAdapter;
import com.soarsky.policeclient.uitl.LogUtil;
import com.soarsky.policeclient.uitl.TimeUtils;
import com.soarsky.policeclient.uitl.ToastUtil;
import com.soarsky.policeclient.server.CheckService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为新增事故Activity界面<br>
 */
public class AddAccidentActivity extends BaseActivity<AddAccidentPresent,AddAccidentModel> implements  AddAccidentView{
    /**
     * 新增事故时间TextView
     */
    private TextView time;
    /**
     * 新增事故位置EditText
     */
    private EditText position;
    /**
     * 新增事故原因TextView
     */
    private TextView reson;
    /**
     * 新增事故备注EditText
     */
    private EditText beizhu;
    /**
     * 添加事故分析车辆图标
     */
    private ImageView addCar;
    /**
     * 添加的事故分析车辆列表ListView
     */
    private ListView carListView;
    /**
     * 确定提交按钮
     */
    private Button ok;
    /**
     * 添加的图片GridView
     */
    private GridView images;
    /**
     * 返回键
     */
    private RelativeLayout back;
    /**
     * 界面标题
     */
    private TextView headerTv;
    /**
     * 选择的车辆列表
     */
    private ArrayList<Car> selectCar;
    /**
     * 事故分析接口实例
     */
    private IAccident iAccident;
    /**
     * 选择车辆列表Adapter
     */
    private SelectCarListAdapter selectCarListAdapter;
    /**
     * 是否完成与终端的通信
     */
    private boolean finished = true;
    /**
     * 从终端读取的车辆事故分析数据HashMap
     */
    private HashMap<Car,AccidentDataBean.AccidentItemBean.AccidentCarBean> carAccidentDataBeanHashMap = new HashMap<>();
    /**
     * 新增的事故数据
     */
    private AccidentDataBean.AccidentItemBean accidentItemBean = new AccidentDataBean.AccidentItemBean();
    /**
     * 是否点击了确认按钮
     */
    private boolean okClicked;
    /**
     * 图片的List
     */
    private List<AccidentDataBean.AccidentItemBean.Image> imagesList = new ArrayList<>();
    /**
     * 图片的Adapter
     */
    private ImageAdapter imageAdapter;
    /**
     * 选择事故原因对话框
     */
    private AccidentBottomDialog dialog;
    /**
     * 是否显示了对话框
     */
    private boolean showDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, CheckService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 获取到service的接口实例
     */
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iAccident = (IAccident) ((CheckService.LocalBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     * 从终端读取数据的回调
     */
    private OnAccidentMessageResponseListener onAccidentMessageResponseListener = new OnAccidentMessageResponseListener() {
        @Override
        public void onResponse(final AccidentDataBean.AccidentItemBean.AccidentCarBean accidentDataBean) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    selectCarListAdapter.notifyDataSetChanged();
                    carAccidentDataBeanHashMap.put(accidentDataBean.getCar(),accidentDataBean);
                }
            });

        }

        @Override
        public void onFinished() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    selectCarListAdapter.notifyDataSetChanged();
                    finished = true;
                    if(okClicked){
                        saveData();
                    }
                }
            });

        }

        @Override
        public void onUnFinished() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    finished = false;
                    selectCarListAdapter.notifyDataSetChanged();
                }
            });

        }
    };


    @Override
    public int getLayoutId() {
        return R.layout.activity_add_accident;
    }

    @Override
    public void initView() {
        time = (TextView) findViewById(R.id.time);
        position = (EditText) findViewById(R.id.position);
        reson = (TextView) findViewById(R.id.reson);
        beizhu = (EditText) findViewById(R.id.beizhu);
        addCar = (ImageView) findViewById(R.id.addCar);
        carListView = (ListView) findViewById(R.id.carListView);
        selectCarListAdapter = new SelectCarListAdapter(this);
        carListView.setAdapter(selectCarListAdapter);
        ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reson.getText()==null || reson.getText().equals("") || position.getText()==null || position.getText().equals("")){
                    ToastUtil.show(AddAccidentActivity.this, R.string.violationerrorimfo);
                    return;
                }
                okClicked = true;
                showProgress();
                if(finished){
                    saveData();
                }
            }
        });
        reson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog();
            }
        });
        images = (GridView) findViewById(R.id.images);
        imageAdapter = new ImageAdapter(this,imagesList,images);
        images.setAdapter(imageAdapter);
        back = (RelativeLayout) findViewById(R.id.listIconLay);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
        headerTv = (TextView) findViewById(R.id.headerTv);
        headerTv.setText("新增事故");
        time.setText(TimeUtils.getCurTimeString());
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccidentDialogUtil.showSelectCarTypeDialog(AddAccidentActivity.this,selectCar);
            }
        });
    }

    /**
     * 显示选择事故原因对话框
     */
    public void showBottomDialog() {
        List<AccidentReason> accidentReasons = App.getApp().getDaoSession().getAccidentReasonDao().loadAll();
        List<String> resons = new ArrayList<>();
        for (AccidentReason accidentReason: accidentReasons) {
            resons.add(accidentReason.getReason());
        }
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();
        AccidentBottomDialog.Builder builder = new AccidentBottomDialog.Builder(this, width, height / 3, new AccidentBottomDialogListener(){
            @Override
            public void confirmClick(String o) {
                reson.setText(o);
                dialog.dismiss();
            }
        }, resons,"事故原因");

        dialog = builder.create();
        dialog.show();

    }

    /**
     * 保存新增事故到数据库
     */
    private void saveData(){
        accidentItemBean.setTime(time.getText().toString());
        accidentItemBean.setBeizhu(beizhu.getText().toString());
        accidentItemBean.setWeizhi(position.getText().toString());
        accidentItemBean.setYuanyin(reson.getText().toString());
        if(selectCar!=null && selectCar.size()!=0){
            ArrayList<AccidentDataBean.AccidentItemBean.AccidentCarBean> accidentItemBeenList = new ArrayList<>();
            for (Car car:selectCar) {
                if(carAccidentDataBeanHashMap.get(car)==null){
                    AccidentDataBean.AccidentItemBean.AccidentCarBean accidentCarBean = new AccidentDataBean.AccidentItemBean.AccidentCarBean();
                    accidentCarBean.setCar(car);
                    accidentItemBeenList.add(accidentCarBean);
                }else {
                    accidentItemBeenList.add(carAccidentDataBeanHashMap.get(car));
                }
            }
            accidentItemBean.setAccidentCarBeanList(accidentItemBeenList);
        }
        accidentItemBean.setImages(imagesList);
        Accident accident = new Accident();
        Gson gson = new Gson();
        accident.setData(gson.toJson(accidentItemBean));
        App.getApp().getDaoSession().getAccidentDao().insert(accident);
        dismissProgress();
        showSuccessDialog();
    }

    /**
     * 显示事故保存成功的对话框
     */
    public void showSuccessDialog(){
        showDialog = true;
        new AlertDialog.Builder(this).setTitle("消息已上传成功").setIcon(R.drawable.carnodataicon).setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBack();
                    }
                }).show();
    }

    @Override
    public void onBackPressed() {
        if(!showDialog){
            finish();
        }
    }

    /**
     * 用户按了返回键的处理方法
     */
    private void onBack(){
        DataNoSerializable.getDataNoSerializable().setCar(null);
        DataNoSerializable.getDataNoSerializable().setCars(null);
        iAccident.stopAccident();
        if(okClicked){
            Intent intent = new Intent();
            DataNoSerializable.getDataNoSerializable().setAccidentItemBean1(accidentItemBean);
            setResult(RESULT_OK,intent);
        }
        finish();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 0){
                selectCar = DataNoSerializable.getDataNoSerializable().getCars();
                selectCarListAdapter.setData(selectCar);
                iAccident.startAccident(selectCar,onAccidentMessageResponseListener);

            }else if(requestCode == 1){
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
                    AccidentDataBean.AccidentItemBean.Image image = new AccidentDataBean.AccidentItemBean.Image();
                    image.setMyPath(fileName);
                    imagesList.add(image);
                    imageAdapter =new ImageAdapter(this,imagesList,images);
                    images.setAdapter(imageAdapter);
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
            }else if(requestCode == 2){
                Car car = DataNoSerializable.getDataNoSerializable().getCar();
                boolean has = car.getBlueName()==null?false:true;
                if(!has){
                    car.setHasRead(true);
                }
                if(selectCar==null){
                    selectCar = new ArrayList<>();
                }
                selectCar.add(car);
                selectCarListAdapter.setData(selectCar);
                if(has){
                    iAccident.startAccident(selectCar,onAccidentMessageResponseListener);
                }

            }
        }

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void getedPosition(String s) {
        position.setText(s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }

    @Override
    public void showProgess() {

    }

    @Override
    public void stopProgess() {

    }
    
}
