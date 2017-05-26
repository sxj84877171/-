package com.soarsky.car.server.cmd;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/30<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 故障查询请求协议封装<br>
 */

public class TroubleCommandRequest extends BaseCmd {
    private static final int TROUBLE_CHECK = 8;//故障查询

    private String carNum="";//车牌号
    private int msgType=0;//命令类型 0 查询 1 应答
    private String msgStatus;//状态
    public TroubleCommandRequest() {
        setMsgId(TROUBLE_CHECK);
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

    /**
     *故障查询消息体
     */
    private byte[]  message;
    public  void roundTroubleMsg(){

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            buffer.write(carNum.getBytes());
            buffer.write(intToBytes(msgType));
        } catch (Exception e) {
            e.printStackTrace();
        }

        message=buffer.toByteArray();
        int lenth = message.length;
        setMsgLength(lenth);
        setMsgSeq(MessageSeq.getInstance().getMessageSeq());
        setUnSubcontract();
    }


    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getMsgStatus() {
        return msgStatus;
    }

    public void setMsgStatus(String msgStatus) {
        this.msgStatus = msgStatus;
    }
}
