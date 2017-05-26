package com.soarsky.policeclient.uitl;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 警务通<br>
 * 作者： 何明辉<br>
 * 时间： 2016/12/5<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 正则工具类<br>
 */
public class ValidatorUtils {
    /**
    * 正则表达式：验证用户名
    */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,16}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";


    /**
     * 正则表达式：验证是否是车牌
     */
    public static final String REGEX_CAR_NO = "^[\\u4E00-\\u9FA5][\\da-zA-Z]{6}$";


    /**
     * 正则表达式：验证是否是车牌
     */
    public static final String REGEX_EFFECT_HOT = "^\\d{10}[A-Z]{1}[A-Z0-9]{5}$";

    //public static final String REGEX_CAR_NO_POLICE = "^([\\d]{8})?[\\u4E00-\\u9FA5][\\da-zA-Z]{6}$";
    public static final String REGEX_CAR_NO_POLICE = "(^1\\d{2})[\\u4E00-\\u9FA5][\\da-zA-Z]{6}-$";
    /**
     * 车牌号验证
     * @param str
     * @return
     */
    public  static boolean  validatorCarNo(String str){
        Pattern pattern = Pattern.compile(REGEX_CAR_NO);
        Matcher matcher = pattern.matcher(str);
        return  matcher.matches();

    }
    /**
     * 判断ssid是否有效
     */

    public  static boolean  validatorSsid(String ssid){
        Pattern pattern = Pattern.compile(REGEX_CAR_NO_POLICE);
        Matcher matcher = pattern.matcher(ssid);
        return  matcher.matches();

    }

}
