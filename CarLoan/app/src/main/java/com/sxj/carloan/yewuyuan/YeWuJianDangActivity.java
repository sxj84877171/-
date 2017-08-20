package com.sxj.carloan.yewuyuan;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.FuncResponseBean;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.util.BeanToMap;
import com.sxj.carloan.util.DateUtil;
import com.sxj.carloan.util.FileUtil;
import com.sxj.carloan.util.LogUtil;
import com.sxj.carloan.util.SearchGoogleUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

/**
 * Created by admin on 2017/8/18.
 */

public class YeWuJianDangActivity extends BaseActivity {

    private View jibenxinxi;
    private View shangchuanshenfenzheng_zheng;
    private View shangchuanshenfenzheng_fan;
    private View shangchuanfuzong;
    private View shangchuanzongjingli;
    private View shangchuancheliang;

    private View jibenxinxi_ok;
    private View shangchuanshenfenzheng_zheng_ok;
    private View shangchuanshenfenzheng_fan_ok;
    private View shangchuanfuzong_ok;
    private View shangchuanzongjingli_ok;
    private View shangchuancheliang_ok;

    private Button submit_action;

    public static ServerBean.RowsBean bean = null;

    private int functionChoose;
    private AlertDialog choosePhotoDialog;
    private File photo;
    private Callback<ResponseBody> responseBodyCallback = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            success();
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            LogUtil.e(t);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ye_wu_jian_dang);
        initView();
        initListenser();

        bean = (ServerBean.RowsBean) getIntent().getSerializableExtra("loan");
    }

    private void initView() {
        jibenxinxi = getViewById(R.id.jibenxinxi);
        shangchuanshenfenzheng_zheng = getViewById(R.id.shangchuanshengfenzheng_zheng);
        shangchuanshenfenzheng_fan = getViewById(R.id.shangchuanshengfenzheng_fan);
        shangchuanfuzong = getViewById(R.id.shangchuanfuzong);
        shangchuanzongjingli = getViewById(R.id.shangchuanzongjingli);
        shangchuancheliang = getViewById(R.id.shangchuancheliangzhaopian);

        jibenxinxi_ok = getViewById(R.id.jibenxinxi_ok);
        shangchuanshenfenzheng_zheng_ok = getViewById(R.id.shangchuanshengfenzheng_zheng_ok);
        shangchuanshenfenzheng_fan_ok = getViewById(R.id.shangchuanshengfenzheng_fan_ok);
        shangchuanfuzong_ok = getViewById(R.id.shangchuanfuzong_ok);
        shangchuanzongjingli_ok = getViewById(R.id.shangchuanzongjingli_ok);
        shangchuancheliang_ok = getViewById(R.id.shangchuancheliangzhaopian_ok);

        submit_action = getViewById(R.id.submit_action);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bean == null) {
            jibenxinxi_ok.setVisibility(View.GONE);
            shangchuanshenfenzheng_zheng.setClickable(false);
            shangchuanshenfenzheng_fan.setClickable(false);
            shangchuanfuzong.setClickable(false);
            shangchuanzongjingli.setClickable(false);
            shangchuancheliang.setClickable(false);
        } else {
            jibenxinxi_ok.setVisibility(View.VISIBLE);
            shangchuanshenfenzheng_zheng.setClickable(true);
            shangchuanshenfenzheng_fan.setClickable(true);
            shangchuanfuzong.setClickable(true);
            shangchuanzongjingli.setClickable(true);
            shangchuancheliang.setClickable(true);
        }
        refeashButton();
    }

    private void initListenser() {
        jibenxinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("state", 1);
                intent.setClass(getActivity(), InfomationActivity.class);
                intent.putExtra("loan", bean);
                startActivity(intent);
            }
        });

        shangchuanshenfenzheng_zheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionChoose = 1;
                takePhoto();
            }
        });

        shangchuanshenfenzheng_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionChoose = 2;
                takePhoto();
            }
        });

        shangchuancheliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionChoose = 5;
                takePhoto();
            }
        });

        shangchuanfuzong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionChoose = 3;
                takePhoto();
            }
        });

        shangchuanzongjingli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                functionChoose = 4;
                takePhoto();
            }
        });

        submit_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bean.getCase_state_id() == 0 || bean.getCase_state_id() == 101){
                    bean.setCase_state_id(1);
                }else if(bean.getCase_state_id() == 8 ){
                    bean.setCase_state_id(10);
                }else{
                    bean.setCase_state_id(bean.getCase_state_id() % 100);
                }

                bean.setDate_ywy(DateUtil.getWaterDate());
                model.update(BeanToMap.transRowsBean2Map(bean)).subscribe(new Subscriber<FuncResponseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        toast("fail.");
                    }

                    @Override
                    public void onNext(FuncResponseBean funcResponseBean) {
                        toast("Success!");
                        finish();
                    }
                });

            }
        });

    }

    private void takePhoto() {
        if (choosePhotoDialog == null) {
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
        choosePhotoDialog.show();
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
            public void run() {
                File localFile = file;

                Location location = getLastKnownLocation();
                if (location != null) {
                    try {
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
                if (bean != null) {
                    switch (functionChoose) {
                        case 1:
                            model.shangchuanShenFengzhengZhengmian("" + bean.getCase_type_id_1(), file).enqueue(responseBodyCallback);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    shangchuanshenfenzheng_zheng_ok.setVisibility(View.VISIBLE);
                                }
                            });

                            break;
                        case 2:
                            model.shangchuanShenFengzhengFanmian("" + bean.getCase_type_id_1(), file).enqueue(responseBodyCallback);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    shangchuanshenfenzheng_fan_ok.setVisibility(View.VISIBLE);
                                }
                            });

                            break;
                        case 3:
                            model.shangchuanFuzong("" + bean.getCase_type_id_1(), file).enqueue(responseBodyCallback);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    shangchuanfuzong_ok.setVisibility(View.VISIBLE);
                                }
                            });
                            break;
                        case 4:
                            model.shangchuanZongjingli("" + bean.getCase_type_id_1(), file).enqueue(responseBodyCallback);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    shangchuanzongjingli_ok.setVisibility(View.VISIBLE);
                                }
                            });
                            break;
                        case 5:
                            model.shangchuanCheLiang("" + bean.getCase_type_id_1(), file).enqueue(responseBodyCallback);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    shangchuancheliang_ok.setVisibility(View.VISIBLE);
                                }
                            });
                            break;
                    }
                }
            }
        }.start();
    }

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


    public void refeashButton() {
        if (jibenxinxi_ok.getVisibility() == View.VISIBLE) {
            submit_action.setVisibility(View.VISIBLE);
        }
    }
}
