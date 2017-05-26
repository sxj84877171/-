package com.soarsky.car.server.wifi;


import android.util.Log;

import com.soarsky.car.server.cmd.BaseCmd;
import com.soarsky.car.server.design.IConnectListener;
import com.soarsky.car.server.design.OnCmdListener;
import com.soarsky.car.server.design.OnReaderThreadListener;
import com.soarsky.car.uitl.ByteUtil;
import com.soarsky.car.uitl.LogUtil;
import com.soarsky.car.uitl.WifiUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Andriod_Car_App<br>
 * 作者：何明辉<br>
 * 时间： 2016/11/15.<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：读取从其他地方过来的消息，并转换为对应的数据包<br>
 * 该类为 与终端通讯服务类<br>
 */


public class ReaderThread extends Thread {

    private InputStream inputStream;
    private boolean run = false;
    private OnCmdListener onCmdListener;
    private OnReaderThreadListener onReaderThreadListener;
//    private byte[] buffer = new byte[1024];
    private IConnectListener connectListener;
    private ByteArrayOutputStream buffer=new ByteArrayOutputStream();

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

    /**
     * 设置读取数据监听
     * @param onReaderThreadListener
     */
    public void setOnReaderThreadListener(OnReaderThreadListener onReaderThreadListener) {
        this.onReaderThreadListener = onReaderThreadListener;
    }


    /**
     * 读取终端返回数据
     */
    @Override
    public void run() {
        run = true;
        byte[] bytes = new byte[4*1024];
        int readLength = -1;
        int index = 0;
        while (run) {
            if (inputStream != null) {
                try {
                    readLength = inputStream.read(bytes);

                    if (readLength > 0) {
                        buffer.write(bytes,index,readLength);
                        index += readLength;
                        if (buffer.toByteArray()[0] == BaseCmd.MARK) {
                            int  msgid=BaseCmd.parseMsgId(bytes);
                            String hex = ByteUtil.bytearrayToHexString(bytes,bytes.length);
                            LogUtil.i(hex);
                            if (readLength > 10) {
                                int msgBodyLength = BaseCmd.parseLength(bytes);
//                                if(index >= msgBodyLength + 10){
                                    BaseCmd cmd = BaseCmd.parseCmd(bytes,readLength);
                                    if (cmd != null && onCmdListener != null) {
                                        onCmdListener.onNewCmd(cmd);
                                    }
//                                }
//                                buffer=new ByteArrayOutputStream();

//                                int length = BaseCmd.parseLength(buffer.toByteArray());
//                                index = index + readLength - length - 10;
//                                System.arraycopy(buffer, length + 10, bytes, 0, index);
//                                System.arraycopy(bytes, 0, buffer, 0, index);

                            }
                        }
                    } else {
                        throw new Exception("socket has closed.");
                    }

                } catch (Exception e) {

                    if(connectListener!=null){
                        connectListener.finsh();
                    }

                    if(onReaderThreadListener != null){
                        onReaderThreadListener.onfail("id=" + Thread.currentThread().getId() + "\r\n  readexception" + Log.getStackTraceString(e));
                    }
                    LogUtil.e(e);
                    WifiUtil.getInstance().recoverWifi();
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
