package com.soarsky.car.ui.carcheck;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.soarsky.car.base.Configure;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.TroubleInfo;
import com.soarsky.car.data.local.db.TroubleDb;
import com.soarsky.car.data.local.db.bean.Trouble;
import com.soarsky.car.data.remote.server1.ApiM;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.TimeUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/12/30<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为<br>
 */


public class TroubleUpload extends HandlerThread{

    public static final String TAG = TroubleUpload.class.getSimpleName();
    private Context context;
    private List<Trouble> troubleList=new ArrayList<>();
    private Handler handler;
    public TroubleUpload(Context context) {
        super(TAG, Thread.NORM_PRIORITY);
        this.context=context;
        init();

    }


    public void  init(){
        start();
        handler = new Handler(getLooper());
        startWork();
    }

    public  void startWork(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                getNuService();
            }
        });

    }


    /**
     * 查询未同步到服务器的罚单
     */

    public  void   getNuService(){
        troubleList= TroubleDb.getInstance(context).getUnSync();
        upload();

    }


    /**
     * 上传数据
     */
    private  void  upload(){
        LogUtil.i("上传罚单");
        for (Trouble trouble:troubleList) {
            upToService(trouble);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startWork();
            }
        }, Configure.AUTOUPDATA);


    }

    private void  upToService(final Trouble trouble){
        ApiM.getInstance().service.sendTrouble(trouble.getCarNum(), TimeUtils.date2String(trouble.getCreateTime()),trouble.getTroubleMessage()).subscribe(new Subscriber<ResponseDataBean<TroubleInfo>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(ResponseDataBean<TroubleInfo> troubleParam) {
                if(troubleParam.getStatus().equals("0")){
                    trouble.setStatus("1");
                    TroubleDb.getInstance(context).updateData(trouble);
                }
            }
        });

    }


}
