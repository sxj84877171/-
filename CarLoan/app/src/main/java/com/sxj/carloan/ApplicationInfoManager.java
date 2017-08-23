package com.sxj.carloan;

import android.util.Log;

import com.sxj.carloan.bean.ServerBean;

/**
 * Created by admin on 2017/8/23.
 */

public class ApplicationInfoManager {

    private ApplicationInfoManager() {
    }

    private static ApplicationInfoManager instance = new ApplicationInfoManager();

    public static ApplicationInfoManager getInstance() {
        return instance;
    }

    private ServerBean.RowsBean info;

    public ServerBean.RowsBean getInfo() {
        return info;
    }

    public void setInfo(ServerBean.RowsBean info) {
//        Throwable throwable = new Throwable();
//        String message = Log.getStackTraceString(throwable);
//        Log.e("TAG" , "info is null: " + (info==null) + ",msg:" + message);
        this.info = info;
    }

    private int type;

    public void setNeed(boolean need) {
        this.need = need;
    }

    public boolean isNeed() {
        return need;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    private boolean need;


}
