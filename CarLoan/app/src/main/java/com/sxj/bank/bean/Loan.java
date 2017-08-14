package com.sxj.bank.bean;

import java.io.Serializable;

/**
 * CarLoan
 * 作者： Elvis
 * 时间： 2017/6/26
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class Loan implements Serializable{

    public static final String ID_KEY = "主键";
    public static final String CASETYPEID_KEY = "业务类型";
    public static final String CUSTNAMETMP_KEY = "申请人";
    public static final String CUST_SEX_KEY = "性别";
    public static final String CUST_IDEN_KEY = "身份证号";


    private String id;

    /**
     * 业务类型
     */
    private String caseTypeId;

    /**
     * 申请人
     */
    private String custNameTmp;

    /**
     * 婚姻状况
     */
    private String cust_marriage_id;

    /**
     * 手机号码
     */
    private String cust_mobile;

    /**
     * 是否共偿
     */
    private String if_gcr_id;

    /**
     * 房产类别
     */
    private String room_type_id;

    /**
     * 家访日期
     */
    private String home_visit_date;

    /**
     * 家访地址
     */
    private String cust_address;

    /***
     * 车型
     */
    private String car_type;

    /**
     * 成交价
     */
    private String deal_price;

    /**
     * 贷款期限
     */
    private String credit_years;

    /**
     * 分期类型
     */
    private String installment_type_id;

    /**
     * 业务员ID
     */
    private String user_id_ywy;

    /**
     * 建立日期
     */
    private String date_ywy;

    /**
     * 业务状态
     */
    private String case_state_id;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCaseTypeId() {
        return caseTypeId;
    }

    public void setCaseTypeId(String caseTypeId) {
        this.caseTypeId = caseTypeId;
    }

    public String getCustNameTmp() {
        return custNameTmp;
    }

    public void setCustNameTmp(String custNameTmp) {
        this.custNameTmp = custNameTmp;
    }

    public String getCust_marriage_id() {
        return cust_marriage_id;
    }

    public void setCust_marriage_id(String cust_marriage_id) {
        this.cust_marriage_id = cust_marriage_id;
    }

    public String getCust_mobile() {
        return cust_mobile;
    }

    public void setCust_mobile(String cust_mobile) {
        this.cust_mobile = cust_mobile;
    }

    public String getIf_gcr_id() {
        return if_gcr_id;
    }

    public void setIf_gcr_id(String if_gcr_id) {
        this.if_gcr_id = if_gcr_id;
    }

    public String getRoom_type_id() {
        return room_type_id;
    }

    public void setRoom_type_id(String room_type_id) {
        this.room_type_id = room_type_id;
    }

    public String getHome_visit_date() {
        return home_visit_date;
    }

    public void setHome_visit_date(String home_visit_date) {
        this.home_visit_date = home_visit_date;
    }

    public String getCust_address() {
        return cust_address;
    }

    public void setCust_address(String cust_address) {
        this.cust_address = cust_address;
    }

    public String getCar_type() {
        return car_type;
    }

    public void setCar_type(String car_type) {
        this.car_type = car_type;
    }

    public String getDeal_price() {
        return deal_price;
    }

    public void setDeal_price(String deal_price) {
        this.deal_price = deal_price;
    }

    public String getCredit_years() {
        return credit_years;
    }

    public void setCredit_years(String credit_years) {
        this.credit_years = credit_years;
    }

    public String getInstallment_type_id() {
        return installment_type_id;
    }

    public void setInstallment_type_id(String installment_type_id) {
        this.installment_type_id = installment_type_id;
    }

    public String getUser_id_ywy() {
        return user_id_ywy;
    }

    public void setUser_id_ywy(String user_id_ywy) {
        this.user_id_ywy = user_id_ywy;
    }

    public String getDate_ywy() {
        return date_ywy;
    }

    public void setDate_ywy(String date_ywy) {
        this.date_ywy = date_ywy;
    }

    public String getCase_state_id() {
        return case_state_id;
    }

    public void setCase_state_id(String case_state_id) {
        this.case_state_id = case_state_id;
    }
}
