package com.soarsky.car.server.check;

import android.content.Context;
import android.os.HandlerThread;
import android.util.Log;

import com.soarsky.car.Constants;
import com.soarsky.car.bean.ParamSetting;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.SoundParamDataBean;
import com.soarsky.car.data.local.db.ParamSetDb;
import com.soarsky.car.data.local.db.SoundParamDb;
import com.soarsky.car.data.local.db.bean.ParamSet;
import com.soarsky.car.data.local.db.bean.SoundParam;
import com.soarsky.car.data.remote.server1.ApiF;
import com.soarsky.car.data.remote.server1.ApiM;
import com.soarsky.car.data.remote.server1.ApiServiceImpl;
import com.soarsky.car.helper.RxSchedulers;
import com.soarsky.car.ui.car.CarActivity;

import java.util.List;

import rx.Subscriber;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/4/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class CarAppInitTask extends Thread {

    private Context mContext ;

    public CarAppInitTask(Context mContext) {
        super("CarAppInitTask");
        this.mContext = mContext;
    }

    @Override
    public void run() {
        super.run();

        updateParamSet(mContext);
    }

    /**
     * 更新参数数据
     */
    public void updateParamSet(final Context mContext){

        List<ParamSet> paramSetList = ParamSetDb.getInstance(mContext).getAllParamSetList();

        List<SoundParam> soundParamList = SoundParamDb.getInstance(mContext).getAllSoundParamlist();
        if(paramSetList.size()>0 || soundParamList.size()>0){
            return;
        }
        new ApiServiceImpl().param().subscribe(new Subscriber<ResponseDataBean<List<ParamSetting>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseDataBean<List<ParamSetting>> listResponseDataBean) {
                if(Constants.REQUEST_SUCCESS.equals(listResponseDataBean.getStatus())){
                    if(listResponseDataBean.getData() != null) {
                        for (ParamSetting paramSetting : listResponseDataBean.getData()) {
                            ParamSet paramSet = new ParamSet();
                            paramSet.setId((long)paramSetting.getId());
                            paramSet.setEffectRange(paramSetting.getEffectRange());
                            paramSet.setEffectTime(paramSetting.getEffectTime());
                            paramSet.setFailureTime(paramSetting.getFailureTime());
                            paramSet.setParameterCode(paramSetting.getParameterCode());
                            paramSet.setParameterDescription(paramSetting.getParameterDescription());
                            paramSet.setParameterValue(paramSetting.getParameterValue());
                            paramSet.setSortCode(paramSetting.getSortCode());
                            paramSet.setSortName(paramSetting.getSortName());
                            paramSet.setUpdatePerson(paramSetting.getUpdatePerson());
                            paramSet.setUpdateTime(paramSetting.getUpdateTime());
                            paramSet.setTstate(0);
                            ParamSetDb.getInstance(mContext).insertData(paramSet);
                        }
                    }
                }
            }
        });

        new ApiServiceImpl().voiceText().subscribe(new Subscriber<ResponseDataBean<List<SoundParamDataBean>>>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {

            }
            @Override
            public void onNext(ResponseDataBean<List<SoundParamDataBean>> listResponseDataBean) {

                if(Constants.REQUEST_SUCCESS.equals(listResponseDataBean.getStatus())){
                    if(listResponseDataBean.getData() != null){
                        for (SoundParamDataBean soundParamDataBean:listResponseDataBean.getData()){
                            SoundParam soundParam = new SoundParam();
                            soundParam.setId((long)soundParamDataBean.getId());
                            soundParam.setContent(soundParamDataBean.getContent());
                            soundParam.setEffectTime(soundParamDataBean.getEffectTime());
                            soundParam.setModifyTime(soundParamDataBean.getModifyTime());
                            soundParam.setModifyUser(soundParamDataBean.getModifyUser());
                            soundParam.setRemark(soundParamDataBean.getRemark());
                            soundParam.setTextId(soundParamDataBean.getTextId());
                            soundParam.setTstate(0);
                            SoundParamDb.getInstance(mContext).insertData(soundParam);
                        }
                    }
                }
            }
        });

       
    }


}
