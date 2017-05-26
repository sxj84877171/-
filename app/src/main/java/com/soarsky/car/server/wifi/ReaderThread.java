package com.soarsky.car.server.wifi;


import android.util.Log;

import com.soarsky.car.server.cmd.BaseCmd;
import com.soarsky.car.server.design.IConnectListener;
import com.soarsky.car.server.design.OnCmdListener;
import com.soarsky.car.server.design.OnReaderThreadListener;
import com.soarsky.car.uitl.ByteUtil;
import com.soarsky.car.uitl.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Andriod_Car_App
 * 作者：何明辉
 * 时间： 2016/11/15.
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：读取从其他地方过来的消息，并转换为对应的数据包
 * 该类为 与终端通讯服务类
 */


public class ReaderThread extends Thread {

    private InputStream inputStream;
    private boolean run = false;
    private OnCmdListener onCmdListener;
    private OnReaderThreadListener onReaderThreadListener;
    private byte[] buffer = new byte[1024];
    private IConnectListener connectListener;

    /**
     * 一次数据交互完成监听
     * @param connectListener
     */
    public void setConnectListener(IConnectListener connectListener) {
        this.connectListener = connectListener;
    }

    /**
     * 设置监听器
     *
     * @param packetListener
     */
    public void setPacketListener(OnCmdListener packetListener) {
        this.onCmdListener = packetListener;
    }

    /**
     * 设置输入流
     *
     * @param inputStream
     */
    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }


    public OnReaderThreadListener getOnReaderThreadListener() {
        return onReaderThreadListener;
    }

    public void setOnReaderThreadListener(OnReaderThreadListener onReaderThreadListener) {
        this.onReaderThreadListener = onReaderThreadListener;
    }

    @Override
    public void run() {
        run = true;
         byte[] bytes = new byte[1024];
        int readLength = -1;
        int index = 0;
        while (run) {
            if (inputStream != null) {
                try {
                    LogUtil.i(new Date().getSeconds()+"--------1");
                    readLength = inputStream.read(bytes);

                    if (readLength > 0) {

                        System.arraycopy(bytes, 0, buffer, index, readLength);
                        index += readLength;
                        if (buffer[0] == BaseCmd.MARK) {
                            int  msgid=BaseCmd.parseMsgId(bytes);
                            String hex = ByteUtil.bytearrayToHexString(bytes,bytes.length);
                            LogUtil.i("收到的结果"+hex);
                            if (readLength > 10) {
                                int msgBodyLength = BaseCmd.parseLength(bytes);
                                if(index >= msgBodyLength + 10){
                                    BaseCmd cmd = BaseCmd.parseCmd(buffer);
                                    if (cmd != null && onCmdListener != null) {
                                        onCmdListener.onNewCmd(cmd);
                                        if (onReaderThreadListener != null) {
                                            onReaderThreadListener=null;
                                        }
                                    }
                                }
                                int length = BaseCmd.parseLength(buffer);
                                index = index + readLength - length - 10;
                                System.arraycopy(buffer, length + 10, bytes, 0, index);
                                System.arraycopy(bytes, 0, buffer, 0, index);

                            }
                        }
                    } else {
                        throw new Exception("socket has closed.");
                    }

                } catch (Exception e) {
                    LogUtil.i(new Date().getSeconds()+"---------------2");

                    if(connectListener!=null){
                        connectListener.finsh();
                    }

                    if(onReaderThreadListener != null){
                        onReaderThreadListener.onfail("id=" + Thread.currentThread().getId() + "\r\n  readexception" + Log.getStackTraceString(e));
                        onReaderThreadListener = null;
                    }
                    LogUtil.e(e);
                    close();
                }
            }
        }
    }


    /**
     * 判断数据中已经存在完整的数据包
     *
     * @param sb 数据源
     * @return 对应数据包的结束地址
     */
    private int getPacketEndIndex(StringBuilder sb) {
        //TODO
        return -1;
    }

    /**
     * 关闭并停止工作。
     */
    public void close() {
        run = false;
        if (inputStream != null) {
            try {
                inputStream.close();
                inputStream = null;
            } catch (IOException e) {
                LogUtil.e(e);
            }
        }
    }

}
