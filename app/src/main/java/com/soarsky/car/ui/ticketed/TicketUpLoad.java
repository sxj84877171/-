package com.soarsky.car.ui.ticketed;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import com.soarsky.car.Constants;
import com.soarsky.car.base.Configure;
import com.soarsky.car.bean.IlleagalParam;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.Ticket;
import com.soarsky.car.data.remote.server1.ApiF;
import com.soarsky.car.data.remote.server1.ApiM;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.NotifictionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/12/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：将未同步到服务器的罚单同步到服务器<br>
 * 该类为<br>
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






    /**
     * 初始化handler
     */
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
        ticketList= TicketDb.getInstance(context).getNuService();
        uploadData();
    }


    /**
     * 同步数据到服务器
     */
    public  synchronized void  uploadData(){
        for (Ticket ticket:ticketList) {

                uploadToService(createCommitParams(ticket),ticket);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startWork();
            }
        }, Configure.AUTOUPDATA);

    }



    public Map<String, String> createCommitParams(Ticket ticket) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("carnum", ticket.getCarnum());
            params.put("idNo", ticket.getIdNo());
            params.put("stime", ticket.getStime());
            params.put("etime", ticket.getEtime());
            params.put("lon", ticket.getLon());
            params.put("lat", ticket.getLat());
            params.put("inf", getInfString(ticket.getIno()));
            params.put("dType", ticket.getDType());
            params.put("ino", ticket.getIno());
            params.put("ptype", ticket.getPtype());

            return params;
        } catch (Exception e) {
            LogUtil.e(e);
            return null;
        }
    }


    /**
     * 上传到服务器
     */
    public  void  uploadToService(Map<String,String> map, final Ticket ticket){
        ApiM.getInstance().getService().appUploadIlleagal(map).compose(RxSchedulers.<ResponseDataBean<IlleagalParam>>io_main()).subscribe(new Subscriber<ResponseDataBean<IlleagalParam>>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                LogUtil.e(e);
            }
            @Override
            public void onNext(ResponseDataBean<IlleagalParam> illeagalParam) {
                /**
                 * 更新罚单状态
                 */
                LogUtil.i(illeagalParam.toJson());
                if(illeagalParam.getStatus().equals(Constants.REQUEST_SUCCESS)){
                    TicketDb.getInstance(context).updateServiceStatus(ticket.getId());
                    NotifictionUtils.getInstance().showNotifiction("罚单上传至服务器完成 "+"  罚单类型："+getInfString(ticket.getIno()));


                }

            }
        });


    }








    /**
     * 解析违章类型
     */

    private String getInfString(String inf) {
        int  index=StringToInt(inf);
        switch (index) {
            case 48:
                return "无证驾驶";
            case 49:
                return "滥用远光";
            case 50:
                return "没系安全带";
            case 51:
                return "违规使用安全带";
            case 52:
                return "超速";
            case 53:
                return "疲劳驾驶";
            case 54:
                return "大客车夜间违章运行";
            case 55:
                return "违反限行规定";
            case 56:
                return "违规使用喇叭";
            case 57:
                return "违停";
            case 97:
                return "开车玩手机";
            case 98:
                return "急停急走";

        }
        return "无证驾驶";
    }


    /**
     * String转Ascii数字
     * @param str
     * @return
     */
    public static int StringToInt(String str) {
        char a=str.charAt(0);
        return a;
    }

}
