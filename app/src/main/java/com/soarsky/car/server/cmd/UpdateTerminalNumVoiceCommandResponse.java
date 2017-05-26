package com.soarsky.car.server.cmd;

import com.soarsky.car.uitl.ByteUtil;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/4/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class UpdateTerminalNumVoiceCommandResponse extends BaseCmd{

    private  int  requestCode=-1;

    public  boolean isSucess(){
        return requestCode==0;
    }

    @Override
    public byte[] toMsgBytes() {
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {
        if(bytes.length>=23){
            requestCode= ByteUtil.byte1ToInt(bytes[23]);
        }
    }
}
