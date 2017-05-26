package com.soarsky.car.server.wifi;


import com.soarsky.car.server.design.ICommand;
import com.soarsky.car.server.design.IConnectListener;
import com.soarsky.car.server.design.ITransfer;
import com.soarsky.car.server.design.OnCmdListener;
import com.soarsky.car.server.design.OnReaderThreadListener;
import com.soarsky.car.uitl.ByteUtil;
import com.soarsky.car.uitl.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 * Andriod_Car_App
 * 作者：Elvis
 * 时间： 2016/11/15.
 * 公司：长沙硕铠电子科技有限公司
 * Email：Elvis@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：wifi传输
 * 该类为 与终端通讯服务类
 */

public class WifiTransfer implements ITransfer {
    private InputStream inputStream;
    private OutputStream outputStream;
    private boolean destroyed = false;
    private boolean start = false;
    private OnCmdListener packetListener;
    private ReaderThread readerThread;
    private OnReaderThreadListener onReaderThreadListener;
    private IConnectListener connectListener;



    public WifiTransfer(OnReaderThreadListener onReaderThreadListener){
       this.onReaderThreadListener=onReaderThreadListener;
    }

    /**
     *
     */
    @Override
    public void start() {
        readerThread = new ReaderThread();
        if (packetListener != null) {
            readerThread.setPacketListener(packetListener);

        }
        if(onReaderThreadListener!=null){
            readerThread.setOnReaderThreadListener(onReaderThreadListener);
        }
        if (inputStream != null) {
            readerThread.setInputStream(inputStream);
        }
        if(connectListener!=null){
            readerThread.setConnectListener(connectListener);

        }
        readerThread.start();
    }

    /**
     *
     * @param outputstream
     */
    @Override
    public void setOutputstream(OutputStream outputstream) {
        this.outputStream = outputstream;
    }

    /**
     *
     * @param inputstream
     */
    @Override
    public void setInputstream(InputStream inputstream) {
        this.inputStream = inputstream;
    }

    /**
     *
     */
    @Override
    public void destroy() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        destroyed = true;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isDestroy() {
        return destroyed;
    }

    @Override
    public void sendCmd(ICommand cmd) {

        if (cmd != null) {
            if (outputStream != null) {
                try {
                    //TODO  这里会超时
                    byte[] bytes = cmd.toBytes();
                    outputStream.write(bytes);
                    LogUtil.i("发送的数据："+ByteUtil.bytearrayToHexString(bytes,bytes.length));
                    outputStream.flush();
                } catch (IOException e) {
                    onReaderThreadListener.onfail("sendCmd");
                    LogUtil.e(e);
                }
            }
        }
    }

    /**
     * @param listener
     */
    @Override
    public void setOnPacketListener(OnCmdListener listener) {
        this.packetListener = listener;
        if (readerThread != null) {
            readerThread.setPacketListener(listener);
        }
    }


    @Override
    public void setConnectListener(IConnectListener connectListener) {
        this.connectListener = connectListener;
        if (readerThread != null) {
            readerThread.setConnectListener(connectListener);
        }
    }
}
