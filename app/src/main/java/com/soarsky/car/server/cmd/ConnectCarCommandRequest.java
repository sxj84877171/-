package com.soarsky.car.server.cmd;


import android.content.Context;
import android.text.TextUtils;

import com.soarsky.car.App;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.TerminalUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/15<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 确认驾驶员请求协议封装<br>
 */

public class ConnectCarCommandRequest extends  BaseCmd{
    private static final int CONNECT_CAR = 01;//确认驾驶员
    private App app;
    public  ConnectCarCommandRequest(){
        setMsgId(CONNECT_CAR);
        app= App.getApp();

    }

    private String carNum ;
    /**
     * 申请类型
     */
    private byte type ;
    /**
     * 驾照类型(11)
     */
    private String driverType ;

    private String authCode;



    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    private String correntLength(int len) {
        StringBuilder sb = new StringBuilder();
        String strLen = "" + len;

        for (int i = strLen.length(); i < 3; i++) {
            sb.append("0");
        }
        sb.append(len);
        return sb.toString();

    }

    private byte[] message;

    /**
     * 确认驾驶员消息体
     */



    public void roundConnectCarMsg() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            buffer.write(carNum.getBytes());
            buffer.write(type);
            buffer.write(toAgreement().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        message=buffer.toByteArray();
        int lenth = message.length;
        setMsgLength(lenth);
        setMsgSeq(MessageSeq.getInstance().getMessageSeq());
        setUnSubcontract();
    }


    private String toAgreement(){
        StringBuilder stringBuilder=new StringBuilder();
        if(TextUtils.isEmpty(app.getCommonParam().getDrivingType())){
            stringBuilder.append(11);
        }else{
            stringBuilder.append(TerminalUtils.toCarType(app.getCommonParam().getDrivingType()));//驾照类型
        }
        stringBuilder.append(app.getCommonParam().getIdNo());//驾驶证好
        if(type==1||type==3){
            stringBuilder.append(1);
            stringBuilder.append(toAgreementPhone(app.getCommonParam().getOwerPhone()));//亲情号
        }
        else if(type==2||type==4){
            stringBuilder.append(0);
            stringBuilder.append(authCode);//授权码
        }else{
            stringBuilder.append(2);
            stringBuilder.append(toAgreementPhone(app.getCommonParam().getOwerPhone()));//本人电话
        }
        return stringBuilder.toString();

    }




    @Override
    public byte[] toMsgBytes() {
        if (message != null) {
            return message;
        }
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {

    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
