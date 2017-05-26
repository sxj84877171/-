package com.soarsky.policeclient.ui.check;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.bean.CheckParam;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.local.db.bean.Check;
import com.soarsky.policeclient.data.local.db.dao.*;
import com.soarsky.policeclient.data.local.db.dao.CheckDao;
import com.soarsky.policeclient.data.remote.server1.ApiM;
import com.soarsky.policeclient.uitl.LogUtil;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * andriod_police_app<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 自动上传稽查记录<br>
 */


public class CheckRecordUpload extends HandlerThread{
    /**
     * Log tag
     */
    public static final String TAG = CheckRecordUpload.class.getSimpleName();
    /**
     * 上下文
     */
    private Context context;
    /**
     * 数据库操作的DaoSession
     */
    private DaoSession daoSession;
    /**
     * 数据库操作的RxQuery
     */
    private  RxQuery<Check> checkQuery;
    /**
     * 数据库操作的RxDao
     */
    private RxDao<Check, Long> checkDao;
    /**
     * 数据库的稽查数据列表
     */
    private List<Check> list=new ArrayList<>();
    /**
     * 消息handler
     */
    private Handler handler;

    public CheckRecordUpload(Context context) {
        this(TAG, Thread.NORM_PRIORITY, context);
    }

    public CheckRecordUpload(String name, int priority,Context context) {
        super(name, priority);
        this.context = context;
        //TODO
        daoSession = ((App) context.getApplicationContext()).getDaoSession();
        LogUtil.d("TAG", "CheckRecordUpload ===");
        init();
    }

    /**
     * 初始化数据
     */
   private void  init(){
       start();
       handler = new Handler(getLooper());
       handler.post(new Runnable() {
           @Override
           public void run() {
               getNoUpCheck();
           }
       });


   }

    /**
     * 从数据库中获取没有上传的数据
     */
    public void  getData(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getNoUpCheck();
            }
        },60*1000*60);
    }


    /**
     * 上传数据到服务器
     */
    public  void  upload(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                uploadCheck();
            }
        },2000);
    }


    /**
     * 更新数据库中的数据
     */
    public  void update(final Check check){
        handler.post(new Runnable() {
            @Override
            public void run() {
                updateStatus( check);
            }
        });


    }


    /**
     * 获取未上传的稽查记录
     */

    public List<Check> getNoUpCheck(){
        getCheckQuery().list().subscribe(new Subscriber<List<Check>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Check> checks) {
                list=checks;
                upload();
            }
        });
             return list;
    }


    /**
     * 上传数据
     */

    public void uploadCheck(){
        for (int i=0;i<list.size();i++){
            final int finalI = i;
            ApiM.getInstance().service.uploadInspect(list.get(i).createCommitMap()).subscribe(new Subscriber<ResponseDataBean<CheckParam>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(ResponseDataBean<CheckParam> checkParam) {
                    if("0".equals(checkParam.getStatus())){
                        Check check=list.get(finalI);
                        check.setStatus("1");
                        update(check);
                    }

                }
            });


        }
        //数据上传完成重新获取
        getData();

    }



    /**
     * 更新状态
     */
    private void  updateStatus(Check check){
        getViolationDao().update(check).subscribe(new Subscriber<Check>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
            e.printStackTrace();
            }

            @Override
            public void onNext(Check check) {
                LogUtil.d("TAG",check.toJson());
            }
        });

    }

    /**
     * 获取CheckQuery对象
     */
    public RxQuery<Check> getCheckQuery() {
        if (checkQuery == null) {
            checkQuery = daoSession.getCheckDao().queryBuilder().where(CheckDao.Properties.Status.eq(0)).distinct().rx();
        }
        return checkQuery;
    }


    /**
     * 获取checkDao对象
     */
    public RxDao<Check, Long> getViolationDao() {
        if (checkDao == null) {
            checkDao = daoSession.getCheckDao().rx();
        }
        return checkDao;
    }
}
