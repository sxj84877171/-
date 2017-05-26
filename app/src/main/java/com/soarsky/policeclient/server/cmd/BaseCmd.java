package com.soarsky.policeclient.server.cmd;

import com.soarsky.policeclient.server.design.ICommand;
import com.soarsky.policeclient.uitl.ByteUtil;
import com.soarsky.policeclient.uitl.LogUtil;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/26<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
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

    public int getMsgId() {
        return msgId;
    }

    /**
     * 消息体属性
     */
    private int msgProperty;

    /**
     * 设备ID
     */
    private int deviceId = 03;

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
     * @param length
     */
    public void setMsgLength(int length) {
        length = length & LENGTH;
        msgProperty = msgProperty & (~LENGTH) ;
        msgProperty = msgProperty | length;
    }

    /**
     * 获取消息体长度
     * @return
     */
    public int getMsgLength(){
        int length = msgProperty & LENGTH ;
        return length ;
    }

    /**
     * 解析字节数组的长度
     * @param bytes 字节数组
     * @param a 起始位置
     * @param b 结束位置
     * @return 长度
     */
    public static int parseLength(byte[] bytes,int a,int b){

        int val = 0 ;
        int t = bytes[a] & 0xff;
        val = val | (t << 8);
        t = bytes[b]  & 0xff;
        val = val | t ;
        return val;

    }
    /**
     * 当第16位为1，表示消息体经过AES算法加密
     */
    public void setAESEncryption() {
        msgProperty = msgProperty | AES;
    }

    /**
     * 是否是AEX加密数据包
     * @return 是否加密
     */
    public boolean isAESEncryption() {
        return (msgProperty & AES) != 0;
    }

    /**
     * 数据是否加密
     * @return 是否加密
     */
    public boolean isEncryption() {
        return (msgProperty & ENCRYPTION) != 0;
    }

    /**
     * 数据是否是长消息（是否分包）
     * @return 是否长消息
     */
    public boolean isSubcontract(){
        return (msgProperty & SUBCONTRACT) != 0;
    }

    /**
     * 设置消息为长消息
     */
    public void setSubcontract(){
        msgProperty = msgProperty | SUBCONTRACT ;
    }

    /**
     * 设置消息不为长消息
     */
    public void setUnSubcontract(){
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
        System.arraycopy(msgBd, 0, toBytes, mk.length + header.length,  msgBd.length);
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
    }

    public abstract byte[] toMsgBytes();

    public byte[] toCRCBytes() {
        return new byte[]{1,2};
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
     *  整形转换成字节
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
     * 解析消息ID
     * @param bytes
     * @return
     */
    public static int parseMsgId(byte[] bytes) {
        if(bytes.length > 2) {
            int val = 0;
            int t = bytes[1]  & 0xff;
            val = val | t << 8;
            t = bytes[2]  & 0xff;
            val = val | t;
            return val;
        }else{
            return -1 ;
        }
    }

    /**
     * 根据字节数组转换为特定的命令
     * @param bytes 字节数组
     * @return 特定的类型的命令
     */
    public static BaseCmd parseCmd(byte[] bytes){
        int msgId = parseMsgId(bytes) ;
        String msg = ByteUtil.bytearrayToHexString(bytes,bytes.length);
        LogUtil.e("msg",msg);
        LogUtil.e("msgId",msgId+"");
        if(msgId == 0x0803){
            ElectronicTicketCommandResponse electronicTicketCommandResponse = new ElectronicTicketCommandResponse();
            electronicTicketCommandResponse.setMsgId(803);
            electronicTicketCommandResponse.setMsgLength(parseLength(bytes,4,5));
            /*electronicTicketCommandResponse.parseState(bytes);
            electronicTicketCommandResponse.parseSeqId(bytes);*/
            electronicTicketCommandResponse.parseDrivingLicenseNumber(bytes);
            return electronicTicketCommandResponse;
        }else if(msgId== 0x0805){
            AccidentCommandResponse accidentCommandResponse = new AccidentCommandResponse();
            accidentCommandResponse.setMsgId(805);
            accidentCommandResponse.setMsgLength(parseLength(bytes,4,5));
            accidentCommandResponse.setContentLength(parseLength(bytes,23,24));
//            accidentCommandResponse
            byte[] bytes2 = new byte[bytes.length-10];
            System.arraycopy(bytes,10,bytes2,0,bytes.length-10);
            accidentCommandResponse.parseBytes(bytes2);
            return accidentCommandResponse;
        }
        return null;
    }

}
