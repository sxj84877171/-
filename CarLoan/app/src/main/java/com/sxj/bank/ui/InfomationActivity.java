package com.sxj.bank.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sxj.bank.BaseActivity;
import com.sxj.bank.R;
import com.sxj.bank.bean.FuncResponseBean;
import com.sxj.bank.bean.ServerBean;
import com.sxj.bank.util.FileUtil;
import com.sxj.bank.util.LogUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;

public class InfomationActivity extends BaseActivity {

    private TextView business_type;
    private View business_type_line;

    private EditText apply_name;
    private TextView sex;
    private View sex_line;
    private View marry_state_line;
    private View is_compension_line;
    private View house_type_line;
    private View staget_type_line;
    private View build_date_line;
    private View home_viste_date_line;
    private View business_state_line;
    private EditText id_no;
    private TextView marry_state;
    private EditText phone_num;
    private TextView is_compension;
    private TextView house_type;
    private TextView home_viste_date;
    private EditText house_address;
    private EditText car_typeEditText;
    private EditText transaction_price;
    private EditText loan_time;
    private TextView staget_type;

    private TextView person_id;
    private TextView build_date;
    private TextView business_state;

    private Button modify;
    private Button save;

    private ServerBean.RowsBean loan;

    private boolean isModify = false;


    /**
     * 0 view
     * 1 modify
     * 2 modify
     */
    private int state = 0;


    /**
     * @param savedInstanceState
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        initViewById();
        Intent intent = getIntent();
        loan = (ServerBean.RowsBean) intent.getSerializableExtra("data");
        if (loan == null) {
            loan = new ServerBean.RowsBean();
        }
        state = intent.getIntExtra("state", 0);

        initViewById();
        displayState();
        initListener();
        initData();
        initChoose();
    }

    private void initListener() {
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = 1;
                isModify = true;
                displayState();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //put to net
                save();
            }
        });
        boolean stateBoolean = state != 0;
        if(!stateBoolean){
            business_type_line.setOnClickListener(null);
            sex_line.setOnClickListener(null);
            marry_state_line.setOnClickListener(null);
            is_compension_line.setOnClickListener(null);
            house_type_line.setOnClickListener(null);
            staget_type_line.setOnClickListener(null);
            home_viste_date_line.setOnClickListener(null);
            build_date_line.setOnClickListener(null);
            business_state_line.setOnClickListener(null);
        }else {
            business_type_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (typeDialog == null) {
                        createAlertTypeDialog();
                    } else {
                        typeDialog.show();
                    }
                }
            });

            sex_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (sexDialog == null) {
                        createAlertSexDialog();
                    } else {
                        sexDialog.show();
                    }
                }
            });

            marry_state_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createMarrayDialog();
                }
            });

            is_compension_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createComparssDialog();
                }
            });

            house_type_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createRootTypeDialog();
                }
            });

            staget_type_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createStagetTypeDialog();
                }
            });

            build_date_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createBuildDatePickDialog();
                }
            });

            home_viste_date_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createVisitDatePickDialog();
                }
            });
            business_state_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createRoldDialog();
                }
            });
        }
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(1, 1, 1, "家庭情况");
        menu.add(1, 2, 2, "个人信用");
        menu.add(1, 3, 3, "偿还能力");
        menu.add(1, 4, 4, "审核意见");
        menu.add(1, 5, 5, "预览报告");
        menu.add(1, 6, 6, "下载报告");

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(android.view.Menu menu) {
//        menu.removeItem(10);
//        if (state != 0) {
//            menu.add(1, 10, 10, "照片");
//        }
        if (loan.getId() > 0) {
            menu.removeItem(7);
            menu.add(1, 7, 7, "上传照片");
        }
        return super.onPrepareOptionsMenu(menu);

    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        LogUtil.i("item " + item.getItemId());
        if (item.getItemId() == 1) {
            Intent intent = new Intent();
            Bundle extras = new Bundle();
            extras.putSerializable("data", loan);
            intent.putExtras(extras);
            intent.setClass(this, HomeInfoAcitivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == 2) {
            Intent intent = new Intent();
            Bundle extras = new Bundle();
            extras.putSerializable("data", loan);
            intent.putExtras(extras);
            intent.setClass(this, PersonCreditActivity.class);
            startActivity(intent);
        }

        if (item.getItemId() == 3) {
            Intent intent = new Intent();
            Bundle extras = new Bundle();
            extras.putSerializable("data", loan);
            intent.putExtras(extras);
            intent.setClass(this, RepaymentActivity.class);
            startActivity(intent);
        }


        if (item.getItemId() == 7) {
            createChoosePhotoDialog();
        }
        return super.onOptionsItemSelected(item);
    }


    private void initViewById() {
        business_type = getViewById(R.id.business_type);
        business_type_line = getViewById(R.id.business_type_line);
        sex = getViewById(R.id.sex);
        sex_line = getViewById(R.id.sex_line);
        id_no = getViewById(R.id.id_no);
        marry_state = getViewById(R.id.marry_state);
        phone_num = getViewById(R.id.phone_num);
        is_compension = getViewById(R.id.is_compension);
        house_type = getViewById(R.id.house_type);
        home_viste_date = getViewById(R.id.home_viste_date);
        house_address = getViewById(R.id.house_address);

        marry_state_line = getViewById(R.id.marry_state_line);
        is_compension_line = getViewById(R.id.is_compension_line);
        house_type_line = getViewById(R.id.house_type_line);
        staget_type_line = getViewById(R.id.staget_type_line);
        home_viste_date_line = getViewById(R.id.home_viste_date_line);
        build_date_line = getViewById(R.id.build_date_line);

        car_typeEditText = getViewById(R.id.car_type);
        transaction_price = getViewById(R.id.transaction_price);
        loan_time = getViewById(R.id.loan_time);
        staget_type = getViewById(R.id.staget_type);
        person_id = getViewById(R.id.person_id);
        build_date = getViewById(R.id.build_date);
        business_state = getViewById(R.id.business_state);
        apply_name = getViewById(R.id.apply_name);
        business_state_line = getViewById(R.id.business_state_line);
        modify = getViewById(R.id.add);
        save = getViewById(R.id.save);
    }

    void initData() {


        if (loan != null) {
            apply_name.setText(getLoanString(loan.getCust_name_tmp()));
            sex.setText(getLoanString(loan.getCust_sex()));
            id_no.setText(getLoanString(loan.getCust_iden()));
            phone_num.setText(getLoanString(loan.getCust_mobile()));
            home_viste_date.setText(loan.getHome_visit_date());
            car_typeEditText.setText(loan.getCar_type());
            transaction_price.setText(loan.getDeal_price() + "");
            loan_time.setText(loan.getCredit_years() + "");
            build_date.setText(getLoanString(loan.getDate_ywy()));
            business_state.setText(initCaseState(loan.getCase_state_id()));
//            person_id.setText(getLoginInfo().getUser_id());
            if(loan.getUser_id_ywy() != 0) {
                person_id.setText(loan.getUser_id_ywy() + "");
            }else{
                person_id.setText(getLoginInfo().getUser_id());
            }
        }
    }


    private void displayState() {
        changeState();
        initListener();
        if (state == 0) {
            modify.setVisibility(View.VISIBLE);
            save.setVisibility(View.GONE);
            modify.setText("修改");
        } else {
            modify.setVisibility(View.GONE);
            save.setVisibility(View.VISIBLE);
        }
    }

    private void changeState() {
        boolean stateBoolean = state != 0;
        apply_name.setFocusable(stateBoolean);
        apply_name.setEnabled(stateBoolean);
        apply_name.setFocusableInTouchMode(stateBoolean);
        business_type.setFocusable(stateBoolean);
        business_type.setEnabled(stateBoolean);
        business_type.setFocusableInTouchMode(stateBoolean);
        id_no.setFocusable(stateBoolean);
        id_no.setFocusableInTouchMode(stateBoolean);
        id_no.setEnabled(stateBoolean);
        marry_state.setFocusable(stateBoolean);
        marry_state.setFocusableInTouchMode(stateBoolean);
        marry_state.setEnabled(stateBoolean);
        house_address.setFocusable(stateBoolean);
        house_address.setFocusableInTouchMode(stateBoolean);
        house_address.setEnabled(stateBoolean);
        phone_num.setFocusable(stateBoolean);
        phone_num.setFocusableInTouchMode(stateBoolean);
        phone_num.setEnabled(stateBoolean);
        house_type.setFocusable(stateBoolean);
        house_type.setFocusableInTouchMode(stateBoolean);
        house_type.setEnabled(stateBoolean);
        home_viste_date.setFocusable(stateBoolean);
        home_viste_date.setFocusableInTouchMode(stateBoolean);
        home_viste_date.setEnabled(stateBoolean);
        car_typeEditText.setFocusable(stateBoolean);
        car_typeEditText.setFocusableInTouchMode(stateBoolean);
        car_typeEditText.setEnabled(stateBoolean);
        transaction_price.setFocusable(stateBoolean);
        transaction_price.setFocusableInTouchMode(stateBoolean);
        transaction_price.setEnabled(stateBoolean);
        loan_time.setFocusable(stateBoolean);
        loan_time.setFocusableInTouchMode(stateBoolean);
        loan_time.setEnabled(stateBoolean);
        staget_type.setFocusable(stateBoolean);
        staget_type.setFocusableInTouchMode(stateBoolean);
        staget_type.setEnabled(stateBoolean);
//        person_id.setFocusable(stateBoolean);
//        person_id.setEnabled(stateBoolean);
//        person_id.setFocusableInTouchMode(stateBoolean);
        build_date.setFocusable(stateBoolean);
        build_date.setFocusableInTouchMode(stateBoolean);
        build_date.setEnabled(stateBoolean);
        business_state.setFocusable(stateBoolean);
        business_state.setFocusableInTouchMode(stateBoolean);
        business_state.setEnabled(stateBoolean);
    }


    public String getLoanString(String str) {
        if (str != null) {
            return str.trim();
        }
        return "";
    }


    public String isMarry(int marry) {
        return marry == 0 ? "已婚" : "未婚";
    }

    private AlertDialog typeDialog;
    private AlertDialog sexDialog;
    private AlertDialog marrayDialog;
    private AlertDialog comparssDialog;
    private AlertDialog rootTypeDialog;
    private AlertDialog stagetTypeDialog;
    private AlertDialog choosePhotoDialog;
    private AlertDialog roleDialog ;
    private AlertDialog visitDatePickDialog ;
    private AlertDialog buildDatePickDialog ;

    void createBuildDatePickDialog(){
        if(buildDatePickDialog == null){
            IDateChooseListener listener = new IDateChooseListener() {
                @Override
                public void onDateChoose(String date, int year, int month, int day) {
                    build_date.setText(date);
                    loan.setDate_ywy(date);
                }
            };
            buildDatePickDialog = createDataTimePick(listener);
        }
        buildDatePickDialog.show();
    }

    void createVisitDatePickDialog(){
        if(visitDatePickDialog == null){
            IDateChooseListener listener = new IDateChooseListener() {
                @Override
                public void onDateChoose(String date, int year, int month, int day) {
                    home_viste_date.setText(date);
                    loan.setHome_visit_date(date);
                }
            };
            visitDatePickDialog = createDataTimePick(listener);
        }
        visitDatePickDialog.show();
    }

    void createStagetTypeDialog() {
        if (stagetTypeDialog == null) {
            String args[] = new String[]{"半分", "全分"};
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    loan.setInstallment_type_id(which + 1);
                    initStageType(which);
                    stagetTypeDialog.dismiss();
                }
            };
            stagetTypeDialog = createAlertDialog(args, listener);
        }
        stagetTypeDialog.show();
    }

    void initChoose() {
        initStageType(loan.getInstallment_type_id() - 1);
        initHouseType(loan.getRoom_type_id() - 1);
        initCompension(loan.getIf_gcr_id());
        initMarrayState(loan.getCust_marriage_id() - 1);
        sex.setText(loan.getCust_sex());
        initType(loan.getCase_type_id() - 1);
    }

    private void initStageType(int which) {
        switch (which) {
            case 0:
                staget_type.setText("半分");
                break;
            case 1:
                staget_type.setText("全分");
                break;
        }
    }

    private Uri selectedImageUri;

    void createChoosePhotoDialog() {
        if (choosePhotoDialog == null) {
            String[] args = new String[]{"拍照", "我的相册"};
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, final int which) {
                    RxPermissions rxPermissions = new RxPermissions(InfomationActivity.this);
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
                                            File photo = FileUtil.getNewFile(InfomationActivity.this, filename);
                                            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                                            if (photo != null) {
                                                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
                                                selectedImageUri = Uri.fromFile(photo);
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
/*
               File file = new File(getFilePath(selectedImageUri));
                new ApiServiceModel().uploadPhoto(loan,file).subscribe(new Subscriber<FuncResponseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(FuncResponseBean funcResponseBean) {
                        if("YES".equals(funcResponseBean.getSuccess())){
                            success();
                        }else{

                        }
                    }
                });*/
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
        }
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

    void createRootTypeDialog() {
        if (rootTypeDialog == null) {
            String[] args = new String[]{"全款商品房", "按揭商品房", "自建房", "租房", "亲属住房"};
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    loan.setRoom_type_id(which + 1);
                    initHouseType(which);
                    rootTypeDialog.dismiss();
                }
            };
            rootTypeDialog = createAlertDialog(args, listener);
        }
        rootTypeDialog.show();
    }

    private void initHouseType(int which) {
        switch (which) {
            case 0:
                house_type.setText("全款商品房");
                break;
            case 1:
                house_type.setText("按揭商品房");
                break;
            case 2:
                house_type.setText("自建房");
                break;
            case 3:
                house_type.setText("租房");
                break;
            case 4:
                house_type.setText("亲属住房");
                break;
        }
    }

    void createComparssDialog() {
        if (comparssDialog == null) {
            String[] args = new String[]{"否", "是"};
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    loan.setIf_gcr_id(which);
                    initCompension(which);
                    comparssDialog.dismiss();
                }
            };
            comparssDialog = createAlertDialog(args, listener);
        }
        comparssDialog.show();
    }

    private void initCompension(int which) {
        switch (which) {
            case 0:
                is_compension.setText("否");
                break;
            case 1:
                is_compension.setText("是");
                break;
        }
    }

    void createMarrayDialog() {
        if (marrayDialog == null) {
            String[] args = new String[]{"已婚", "未婚", "离异", "丧偶", "离异再婚"};
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    loan.setCust_marriage_id(which + 1);
                    initMarrayState(which);
                    marrayDialog.dismiss();
                }
            };
            marrayDialog = createAlertDialog(args, listener);
        }
        marrayDialog.show();
    }

    private void initMarrayState(int which) {
        switch (which) {
            case 0:
                marry_state.setText("已婚");
                break;
            case 1:
                marry_state.setText("未婚");
                break;
            case 2:
                marry_state.setText("离异");
                break;
            case 3:
                marry_state.setText("丧偶");
                break;
            case 4:
                marry_state.setText("离异再婚");
                break;

        }
    }

    void createAlertSexDialog() {
        if (sexDialog == null) {
            String[] args = new String[]{"男", "女"};
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    initSex(which);
                    sex.setText(loan.getCust_sex());
                    sexDialog.dismiss();
                }
            };

            sexDialog = createAlertDialog(args, listener);
        }
        sexDialog.show();
    }

    private void initSex(int which) {
        if (which == 0) {
            loan.setCust_sex("男");
        } else {
            loan.setCust_sex("女");
        }
    }


    void createAlertTypeDialog() {
        if (typeDialog == null) {
            String[] args = new String[]{"二手车", "新车"};
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    initType(which);
                    loan.setCase_type_id(which + 1);
                    typeDialog.dismiss();
                }
            };

            typeDialog = createAlertDialog(args, listener);
        }
        typeDialog.show();
    }

    private void initType(int which) {
        if (which == 0) {
            business_type.setText("二手车");
        } else {
            business_type.setText("新车");
        }
    }


    AlertDialog createAlertDialog(String[] args, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(args, listener);
        return builder.create();
    }

    AlertDialog createDataTimePick(final IDateChooseListener listener) {
        final DatePicker datePicker = new DatePicker(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("选择日期");
        builder.setView(datePicker);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int year = datePicker.getYear();
                int month = datePicker.getMonth()  +1 ;
                int day = datePicker.getDayOfMonth();
                String dateString = year + "-" + month + "-" + day ;
                if(listener != null){
                    listener.onDateChoose(dateString,year,month,day);
                }
            }
        });
        builder.setNegativeButton("取消", null);
        return builder.create();
    }

    static interface IDateChooseListener{
        void onDateChoose(String date, int year,int month,int day);
    }


    public void save() {
        // loan
        loan.setCust_name_tmp(apply_name.getText().toString());
        loan.setCust_iden(id_no.getText().toString());
        loan.setCust_mobile(phone_num.getText().toString());
        loan.setHome_visit_date(home_viste_date.getText().toString());
        loan.setCar_type(car_typeEditText.getText().toString());
        double price = 0;
        try {
            price = Double.parseDouble(transaction_price.getText().toString());
        } catch (Exception ex) {
            Toast.makeText(this, "成交价输入不正确", Toast.LENGTH_LONG).show();
            return;
        }
        loan.setDeal_price(price);
        int creditYears = 0;
        try {
            creditYears = Integer.parseInt(loan_time.getText().toString());
        } catch (Exception ex) {
            Toast.makeText(this, "贷款年限输入不正确！", Toast.LENGTH_LONG).show();
            return;
        }
        loan.setCredit_years(creditYears);
        loan.setDate_ywy(build_date.getText().toString());
        loan.setCase_type_id(0);

        if (isModify) {
            model.update(loan).subscribe(new Subscriber<FuncResponseBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(InfomationActivity.this, "保存失败", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNext(FuncResponseBean serverBean) {
                    if ("YES".equals(serverBean.getSuccess())) {
                        Toast.makeText(InfomationActivity.this, "保存成功", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            });
        } else {
            model.insert(loan).subscribe(new Subscriber<FuncResponseBean>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(InfomationActivity.this, "保存失败", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNext(FuncResponseBean serverBean) {
                    if ("YES".equals(serverBean.getSuccess())) {
                        Toast.makeText(InfomationActivity.this, "保存成功", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            });
        }
    }

    final  String[] role_state = new String[]{"建档中", "信用体系待查", "保险待查", "待传照片", "待派单", "待调查", "待风控", "待审批",
            "待请款", "待税费汇总", "待电审", "待税费确认", "待批复", "待放款", "待住行审核", "待住行送审", "待调额", "待刷卡", "已刷卡"};


    void createRoldDialog(){
        if(roleDialog == null) {
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    business_state.setText(role_state[which]);
                    loan.setCase_state_id(which);
                }
            };
            roleDialog = createAlertDialog(role_state, listener);
        }
        roleDialog.show();
    }
    String initCaseState(int id) {
        String[] args = new String[]{"建档中", "信用体系待查", "保险待查", "待传照片", "待派单", "待调查", "待风控", "待审批",
                "待请款", "待税费汇总", "待电审", "待税费确认", "待批复", "待放款", "待住行审核", "待住行送审", "待调额", "待刷卡", "已刷卡"};
        if (id >= 0 && id < 19) {
            return args[id];
        }
        return "";
    }


}
