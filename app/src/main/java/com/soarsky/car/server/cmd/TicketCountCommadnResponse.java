package com.soarsky.car.server.cmd;

import com.soarsky.car.uitl.ByteUtil;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/5/2
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 读取罚单条数
 */


public class TicketCountCommadnResponse extends BaseCmd{



    private int  requestCode=-1;



    private   int TicketCount=0;





    @Override
    public byte[] toMsgBytes() {
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {
        if(bytes.length>25){
            requestCode= ByteUtil.byte1ToInt(bytes[23]);
            TicketCount=parseLength(bytes,25);
        }
    }


    public int getTicketCount() {
        return TicketCount;
    }



    public  boolean  isSucess(){
        return  requestCode==0;
    }
}
