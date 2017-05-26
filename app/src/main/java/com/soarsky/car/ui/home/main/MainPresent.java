package com.soarsky.car.ui.home.main;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.soarsky.car.App;
import com.soarsky.car.CommonParam;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BasePresenter;
import com.soarsky.car.bean.Car;
import com.soarsky.car.bean.CheckVersionBean;
import com.soarsky.car.bean.DriviLicenseSendParam;
import com.soarsky.car.bean.DrivingLicenseInfo;
import com.soarsky.car.bean.DrivingLicenseInformationDataBean;
import com.soarsky.car.bean.FamilyNumIlistBean;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.QueryFamilyBean;
import com.soarsky.car.bean.QueryFamilySendParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.TerminalInfoParam;
import com.soarsky.car.bean.UploadFile;
import com.soarsky.car.bean.ViolationDealInfo;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.server.wifi.ConnectCar;
import com.soarsky.car.server.wifi.WifiTransfer;
import com.soarsky.car.ui.illegal.IllegalManageActivity;
import com.soarsky.car.ui.violationdeal.ViolationDealSendParam;
import com.soarsky.car.uitl.ConstUtils;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.TimeUtils;
import com.soarsky.car.uitl.ToastUtil;

import java.lang.reflect.Array;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/1<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  检测版本更新的P层<br>
 */
public class MainPresent extends BasePresenter<MainModel, MainView> {
    private int voice;

    private ConnectCar connectCar;
    private App app ;
    Gson gson = new Gson();

    @Override
    public void onStart() {
        mModel.setContext(context);
        connectCar = new ConnectCar(context);
        app = (App) context.getApplication();
        SpUtil.init(context);



        CommonParam commonParam = new CommonParam();
        commonParam.setUserName(SpUtil.get(Constants.USERNAME));
        commonParam.setPassWord(SpUtil.get(Constants.PASSWORD));
        commonParam.setIdNo(SpUtil.get(Constants.IDNO));
        commonParam.setIsBind(SpUtil.get(Constants.IS_BIND));
        commonParam.setOwerPhone(SpUtil.get(Constants.OWNER_PHONE));
        commonParam.setQueryPwd(SpUtil.get(Constants.QUERY_PWD));
        commonParam.setRealName(SpUtil.get(Constants.REAL_NAME));
        commonParam.setCarNum(SpUtil.get(Constants.CAR_NUM));
        commonParam.setDrivingLicence(SpUtil.get(Constants.LICESE_NUM));
        commonParam.setRegisterCarDate(SpUtil.get(Constants.REGISTER_CAR_DATE));
        commonParam.setRegisterDriverDate(SpUtil.get(Constants.REGISTER_DRIVERDATE));
        commonParam.setDrivingType(SpUtil.get(Constants.DRIVING_TYPE));

        app.setOnline(true);
        app.setImageUrl(SpUtil.get(Constants.IMAGE_URL));
        app.setCommonParam(commonParam);

        mView.initViewData(commonParam);



    }


    public void initData(){

        //获取所有车辆
        getAllCarnum(app.getCommonParam().getOwerPhone());
        //获取驾驶证
        getDriverList(app.getCommonParam().getIdNo());
        // 获取行驶证
        DriviLicenseSendParam p = new DriviLicenseSendParam();
        p.setPhone(app.getCommonParam().getOwerPhone());
        p.setIdNo(app.getCommonParam().getIdNo());
        p.setVerCode(Constants.VerCode);
        getElecDriver(p);

        //获取违章信息条数
        ViolationDealSendParam pp = new ViolationDealSendParam();
        pp.setCarnum(app.getCommonParam().getCarNum());
        violationDeal(pp);

        initFamilyPhoneNum();
    }

    /***
     * 获取亲情号码
     * @param param 入参
     */
    public void queryFirendPhone(QueryFamilySendParam param){
        mView.showProgess();
        mModel.queryFirendPhone(param).subscribe(new Subscriber<ResponseDataBean<QueryFamilyBean>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();

            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.showFamilyFail();
            }

            @Override
            public void onNext(ResponseDataBean<QueryFamilyBean> param) {

                mView.stopProgess();
                mView.showFamilySuccess(param);
            }
        });
    }

    /***
     * 插入亲情号码数据
     * @param familyNum 亲情号
     */
    public void insertFamilyNum(FamilyNum familyNum){

        mRxManager.add(mModel.insertFamilyNum(familyNum).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<FamilyNum>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(FamilyNum familyNum) {
                Log.d("TAA","+++success!"+familyNum.toJson());
            }
        }));

    }

    /***
     * 清空亲情表所有的数据
     */
    public void deleteAll(){
        mView.showProgess();

        mRxManager.add(mModel.deleteAll().observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Void>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.deleteAllFail();
            }

            @Override
            public void onNext(Void aVoid) {
                mView.stopProgess();
                mView.deleteAllSuccess();
            }
        }));
    }


    /**
     * 设置SeekBar监听
     * @param seekBar 进度条
     */
    public void  setSeekBarListener(SeekBar seekBar){

        seekBar.setOnSeekBarChangeListener(new SeekBarListener());

    }

    /**
     * 设置服务
     * @param confirmDriverService
     */
    public void  setConfirmDriverService(ConfirmDriverService confirmDriverService){
        mModel.setConfirmDriverService(confirmDriverService);
    }



    private class  SeekBarListener implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(progress<3){
                seekBar.setProgress(3);
                ToastUtil.show(context,"声音不能再小了！");
            }
            voice=progress;
            SpUtil.save("volume",String.valueOf(voice));



//            if(WifiUtil.getInstance().getWifiManager().getConnectionInfo().getSSID())




        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {


            LogUtil.i("volume"+voice);
            SpUtil.save("volume",String.valueOf(voice));


        }
    }

    /**
     * 与终端连接监听
     */
    private void  connectVolumeListen(){
        mModel.setVolumeListener(new OnVolumeListener() {
            @Override
            public void onVolumeSuccess(WifiTransfer transfer,Socket socket) {
                mView.stopProgess();
                mView.connectSuccess(transfer,socket);
            }

            @Override
            public void onVolumeFailed() {

                mView.stopProgess();
                mView.connectFail();

            }
        });
    }


    /**
     * 发送连接申请
     * @param car  车
     * @param type  申请类型
     */
    public void  sendApply(Car car, int type){

        mView.showProgess();
        connectVolumeListen();
        mModel.connectCar(car,type);
    }


    /**
     * 发送离车请求
     */

    public void sendDriverLeave(){
        mModel.sendDriverLeave();
    }


    /**
     * 请求违章处理请求
     * @param param 违章请求参数
     */
    public void violationDeal(ViolationDealSendParam param){
//        mView.showProgess();
        mModel.violationDeal(param).subscribe(new Subscriber<ResponseDataBean<ViolationDealInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
//                mView.stopProgess();
                mView.showError(e);
            }

            @Override
            public void onNext(ResponseDataBean<ViolationDealInfo> violationDealParam) {
//                mView.stopProgess();
                mView.showSuccess(violationDealParam);

            }
        });
    }

    /**
     * 获取车牌号
     * @param phone 号码
     */
    public void getAllCarnum(String phone){

//        mView.showProgess();

        mModel.getAllCarnum(phone).subscribe(new Subscriber<ResponseDataBean<List<LicenseInfo>>>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
//                mView.stopProgess();
//                mView.getAllCarnumFail();
                if(SpUtil.get(Constants.CAR_LIST)!=null &&!(("").equals(SpUtil.get(Constants.CAR_LIST)))){

                    List<LicenseInfo> car_list = gson.fromJson(SpUtil.get(Constants.CAR_LIST), new TypeToken<List<LicenseInfo>>(){}.getType());

                    app.setCar_list(car_list);
                }

            }

            @Override
            public void onNext(ResponseDataBean<List<LicenseInfo>> carNumParam) {
//                mView.stopProgess();
//                mView.getAllCarnumSuccess(carNumParam);
                if(carNumParam!=null) {
                    if (Constants.REQUEST_SUCCESS.equals(carNumParam.getStatus())) {
                        List<LicenseInfo> car_list = carNumParam.getData();
                        SpUtil.save(Constants.CAR_LIST,gson.toJson(car_list));
                        app.setCar_list(carNumParam.getData());
                    }
                }else {
                    if(SpUtil.get(Constants.CAR_LIST)!=null &&!(("").equals(SpUtil.get(Constants.CAR_LIST)))){

                        List<LicenseInfo> car_list = gson.fromJson(SpUtil.get(Constants.CAR_LIST), new TypeToken<List<LicenseInfo>>(){}.getType());

                        app.setCar_list(car_list);
                    }
                }
                getTerminalInfo(carNumParam.getData());

            }
        });

    }

    /**
     * 获取我的驾驶证
     * @param param 驾驶证请求参数
     */
    public void getElecDriver(DriviLicenseSendParam param){
//        mView.showProgess();
        mModel.getElecDriver(param.getIdNo(),param.getPhone(),param.getVerCode()).subscribe(new Subscriber<ResponseDataBean<DrivingLicenseInfo>>() {
            @Override
            public void onCompleted() {

//                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

//                mView.stopProgess();
                mView.showLicenseError();
            }

            @Override
            public void onNext(ResponseDataBean<DrivingLicenseInfo> param) {

//                mView.stopProgess();
                if (param != null) {
                    if (Constants.REQUEST_SUCCESS.equals(param.getStatus())) {
                        app.getCommonParam().setDrivingLicence(param.getData().getNum());
                        app.getCommonParam().setDrivingType(param.getData().getDrivingType());
                        app.getCommonParam().setRegisterDriverDate(param.getData().getClearDate());
                        SpUtil.save(Constants.LICESE_NUM, param.getData().getNum());
                        SpUtil.save(Constants.DRIVING_TYPE, param.getData().getDrivingType());
                        SpUtil.save(Constants.REGISTER_DRIVERDATE, param.getData().getClearDate());
                    }
                }
                mView.showLicenseSuccess(param);

            }
        });
    }

    /**
     * 根据车牌号获取终端信息
     * @param carNumParam 驾照参数
     */

    public void getTerminalInfo(final List<LicenseInfo> carNumParam){


        if(null!=carNumParam&&carNumParam.size()>0){
            for(int i=0;i<carNumParam.size();i++){
                String carnum=carNumParam.get(i).getPlateno();
                final int index = i;
                mModel.getTerminalInfo(carnum,index).subscribe(new Subscriber<ResponseDataBean<TerminalInfoParam>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onNext(ResponseDataBean<TerminalInfoParam> terminalInfoParam) {
//                        mView.getTerminalInfoSuccess( index,terminalInfoParam);

                        if(terminalInfoParam!= null){
                            if(Constants.REQUEST_SUCCESS.equals(terminalInfoParam.getStatus())){
//                                Log.d("TAG","index=="+index+"trimel="+terminalInfoParam.getData().getTerminalnumber());
                                app.getCar_list().get(index).setAuthcode(terminalInfoParam.getData().getWarrant());
                                Log.d("TAG","index=="+index+"auth=="+app.getCar_list().get(index).getAuthcode());

                            }
                        }
                    }
                });
            }
            }



        }







    /**
     * 照片上传
     * @param imgPath 路径
     */
    public void uploadImg(String imgPath) {
        mView.showProgess();
        mModel.upLoadImg(imgPath).subscribe(new Subscriber<UploadFile>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.uploadImgFail();
                Log.d("TAG",e.getStackTrace().toString());
                LogUtil.i("onError" + e.getStackTrace().toString());
            }

            @Override
            public void onNext(UploadFile uploadFile) {
                mView.stopProgess();
                mView.uploadImgSuccess(uploadFile);
                Log.d("TAG",uploadFile.toJson());
                LogUtil.i("onNext" + uploadFile.toJson());

            }
        });
    }

    /**
     * 上传图片信息接口
     * @param imageUrl 路径
     * @param username 用户名
     */
    public void modifyAppImage(String imageUrl,String username){
        mView.showProgess();

        mModel.modifyAppImage(imageUrl,username).subscribe(new Subscriber<ResponseDataBean<String>>() {
            @Override
            public void onCompleted() {
                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {

                mView.stopProgess();
                mView.modifyAppImageFail();
            }

            @Override
            public void onNext(ResponseDataBean<String> stringResponseDataBean) {

                mView.stopProgess();
                mView.modifyAppImageSuccess(stringResponseDataBean);

            }
        });

    }

    /**
     * 获取车牌号
     * @param idNo 身份证
     */
    public void getDriverList(String idNo){

//        mView.showProgess();

        mModel.getDriverList(idNo).subscribe(new Subscriber<ResponseDataBean<List<DrivingLicenseInformationDataBean>>>() {
            @Override
            public void onCompleted() {

                mView.stopProgess();
            }

            @Override
            public void onError(Throwable e) {


            }

            @Override
            public void onNext(ResponseDataBean<List<DrivingLicenseInformationDataBean>> carNumParam) {


                if(carNumParam != null){
                    if (Constants.REQUEST_SUCCESS.equals(carNumParam.getStatus())) {
                        if (carNumParam.getData().size() > 0) {
                            app.getCommonParam().setCarNum(carNumParam.getData().get(0).getPlateno());
                            app.getCommonParam().setRegisterCarDate(carNumParam.getData().get(0).getRegisterdate());
                            SpUtil.save(Constants.CAR_NUM, carNumParam.getData().get(0).getPlateno());
                            SpUtil.save(Constants.REGISTER_CAR_DATE, carNumParam.getData().get(0).getRegisterdate());
                        }
                    }

                    mView.getDriverListSuccess(carNumParam);
                }

            }
        });

    }

  public int getVoice(){
        return voice;
    }
    /**
     * 检测版本号
     * @param type 类型0，车主，1警务通
     */
    public void checkVersion(String type){
        mView.showProgess();
        mModel.checkVersion(type).subscribe(new Subscriber<ResponseDataBean<CheckVersionBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mView.stopProgess();
                mView.showError(e);
            }

            @Override
            public void onNext(ResponseDataBean<CheckVersionBean> checkVersion) {
                mView.stopProgess();
                if (checkVersion.getStatus().equals(SUCCESS_FLAG)){
                    mView.checkSuccess(checkVersion);
                }else {
                    mView.checkFail(checkVersion);
                }
            }
        });


    }
    /**
     * 下载文件
     * @param url   资源文件
     */
    public  void  loadFile(String url){


        mView.showProgess();
        mModel.loadFile(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mView.stopProgess();
                mView.loadSuccess(call,response);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mView.stopProgess();
                mView.loadFail(call,t);
            }
        });
    }

    /**
     * 收到违章信息通知栏
     * @param context 上下文本
     * @param count 违章数
     */
    public void showNotification(Context context,int count){

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher).setContentTitle("通知：").
                setContentText("您现在已收到"+count+"违章记录！");
        Intent resultIntent = new Intent(context, IllegalManageActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
       // mId allows you to update the notification later on.
        Notification notification = mBuilder.build();

        //notification.defaults|= Notification.DEFAULT_VIBRATE;
        mNotificationManager.notify(0, notification);

    }


    public void queryUpdateTask(String carnum,String deviceTocken,String type){

        mModel.queryUpdateTask(carnum,deviceTocken,type).subscribe(new Subscriber<ResponseDataBean<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseDataBean<String> stringResponseDataBean) {

            }
        });
    }

    public void changeCarText(TextView carTv,TextView dateTv){

        carTv.setText(SpUtil.get(Constants.CAR_NUM));
        try {
            if ( SpUtil.get(Constants.REGISTER_CAR_DATE) != null) {
                if (!(TextUtils.isEmpty( SpUtil.get(Constants.REGISTER_CAR_DATE)))) {
                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = sDateFormat.parse( SpUtil.get(Constants.REGISTER_CAR_DATE));
                    // 车的年检日期
                    Date dd = TimeUtils.addYear(d, 1);
                    long ddd = dd.getTime() - new Date().getTime();
                    dateTv.setText("" + roundCleanDay(TimeUtils.milliseconds2Unit(ddd, ConstUtils.DAY)) + "天");
                } else {
                    dateTv.setText("无");
                }
            } else {
                dateTv.setText("无");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private long roundCleanDay(long day) {
        while (day < 0) {
            day += 365;
        }

        while (day >= 365) {
            day -= 365;
        }

        return day;
    }

    //初始化亲情号
    public void initFamilyPhoneNum(){


        mRxManager.add(mModel.deleteAll().observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Void>() {
            @Override
            public void onCompleted() {

                QueryFamilySendParam p = new QueryFamilySendParam();
                p.setPhone(app.getCommonParam().getOwerPhone());
                p.setUsername(app.getCommonParam().getUserName());
                p.setCarnum(app.getCommonParam().getCarNum());
                mModel.queryFirendPhone(p).subscribe(new Subscriber<ResponseDataBean<QueryFamilyBean>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onNext(ResponseDataBean<QueryFamilyBean> param) {

                        for (FamilyNumIlistBean bean : param.getData().getIlist()) {
                            FamilyNum familyNum = new FamilyNum();
                            familyNum.setPhone(bean.getPhone());
                            familyNum.setUsername(app.getCommonParam().getUserName());
                            familyNum.setCarnum(app.getCommonParam().getCarNum());
                            familyNum.setIs_owner(bean.getIsOwner());
                            familyNum.setSstate(1);
                            familyNum.setTstate(0);
                            insertFamilyNum(familyNum);
                            Log.d("TAG","insertFamilyNum");
                        }
                    }
                });
            }

            @Override
            public void onError(Throwable e) {

                LogUtil.d("suyun",e.getMessage());
            }

            @Override
            public void onNext(Void aVoid) {


            }
        }));
    }

}
