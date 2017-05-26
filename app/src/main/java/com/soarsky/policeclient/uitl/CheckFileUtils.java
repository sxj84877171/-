package com.soarsky.policeclient.uitl;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/27<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为输出信息到指定的测试文件类<br>
 */
public class CheckFileUtils {
    /**
     * 文件路径
     */
    private static String SDCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
    /**
     * 文件实例
     */
    private static File file;
    /**
     * 文件名
     */
    private static String fileName = "第二套稽查方案.txt";

    /**
     * 创建文件
     * @param fileName 文件名
     */
    private static void createFile(String fileName) {
        if(file==null){
            file = new File(SDCardRoot + "/" + fileName);
        }
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                LogUtil.e("IOException",e.toString());
                e.printStackTrace();
            }
        }
    }

    /**
     * 打印时间
     * @param s 时间值
     */
    public static void printTime(String s){
        createFile(fileName);
        print(s);

    }

    /**
     * 打印功能
     * @param s 打印字符串
     */
    private static void print(String s) {
        String time = TimeUtils.getCurTimeString();
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file,true);
            fileWriter.write(s+" "+ time + "\n");
            fileWriter.flush();
        } catch (IOException e) {

            LogUtil.e("weikai",e.toString());
            e.printStackTrace();
        }finally {
            if(fileWriter!=null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
