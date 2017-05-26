package com.soarsky.car.server.dangerousdriving;

import android.content.Context;
import android.media.AudioManager;
import android.telephony.*;
import android.util.Log;

import com.soarsky.car.App;
import com.soarsky.car.bean.Car;
import com.soarsky.car.server.check.ConfirmDriverService;
import com.soarsky.car.uitl.LogUtil;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/12/16<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 监听开车接电话<br>
 */


public class PhoneStateManage {

    /**
     * 开车打电话 ，违章类型。
     */
    public static final String DANGEROUS_TYPE_PHONE = "00" ;
    /**
     * 确认驾驶员服务
     */
    private ConfirmDriverService confirmDriverService;
    private Context context;
    TelephonyManager telManager;
    /**
     * 来电监听
     */
    private MyPhoneStateListener myPhoneStateListener;

    private AudioManager audioManager;
    private App app;


    public PhoneStateManage(Context context, ConfirmDriverService confirmDriverService) {
        this.context = context;
        this.confirmDriverService = confirmDriverService;
        app = (App) (context.getApplicationContext());
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

    }

    /**
     * 注册监听
     */
    public void registerListener() {
        if (null == myPhoneStateListener) {
            myPhoneStateListener = new MyPhoneStateListener();
            telManager.listen(myPhoneStateListener, android.telephony.PhoneStateListener.LISTEN_CALL_STATE);

        }
        LogUtil.i("电话监听注册成功");
    }

    /**
     * 取消注册
     */
    public void unRegisterListener() {
        if (myPhoneStateListener != null) {
            telManager.listen(myPhoneStateListener, PhoneStateListener.LISTEN_NONE);
            myPhoneStateListener = null;
        }
    }


    /**
     * 来电监听
     */
    class MyPhoneStateListener extends android.telephony.PhoneStateListener {
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE: /* 无任何状态时 */

                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK: /* 接起电话时 */
                    if (getBluetoothState() || getHeadsetState() || isSpeakerphoneOn()) {

                    } else {
                        //// TODO: 2017/1/4 通知终端
                        if (app.isConfirmDriver()) {
                            Car car = new Car(app.getiBridgeDevice());
                            car.setDangerousType(DANGEROUS_TYPE_PHONE);
                            confirmDriverService.byBluetoothSendMeeage(car,13);
                        }
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


    /**
     * 判断蓝牙耳机是否连接上
     */
    public boolean getBluetoothState() {

        if (audioManager.isBluetoothA2dpOn() || audioManager.isBluetoothScoOn()) {
            Log.d("TAG", "Bluetooth headset is connected!");
            return true;
        } else {
            Log.d("TAG", "Bluetooth headset is disconnected!");
            return false;
        }
    }

    /**
     * 判断耳机是否插上
     *
     * @return true  插上手机 false没有
     */
    public boolean getHeadsetState() {
        return audioManager.isWiredHeadsetOn();

    }

    /**
     * 判断是否开启免提
     *
     * @return TRUE 开启  false 没有
     */

    public Boolean isSpeakerphoneOn() {
        return audioManager.isSpeakerphoneOn();
    }


}