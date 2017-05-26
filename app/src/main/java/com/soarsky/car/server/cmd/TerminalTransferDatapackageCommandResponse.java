package com.soarsky.car.server.cmd;

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


public class TerminalTransferDatapackageCommandResponse extends BaseCmd{

    private int  responseCode;
    /**
     * 下个包的编号
     */
    private int  nextPackageNum;

    /**
     * 终端id
     */
    private String  TerminalId="";

    public  boolean isFinish(){
        return  false;
    }

    /**
     * 任务id
     */
    private  String taskid="";

    /**
     * 版本号
     */

    private String  verCode;

    private int  verCodeLength=0;

    @Override
    public byte[] toMsgBytes() {
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {
        if(bytes.length>23){
            responseCode= ByteUtil.byte1ToInt(bytes[23]);
        }
        if(bytes.length>15){
            nextPackageNum=parseLength(bytes,24);
        }
        if(bytes.length>54){

            byte[] taskidbyte=new byte[10];
            byte[] idbyte=new byte[18];

            verCodeLength=ByteUtil.byte1ToInt(bytes[54]);
            byte[]verbyte=new byte[verCodeLength];
            System.arraycopy(bytes,36,idbyte,0,18);
            System.arraycopy(bytes,26,taskidbyte,0,10);
            System.arraycopy(bytes,55,verbyte,0,verCodeLength);
            TerminalId=new String(idbyte);
            taskid=new String(taskidbyte).replace("F","").trim();
            verCode=new String(verbyte);
        }

    }


    public int getResponseCode() {
        return responseCode;
    }

    public int getNextPackageNum() {
        return nextPackageNum;
    }


    public String getTerminalId() {
        return TerminalId;
    }


    public String getTaskid() {
        return taskid;
    }

    public String getVerCode() {
        return verCode;
    }
}
