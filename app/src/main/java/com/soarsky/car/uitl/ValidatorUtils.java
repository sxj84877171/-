package com.soarsky.car.uitl;


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
    public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";

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
    public static final String REGEX_ID_CARD = "^(\\d{6})(\\d{4})(\\d{2})(\\d{2})(\\d{3})([0-9]|X)$";


    /**
     * 正则表达式：验证是否是车牌
     */
    public static final String REGEX_CAR_NO = "^[A-Z]{1}[A-Z0-9]{5}$";


    /**
     * 正则表达式：验证是否是车牌
     */
    public static final String REGEX_EFFECT_HOT = "^\\d{10}[A-Z]{1}[A-Z0-9]{5}$";

//    public static final String REGEX_CAR_NO_POLICE = "^([\\d]{8})?[\\u4E00-\\u9FA5][\\da-zA-Z]{6}([a-zA-Z]{1})?$";

//    public static final String REGEX_CAR_NO_POLICE = "(^\\d{8})[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z0-9]{5}$";

    public static final String REGEX_CAR_NO_POLICE = "(^\\d{8})\\S{1}[A-Z]{1}[A-Z0-9]{5}$";


    public static final String BLUETOOTH_REGEX_CAR_NO_POLICE = "(^\\d{3})\\S{1}[A-Z]{1}[A-Z0-9]{5}-$";


    public static final String REGEX_INPUT_CARNO = "^[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z0-9]{5}$";

    /**
     * 车牌号验证
     * @param str
     * @return
     */
    public  static boolean  validatorCarNo(String str){
        Pattern pattern = Pattern.compile(REGEX_INPUT_CARNO);
        Matcher matcher = pattern.matcher(str);
        return  matcher.matches();

    }
    /**
     * 判断ssid是否有效
     */

    public  static boolean  validatorSsid(String ssid){
        Pattern pattern = Pattern.compile(BLUETOOTH_REGEX_CAR_NO_POLICE);
        Matcher matcher = pattern.matcher(ssid);
        return  matcher.matches();

    }



    /**
     * 判断蓝牙名称是否有效
     */

    public  static boolean  validatorBlueTooth(String deviceName){
        Pattern pattern = Pattern.compile(BLUETOOTH_REGEX_CAR_NO_POLICE);
        Matcher matcher = pattern.matcher(deviceName);
        return  matcher.matches();

    }


    /**
     * 校验车牌号是否正确
     * @param ssid
     * @return
     */
    public  static boolean  validatorCarNum(String ssid){
        Pattern pattern = Pattern.compile(REGEX_INPUT_CARNO);
        Matcher matcher = pattern.matcher(ssid);
        return  matcher.matches();

    }

    /**
     * 校验手机号是否正确
     * @param ssid
     * @return
     */
    public static boolean validatorPhone(String ssid){
        Pattern pattern = Pattern.compile(REGEX_MOBILE);
        Matcher matcher = pattern.matcher(ssid);
        return  matcher.matches();
    }

    /**
     * 校验身份证
     * @param idNo
     * @return
     */
    public static boolean validatorIdNo(String idNo){
        Pattern pattern = Pattern.compile(REGEX_ID_CARD);
        Matcher matcher = pattern.matcher(idNo);
        return  matcher.matches();
    }

    /**
     * 根据idno获取province
     * @param idno
     * @return
     */
    public static String getProvinceByIdNo(String idno){

        String province ="";
        if(idno.equals("11")){
            province = "北京";
        }else if(idno.equals("12")){
            province = "天津";
        }else if(idno.equals("13")){
            province = "河北";
        }else if(idno.equals("14")){
            province = "山西";
        }else if (idno.equals("15")){
            province = "内蒙古";
        }else if(idno.equals("21")){
            province = "辽宁";
        }else if(idno.equals("22")){
            province = "吉林";
        }else if(idno.equals("23")){
            province = "黑龙江";
        }else if(idno.equals("31")){
            province = "上海";
        }else if(idno.equals("32")){
            province = "江苏";
        }else if(idno.equals("33")){
            province = "浙江";
        }else if (idno.equals("34")){
            province = "安徽";
        }else if(idno.equals("35")){
            province = "福建";
        }else if(idno.equals("36")){
            province = "江西";
        }else if(idno.equals("37")){
            province = "山东";
        }else if(idno.equals("41")){
            province = "河南";
        }else if(idno.equals("42")){
            province = "湖北";
        }else if(idno.equals("43")){
            province = "湖南";
        }else if(idno.equals("44")){
            province = "广东";
        }else if(idno.equals("45")){
            province = "广西";
        }else if(idno.equals("46")){
            province = "海南";
        }else if(idno.equals("50")){
            province = "重庆";
        }else if(idno.equals("51")){
            province = "四川";
        }else if(idno.equals("52")){
            province = "贵州";
        }else if (idno.equals("53")){
            province = "云南";
        }else if(idno.equals("54")){
            province = "西藏";
        }else if(idno.equals("61")){
            province = "陕西";
        }else if(idno.equals("62")){
            province = "甘肃";
        }else if(idno.equals("63")){
            province = "青海";
        }else if(idno.equals("64")){
            province = "宁夏";
        }else if(idno.equals("65")){
            province = "新疆";
        }else if(idno.equals("71")){
            province = "台湾";
        }else if(idno.equals("81")){
            province = "香港";
        }else if(idno.equals("91")){
            province = "澳门";
        }



        return province;
    }
}
