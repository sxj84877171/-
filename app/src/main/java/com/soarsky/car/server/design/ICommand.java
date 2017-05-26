package com.soarsky.car.server.design;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/11/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：通讯协议命令
 * 该类为 与终端通讯服务类
 */
public interface ICommand {
    final static int LENGTH = 0xFFFF;

    final static int AES = 0x00010000;

    final static int ENCRYPTION = 0x000F0000 ;

    final static int SUBCONTRACT = 0x00100000 ;


    public byte[] toBytes();

    public void parseBytes(byte[] bytes);
}
