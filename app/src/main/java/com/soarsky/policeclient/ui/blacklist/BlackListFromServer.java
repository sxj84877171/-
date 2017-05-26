package com.soarsky.policeclient.ui.blacklist;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

import com.soarsky.policeclient.Constants;
import com.soarsky.policeclient.bean.ResponseDataBean;
import com.soarsky.policeclient.data.local.db.bean.BlackCar;
import com.soarsky.policeclient.uitl.LogUtil;
import com.soarsky.policeclient.uitl.SpUtil;
import com.soarsky.policeclient.uitl.TimeUtils;

import java.util.Date;
import java.util.List;

import rx.Subscriber;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/7<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 从服务器获取黑名单列表<br>
 */
public class BlackListFromServer extends HandlerThread {
    /**
     * log tag
     */
    public static final String TAG = BlackListFromServer.class.getSimpleName();
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 黑名单列表model
     */
    private BlackListModel blackListModel;
    /**
     * 消息handler
     */
    private Handler handler;

    public BlackListFromServer(Context context) {
        this(TAG, Thread.NORM_PRIORITY, context);
    }

    public BlackListFromServer(String name, int priority, Context context) {
        super(name, priority);
        this.mContext = context;
        SpUtil.init(mContext);
        blackListModel = new BlackListModel();
        blackListModel.setContext();
    }

    /**
     * 开启线程
     */
    public void startGetBlackListFromServer() {
        start();
        handler = new Handler(getLooper());
        getBlackListFromServer();
        LogUtil.i("开始同步黑名单列表");
    }


    private  void getBlackListFromServer() {
        final String time = SpUtil.get(SpUtil.get(Constants.CONS_USERNAME) + Constants.BLACKLISTUPDATETIME);
        handler.post(new Runnable() {
            @Override
            public void run() {
                blackListModel.getBlackCar(time).subscribe(new Subscriber<ResponseDataBean<List<BlackCar>>>() {
                    @Override
                    public void onCompleted() {
                        blackListModel.getBlackCarQuery().list().subscribe(new Subscriber<List<BlackCar>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(List<BlackCar> blackCars) {
                                LogUtil.i("黑名单总数："+ blackCars.size());
                                for(BlackCar car:blackCars) {
                                    LogUtil.i("黑名单车牌号码："+ car.getCarnum());
                                }
                            }
                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(e);
                    }

                    @Override
                    public void onNext(ResponseDataBean<List<BlackCar>> blackListParam) {
                        SpUtil.save(SpUtil.get(Constants.CONS_USERNAME) + Constants.BLACKLISTUPDATETIME, TimeUtils.milliseconds3String(new Date().getTime()));
                        blackListModel.insertBlackCar(blackListParam.getData());
                    }
                });
            }
        });
        handler.removeCallbacks(syncBlackTask);
        handler.postDelayed(syncBlackTask, 60 * 1000 * 60);
    }

    private  Runnable syncBlackTask = new Runnable() {
        @Override
        public void run() {
            getBlackListFromServer();
        }
    };

    public void syncBlackCar(){
        handler.removeCallbacks(syncBlackTask);
        getBlackListFromServer();
    }
}
