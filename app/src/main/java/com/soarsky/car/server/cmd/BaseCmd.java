package com.soarsky.car.server.cmd;


import android.support.annotation.NonNull;

import com.soarsky.car.server.design.ICommand;
import com.soarsky.car.uitl.ByteUtil;
import com.soarsky.car.uitl.LogUtil;

/**
 * 车主APP
 * 作者： 孙向锦
 * 时间： 8/18/2016
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 基本数据包<br/>
 * 每条消息由标识位、消息头、消息体和校验码组成</br>
 * 标识位</br>
 * 采用0X7E表示</br>
 */
public abstract class BaseCmd implements ICommand {

    public BaseCmd() {
        msgProperty = 0x00;
    }

    /**
     * 校验码
     */
    public static final byte MARK = 0x7E;
    /**
     * 标识位
     */
    private byte mark = MARK;

    /**
     * 校验码
     */
    private int crc;

    /**
     * 消息ID
     */
    private int msgId;

    /**
     * 消息体属性
     */
    private int msgProperty;

    /**
     * 设备ID
     */
    private int deviceId = 04;

    /**
     * 消息流水号
     */
    private int msgSeq;

    /**
     * 消息包封装项
     */
    private byte msgPkg;


    /**
     * 写入命令头，消息体的长度
     * java中int 是4个字节，我们只要取后面2个字节（消息头里面的长度只有2个字节）
     * 然后把消息属性的表示消息体长度的字节置0
     * 然后把长度属性写入到消息体属性中
     *
     * @param length
     */
    public void setMsgLength(int length) {
        length = length & LENGTH;
        msgProperty = msgProperty & (~LENGTH);
        msgProperty = msgProperty | length;
    }

    /**
     * 获取消息体长度
     *
     * @return
     */
    public int getMsgLength() {
        int length = msgProperty & LENGTH;
        return length;
    }

    /**
     * 当第16位为1，表示消息体经过AES算法加密
     */
    public void setAESEncryption() {
        msgProperty = msgProperty | AES;
    }

    /**
     * 是否是AEX加密数据包
     *
     * @return 是否加密
     */
    public boolean isAESEncryption() {
        return (msgProperty & AES) != 0;
    }

    /**
     * 数据是否加密
     *
     * @return 是否加密
     */
    public boolean isEncryption() {
        return (msgProperty & ENCRYPTION) != 0;
    }

    /**
     * 数据是否是长消息（是否分包）
     *
     * @return 是否长消息
     */
    public boolean isSubcontract() {
        return (msgProperty & SUBCONTRACT) != 0;
    }

    /**
     * 设置消息为长消息
     */
    public void setSubcontract() {
        msgProperty = msgProperty | SUBCONTRACT;
    }

    /**
     * 设置消息不为长消息
     */
    public void setUnSubcontract() {
        msgProperty = msgProperty & (~SUBCONTRACT);
    }


    /**
     * 根据消息，转换成字节，发送给智能终端
     *
     * @return
     */
    @Override
    public byte[] toBytes() {
        byte[] mk = toMarkBytes();
        byte[] header = toHeaderBytes();
        byte[] msgBd = toMsgBytes();
        byte[] crc = toCRCBytes();
        byte[] toBytes = new byte[mk.length + header.length + msgBd.length + crc.length];
        System.arraycopy(mk, 0, toBytes, 0, mk.length);
        System.arraycopy(header, 0, toBytes, mk.length, header.length);
        System.arraycopy(msgBd, 0, toBytes, mk.length + header.length, msgBd.length);
        System.arraycopy(crc, 0, toBytes, mk.length + header.length + msgBd.length, crc.length);
        return toBytes;
    }

    /**
     * 头消息组包，头消息包的固定长度为9个字节。
     * 1-2位表示消息id
     * 3-5位表示消息属性
     * 6-7位表示设备id
     * 8-9位表示消息序列号
     *
     * @return
     */
    public byte[] toHeaderBytes() {
        byte[] header = new byte[9];
        byte[] tempBytes = intToBytes(msgId);
        header[0] = tempBytes[1];
        header[1] = tempBytes[0];

        tempBytes = intToBytes(msgProperty);
        header[2] = tempBytes[2];
        header[3] = tempBytes[1];
        header[4] = tempBytes[0];

        tempBytes = intToBytes(deviceId);
        header[5] = tempBytes[1];
        header[6] = tempBytes[0];

        tempBytes = intToBytes(msgSeq);
        header[7] = tempBytes[1];
        header[8] = tempBytes[0];

        return header;
//        return "010360400".getBytes();
    }

    public abstract byte[] toMsgBytes();

    public byte[] toCRCBytes() {
        return new byte[0];
    }

    public byte[] toMarkBytes() {
        return new byte[]{mark};
    }


    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public void setMsgSeq(int msgSeq) {
        this.msgSeq = msgSeq;
    }

    /**
     * 计算CRC16校验
     */
    public static byte[] intToBytes(int value) {
        byte[] src = new byte[4];
        src[3] = (byte) ((value >> 24) & 0xFF);
        src[2] = (byte) ((value >> 16) & 0xFF);
        src[1] = (byte) ((value >> 8) & 0xFF);
        src[0] = (byte) (value & 0xFF);
        return src;
    }


    /**
     * 校验数据协议头是否正确
     * @param bytes
     * @return
     */
    public static boolean isVal(byte[] bytes) {
        return bytes[0] == 0x7e;
    }

    /**
     * 解析消息ID
     * @param bytes
     * @return
     */
    public static int parseMsgId(byte[] bytes) {
        if(bytes.length > 2) {
            int val = 0;
            int t = bytes[1];
            val = val | t << 8;
            t = bytes[2];
            val = val | t;
            return val;
        }else{
            return -1 ;
        }
    }



    /**
     * 解析消息头中消息体的长度
     * @param bytes
     * @return
     */
    public static int parseLength(byte[] bytes){
        int val = 0 ;
        int t = bytes[4];
        val = val | (t << 8);
        t = bytes[5];
        val = val | t ;
        return val;
    }

    public static boolean isEncryption(byte[] bytes){
        if(bytes.length > 5){
            return (bytes[5] & 1) != 0;
        }
        throw new RuntimeException("bytes length is wrong!");
    }


    public static BaseCmd parseCmd(byte[] bytes){
        int msgId = parseMsgId(bytes);

        LogUtil.i("msgId"+msgId);

     if(msgId == 801){
            ConnectCarCommandResponse connectCarCommandResponse = getConnectCarCommandResponse(bytes);
            return connectCarCommandResponse;
        }

        if(msgId == 803){
            DearNumCommandResponse dearNumCommandResponse=getDearNumCommandResponse(bytes);
            return dearNumCommandResponse;
        }
        if(msgId == 808){
            TroubleCommandResponse troubleCommandResponse=getTroubleCommandResponse(bytes);
            return troubleCommandResponse;
        }
        if(msgId == 802){
            TicketCommandResponse ticketCommandResponse=getTicketCommandResponse(bytes);
            return ticketCommandResponse;
        }
        if(msgId == 809){
            ReadDataCommandResponse readDataCommandResponse=getReadDataCommandResponse(bytes);
            return readDataCommandResponse;
        }
        if(msgId == 806){
            DangerousDrivingCommandResponse dangerousDrivingCommandResponse=getDangerousDrivingCommandResponse(bytes);
            return dangerousDrivingCommandResponse;
        }
        if(msgId == 805){
            VolumeCommandResponse volumeCommandResponse=getVolumeCommandResponse(bytes);
            return volumeCommandResponse;
        }
        return null;
    }

    /**
     *
     * @param bytes
     * @return
     */
    @NonNull
    private static ConnectCarCommandResponse getConnectCarCommandResponse(byte[] bytes) {
        ConnectCarCommandResponse connectCarCommandResponse = new ConnectCarCommandResponse();
        connectCarCommandResponse.setMsgId(801);
        connectCarCommandResponse.setMsgLength(parseLength(bytes));
        connectCarCommandResponse.parseState(bytes);
        connectCarCommandResponse.parseSeqId(bytes);
        return connectCarCommandResponse;
    }


    /**
     *
     * @param bytes
     * @return
     */
    @NonNull
    private static DearNumCommandResponse getDearNumCommandResponse(byte[] bytes) {
        DearNumCommandResponse dearNumCommandResponse = new DearNumCommandResponse();
        dearNumCommandResponse.setMsgId(803);
        dearNumCommandResponse.setMsgLength(parseLength(bytes));
        dearNumCommandResponse.parseBytes(bytes);
        return dearNumCommandResponse;
    }


    /**
     *
     * @param bytes
     * @return
     */
    @NonNull
    private static TroubleCommandResponse getTroubleCommandResponse(byte[] bytes) {
        TroubleCommandResponse troubleCommandResponse = new TroubleCommandResponse();
        troubleCommandResponse.setMsgId(808);
        troubleCommandResponse.setMsgLength(parseLength(bytes));
        troubleCommandResponse.parseBytes(bytes);
        return troubleCommandResponse;
    }


    @NonNull
    private static TicketCommandResponse getTicketCommandResponse(byte[] bytes) {
        TicketCommandResponse ticketCommandResponse = new TicketCommandResponse();
        ticketCommandResponse.setMsgId(802);
        ticketCommandResponse.setMsgLength(parseLength(bytes));
        ticketCommandResponse.parseBytes(bytes);
        return ticketCommandResponse;
    }


    @NonNull
    private static ReadDataCommandResponse getReadDataCommandResponse(byte[] bytes) {
        ReadDataCommandResponse readDataCommandResponse = new ReadDataCommandResponse();
        readDataCommandResponse.setMsgId(809);
        readDataCommandResponse.setMsgLength(parseLength(bytes));
        readDataCommandResponse.parseBytes(bytes);
        return readDataCommandResponse;
    }

    @NonNull
    private static VolumeCommandResponse getVolumeCommandResponse(byte[] bytes) {
        VolumeCommandResponse volumeCommandResponse = new VolumeCommandResponse();
        volumeCommandResponse.setMsgId(805);
        volumeCommandResponse.setMsgLength(parseLength(bytes));
        volumeCommandResponse.parseBytes(bytes);
        return volumeCommandResponse;
    }


    @NonNull
    private static DangerousDrivingCommandResponse getDangerousDrivingCommandResponse(byte[] bytes) {
        DangerousDrivingCommandResponse dangerousDrivingCommandResponse = new DangerousDrivingCommandResponse();
        dangerousDrivingCommandResponse.setMsgId(809);
        dangerousDrivingCommandResponse.setMsgLength(parseLength(bytes));
        dangerousDrivingCommandResponse.parseBytes(bytes);
        return dangerousDrivingCommandResponse;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
}
