package com.soarsky.car.ui.danger.compen;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.customview.PointMessageDialog;
import com.soarsky.car.customview.ShowImgDialog;
import com.soarsky.car.uitl.TimeUtils;
import com.soarsky.car.uitl.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class CompensateActivity extends BaseActivity<CompensatePresent, CompensateModel> implements CompensateView, View.OnClickListener {

    private LinearLayout tab1, tab2;

    private Button tab1Btn, tab2Btn;

    private TextView tabtv1, tabtv2;

    private LinearLayout backLay;

    private TextView titleTv;

    /**
     * 事故时间
     */
    private TextView compenStateTimeTv;
    /**
     * 事故位置
     */
    private EditText compenStatePositionEt;
    /**
     * 车牌号码
     */
    private TextView compenStateCarNumTv;
    /**
     * 驾驶证号
     */
    private TextView compenStateDriverTv;
    /**
     * 对方车牌号码
     */
    private EditText compenStateOtherCarNumEt;
    /**
     * 对方身份证号码
     */
    private EditText compenStateOtherDriverEt;
    /**
     * 己方的责任
     */
    private RadioGroup other_radio_group;
    /**
     * 己方的责任-全责
     */
    private RadioButton other_radio_button1;
    /**
     * 己方的责任-同责
     */
    private RadioButton other_radio_button2;
    /**
     * 己方的责任-无责
     */
    private RadioButton other_radio_button3;
    /**
     * 对方的责任
     */
    private RadioGroup pay_radio_group;
    /**
     * 对方的责任-全责
     */
    private RadioButton pay_radio_button1;
    /**
     * 对方的责任-同责
     */
    private RadioButton pay_radio_button2;
    /**
     * 对方的责任-无责
     */
    private RadioButton pay_radio_button3;
    /**
     * 己方的责任
     */
    private String faffirm = "1";
    /**
     * 对方的责任
     */
    private String saffirm = "3";

    private App app;

    private final static String TAG = "CompensateActivity";

    private   String localTempImgFileName ;

    private final static String localTempImgDir = "CarApp";


    private  int id;


    /**
     * 现场远景
     */
    private GridView distantGridView;
    private List<CompensateImage> distantList = new ArrayList<>();
    private List<String> distantUrlList=new ArrayList<>();
    private GridAdapter distantgridAdapter;
    private static final int DISTANT = 101;
    /**
     * 现场近景
     */
    private GridView colseShortGridView;
    private List<CompensateImage> colseShortList = new ArrayList<>();
    private List<String> colseShortUrlList=new ArrayList<>();
    private GridAdapter colseShortgridAdapter;
    private static final int COLSESHORT = 102;
    /**
     * 事故损失全景
     */
    private GridView loseOverallViewGridView;
    private List<CompensateImage> loseOverallViewList = new ArrayList<>();
    private List<String> loseOverallViewUrlList=new ArrayList<>();
    private GridAdapter loseOverallViewgridAdapter;
    private static final int LOSEOVERALL = 103;
    /**
     * 受损部件特写
     */
    private GridView assemblyUnitGridView;
    private List<CompensateImage> assemblyUnitList = new ArrayList<>();
    private List<String> assemblyUnitUrlList=new ArrayList<>();
    private GridAdapter assemblyUnitgridAdapter;
    private static final int ASSEMBLYUNIT = 104;
    /**
     * 事故车架
     */
    private GridView vehicleIdentificationNumberGridView;
    private List<CompensateImage> vehicleIdentificationNumberList = new ArrayList<>();
    private List<String> vehicleIdentificationNumberUrlList=new ArrayList<>();
    private GridAdapter vehicleIdentificationNumberAdapter;
    private static final int VEHICLEIDENTICATIONNUMBER = 105;
    /**
     * 双方驾照
     */
    private GridView driverListenerGridView;
    private List<CompensateImage> driverListenerList = new ArrayList<>();
    private List<String> driverListenerUrlList=new ArrayList<>();
    private GridAdapter driverListenergridAdapter;
    private static final int DRIVERLISTENER = 106;
    /**
     * 人车合影
     */
    private GridView groupPhotoGridView;
    private List<CompensateImage> groupPhotoList = new ArrayList<>();
    private List<String> groupPhotoUrlList=new ArrayList<>();
    private GridAdapter groupPhotogridAdapter;
    private static final int GROUPPHOTO = 107;



    private ShowImgDialog showImgDialog;

    private PointMessageDialog pointMessageDialog;



    @Override
    public int getLayoutId() {
        return R.layout.activity_dangerbasemessageup;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG, this);

        tab1 = (LinearLayout) findViewById(R.id.tab1);
        tab2 = (LinearLayout) findViewById(R.id.tab2);
        tabtv1 = (TextView) findViewById(R.id.compenstate_tbtv1);
        tabtv2 = (TextView) findViewById(R.id.compenstate_tbtv2);
        titleTv = (TextView) findViewById(R.id.topicTv);
        titleTv.setText(getString(R.string.quick_deal));
        showImgDialog=new ShowImgDialog(this);
        /**
         * 现场远景
         */
        distantGridView = (GridView) findViewById(R.id.gridView1);
        distantgridAdapter = new GridAdapter(this);
        distantList = mPresenter.initData(distantList);
        distantgridAdapter.setData(distantList);
        distantgridAdapter.setImagecancleCallback(new imageClick(distantList, distantgridAdapter,DISTANT));
        distantGridView.setAdapter(distantgridAdapter);


        /**
         * 现场近景
         */
        colseShortGridView = (GridView) findViewById(R.id.gridView2);
        colseShortgridAdapter = new GridAdapter(this);
        colseShortList = mPresenter.initData(colseShortList);
        colseShortgridAdapter.setData(colseShortList);
        colseShortgridAdapter.setImagecancleCallback(new imageClick(colseShortList, colseShortgridAdapter,COLSESHORT));
        colseShortGridView.setAdapter(colseShortgridAdapter);


        /**
         * 事故损失全景
         */
        loseOverallViewGridView = (GridView) findViewById(R.id.gridView3);
        loseOverallViewgridAdapter = new GridAdapter(this);
        loseOverallViewList = mPresenter.initData(loseOverallViewList);
        loseOverallViewgridAdapter.setData(loseOverallViewList);
        loseOverallViewgridAdapter.setImagecancleCallback(new imageClick(loseOverallViewList, loseOverallViewgridAdapter,LOSEOVERALL));
        loseOverallViewGridView.setAdapter(loseOverallViewgridAdapter);


        /**
         * 受损部件特写
         */
        assemblyUnitGridView = (GridView) findViewById(R.id.gridView4);
        assemblyUnitgridAdapter = new GridAdapter(this);
        assemblyUnitList = mPresenter.initData(assemblyUnitList);
        assemblyUnitgridAdapter.setData(assemblyUnitList);
        assemblyUnitgridAdapter.setImagecancleCallback(new imageClick(assemblyUnitList, assemblyUnitgridAdapter,ASSEMBLYUNIT));
        assemblyUnitGridView.setAdapter(assemblyUnitgridAdapter);


        /**
         * 事故车架
         */
        vehicleIdentificationNumberGridView = (GridView) findViewById(R.id.gridView5);
        vehicleIdentificationNumberAdapter = new GridAdapter(this);
        vehicleIdentificationNumberList = mPresenter.initData(vehicleIdentificationNumberList);
        vehicleIdentificationNumberAdapter.setData(vehicleIdentificationNumberList);
        vehicleIdentificationNumberAdapter.setImagecancleCallback(new imageClick(vehicleIdentificationNumberList, vehicleIdentificationNumberAdapter,VEHICLEIDENTICATIONNUMBER));
        vehicleIdentificationNumberGridView.setAdapter(vehicleIdentificationNumberAdapter);


        /**
         * 双方驾照
         */

        driverListenerGridView = (GridView) findViewById(R.id.gridView6);
        driverListenergridAdapter = new GridAdapter(this);
        driverListenerList = mPresenter.initData(driverListenerList);
        driverListenergridAdapter.setData(driverListenerList);
        driverListenergridAdapter.setImagecancleCallback(new imageClick(driverListenerList, driverListenergridAdapter,DRIVERLISTENER));
        driverListenerGridView.setAdapter(driverListenergridAdapter);


        /**
         * 人车合影
         */

        groupPhotoGridView = (GridView) findViewById(R.id.gridView7);
        groupPhotogridAdapter = new GridAdapter(this);
        groupPhotoList = mPresenter.initData(groupPhotoList);
        groupPhotogridAdapter.setData(groupPhotoList);
        groupPhotogridAdapter.setImagecancleCallback(new imageClick(groupPhotoList, groupPhotogridAdapter,GROUPPHOTO));
        groupPhotoGridView.setAdapter(groupPhotogridAdapter);



        findViewById(R.id.compenstate_tbn1).setOnClickListener(CompensateActivity.this);
        findViewById(R.id.backView).setOnClickListener(this);

        compenStateTimeTv = (TextView) findViewById(R.id.compenStateTimeTv);

        compenStateTimeTv.setText(TimeUtils.milliseconds2String(new Date().getTime()));

        compenStatePositionEt = (EditText) findViewById(R.id.compenStatePositionEt);
        compenStateCarNumTv = (TextView) findViewById(R.id.compenStateCarNumTv);
        compenStateCarNumTv.setText(app.getCommonParam().getCarNum() == null ? "" : app.getCommonParam().getCarNum());

        compenStateDriverTv = (TextView) findViewById(R.id.compenStateDriverTv);

        compenStateDriverTv.setText(app.getCommonParam().getDrivingLicence() == null ? "" : app.getCommonParam().getDrivingLicence());

        compenStateOtherCarNumEt = (EditText) findViewById(R.id.compenStateOtherCarNumEt);
        compenStateOtherDriverEt = (EditText) findViewById(R.id.compenStateOtherDriverEt);


        other_radio_group = (RadioGroup) findViewById(R.id.other_radio_group);
        other_radio_button1 = (RadioButton) findViewById(R.id.other_radio_button1);
        other_radio_button2 = (RadioButton) findViewById(R.id.other_radio_button2);
        other_radio_button3 = (RadioButton) findViewById(R.id.other_radio_button3);
        other_radio_button1.setChecked(true);

        pay_radio_group = (RadioGroup) findViewById(R.id.pay_radio_group);
        pay_radio_button1 = (RadioButton) findViewById(R.id.pay_radio_button1);
        pay_radio_button2 = (RadioButton) findViewById(R.id.pay_radio_button2);
        pay_radio_button3 = (RadioButton) findViewById(R.id.pay_radio_button3);

        pay_radio_button3.setChecked(true);


        findViewById(R.id.compenstate_imgup).setOnClickListener(this);

        other_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == other_radio_button1.getId()) {
                    faffirm = "1";

                } else if (i == other_radio_button2.getId()) {
                    faffirm = "2";
                } else if (i == other_radio_button3.getId()) {
                    faffirm = "3";
                }
            }
        });


        pay_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if (i == pay_radio_button1.getId()) {
                    saffirm = "1";
                } else if (i == pay_radio_button2.getId()) {
                    saffirm = "2";
                } else if (i == pay_radio_button3.getId()) {
                    saffirm = "3";
                }
            }
        });

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        findViewById(R.id.img_1_2).setOnClickListener(this);
        findViewById(R.id.img_2_2).setOnClickListener(this);
        findViewById(R.id.img_3_2).setOnClickListener(this);
        findViewById(R.id.img_4_2).setOnClickListener(this);
        findViewById(R.id.img_5_2).setOnClickListener(this);
        findViewById(R.id.img_6_2).setOnClickListener(this);
        findViewById(R.id.img_7_2).setOnClickListener(this);




    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 上传快赔信息成功
     *
     * @param param
     */
    @Override
    public void uploadFastDamagerSuccess(CompenstateParam param) {

        if (param != null) {
            if ("0".equals(param.getStatus())) {
                tab1.setVisibility(View.GONE);
                tab2.setVisibility(View.VISIBLE);
                tabtv1.setVisibility(View.INVISIBLE);
                tabtv2.setVisibility(View.VISIBLE);
                id=param.getData().getId();
            } else {
                ToastUtil.show(this, param.getMessage());
            }
        }

    }

    /**
     * 上传快赔信息失败
     */
    @Override
    public void uploadFastDamagerFail() {

        ToastUtil.show(this, R.string.throwable);
    }

    @Override
    public boolean verifyData() {
        boolean flag = false;
        if (TextUtils.isEmpty(compenStateTimeTv.getText().toString())) {
            ToastUtil.show(this, R.string.compenstatetimeempty);
            flag = false;
        } else {
            flag = true;
        }

        if (TextUtils.isEmpty(compenStatePositionEt.getText().toString())) {
            ToastUtil.show(this, R.string.compenstatepositionempty);
            flag = false;
        } else {
            flag = true;
        }

        if (TextUtils.isEmpty(compenStateCarNumTv.getText().toString())) {
            ToastUtil.show(this, R.string.compenstatefcarnumempty);
            flag = false;
        } else {
            flag = true;
        }

        if (TextUtils.isEmpty(compenStateDriverTv.getText().toString())) {
            ToastUtil.show(this, R.string.compenstatediverempty);
            flag = false;
        } else {
            flag = true;
        }

        if (TextUtils.isEmpty(compenStateOtherCarNumEt.getText().toString())) {
            ToastUtil.show(this, R.string.compenstatepaycarnumempty);
            flag = false;
        } else {
            flag = true;
        }

        if (TextUtils.isEmpty(compenStateOtherDriverEt.getText().toString())) {
            ToastUtil.show(this, R.string.compenstatepaydiverempty);
            flag = false;
        } else {
            flag = true;
        }

        return flag;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.backLay:
                finish();
                break;
            case R.id.compenstate_tbn1:


//                tab1.setVisibility(View.GONE);
//                tab2.setVisibility(View.VISIBLE);
//                tabtv1.setVisibility(View.INVISIBLE);
//                tabtv2.setVisibility(View.VISIBLE);
////


                if(mPresenter.verifyData()) {
                    CompenstateSendParam p = new CompenstateSendParam();
                    p.setStime(compenStateTimeTv.getText().toString());
                    p.setLocation(compenStatePositionEt.getText().toString());
                    p.setFcarnum(compenStateCarNumTv.getText().toString());
                    p.setFidNo(compenStateDriverTv.getText().toString());
                    p.setFaffirm(faffirm);
                    p.setScarnum(compenStateOtherCarNumEt.getText().toString());
                    p.setSidNo(compenStateOtherDriverEt.getText().toString());
                    p.setSaffirm(saffirm);
                    mPresenter.uploadFastDamager(p);
                }

                break;


            case R.id.compenstate_imgup:
                if(!mPresenter.validateImageComplete(distantUrlList,colseShortUrlList,loseOverallViewUrlList,assemblyUnitUrlList,
                        vehicleIdentificationNumberUrlList,driverListenerUrlList,groupPhotoUrlList)){


                       pointMessageDialog=new PointMessageDialog(mContext,getResources().getString(R.string.take_photo_complete),"","");
                       pointMessageDialog.setOnBtnClickLisenter(new PointMessageDialog.OnBtnClickLisenter() {
                           @Override
                           public void onClick() {
                               pointMessageDialog.dismiss();
                               pointMessageDialog=null;
                           }
                       });
                    pointMessageDialog.show();

                }else{
                    mPresenter.upLoadParam(distantUrlList,colseShortUrlList,loseOverallViewUrlList,assemblyUnitUrlList,
                            vehicleIdentificationNumberUrlList,driverListenerUrlList,groupPhotoUrlList,id);
                }


                break;

            case R.id.img_1_2:
                mPresenter.showImg(0);
                break;
            case R.id.img_2_2:
                mPresenter.showImg(1);
                break;
            case R.id.img_3_2:
                mPresenter.showImg(2);
                break;
            case R.id.img_4_2:
                mPresenter.showImg(3);
                break;
            case R.id.img_5_2:
                mPresenter.showImg(4);
                break;
            case R.id.img_6_2:
                mPresenter.showImg(5);
                break;
            case R.id.img_7_2:
                mPresenter.showImg(6);
                break;


        }
    }


    public class imageClick implements ImagecancleCallback {

        private List<CompensateImage> data;
        private GridAdapter gridAdapter;
        private int requestCode;

        public imageClick(List<CompensateImage> list, GridAdapter gridAdapter,int requestCode) {
            this.data = list;
            this.gridAdapter = gridAdapter;
            this.requestCode=requestCode;
        }

        @Override
        public void delete(int position) {

            if (data.size() == 3) {
                boolean isInit = true;
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getShowDelete() == 0) {
                        isInit = false;
                    }
                }

                if (isInit) {
                    mPresenter.initData(data);
                }

            }


            data.remove(position);
            gridAdapter.setData(data);

        }

        @Override
        public void add() {
            if (data.size() == 3) {
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getShowDelete() == 0) {
                        data.remove(i);
                    }
                }

            }

            addPhoto(requestCode);

        }


    }


    @Override
    public void saveNetUri(List<String> list, String url) {
        list.add(url);
    }

    @Override
    public void uploadFastImgSucess() {

            pointMessageDialog=new PointMessageDialog(mContext,getResources().getString(R.string.message_upload),getResources().getString(R.string.drive_leaf),getResources().getString(R.string.avoid));
            pointMessageDialog.setOnBtnClickLisenter(new PointMessageDialog.OnBtnClickLisenter() {
                @Override
                public void onClick() {

                    pointMessageDialog.dismiss();
                    finish();
                    pointMessageDialog=null;
                }
            });
        pointMessageDialog.show();
    }

    @Override
    public void uploadFastImgf() {
        ToastUtil.show(this,"图片上传失败");
        finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            String fileStr=Environment.getExternalStorageDirectory()
                    + "/" + localTempImgDir + "/" + localTempImgFileName;

            File f = new File(fileStr);
            try {
                Uri u =
                        Uri.parse(android.provider.MediaStore.Images.Media.insertImage(getContentResolver(),
                                f.getAbsolutePath(), null, null));
                //u就是拍摄获得的原始图片的uri，剩下的你想干神马坏事请便……
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            CompensateImage compensateImage=new CompensateImage();
            compensateImage.setShowDelete(1);
            compensateImage.setImgUrl(fileStr);

            switch (requestCode) {
                case DISTANT:
                    mPresenter.uploadImg(distantUrlList,fileStr);
                    distantList.add(compensateImage);
                    distantgridAdapter.setData(distantList);

                    break;
                case COLSESHORT:
                    mPresenter.uploadImg(colseShortUrlList,fileStr);
                    colseShortList.add(compensateImage);
                    colseShortgridAdapter.setData(colseShortList);
                    break;
                case LOSEOVERALL:
                    mPresenter.uploadImg(loseOverallViewUrlList,fileStr);
                    loseOverallViewList.add(compensateImage);
                    loseOverallViewgridAdapter.setData(loseOverallViewList);
                    break;
                case ASSEMBLYUNIT:
                    mPresenter.uploadImg(assemblyUnitUrlList,fileStr);
                   assemblyUnitList.add(compensateImage);
                    assemblyUnitgridAdapter.setData(assemblyUnitList);
                    break;
                case VEHICLEIDENTICATIONNUMBER:
                    mPresenter.uploadImg(vehicleIdentificationNumberUrlList,fileStr);
                    vehicleIdentificationNumberList.add(compensateImage);
                    vehicleIdentificationNumberAdapter.setData(vehicleIdentificationNumberList);
                    break;
                case DRIVERLISTENER:
                    mPresenter.uploadImg(driverListenerUrlList,fileStr);
                    driverListenerList.add(compensateImage);
                    driverListenergridAdapter.setData(driverListenerList);

                    break;
                case GROUPPHOTO:
                    mPresenter.uploadImg(groupPhotoUrlList,fileStr);
                    groupPhotoList.add(compensateImage);
                    groupPhotogridAdapter.setData(groupPhotoList);
                    break;

            }
        }

        super.onActivityResult(requestCode, resultCode, data);


    }


    public void addPhoto(int requestCode) {
        String status = Environment.getExternalStorageState();
        if (status.equals(Environment.MEDIA_MOUNTED)) {
            try {

                File dir = new File(Environment.getExternalStorageDirectory() + "/" + localTempImgDir);
                if (!dir.exists()) dir.mkdirs();

                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                localTempImgFileName=new Date().getTime()+".jpg";
                File f = new File(dir, localTempImgFileName);//localTempImgDir和localTempImageFileName是自己定义的名字
                Uri u = Uri.fromFile(f);
                intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, u);
                startActivityForResult(intent, requestCode);
            } catch (ActivityNotFoundException e) {
                // TODO Auto-generated catch block
                Toast.makeText(CompensateActivity.this, "没有找到储存目录", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(CompensateActivity.this, "没有储存卡", Toast.LENGTH_LONG).show();
        }
    }






}



