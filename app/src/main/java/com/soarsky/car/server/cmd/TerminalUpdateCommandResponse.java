package com.soarsky.car.server.cmd;

import com.soarsky.car.App;
import com.soarsky.car.uitl.ByteUtil;

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


public class TerminalUpdateCommandResponse extends BaseCmd{


    public  boolean hasUpdate(){
        return responseCode==0;
    }

    /**
     * 0x00：成功/确认；
     0x01：车牌号不对；
     0x02：驾照号不对；
     0x03：消息有误；
     0x04: 授权码错误;
     0x05：不支持

     */
    private  int  responseCode=-1;
    /**
     * 下针传送的升级包编号
     */
    private  int  nextPackageNum;

    /**
     * 分包长度
     */
    private int packageLength;
    /**
     * 版本号
     */
    private  String  ver;

    /**
     * 版本串长
     */

    private  int  verLength;

    private String taskId;

    private  String  terminalId;

    @Override
    public byte[] toMsgBytes() {
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {


        if(bytes.length>23){
            responseCode= ByteUtil.byte1ToInt(bytes[23]);
        }
        nextPackageNum=parseLength(bytes,26);
        packageLength=parseLength(bytes,24);
        verLength=ByteUtil.byte1ToInt(bytes[28]);
        packageLength=parseLength(bytes,24);
        byte[]verbyte=new byte[verLength];
        System.arraycopy(bytes,29,verbyte,0,verLength);
        ver=new String(verbyte);
        byte[]taskbyte=new byte[10];
        System.arraycopy(bytes,29+verLength,taskbyte,0,10);
        taskId=new String(taskbyte).replace("F"," ").trim();
        byte[]terminalbyte=new byte[18];
        System.arraycopy(bytes,39+verLength,terminalbyte,0,18);
        terminalId=new String(terminalbyte);
        App.getApp().setTerminalVer(ver);
    }

    public int getNextPackageNum() {
        return nextPackageNum;
    }


    public int getPackageLength() {
        return packageLength;
    }


    public int getResponseCode() {
        return responseCode;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getTerminalId() {
        return terminalId;
    }
}
