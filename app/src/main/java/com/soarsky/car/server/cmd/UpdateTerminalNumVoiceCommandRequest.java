package com.soarsky.car.server.cmd;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/4/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class UpdateTerminalNumVoiceCommandRequest extends BaseCmd{
    /**
     * 车牌号
     */
    private  String carNum;

    /**
     * 请求内容
     */
    private  String content;

    public UpdateTerminalNumVoiceCommandRequest() {
        setMsgId(0x0012);
    }


    private byte[] message;
    public void rundUpdateTerminalNumCommanRequest(){
        String  str=carNum+content;
        message=str.getBytes();
        int lenth = message.length;
        setMsgLength(lenth);
        setMsgSeq(MessageSeq.getInstance().getMessageSeq());
        setUnSubcontract();


    }

    @Override
    public byte[] toMsgBytes() {
        if(message!=null){
            return message;
        }
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {

    }


    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
