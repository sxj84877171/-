package com.soarsky.policeclient.ui.violation;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.bean.ViolationDataBean;
import com.soarsky.policeclient.data.local.db.bean.Violation;
import com.soarsky.policeclient.data.local.db.dao.DaoSession;
import com.soarsky.policeclient.data.local.db.dao.ViolationDao;
import com.soarsky.policeclient.data.remote.server1.ApiM;
import com.soarsky.policeclient.data.remote.server1.ApiF;
import com.soarsky.policeclient.uitl.LogUtil;
import com.soarsky.policeclient.uitl.TimeUtils;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Subscriber;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 开电子罚单上传<br>
 */

public class ViolationUpload extends HandlerThread {
    /**
     * log tag
     */
    public static final String TAG = ViolationUpload.class.getSimpleName();
    private Context context;
    /**
     * 数据库DaoSession
     */
    private DaoSession daoSession;
    /**
     * 电子罚单列表
     */
    private List<Violation> list;
    /**
     * 网络图片地址
     */
    private String netimgurl = "";
    /**
     * RxQuery
     */
    private RxQuery<Violation> violationQuery;
    /**
     * RxDao
     */
    private RxDao<Violation, Long> violationDao;
    private static final int IMGUP_FINISH = 1;
    private static final int IMGUP_UNFINISH = 0;
    private static final int NETIMG = 2;
    private static final int LOCALIMG = 3;
    /**
     * Handler
     */
    private Handler handler;
    /**
     * 电子罚单信息
     */
    private Violation violation;


    public ViolationUpload(Context context) {
        this(TAG, Thread.NORM_PRIORITY, context);
    }

    public ViolationUpload(String name, int priority, Context context) {
        super(name, priority);
        this.context = context;
        //TODO
        daoSession = ((App) context.getApplicationContext()).getDaoSession();
        init();
        getNotUploadViolation();
        LogUtil.e("TAG", "ViolationUpload ===");
    }

    /**
     * 初始化
     */
    public void init() {
        start();
        handler = new Handler(getLooper());

    }



    /**
     * 获取数据
     */
    private void getData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getNotUploadViolation();
            }
        }, 60 * 1000);
    }
    /**
     * 上传服务器
     */
    private void updata() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                uploadImg();
            }
        }, 2000);
    }


    /**
     * 获取为上传的电子罚单
     *
     * @return
     */
    public RxQuery<Violation> getViolationQuery() {
        if (violationQuery == null) {
            violationQuery = daoSession.getViolationDao().queryBuilder().where(ViolationDao.Properties.Status.eq(0)).distinct().rx();
        }
        return violationQuery;
    }


    /**
     * 获取未上传的电子罚单
     *
     * @return
     */
    public List<Violation> getNotUploadViolation() {


        getViolationQuery().list().subscribe(new Subscriber<List<Violation>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                list = new ArrayList<Violation>();
            }

            @Override
            public void onNext(List<Violation> violations) {
                list = violations;
                updata();
            }
        });
        return list;
    }


    /**
     * 把未上传的图片上传到服务器
     */
    public void uploadImg() {

        if (list == null) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            violation = list.get(i);
            //将本地图片转换成数组
            String[] localimg = getLocalImgpath(violation.getLocalimgurlurl());
            //将网络图片转换成数组
            String[] netimg = getLocalImgpath(violation.getFileurl());
            //没有本地路径 不需要上传
            if (/*null == localimg*/violation.getSId()==null) {
                updateViolationToService();
                continue;
            }
            //已上传的网络图片张数
            int netimgcount = 0;
            //有本地路径 网络路径为空
            if (null != localimg && null == netimg) {
                netimgcount = 0;
            }
            //两个路径都不为空
            if (null != localimg && null != netimg) {
                //表示图片已上传完成
                if (localimg.length == netimg.length) {
                    modifyIlleagal();
                    continue;
                } else {
                    netimgcount = netimg.length-1;

                }
            }
            if(localimg!=null){
                for (int j = netimgcount; j < localimg.length; j++) {
                    if (j == localimg.length-1 ) {
                        upLoadImg(localimg[j], IMGUP_FINISH);
                    } else {
                        upLoadImg(localimg[j], IMGUP_UNFINISH);
                    }
                }
            }

        }
        //所有数据上传完成后重新取一次数据
        getData();
    }


    private void modifyIlleagal(){
        ApiM.getInstance().service.modifyIlleagal(Integer.parseInt(violation.getSId()),violation.getFileurl()).subscribe(new Subscriber<ResponseDataBean<Void>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseDataBean<Void> voidResponseDataBean) {
                updateViolationStatus();
            }
        });
    }

    /**
     * 获取图片路径
     * @param img 根据一个路径解析成字符串数组
     * @return 解析出的字符串数组
     */
    public String[] getLocalImgpath(String img) {
        String[] data;
        if (img == null || img.equals("")) {
            return null;
        }

        if (img.contains(",")) {
            data = img.split(",");
        } else {
            data = new String[]{img};


        }
        return data;
    }

    /**
     * 图片上传
     *
     * @param imgPath
     * @param status  判断图片是否更新完成 1表示完成 0表示还有未上传
     */
    public void upLoadImg(String imgPath, final int status) {


        File file = new File(imgPath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        uploadImgFile(status, body);

    }

    /**
     * 上传图片文件
     * @param status 是否上传的状态
     * @param body 图片数据
     */
    public void uploadImgFile(final int status, final MultipartBody.Part body) {

        ApiF.getInstance().service.uploadFile(body).subscribe(new Subscriber<UploadFile>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            /**
             * @param uploadFile
             */
            @Override
            public void onNext(UploadFile uploadFile) {
                LogUtil.d("TAG", "onNext ===" + uploadFile.getMsg());
                netimgurl = uploadFile.getData().get(0).getFileUrl();

                if (TextUtils.isEmpty(violation.getFileurl())) {
                    violation.setFileurl(netimgurl);
                } else {
                    violation.setFileurl(violation.getFileurl() + "," + netimgurl);
                }
                if (status == IMGUP_FINISH) {
                    //TODO 上传给服务器并将状态更新
                    modifyIlleagal();
                } else {
                    updateViolation();
                }


            }
        });
    }
    /**
     * 将违章信息上传到服务器
     */
    public void updateViolationToService() {

        ApiM.getInstance().service.sendViolation(packageParam()).subscribe(new Subscriber<ResponseDataBean<ViolationDataBean>>() {
            @Override
            public void onCompleted() {
                LogUtil.d("TAG", "onNext ===");
            }

            @Override
            public void onError(Throwable e) {
                LogUtil.e("TAG", "onError ===");
                e.printStackTrace();
            }

            @Override
            public void onNext(ResponseDataBean<ViolationDataBean> violationParam) {

                LogUtil.e("TAG", "onNext ===" + violationParam.getMessage());

                updateViolationSid(violationParam.getData().getId());
            }
        });

    }


    /**
     * 封装服务请求数据
     */
    public Map<String, String> packageParam() {
        ViolationSendParam violationSendParam = new ViolationSendParam();
        violationSendParam.setCarnum(violation.getCarnum());
        violationSendParam.setGrade(violation.getGrade());
        violationSendParam.setOpuser(violation.getOpuser());
        violationSendParam.setAddress(violation.getAddress());
        violationSendParam.setDrivers(violation.getDrivers());
        violationSendParam.setCent(violation.getCent() + "");
        violationSendParam.setInf(violation.getInf());
        violationSendParam.setIno(violation.getIno());
        violationSendParam.setMoney(violation.getMoney() + "");
        violationSendParam.setFileUrl(violation.getFileurl()==null?"":violation.getFileurl());
        violationSendParam.setStime(TimeUtils.date2String(violation.getStime(),new SimpleDateFormat("yyyyMMddHHmmss")));
        return violationSendParam.createCommitParams();
    }


    /**
     * 更新数据
     */
    public void updateViolation() {
        LogUtil.d("TAG", "111" + violation.toJson());
        getViolationDao().update(violation).subscribe(new Subscriber<Violation>() {
            @Override
            public void onCompleted() {
                LogUtil.d("TAG", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                LogUtil.d("TAG", "onError");
            }

            @Override
            public void onNext(Violation violation) {
                LogUtil.d("TAG", "222" + violation.toJson());
                LogUtil.d("TAG", "sucess");
            }
        });

    }

    /**
     * 更新服务器返回的id
     * @param id
     */
    public void updateViolationSid(String id) {
        violation.setSId(id);
        getViolationDao().update(violation).subscribe(new Subscriber<Violation>() {
            @Override
            public void onCompleted() {
                LogUtil.d("TAG", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                LogUtil.d("TAG", "onError");
            }

            @Override
            public void onNext(Violation violation) {
                LogUtil.d("TAG", "sucess");
            }
        });

    }
    /**
     * 更新数据
     */
    public void updateViolationStatus() {
        violation.setStatus(1);
        getViolationDao().update(violation).subscribe(new Subscriber<Violation>() {
            @Override
            public void onCompleted() {
                LogUtil.d("TAG", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                LogUtil.d("TAG", "onError");
            }

            @Override
            public void onNext(Violation violation) {
                LogUtil.d("TAG", "sucess");
            }
        });

    }

    public RxDao<Violation, Long> getViolationDao() {
        if (violationDao == null) {
            violationDao = daoSession.getViolationDao().rx();
        }
        return violationDao;
    }


    //停止handlerThread
    public void stopWork() {

        getLooper().quit();
    }


}
