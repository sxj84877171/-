package com.soarsky.car.server.cmd;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/11/30
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 故障查询请求协议封装
 */

public class TroubleCommandRequest extends BaseCmd {
    private static final int TROUBLE_CHECK = 8;//故障查询

    private String carNum;//车牌号
    private int msgType=0;//命令类型 0 查询 1 应答
    private String msgStatus;//状态
    public TroubleCommandRequest() {
        setMsgId(TROUBLE_CHECK);
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

    /**
     *故障查询消息体
     */
    private String  message;
    public  void roundTroubleMsg(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(carNum);
        stringBuilder.append(msgType);
        message=stringBuilder.toString();
        byte[] bytes = message.getBytes();
        int lenth = bytes.length;
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
