package com.soarsky.car.server.cmd;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.uitl.TerminalUpdateUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/4/19
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class TerminalTransferDatapackageCommandRequest extends BaseCmd{
    /**
     * 车牌号
     */
    private  String carNum;
    /**
     * 总包数
     */
    private  int  packageNum;

    /**
     * 升级包编号
     */
    private int  packageCode;

    /**
     * 升级包长度
     */
    private int packageLength;

    /**
     * 升级数据
     */
    private byte[]  packageContet;


    /**
     * 总数据流
     */
    private byte[]  buffer;

    public TerminalTransferDatapackageCommandRequest() {
        setMsgId(0x000E);
        buffer=TerminalUpdateUtil.toBytes(Constants.TermSrc);
        packageNum=TerminalUpdateUtil.getPackageNum(buffer);
        carNum= App.getApp().getCarNum();
    }


    private  byte[]message;
    public  void  roudTerminalTransferDatapackage(){
        packageContet=TerminalUpdateUtil.getpackContent(buffer,packageCode,packageNum);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try {
            buffer.write(carNum.getBytes());
            buffer.write(intToBytes2(packageNum));
            buffer.write(intToBytes2(packageCode));
//            buffer.write(intToBytes2(Constants.PACKAGE_LENGTH));
            buffer.write(packageContet);

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


    public void setPackageCode(int packageCode) {
        this.packageCode = packageCode;
    }
}
