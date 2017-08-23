package com.sxj.carloan.ui.investigation;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.View;
import android.widget.Toast;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.FuncResponseBean;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.rongyi.VideoChatViewActivity;
import com.sxj.carloan.rongyi.VideoListActivity;
import com.sxj.carloan.util.BeanToMap;
import com.sxj.carloan.util.DateUtil;
import com.sxj.carloan.util.FileUtil;
import com.sxj.carloan.util.LogUtil;
import com.sxj.carloan.util.SearchGoogleUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_function_investigation);

        submit_action = getViewById(R.id.submit_action);

    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
//        menu.add(1, 1, 1, "查看面签视频");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == 1) {
            Intent intent = new Intent(this, VideoListActivity.class);
            startActivity(intent);
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


    public void uploadChanPhoto(View view) {
//        toast("功能在努力开发中……");
        functionChoose = 4;
        takePhoto();
    }


    public void uploadIdPhoto(View view) {
//        toast("功能在努力开发中……");
        functionChoose = 5;
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

                if (loan != null) {
                    switch (functionChoose) {
                        case 1:
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
                            break;
                    }
                }
            }
        }.start();
    }


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
}
