package com.soarsky.car.server.cmd;

import com.soarsky.car.uitl.ByteUtil;
import com.soarsky.car.uitl.LogUtil;


/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/16<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 确认驾驶员应答解析<br>
 */

public class ConnectCarCommandResponse extends BaseCmd {
    @Override
    public byte[] toMsgBytes() {
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {

    }

    private int seqId = 0 ;
//    0x01：没有使用权；
//    0x02：驾照类型不符合要求；
//    0x03：等待
//    0x04：不支持
    private int state = -1 ;//0x00：成功/确认；



    public void setSeqId(int seqId) {
        this.seqId = seqId;
    }

    public int getState() {
        return state;
    }

    public int getSeqId() {
        return seqId;
    }


    public boolean isDriver(){
        return state == 0 ;
    }

    public void parseState(byte[] bytes){
        LogUtil.i("------------------");
        if(bytes.length >= 23){
            //TODO 记得改回14
            state = ByteUtil.byte1ToInt(bytes[23]) ;
        }
        LogUtil.i(ByteUtil.bytearrayToHexString(bytes,bytes.length));
    }


    public void parseSeqId(byte[] bytes){
        seqId = 0 ;
        if(bytes.length >= 14){
            int t = 0 ;
            t = bytes[12] ;
            seqId = seqId | (t << 8);
            t = bytes[13] ;
            seqId = seqId | t ;
        }
    }




}
