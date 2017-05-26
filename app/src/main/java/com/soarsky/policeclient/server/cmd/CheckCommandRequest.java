package com.soarsky.policeclient.server.cmd;


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
 * 电子罚单请求类<br>
 * 向智能终端发送稽查命令<br>
 */
public class CheckCommandRequest extends BaseCmd {

    /**
     * 电子罚单指令
     */
    private static final int ELECTRONIC_TICKET = 0x0004;

    /**
     * 电子罚单指令
     */
    public CheckCommandRequest() {
        setMsgId(ELECTRONIC_TICKET);
    }


    /**
     * 电子罚单内容，根据电子罚单内容，组织电子罚单包
     * @param msg
     */
    public CheckCommandRequest(String msg) {
        this();
        setElectronicMsg(msg);
    }

    /**
     * 电子罚单内容，根据电子罚单内容，组织电子罚单包
     * @param carNum 车牌号码
     * @param length 内容长度
     * @param content 电子罚单内容
     */
    public CheckCommandRequest(String carNum, int length, String content) {
        setElectronicMsg(carNum + correntLength(length) + content);
    }

    /**
     * 电子罚单内容，根据电子罚单内容，组织电子罚单包
     * @param carNum 车牌号码
     * @param content 电子罚单内容
     */
    public CheckCommandRequest(String carNum, String content) {
        this();
        setElectronicMsg(carNum + content);
    }

    /**
     * 电子罚单内容长度，转换长固定长度3位
     * @param len
     * @return
     */
    private String correntLength(int len) {
        StringBuilder sb = new StringBuilder();
        String strLen = "" + len;

        for (int i = strLen.length(); i < 3; i++) {
            sb.append("0");
        }
        sb.append(len);
        return sb.toString();

    }

    /**
     * 电子罚单内容
     */
    private String message;

    /**
     * 电子罚单内容转换成字节数组
     * @return
     */
    @Override
    public byte[] toMsgBytes() {
        if (message != null) {
            return message.getBytes();
        }
        return new byte[0];
    }

    /**
     * 根据字节数组，转换成电子罚单内容
     * 目前没有这样的需求，暂时不实现
     * @param bytes
     */
    @Override
    public void parseBytes(byte[] bytes) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * 设置电子罚单内容，组织数据包
     * @param msg
     */
    private void setElectronicMsg(String msg) {
        message = msg;
        byte[] bytes = msg.getBytes();
        int lenth = bytes.length;
        setMsgLength(lenth);
        setMsgSeq(MessageSeq.getInstance().getMessageSeq());
        setUnSubcontract();
    }


}
