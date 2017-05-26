package com.soarsky.car.ui.illegal.advise;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/2/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：违章劝离<br>
 * 该类为 IllegalAdviseActivity<br>
 */


public class IllegalAdviseActivity extends BaseActivity<IllegalAdvisePresent,IllegalAdviseModel> implements IllegalAdviseView,View.OnClickListener,RemovePictureListener{

    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 上传
     */
    private Button adviseUploadBtn;
    /**
     * 照片展示列表
     */
    private GridView gridView;
    /**
     * 适配器
     */
    private IllegalAdviseImageAdapter adapter;
    /**
     * PopupWindow
     */
    private PopupWindow mPopWindow;
    /**
     * 图片地址集合
     */
    private List<String> images = new ArrayList<String>();
    /**
     * 时间
     */
    private String violationTime ="";
    /**
     * 类型
     */
    private String ptype ="";
    /**
     * 车牌
     */
    private String carnum ="";
    /**
     * application
     */
    private App app;
    /**
     * 该类的key
     */
    private static final String TAG = "IllegalAdviseActivity";
    /**
     * URL
     */
    private static String imageUrl = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_advise;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.illegal_advise));



        adviseUploadBtn = (Button) findViewById(R.id.adviseUploadBtn);
        adviseUploadBtn.setOnClickListener(this);

        gridView = (GridView) findViewById(R.id.gridView);

    }




    @Override
    protected String getHeaderTitle() {
        return null;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        violationTime = getIntent().getStringExtra("time");
        ptype = getIntent().getStringExtra("ptype");
        carnum = getIntent().getStringExtra("carnum");

        images.clear();
        File root = new File("/sdcard/uploadImage/");
        AdviseUilt.deleteAllFiles(root);
        adapter = new IllegalAdviseImageAdapter(this, images);
        adapter.setRemovePictureListener(this);
        gridView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    /**
     * 保存路径
     * @param remoteUrl 路径
     */
    @Override
    public void saveNetUri(String remoteUrl) {

        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String rtime =sDateFormat.format(new Date());
        Log.d("TAG","url"+remoteUrl);
        mPresenter.uploadViolationAdvice(carnum,ptype,rtime,violationTime,remoteUrl);

    }

    /**
     * 上传成功回调
     */
    @Override
    public void uploadImgSucess() {

        File root = new File("/sdcard/uploadImage/");
        AdviseUilt.deleteAllFiles(root);
        ToastUtil.show(this,getString(R.string.upload_success));

        new AlertDialog.Builder(mContext).setTitle(R.string.upload_success_tip)
                .setPositiveButton(R.string.dialog_text, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作

                        finish();
                    }
                })
               .show();

    }

    /**
     * 上传图片失败
     * @param message 信息
     */
    @Override
    public void uploadImgf(String message) {
        LogUtil.e("违章撤销图片上传失败"+message);
        ToastUtil.show(this,getText(R.string.upload_img_fail));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLay:
                finish();
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
            case R.id.adviseUploadBtn:

//                List<String> paths = AdviseUilt.getImagePathFromSD("/sdcard/uploadImage/");

//                Log.d("TAG","size==="+paths.size());
                if(images.size()>0) {
                    if (images.size() == 2){
                        mPresenter.uploadImg(images);
                    }else {
                        ToastUtil.show(this,R.string.take_two_photos);
                    }

                }else {
                    ToastUtil.show(this,R.string.upload_no_pic);
                }

                break;

            }

    }


    /**
     * 选择照片弹框
     */
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(IllegalAdviseActivity.this).inflate(R.layout.layout_popupwindow, null);
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
        View rootview = LayoutInflater.from(IllegalAdviseActivity.this).inflate(R.layout.activity_advise, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPopWindow.dismiss();
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1:
                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                        Log.i("TestFile",
                                "SD card is not avaiable/writeable right now.");
                        return;
                    }
                    String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
//            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式

                    FileOutputStream b = null;
                    //
                    File file = new File("/sdcard/uploadImage/");
                    file.mkdirs();// 创建文件夹
                    String fileName = "/sdcard/uploadImage/" + name;

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
                        Log.i("TestFile",
                                "SD card is not avaiable/writeable right now.");
                        return;
                    }
                    String name1 = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
//            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
                    Uri uri = data.getData();

                    FileOutputStream b1 = null;
                    //
                    File file1 = new File("/sdcard/uploadImage/");
                    file1.mkdirs();// 创建文件夹
                    String fileName1 = "/sdcard/uploadImage/" + name1;

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

    /**
     * 加载图片
     */
    public void showImages() {

        adapter = new IllegalAdviseImageAdapter(this, images);
        adapter.setRemovePictureListener(this);
        gridView.setAdapter(adapter);

    }

    /**
     * 删除图片
     * @param position 第几个
     */
    @Override
    public void removePicture(final int position) {
        new AlertDialog.Builder(mContext).setTitle(R.string.delete_confirm)
                .setPositiveButton(R.string.dialog_text, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击“确认”后的操作

                        File file = new File(images.get(position).toString());//文件路径
                        file.delete();
                        images.remove(position);
                        adapter = new IllegalAdviseImageAdapter(IllegalAdviseActivity.this, images);
                        adapter.setRemovePictureListener(IllegalAdviseActivity.this);
                        gridView.setAdapter(adapter);


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
     * 添加图片
     */
    @Override
    public void add() {
        showPopupWindow();
    }

}
