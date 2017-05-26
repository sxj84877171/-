package com.soarsky.policeclient.uitl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.soarsky.policeclient.ui.accident.AccidentUpload;
import com.soarsky.policeclient.ui.check.CheckRecordUpload;
import com.soarsky.policeclient.ui.violation.ViolationUpload;

/**
 * 警务通<br>
 * 作者： 孙向锦<br>
 * 时间： 2016/12/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 监听是否有网<br>
 */

public class NetState extends BroadcastReceiver {
    /**
     * 上下文
     */
    private Context context;
    /**
     * 电子罚单上传
     */
    private ViolationUpload violationUpload;
    /**
     * 稽查记录上传
     */
    private CheckRecordUpload checkRecordUpload;
    /**
     * 事故分析上传
     */
    private AccidentUpload accidentUpload;

    public NetState(Context context) {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        LogUtil.e("NetState", "===========================");
        if (NetworkUtils.isAvailable(context)) {

            if (NetworkUtils.isWifiConnected(context)) {

                //判断连接的热点名字是不是车牌号
                if (ValidatorUtils.validatorSsid(NetworkUtils.getWifiName(context))) {
                    stopUpload();
                } else {
                    upload();
                }

            } else {
                upload();
            }

        } else {
            stopUpload();
        }

    }

    /**
     * 上传初始化
     */
    public void upload() {
        violationUpload = new ViolationUpload(context);
        checkRecordUpload = new CheckRecordUpload(context);
        accidentUpload = new AccidentUpload(context);
    }

    /**
     * 停止上传
     */
    public void stopUpload() {
        if (violationUpload != null) {

            violationUpload.getLooper().quit();
        }
        if(checkRecordUpload!=null)

        {
            checkRecordUpload.getLooper().quit();
        }
        if(accidentUpload != null){
            if(accidentUpload.getLooper()!=null){
                accidentUpload.getLooper().quit();
            }
        }
        checkRecordUpload = null;
        violationUpload = null;
        checkRecordUpload = null;

    }




}
