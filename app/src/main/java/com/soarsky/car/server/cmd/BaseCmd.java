package com.soarsky.car.server.cmd;


import android.support.annotation.NonNull;

import com.soarsky.car.server.design.ICommand;
import com.soarsky.car.uitl.ByteUtil;
import com.soarsky.car.uitl.CRC16;
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

    public static final int HEADER_LENTH = 9;

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
        byte[] header = new byte[HEADER_LENTH];
        byte[] tempBytes = intToBytes2(msgId);
        header[0] = tempBytes[0];
        header[1] = tempBytes[1];

        tempBytes = intToBytes(msgProperty);
        header[2] = tempBytes[1];
        header[3] = tempBytes[2];
        header[4] = tempBytes[3];

        tempBytes = intToBytes2(deviceId);
        header[5] = tempBytes[0];
        header[6] = tempBytes[1];

        tempBytes = intToBytes2(msgSeq);
        header[7] = tempBytes[0];
        header[8] = tempBytes[1];

        return header;
//        return "010360400".getBytes();
    }

    public abstract byte[] toMsgBytes();

    public byte[] toCRCBytes() {
//        return new byte[0];
//        byte[] mk = toMarkBytes();
        byte[] header = toHeaderBytes();
        byte[] msgBd = toMsgBytes();
        byte[] toBytes = new byte[header.length + msgBd.length];
        System.arraycopy(header, 0, toBytes, 0, header.length);
        System.arraycopy(msgBd, 0, toBytes, header.length, msgBd.length);
        return CRC16.crcEncodeToBytes(toBytes);
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
     * int转字节
     */
    public static byte[] intToBytes(int value) {
        byte[] src = new byte[4];
        src[0] = (byte) ((value >> 24) & 0xFF);
        src[1] = (byte) ((value >> 16) & 0xFF);
        src[2] = (byte) ((value >> 8) & 0xFF);
        src[3] = (byte) (value & 0xFF);
        return src;
    }




    /**
     * int转字节
     */
    public static byte[] intToBytes2(int value) {
        byte[] src = new byte[2];
//        src[3] = (byte) ((value >> 24) & 0xFF);
//        src[2] = (byte) ((value >> 16) & 0xFF);
        src[0] = (byte) ((value >> 8) & 0xFF);
        src[1] = (byte) (value & 0xFF);
        return src;
    }




    /**
     * int转字节
     */
    public static byte[] intToBytes1(int value) {
        byte[] src = new byte[1];
//        src[3] = (byte) ((value >> 24) & 0xFF);
//        src[2] = (byte) ((value >> 16) & 0xFF);
//        src[0] = (byte) ((value >> 8) & 0xFF);
        src[0] = (byte) (value & 0xFF);
        return src;
    }
    /**
     * 校验数据协议头是否正确
     *
     * @param bytes
     * @return
     */
    public static boolean isVal(byte[] bytes) {
        return bytes[0] == 0x7e;
    }

    /**
     * 解析消息ID
     *
     * @param bytes
     * @return
     */
    public static int parseMsgId(byte[] bytes) {
        if (bytes.length > 2) {
            int val = 0;
            int t = bytes[1] & 0xFF;
            val = val | t << 8;
            t = bytes[2] & 0xFF;
            val = val | t;
            return val;
        } else {
            return -1;
        }
    }


    public static int getCmdHeaderLength() {
        return HEADER_LENTH + 1;
    }

    public static int indexOfMark(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == MARK) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 解析消息头中消息体的长度
     *
     * @param bytes
     * @return
     */
    public static int parseLength(byte[] bytes) {
        int val = 0;
        int t = bytes[4] & 0xff;
        val = val | (t << 8);
        t = bytes[5] & 0xff;
        val = val | t;
        return val;
    }

    /**
     * 解析消息体中消息体的长度
     *
     * @param bytes
     * @return
     */
    public static int parseLength(byte[] bytes,int index) {
        int val = 0;
        int t = bytes[index] & 0xff;
        val = val | (t << 8);
        t = bytes[index+1] & 0xff;
        val = val | t;
        return val;
    }




    public static boolean isEncryption(byte[] bytes) {
        if (bytes.length > 5) {
            return (bytes[5] & 1) != 0;
        }
        throw new RuntimeException("bytes length is wrong!");
    }


    public static BaseCmd parseCmd(byte[] bytes, int length) {

        int msgId = parseMsgId(bytes);

        LogUtil.i("msgId:" + msgId);

        LogUtil.i("checkresult:" + CRC16.checkCRC(bytes, length));

        if (msgId == CMD_RESPONED_DRIVER) {
            ConnectCarCommandResponse connectCarCommandResponse = getConnectCarCommandResponse(bytes);
            return connectCarCommandResponse;
        }

        if (msgId == CMD_RESOPONED_MODIFY_PHONE) {
            DearNumCommandResponse dearNumCommandResponse = getDearNumCommandResponse(bytes);
            return dearNumCommandResponse;
        }
        if (msgId == CMD_RESPONED_FAULT_CODE) {
            TroubleCommandResponse troubleCommandResponse = getTroubleCommandResponse(bytes);
            return troubleCommandResponse;
        }
        if (msgId == CMD_RESPONED_READ_TICKET) {
            TicketCommandResponse ticketCommandResponse = getTicketCommandResponse(bytes, length);
            return ticketCommandResponse;
        }
        if (msgId == CMD_RESPONED_READ_DATA) {
            ReadDataCommandResponse readDataCommandResponse = getReadDataCommandResponse(bytes);
            return readDataCommandResponse;
        }
        if (msgId == CMD_RESPONED_PLAY_PHONE) {
            DangerousDrivingCommandResponse dangerousDrivingCommandResponse = getDangerousDrivingCommandResponse(bytes);
            return dangerousDrivingCommandResponse;
        }
        if (msgId == CMD_RESPONED_MUITE) {
            VolumeCommandResponse volumeCommandResponse = getVolumeCommandResponse(bytes);
            return volumeCommandResponse;
        }
        if (msgId == CMD_RESPONED_PLEASE_MOVE_CAR) {
            MoveCarCommandResponse moveCarCommandResponse = getMoveCarCommandResponse(bytes);
            return moveCarCommandResponse;
        }
        if(msgId==CMD_RESPOND_CHECKALIVE){
            CheckDriverAliveCommandResponse checkDriverAliveCommandResponse=getCheckDriverAliveCommandResponse(bytes);
            return checkDriverAliveCommandResponse;
        }

        if(msgId==CMD_RESPONED_SYNCTIME){

            SyncTimeCommandResponse syncTimeCommandResponse=getSyncTimeCommandResponse(bytes);
            return syncTimeCommandResponse;
        }

        if(msgId==CMD_RESPONED_UPDATEMESSAGE){

            TerminalUpdateCommandResponse terminalUpdateCommandResponse=getTerminalUpdateCommandResponse(bytes);
            return terminalUpdateCommandResponse;
        }

        if(msgId==CMD_RESPONED_TRANSFERPACKAGE){

            TerminalTransferDatapackageCommandResponse terminalTransferDatapackageCommandResponse=getTerminalTransferDatapackageCommandResponse(bytes);
            return terminalTransferDatapackageCommandResponse;
        }


        if(msgId==CMD_RESPONED_UPDATEPARAM){
            UpdateTerminalNumCommanResponse updateTerminalNumCommanResponse=getUpdateTerminalNumCommanResponse(bytes);
            return  updateTerminalNumCommanResponse;
        }

        if(msgId==CMD_RESPONED_UPDATECODEVOICE){
            UpdateTerminalNumVoiceCommandResponse updateTerminalNumVoiceCommandResponse=getUpdateTerminalNumVoiceCommandResponse(bytes);
            return updateTerminalNumVoiceCommandResponse;
        }


        if(msgId==CMD_RESPONED_TICKETCOUNT){
            TicketCountCommadnResponse ticketCountCommadnResponse=getTicketCountCommadnResponse(bytes);
            return ticketCountCommadnResponse;
        }

        if(msgId==CMD_RESPONED_EXITDRIVER){
            DriverLeaveCommandResponse driverLeaveCommandResponse=getExitDriverCommadnResponse(bytes);
            return driverLeaveCommandResponse;
        }
        return null;
    }

    /**
     * @param bytes
     * @return
     */
    @NonNull
    private static ConnectCarCommandResponse getConnectCarCommandResponse(byte[] bytes) {
        ConnectCarCommandResponse connectCarCommandResponse = new ConnectCarCommandResponse();
        connectCarCommandResponse.setMsgId(CMD_RESPONED_DRIVER);
        connectCarCommandResponse.setMsgLength(parseLength(bytes));
        connectCarCommandResponse.parseState(bytes);
        connectCarCommandResponse.parseSeqId(bytes);
        return connectCarCommandResponse;
    }


    /**
     * @param bytes
     * @return
     */
    @NonNull
    private static DearNumCommandResponse getDearNumCommandResponse(byte[] bytes) {
        DearNumCommandResponse dearNumCommandResponse = new DearNumCommandResponse();
        dearNumCommandResponse.setMsgId(CMD_RESOPONED_MODIFY_PHONE);
        dearNumCommandResponse.setMsgLength(parseLength(bytes));
        dearNumCommandResponse.parseBytes(bytes);
        return dearNumCommandResponse;
    }


    /**
     * @param bytes
     * @return
     */
    @NonNull
    private static TroubleCommandResponse getTroubleCommandResponse(byte[] bytes) {
        TroubleCommandResponse troubleCommandResponse = new TroubleCommandResponse();
        troubleCommandResponse.setMsgId(CMD_RESPONED_FAULT_CODE);
        troubleCommandResponse.setMsgLength(parseLength(bytes));
        troubleCommandResponse.parseBytes(bytes);
        return troubleCommandResponse;
    }


    @NonNull
    private static TicketCommandResponse getTicketCommandResponse(byte[] bytes, int length) {
        TicketCommandResponse ticketCommandResponse = new TicketCommandResponse();
        ticketCommandResponse.setMsgId(CMD_RESPONED_READ_TICKET);
        ticketCommandResponse.setMsgLength(parseLength(bytes));
        ticketCommandResponse.paresBytes2(bytes, length);
        return ticketCommandResponse;
    }


    @NonNull
    private static ReadDataCommandResponse getReadDataCommandResponse(byte[] bytes) {
        ReadDataCommandResponse readDataCommandResponse = new ReadDataCommandResponse();
        readDataCommandResponse.setMsgId(CMD_RESPONED_READ_DATA);
        readDataCommandResponse.setMsgLength(parseLength(bytes));
        readDataCommandResponse.parseBytes(bytes);
        return readDataCommandResponse;
    }

    @NonNull
    private static VolumeCommandResponse getVolumeCommandResponse(byte[] bytes) {
        VolumeCommandResponse volumeCommandResponse = new VolumeCommandResponse();
        volumeCommandResponse.setMsgId(CMD_RESPONED_MUITE);
        volumeCommandResponse.setMsgLength(parseLength(bytes));
        volumeCommandResponse.parseBytes(bytes);
        return volumeCommandResponse;
    }


    @NonNull
    private static DangerousDrivingCommandResponse getDangerousDrivingCommandResponse(byte[] bytes) {
        DangerousDrivingCommandResponse dangerousDrivingCommandResponse = new DangerousDrivingCommandResponse();
        dangerousDrivingCommandResponse.setMsgId(CMD_RESPONED_PLAY_PHONE);
        dangerousDrivingCommandResponse.setMsgLength(parseLength(bytes));
        dangerousDrivingCommandResponse.parseBytes(bytes);
        return dangerousDrivingCommandResponse;
    }


    @NonNull
    private static MoveCarCommandResponse getMoveCarCommandResponse(byte[] bytes) {
        MoveCarCommandResponse moveCarCommandResponse = new MoveCarCommandResponse();
        moveCarCommandResponse.setMsgId(CMD_RESPONED_PLEASE_MOVE_CAR);
        moveCarCommandResponse.setMsgLength(parseLength(bytes));
        moveCarCommandResponse.parseBytes(bytes);
        return moveCarCommandResponse;
    }


    @NonNull
    private  static CheckDriverAliveCommandResponse getCheckDriverAliveCommandResponse(byte[]bytes){
        CheckDriverAliveCommandResponse checkDriverAliveCommandResponse=new CheckDriverAliveCommandResponse();
        checkDriverAliveCommandResponse.setMsgId(CMD_RESPOND_CHECKALIVE);
        checkDriverAliveCommandResponse.setMsgLength(parseLength(bytes));
        checkDriverAliveCommandResponse.parseBytes(bytes);
        return checkDriverAliveCommandResponse;


    }

    @NonNull
    private  static SyncTimeCommandResponse getSyncTimeCommandResponse(byte[]bytes){
        SyncTimeCommandResponse syncTimeCommandResponse=new SyncTimeCommandResponse();
        syncTimeCommandResponse.setMsgId(CMD_RESPOND_CHECKALIVE);
        syncTimeCommandResponse.setMsgLength(parseLength(bytes));
        syncTimeCommandResponse.parseBytes(bytes);
        return syncTimeCommandResponse;
    }

    @NonNull
    private  static TerminalUpdateCommandResponse getTerminalUpdateCommandResponse(byte[]bytes){
        TerminalUpdateCommandResponse terminalUpdateCommandResponse=new TerminalUpdateCommandResponse();
        terminalUpdateCommandResponse.setMsgId(CMD_RESPONED_UPDATEMESSAGE);
        terminalUpdateCommandResponse.setMsgLength(parseLength(bytes));
        terminalUpdateCommandResponse.parseBytes(bytes);
        return terminalUpdateCommandResponse;
    }

    @NonNull
    private  static TerminalTransferDatapackageCommandResponse getTerminalTransferDatapackageCommandResponse(byte[]bytes){
        TerminalTransferDatapackageCommandResponse terminalTransferDatapackageCommandResponse=new TerminalTransferDatapackageCommandResponse();
        terminalTransferDatapackageCommandResponse.setMsgId(CMD_RESPONED_TRANSFERPACKAGE);
        terminalTransferDatapackageCommandResponse.setMsgLength(parseLength(bytes));
        terminalTransferDatapackageCommandResponse.parseBytes(bytes);
        return terminalTransferDatapackageCommandResponse;
    }


    @NonNull
    private  static UpdateTerminalNumCommanResponse getUpdateTerminalNumCommanResponse(byte[]bytes){
        UpdateTerminalNumCommanResponse updateTerminalNumCommanResponse=new UpdateTerminalNumCommanResponse();
        updateTerminalNumCommanResponse.setMsgId(CMD_RESPONED_UPDATEPARAM);
        updateTerminalNumCommanResponse.setMsgLength(parseLength(bytes));
        updateTerminalNumCommanResponse.parseBytes(bytes);
        return updateTerminalNumCommanResponse;
    }

    @NonNull
    private  static UpdateTerminalNumVoiceCommandResponse getUpdateTerminalNumVoiceCommandResponse(byte[]bytes){
        UpdateTerminalNumVoiceCommandResponse updateTerminalNumVoiceCommandResponse=new UpdateTerminalNumVoiceCommandResponse();
        updateTerminalNumVoiceCommandResponse.setMsgId(CMD_RESPONED_UPDATECODEVOICE);
        updateTerminalNumVoiceCommandResponse.setMsgLength(parseLength(bytes));
        updateTerminalNumVoiceCommandResponse.parseBytes(bytes);
        return updateTerminalNumVoiceCommandResponse;
    }


    @NonNull
    private  static TicketCountCommadnResponse getTicketCountCommadnResponse(byte[]bytes){
        TicketCountCommadnResponse ticketCountCommadnResponse=new TicketCountCommadnResponse();
        ticketCountCommadnResponse.setMsgId(CMD_RESPONED_TICKETCOUNT);
        ticketCountCommadnResponse.setMsgLength(parseLength(bytes));
        ticketCountCommadnResponse.parseBytes(bytes);
        return ticketCountCommadnResponse;
    }



    @NonNull
    private  static DriverLeaveCommandResponse getExitDriverCommadnResponse(byte[]bytes){
        DriverLeaveCommandResponse driverLeaveCommandResponse=new DriverLeaveCommandResponse();
        driverLeaveCommandResponse.setMsgId(CMD_RESPONED_EXITDRIVER);
        driverLeaveCommandResponse.setMsgLength(parseLength(bytes));
        driverLeaveCommandResponse.parseBytes(bytes);
        return driverLeaveCommandResponse;
    }



    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }


    public String coverBytesToString(byte[] bytes, int start, int end) {
        int send = start;
        for (int i = start; i < end; i++) {
            send = i + 1;
            if (bytes[i] == 0x0) {
                break;
            }
        }
        return new String(bytes, start, send);
    }

    public String coverBytesToNumString(byte[] bytes, int start, int end) {
        int send = start;
        for (int i = start; i < end; i++) {
            send = i + 1;
            if (bytes[i] < 0x30 || bytes[i] > 0x46) {
                break;
            }
        }
        return new String(bytes, start, send);
    }






    public String  toAgreementPhone(String carNum){
        int  phoneLength=carNum.length();
        if(phoneLength>16){
            return carNum.substring(0,16);
        }

        if(phoneLength==16){
            return carNum;
        }
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(carNum);
        for (int i=0;i<16-carNum.length();i++){
            stringBuilder.append("F");
        }
        return stringBuilder.toString();
    }
}
