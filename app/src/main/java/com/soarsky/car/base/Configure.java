package com.soarsky.car.base;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/8
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 一些常量的定义
 */


public class Configure {
    /**
     * 是否启动自动连接
     */
    public static  boolean ISAUTOCOONECT=true;

    /**
     * 扫描间隔毫秒数
     */
    public static final long SACN_TIME = 15*1000;

    /**
     * 获取扫描结果毫秒数
     */
    public static final long SACN_RESULT = 5*1000;

    /**
     * 确认驾驶员后检查驾驶员是否还在间隔毫秒数
     */
    public static final long ISDRIVERALIVE = 1*30*1000;

    /**
     * 自动同步数据时间
     */
    public static final long AUTOUPDATA = 10*60*1000;



    /**
     * wifi连接时间
     */
    public static final long WIFICONNNECTTIME = 15*1000;


    /**
     * 驾驶员离开时间
     */
    public static final long DRIVERLEAVE = 5*60*1000;

    /**
     * 判断乘客是否离车间隔
     */
    public static final long PASSENGERLEAVE = 1*60*1000;


    /**
     * 每隔10分钟检测一次是否在保护时间内
     */
    public static final long CHECKUPTIME = 10*60*1000;
}
