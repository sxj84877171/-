package com.soarsky.installationmanual.ui.main.fragment.task.nofinish.imageupload;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.ui.main.MainActivity;
import com.soarsky.installationmanual.ui.main.fragment.task.nofinish.autocheck.AutoCheckActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 图片上传界面<br>
 */

public class ImageUploadActivity extends BaseActivity<ImageUploadPresent, ImageUploadModel> implements ImageUploadView {

    private ImageView back;
    private ImageView uploadBtn;

    private List<String> imageList1 = new ArrayList<>();
    private List<String> imageList2 = new ArrayList<>();
    private List<String> imageList3 = new ArrayList<>();
    private List<String> imageList4 = new ArrayList<>();
    private List<String> imageList5 = new ArrayList<>();
    private List<String> imageList6 = new ArrayList<>();
    private ImageAdapter imageAdapter1;
    private ImageAdapter imageAdapter2;
    private ImageAdapter imageAdapter3;
    private ImageAdapter imageAdapter4;
    private ImageAdapter imageAdapter5;
    private ImageAdapter imageAdapter6;
    private GridView gridView1;
    private GridView gridView2;
    private GridView gridView3;
    private GridView gridView4;
    private GridView gridView5;
    private GridView gridView6;


    @Override
    public int getLayoutId() {
        return R.layout.activity_image_upload;
    }

    @Override
    public void initView() {
        addView(R.layout.back_left_toolbar);
    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        back = (ImageView) findViewById(R.id.backBtn);
        uploadBtn = (ImageView) findViewById(R.id.uploadBtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ImageUploadActivity.this, MainActivity.class));
            }
        });
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ImageUploadActivity.this).setTitle("任务已完成请您恢复车辆外观").setIcon(R.drawable.okdialog).setCancelable(true)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(ImageUploadActivity.this, MainActivity.class));
                            }
                        }).show();
            }
        });
        gridView1 = (GridView) findViewById(R.id.gridView1);
        imageAdapter1 = new ImageAdapter(this, imageList1, gridView1, 1);
        gridView1.setAdapter(imageAdapter1);

        gridView2 = (GridView) findViewById(R.id.gridView2);
        imageAdapter2 = new ImageAdapter(this, imageList2, gridView2, 2);
        gridView2.setAdapter(imageAdapter2);

        gridView3 = (GridView) findViewById(R.id.gridView3);
        imageAdapter3 = new ImageAdapter(this, imageList3, gridView3, 3);
        gridView3.setAdapter(imageAdapter3);

        gridView4 = (GridView) findViewById(R.id.gridView4);
        imageAdapter4 = new ImageAdapter(this, imageList4, gridView4, 4);
        gridView4.setAdapter(imageAdapter4);

        gridView5 = (GridView) findViewById(R.id.gridView5);
        imageAdapter5 = new ImageAdapter(this, imageList5, gridView5, 5);
        gridView5.setAdapter(imageAdapter5);

        gridView6 = (GridView) findViewById(R.id.gridView6);
        imageAdapter6 = new ImageAdapter(this, imageList6, gridView6, 6);
        gridView6.setAdapter(imageAdapter6);
    }

    @Override
    public void showSuccess() {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

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
            File file = new File("/sdcard/myImage/");
            file.mkdirs();// 创建文件夹
            String fileName = "/sdcard/myImage/" + name;

            try {
                b = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
                switch (requestCode) {
                    case 1:
                        imageList1.add(fileName);
                        imageAdapter1 = new ImageAdapter(this, imageList1, gridView1, 1);
                        gridView1.setAdapter(imageAdapter1);
                        break;
                    case 2:
                        imageList2.add(fileName);
                        imageAdapter2 = new ImageAdapter(this, imageList2, gridView2, 2);
                        gridView2.setAdapter(imageAdapter2);
                        break;
                    case 3:
                        imageList3.add(fileName);
                        imageAdapter3 = new ImageAdapter(this, imageList3, gridView3, 3);
                        gridView3.setAdapter(imageAdapter3);
                        break;
                    case 4:
                        imageList4.add(fileName);
                        imageAdapter4 = new ImageAdapter(this, imageList4, gridView4, 4);
                        gridView4.setAdapter(imageAdapter4);
                        break;
                    case 5:
                        imageList5.add(fileName);
                        imageAdapter5 = new ImageAdapter(this, imageList5, gridView5, 5);
                        gridView5.setAdapter(imageAdapter5);
                        break;
                    case 6:
                        imageList6.add(fileName);
                        imageAdapter6 = new ImageAdapter(this, imageList6, gridView6, 6);
                        gridView6.setAdapter(imageAdapter6);
                        break;
                }

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
        }
    }

}
