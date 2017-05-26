package com.soarsky.car;

/**
 * 车主APP<br>
 * 作者： 孙向锦<br>
 * 时间： 2016/12/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 常量类<br>
 */
public class Constants {


    /**
     * 企业服务器后台地址
     * http://m.gtw.soarsky-e.com/gtw/
     */


    public static final String BASE_URL = "http://119.23.17.93:80/gtw/";//"http://m.gtw.soarsky-e.com/gtw/" ;
    // "" ;  //http://192.168.100.16:8080/182.92.158.206//"http://182.92.158.206/";//"http://119.23.17.93:80/gtw/";

    /**
     * 企业服务器文件地址
     */

    public static final String BASE_URL2 = "http://112.74.173.0:8080/";//"http://f.gtw.soarsky-e.com/";


    /**
     * 闪灯找车地址
     */

    /**
     * 闪灯找车
     * 服务器走网关地址
     * 闪灯找车业务，从服务器走网关地址
     */

    public static final String BASE_URL3 = "http://112.74.173.0:3000/gtw/";//"http://192.168.100.56:3000/gtw/";
    // "http://182.92.158.206:3000/gtw/" ;//"http://112.74.173.0:3000/";


    /**
     * 是否有sim卡
     */
    public static final String HAS_SIM_CARD = "hasSimCard";

    /**
     * 授权码
     */
    public static final String VerCode = "1111";

    public static final String REQUEST_SUCCESS = "200";

    /**
     * 后台返回的状态码201--操作失败
     */
    public static final String REQUEST_FAIL = "201";
    /**
     * 保存用户名的Key
     */
    public static final String USERNAME = "userName";
    /**
     * 保存密码的Key
     */
    public static final String PASSWORD = "passWord";

    /***
     * 自动乘车记录weekday
     */
    public static final String AUTOWEEKDAY = "autoWeekDay";


    /***
     * 是否开启自动乘车
     */
    public static final String TRIVELRECORDISOPEN = "trivelRecordisOpen";


    /***
     * 自动乘车开始时间
     */
    public static final String TRIVELRECORDSTARTTIME = "trivelRecordStartTime";

    /***
     * 自动乘车结束时间
     */
    public static final String TRIVELRECORDENDTIME = "trivelRecordEndTime";

    /**
     * 事件的标识，传递车牌号
     */
    public static final String DRIVE_CARNUM_ACTION = "drive_car_action";
    /**
     * 用户名保存key
     */
    public static final String CONS_USERNAME = "username";

    /**
     * 身份证号
     */
    public static final String IDNO = "idNo";
    /**
     * 是否绑定终端
     */
    public static final String IS_BIND = "isBind";
    /**
     * 电话号码
     */
    public static final String OWNER_PHONE = "owerPhone";
    /**
     * 查询密码
     */
    public static final String QUERY_PWD = "queryPwd";
    /**
     * 真实姓名
     */
    public static final String REAL_NAME = "realName";
    /**
     * 头像路径
     */
    public static final String IMAGE_URL = "imageUrl";
    /**
     * 服务器地址配置文件文件名
     */
    public static final String CONFIG_FILE_URL = "config.txt";
    /**
     * 路径1
     */
    public static final String URL1 = "url1";

    /**
     * 路径2
     */
    public static final String URL2 = "url2";

    /**
     * 路径3
     */
    public static final String URL3 = "url3";
    /**
     * 路径4
     */
    public static final String URL4 = "url4";
    /**
     * 文件路径
     */
    public static final String SD_CARD = "sdcard";

    /**
     * 道路救援记录的进行状态
     */
    public static final String ROADSIDE_RECORED_STATUS = "0";
    /**
     * 道路救援记录的已完成状态
     */
    public static final String ROADSIDE_FINISH_STATUS = "1";
    /**
     * 获取交通法规资讯的请求类型
     */
    public static final int TYPE_TRAFFIC_RULE = 2;

    /**
     * 获取交通安全资讯的请求类型
     */
    public static final int TYPE_TRAFFIC_SAFE = 1;

    /**
     * 获取汽车保养资讯的请求类型
     */
    public static final int TYPE_CAR_CARE = 0;

    /**
     * 无责
     */
    public static final String NO_DUTY = "0";

    /**
     * 全责
     */
    public static final String ALL_DUTY = "2";

    /**
     * 同责
     */
    public static final String SAME_DUTY = "1";

    /**
     * 复审中
     */
    public static final String REVIEW = "3";

    /**
     * 已复审
     */
    public static final String REVIEWED = "4";

    /**
     * 车牌号
     */
    public static final String CAR_NUM = "carNum";
    /**
     * 驾驶证号
     */
    public static final String LICESE_NUM = "liceseNum";
    /**
     * 发车日期
     */
    public static final String REGISTER_CAR_DATE = "registerCarDate";
    /**
     * 驾驶证类型
     */
    public static final String DRIVING_TYPE = "drivingType";
    /**
     * 发证日期
     */
    public static final String REGISTER_DRIVERDATE = "registerDriverDate";

    /**
     * 最近阅读
     */
    public static final String LATEST_READ = "0";


    //与终端连接请求类型

    /**
     * 危险驾驶
     */
    public static final int DANGEROUS_REAVER = 13;
    /**
     * 读取行驶异常数据
     */
    public static final int TRAVEL_NOMALY = 11;

    /**
     * 同步罚单
     */
    public static final int TICKET_SYNC = 10;
    /**
     * 读取未读罚单
     */
    public static final int TICKET_UNREAD = 9;

    /**
     * 请人移车
     */
    public static final int MOVE_CAR = 8;

    /**
     * 申请一
     */
    public static final int APPLY_ONE = 1;
    /**
     * 申请二
     */
    public static final int APPLY_TWO = 2;
    /**
     * 申请三
     */
    public static final int APPLY_THREE = 3;

    /**
     * 申请四
     */
    public static final int APPLY_FOUR = 4;

    /**
     * 申请五
     */
    public static final int APPLY_FIVE = 5;

    /**
     * 电子驾照随车通知
     */
    public static final int CHECK_ALIVE = 12;


    /**
     * 同步亲情号
     */
    public static final int SYNC_DERANUM = 7;

    /***
     * 音量设置
     */
    public static final int VOLUME_SET = 14;

    /***
     * 用车记录
     */
    public static final int CAR_RECORD = 6;


    /***
     * 离车
     */
    public static final int DRIVER_LEAVE = 15;

    /***
     * 读取故障信息
     */
    public static final int READ_TROUBLE = 16;


    /***
     * 同步时间
     */
    public static final int SYNC_TIME = 17;


    /**
     * 终端升级消息
     */
    public static final int TERMINAL_UPDATE = 18;


    /**
     * 传送升级数据包
     */
    public static final int TERMINAL_TRANSFER_DATAPACKAGE = 19;


    /**
     * 设置终端参数
     */
    public static final int TERMINAL_PARAM_SET = 20;

    /**
     * 设置终端语音
     */
    public static final int TERMINAL_SOUNT_PARAM = 21;

    /**
     * 同步罚单
     */
    public static final int SYNC_TICKET = 22;

    /**
     * 读取未读罚单条数
     */
    public static final int READ_UNREADTICKET_COUNT = 23;


    /**
     * 当前密码错误
     */
    public static final String CURRENT_PWD_ERROE = "303";

    /**
     * 罚单不是可以申请撤销的状态
     */
    public static final String NO_REVOKE = "0";

    /**
     * 罚单可以申请撤销
     */
    public static final String CAN_REVOKE = "1";

    /**
     * 罚单撤销中
     */
    public static final String REVOKEING = "5";

    /**
     * 罚单已撤销
     */
    public static final String REVOKED = "6";

    /**
     * 车辆所属--自己
     */
    public static final int PERSONAL = 1;

    /**
     * 车辆所属--好友
     */
    public static final int FRIEND = 0;

    /**
     * 终端状态1表示停车状态
     */
    public static final String TERMINAL_STATUS_1 = "1";
    /**
     * 终端状态2表示点火状态
     */
    public static final String TERMINAL_STATUS_2 = "2";
    /**
     * 终端状态3表示已经通过防盗验证的状态
     */
    public static final String TERMINAL_STATUS_3 = "3";
    /**
     * 终端状态4表示行驶中
     */
    public static final String TERMINAL_STATUS_4 = "4";


    /**
     * 驾车随行时间
     */

    public static final int DREIVER_ALIVE_TIME = 5*60*1000;

    /**
     * 驾照类型
     */
    public static final String CAR_TYPE_P = "P";
    public static final String CAR_TYPE_N = "N";
    public static final String CAR_TYPE_M = "M";
    public static final String CAR_TYPE_F = "F";
    public static final String CAR_TYPE_E = "E";
    public static final String CAR_TYPE_D = "D";
    public static final String CAR_TYPE_C5 = "C5";
    public static final String CAR_TYPE_C4 = "C4";
    public static final String CAR_TYPE_C3 = "C3";
    public static final String CAR_TYPE_C2 = "C2";
    public static final String CAR_TYPE_C1 = "C1";
    public static final String CAR_TYPE_B2 = "B2";
    public static final String CAR_TYPE_B1 = "B1";
    public static final String CAR_TYPE_A3 = "A3";
    public static final String CAR_TYPE_A2 = "A2";
    public static final String CAR_TYPE_A1 = "A1";

    /**
     * 驾照证详情
     */
    public static final String DriviLicenseNum = "driviLicenseNum";
    public static final String DriviLicenseDate = "driviLicenseDate";
    public static final String DriviLicenseCent = "driviLicenseCent";
    public static final String DrivDistance = "drivDistance";
    public static final String DriviType = "driviType";
    public static final String ClearDate = "clearDate";
    public static final String DriviLicenseStatus = "driviLicenseStatus";

    /**
     * 行驶证详情
     */
    public static final String CarInfoNum = "carInfoNum";
    public static final String CarInfoColor = "carInfoColor";
    public static final String CarInfoType = "carInfoType";
    public static final String CarInfoIdenty = "carInfoIdenty";
    public static final String CarInfoEngineNo = "carInfoEngineNo";
    public static final String OwerName = "owerName";
    public static final String TelePhone = "telePhone";
    public static final String CarInfoDriviStatus = "carInfoDriviStatus";
    public static final String CarInfoDate = "carInfoDate";

    /**
     * 终端数据
     */
    public static final String TermData = "termData";

    public static final long TERM_SLEEP = 250;

    /**
     * 每次读取终端条数
     */
    public static final int READTICKET_COUNT = 5;
    /***
     * 下载终端升级文件路径
     */
    public static final String TermSrc = "/sdcard/" + "CarApp/test.txt";


    /**
     * 发送给终端每条数据长度
     */
    public static final int PACKAGE_LENGTH = 128;

    /***
     * 友盟推送device token key 值
     */
    public static final String UMENG_DEVICE_TOKEN = "umeng_device_token";

    /**
     * 汽车类行的代号  01----大型汽车
     */
    public static final String NUM_ONE_CODE = "01";
    public static final String NUM_ONE_TYPE = "大型汽车";

    /**
     * 汽车类行的代号  02----小型汽车
     */
    public static final String NUM_TWO_CODE = "02";
    public static final String NUM_TWO_TYPE = "小型汽车";

    /**
     * 汽车类行的代号  03----使馆汽车
     */
    public static final String NUM_THREE_CODE = "03";
    public static final String NUM_THREE_TYPE = "使馆汽车";

    /**
     * 汽车类行的代号  04----领馆汽车
     */
    public static final String NUM_FOUR_CODE = "04";
    public static final String NUM_FOUR_TYPE = "领馆汽车";

    /**
     * 汽车类行的代号  05----境外汽车
     */
    public static final String NUM_FIVE_CODE = "05";
    public static final String NUM_FIVE_TYPE = "境外汽车";

    /**
     * 汽车类行的代号  06----外籍汽车
     */
    public static final String NUM_SIX_CODE = "06";
    public static final String NUM_SIX_TYPE = "外籍汽车";

    /**
     * 汽车类行的代号  07----两、三轮摩托车
     */
    public static final String NUM_SEVEN_CODE = "07";
    public static final String NUM_SEVEN_TYPE = "两、三轮摩托车";

    /**
     * 汽车类行的代号  08----轻便摩托车
     */
    public static final String NUM_EIGHT_CODE = "08";
    public static final String NUM_EIGHT_TYPE = "轻便摩托车";

    /**
     * 汽车类行的代号  09----使馆摩托车
     */
    public static final String NUM_NIGHT_CODE = "09";
    public static final String NUM_NIGHT_TYPE = "使馆摩托车";

    /**
     * 汽车类行的代号  10----领馆摩托车
     */
    public static final String NUM_TEN_CODE = "10";
    public static final String NUM_TEN_TYPE = "领馆摩托车";

    /**
     * 汽车类行的代号  11----外籍汽车
     */
    public static final String NUM_ELEVEN_CODE = "11";
    public static final String NUM_ELEVEN_TYPE = "境外摩托车";

    /**
     * 汽车类行的代号  12----外籍摩托车
     */
    public static final String NUM_TWELVE_CODE = "12";
    public static final String NUM_TWELVE_TYPE = "外籍摩托车";

    /**
     * 汽车类行的代号  13----外籍汽车
     */
    public static final String NUM_THIRTEEN_CODE = "13";
    public static final String NUM_THIRTEEN_TYPE = "农用运输车";

    /**
     * 汽车类行的代号  14----拖拉机
     */
    public static final String NUM_FOURTEEN_CODE = "14";
    public static final String NUM_FOURTEEN_TYPE = "拖拉机";

    /**
     * 汽车类行的代号  15----教练汽车
     */
    public static final String NUM_FIFTEEN_CODE = "15";
    public static final String NUM_FIFTEEN_TYPE = "教练汽车";

    /**
     * 汽车类行的代号  16----外籍汽车
     */
    public static final String NUM_SIXTEEN_CODE = "16";
    public static final String NUM_SIXTEEN_TYPE = "教练摩托车";

    /**
     * 汽车类行的代号  17----试验汽车
     */
    public static final String NUM_SEVENTEEN_CODE = "17";
    public static final String NUM_SEVENTEEN_TYPE = "试验汽车";

    /**
     * 汽车类行的代号  18----试验摩托车
     */
    public static final String NUM_EIGHTTEEN_CODE = "18";
    public static final String NUM_EIGHTTEEN_TYPE = "试验摩托车";

    /**
     * 汽车类行的代号  19----临时入境汽车
     */
    public static final String NUM_NIGHTTEEN_CODE = "19";
    public static final String NUM_NIGHTTEEN_TYPE = "临时入境汽车";

    public static final String NO_CODE = "轿车";

    /**
     * 最后阅读标识
     */
    public static final String READ = "lastRead";
}
