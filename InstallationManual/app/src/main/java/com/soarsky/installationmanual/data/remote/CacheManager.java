package com.soarsky.installationmanual.data.remote;

import com.soarsky.installationmanual.util.EncryptUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * InstallationManual
 * 作者： Elvis
 * 时间： 2017/2/16
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class CacheManager {

    private final String CACHE_PATH = "/sdcar/InstallationManual/cache/" ;

    /**
     * 描述：静态实例化
     */
    private static CacheManager instance = null ;
    
    /**
     * 作者：
     * 时间：
     * 描述：私有化构造类，不允许外面构造该类
     */
    private CacheManager(){
    }
    
    /**
     * 获取静态实例
     */
    public static CacheManager getInstance(){
        if(instance == null){
            synchronized(CacheManager.class){
                if(instance == null){
                    instance = new CacheManager();
                }
            }
         }
         return instance;
    }

    /**
     * 保存缓存到硬盘
     * @param  signature  方法名字
     * @param args 方法参数
     * @return
     */
    public boolean saveCatch(Object ret , String signature,String... args){

        StringBuilder sb = new StringBuilder();
        sb.append(signature);
        if(args != null){
            for(String arg : args){
                sb.append(arg);
            }
        }

        String path = EncryptUtils.encryptMD5File2String(sb.toString());
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(CACHE_PATH + File.separator + path);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(ret);
            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    /**
     * 从硬盘取出缓存
     * @param signature 方法名
     * @param args 方法参数
     * @param <T> 返回的类型
     * @return 返回的对象
     */
    public <T> T getCatch(String signature,String... args){
        T  ret = null ;
        StringBuilder sb = new StringBuilder();
        sb.append(signature);
        if(args != null){
            for(String arg : args){
                sb.append(arg);
            }
        }

        String path = EncryptUtils.encryptMD5File2String(sb.toString());

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(CACHE_PATH + File.separator + path);
            ois = new ObjectInputStream(fis);
            ret = (T)ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }
}
