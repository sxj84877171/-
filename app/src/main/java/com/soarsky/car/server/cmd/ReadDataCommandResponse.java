package com.soarsky.car.server.cmd;

import com.soarsky.car.uitl.LogUtil;

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
 * 该类为 读取终端消息应答解析
 */

public class ReadDataCommandResponse extends BaseCmd{
    /**
     * 应答命令
     * 01 成功 02 失败 03  消息有误 04 不支持 05 行驶异常数据 06 车况(用车记录) 07亲情号
     *
     */
    private String messageType;

    /**
     * 内容长度
     */
    private int  messageLength;
    /**
     * 内容体
     */
    private String  contentStr;

    /**
     * 罚单序号
     */
    private  String  TicketNo;

    @Override
    public byte[] toMsgBytes() {
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {

        StringBuilder stringBuilder=new StringBuilder();
        if(bytes.length>23){
            stringBuilder.append(bytes[23]);
            stringBuilder.append(bytes[24]);
        }
        messageType=stringBuilder.toString();
        if(bytes.length>27){
            messageLength=bytes[27];
            TicketNo=parseRecordNo(bytes);
            contentStr =new String(bytes,28,messageLength);
        }

        LogUtil.i("读取信息 messageType："+messageType+" contentStr:"+contentStr);

    }


    public String  getMessageType() {
        return messageType;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public String getContentStr() {
        return contentStr;
    }

    public String getTicketNo() {
        return TicketNo;
    }


    private String parseRecordNo(byte[] bytes){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(bytes[28]);
        stringBuilder.append(bytes[29]);
        stringBuilder.append(bytes[30]);
        stringBuilder.append(bytes[31]);
        return stringBuilder.toString();
    }



}
