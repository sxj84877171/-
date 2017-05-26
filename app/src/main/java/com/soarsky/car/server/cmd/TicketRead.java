package com.soarsky.car.server.cmd;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.NotificationCompat;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.data.local.db.bean.Ticket;
import com.soarsky.car.server.bluetooth.BlueToothManage;
import com.soarsky.car.server.bluetooth.BuleToothDataAnalyze;
import com.soarsky.car.server.design.ICommand;
import com.soarsky.car.ui.ticketed.TicketDb;
import com.soarsky.car.uitl.ByteUtil;
import com.soarsky.car.uitl.NotifictionUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2017/5/3
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 罚单的读取
 */


public class TicketRead {


    /**
     * 未读罚单总数
     */
    private int totalCount;
    /**
     * 第几次读取罚单
     */
    private int index = 0;
    /**
     * 每次读取多少条罚单
     */
    private int count = Constants.READTICKET_COUNT;


    /**
     * 需要读取几次罚单
     */

    private int needIndex;
    BlueToothManage blueToothManage;
    BuleToothDataAnalyze.Callback paramSetCallback;


    private int notifictionID = 102;
    /**
     *
     */
    private int syncindex;


    /**
     * 用来保存罚单编号的数组
     */
    private byte[] ticketNobytes;


    /**
     * 要同步的罚单条数
     */
    private int syncTicketCount;

    /**
     * 用来缓存罚单的集合
     */
    private List<Ticket> ticketList = new ArrayList<>();


    public TicketRead(int totalCount, BlueToothManage blueToothManage) {
        this.blueToothManage = blueToothManage;
        this.totalCount = totalCount;
        needIndex = totalCount / count;
        if (totalCount % count > 0) {
            needIndex = needIndex + 1;
        }
        readTicket();
    }


    /**
     * 读取罚单
     */
    private void readTicket() {

        if (index < needIndex) {
            blueToothManage.init(Constants.TICKET_UNREAD, (index * count));
        } else {
            paramSetCallback.onfinsh(1);
        }


    }


    public void setCmd(BaseCmd cmd) {
        if (cmd instanceof TicketCommandResponse) {
            TicketCommandResponse ticketCommandResponse = (TicketCommandResponse) cmd;
            if (ticketCommandResponse.getMessageType() != 0) {
            } else {
                List<Ticket> ticketList = ticketCommandResponse.getTicketList();
                for (Ticket ticket : ticketList) {
                    TicketDb.getInstance(App.getApp()).inserData(ticket);
                    NotifictionUtils.getInstance().showNotifiction("inno:" + ticket.getIno() + "  罚单类型：" + getInfString(ticket.getIno()));
                }
            }
            index++;
            readTicket();
        }


    }

    /**
     * 设置请求完成回调
     *
     * @param paramSetCallback
     */
    public void setParamSetCallback(BuleToothDataAnalyze.Callback paramSetCallback) {
        this.paramSetCallback = paramSetCallback;
    }


    /**
     * 解析违章类型
     */

    private String getInfString(String inf) {
        int index = StringToInt(inf);
        switch (index) {
            case 48:
                return "无证驾驶";
            case 49:
                return "滥用远光";
            case 50:
                return "没系安全带";
            case 51:
                return "违规使用安全带";
            case 52:
                return "超速";
            case 53:
                return "疲劳驾驶";
            case 54:
                return "大客车夜间违章运行";
            case 55:
                return "违反限行规定";
            case 56:
                return "违规使用喇叭";
            case 57:
                return "违停";
            case 97:
                return "开车玩手机";
            case 98:
                return "急停急走";

        }
        return "无证驾驶";
    }


    /**
     * String转Ascii数字
     *
     * @param str
     * @return
     */
    public static int StringToInt(String str) {
        char a = str.charAt(0);
        return a;
    }
}