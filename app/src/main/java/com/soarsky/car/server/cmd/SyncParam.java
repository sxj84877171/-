package com.soarsky.car.server.cmd;

import com.google.gson.Gson;
import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.bean.TerminalParamBean;
import com.soarsky.car.data.local.db.ParamSetDb;
import com.soarsky.car.data.local.db.bean.ParamSet;
import com.soarsky.car.server.bluetooth.BlueToothManage;
import com.soarsky.car.server.bluetooth.BuleToothDataAnalyze;

import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/4/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class SyncParam {

    BlueToothManage blueToothManage;
    private List<ParamSet> paramSetList;


    /**
     * 要同步的条数
     */
    private int syncCount = 0;

    /**
     * 已同步的条数
     */
    private int index = 0;


    /**
     * @param blueToothManage
     */
    BuleToothDataAnalyze.Callback paramSetCallback;

    public SyncParam(BlueToothManage blueToothManage) {
        this.blueToothManage = blueToothManage;
    }


    //懒汉式单例类.在第一次调用的时候实例化自己
    private static SyncParam single = null;

    //静态工厂方法
    public static SyncParam getInstance(BlueToothManage blueToothManage) {
        if (single == null) {
            single = new SyncParam(blueToothManage);
        }
        return single;
    }


    /**
     * 获取未同步到终端的数据
     */


    public void getNuTerminal() {
        paramSetList = ParamSetDb.getInstance(App.getApp()).getParamSetlist(0);
        syncData();
    }

    /**
     * 同步数据
     */
    public void syncData() {
        if (paramSetList == null || paramSetList.size() == 0) {
            if (paramSetCallback != null) {
                paramSetCallback.onfinsh(BuleToothDataAnalyze.PARAM_SET);
                single=null;
            }
            return;
        }
        syncCount = paramSetList.size();
        index = 0;
        if (blueToothManage != null) {
            blueToothManage.init(Constants.TERMINAL_PARAM_SET, paseData(paramSetList.get(index)));
        }
    }


    /**
     * 封装请求数据
     */

    private String paseData(ParamSet paramSet) {
        List<TerminalParamBean> list = new ArrayList<>();
        TerminalParamBean terminalParamBean = new TerminalParamBean();
        terminalParamBean.setK(paramSet.getParameterCode());
        terminalParamBean.setV(paramSet.getParameterValue());
        list.add(terminalParamBean);
        Gson gson = new Gson();
        return gson.toJson(list);

    }


    public void setCmd(BaseCmd cmd) {
        if (cmd instanceof UpdateTerminalNumCommanResponse) {
            UpdateTerminalNumCommanResponse updateTerminalNumCommanResponse = (UpdateTerminalNumCommanResponse) cmd;
            if (updateTerminalNumCommanResponse.isSucess()) {
                try {
                    ParamSet paramSet = paramSetList.get(index);
                    paramSet.setTstate(1);
                    ParamSetDb.getInstance(App.getApp()).updateData(paramSet);
                } catch (Exception e) {
                }
            }
            index++;
            if (index < syncCount) {
                blueToothManage.init(Constants.TERMINAL_PARAM_SET, paseData(paramSetList.get(index)));
            } else {
                if (paramSetCallback != null) {
                    paramSetCallback.onfinsh(BuleToothDataAnalyze.PARAM_SET);
                }

            }


        }

    }


    public void setParamSetCallback(BuleToothDataAnalyze.Callback paramSetCallback) {
        this.paramSetCallback = paramSetCallback;
    }
}
