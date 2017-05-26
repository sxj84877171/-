package com.soarsky.car.server.cmd;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/11/2
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 读取终端消息请求协议封装
 */

public class ReadDataCommandRequest extends BaseCmd {


    private static final int READ_MESSAGE = 9;
    /**
     * 命令类型 00 应答消息 01读取行驶异常数据 02读取车况 03 读取亲情号
     */
    private  String  msgType;

    /**
     * 车牌号
     */
    private String carNum;
    /**
     * 罚单编号 msgType为00时才有
     */
    private String TicketNo;

    /**
     * msgType为00时才有
     * 状态  0 未上传  1  已上传
     */
    private int  status;


    public ReadDataCommandRequest(){
        setMsgId(READ_MESSAGE);

    }

    private String message;
    public void roundConnectCarMsg() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(carNum);
        stringBuilder.append(msgType);
        if(msgType.equals("00")){
            stringBuilder.append(TicketNo);
            stringBuilder.append(status);
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
        if(message!=null){
            return  message.getBytes();
        }
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {

    }


    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public void setTicketNo(String ticketNo) {
        TicketNo = ticketNo;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
