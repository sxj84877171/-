package com.sxj.carloan.ui.investigation;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.FuncResponseBean;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.rongyi.VideoChatViewActivity;
import com.sxj.carloan.rongyi.VideoListActivity;
import com.sxj.carloan.ui.LoanSubscriber;
import com.sxj.carloan.ui.LoginActivity;
import com.sxj.carloan.util.BeanToMap;
import com.sxj.carloan.util.DateUtil;
import com.sxj.carloan.util.FileObject;
import com.sxj.carloan.util.FileUtil;
import com.sxj.carloan.util.GlideImageLoader;
import com.sxj.carloan.util.LogUtil;
import com.sxj.carloan.util.SearchGoogleUtil;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by admin on 2017/8/11.
 */

public class InvestigationFunctionChoose extends BaseActivity {

    private int functionChoose;
    private AlertDialog choosePhotoDialog;
    private File photo;
    private int lujia = 0;
    private int lugong = 0;
    private int luyin = 0;
    private int lumian = 0;
    private int luchan = 0;
    private int luzheng = 0;
    private int lumore = 0;

    private String TAG = "FunctionChoose";
    private GalleryConfig galleryConfig;
    protected IHandlerCallBack iHandlerCallBack;
    protected List<String> path = new ArrayList<>();
    private final int PERMISSIONS_REQUEST_READ_CONTACTS = 8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_function_investigation);

        submit_action = getViewById(R.id.submit_action);
        initPermissions();
        initGallery();
        initImageLoad();

        model.GetDcyPhotoName(loan.getId()).subscribe(new LoanSubscriber<FuncResponseBean>() {
            @Override
            public void onNext(FuncResponseBean funcResponseBean) {
                super.onNext(funcResponseBean);
                if ("YES".equals(funcResponseBean.getSuccess())) {
                    String msg = funcResponseBean.getMessage();
                    if (!TextUtils.isEmpty(msg)) {
                        String[] strs = msg.split(",");
                        for (String str : strs) {
                            if (str.startsWith(loan.getId() + "_1d")) {
                                lujia++;
                            }
                            if (str.startsWith(loan.getId() + "_2d")) {
                                lugong++;
                            }
                            if (str.startsWith(loan.getId() + "_3d")) {
                                luyin++;
                            }
                            if (str.startsWith(loan.getId() + "_4d")) {
                                lumian++;
                            }
                            if (str.startsWith(loan.getId() + "_5d")) {
                                luchan++;
                            }
                            if (str.startsWith(loan.getId() + "_6d")) {
                                luzheng++;
                            }
                        }
                    }
                    refreashNum();
                }
            }
        });
    }

    private void initImageLoad() {
        galleryConfig = new GalleryConfig.Builder()
                .imageLoader(new GlideImageLoader())    // ImageLoader 加载框架（必填）
                .iHandlerCallBack(iHandlerCallBack)     // 监听接口（必填）
                .provider("com.yancy.gallerypickdemo.fileprovider")   // provider(必填)
                .pathList(path)                         // 记录已选的图片
                .multiSelect(true)                      // 是否多选   默认：false
                .multiSelect(true, 9)                   // 配置是否多选的同时 配置多选数量   默认：false ， 9
                .maxSize(9)                             // 配置多选时 的多选数量。    默认：9
                .crop(false)                             // 快捷开启裁剪功能，仅当单选 或直接开启相机时有效
                .crop(false, 1, 1, 500, 500)             // 配置裁剪功能的参数，   默认裁剪比例 1:1
                .isShowCamera(true)                     // 是否现实相机按钮  默认：false
                .filePath("/Gallery/Pictures")          // 图片存放路径
                .isOpenCamera(false)
                .build();
    }


    private void refreashNum() {
        if (lujia > 0) {
            ((TextView) findViewById(R.id.lujia)).setText("(" + lujia + ")");
        }
        if (lugong > 0) {
            ((TextView) findViewById(R.id.lugong)).setText("(" + lugong + ")");
        }
        if (luyin > 0) {
            ((TextView) findViewById(R.id.luyin)).setText("(" + luyin + ")");
        }
        if (lumian > 0) {
            ((TextView) findViewById(R.id.lumian)).setText("(" + lumian + ")");
        }
        if (luchan > 0) {
            ((TextView) findViewById(R.id.luchan)).setText("(" + luchan + ")");
        }
        if (luzheng > 0) {
            ((TextView) findViewById(R.id.luzheng)).setText("(" + luzheng + ")");
        }
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(1, 1, 1, "查看详情");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == 1) {
            gotoViewInfo(2);
        }
        return super.onOptionsItemSelected(item);
    }

    public void rongYiMianQian(View view) {
        Intent intent = new Intent(this, VideoChatViewActivity.class);
        startActivity(intent);
    }

    public void modifyInfo(View view) {
        toast("功能在努力开发中……");
    }

    public void addMoreInfo(View view) {
//        toast("功能在努力开发中……");
        gotoWeiFuXinXi(loan);
    }

    public void uploadFamilyPhoto(View view) {
//        toast("功能在努力开发中……");
        functionChoose = 1;
        takePhoto();

    }

    public void uploadWorkAddressPhoto(View view) {
//        toast("功能在努力开发中……");
        functionChoose = 2;
        takePhoto();
    }

    public void uploadBankPhoto(View view) {
//        toast("功能在努力开发中……");
        functionChoose = 3;
        takePhoto();
    }

    public void lurumianqian(View view) {
        functionChoose = 4;
        takePhoto();
    }

    public void uploadChanPhoto(View view) {
//        toast("功能在努力开发中……");
        functionChoose = 5;
        takePhoto();
    }


    public void uploadIdPhoto(View view) {
//        toast("功能在努力开发中……");
        functionChoose = 6;
        takePhoto();
    }

    public void calc(View view) {
        double dcy = 0;
        try {
            dcy = Double.parseDouble(loan.getLoan_amount_dcy());
        } catch (Exception ex) {
        }
        if (dcy > 0) {
            gotoCalcActivity(loan, 2);
        } else {
            toast("请先进行评估后，再计算。");
        }

//
    }

    public void sumbitInfo(View view) {
        if(loan.getZhengxin_result_id() == 0){
            toast("征信未查，请先通过征信！");
            return;
        }

        if(loan.getZhengxin_result_id() == 1){
            toast("征信未通过，请先通过征信！");
            return;
        }

        if(loan.getUser_id_baoxian() == 0 && loan.getCase_type_id() == 1){
            toast("未做评估,请先做评估");
            return;
        }

        if (loan.getDcy_result_id() == 1) {
            loan.setCase_state_id(105);
        } else {
            if (Double.parseDouble(loan.getLoan_amount_dcy()) > 10) {
                loan.setCase_state_id(6);
            } else {
                loan.setCase_state_id(8);
            }
        }
        model.update(BeanToMap.transRowsBean2Map(loan)).subscribe(new Subscriber<FuncResponseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                toast("保存出错");
            }

            @Override
            public void onNext(FuncResponseBean funcResponseBean) {
                if ("YES".equals(funcResponseBean.getSuccess())) {
                    toast("提交成功");
                    finish();
                } else {
                    toast("保存失败");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    private void takePhoto() {
       /* if (choosePhotoDialog == null) {
            String[] args = new String[]{"拍照", "我的相册"};
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, final int which) {
                    RxPermissions rxPermissions = new RxPermissions(getActivity());
                    rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            .subscribe(new Subscriber<Boolean>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(Boolean aBoolean) {
                                    if (aBoolean) {
                                        if (which == 0) {
                                            String filename = UUID.randomUUID() + ".jpg";
                                            photo = FileUtil.getNewFile(getActivity(), filename);
                                            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                                            if (photo != null) {
                                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
                                                startActivityForResult(intent, 101);
                                            }
                                        } else {
                                            Intent mIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                            startActivityForResult(mIntent, 102);
                                        }
                                    } else {
                                        getPermissionFail();
                                    }
                                }
                            });

                }
            };
            choosePhotoDialog = createAlertDialog(args, listener);
        }
        choosePhotoDialog.show();*/
        GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // 拍照前检查SDCard是否存在
            if (!isSDAvailable()) {
                return;
            }
            if (requestCode == 101) {
                uploadImage(photo);
            }
            if (requestCode == 102) {
                Uri origina = data.getData();
                File file = new File(getFilePath(origina));
                uploadImage(file);
            }
        }
    }

    private void uploadImage(final File file) {
        new Thread() {
            public synchronized void run() {
                File localFile = file;

                Location location = getLastKnownLocation();
                if (location != null) {
                    try {
                        compressBitmap(file.getAbsolutePath(),500,file.getAbsolutePath());
                        Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        String address = SearchGoogleUtil.getAddr(location.getLatitude(), location.getLongitude());
                        File file1 = pressText(DateUtil.getWaterDate(), String.format(
                                getString(R.string.latitude_longitude),
                                location.getLatitude(), location.getLongitude()), address, myBitmap,
                                "宋体", 36, Color.YELLOW, 25, 20, 0, 0x88);
                        if (file1 != null) {
                            localFile = file1;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }

                if (loan != null) {
                    switch (functionChoose) {
                        case 1:
                            responseBodyCallback = new ResponseBodyCallback();
                            responseBodyCallback.setPath(file.getName());
                            model.shangchuanDiaoChayuan1("" + loan.getId(), localFile).enqueue(responseBodyCallback);
                            break;
                        case 2:
                            model.shangchuanDiaoChayuan2("" + loan.getId(), localFile).enqueue(responseBodyCallback);
                            break;
                        case 3:
                            model.shangchuanDiaoChayuan3("" + loan.getId(), localFile).enqueue(responseBodyCallback);
                            break;
                        case 4:
                            model.shangchuanDiaoChayuan4("" + loan.getId(), localFile).enqueue(responseBodyCallback);
                            break;
                        case 5:
                            model.shangchuanDiaoChayuan5("" + loan.getId(), localFile).enqueue(responseBodyCallback);
                        case 6:
                            model.shangchuanDiaoChayuan6("" + loan.getId(), localFile).enqueue(responseBodyCallback);
                            break;
                    }
                }
            }
        }.start();
    }

    private ResponseBodyCallback responseBodyCallback = new ResponseBodyCallback();
    private class ResponseBodyCallback implements Callback<ResponseBody> {

        private String path ;

        public void setPath(String path) {
            this.path = path;
        }

        @Override
        public synchronized void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            switch (functionChoose) {
                case 1:
                    lujia++;
                    break;
                case 2:
                    lugong++;
                    break;
                case 3:
                    luyin++;
                    break;
                case 4:
                    lumian++;
                    break;
                case 5:
                    luchan++;
                    break;
                case 6:
                    luzheng++;
                    break;
            }

            refreashNum();
            toast(path + " 上传成功！");
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            LogUtil.e(t);
            toast(path + " 上传失败，请重试！");
        }
    };

    /**
     * 根据Uri返回文件路径
     */
    private String getFilePath(Uri mUri) {
        try {
            if (mUri.getScheme().equals("file")) {
                return mUri.getPath();
            } else {
                return getFilePathByUri(mUri);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private String getFilePathByUri(Uri mUri) throws FileNotFoundException {
        String imgPath;
        Cursor cursor = getContentResolver().query(mUri, null, null, null, null);
        cursor.moveToFirst();
        imgPath = cursor.getString(1); // 图片文件路径
        return imgPath;
    }

    /**
     * 检查SD卡是否可用
     */
    private boolean isSDAvailable() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    private View submit_action;

    @Override
    protected void onResume() {
        super.onResume();
        double dcy = getDoubleByString(loan.getLoan_amount_dcy());
        if (dcy > 0) {
            submit_action.setVisibility(View.VISIBLE);
        } else {
            submit_action.setVisibility(View.GONE);
        }
    }

    private void initGallery() {
        iHandlerCallBack = new IHandlerCallBack() {
            @Override
            public void onStart() {
                Log.i(TAG, "onStart: 开启");
            }

            @Override
            public void onSuccess(List<String> photoList) {
                LogUtil.i(TAG, "onSuccess: 返回数据");
                path.clear();
                for (String s : photoList) {
//                    LogUtil.i(TAG, s);
//                    path.add(s);
                    uploadImage(new File(s));
                }
            }

            @Override
            public void onCancel() {
                LogUtil.i(TAG, "onCancel: 取消");
            }

            @Override
            public void onFinish() {
                LogUtil.i(TAG, "onFinish: 结束");
            }

            @Override
            public void onError() {
                Log.i(TAG, "onError: 出错");
            }
        };

    }

    // 授权管理
    private void initPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "需要授权 ");
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Log.i(TAG, "拒绝过了");
                Toast.makeText(this, "请在 设置-应用管理 中开启此应用的储存授权。", Toast.LENGTH_SHORT).show();
            } else {
                Log.i(TAG, "进行授权");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        } else {
            Log.i(TAG, "不需要授权 ");
            GalleryPick.getInstance().setGalleryConfig(galleryConfig).open(this);
        }
    }
}
