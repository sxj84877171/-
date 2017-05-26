package com.soarsky.car.server.cmd;

import com.soarsky.car.App;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/4/13
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class CheckDriverAliveCommandRequest extends BaseCmd{

    private App app;

    public CheckDriverAliveCommandRequest() {
        setMsgId(0x000D);
        this.app = App.getApp();
    }

    private  byte[] message;
    public void roundCheckDriverAliveMsg() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            buffer.write(app.getCarNum().getBytes());
            buffer.write(app.getCommonParam().getIdNo().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        message=buffer.toByteArray();
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
}
