package com.soarsky.car.server.design;

import java.io.InputStream;
import java.io.OutputStream;


/**
 * Andriod_Car_App
 * 作者： Elvis
 * 时间： 2016/11/3.
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：负责一个传输通道
 * 该类为 与终端通讯服务类
 */



public interface ITransfer {
    /**
     *开启传输通道
     */
    public void start();

    /**
     * 设置输出流
     * @param outputstream
     */
    public void setOutputstream(OutputStream outputstream);

    /**
     * 设置输入流
     * @param inputstream
     */
    public void setInputstream(InputStream inputstream);

    /**
     *销毁连接
     */
    public void destroy();

    /**
     * 是否已经被销毁
     */
    public boolean isDestroy();

    /**
     * 发送指令
     * @param cmd
     */
    public void sendCmd(ICommand cmd);

    /**
     * socket连接完成监听
     * @param connectListener
     */
    public  void setConnectListener(IConnectListener connectListener);

    /**
     * 监听收到的包信息
     * @param listener
     */
    public  void setOnPacketListener(OnCmdListener listener);
}
