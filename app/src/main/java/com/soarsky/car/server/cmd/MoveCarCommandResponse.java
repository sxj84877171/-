package com.soarsky.car.server.cmd;

import com.soarsky.car.uitl.ByteUtil;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2017/1/5<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 请人移车请求协议封装放回协议封装<br>
 */


public class MoveCarCommandResponse extends  BaseCmd {




    /**
     * 应答类型0 已通知车主 1通信失败无法联系车主 2 没有配备sim卡
     */
    private int responseType;


    @Override
    public byte[] toMsgBytes() {
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {

        if(bytes.length>22){
            responseType= bytes[23];
        }
    }


    public int getResponseType() {
        return responseType;
    }


}
