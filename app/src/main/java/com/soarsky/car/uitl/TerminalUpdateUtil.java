package com.soarsky.car.uitl;

import com.soarsky.car.Constants;
import com.soarsky.car.bean.TermDataBean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/4/19
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class TerminalUpdateUtil {



    /**
     * 获得指定文件的byte数组
     */
    public  static   byte[] toBytes(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }

            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }


    /**
     * 获取总包数
     */

    public  static   int   getPackageNum(byte[] bytes){
        if(bytes==null){
            return 0;
        }
        int  length=bytes.length;
        int packagenum=length/Constants.PACKAGE_LENGTH;
        int yushu=length%Constants.PACKAGE_LENGTH;
        if (yushu>0){
            return  packagenum+1;
        }
        return  packagenum;
    }


    /**
     * 获取每个包的数据
     * @param bytes  文件字节流
     * @param packageNum 第几个包
     * @return
     */
    public static byte[] getpackContent(byte[]bytes,int packageNum,int totalPackageNum){

        LogUtil.i("packageNum:"+packageNum+"   totalPackageNum:"+totalPackageNum);

        if(packageNum>totalPackageNum-1){
            return new byte[0];
        }

        byte[]content;
        if(bytes==null){
            return new byte[0];
        }

        if(packageNum==totalPackageNum-1){
            int copyLength=bytes.length%Constants.PACKAGE_LENGTH;
            if(copyLength==0){
                copyLength=Constants.PACKAGE_LENGTH;
            }
            content=new byte[copyLength];
            System.arraycopy(bytes,packageNum*Constants.PACKAGE_LENGTH,content,0,copyLength);
            return content;

        }else{
            content=new byte[Constants.PACKAGE_LENGTH];
            System.arraycopy(bytes, packageNum*Constants.PACKAGE_LENGTH,content,0,Constants.PACKAGE_LENGTH);
            return content;
        }

    }


}
