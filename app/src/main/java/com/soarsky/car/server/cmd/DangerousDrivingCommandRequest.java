package com.soarsky.car.server.cmd;


import com.soarsky.car.uitl.TimeUtils;

import java.util.Date;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/2
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 危险驾驶请求协议的封装
 */

public class DangerousDrivingCommandRequest extends BaseCmd {
    private static final int DANGEROUSDRVING = 6;

    /**
     * 命令类型 00 玩手机 05 打电话
     */
    private String msgType;


    /**
     * 时间
     */
    private String time = "20161201121212";


    /**
     * 车牌号
     */
    private String carNum ;

    public DangerousDrivingCommandRequest() {
        setMsgId(DANGEROUSDRVING);
    }


    /**
     * 读取数据消息体
     */
    private String message;


    public  void roundDangerousDrivingMsg() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(carNum);
        stringBuilder.append(msgType);
        stringBuilder.append(TimeUtils.date2String2(new Date()));
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


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
}
