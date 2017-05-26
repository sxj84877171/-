package com.soarsky.car.server.cmd;

import com.google.gson.Gson;
import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.bean.TerminalParamBean;
import com.soarsky.car.data.local.db.ParamSetDb;
import com.soarsky.car.data.local.db.SoundParamDb;
import com.soarsky.car.data.local.db.bean.ParamSet;
import com.soarsky.car.data.local.db.bean.SoundParam;
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


public class SyncParamSound {

    BlueToothManage blueToothManage;

    List<SoundParam> soundParamList;



    /**
     * 要同步的条数
     */
    private  int  syncCount=0;

    /**
     * 已同步的条数
     */
    private  int   index=0;


    /**
     *
     * @param blueToothManage
     */
    BuleToothDataAnalyze.Callback  paramSetCallback;

    public SyncParamSound(BlueToothManage blueToothManage) {
        this.blueToothManage = blueToothManage;
    }


    //懒汉式单例类.在第一次调用的时候实例化自己
        private static SyncParamSound single=null;
        //静态工厂方法
        public static SyncParamSound getInstance(BlueToothManage blueToothManage) {
             if (single == null) {
                 single = new SyncParamSound(blueToothManage);
             }

            return single;
        }

    /**
     * 获取未同步到终端的数据
     */

    public void getNuTerminal(){
        soundParamList= SoundParamDb.getInstance(App.getApp()).getSoundParamlist(0);
        syncData();
    }

    /**
     * 同步数据
     */
    public  void  syncData(){
        if(soundParamList==null||soundParamList.size()==0){
            if(paramSetCallback!=null){
                paramSetCallback.onfinsh(BuleToothDataAnalyze.PARAM_SOUND);
                single=null;
            }


            return;
        }
        syncCount=soundParamList.size();
        if(blueToothManage!=null){
            try {
                blueToothManage.init(Constants.TERMINAL_SOUNT_PARAM,paseData(soundParamList.get(index)));
            }catch (Exception e){

                if(paramSetCallback!=null){
                    paramSetCallback.onfinsh(BuleToothDataAnalyze.PARAM_SOUND);
                }
            }

        }
    }


    /**
     * 封装请求数据
     */

    private   String  paseData( SoundParam soundParam){
        List<TerminalParamBean> list=new ArrayList<>();
        TerminalParamBean  terminalParamBean=new TerminalParamBean();
        terminalParamBean.setK(soundParam.getTextId());
        terminalParamBean.setV(soundParam.getContent());
        list.add(terminalParamBean);
        Gson gson = new Gson();
        return  gson.toJson(list);

    }




    public  void  setCmd(BaseCmd cmd){
        if(cmd instanceof  UpdateTerminalNumVoiceCommandResponse){
            UpdateTerminalNumVoiceCommandResponse updateTerminalNumVoiceCommandResponse= (UpdateTerminalNumVoiceCommandResponse) cmd;
            if(updateTerminalNumVoiceCommandResponse.isSucess()){

                try {
                    SoundParam soundParam=soundParamList.get(index);
                    soundParam.setTstate(1);
                    SoundParamDb.getInstance(App.getApp()).updateData(soundParam);
                }catch (Exception e){}

            }


            index++;
            if(index<syncCount){
                blueToothManage.init(Constants.TERMINAL_SOUNT_PARAM,paseData(soundParamList.get(index)));
            }else {
                if(paramSetCallback!=null){
                    paramSetCallback.onfinsh(BuleToothDataAnalyze.PARAM_SOUND);
                }
            }
        }

    }


    public void setParamSetCallback(BuleToothDataAnalyze.Callback paramSetCallback) {
        this.paramSetCallback = paramSetCallback;
    }
}
