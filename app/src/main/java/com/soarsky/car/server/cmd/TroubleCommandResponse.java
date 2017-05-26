package com.soarsky.car.server.cmd;


/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/11/30
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 故障查询应答解析
 */

public class TroubleCommandResponse extends  BaseCmd{

    private int state=-1;
    private String troubleStr;//故障信息


    @Override
    public byte[] toMsgBytes() {
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {
        if(bytes.length >= 23){
            state = bytes[23] ;
            troubleStr=new String(bytes,24,getMsgLength()+10-24);
        }
    }



    public boolean   hasTrouble(){
        return state==1;
    }

    public String  getTroubleStr(){
        return troubleStr;
    }

}
