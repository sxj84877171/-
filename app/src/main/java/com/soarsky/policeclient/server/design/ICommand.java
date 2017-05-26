package com.soarsky.policeclient.server.design;


/**
 * 警务通<br>
 * 作者： 孙向锦<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 通讯协议命令<br>
 * 数据类型	描述及要求<br>
 * BYTE	无符号单字节整型(字节，8位)<br>
 * WORD	无符号双字节整型(字，16位)<br>
 * DWORD	无符号四字节整型(双字，32位)<br>
 * BYTE[n]	n字节<br>
 * BCD[n]	8421码，n字节<br>
 * STRING	GBK编码，采用0终结符，若无数据，则放一个0终结符<br>
 * 传输规则：<br>
 * 协议采用大端模式(big-endian)的网络字节序来传递字和双字。<br>
 * 约定如下:<br>
 * ——字节 (BYTE)的传输约定:按照字节流的方式传输；<br>
 * ——字(WORD)的传输约定:先传递高八位，再传递低八位；<br>
 * ——双字(DWORD)的传输约定:先传递高24位，然后传递高16位，再传递高八位，<br>
 * 最后传递低八位。<br>
 * 每条消息由标识位、消息头、消息体和校验码组成，消息结构图如图1所示：<br>
 * 标识位	消息头	消息体	校验码<br>
 */
public interface ICommand {


    /**
     * 消息长度
     */
    final static int LENGTH = 0xFFFF;

    /**
     * 当第16位为1，表示消息体经过AES算法加密；
     */
    final static int AES = 0x00010000;

    /**
     * bit16 - bit19为数据加密标识位；
     *当此四位都为0，表示消息体不加密；
     */
    final static int ENCRYPTION = 0x000F0000 ;

    /**
     * 如果消息体属性中相关标识位确定消息分包处理，则该项有内容，否则无该项
     */
    final static int SUBCONTRACT = 0x00100000 ;


    /**
     * 最后消息的字节码
     * @return
     */
    public byte[] toBytes();

    /**
     * 根据字节码转换成消息
     * @param bytes 消息字节码
     */
    public void parseBytes(byte[] bytes);
}
