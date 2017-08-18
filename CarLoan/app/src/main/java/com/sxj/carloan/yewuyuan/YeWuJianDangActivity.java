package com.sxj.carloan.yewuyuan;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.Loan;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.util.FileUtil;
import com.sxj.carloan.util.LogUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
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
    private View shangchuanshenfenzheng_zheng ;
    private View shangchuanshenfenzheng_fan;
    private View shangchuanfuzong;
    private View shangchuanzongjingli;
    private View shangchuancheliang;

    private ServerBean.RowsBean bean;


    private AlertDialog choosePhotoDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ye_wu_jian_dang);
        initView();
        initListenser();
    }

    private void initView(){
        jibenxinxi = getViewById(R.id.jibenxinxi);
        shangchuanshenfenzheng_zheng = getViewById(R.id.shangchuanshengfenzheng_zheng);
        shangchuanshenfenzheng_fan = getViewById(R.id.shangchuanshengfenzheng_fan);
        shangchuanfuzong = getViewById(R.id.shangchuanfuzong);
        shangchuanzongjingli = getViewById(R.id.shangchuanzongjingli);
        shangchuancheliang = getViewById(R.id.shangchuancheliangzhaopian);
    }

    private void initListenser(){
        jibenxinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("state", 1);
                intent.setClass(getActivity(), InfomationActivity.class);
                startActivity(intent);
            }
        });

        shangchuanshenfenzheng_zheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                                File photo = FileUtil.getNewFile(getActivity(), filename);
                                                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                                                if (photo != null) {
                                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
//                                                    selectedImageUri = Uri.fromFile(photo);
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
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      /*  if (resultCode == RESULT_OK) {
            // 拍照前检查SDCard是否存在
            if (!isSDAvailable()) {
                return;
            }

            if (requestCode == 101) {
                File file = new File(getFilePath(selectedImageUri));
                model.uploadIdPhoto(loan, file).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        success();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        LogUtil.e(t);
                    }
                });
            }
            if (requestCode == 102) {
                Uri origina = data.getData();
                File file = new File(getFilePath(origina));
                model.uploadIdPhoto(loan, file).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        success();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
            }
        }*/
    }
}
