package com.sxj.carloan.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by admin on 2017/7/3.
 */

public class FileObject {

    public final static String CACHE_PATH = "/sdcard/loan/cache/" ;

    public static void saveObject(String filename , Object o){
        File path = new File(CACHE_PATH);
        if(!path.exists()){
            boolean success = path.mkdirs();
            if(!success){
                return;
            }
        }
        File file = new File(CACHE_PATH + filename + ".tmp");
        ObjectOutputStream objectOutputStream = null;
        try{
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(o);
            objectOutputStream.flush();
        }catch (Exception ex){
            LogUtil.e(ex);
        }finally {
            if(objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    public static Object getObject(String filename){
        File file = new File(CACHE_PATH + filename+ ".tmp");
        ObjectInputStream objectInputStream = null;
        if(file.exists()){
            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(file));
                return objectInputStream.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                if(objectInputStream != null){
                    try {
                        objectInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }


    public static void cleanFile(String filename){
        File file = new File(CACHE_PATH + filename + ".tmp");
        if(file.exists()){
            file.delete();
        }
    }
}
