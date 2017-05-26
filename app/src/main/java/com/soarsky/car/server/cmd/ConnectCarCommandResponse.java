package com.soarsky.car.server.cmd;

import com.soarsky.car.uitl.ByteUtil;
import com.soarsky.car.uitl.LogUtil;


/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/11/16
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 确认驾驶员应答解析
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

    private int state = -1 ;//0成功1没有使用权2驾照类型不匹配3等待4不支持

    public void setSeqId(int seqId) {
        this.seqId = seqId;
    }

    public int getState() {
        return state;
    }

    public int getSeqId() {
        return seqId;
    }

    public void setState(int state) {
        this.state = state;
    }

    public boolean isDriver(){
        return state == 0 ;
    }

    public void parseState(byte[] bytes){
        LogUtil.i("------------------");
        if(bytes.length >= 14){
            //TODO 记得改回14
            state = bytes[14] ;
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
