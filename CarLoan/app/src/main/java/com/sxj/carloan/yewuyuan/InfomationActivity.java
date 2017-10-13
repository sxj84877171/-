package com.sxj.carloan.yewuyuan;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sxj.carloan.ApplicationInfoManager;
import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.FuncResponseBean;
import com.sxj.carloan.bean.ProductBean;
import com.sxj.carloan.bean.ResultListBean;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.product.ProductFactroy;
import com.sxj.carloan.ui.HomeInfoAcitivity;
import com.sxj.carloan.ui.LoanSubscriber;
import com.sxj.carloan.ui.PersonCreditActivity;
import com.sxj.carloan.ui.RepaymentActivity;
import com.sxj.carloan.util.BeanToMap;
import com.sxj.carloan.util.FileUtil;
import com.sxj.carloan.util.LogUtil;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
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
    private View home_viste_date_line;
    private EditText id_no;
    private TextView marry_state;
    private EditText phone_num;
    private TextView is_compension;
    private TextView house_type;
    private TextView home_viste_date;
    private EditText house_address;
    private EditText car_typeEditText;
    private EditText transaction_price;
    private TextView loan_time;
    private EditText car_name;
    private EditText car_address;
    private EditText fapiao_jine;
    private EditText daikuan_jine;
    private EditText yinhangshenbao_jine;

    private Button modify;
    private Button save;
    private boolean isModify = false;


    private static String[] PRODUCT_TYPES = new String[]{};
    private List<ProductBean> productBeanList;


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
        isModify = true;
        if (loan == null) {
            isModify = false;
            loan = new ServerBean.RowsBean();
            ApplicationInfoManager.getInstance().setInfo(loan);
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
        if (!stateBoolean) {
            business_type_line.setOnClickListener(null);
            sex_line.setOnClickListener(null);
            marry_state_line.setOnClickListener(null);
            is_compension_line.setOnClickListener(null);
            house_type_line.setOnClickListener(null);
            home_viste_date_line.setOnClickListener(null);
        } else {
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

            home_viste_date_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createVisitDatePickDialog();
                }
            });

        }
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
//        menu.add(1, 1, 1, "家庭情况");
//        menu.add(1, 2, 2, "个人信用");
//        menu.add(1, 3, 3, "偿还能力");
//        menu.add(1, 4, 4, "审核意见");
//        menu.add(1, 5, 5, "预览报告");
//        menu.add(1, 6, 6, "下载报告");

        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(android.view.Menu menu) {
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
        home_viste_date_line = getViewById(R.id.home_viste_date_line);

        car_name = getViewById(R.id.car_name);
        car_address = getViewById(R.id.car_address);
        fapiao_jine = getViewById(R.id.fapiao_jine);
        yinhangshenbao_jine = getViewById(R.id.yinhangshenbao_jine);
        daikuan_jine = getViewById(R.id.daikuan_jine);

        car_typeEditText = getViewById(R.id.car_type);
        transaction_price = getViewById(R.id.transaction_price);
        loan_time = getViewById(R.id.loan_time);
        apply_name = getViewById(R.id.apply_name);
        modify = getViewById(R.id.add);
        save = getViewById(R.id.save);
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
    private AlertDialog roleDialog;
    private AlertDialog visitDatePickDialog;
    private AlertDialog buildDatePickDialog;

    void createBuildDatePickDialog() {
        if (buildDatePickDialog == null) {
            IDateChooseListener listener = new IDateChooseListener() {
                @Override
                public void onDateChoose(String date, int year, int month, int day) {
//                    build_date.setText(date);
                    loan.setDate_ywy(date);
                }
            };
            buildDatePickDialog = createDataTimePick(listener);
        }
        buildDatePickDialog.show();
    }

    void createVisitDatePickDialog() {
        if (visitDatePickDialog == null) {
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
                    loan.setInstallment_type_id_1(which + 1);
//                    initStageType(which);
                    stagetTypeDialog.dismiss();
                }
            };
            stagetTypeDialog = createAlertDialog(args, listener);
        }
        stagetTypeDialog.show();
    }

    void initChoose() {
//        initStageType(loan.getInstallment_type_id() - 1);
        initHouseType(loan.getRoom_type_id() - 1);
        initCompension(loan.getIf_gcr_id());
        initMarrayState(loan.getCust_marriage_id());
        sex.setText(loan.getCust_sex());
        initType(loan.getCase_type_id() - 1);
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

            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    initType(which);
                    loan.setProduct_id("" + productBeanList.get(which).getId());
                    loan.setCase_type_id(productBeanList.get(which).getCase_type_id());
                    typeDialog.dismiss();
                    loan_time.setText("" + productBeanList.get(which).getCredit_years());
                }
            };

            typeDialog = createAlertDialog(PRODUCT_TYPES, listener);
        }
        typeDialog.show();
    }

    private void initType(int which) {
        if (which < PRODUCT_TYPES.length && which >= 0 && productBeanList != null) {
            business_type.setText(productBeanList.get(which).getProduct_name());
            loan.setProduct_id("" + productBeanList.get(which).getId());
        }
    }


    void initData() {
        if (ProductFactroy.getInstance().getProductBeanList() != null) {
            PRODUCT_TYPES = new String[ProductFactroy.getInstance().getProductBeanList().size()];
            productBeanList = ProductFactroy.getInstance().getProductBeanList();
            for (int i = 0; i < productBeanList.size(); i++) {
                PRODUCT_TYPES[i] = productBeanList.get(i).getProduct_name();
            }
        }
        if (loan != null) {
            int productId = 1;
            ProductBean productBean;
            try {
                productId = Integer.parseInt(loan.getProduct_id());
                productBean = findProduct(productId);
            } catch (Exception ex) {
                loan.setProduct_id("1");
                loan.setCase_type_id(1);
                productBean = null;
            }
            loan_time.setText(loan.getCredit_years() + "");
            if (productBean != null) {
                business_type.setText(productBean.getProduct_name());
                loan_time.setText("" + productBean.getCredit_years());
            }
            apply_name.setText(getLoanString(loan.getCust_name_tmp()));
            sex.setText(getLoanString(loan.getCust_sex()));
            id_no.setText(getLoanString(loan.getCust_iden()));
            phone_num.setText(getLoanString(loan.getCust_mobile()));
            home_viste_date.setText(loan.getHome_visit_date());
            car_typeEditText.setText(loan.getCar_type());
            house_address.setText(loan.getCust_address());
            transaction_price.setText(loan.getDeal_price() + "");
            car_name.setText(loan.getChehang_name());
            car_address.setText(loan.getChehang_address());
            fapiao_jine.setText(loan.getInvoice_price());
            yinhangshenbao_jine.setText(loan.getLoan_amount_high());
            daikuan_jine.setText(loan.getLoan_amount_ywy());
        }
    }


    ProductBean findProduct(int id) {
        if (productBeanList != null) {
            for (ProductBean productBean : productBeanList) {
                if (productBean.getId() == id) {
                    return productBean;
                }
            }
        }
        return null;
    }


    public void save() {

        loan.setUser_id_ywy(Integer.parseInt(getLoginInfo().getUser_id()));

        if (TextUtils.isEmpty(business_type.getText().toString())) {
            toast("请选择产品");
            return;
        }

        // loan
        if (TextUtils.isEmpty(apply_name.getText().toString())) {
            toast("请填写申请人名字！");
            apply_name.findFocus();
            return;
        }
        loan.setCust_name_tmp(apply_name.getText().toString());
        if (TextUtils.isEmpty(id_no.getText().toString())) {
            toast("请填写申请人身份证号码！");
            id_no.findFocus();
            return;
        }
        loan.setCust_iden(id_no.getText().toString());

        if (TextUtils.isEmpty(marry_state.getText())) {
            toast("请选择婚姻状况");
            return;
        }

        if (TextUtils.isEmpty(phone_num.getText().toString())) {
            toast("请填写申请人手机号码！");
            phone_num.findFocus();
            return;
        }
        loan.setCust_mobile(phone_num.getText().toString());

        if (TextUtils.isEmpty(is_compension.getText().toString())) {
            toast("请选择是否共偿");
            return;
        }

        if (TextUtils.isEmpty(home_viste_date.getText().toString())) {
            toast("请选择家访日期！");
            return;
        }
        loan.setHome_visit_date(home_viste_date.getText().toString());

        if (TextUtils.isEmpty(house_address.getText().toString())) {
            toast("ssssss");
            return;
        }
        loan.setCust_address(house_address.getText().toString());

        if (TextUtils.isEmpty(car_typeEditText.getText().toString())) {
            toast("请填写车型！");
            car_typeEditText.findFocus();
            return;
        }
        loan.setCar_type(car_typeEditText.getText().toString());

        if (TextUtils.isEmpty(car_address.getText().toString())) {
            toast("请填写车行地址！");
            car_address.findFocus();
            return;
        }
        loan.setChehang_address(car_address.getText().toString());

        if (TextUtils.isEmpty(house_type.getText().toString())) {
            toast("请选择房产类别");
            return;
        }

        if (TextUtils.isEmpty(car_name.getText().toString())) {
            toast("请填写车行名称！");
            car_name.findFocus();
            return;
        }
        loan.setChehang_name(car_name.getText().toString());

        if (TextUtils.isEmpty(car_address.getText().toString())) {
            toast("请填写车行地址");
            return;
        }
        loan.setChehang_address(car_address.getText().toString());

        if (TextUtils.isEmpty(car_typeEditText.getText())) {
            toast("请填写车型");
            return;
        }

        double price = 0;
        try {
            price = Double.parseDouble(transaction_price.getText().toString());
        } catch (Exception ex) {
            Toast.makeText(this, "成交价输入不正确", Toast.LENGTH_LONG).show();
            transaction_price.findFocus();
            return;
        }
        loan.setDeal_price(price);
        try {
            price = Double.parseDouble(fapiao_jine.getText().toString());
        } catch (Exception ex) {
            Toast.makeText(this, "发票金额输入不正确", Toast.LENGTH_LONG).show();
            fapiao_jine.findFocus();
            return;
        }
        loan.setInvoice_price("" + price);

        try {
            price = Double.parseDouble(daikuan_jine.getText().toString());
        } catch (Exception ex) {
            Toast.makeText(this, "贷款金额输入不正确", Toast.LENGTH_LONG).show();
            daikuan_jine.findFocus();
            return;
        }
        loan.setLoan_amount_ywy("" + price);
        loan.setLoan_amount("" + price);
        //loan_amount_ywy

//        try {
//            price = Double.parseDouble(yinhangshenbao_jine.getText().toString());
//        } catch (Exception ex) {
//            Toast.makeText(this, "银行申报金额输入不正确", Toast.LENGTH_LONG).show();
//            yinhangshenbao_jine.findFocus();
//            return;
//        }
//        loan.setLoan_amount_high("" + price);

        int creditYears = 0;
        try {
            creditYears = Integer.parseInt(loan_time.getText().toString());
        } catch (Exception ex) {
            Toast.makeText(this, "贷款年限输入不正确！", Toast.LENGTH_LONG).show();
            loan_time.findFocus();
            return;
        }

        loan.setCredit_years(creditYears);

        if (isModify) {
            model.update(BeanToMap.transRowsBean2Map(loan)).subscribe(new Subscriber<FuncResponseBean>() {
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
                    } else {
                        toast("保存失败");
                    }
                }
            });
        } else {
            Map map = BeanToMap.transRowsBean2Map(loan);
            map.remove("id");
            model.insertBean(map).subscribe(new Subscriber<FuncResponseBean>() {
                @Override
                public void onCompleted() {
                }

                @Override
                public void onError(Throwable e) {
                    toast("保存出错");
                }

                @Override
                public void onNext(FuncResponseBean serverBean) {
                    if ("YES".equals(serverBean.getSuccess())) {
                        toast("保存成功");
                        loan.setId(Integer.parseInt(serverBean.getMessage()));
                        ApplicationInfoManager.getInstance().setInfo(loan);
                        finish();
                    } else {
                        toast("保存失败");
                    }
                }
            });
        }
    }

    final String[] role_state = new String[]{"建档中", "信用体系待查", "保险待查", "待传照片", "待派单", "待调查", "待风控", "待审批",
            "待请款", "待税费汇总", "待电审", "待税费确认", "待批复", "待放款", "待住行审核", "待住行送审", "待调额", "待刷卡", "已刷卡"};


    void createRoldDialog() {
        if (roleDialog == null) {
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    business_state.setText(role_state[which]);
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
