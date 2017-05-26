package com.soarsky.installationmanual.util;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 安装手册
 * 作者： 孙向锦
 * 时间： 2017/02/09
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 工具日志类
 */
public class LogUtil {
    public static String customTagPrefix = "InstallationManual";
    private static FileWriter fileWriter;
    public static String LOG_PATH = File.separator + "sdcard" + File.separator + customTagPrefix + File.separator + "log" + File.separator;
    public static boolean DEBUG = true ;
    public static String SPEAR_LINE =
            "------------------------------------------------------------------------------------------------------------------------------------------\n" +
                    "------------------------------------------------------------------------------------------------------------------------------------------\n" +
                    "------------------------------------------------------------------------------------------------------------------------------------------\n";

    /**
     * 日志工具类，无法构造对象。直接使用
     */
    private LogUtil() {
        throw  new UnsupportedOperationException("No Impl");
    }

    /**+
     * 日志工具类初始化
     */
    private static void init() {
        if (fileWriter == null) {
            File path = new File(LOG_PATH);
            if (!path.exists()) {
                path.mkdirs();
            }
            File logFile = new File(LOG_PATH + customTagPrefix + ".log");

            if (!logFile.exists()) {
                try {
                    logFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                if (fileWriter == null) {
                    fileWriter = new FileWriter(logFile, true);
                }
            } catch (IOException e) {
            }
        }
    }

    /**
     * 自动生成tag
     * @return
     */
    private static String generateTag() {
      StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    /**
     * 自动打印堆栈信息
     */
    public static void stack(){
        StackTraceElement[] stackTraceElements = new Throwable().getStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        for(StackTraceElement caller:stackTraceElements) {
            String callerClazzName = caller.getClassName();
            callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
            stringBuilder.append("\r\nclassName:").append(callerClazzName).append("\r\nMethodName:").append(caller.getMethodName())
                    .append("\r\nLineNumber:" + caller.getLineNumber());
        }
        i(stringBuilder.toString());
    }

    /**
     * 警告输出信息
     * @param content
     */
    public static void d(String content) {
        String tag = generateTag();
        d(tag, content);
    }

    /**
     * 警告输出信息
     * @param tag
     * @param content
     */
    public static void d(String tag, String content) {
        if ( DEBUG) {
            Log.d(tag, content);
        } else {
            writeToFile(tag, content);
        }
    }

    /**
     * 警告输出信息
     * @param content
     * @param tr
     */
    public static void d(String content, Throwable tr) {
        String tag = generateTag();
        d(tag, content + getErrorMessage(tr));
    }

    /**
     * 错误日志信息
     * @param content
     */
    public static void e(String content) {
        String tag = generateTag();
        e(tag, content);
    }

    /**
     * 错误日志信息
     * @param tag
     * @param content
     */
    public static void e(String tag, String content) {
        if ( DEBUG) {
            Log.e(tag, content);
        } else {
            writeToFile(tag, content);
        }
    }


    /***
     *  错误日志信息
     * @param tr
     */
    public static void e(Throwable tr){
        String tag = generateTag();
        e(tag, getErrorMessage(tr));
    }

    /**
     * 错误日志信息
     * @param content
     * @param tr
     */
    public static void e(String content, Throwable tr) {
        String tag = generateTag();
        e(tag, content + getErrorMessage(tr));

    }

    /**
     * 信息级别输出信息
     * @param content
     */
    public static void i(String content) {
        String tag = generateTag();
        i(tag, content);
    }

    /**
     * 信息级别输出信息
     * @param bytes
     */
    public static void i(byte[] bytes){
        i(bytes,0,bytes.length);
    }

    /**
     * 信息级别输出信息
     * @param bytes
     * @param start
     * @param len
     */
    public static void i(byte[] bytes,int start, int len){
        StringBuilder sb = new StringBuilder();
        if(bytes != null){
            for(int i = 0 ; i < len;i++){
                sb.append(bytes[i+start]);
            }
        }
        i(sb.toString());
    }

    /**
     * 信息级别输出信息
     * @param tag
     * @param content
     */
    public static void i(String tag, String content) {
        if ( DEBUG) {
            Log.i(tag, content);
        } else {
            writeToFile(tag, content);
        }
    }

    /**
     * 信息级别输出信息
     * @param content
     * @param tr
     */
    public static void i(String content, Throwable tr) {
        String tag = generateTag();
        i(tag, content + getErrorMessage(tr));
    }

    /**
     * 提示级别输出信息
     * @param content
     */
    public static void v(String content) {
        String tag = generateTag();
        v(tag, content);
    }

    /**
     * 提示级别输出信息
     * @param tag
     * @param content
     */
    public static void v(String tag, String content) {
        if ( DEBUG) {
            Log.v(tag, content);
        } else {
            writeToFile(tag, content);
        }
    }

    /**
     * 提示级别输出信息
     * @param content
     * @param tr
     */
    public static void v(String content, Throwable tr) {
        String tag = generateTag();
        v(tag, content + getErrorMessage(tr));
    }

    /**
     * 提示级别输出信息
     * @param content
     */
    public static void w(String content) {
        String tag = generateTag();
        w(tag, content);
    }

    /**
     * 警告级别输出信息
     * @param tag
     * @param content
     */
    public static void w(String tag, String content) {
        if ( DEBUG) {
            Log.w(tag, content);
        } else {
            writeToFile(tag, content);
        }
    }

    /**
     * 警告级别输出信息
     * @param content
     * @param tr
     */
    public static void w(String content, Throwable tr) {
        String tag = generateTag();
        w(tag, content + getErrorMessage(tr));
    }

    /**
     * 警告级别输出信息
     * @param tr
     */
    public static void w(Throwable tr) {
        String tag = generateTag();
        w(tag, getErrorMessage(tr));
    }


    /**
     * 警告级别输出信息
     * @param content
     */
    public static void wtf(String content) {
        String tag = generateTag();
        wtf(tag, content, null);
    }

    /**
     * 警告级别输出信息
     * @param content
     * @param tr
     */
    public static void wtf(String content, Throwable tr) {
        String tag = generateTag();
        wtf(tag, content, tr);
    }

    /**
     * 警告级别输出信息
     * @param tag
     * @param content
     * @param tr
     */
    public static void wtf(String tag, String content, Throwable tr) {
        if (content == null) {
            content = "log:";
        }
        if ( DEBUG) {
            Log.wtf(tag, content, tr);
        } else {
            writeToFile(tag, content + getErrorMessage(tr));
        }
    }

    /**
     * 警告级别输出信息
     * @param tr
     */
    public static void wtf(Throwable tr) {
        wtf(null, tr);
    }

    /**
     * 写入信息到文件
     * @param message
     */
    public static void writeToFile(String tag,String message) {
        writeToFile(tag + "  " + message);
    }

    /**
     * 写入信息到文件
     * @param message
     */
    public static void writeToFile(String message) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        init();
        String tag = generateTag();
        String msg = String.format("%s : %s\r\n", dateStr, message);
        writeMessageToFile(msg);
    }

    /**
     * 写入信息到文件
     * @param tag
     * @param message
     */
    private static void writeMessageToFile(String tag, String message) {
        writeMessageToFile(tag + "| " + message);
    }

    /**
     * 写入信息到文件
     * @param message
     */
    private static void writeMessageToFile(String message) {
        init();
        if (fileWriter != null) {
            try {
                fileWriter.write(message);
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 写入信息到文件
     * @param e
     * @return
     */
    private static String getErrorMessage(Throwable e) {
        if (e != null) {
            return Log.getStackTraceString(e);
        } else {
            return "";
        }
    }

    /**
     * 写入信息到文件
     */
    public static void writeSpearLine() {
        try {
            init();
            if (fileWriter != null) {
                fileWriter.write(SPEAR_LINE);
                fileWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void close() {
        if (fileWriter != null) {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
