package com.soarsky.car.ui.danger.compen;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.CompenstateParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.customview.MyRadioButton;
import com.soarsky.car.customview.PointMessageDialog;
import com.soarsky.car.customview.ShowImgDialog;
import com.soarsky.car.uitl.BitmapUtils;
import com.soarsky.car.uitl.TimeUtils;
import com.soarsky.car.uitl.ToastUtil;
import com.soarsky.car.uitl.ValidatorUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.soarsky.car.Constants.ALL_DUTY;
import static com.soarsky.car.Constants.NO_DUTY;
import static com.soarsky.car.Constants.SAME_DUTY;

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
 * 程序功能：出险报警信息上传界面
 * 该类为
 */


public class CompensateActivity extends BaseActivity<CompensatePresent, CompensateModel> implements CompensateView, View.OnClickListener {

    /**
     * tab栏
     */
    private LinearLayout tab1, tab2;
    /**
     * 两个tab栏对应的按钮
     */
    private Button tab1Btn, tab2Btn;

    /**
     * tab栏下面的标志线
     */
    private TextView tabtv1, tabtv2;
    /**
     * 返回按钮
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
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
    private EditText compenStateDriverTv;
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
    private MyRadioButton other_radio_button1;
    /**
     * 己方的责任-同责
     */
    private MyRadioButton other_radio_button2;
    /**
     * 己方的责任-无责
     */
    private MyRadioButton other_radio_button3;
    /**
     * 对方的责任
     */
    private RadioGroup pay_radio_group;
    /**
     * 对方的责任-全责
     */
    private MyRadioButton pay_radio_button1;
    /**
     * 对方的责任-同责
     */
    private MyRadioButton pay_radio_button2;
    /**
     * 对方的责任-无责
     */
    private MyRadioButton pay_radio_button3;
    /**
     * 己方的责任
     */
    private String faffirm = "0";
    /**
     * 对方的责任
     */
    private String saffirm = "2";

    private App app;

    private final static String TAG = "CompensateActivity";

    private   String localTempImgFileName ;

    private final static String localTempImgDir = "CarApp";


    /**
     * 选择我方责任时间
     */
    private long  payTime=0;
    /**
     * 选择对方责任时间
     */
    private long  otherTime=0;
    /**
     * 事件id
     */
    private  int id;

    /**
     * 现场远景
     */
    private GridView distantGridView;//图片展示控件
    private List<CompensateImage> distantList = new ArrayList<>();// 本地路径集合
    private List<String> distantUrlList=new ArrayList<>();//网络路径集合
    private GridAdapter distantgridAdapter;// 适配器
    private static final int DISTANT = 101;// 标志常量
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


    /**
     * 显示样本照片dialog
     */
    private ShowImgDialog showImgDialog;
    /**
     * 对话框的dialog
     */
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
        distantgridAdapter.setType(DISTANT);
        distantgridAdapter.setData(new ArrayList<CompensateImage>(distantList));
        distantgridAdapter.setImagecancleCallback(new imageClick(distantList, distantgridAdapter,DISTANT));
        distantGridView.setAdapter(distantgridAdapter);


        /**
         * 现场近景
         */
        colseShortGridView = (GridView) findViewById(R.id.gridView2);
        colseShortgridAdapter = new GridAdapter(this);
        colseShortgridAdapter.setType(COLSESHORT);
        colseShortgridAdapter.setData(new ArrayList<CompensateImage>(colseShortList));
        colseShortgridAdapter.setImagecancleCallback(new imageClick(colseShortList, colseShortgridAdapter,COLSESHORT));
        colseShortGridView.setAdapter(colseShortgridAdapter);


        /**
         * 事故损失全景
         */
        loseOverallViewGridView = (GridView) findViewById(R.id.gridView3);
        loseOverallViewgridAdapter = new GridAdapter(this);
        loseOverallViewgridAdapter.setType(LOSEOVERALL);
        loseOverallViewgridAdapter.setData(new ArrayList<CompensateImage>(loseOverallViewList));
        loseOverallViewgridAdapter.setImagecancleCallback(new imageClick(loseOverallViewList, loseOverallViewgridAdapter,LOSEOVERALL));
        loseOverallViewGridView.setAdapter(loseOverallViewgridAdapter);


        /**
         * 受损部件特写
         */
        assemblyUnitGridView = (GridView) findViewById(R.id.gridView4);
        assemblyUnitgridAdapter = new GridAdapter(this);
        assemblyUnitgridAdapter.setType(ASSEMBLYUNIT);
        assemblyUnitgridAdapter.setData(new ArrayList<CompensateImage>(assemblyUnitList));
        assemblyUnitgridAdapter.setImagecancleCallback(new imageClick(assemblyUnitList, assemblyUnitgridAdapter,ASSEMBLYUNIT));
        assemblyUnitGridView.setAdapter(assemblyUnitgridAdapter);


        /**
         * 事故车架
         */
        vehicleIdentificationNumberGridView = (GridView) findViewById(R.id.gridView5);
        vehicleIdentificationNumberAdapter = new GridAdapter(this);
        vehicleIdentificationNumberAdapter.setType(VEHICLEIDENTICATIONNUMBER);
        vehicleIdentificationNumberAdapter.setData(new ArrayList<CompensateImage>(vehicleIdentificationNumberList));
        vehicleIdentificationNumberAdapter.setImagecancleCallback(new imageClick(vehicleIdentificationNumberList, vehicleIdentificationNumberAdapter,VEHICLEIDENTICATIONNUMBER));
        vehicleIdentificationNumberGridView.setAdapter(vehicleIdentificationNumberAdapter);


        /**
         * 双方驾照
         */

        driverListenerGridView = (GridView) findViewById(R.id.gridView6);
        driverListenergridAdapter = new GridAdapter(this);
        driverListenergridAdapter.setType(DRIVERLISTENER);
        driverListenergridAdapter.setData(new ArrayList<CompensateImage>(driverListenerList));
        driverListenergridAdapter.setImagecancleCallback(new imageClick(driverListenerList, driverListenergridAdapter,DRIVERLISTENER));
        driverListenerGridView.setAdapter(driverListenergridAdapter);


        /**
         * 人车合影
         */

        groupPhotoGridView = (GridView) findViewById(R.id.gridView7);
        groupPhotogridAdapter = new GridAdapter(this);
        groupPhotogridAdapter.setType(GROUPPHOTO);
        groupPhotogridAdapter.setData(new ArrayList<CompensateImage>(groupPhotoList));
        groupPhotogridAdapter.setImagecancleCallback(new imageClick(groupPhotoList, groupPhotogridAdapter,GROUPPHOTO));
        groupPhotoGridView.setAdapter(groupPhotogridAdapter);



        findViewById(R.id.compenstate_tbn1).setOnClickListener(CompensateActivity.this);
        findViewById(R.id.backView).setOnClickListener(this);

        compenStateTimeTv = (TextView) findViewById(R.id.compenStateTimeTv);

        compenStateTimeTv.setText(TimeUtils.milliseconds2String(new Date().getTime()));

        compenStatePositionEt = (EditText) findViewById(R.id.compenStatePositionEt);
        compenStateCarNumTv = (TextView) findViewById(R.id.compenStateCarNumTv);
        compenStateCarNumTv.setText(app.getCommonParam().getCarNum() == null ? "" : app.getCommonParam().getCarNum());

        compenStateDriverTv = (EditText) findViewById(R.id.compenStateDriverTv);

        compenStateDriverTv.setText(app.getCommonParam().getDrivingLicence() == null ? "" : app.getCommonParam().getDrivingLicence());

        compenStateOtherCarNumEt = (EditText) findViewById(R.id.compenStateOtherCarNumEt);

        compenStateOtherDriverEt = (EditText) findViewById(R.id.compenStateOtherDriverEt);
        compenStateOtherCarNumEt.addTextChangedListener(new EditTextWatcher(compenStateOtherCarNumEt));
        compenStateOtherDriverEt.addTextChangedListener(new EditTextWatcher(compenStateOtherDriverEt));

        other_radio_group = (RadioGroup) findViewById(R.id.other_radio_group);
        other_radio_button1 = (MyRadioButton) findViewById(R.id.other_radio_button1);
        other_radio_button2 = (MyRadioButton) findViewById(R.id.other_radio_button2);
        other_radio_button3 = (MyRadioButton) findViewById(R.id.other_radio_button3);
        other_radio_button1.setChecked(true);

        pay_radio_group = (RadioGroup) findViewById(R.id.pay_radio_group);
        pay_radio_button1 = (MyRadioButton) findViewById(R.id.pay_radio_button1);
        pay_radio_button2 = (MyRadioButton) findViewById(R.id.pay_radio_button2);
        pay_radio_button3 = (MyRadioButton) findViewById(R.id.pay_radio_button3);






        findViewById(R.id.compenstate_imgup).setOnClickListener(this);


        other_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                otherTime=new Date().getTime();

                /**
                 * 判断两组radiogroup的点击时间  如果后点击 不执行后面代码 避免死循环
                 */
                if(otherTime>payTime&&payTime!=0){
                    otherTime=0;

                    return;
                }

                if (i == other_radio_button1.getId()) {
                    faffirm = Constants.ALL_DUTY;
                        pay_radio_group.check(pay_radio_button3.getId());
                        saffirm = NO_DUTY;
                } else if (i == other_radio_button2.getId()) {

                        faffirm = Constants.SAME_DUTY;
                        pay_radio_group.check(pay_radio_button2.getId());
                        saffirm = Constants.SAME_DUTY;
                } else if (i == other_radio_button3.getId()) {
                         faffirm = NO_DUTY;
                        pay_radio_group.check(pay_radio_button1.getId());
                        saffirm = Constants.ALL_DUTY;
                }
                otherTime=0;
            }
        });



        pay_radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                payTime=new Date().getTime();
                /**
                 * 判断两组radiogroup的点击时间  如果后点击 不执行后面代码 避免死循环
                 */
                if(payTime>otherTime&&otherTime!=0){
                    payTime=0;
                    return;
                }

                if (i == pay_radio_button1.getId()) {
                    saffirm = ALL_DUTY;
                    other_radio_group.check(other_radio_button3.getId());
                        faffirm = NO_DUTY;
                } else if (i == pay_radio_button2.getId()) {
                    saffirm = SAME_DUTY;
                    other_radio_group.check(other_radio_button2.getId());
                    faffirm = SAME_DUTY;
                } else if (i == pay_radio_button3.getId()) {
                    saffirm = NO_DUTY;
                    other_radio_group.check(other_radio_button1.getId());
                    faffirm = ALL_DUTY;
                }
                payTime=0;
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

//        tab1.setVisibility(View.GONE);
//                tab2.setVisibility(View.VISIBLE);

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
    public void uploadFastDamagerSuccess(ResponseDataBean<CompenstateParam> param) {

        if (param != null) {
            if (Constants.REQUEST_SUCCESS.equals(param.getStatus())) {
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


    /**
     * 检验数据的准确和完整
     * @return  TRUE  验证通过 false 验证不通过
     */
    public boolean verifyData() {
        boolean flag = false;
        if (TextUtils.isEmpty(compenStateTimeTv.getText().toString())) {
            ToastUtil.show(this, R.string.compenstatetimeempty);
            return false;
        }

        if (TextUtils.isEmpty(compenStatePositionEt.getText().toString())) {
            ToastUtil.show(this, R.string.compenstatepositionempty);
            return false;
        }

        if (TextUtils.isEmpty(compenStateCarNumTv.getText().toString())) {
            ToastUtil.show(this, R.string.compenstatefcarnumempty);
            return false;
        }else{
            if(!ValidatorUtils.validatorCarNo(compenStateCarNumTv.getText().toString())){
                ToastUtil.show(this, R.string.compenstatefcarnumerror);
                return false;
            }
        }

        if (TextUtils.isEmpty(compenStateDriverTv.getText().toString())) {
            ToastUtil.show(this, R.string.compenstatediverempty);
            return false;
        }else{
            if(!ValidatorUtils.validatorIdNo(compenStateDriverTv.getText().toString())){
                ToastUtil.show(this, R.string.compenstatedivererror);
                return false;
            }
        }


        if (TextUtils.isEmpty(compenStateOtherCarNumEt.getText().toString())) {
            ToastUtil.show(this, R.string.compenstatepaycarnumempty);
            return false;
        }else{
            if(!ValidatorUtils.validatorCarNo(compenStateOtherCarNumEt.getText().toString())){
                ToastUtil.show(this, R.string.compenstatepaycarnumerror);
                return false;
            }
        }

        if (TextUtils.isEmpty(compenStateOtherDriverEt.getText().toString())) {
            ToastUtil.show(this, R.string.compenstatepaydiverempty);
            return false;
        }else{
            if(!ValidatorUtils.validatorIdNo(compenStateOtherDriverEt.getText().toString())){
                ToastUtil.show(this, R.string.compenstatepaydivererror);
                return false;
            }
        }

        if(compenStateOtherDriverEt.getText().toString().equals(compenStateDriverTv.getText().toString())){

            ToastUtil.show(this,R.string.compenstatepaydiverequals);
            return false;
        }
        if(compenStateOtherCarNumEt.getText().toString().equals(compenStateCarNumTv.getText().toString())){

            ToastUtil.show(this,R.string.compenstatepaycarnumequals);
            return false;
        }

        return true;
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


                if(verifyData()) {
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
//                if(!mPresenter.validateImageComplete(distantUrlList,colseShortUrlList,loseOverallViewUrlList,assemblyUnitUrlList,
//                        vehicleIdentificationNumberUrlList,driverListenerUrlList,groupPhotoUrlList)){
                if(!mPresenter.validateImageComplete2(distantList,colseShortList,loseOverallViewList,assemblyUnitList,
                        vehicleIdentificationNumberList,driverListenerList,groupPhotoList)){

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
                    //保存本地路径集合
                    List<List<CompensateImage>>lists=new ArrayList<>();
                    lists.add(distantList);
                    lists.add(colseShortList);
                    lists.add(loseOverallViewList);
                    lists.add(assemblyUnitList);
                    lists.add(vehicleIdentificationNumberList);
                    lists.add(driverListenerList);
                    lists.add(groupPhotoList);
                    int size = distantList.size()+colseShortList.size()+loseOverallViewList.size()+assemblyUnitList.size()
                            +vehicleIdentificationNumberList.size()+driverListenerList.size()+groupPhotoList.size();
                    mPresenter.uploadImage(lists,id,size);

//                    mPresenter.upLoadParam(distantUrlList,colseShortUrlList,loseOverallViewUrlList,assemblyUnitUrlList,
//                            vehicleIdentificationNumberUrlList,driverListenerUrlList,groupPhotoUrlList,id);
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




    /**
     * 取消上传图片的回调接口
     */
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
        public void delete(int position,int type) {

            data.remove(position);
            gridAdapter.setData(new ArrayList<CompensateImage>(data));

            switch (type){

                case DISTANT:
                    if(distantUrlList.size()-1>=position){
                        distantUrlList.remove(position);
                    }
                    break;
                case COLSESHORT:

                    if(colseShortUrlList.size()-1>=position){
                        colseShortUrlList.remove(position);
                    }

                    break;
                case LOSEOVERALL:
                    if(loseOverallViewUrlList.size()-1>=position){
                        loseOverallViewUrlList.remove(position);
                    }
                    break;
                case ASSEMBLYUNIT:
                    if(assemblyUnitUrlList.size()-1>=position){
                        assemblyUnitUrlList.remove(position);
                    }
                    break;
                case VEHICLEIDENTICATIONNUMBER:
                    if(vehicleIdentificationNumberUrlList.size()-1>=position){
                        vehicleIdentificationNumberUrlList.remove(position);
                    }


                    break;
                case DRIVERLISTENER:
                    if(driverListenerUrlList.size()-1>=position){
                        driverListenerUrlList.remove(position);
                    }
                    break;
                case GROUPPHOTO:
                    if(groupPhotoUrlList.size()-1>=position){
                        groupPhotoUrlList.remove(position);
                    }
                    break;
            }


        }

        @Override
        public void add() {
            if(requestCode==DRIVERLISTENER){
                if (data.size() ==4) {
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getShowDelete() == 0) {
                            data.remove(i);
                        }
                    }

                }
            }else{
                if (data.size() == 3) {
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getShowDelete() == 0) {
                            data.remove(i);
                        }
                    }

                }
            }

            addPhoto(requestCode);

        }


    }


    @Override
    public void saveNetUri(int index, String url,boolean upStatus) {
//        stopProgess();
        switch (index){
            case DISTANT:
                if(upStatus){
                    distantUrlList.add(url);
                }else{
                    distantList.remove(driverListenerUrlList.size());
                    distantgridAdapter.setData(distantList);

                }
                break;
            case COLSESHORT:
                if(upStatus) {
                    colseShortUrlList.add(url);
                }else{
                    colseShortList.remove(distantUrlList.size());
                    driverListenergridAdapter.setData(driverListenerList);

                }

                break;
            case LOSEOVERALL:
                if(upStatus) {
                    loseOverallViewUrlList.add(url);
                }else{
                    loseOverallViewList.remove(loseOverallViewUrlList.size());
                    loseOverallViewgridAdapter.setData(loseOverallViewList);

                }
            break;
            case ASSEMBLYUNIT:
                if(upStatus) {
                    assemblyUnitUrlList.add(url);
                }else{
                    assemblyUnitList.remove(assemblyUnitUrlList.size());
                    assemblyUnitgridAdapter.setData(assemblyUnitList);

                }
            break;
            case VEHICLEIDENTICATIONNUMBER:
                if(upStatus) {
                    vehicleIdentificationNumberUrlList.add(url);
                }else{
                    vehicleIdentificationNumberList.remove(vehicleIdentificationNumberUrlList.size());
                    vehicleIdentificationNumberAdapter.setData(vehicleIdentificationNumberList);

                }


            break;
            case DRIVERLISTENER:
                if(upStatus) {
                    driverListenerUrlList.add(url);
                }else{
                    driverListenerList.remove(driverListenerUrlList.size());
                    driverListenergridAdapter.setData(driverListenerList);

                }
            break;
            case GROUPPHOTO:
                if(upStatus) {
                    groupPhotoUrlList.add(url);
                }else{
                    groupPhotoList.remove(groupPhotoUrlList.size());
                    groupPhotogridAdapter.setData(groupPhotoList);
                }
            break;



        }



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
        ToastUtil.show(this,getString(R.string.upload_img_fail));
        finish();
    }

    @Override
    public void upLoadImgIng() {
        ToastUtil.show(this,"正在上传图片中");
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
            String fileStr1 = Environment.getExternalStorageDirectory()
                    + "/" + localTempImgDir + "/1" + localTempImgFileName;
            File f = new File(fileStr);
            try {
                Uri u =
                        Uri.parse(android.provider.MediaStore.Images.Media.insertImage(getContentResolver(),
                                f.getAbsolutePath(), null, null));
                //u就是拍摄获得的原始图片的uri，剩下的你想干神马坏事请便……

//                BitmapUtils.transImage(fileStr,fileStr1,200,100,100);
//                BitmapUtils.transImageCompress(fileStr,fileStr1,(float) 0.35,(float)0.35,100);
                BitmapUtils.transImageCompress(fileStr,fileStr1,(float) 0.3,(float)0.3,100);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            f.delete();
            CompensateImage compensateImage=new CompensateImage();
            compensateImage.setShowDelete(1);
            compensateImage.setImgUrl(fileStr1);

            switch (requestCode) {
                case DISTANT:
//                    mPresenter.uploadImg(DISTANT,fileStr);
                    compensateImage.setFlag(1);
                    distantList.add(compensateImage);
                    distantgridAdapter.setData(new ArrayList<CompensateImage>(distantList));

                    break;
                case COLSESHORT:
                    compensateImage.setFlag(2);
//                    mPresenter.uploadImg(COLSESHORT,fileStr);
                    colseShortList.add(compensateImage);
                    colseShortgridAdapter.setData(new ArrayList<CompensateImage>(colseShortList));
                    break;
                case LOSEOVERALL:
                    compensateImage.setFlag(3);
//                    mPresenter.uploadImg(LOSEOVERALL,fileStr);
                    loseOverallViewList.add(compensateImage);
                    loseOverallViewgridAdapter.setData(new ArrayList<CompensateImage>(loseOverallViewList));
                    break;
                case ASSEMBLYUNIT:
                    compensateImage.setFlag(4);
//                    mPresenter.uploadImg(ASSEMBLYUNIT,fileStr);
                    assemblyUnitList.add(compensateImage);
                    assemblyUnitgridAdapter.setData(new ArrayList<CompensateImage>(assemblyUnitList));
                    break;
                case VEHICLEIDENTICATIONNUMBER:
                    compensateImage.setFlag(5);
//                   mPresenter.uploadImg(VEHICLEIDENTICATIONNUMBER,fileStr);
                    vehicleIdentificationNumberList.add(compensateImage);
                    vehicleIdentificationNumberAdapter.setData(new ArrayList<CompensateImage>(vehicleIdentificationNumberList));
                    break;
                case DRIVERLISTENER:
//                    mPresenter.uploadImg(DRIVERLISTENER,fileStr);
                    compensateImage.setFlag(6);
                    driverListenerList.add(compensateImage);
                    driverListenergridAdapter.setData(new ArrayList<CompensateImage>(driverListenerList));

                    break;
                case GROUPPHOTO:
//                    mPresenter.uploadImg(GROUPPHOTO,fileStr);
                    compensateImage.setFlag(7);
                    groupPhotoList.add(compensateImage);
                    groupPhotogridAdapter.setData(new ArrayList<CompensateImage>(groupPhotoList));
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
                Toast.makeText(CompensateActivity.this, R.string.no_find_catch_dir, Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(CompensateActivity.this, R.string.no_catch_card, Toast.LENGTH_LONG).show();
        }
    }





        class  EditTextWatcher implements TextWatcher {

            String tmp = "";
            String digits = "/\\:*?<>|\"\n\t";
            private EditText editText;

           public  EditTextWatcher(EditText editText){
               this.editText=editText;

           }


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                tmp = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.setSelection(s.length());
            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
            if (str.equals(tmp)) {
                return;
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < str.length(); i++) {
                if (digits.indexOf(str.charAt(i)) < 0) {
                    sb.append(str.charAt(i));
                }
            }
            tmp = sb.toString();
                editText.setText(tmp);
        }
            }

    private void processPicture(Uri uri) {
        final String[] projection = {MediaStore.Images.Media.DATA};
        final Cursor cursor = managedQuery(uri, projection, null, null, null);
        cursor.moveToFirst();
        final int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        String imagePath = cursor.getString(columnIndex);


        final String targetPath = BitmapUtils.toRegularHashCode(imagePath) + ".jpg";
        BitmapUtils.compressBitmap(imagePath, targetPath, 640);  //压缩


    }


}



