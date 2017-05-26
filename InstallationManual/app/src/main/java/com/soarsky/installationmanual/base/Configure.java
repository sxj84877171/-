package com.soarsky.installationmanual.base;

/**
 * 安装手册<br>
 * 作者： 孙向锦<br>
 * 时间： 2017/02/09<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 一些常量的定义<br>
 */


public class Configure {
    /**
     * 是否启动自动连接
     */
    public static  boolean ISAUTOCOONECT=false;

    /**
     * 扫描间隔毫秒数
     */
    public static final long SACN_TIME = 5*1000;

    /**
     * 获取扫描结果毫秒数
     */
    public static final long SACN_RESULT = 5*1000;

    /**
     * 确认驾驶员后检查驾驶员是否还在间隔毫秒数
     */
    public static final long ISDRIVERALIVE = 1*45*1000;

    /**
     * 自动同步数据时间
     */
    public static final long AUTOUPDATA = 10*60*1000;



    /**
     * wifi连接时间
     */
    public static final long WIFICONNNECTTIME = 25*1000;

}
