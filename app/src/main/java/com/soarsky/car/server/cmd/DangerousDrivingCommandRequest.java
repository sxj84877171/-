package com.soarsky.car.server.cmd;


import com.soarsky.car.uitl.TimeUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/12/2<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 危险驾驶请求协议的封装<br>
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
    private String time ;


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
    private byte[] message;


    public  void roundDangerousDrivingMsg() {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            buffer.write(carNum.getBytes());
            buffer.write(intToBytes(Integer.parseInt(msgType)));
            buffer.write(TimeUtils.date2String2(new Date()).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }


        message = buffer.toByteArray();
        int lenth = message.length;
        setMsgLength(lenth);
        setMsgSeq(MessageSeq.getInstance().getMessageSeq());
        setUnSubcontract();

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
