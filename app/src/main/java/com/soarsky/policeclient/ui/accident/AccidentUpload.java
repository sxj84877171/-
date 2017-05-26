package com.soarsky.policeclient.ui.accident;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.google.gson.Gson;
import com.soarsky.policeclient.App;
import com.soarsky.policeclient.Constants;
import com.soarsky.policeclient.bean.AccidentDataBean;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.local.db.bean.accident.Accident;
import com.soarsky.policeclient.data.local.db.dao.AccidentDao;
import com.soarsky.policeclient.data.local.db.dao.AccidentReasonDao;
import com.soarsky.policeclient.data.local.db.dao.DaoSession;
import com.soarsky.policeclient.data.remote.server1.ApiM;
import com.soarsky.policeclient.data.remote.server1.ApiF;
import com.soarsky.policeclient.ui.accident.serverbean.AccidentAnalysis;
import com.soarsky.policeclient.ui.accident.serverbean.GuzhangBean;
import com.soarsky.policeclient.ui.violation.UploadFile;
import com.soarsky.policeclient.uitl.CarTypeUtils;
import com.soarsky.policeclient.uitl.CarUtil;
import com.soarsky.policeclient.uitl.LogUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/26<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 事故分析数据上传到后台<br>
 */
public class AccidentUpload extends HandlerThread {
    /**
     * Log tag
     */
    public static final String TAG = AccidentUpload.class.getSimpleName();
    /**
     * 上下文
     */
    private Context context;
    /**
     * 数据库session
     */
    private DaoSession daoSession;
    /**
     * 消息handler
     */
    private Handler handler;

    public AccidentUpload(Context context) {
        this(TAG, Thread.NORM_PRIORITY, context);
    }

    public AccidentUpload(String name, int priority, Context context) {
        super(name, priority);
        this.context = context;
        daoSession = ((App) context.getApplicationContext()).getDaoSession();
        init();
        LogUtil.d("TAG", "ViolationUpload ===");
    }

    /**
     * 初始化方法，开启上传功能
     */
    public void init() {
        start();
        handler = new Handler(getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                upload();
            }
        });
    }

    /**
     * 是否循环执行成功
     */
    boolean isLoopSuccess = true;

    /**
     * 执行上传事故数据到服务器
     */
    private void upload() {

        final List<Accident> accidents = App.getApp().getDaoSession().getAccidentDao().queryBuilder().where(AccidentDao.Properties.HasUpload.eq(false)).list();
        if (accidents != null && !accidents.isEmpty()) {
            final Gson gson = new Gson();
            for (int i = 0; i < accidents.size(); i++) {
                isLoopSuccess = true;
                if (accidents.get(i).getData() != null && !accidents.get(i).getData().equals("")) {
                    final AccidentDataBean.AccidentItemBean accidentItemBean = gson.fromJson(accidents.get(i).getData(), AccidentDataBean.AccidentItemBean.class);
                    if (accidentItemBean!=null && accidentItemBean.getAccidentCarBeanList() != null) {
                        for (int j = 0; j < accidentItemBean.getAccidentCarBeanList().size() && isLoopSuccess; j++) {
                            if (accidentItemBean.getAccidentCarBeanList().get(j).getGuzhangItems() != null) {
                                for (int k = 0; k < accidentItemBean.getAccidentCarBeanList().get(j).getGuzhangItems().size() && isLoopSuccess; k++) {
                                    if (accidentItemBean.getAccidentCarBeanList().get(j).getGuzhangItems().get(k).getId() == 0) {
                                        final int finalJ = j;
                                        final int finalK = k;
                                        final int finalI = i;

                                        if (accidentItemBean.getAccidentCarBeanList().get(j).getCar() != null && accidentItemBean.getAccidentCarBeanList().get(j).getCar().getCarNum() != null) {
                                            ApiM.getInstance().service.sendGuzhang(accidentItemBean.getAccidentCarBeanList().get(j).getCar().getCarNum(), accidentItemBean.getAccidentCarBeanList().get(j).getGuzhangItems().get(k).getTime(), accidentItemBean.getAccidentCarBeanList().get(j).getGuzhangItems().get(k).getGuzhang()).subscribe(new Subscriber<ResponseDataBean<GuzhangBean>>() {
                                                @Override
                                                public void onCompleted() {

                                                }

                                                @Override
                                                public void onError(Throwable e) {
                                                    isLoopSuccess = false;
                                                }

                                                @Override
                                                public void onNext(ResponseDataBean<GuzhangBean> guzhang) {
                                                    if (guzhang != null && guzhang.getData() != null && guzhang.getData().getId() != 0) {
                                                        accidentItemBean.getAccidentCarBeanList().get(finalJ).getGuzhangItems().get(finalK).setId(guzhang.getData().getId());
                                                        accidents.get(finalI).setData(gson.toJson(accidentItemBean));
                                                        App.getApp().getDaoSession().getAccidentDao().insertOrReplace(accidents.get(finalI));
                                                    }
                                                }
                                            });
                                        } else {
                                            isLoopSuccess = false;
                                        }

                                    }
                                }
                            }

                            if (isLoopSuccess && accidentItemBean.getAccidentCarBeanList().get(j) != null) {
                                //上传车辆的事故分析
                                final AccidentDataBean.AccidentItemBean.AccidentCarBean carBean = accidentItemBean.getAccidentCarBeanList().get(j);
                                boolean canUpload = true;
                                StringBuilder fault = new StringBuilder();
                                if (carBean.getGuzhangItems() != null) {
                                    for (AccidentDataBean.AccidentItemBean.AccidentCarBean.GuzhangItem guzhangItem : carBean.getGuzhangItems()) {
                                        if (guzhangItem.getId() == 0) {
                                            canUpload = false;
                                        }
                                        fault.append(guzhangItem.getId());
                                        fault.append(",");
                                    }
                                    if (fault.length() > 0) {
                                        if (fault.substring(fault.length() - 1).equals(",")) {
                                            fault.deleteCharAt(fault.length() - 1);
                                        }
                                    }

                                }
                                if (carBean.getId() == 0 && canUpload) {
                                    HashMap<String, String> dataMap = new HashMap<>();
                                    if (carBean.getCar() != null && carBean.getCar().getCarNum() != null && !carBean.getCar().getCarNum().equals("")) {
                                        dataMap.put("carnum", carBean.getCar().getCarNum());
                                    }
                                    if(carBean.getCar().getType()==null || carBean.getCar().getType().equals("")){
                                        dataMap.put("ctype", CarTypeUtils.carType(CarTypeUtils.parseCarType(carBean.getCar().getBlueName())));
                                    }else {
                                        dataMap.put("ctype", carBean.getCar().getType());
                                    }
                                    dataMap.put("safeState", carBean.getAnquandai()==null?"":CarUtil.parseCarAnquandaiDesc(carBean.getAnquandai()));
                                    dataMap.put("fault", fault.toString());
                                    if (carBean.getSudus() != null) {
                                        for (int a = 0; a < carBean.getSudus().size(); a++) {
                                            StringBuilder s = new StringBuilder();
                                            for (AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem suduItem : carBean.getSudus().get(a).getSuduItems()) {
                                                s.append(suduItem.getValue());
                                                s.append(",");
                                            }
                                            if (s.length() > 0) {
                                                if (s.substring(s.length() - 1).equals(",")) {
                                                    s.deleteCharAt(s.length() - 1);
                                                }
                                            }
                                            int index1 = a+1;
                                            dataMap.put("s" + index1, s.toString());
                                            if(carBean.getSudus().get(a).getSuduItems()!=null && carBean.getSudus().get(a).getSuduItems().get(0)!=null){
                                                dataMap.put("stime" + index1, carBean.getSudus().get(a).getSuduItems().get(0).getTime());
                                            }
                                        }

                                    }
                                    if (carBean.getDengItems() != null) {
                                        for (int b = 0; b < carBean.getDengItems().size(); b++) {
                                            int index2 = b+1;
                                            dataMap.put("l" + index2, carBean.getDengItems().get(b).getDeng());
                                            dataMap.put("ltime" + index2, carBean.getDengItems().get(b).getTime());
                                        }
                                    }
                                    dataMap.put("stime", accidentItemBean.getTime());
                                    final int finalI1 = i;
                                    ApiM.getInstance().service.uploadAccidentAnalysis(dataMap).subscribe(new Subscriber<ResponseDataBean<AccidentAnalysis>>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            isLoopSuccess = false;
                                        }

                                        @Override
                                        public void onNext(ResponseDataBean<AccidentAnalysis> accidentAnalysis) {
                                            if (accidentAnalysis != null && accidentAnalysis.getData() != null && accidentAnalysis.getData().getId() != 0) {
                                                carBean.setId(accidentAnalysis.getData().getId());
                                                accidents.get(finalI1).setData(gson.toJson(accidentItemBean));
                                                App.getApp().getDaoSession().getAccidentDao().insertOrReplace(accidents.get(finalI1));
                                            }
                                        }
                                    });
                                }
                            }

                        }
                    }


                    //上传事故需要先上传图片然后再上传事故
                    if (accidentItemBean.getImages() != null) {
                        for (int c = 0; c < accidentItemBean.getImages().size() && isLoopSuccess; c++) {
                            if (accidentItemBean.getImages().get(c).getServerPath() == null) {
                                File file = new File(accidentItemBean.getImages().get(c).getMyPath());
                                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                                // MultipartBody.Part is used to send also the actual file name
                                MultipartBody.Part body =
                                        MultipartBody.Part.createFormData("file", file.getName(), requestFile);
                                final int finalC = c;
                                final int finalI1 = i;
                                ApiF.getInstance().service.uploadFile(body).subscribe(new Subscriber<UploadFile>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        isLoopSuccess = false;
                                    }

                                    /**
                                     * @param uploadFile
                                     */
                                    @Override
                                    public void onNext(UploadFile uploadFile) {
                                        if (uploadFile != null && uploadFile.getData() != null && uploadFile.getData().size() != 0) {
                                            accidentItemBean.getImages().get(finalC).setServerPath(uploadFile.getData().get(0).getFileUrl());
                                            accidents.get(finalI1).setData(gson.toJson(accidentItemBean));
                                            App.getApp().getDaoSession().getAccidentDao().insertOrReplace(accidents.get(finalI1));
                                        }
                                    }
                                });
                            }

                        }
                    }


                    if (isLoopSuccess) {
                        StringBuilder iamgeSb = new StringBuilder();
                        if (accidentItemBean.getImages() != null) {
                            for (AccidentDataBean.AccidentItemBean.Image image : accidentItemBean.getImages()) {
                                if (image.getServerPath() == null || image.getServerPath().equals("")) {
                                    isLoopSuccess = false;
                                }
                                iamgeSb.append(image.getServerPath());
                                iamgeSb.append(",");
                            }
                            if (iamgeSb.length() > 0) {
                                if (iamgeSb.substring(iamgeSb.length() - 1).equals(",")) {
                                    iamgeSb.deleteCharAt(iamgeSb.length() - 1);
                                }
                            }

                        }
                        StringBuilder aanalysisSb = new StringBuilder();
                        if (accidentItemBean.getAccidentCarBeanList() != null) {

                            for (AccidentDataBean.AccidentItemBean.AccidentCarBean accidentCarBean : accidentItemBean.getAccidentCarBeanList()) {
                                if (accidentCarBean.getId() == 0) {
                                    isLoopSuccess = false;
                                }
                                aanalysisSb.append(accidentCarBean.getId());
                                aanalysisSb.append(",");
                            }
                            if (aanalysisSb.length() > 0) {
                                if (aanalysisSb.substring(aanalysisSb.length() - 1).equals(",")) {
                                    aanalysisSb.deleteCharAt(aanalysisSb.length() - 1);
                                }
                            }

                        }

                        if (isLoopSuccess) {
                            if (accidentItemBean.getYuanyin() != null && !accidentItemBean.getYuanyin().equals("")) {
                                com.soarsky.policeclient.data.local.db.bean.accident.AccidentReason accidentReason = App.getApp().getDaoSession().getAccidentReasonDao().queryBuilder().where(AccidentReasonDao.Properties.Reason.eq(accidentItemBean.getYuanyin())).unique();
                                final int finalI2 = i;
                                if(accidents.get(i).getIsUpdate()){
                                    ApiM.getInstance().service.updateAccident(accidentItemBean.getId()+"",aanalysisSb.toString(), accidentReason.getId() + "", accidentItemBean.getTime(), accidentItemBean.getWeizhi(), accidentItemBean.getYuanyin(), iamgeSb.toString(), accidentItemBean.getBeizhu()).subscribe(new Subscriber<ResponseDataBean>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                            Log.e("weikai","Throwable");
                                        }

                                        @Override
                                        public void onNext(ResponseDataBean accident) {
                                            if (accident != null && accident.getStatus().equals(Constants.STATUS)) {
                                                accidents.get(finalI2).setHasUpload(true);
                                                accidents.get(finalI2).setData(gson.toJson(accidentItemBean));
                                                App.getApp().getDaoSession().getAccidentDao().insertOrReplace(accidents.get(finalI2));
                                            }
                                        }
                                    });
                                }else {
                                    ApiM.getInstance().service.uploadAccident(aanalysisSb.toString(), accidentReason.getId() + "", accidentItemBean.getTime(), accidentItemBean.getWeizhi(), accidentItemBean.getYuanyin(), iamgeSb.toString(), accidentItemBean.getBeizhu()).subscribe(new Subscriber<ResponseDataBean>() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                        }

                                        @Override
                                        public void onNext(ResponseDataBean accident) {
                                            if (accident != null && accident.getStatus().equals(Constants.STATUS)) {
                                                accidents.get(finalI2).setHasUpload(true);
                                                accidents.get(finalI2).setData(gson.toJson(accidentItemBean));
                                                App.getApp().getDaoSession().getAccidentDao().insertOrReplace(accidents.get(finalI2));
                                            }
                                        }
                                    });
                                }

                            }

                        }
                    }

                }
            }

        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 60 * 60 * 1000);
    }

}
