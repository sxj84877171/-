package com.soarsky.car.server.design;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：通讯协议命令<br>
 * 该类为 与终端通讯服务类<br>
 */
public interface ICommand {
     int LENGTH = 0xFFFF;

     int AES = 0x00010000;

     int ENCRYPTION = 0x000F0000 ;

     int SUBCONTRACT = 0x00100000 ;

     int CRC_LENGTH = 2 ;

    /**
     * 0x0001	0x0801	验证驾驶员
     */
     int CMD_RESPONED_DRIVER = 2049 ;

    /**
     * 0x0002	0x0802	读取罚单信息
     */
     int CMD_RESPONED_READ_TICKET = 2063 ;

    /**
     * 0x0003	0x0803	增加修改车主、亲情号码
     */
     int CMD_RESOPONED_MODIFY_PHONE = 2051;

    /**
     * 0x0004	0x0804	回复相关数据的处理状态
     */
     int cmd_responed_data_state = 804 ;

    /**
     *  0x0005	0x0805	设置车载终端的音量
     */
     int CMD_RESPONED_MUITE = 2053 ;

    /**
     * 0x0006	0x0806	开车玩手机
     */
     int CMD_RESPONED_PLAY_PHONE = 2054 ;

    /**
     * 0x0007	0x0807	终端升级信息
     */
     int CMD_RESPONED_TEM_UPDATE = 807;
    /**
     *   0x0008	0x0808	智能终端的故障代码发送到车主APP
     */
     int CMD_RESPONED_FAULT_CODE = 2056 ;
    /**
     *0x0009	0x0809	读取数据命令
     */
     int CMD_RESPONED_READ_DATA =0x0809 ;
    /**
     *0x000A	0x0810	请人移车
     */
     int CMD_RESPONED_PLEASE_MOVE_CAR = 2058 ;
    /**
     *0x000B	0x0811	更新终端时间
     */
     int CMD_RESPONED_UPDATE_TEM_TIME = 811 ;
    /**
     *0x000C	0x080C	退出驾驶状态
     */
     int CMD_RESPONED_EXIT_DRIVER = 0x080C ;
    /**
     *0x000D	0x080D	电子驾照随车通知
     */
     int CMD_RESPONED_DRIVER_LIVE = 0x080D ;
    /**
     *0x000E	0x080E	申请升级数据包
     */
     int CMD_RESPONED_TEM_UPDATE_DATA_PACKET = 0x080E ;
    /**
     *0x000F	0x080F	申请罚单数据包
     */
     int CMD_RESPONED_APPLY_DATA_PACKET = 0x080F ;
    /**
     *
     0x0010	0x0810	标记已上传罚单
     */
     int cmd_responed_ticket_has_upload = 0x0810 ;



    int CMD_RESPOND_CHECKALIVE=0x080D;

    /**
     * 0x01申请一
     */
    int cmd_responed_CMD_APPLY_ONE=0x01;


    /**
     * 0x01申请二
     */
    int cmd_responed_CMD_APPLY_TWO=0x02;


    /**
     * 0x01申请三
     */
    int cmd_responed_CMD_APPLY_THREE=0x03;

    /**
     * 0x01申请四
     */
    int cmd_responed_CMD_APPLY_FOUR=0x04;

    /**
     * 0x01申请五
     */
    int cmd_responed_CMD_APPLY_FIVE=0x05;



    /**
     * 同步时间
     */
    int  CMD_RESPONED_SYNCTIME=0x080B;

    /**
     * 终端升级信息
     */
    int  CMD_RESPONED_UPDATEMESSAGE=0x0807;

    /**
     * 传送数据包
     */
    int  CMD_RESPONED_TRANSFERPACKAGE=0x080E;

    /**
     * 更新指定编号参数
     */
    int  CMD_RESPONED_UPDATEPARAM=0x0811;

    /**
     * 更新指定编号语音
     */
    int  CMD_RESPONED_UPDATECODEVOICE=0x0812;

    /**
     * 读取罚单条数
     */
    int  CMD_RESPONED_TICKETCOUNT=0x0802;

    /**
     * 退出驾驶员状态
     */
    int  CMD_RESPONED_EXITDRIVER=0x080C;

    /**
     *
     */
    public byte[] toBytes();
    /**
     *
     */
    public void parseBytes(byte[] bytes);
}
