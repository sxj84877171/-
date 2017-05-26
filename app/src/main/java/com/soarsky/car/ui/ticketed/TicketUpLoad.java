package com.soarsky.car.ui.ticketed;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.soarsky.car.base.Configure;
import com.soarsky.car.data.local.db.bean.Ticket;
import com.soarsky.car.data.remote.server1.Api;
import com.soarsky.car.uitl.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/6
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：将未同步到服务器的罚单同步到服务器
 * 该类为
 */


public class TicketUpLoad extends HandlerThread{
    public static final String TAG = TicketUpLoad.class.getSimpleName();
    private Context context;
    private List<Ticket> ticketList=new ArrayList<>();
    private Handler handler;
    public TicketUpLoad(Context context) {
        super(TAG, Thread.NORM_PRIORITY);
        this.context=context;
        init();

    }


    public void  init(){
        start();
        handler = new Handler(getLooper());
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
        ticketList= TicketDb.getInstance(context).getNuService();
        LogUtil.i("ticketList.size:"+ticketList.size());
        uploadData();
    }


    /**
     * 同步数据到服务器
     */
    public   void  uploadData(){
        for (Ticket ticket:ticketList) {
            uploadToService(new TicketUtil(ticket.getContent()).createCommitParams(),ticket);
        }


    }



    /**
     * 上传到服务器
     */
    public  void  uploadToService(Map<String,String> map, final Ticket ticket){
        Api.getInstance().service.appUploadIlleagal(map).subscribe(new Subscriber<IlleagalParam>() {
            @Override
            public void onCompleted() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startWork();
                    }
                },Configure.AUTOUPDATA);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                LogUtil.i(e.getStackTrace().toString());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startWork();
                    }
                }, Configure.AUTOUPDATA);
            }

            @Override
            public void onNext(IlleagalParam illeagalParam) {
                /**
                 * 更新罚单状态
                 */
                LogUtil.i(illeagalParam.toJson());
                ticket.setServiceStatus(2);
                TicketDb.getInstance(context).updateData(ticket);
            }
        });


    }



    public void stopWork() {
       this.getLooper().quit();
    }

}
