package com.soarsky.car.server.cmd;


import android.content.Context;

import com.soarsky.car.App;


/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/11/15
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 确认驾驶员请求协议封装
 */

public class ConnectCarCommandRequest extends  BaseCmd{
    private static final int CONNECT_CAR = 01;//确认驾驶员
    private App app;
    public  ConnectCarCommandRequest(Context context){
        setMsgId(CONNECT_CAR);
        app= (App) context.getApplicationContext();

    }

    private String carNum ;
    /**
     * 申请类型
     */
    private int type ;
    /**
     * 驾照类型(11)
     */
    private String driverType ;





    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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

    private String message;

    /**
     * 确认驾驶员消息体
     */
    public void roundConnectCarMsg() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(carNum);
        stringBuilder.append(type);
        stringBuilder.append(app.getCommonParam().getDrivingType());//驾照类型
        stringBuilder.append(app.getCommonParam().getIdNo());//驾驶证好
        if(type==1||type==3){
            stringBuilder.append(1);
            stringBuilder.append(app.getCommonParam().getOwerPhone());//亲情号
        }
        else if(type==2||type==4){
            stringBuilder.append(0);
            stringBuilder.append(app.getCommonParam().getAuthCode());//授权码
        }
        message = stringBuilder.toString();
        byte[] bytes = message.getBytes();
        int lenth = bytes.length;
        setMsgLength(lenth);
        setMsgSeq(MessageSeq.getInstance().getMessageSeq());
        setUnSubcontract();
    }




    @Override
    public byte[] toMsgBytes() {
        if (message != null) {
            return message.getBytes();
        }
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {

    }
}
