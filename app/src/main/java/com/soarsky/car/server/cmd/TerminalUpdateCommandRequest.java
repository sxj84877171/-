package com.soarsky.car.server.cmd;

import com.google.gson.Gson;
import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.bean.TermDataBean;
import com.soarsky.car.uitl.SpUtil;
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


public class TerminalUpdateCommandRequest extends BaseCmd {
    private App app;

    public String termData = "";


    public TerminalUpdateCommandRequest() {
        setMsgId(07);
        app = App.getApp();
        SpUtil.init(app);
        termData = SpUtil.get(Constants.TermData);
        carNum = app.getCarNum();
    }


    /**
     * 车牌号
     */
    private String carNum;


    /**
     * 数据长度
     */

    private int length=0;

    private byte[] bLength;

    private byte[] message;


    /**
     * 启用日期
     */

    private  String   startDate="20170418134545";
    /**
     * taskid
     */
    private String taskId="1234567813";

    public void rundTerminalUpdateMessage() {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        //数据长度

        byte[] bytes=TerminalUpdateUtil.toBytes(Constants.TermSrc);
        if(null!=bytes){
            length = TerminalUpdateUtil.toBytes(Constants.TermSrc).length;
        }
        bLength = intToBytes(length);
        //版本号
        String verCode = "";
        int crc = 0;
        if (termData != null && !(termData.equals(""))) {
            Gson gson = new Gson();
            TermDataBean bean = gson.fromJson(termData, TermDataBean.class);
            verCode = bean.getParams().getVer();
            taskId=bean.getParams().getTaskid()+"";
            crc = Integer.parseInt(bean.getParams().getCrc());
        }

        StringBuilder sb=new StringBuilder();
        if(taskId.length()<10){
            for (int  i=0;i<10-(taskId.length());i++){
               sb.append("F");
            }
            sb.append(taskId);
            taskId=sb.toString();
        }



        //版本号长度
        int verCodeLength = verCode.length();
        try {
            buffer.write(carNum.getBytes());
            buffer.write(bLength);
            buffer.write(intToBytes2(crc));
            buffer.write(intToBytes1(verCodeLength));
            buffer.write(verCode.getBytes());
            buffer.write(startDate.getBytes());
            buffer.write(taskId.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        message = buffer.toByteArray();
        int lenth = message.length;
        setMsgLength(lenth);
        setMsgSeq(MessageSeq.getInstance().getMessageSeq());
        setUnSubcontract();
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
