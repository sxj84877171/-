package com.soarsky.car.server.cmd;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/30<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 设置声音消息应答解析<br>
 */

public class VolumeCommandResponse extends  BaseCmd{
    /**
     * 结果 0 确认 1 失败 2消息有误 3 不支持
     */

    private int result=-1;

    @Override
    public byte[] toMsgBytes() {
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {
        if(bytes.length>23){
            result=bytes[23];
        }
    }

    public int getResult() {
        return result;
    }
}
