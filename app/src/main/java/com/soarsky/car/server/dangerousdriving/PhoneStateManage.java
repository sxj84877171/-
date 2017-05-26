package com.soarsky.car.server.dangerousdriving;

import android.content.Context;
import android.media.AudioManager;
import android.telephony.*;
import android.util.Log;

import com.soarsky.car.server.check.ConfirmDriverService;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/16
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class PhoneStateManage {
    private ConfirmDriverService confirmDriverService;
    private Context context;
    TelephonyManager telManager;
    private MyPhoneStateListener myPhoneStateListener;

    AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

    public PhoneStateManage(Context context, ConfirmDriverService confirmDriverService) {
        this.context = context;
        this.confirmDriverService = confirmDriverService;
        telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     *注册监听
     */
    private void registerListener() {
        if(myPhoneStateListener==null){
            myPhoneStateListener=new MyPhoneStateListener();
            telManager.listen(myPhoneStateListener, android.telephony.PhoneStateListener.LISTEN_CALL_STATE);
        }

    }

    /**
     *取消注册
     */
    private void unRegisterListener() {
        if(myPhoneStateListener!=null){
            telManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_NONE);
            myPhoneStateListener=null;
        }

    }





    class MyPhoneStateListener extends android.telephony.PhoneStateListener{
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE: /* 无任何状态时 */

                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK: /* 接起电话时 */
                    if (getBluetoothState() || getHeadsetState()||isSpeakerphoneOn()) {

                    } else {

                    }
                    break;
                case TelephonyManager.CALL_STATE_RINGING: /* 电话进来时 */
                    break;
                default:
                    break;
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    }






    //判断蓝牙耳机是否连接上
    public boolean getBluetoothState() {

        if (audioManager.isBluetoothA2dpOn() || audioManager.isBluetoothScoOn()) {
            Log.d("TAG", "Bluetooth headset is connected!");
            return true;
        } else {
            Log.d("TAG", "Bluetooth headset is disconnected!");
            return false;
        }
    }

    //判断耳机是否插上
    public boolean getHeadsetState() {
        return audioManager.isWiredHeadsetOn();

    }
    /**
     * 判断是否开启免提
     */

    public Boolean isSpeakerphoneOn(){
        return  audioManager.isSpeakerphoneOn();
    }


}