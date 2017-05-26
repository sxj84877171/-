package com.soarsky.car.uitl;

import com.soarsky.car.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Andriod_Car_App<br>
 * 作者： Elvis<br>
 * 时间： 2017/3/16<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为服务器配置地址
 * 配置文件配置要求，以键值对进行配置，上面可以采用注释符号来进行注释。有容错处理
 */

public class HostUtil {

    /***
     *配置文件路径
     */
    public static final String CONFIG_PATH = File.separator + Constants.SD_CARD + File.separator + LogUtil.CUSTOM_TAG_PREFIX + File.separator + Constants.CONFIG_FILE_URL ;

    /**
     * 从配置文件中获取指定key的value值
     * @param key 配置文件中url key值
     * @return 返回对应配置的URL路径
     */
    public static String getHostIp(String key){
        File file = new File(CONFIG_PATH);
        if(file.exists()){
            BufferedReader br = null ;
            try{
                br= new BufferedReader(new FileReader(file));
                String temp = null;
                while((temp = br.readLine()) != null){
                    if(temp != null && temp.trim().startsWith(key)){
                        String ret = temp.substring(temp.indexOf("=")+1).trim();
                        if(ret !=  null && !"".equals(ret)){
                            return ret ;
                        }
                    }
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }finally {
                if(br != null){
                    try {
                        br.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取服务器1地址路径
     * @return 服务器地址
     */
    public static String getHostUrl1(){
        return getHostIp(Constants.URL1);
    }

    /**
     * 获取服务器2地址路径
     * @return 服务器地址
     */
    public static String getHostUrl2(){
        return getHostIp(Constants.URL2);
    }

    /**
     * 获取服务器3地址路径
     * @return 服务器地址
     */
    public static String getHostUrl3(){
        return getHostIp(Constants.URL3);
    }

    /**
     * 获取服务器4地址路径
     * @return 服务器地址
     */
    public static String getHostUrl4(){
        return getHostIp(Constants.URL4);
    }
}
