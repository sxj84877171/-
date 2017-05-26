package com.soarsky.car.server.cmd;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2017/1/5<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为请人移车请求协议封装<br>
 */


public class MoveCarCommandRequest extends BaseCmd {

    private static final int MoveCar = 10;
    /**
     * 车牌号
     */
    private String carNum;
    /**
     * 消息类型
     */
    private String messageType = "1";
    /**
     * 手机号码
     */
    private String phone;

    public MoveCarCommandRequest() {
        setMsgId(MoveCar);
    }

    private byte[] message;

    /**
     * 请求数据封装
     */
    public void roundMoveCarMsg() {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            buffer.write(carNum.getBytes());
            buffer.write(toAgreementPhone(phone).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }




        message = buffer.toByteArray();
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


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
