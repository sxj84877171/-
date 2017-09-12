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
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sxj.carloan.ApplicationInfoManager;
import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.FuncResponseBean;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.product.ProductFactroy;
import com.sxj.carloan.ui.LoanSubscriber;
import com.sxj.carloan.ui.LoginActivity;
import com.sxj.carloan.ui.ViewInformation;
import com.sxj.carloan.util.BeanToMap;
import com.sxj.carloan.util.DateUtil;
import com.sxj.carloan.util.FileObject;
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
    private TextView shangchuancheliang_ok;

    private Button submit_action;
    private int functionChoose;
    private AlertDialog choosePhotoDialog;
    private File photo;
    private Callback<ResponseBody> responseBodyCallback = new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if (response.isSuccessful()) {
                success();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        switch (functionChoose){
                            case 1:
                                shangchuanshenfenzheng_zheng_ok.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                shangchuanshenfenzheng_fan_ok.setVisibility(View.VISIBLE);
                                break;
                            case 3:
                                shangchuanzongjingli_ok.setVisibility(View.VISIBLE);
                                break;
                            case 4:
                                shangchuanfuzong_ok.setVisibility(View.VISIBLE);
                                break;
                            case 5:
                                shangchuancheliang.setVisibility(View.VISIBLE);
                                break;
                        }

                    }
                });
            } else {
                toast("上传失败，请重新上传");
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            toast("上传出错，请重新上传");
            LogUtil.e(t);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ye_wu_jian_dang);
        initView();
        initListenser();
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
        if (loan == null || loan.getId() < 1) {
            loan = null;
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
            refeashButton();

            if (loan.getId() > 0) {
                model.FileExisted("photo/" + loan.getId() + "/1_1.jpg").subscribe(new LoanSubscriber<FuncResponseBean>() {
                    @Override
                    public void onNext(FuncResponseBean funcResponseBean) {
                        if ("YES".equals(funcResponseBean.getSuccess())) {
                            shangchuanshenfenzheng_zheng_ok.setVisibility(View.VISIBLE);
                        }
                    }
                });
                model.FileExisted("photo/" + loan.getId() + "/1_2.jpg").subscribe(new LoanSubscriber<FuncResponseBean>() {
                    @Override
                    public void onNext(FuncResponseBean funcResponseBean) {
                        if ("YES".equals(funcResponseBean.getSuccess())) {
                            shangchuanshenfenzheng_fan_ok.setVisibility(View.VISIBLE);
                        }
                    }
                });
                model.FileExisted("photo/" + loan.getId() + "/1_3.jpg").subscribe(new LoanSubscriber<FuncResponseBean>() {
                    @Override
                    public void onNext(FuncResponseBean funcResponseBean) {
                        if ("YES".equals(funcResponseBean.getSuccess())) {
                            shangchuanfuzong_ok.setVisibility(View.VISIBLE);
                        }else{
                            shangchuanfuzong_ok.setVisibility(View.GONE);
                        }
                    }
                });
                model.FileExisted("photo/" + loan.getId() + "/1_4.jpg").subscribe(new LoanSubscriber<FuncResponseBean>() {
                    @Override
                    public void onNext(FuncResponseBean funcResponseBean) {
                        if ("YES".equals(funcResponseBean.getSuccess())) {
                            shangchuanzongjingli_ok.setVisibility(View.VISIBLE);
                        }else{
                            shangchuanzongjingli_ok.setVisibility(View.GONE);
                        }
                    }
                });
                model.getCarPhotoName(loan.getId()).subscribe(new LoanSubscriber<FuncResponseBean>() {
                    @Override
                    public void onNext(FuncResponseBean funcResponseBean) {
                        if ("YES".equals(funcResponseBean.getSuccess())) {
                            if (!TextUtils.isEmpty(funcResponseBean.getMessage())) {
                                cheliangNum = funcResponseBean.getMessage().split(",").length;
                                if (cheliangNum > 0) {
                                    shangchuancheliang_ok.setVisibility(View.VISIBLE);
                                    shangchuancheliang_ok.setText("+" + cheliangNum);
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    private void initListenser() {
        jibenxinxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("state", 1);
                ApplicationInfoManager.getInstance().setInfo(loan);
                intent.setClass(getActivity(), InfomationActivity.class);
                startActivity(intent);
            }
        });

        shangchuanshenfenzheng_zheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loan != null && loan.getId() > 0) {
                    functionChoose = 1;
                    takePhoto();
                } else {
                    toast("请录入基本信息再上传照片");
                }
            }
        });

        shangchuanshenfenzheng_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loan != null && loan.getId() > 0) {
                    functionChoose = 2;
                    takePhoto();
                } else {
                    toast("请录入基本信息再上传照片");
                }
            }
        });

        shangchuancheliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loan != null && loan.getId() > 0) {
                    functionChoose = 5;
                    takePhoto();
                } else {
                    toast("请录入基本信息再上传照片");
                }
            }
        });

        shangchuanfuzong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loan != null && loan.getId() > 0) {
                    functionChoose = 3;
                    takePhoto();
                } else {
                    toast("请录入基本信息再上传照片");
                }
            }
        });

        shangchuanzongjingli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loan != null && loan.getId() > 0) {
                    functionChoose = 4;
                    takePhoto();
                } else {
                    toast("请录入基本信息再上传照片");
                }
            }
        });

        submit_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loan != null && loan.getId() > 0) {
                    double total = 0;
                    try {
                        total = Double.parseDouble(loan.getFee_total());
                    }catch (Exception e){}
                    if(total == 0){
                        try {
                        total = Double.parseDouble(loan.getEarlier_fee());
                        }catch (Exception e){}
                    }
                    if (total > 0) {
                        String cast_type_id = ProductFactroy.getInstance().processProductType(Integer.parseInt(loan.getProduct_id()), 0).getCarType();
                        model.YwyPhotoOk(loan.getId(), cast_type_id).subscribe(new LoanSubscriber<FuncResponseBean>() {
                            @Override
                            public void onNext(FuncResponseBean funcResponseBean) {
                                if ("YES".equals(funcResponseBean.getSuccess())) {
                                    if (loan.getCase_state_id() == 0 || loan.getCase_state_id() == 101) {
                                        loan.setCase_state_id(1);
                                    } else if (loan.getCase_state_id() == 8) {
                                        loan.setCase_state_id(10);
                                    } else {
                                        loan.setCase_state_id(loan.getCase_state_id() % 100);
                                    }

                                    loan.setDate_ywy(DateUtil.getWaterDate());
                                    model.update(BeanToMap.transRowsBean2Map(loan)).subscribe(new LoanSubscriber<FuncResponseBean>() {
                                        @Override
                                        public void onNext(FuncResponseBean funcResponseBean) {
                                            toast("Success!");
                                            finish();
                                        }
                                    });
                                } else {
                                    toast("请先上传证照！");
                                }

                            }
                        });
                    }else {
                        toast("请先计算税费.");
                    }
                } else {
                    toast("请录入基本信息再上传照片");
                }

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
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
                                "宋体", 50, Color.YELLOW, 25, 20, 0, 0x88);
                        if (file1 != null) {
                            localFile = file1;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if (loan != null && loan.getId() > 0) {
                    switch (functionChoose) {
                        case 1:
                            model.shangchuanShenFengzhengZhengmian("" + loan.getId(), localFile).enqueue(responseBodyCallback);


                            break;
                        case 2:
                            model.shangchuanShenFengzhengFanmian("" + loan.getId(), localFile).enqueue(responseBodyCallback);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    shangchuanshenfenzheng_fan_ok.setVisibility(View.VISIBLE);
                                }
                            });

                            break;
                        case 3:
                            model.shangchuanFuzong("" + loan.getId(), localFile).enqueue(responseBodyCallback);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    shangchuanfuzong_ok.setVisibility(View.VISIBLE);
                                }
                            });
                            break;
                        case 4:
                            model.shangchuanZongjingli("" + loan.getId(), localFile).enqueue(responseBodyCallback);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    shangchuanzongjingli_ok.setVisibility(View.VISIBLE);
                                }
                            });
                            break;
                        case 5:
                            model.shangchuanCheLiang("" + loan.getId(), localFile).enqueue(responseBodyCallback);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    shangchuancheliang_ok.setVisibility(View.VISIBLE);
                                    shangchuancheliang_ok.setText("+" + (++cheliangNum));
                                }
                            });
                            break;
                    }
                }
            }
        }.start();
    }

    private int cheliangNum = 0;

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

        double ywy = 0;
        if (loan != null && loan.getId() > 0) {
            ywy = getDoubleByString(loan.getLoan_amount_ywy());
        }
        if (ywy > 0) {
            submit_action.setVisibility(View.VISIBLE);
        } else {
            submit_action.setVisibility(View.GONE);
        }
    }

    public void calc(View view) {
        double ywy = 0;
        if (loan != null && loan.getId() > 0) {
            ywy = getDoubleByString(loan.getLoan_amount_ywy());
        }
        if (ywy > 0) {
            gotoCalcActivity(loan, 1);
        } else {
            toast("请先填写完贷款信息");
        }
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
            menu.add(2, 2, 2, "查看详情");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        LogUtil.i("item " + item.getItemId());
        if (item.getItemId() == 2) {
            gotoViewInfo(1);
        }
        return super.onOptionsItemSelected(item);
    }
}
