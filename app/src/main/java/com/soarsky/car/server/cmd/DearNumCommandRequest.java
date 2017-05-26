package com.soarsky.car.server.cmd;

import android.content.Context;

import com.soarsky.car.App;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/2<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 同步亲情号请求协议封装<br>
 */

public class DearNumCommandRequest extends BaseCmd {
    private static final int DEAR_NUM = 03;
    /**
     * 车牌号
     */
    private String carNum;
    /**
     * 车主手机号
     */
    private String dearNum;
    /**
     * 亲情号
     */
    private String dearNum1;
    /**
     * 授权码11235813213
     */
    private String authCode;


    private App app;

    public DearNumCommandRequest() {
        app = App.getApp();
        setMsgId(DEAR_NUM);
    }


    private String message;

    /**
     * 亲情号码消息体
     */
    public void roundDearNumMsg() {
        StringBuilder stringBuilder = new StringBuilder();

        if (app.getCommonParam() != null) {
            stringBuilder.append(carNum);
            stringBuilder.append(app.getCommonParam().getIdNo());
        }
        stringBuilder.append(authCode);
        stringBuilder.append(toAgreementPhone(dearNum));
        stringBuilder.append(dearNum1);
        message = stringBuilder.toString();
        byte[] bytes = message.getBytes();
        int lenth = bytes.length;
        setMsgLength(lenth);
        setMsgSeq(MessageSeq.getInstance().getMessageSeq());
        setUnSubcontract();
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


    public void setDearNum1(String dearNum1) {
        this.dearNum1 = dearNum1;
    }

    public void setDearNum(String dearNum) {
        this.dearNum = dearNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
