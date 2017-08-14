package com.sxj.carloan.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by admin on 2017/8/12.
 */

public class CrashHandler  implements Thread.UncaughtExceptionHandler {

    private static CrashHandler instance;
    private Thread.UncaughtExceptionHandler uncaughtExceptionHandler ;

    /** 获取CrashHandler实例 ,单例模式*/

    public static CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();
        }
        return instance;
    }

    public void init(){
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread arg0, Throwable arg1) {
        if(uncaughtExceptionHandler != null){
            uncaughtExceptionHandler.uncaughtException(arg0,arg1);
        }
        String logdir ;
        if(Environment.getExternalStorageDirectory()!=null){
            logdir = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + "Loan"+File.separator+"loan_crash.log" ;


            File file = new File(logdir);
            boolean mkSuccess;
            if (!file.isDirectory()) {
                mkSuccess = file.mkdirs();
                if (!mkSuccess) {
                    mkSuccess = file.mkdirs();
                }
            }
            try {
                FileWriter fw = new FileWriter(logdir+File.separator+"error.log",true);
                fw.write(new Date()+"\n");
                StackTraceElement[] stackTrace = arg1.getStackTrace();
                fw.write(arg1.getMessage() + "\n");
                for (int i = 0; i < stackTrace.length; i++) {
                    fw.write("file:" + stackTrace[i].getFileName() + " class:" + stackTrace[i].getClassName()
                            + " method:" + stackTrace[i].getMethodName() + " line:" + stackTrace[i].getLineNumber()
                            + "\n");
                }
                fw.write("\n");
                fw.close();
            } catch (IOException e) {
                Log.e("crash handler", "load file failed...", e.getCause());
            }
        }
        arg1.printStackTrace();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}