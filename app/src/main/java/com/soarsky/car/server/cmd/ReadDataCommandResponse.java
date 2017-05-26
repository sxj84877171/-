package com.soarsky.car.server.cmd;

import com.soarsky.car.uitl.LogUtil;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/2<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 读取终端消息应答解析<br>
 */

public class ReadDataCommandResponse extends BaseCmd{
    /**
     * 应答命令
     0x00：成功/确认；
     0x01：车牌号不对；
     0x02：驾照号不对；
     0x03：消息有误；
     0x04: 授权码错误;
     0x05：不支持

     *
     */
    private int resposneType;

    /**
     * 请求类型
     * 0x01: 读取行驶异常数据
       0x02: 读取车况(用车记录)
     */
    private int  requestType;
    /**
     * 内容体
     */
    private String  contentStr;


    private  int  contentLength;


    private  String  carNum;



    public boolean isSucess(){
        return resposneType==0;
    }

    @Override
    public byte[] toMsgBytes() {
        return new byte[0];
    }

    @Override
    public void parseBytes(byte[] bytes) {

//        StringBuilder stringBuilder=new StringBuilder();
//        if(bytes.length>23){
//            resposneType= bytes[23];
//            resposneType=bytes[24];
//            contentLength=parseLength(bytes,25);
//            byte[] contetBuffer = new byte[contentLength];
//            System.arraycopy(bytes,27,contetBuffer,0,contentLength);
//            contentStr=new String(contetBuffer);
//            byte[] carNumBuffer=new byte[9];
//            System.arraycopy(bytes,14,contetBuffer,0,9);
//        }


    }



    public String getContentStr() {
        return contentStr;
    }


    public int getRequestType() {
        return requestType;
    }


    public String getCarNum() {
        return carNum;
    }
}
