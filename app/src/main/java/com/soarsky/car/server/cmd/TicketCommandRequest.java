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
 * 该类为 读取罚单请求协议封装
 */

public class TicketCommandRequest extends BaseCmd{
    /**
     *车牌号
     */
    private String carNum;
    /**
     * 消息类型 1 未读罚单 2所有罚单 3返回违章数据处理状态
     */
    private int type;
    /**
     * 罚单编号 读取类型为3时才有此内容
     */
    private String tickerNo;

    /**
     * 0 未已上传  1已上传 读取类型为3时才有此内容
     */
    private  int status;

    private static final int TICKET = 02;//确认驾驶员
    public TicketCommandRequest() {
        setMsgId(TICKET);
    }

    @Override
    public byte[] toMsgBytes() {

        if(message!=null){
            return message.getBytes();
        }
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {

    }

    /**
     * 罚单消息体
     */
    private String message;
    public  void   roundTicketMsg(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(carNum);
        stringBuilder.append(type);
        if(type==3){
            stringBuilder.append(tickerNo);
            stringBuilder.append(status);
        }
        message=stringBuilder.toString();
        byte[] bytes = message.getBytes();
        int lenth = bytes.length;
        setMsgLength(lenth);
        setMsgSeq(MessageSeq.getInstance().getMessageSeq());
        setUnSubcontract();
    }


    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTickerNo(String tickerNo) {
        this.tickerNo = tickerNo;
    }
}
