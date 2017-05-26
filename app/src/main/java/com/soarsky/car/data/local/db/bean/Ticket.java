package com.soarsky.car.data.local.db.bean;

import com.google.gson.Gson;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/8
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 罚单表实体类
 */
@Entity
public class Ticket {
    @Id
    private Long id;
    @NotNull
    /**
     *内容
     */
    private String content;
    /**
     * 是否同步服务器
     */
    private int serviceStatus;
    /**
     * 是否同步终端
     */
    private int terminalStatus;

    /**
     * 罚单编号
     */
    private  String TicketNo;
    /**
     *  车牌号
     */
    private  String  carNum;

    public int getTerminalStatus() {
        return this.terminalStatus;
    }
    public void setTerminalStatus(int terminalStatus) {
        this.terminalStatus = terminalStatus;
    }
    public int getServiceStatus() {
        return this.serviceStatus;
    }
    public void setServiceStatus(int serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCarNum() {
        return this.carNum;
    }
    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
    public String getTicketNo() {
        return this.TicketNo;
    }
    public void setTicketNo(String TicketNo) {
        this.TicketNo = TicketNo;
    }
    @Generated(hash = 977747484)
    public Ticket(Long id, @NotNull String content, int serviceStatus,
            int terminalStatus, String TicketNo, String carNum) {
        this.id = id;
        this.content = content;
        this.serviceStatus = serviceStatus;
        this.terminalStatus = terminalStatus;
        this.TicketNo = TicketNo;
        this.carNum = carNum;
    }
    @Generated(hash = 941848399)
    public Ticket() {
    }


    public static Ticket parse(String json){
        Gson gson = new Gson();
        return gson.fromJson(json,Ticket.class);
    }

    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}