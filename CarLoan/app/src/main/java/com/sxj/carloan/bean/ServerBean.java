package com.sxj.carloan.bean;

import java.io.Serializable;
import java.util.List;

/**
 * CarLoan
 * 作者： Elvis
 * 时间： 2017/6/27
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class ServerBean implements Serializable{

/*

{
    "total": 1,
    "rows": [
        {
            "zh_sh_card_iden": null,
            "user_id_gps": 0,
            "earlier_interest_fee_1": null,
            "user_id_loan_cw": 0,
            "fk_zg_result_id": 0,
            "user_id_dcy": 0,
            "fdb_iden": null,
            "card_deposit_fee_1": null,
            "salary_info": null,
            "loan_cw_result_id": 0,
            "user_id_nq": 0,
            "id": 1,
            "cw_sk_info": null,
            "user_id_dispatch": 0,
            "baoxian_fee": null,
            "loan_amount_fk_zg": null,
            "date_bgnq_info": null,
            "loan_amount": null,
            "date_gps": null,
            "date_zh_sh_sign1": null,
            "cust_address": null,
            "fk_result_id": 0,
            "car_used_km": null,
            "room_info": null,
            "date_zh_lk": null,
            "user_id_zh_lk": 0,
            "date_zh_te": null,
            "date_certificate_finished": null,
            "gps_info": null,
            "date_fk": null,
            "yw_pf_info": null,
            "fee_rate": null,
            "evaluation_price_dcy": null,
            "date_fee_nq_1": null,
            "user_id_fee_cw": 0,
            "zh_sh_yh_jl2": null,
            "dcy_result_id": 0,
            "user_id_ds": 0,
            "user_id_fk": 0,
            "user_id_certificate": 0,
            "cust_sex": null,
            "date_gps_finished": null,
            "earlier_fee": null,
            "car_info": null,
            "car_used_month": null,
            "date_dispatch": null,
            "invoice_price": null,
            "payment_for_agency": null,
            "zh_te_info": null,
            "date_zhengxin": null,
            "cust_mobile": "",
            "date_cw_sk": null,
            "ds_result_id": 0,
            "yw_pf_result_id": 0,
            "fk_info": null,
            "date_zh_sh_sign2": null,
            "if_gcr_id": 0,//是否共偿 0 表示否 1 表示是
            "date_ds": null,
            "date_ywy": null,
            "fk_zg_info": null,
            "date_zh_lk_finished": null,
            "sh_info": null,
            "zh_te_result_id": 0,
            "date_loan_cw": null,
            "commercial_insurance": null,
            "date_ywy_qk": null,
            "date_zh_ss": null,
            "date_baoxian": null,
            "case_ryp_id": 0,
            "user_id_ywy": 0,
            "date_dcy_info": null,
            "payback_month_12": null,
            "date_zh_ss_finished": null,
            "yw_sp_result_id": 0,
            "user_id_fk_zg": 0,
            "user_id_sh": 0,
            "row_num": 1,
            "date_car_photo": null,
            "user_id_baoxian": 0,
            "cw_sk_result_id": 0,
            "fee_return_custom": null,
            "deposit": null,
            "loan_amount_ywy_low_1": null,
            "user_id_zh_te": 0,
            "zh_ss_info": null,
            "yw_sp_info": null,
            "fee_rate_advance": null,
            "date_zhengxin_finished": null,
            "date_certificate": null,
            "case_type_id_1": 0,
            "installment_type_id_1": 0,
            "lsh": null,
            "date_dispatch_finished": null,
            "loan_amount_ywy": null,
            "date_cw_sk_finished": null,
            "nq_state_id": 0,
            "gps_state_id": 0,
            "home_visit_date": "",
            "cust_marriage_id": 0,
            "date_yw_pf": null,
            "interest_company": null,
            "loan_amount_fk": null,
            "date_baoxian_finished": null,
            "date_zh_sh_sign3": null,
            "deal_price": 0,
            "advance_payment_1": null,
            "fee_return_agency": null,
            "cust_gtczr_relation": null,
            "evaluation_price": null,
            "cust_name_tmp": "",
            "loan_advance_percent": null,
            "date_zh_sh_card": null,
            "zh_lk_info": null,
            "user_id_bgnq_info": 0,
            "user_id_yw_pf": 0,
            "date_ds_finished": null,
            "date_zh_te_finished": null,
            "car_new_company": null,
            "if_need_yw_sp": 0,
            "date_nq": null,
            "dcy_info": null,
            "zhengxin_result_id": 0,
            "gps_fee": null,
            "date_dcy": null,
            "date_zh_sh": null,
            "zhengxin_info": null,
            "car_type": "",
            "fdb_address": null,
            "chehang_address": null,
            "certificate_info": null,
            "outcome_info": null,
            "product_id": null,
            "fdb_mobile": null,
            "fdb_unit_name": null,
            "mortgage_fee": null,
            "if_need_fk_zg": 0,
            "payback_month": null,
            "user_id_zhengxin": 0,
            "user_id_dcy_info": 0,
            "date_yw_sp": null,
            "zh_sh_info": null,
            "home_visit_fee": null,
            "credit_years": 0,
            "date_sh": null,
            "loan_amount_yw_sp": null,
            "user_id_car_photo": 0,
            "certificate_state_id": 0,
            "room_type_id": 0,
            "date_dcy_yw": null,
            "cust_iden": "",
            "chehang_name": null,
            "interest_bank": null,
            "loan_amount_high": null,
            "date_case": null,
            "car_iden": null,
            "date_loan_cw_finished": null,
            "fdb_name": null,
            "user_id_cw_sk": 0,
            "user_id_yw_sp": 0,
            "fee_total": null,
            "baoxian_info": null,
            "user_id_zh_ss": 0,
            "date_fee_cw": null,
            "report_state_id": 0,
            "date_fk_zg": null,
            "zh_sh_result_id": 0,
            "ds_info": null,
            "extras_fee": null,
            "zh_ss_result_id": 0,
            "outcome_salary": 0,
            "zh_lk_result_id": 0,
            "evaluation_fee": null,
            "user_id_fee_nq_1": 0,
            "user_id_zh_sh": 0,
            "service_fee": null,
            "fee_rate_balance": null,
            "loan_amount_dcy": null,
            "zh_lk_yh_jl1": null,
            "case_state_id": 0,
            "loan_amount_ywy_corp": null,
            "commercial_insurance_return": null,
            "cust_children": null
        }
    ]
}


业务类型  case_type_id
分期类型  installment_type_id
选择产品  product_id
申请人    cust_name_tmp
身份证号  cust_iden
性别      cust_sex  男或女
婚姻状况  cust_marriage_id
手机号码  cust_mobile
是否共偿  if_gcr_id  <option value='0'>否</option> <option value='1'>是</option>
家访地址  cust_address
家访日期  home_visit_date
房产类别  room_type_id [{ id: 1, text: "全款商品房" }, { id: 2, text: '按揭商品房' }, { id: 3, text: '自建房' }, { id: 4, text: '租房' }, { id: 5, text: '亲属住房'}];


车行名称  chehang_name
车行地址  chehang_address
车型  car_type
成交价deal_price
发票金额invoice_price
公司贷款额loan_amount_ywy_corp
贷款金额loan_amount_ywy
银行申报金额loan_amount_high
银行利息interest_bank
前置利息interest_company
贷款期限credit_years
首付比例loan_advance_percent
费率fee_rate
首付费率fee_rate_advance
余额费率fee_rate_balance
保证金deposit
月供[前12月]payback_month_12
月供payback_month
调整项extras_fee
流程保证金及服务费service_fee
GPS费gps_fee
抵押费mortgage_fee
前期利息earlier_interest_fee
家访费home_visit_fee
上牌保证金card_deposit_fee
保险费baoxian_fee
评估费evaluation_fee
前期收取费earlier_fee
返经销商fee_return_agency
合计fee_total
客户履约后退款fee_return_custom
商业险commercial_insurance
商业险返commercial_insurance_return
付经销商合计payment_for_agency

 */


    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean implements Serializable {
        private String car_used_month;
        private int case_type_id_1;
        private int user_id_ds;
        private int gps_state_id;
        private String date_case;
        private String cust_gtczr_relation;
        private String date_dcy_info;
        private String date_zh_ss_finished;
        private int installment_type_id_1;
        private String sh_info;
        private String extras_fee;
        private String zhengxin_info;
        private int row_num;
        private int case_state_id;
        private String baoxian_info;
        private String date_dispatch_finished;
        private String dcy_info;
        private String product_id;
        private int id;
        private String date_certificate;
        private int user_id_bgnq_info;
        private String commercial_insurance_return;
        private int fk_zg_result_id;
        private int ds_result_id;
        private String loan_amount_ywy_low_1;
        private String yw_sp_info;
        private int nq_state_id;
        private int if_need_yw_sp;
        private double deal_price;
        private String date_dcy;
        private String home_visit_fee;
        private int room_type_id;
        private String salary_info;
        private String cust_name_tmp;
        private String gps_fee;
        private String fdb_address;
        private String fdb_mobile;
        private String payback_month_12;
        private String date_yw_sp;
        private String date_certificate_finished;
        private String commercial_insurance;
        private String deposit;
        private String car_used_km;
        private int user_id_sh;
        private String fee_return_agency;
        private String date_fee_cw;
        private int user_id_baoxian;
        private String cust_children;
        private int zh_sh_result_id;
        private String date_zh_sh_sign3;
        private String evaluation_fee;
        private int user_id_fk;
        private String chehang_name;
        private int user_id_nq;
        private String date_zh_sh_sign2;
        private String date_bgnq_info;
        private String date_zh_sh_sign1;
        private String certificate_info;
        private int user_id_zh_lk;
        private String fk_zg_info;
        private int outcome_salary;
        private int user_id_fee_cw;
        private String fk_info;
        private int user_id_certificate;
        private String date_zh_lk_finished;
        private String cust_iden;
        private String cw_sk_info;
        private String cust_sex;
        private String loan_amount_ywy_corp;
        private String date_zh_sh_card;
        private String cust_address;
        private String date_loan_cw;
        private String home_visit_date;
        private String advance_payment_1;
        private int user_id_gps;
        private String zh_ss_info;
        private int zh_ss_result_id;
        private String date_cw_sk;
        private int zh_te_result_id;
        private String chehang_address;
        private String date_fk_zg;
        private String zh_lk_yh_jl1;
        private int user_id_dispatch;
        private String fdb_unit_name;
        private String date_gps;
        private String zh_sh_card_iden;
        private String invoice_price;
        private String yw_pf_info;
        private String car_info;
        private String baoxian_fee;
        private int user_id_car_photo;
        private String date_sh;
        private String zh_sh_yh_jl2;
        private int cw_sk_result_id;
        private int user_id_zh_sh;
        private int yw_sp_result_id;
        private String loan_amount_dcy;
        private String cust_mobile;
        private int user_id_fk_zg;
        private int user_id_zhengxin;
        private String car_type;
        private int report_state_id;
        private String date_dcy_yw;
        private int zhengxin_result_id;
        private String evaluation_price_dcy;
        private int cust_marriage_id;
        private String date_dispatch;
        private String fee_return_custom;
        private int dcy_result_id;
        private String date_yw_pf;
        private String car_new_company;
        private int user_id_zh_te;
        private int user_id_yw_sp;
        private int loan_cw_result_id;
        private String fee_rate_balance;
        private String card_deposit_fee_1;
        private int fk_result_id;
        private String loan_amount;
        private String date_baoxian_finished;
        private String payment_for_agency;
        private String fee_rate;
        private int case_ryp_id;
        private String interest_company;
        private String loan_amount_yw_sp;
        private int zh_lk_result_id;
        private String fdb_iden;
        private int certificate_state_id;
        private int user_id_zh_ss;
        private String date_zh_sh;
        private String payback_month;
        private String date_gps_finished;
        private String earlier_interest_fee_1;
        private String evaluation_price;
        private String mortgage_fee;
        private String date_baoxian;
        private int user_id_dcy;
        private String loan_amount_fk;
        private String date_car_photo;
        private String date_zh_ss;
        private String lsh;
        private String outcome_info;
        private int if_need_fk_zg;
        private String earlier_fee;
        private String ds_info;
        private int user_id_fee_nq_1;
        private String date_ds;
        private int user_id_cw_sk;
        private String loan_amount_ywy;
        private String room_info;
        private String car_iden;
        private String fee_rate_advance;
        private int user_id_yw_pf;
        private String date_zh_te;
        private String date_cw_sk_finished;
        private String fdb_name;
        private String date_zh_te_finished;
        private String date_ywy_qk;
        private String zh_lk_info;
        private String date_ds_finished;
        private String zh_te_info;
        private String date_zh_lk;
        private String gps_info;
        private String date_loan_cw_finished;
        private int if_gcr_id;
        private int yw_pf_result_id;
        private String loan_amount_high;
        private int user_id_loan_cw;
        private String fee_total;
        private String service_fee;
        private String loan_amount_fk_zg;
        private String date_ywy;
        private String zh_sh_info;
        private int user_id_dcy_info;
        private String date_fee_nq_1;
        private String date_fk;
        private int credit_years;
        private String date_zhengxin;
        private String loan_advance_percent;
        private String date_nq;
        private String interest_bank;
        private String date_zhengxin_finished;
        private int user_id_ywy;

        public String getCar_used_month() {
            return car_used_month;
        }

        public void setCar_used_month(String car_used_month) {
            this.car_used_month = car_used_month;
        }

        public int getCase_type_id_1() {
            return case_type_id_1;
        }

        public void setCase_type_id_1(int case_type_id_1) {
            this.case_type_id_1 = case_type_id_1;
        }

        public int getUser_id_ds() {
            return user_id_ds;
        }

        public void setUser_id_ds(int user_id_ds) {
            this.user_id_ds = user_id_ds;
        }

        public int getGps_state_id() {
            return gps_state_id;
        }

        public void setGps_state_id(int gps_state_id) {
            this.gps_state_id = gps_state_id;
        }

        public String getDate_case() {
            return date_case;
        }

        public void setDate_case(String date_case) {
            this.date_case = date_case;
        }

        public String getCust_gtczr_relation() {
            return cust_gtczr_relation;
        }

        public void setCust_gtczr_relation(String cust_gtczr_relation) {
            this.cust_gtczr_relation = cust_gtczr_relation;
        }

        public String getDate_dcy_info() {
            return date_dcy_info;
        }

        public void setDate_dcy_info(String date_dcy_info) {
            this.date_dcy_info = date_dcy_info;
        }

        public String getDate_zh_ss_finished() {
            return date_zh_ss_finished;
        }

        public void setDate_zh_ss_finished(String date_zh_ss_finished) {
            this.date_zh_ss_finished = date_zh_ss_finished;
        }

        public int getInstallment_type_id_1() {
            return installment_type_id_1;
        }

        public void setInstallment_type_id_1(int installment_type_id_1) {
            this.installment_type_id_1 = installment_type_id_1;
        }

        public String getSh_info() {
            return sh_info;
        }

        public void setSh_info(String sh_info) {
            this.sh_info = sh_info;
        }

        public String getExtras_fee() {
            return extras_fee;
        }

        public void setExtras_fee(String extras_fee) {
            this.extras_fee = extras_fee;
        }

        public String getZhengxin_info() {
            return zhengxin_info;
        }

        public void setZhengxin_info(String zhengxin_info) {
            this.zhengxin_info = zhengxin_info;
        }

        public int getRow_num() {
            return row_num;
        }

        public void setRow_num(int row_num) {
            this.row_num = row_num;
        }

        public int getCase_state_id() {
            return case_state_id;
        }

        public void setCase_state_id(int case_state_id) {
            this.case_state_id = case_state_id;
        }

        public String getBaoxian_info() {
            return baoxian_info;
        }

        public void setBaoxian_info(String baoxian_info) {
            this.baoxian_info = baoxian_info;
        }

        public String getDate_dispatch_finished() {
            return date_dispatch_finished;
        }

        public void setDate_dispatch_finished(String date_dispatch_finished) {
            this.date_dispatch_finished = date_dispatch_finished;
        }

        public String getDcy_info() {
            return dcy_info;
        }

        public void setDcy_info(String dcy_info) {
            this.dcy_info = dcy_info;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDate_certificate() {
            return date_certificate;
        }

        public void setDate_certificate(String date_certificate) {
            this.date_certificate = date_certificate;
        }

        public int getUser_id_bgnq_info() {
            return user_id_bgnq_info;
        }

        public void setUser_id_bgnq_info(int user_id_bgnq_info) {
            this.user_id_bgnq_info = user_id_bgnq_info;
        }

        public String getCommercial_insurance_return() {
            return commercial_insurance_return;
        }

        public void setCommercial_insurance_return(String commercial_insurance_return) {
            this.commercial_insurance_return = commercial_insurance_return;
        }

        public int getFk_zg_result_id() {
            return fk_zg_result_id;
        }

        public void setFk_zg_result_id(int fk_zg_result_id) {
            this.fk_zg_result_id = fk_zg_result_id;
        }

        public int getDs_result_id() {
            return ds_result_id;
        }

        public void setDs_result_id(int ds_result_id) {
            this.ds_result_id = ds_result_id;
        }

        public String getLoan_amount_ywy_low_1() {
            return loan_amount_ywy_low_1;
        }

        public void setLoan_amount_ywy_low_1(String loan_amount_ywy_low_1) {
            this.loan_amount_ywy_low_1 = loan_amount_ywy_low_1;
        }

        public String getYw_sp_info() {
            return yw_sp_info;
        }

        public void setYw_sp_info(String yw_sp_info) {
            this.yw_sp_info = yw_sp_info;
        }

        public int getNq_state_id() {
            return nq_state_id;
        }

        public void setNq_state_id(int nq_state_id) {
            this.nq_state_id = nq_state_id;
        }

        public int getIf_need_yw_sp() {
            return if_need_yw_sp;
        }

        public void setIf_need_yw_sp(int if_need_yw_sp) {
            this.if_need_yw_sp = if_need_yw_sp;
        }

        public double getDeal_price() {
            return deal_price;
        }

        public void setDeal_price(double deal_price) {
            this.deal_price = deal_price;
        }

        public String getDate_dcy() {
            return date_dcy;
        }

        public void setDate_dcy(String date_dcy) {
            this.date_dcy = date_dcy;
        }

        public String getHome_visit_fee() {
            return home_visit_fee;
        }

        public void setHome_visit_fee(String home_visit_fee) {
            this.home_visit_fee = home_visit_fee;
        }

        public int getRoom_type_id() {
            return room_type_id;
        }

        public void setRoom_type_id(int room_type_id) {
            this.room_type_id = room_type_id;
        }

        public String getSalary_info() {
            return salary_info;
        }

        public void setSalary_info(String salary_info) {
            this.salary_info = salary_info;
        }

        public String getCust_name_tmp() {
            return cust_name_tmp;
        }

        public void setCust_name_tmp(String cust_name_tmp) {
            this.cust_name_tmp = cust_name_tmp;
        }

        public String getGps_fee() {
            return gps_fee;
        }

        public void setGps_fee(String gps_fee) {
            this.gps_fee = gps_fee;
        }

        public String getFdb_address() {
            return fdb_address;
        }

        public void setFdb_address(String fdb_address) {
            this.fdb_address = fdb_address;
        }

        public String getFdb_mobile() {
            return fdb_mobile;
        }

        public void setFdb_mobile(String fdb_mobile) {
            this.fdb_mobile = fdb_mobile;
        }

        public String getPayback_month_12() {
            return payback_month_12;
        }

        public void setPayback_month_12(String payback_month_12) {
            this.payback_month_12 = payback_month_12;
        }

        public String getDate_yw_sp() {
            return date_yw_sp;
        }

        public void setDate_yw_sp(String date_yw_sp) {
            this.date_yw_sp = date_yw_sp;
        }

        public String getDate_certificate_finished() {
            return date_certificate_finished;
        }

        public void setDate_certificate_finished(String date_certificate_finished) {
            this.date_certificate_finished = date_certificate_finished;
        }

        public String getCommercial_insurance() {
            return commercial_insurance;
        }

        public void setCommercial_insurance(String commercial_insurance) {
            this.commercial_insurance = commercial_insurance;
        }

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public String getCar_used_km() {
            return car_used_km;
        }

        public void setCar_used_km(String car_used_km) {
            this.car_used_km = car_used_km;
        }

        public int getUser_id_sh() {
            return user_id_sh;
        }

        public void setUser_id_sh(int user_id_sh) {
            this.user_id_sh = user_id_sh;
        }

        public String getFee_return_agency() {
            return fee_return_agency;
        }

        public void setFee_return_agency(String fee_return_agency) {
            this.fee_return_agency = fee_return_agency;
        }

        public String getDate_fee_cw() {
            return date_fee_cw;
        }

        public void setDate_fee_cw(String date_fee_cw) {
            this.date_fee_cw = date_fee_cw;
        }

        public int getUser_id_baoxian() {
            return user_id_baoxian;
        }

        public void setUser_id_baoxian(int user_id_baoxian) {
            this.user_id_baoxian = user_id_baoxian;
        }

        public String getCust_children() {
            return cust_children;
        }

        public void setCust_children(String cust_children) {
            this.cust_children = cust_children;
        }

        public int getZh_sh_result_id() {
            return zh_sh_result_id;
        }

        public void setZh_sh_result_id(int zh_sh_result_id) {
            this.zh_sh_result_id = zh_sh_result_id;
        }

        public String getDate_zh_sh_sign3() {
            return date_zh_sh_sign3;
        }

        public void setDate_zh_sh_sign3(String date_zh_sh_sign3) {
            this.date_zh_sh_sign3 = date_zh_sh_sign3;
        }

        public String getEvaluation_fee() {
            return evaluation_fee;
        }

        public void setEvaluation_fee(String evaluation_fee) {
            this.evaluation_fee = evaluation_fee;
        }

        public int getUser_id_fk() {
            return user_id_fk;
        }

        public void setUser_id_fk(int user_id_fk) {
            this.user_id_fk = user_id_fk;
        }

        public String getChehang_name() {
            return chehang_name;
        }

        public void setChehang_name(String chehang_name) {
            this.chehang_name = chehang_name;
        }

        public int getUser_id_nq() {
            return user_id_nq;
        }

        public void setUser_id_nq(int user_id_nq) {
            this.user_id_nq = user_id_nq;
        }

        public String getDate_zh_sh_sign2() {
            return date_zh_sh_sign2;
        }

        public void setDate_zh_sh_sign2(String date_zh_sh_sign2) {
            this.date_zh_sh_sign2 = date_zh_sh_sign2;
        }

        public String getDate_bgnq_info() {
            return date_bgnq_info;
        }

        public void setDate_bgnq_info(String date_bgnq_info) {
            this.date_bgnq_info = date_bgnq_info;
        }

        public String getDate_zh_sh_sign1() {
            return date_zh_sh_sign1;
        }

        public void setDate_zh_sh_sign1(String date_zh_sh_sign1) {
            this.date_zh_sh_sign1 = date_zh_sh_sign1;
        }

        public String getCertificate_info() {
            return certificate_info;
        }

        public void setCertificate_info(String certificate_info) {
            this.certificate_info = certificate_info;
        }

        public int getUser_id_zh_lk() {
            return user_id_zh_lk;
        }

        public void setUser_id_zh_lk(int user_id_zh_lk) {
            this.user_id_zh_lk = user_id_zh_lk;
        }

        public String getFk_zg_info() {
            return fk_zg_info;
        }

        public void setFk_zg_info(String fk_zg_info) {
            this.fk_zg_info = fk_zg_info;
        }

        public int getOutcome_salary() {
            return outcome_salary;
        }

        public void setOutcome_salary(int outcome_salary) {
            this.outcome_salary = outcome_salary;
        }

        public int getUser_id_fee_cw() {
            return user_id_fee_cw;
        }

        public void setUser_id_fee_cw(int user_id_fee_cw) {
            this.user_id_fee_cw = user_id_fee_cw;
        }

        public String getFk_info() {
            return fk_info;
        }

        public void setFk_info(String fk_info) {
            this.fk_info = fk_info;
        }

        public int getUser_id_certificate() {
            return user_id_certificate;
        }

        public void setUser_id_certificate(int user_id_certificate) {
            this.user_id_certificate = user_id_certificate;
        }

        public String getDate_zh_lk_finished() {
            return date_zh_lk_finished;
        }

        public void setDate_zh_lk_finished(String date_zh_lk_finished) {
            this.date_zh_lk_finished = date_zh_lk_finished;
        }

        public String getCust_iden() {
            return cust_iden;
        }

        public void setCust_iden(String cust_iden) {
            this.cust_iden = cust_iden;
        }

        public String getCw_sk_info() {
            return cw_sk_info;
        }

        public void setCw_sk_info(String cw_sk_info) {
            this.cw_sk_info = cw_sk_info;
        }

        public String getCust_sex() {
            return cust_sex;
        }

        public void setCust_sex(String cust_sex) {
            this.cust_sex = cust_sex;
        }

        public String getLoan_amount_ywy_corp() {
            return loan_amount_ywy_corp;
        }

        public void setLoan_amount_ywy_corp(String loan_amount_ywy_corp) {
            this.loan_amount_ywy_corp = loan_amount_ywy_corp;
        }

        public String getDate_zh_sh_card() {
            return date_zh_sh_card;
        }

        public void setDate_zh_sh_card(String date_zh_sh_card) {
            this.date_zh_sh_card = date_zh_sh_card;
        }

        public String getCust_address() {
            return cust_address;
        }

        public void setCust_address(String cust_address) {
            this.cust_address = cust_address;
        }

        public String getDate_loan_cw() {
            return date_loan_cw;
        }

        public void setDate_loan_cw(String date_loan_cw) {
            this.date_loan_cw = date_loan_cw;
        }

        public String getHome_visit_date() {
            return home_visit_date;
        }

        public void setHome_visit_date(String home_visit_date) {
            this.home_visit_date = home_visit_date;
        }

        public String getAdvance_payment_1() {
            return advance_payment_1;
        }

        public void setAdvance_payment_1(String advance_payment_1) {
            this.advance_payment_1 = advance_payment_1;
        }

        public int getUser_id_gps() {
            return user_id_gps;
        }

        public void setUser_id_gps(int user_id_gps) {
            this.user_id_gps = user_id_gps;
        }

        public String getZh_ss_info() {
            return zh_ss_info;
        }

        public void setZh_ss_info(String zh_ss_info) {
            this.zh_ss_info = zh_ss_info;
        }

        public int getZh_ss_result_id() {
            return zh_ss_result_id;
        }

        public void setZh_ss_result_id(int zh_ss_result_id) {
            this.zh_ss_result_id = zh_ss_result_id;
        }

        public String getDate_cw_sk() {
            return date_cw_sk;
        }

        public void setDate_cw_sk(String date_cw_sk) {
            this.date_cw_sk = date_cw_sk;
        }

        public int getZh_te_result_id() {
            return zh_te_result_id;
        }

        public void setZh_te_result_id(int zh_te_result_id) {
            this.zh_te_result_id = zh_te_result_id;
        }

        public String getChehang_address() {
            return chehang_address;
        }

        public void setChehang_address(String chehang_address) {
            this.chehang_address = chehang_address;
        }

        public String getDate_fk_zg() {
            return date_fk_zg;
        }

        public void setDate_fk_zg(String date_fk_zg) {
            this.date_fk_zg = date_fk_zg;
        }

        public String getZh_lk_yh_jl1() {
            return zh_lk_yh_jl1;
        }

        public void setZh_lk_yh_jl1(String zh_lk_yh_jl1) {
            this.zh_lk_yh_jl1 = zh_lk_yh_jl1;
        }

        public int getUser_id_dispatch() {
            return user_id_dispatch;
        }

        public void setUser_id_dispatch(int user_id_dispatch) {
            this.user_id_dispatch = user_id_dispatch;
        }

        public String getFdb_unit_name() {
            return fdb_unit_name;
        }

        public void setFdb_unit_name(String fdb_unit_name) {
            this.fdb_unit_name = fdb_unit_name;
        }

        public String getDate_gps() {
            return date_gps;
        }

        public void setDate_gps(String date_gps) {
            this.date_gps = date_gps;
        }

        public String getZh_sh_card_iden() {
            return zh_sh_card_iden;
        }

        public void setZh_sh_card_iden(String zh_sh_card_iden) {
            this.zh_sh_card_iden = zh_sh_card_iden;
        }

        public String getInvoice_price() {
            return invoice_price;
        }

        public void setInvoice_price(String invoice_price) {
            this.invoice_price = invoice_price;
        }

        public String getYw_pf_info() {
            return yw_pf_info;
        }

        public void setYw_pf_info(String yw_pf_info) {
            this.yw_pf_info = yw_pf_info;
        }

        public String getCar_info() {
            return car_info;
        }

        public void setCar_info(String car_info) {
            this.car_info = car_info;
        }

        public String getBaoxian_fee() {
            return baoxian_fee;
        }

        public void setBaoxian_fee(String baoxian_fee) {
            this.baoxian_fee = baoxian_fee;
        }

        public int getUser_id_car_photo() {
            return user_id_car_photo;
        }

        public void setUser_id_car_photo(int user_id_car_photo) {
            this.user_id_car_photo = user_id_car_photo;
        }

        public String getDate_sh() {
            return date_sh;
        }

        public void setDate_sh(String date_sh) {
            this.date_sh = date_sh;
        }

        public String getZh_sh_yh_jl2() {
            return zh_sh_yh_jl2;
        }

        public void setZh_sh_yh_jl2(String zh_sh_yh_jl2) {
            this.zh_sh_yh_jl2 = zh_sh_yh_jl2;
        }

        public int getCw_sk_result_id() {
            return cw_sk_result_id;
        }

        public void setCw_sk_result_id(int cw_sk_result_id) {
            this.cw_sk_result_id = cw_sk_result_id;
        }

        public int getUser_id_zh_sh() {
            return user_id_zh_sh;
        }

        public void setUser_id_zh_sh(int user_id_zh_sh) {
            this.user_id_zh_sh = user_id_zh_sh;
        }

        public int getYw_sp_result_id() {
            return yw_sp_result_id;
        }

        public void setYw_sp_result_id(int yw_sp_result_id) {
            this.yw_sp_result_id = yw_sp_result_id;
        }

        public String getLoan_amount_dcy() {
            return loan_amount_dcy;
        }

        public void setLoan_amount_dcy(String loan_amount_dcy) {
            this.loan_amount_dcy = loan_amount_dcy;
        }

        public String getCust_mobile() {
            return cust_mobile;
        }

        public void setCust_mobile(String cust_mobile) {
            this.cust_mobile = cust_mobile;
        }

        public int getUser_id_fk_zg() {
            return user_id_fk_zg;
        }

        public void setUser_id_fk_zg(int user_id_fk_zg) {
            this.user_id_fk_zg = user_id_fk_zg;
        }

        public int getUser_id_zhengxin() {
            return user_id_zhengxin;
        }

        public void setUser_id_zhengxin(int user_id_zhengxin) {
            this.user_id_zhengxin = user_id_zhengxin;
        }

        public String getCar_type() {
            return car_type;
        }

        public void setCar_type(String car_type) {
            this.car_type = car_type;
        }

        public int getReport_state_id() {
            return report_state_id;
        }

        public void setReport_state_id(int report_state_id) {
            this.report_state_id = report_state_id;
        }

        public String getDate_dcy_yw() {
            return date_dcy_yw;
        }

        public void setDate_dcy_yw(String date_dcy_yw) {
            this.date_dcy_yw = date_dcy_yw;
        }

        public int getZhengxin_result_id() {
            return zhengxin_result_id;
        }

        public void setZhengxin_result_id(int zhengxin_result_id) {
            this.zhengxin_result_id = zhengxin_result_id;
        }

        public String getEvaluation_price_dcy() {
            return evaluation_price_dcy;
        }

        public void setEvaluation_price_dcy(String evaluation_price_dcy) {
            this.evaluation_price_dcy = evaluation_price_dcy;
        }

        public int getCust_marriage_id() {
            return cust_marriage_id;
        }

        public void setCust_marriage_id(int cust_marriage_id) {
            this.cust_marriage_id = cust_marriage_id;
        }

        public String getDate_dispatch() {
            return date_dispatch;
        }

        public void setDate_dispatch(String date_dispatch) {
            this.date_dispatch = date_dispatch;
        }

        public String getFee_return_custom() {
            return fee_return_custom;
        }

        public void setFee_return_custom(String fee_return_custom) {
            this.fee_return_custom = fee_return_custom;
        }

        public int getDcy_result_id() {
            return dcy_result_id;
        }

        public void setDcy_result_id(int dcy_result_id) {
            this.dcy_result_id = dcy_result_id;
        }

        public String getDate_yw_pf() {
            return date_yw_pf;
        }

        public void setDate_yw_pf(String date_yw_pf) {
            this.date_yw_pf = date_yw_pf;
        }

        public String getCar_new_company() {
            return car_new_company;
        }

        public void setCar_new_company(String car_new_company) {
            this.car_new_company = car_new_company;
        }

        public int getUser_id_zh_te() {
            return user_id_zh_te;
        }

        public void setUser_id_zh_te(int user_id_zh_te) {
            this.user_id_zh_te = user_id_zh_te;
        }

        public int getUser_id_yw_sp() {
            return user_id_yw_sp;
        }

        public void setUser_id_yw_sp(int user_id_yw_sp) {
            this.user_id_yw_sp = user_id_yw_sp;
        }

        public int getLoan_cw_result_id() {
            return loan_cw_result_id;
        }

        public void setLoan_cw_result_id(int loan_cw_result_id) {
            this.loan_cw_result_id = loan_cw_result_id;
        }

        public String getFee_rate_balance() {
            return fee_rate_balance;
        }

        public void setFee_rate_balance(String fee_rate_balance) {
            this.fee_rate_balance = fee_rate_balance;
        }

        public String getCard_deposit_fee_1() {
            return card_deposit_fee_1;
        }

        public void setCard_deposit_fee_1(String card_deposit_fee_1) {
            this.card_deposit_fee_1 = card_deposit_fee_1;
        }

        public int getFk_result_id() {
            return fk_result_id;
        }

        public void setFk_result_id(int fk_result_id) {
            this.fk_result_id = fk_result_id;
        }

        public String getLoan_amount() {
            return loan_amount;
        }

        public void setLoan_amount(String loan_amount) {
            this.loan_amount = loan_amount;
        }

        public String getDate_baoxian_finished() {
            return date_baoxian_finished;
        }

        public void setDate_baoxian_finished(String date_baoxian_finished) {
            this.date_baoxian_finished = date_baoxian_finished;
        }

        public String getPayment_for_agency() {
            return payment_for_agency;
        }

        public void setPayment_for_agency(String payment_for_agency) {
            this.payment_for_agency = payment_for_agency;
        }

        public String getFee_rate() {
            return fee_rate;
        }

        public void setFee_rate(String fee_rate) {
            this.fee_rate = fee_rate;
        }

        public int getCase_ryp_id() {
            return case_ryp_id;
        }

        public void setCase_ryp_id(int case_ryp_id) {
            this.case_ryp_id = case_ryp_id;
        }

        public String getInterest_company() {
            return interest_company;
        }

        public void setInterest_company(String interest_company) {
            this.interest_company = interest_company;
        }

        public String getLoan_amount_yw_sp() {
            return loan_amount_yw_sp;
        }

        public void setLoan_amount_yw_sp(String loan_amount_yw_sp) {
            this.loan_amount_yw_sp = loan_amount_yw_sp;
        }

        public int getZh_lk_result_id() {
            return zh_lk_result_id;
        }

        public void setZh_lk_result_id(int zh_lk_result_id) {
            this.zh_lk_result_id = zh_lk_result_id;
        }

        public String getFdb_iden() {
            return fdb_iden;
        }

        public void setFdb_iden(String fdb_iden) {
            this.fdb_iden = fdb_iden;
        }

        public int getCertificate_state_id() {
            return certificate_state_id;
        }

        public void setCertificate_state_id(int certificate_state_id) {
            this.certificate_state_id = certificate_state_id;
        }

        public int getUser_id_zh_ss() {
            return user_id_zh_ss;
        }

        public void setUser_id_zh_ss(int user_id_zh_ss) {
            this.user_id_zh_ss = user_id_zh_ss;
        }

        public String getDate_zh_sh() {
            return date_zh_sh;
        }

        public void setDate_zh_sh(String date_zh_sh) {
            this.date_zh_sh = date_zh_sh;
        }

        public String getPayback_month() {
            return payback_month;
        }

        public void setPayback_month(String payback_month) {
            this.payback_month = payback_month;
        }

        public String getDate_gps_finished() {
            return date_gps_finished;
        }

        public void setDate_gps_finished(String date_gps_finished) {
            this.date_gps_finished = date_gps_finished;
        }

        public String getEarlier_interest_fee_1() {
            return earlier_interest_fee_1;
        }

        public void setEarlier_interest_fee_1(String earlier_interest_fee_1) {
            this.earlier_interest_fee_1 = earlier_interest_fee_1;
        }

        public String getEvaluation_price() {
            return evaluation_price;
        }

        public void setEvaluation_price(String evaluation_price) {
            this.evaluation_price = evaluation_price;
        }

        public String getMortgage_fee() {
            return mortgage_fee;
        }

        public void setMortgage_fee(String mortgage_fee) {
            this.mortgage_fee = mortgage_fee;
        }

        public String getDate_baoxian() {
            return date_baoxian;
        }

        public void setDate_baoxian(String date_baoxian) {
            this.date_baoxian = date_baoxian;
        }

        public int getUser_id_dcy() {
            return user_id_dcy;
        }

        public void setUser_id_dcy(int user_id_dcy) {
            this.user_id_dcy = user_id_dcy;
        }

        public String getLoan_amount_fk() {
            return loan_amount_fk;
        }

        public void setLoan_amount_fk(String loan_amount_fk) {
            this.loan_amount_fk = loan_amount_fk;
        }

        public String getDate_car_photo() {
            return date_car_photo;
        }

        public void setDate_car_photo(String date_car_photo) {
            this.date_car_photo = date_car_photo;
        }

        public String getDate_zh_ss() {
            return date_zh_ss;
        }

        public void setDate_zh_ss(String date_zh_ss) {
            this.date_zh_ss = date_zh_ss;
        }

        public String getLsh() {
            return lsh;
        }

        public void setLsh(String lsh) {
            this.lsh = lsh;
        }

        public String getOutcome_info() {
            return outcome_info;
        }

        public void setOutcome_info(String outcome_info) {
            this.outcome_info = outcome_info;
        }

        public int getIf_need_fk_zg() {
            return if_need_fk_zg;
        }

        public void setIf_need_fk_zg(int if_need_fk_zg) {
            this.if_need_fk_zg = if_need_fk_zg;
        }

        public String getEarlier_fee() {
            return earlier_fee;
        }

        public void setEarlier_fee(String earlier_fee) {
            this.earlier_fee = earlier_fee;
        }

        public String getDs_info() {
            return ds_info;
        }

        public void setDs_info(String ds_info) {
            this.ds_info = ds_info;
        }

        public int getUser_id_fee_nq_1() {
            return user_id_fee_nq_1;
        }

        public void setUser_id_fee_nq_1(int user_id_fee_nq_1) {
            this.user_id_fee_nq_1 = user_id_fee_nq_1;
        }

        public String getDate_ds() {
            return date_ds;
        }

        public void setDate_ds(String date_ds) {
            this.date_ds = date_ds;
        }

        public int getUser_id_cw_sk() {
            return user_id_cw_sk;
        }

        public void setUser_id_cw_sk(int user_id_cw_sk) {
            this.user_id_cw_sk = user_id_cw_sk;
        }

        public String getLoan_amount_ywy() {
            return loan_amount_ywy;
        }

        public void setLoan_amount_ywy(String loan_amount_ywy) {
            this.loan_amount_ywy = loan_amount_ywy;
        }

        public String getRoom_info() {
            return room_info;
        }

        public void setRoom_info(String room_info) {
            this.room_info = room_info;
        }

        public String getCar_iden() {
            return car_iden;
        }

        public void setCar_iden(String car_iden) {
            this.car_iden = car_iden;
        }

        public String getFee_rate_advance() {
            return fee_rate_advance;
        }

        public void setFee_rate_advance(String fee_rate_advance) {
            this.fee_rate_advance = fee_rate_advance;
        }

        public int getUser_id_yw_pf() {
            return user_id_yw_pf;
        }

        public void setUser_id_yw_pf(int user_id_yw_pf) {
            this.user_id_yw_pf = user_id_yw_pf;
        }

        public String getDate_zh_te() {
            return date_zh_te;
        }

        public void setDate_zh_te(String date_zh_te) {
            this.date_zh_te = date_zh_te;
        }

        public String getDate_cw_sk_finished() {
            return date_cw_sk_finished;
        }

        public void setDate_cw_sk_finished(String date_cw_sk_finished) {
            this.date_cw_sk_finished = date_cw_sk_finished;
        }

        public String getFdb_name() {
            return fdb_name;
        }

        public void setFdb_name(String fdb_name) {
            this.fdb_name = fdb_name;
        }

        public String getDate_zh_te_finished() {
            return date_zh_te_finished;
        }

        public void setDate_zh_te_finished(String date_zh_te_finished) {
            this.date_zh_te_finished = date_zh_te_finished;
        }

        public String getDate_ywy_qk() {
            return date_ywy_qk;
        }

        public void setDate_ywy_qk(String date_ywy_qk) {
            this.date_ywy_qk = date_ywy_qk;
        }

        public String getZh_lk_info() {
            return zh_lk_info;
        }

        public void setZh_lk_info(String zh_lk_info) {
            this.zh_lk_info = zh_lk_info;
        }

        public String getDate_ds_finished() {
            return date_ds_finished;
        }

        public void setDate_ds_finished(String date_ds_finished) {
            this.date_ds_finished = date_ds_finished;
        }

        public String getZh_te_info() {
            return zh_te_info;
        }

        public void setZh_te_info(String zh_te_info) {
            this.zh_te_info = zh_te_info;
        }

        public String getDate_zh_lk() {
            return date_zh_lk;
        }

        public void setDate_zh_lk(String date_zh_lk) {
            this.date_zh_lk = date_zh_lk;
        }

        public String getGps_info() {
            return gps_info;
        }

        public void setGps_info(String gps_info) {
            this.gps_info = gps_info;
        }

        public String getDate_loan_cw_finished() {
            return date_loan_cw_finished;
        }

        public void setDate_loan_cw_finished(String date_loan_cw_finished) {
            this.date_loan_cw_finished = date_loan_cw_finished;
        }

        public int getIf_gcr_id() {
            return if_gcr_id;
        }

        public void setIf_gcr_id(int if_gcr_id) {
            this.if_gcr_id = if_gcr_id;
        }

        public int getYw_pf_result_id() {
            return yw_pf_result_id;
        }

        public void setYw_pf_result_id(int yw_pf_result_id) {
            this.yw_pf_result_id = yw_pf_result_id;
        }

        public String getLoan_amount_high() {
            return loan_amount_high;
        }

        public void setLoan_amount_high(String loan_amount_high) {
            this.loan_amount_high = loan_amount_high;
        }

        public int getUser_id_loan_cw() {
            return user_id_loan_cw;
        }

        public void setUser_id_loan_cw(int user_id_loan_cw) {
            this.user_id_loan_cw = user_id_loan_cw;
        }

        public String getFee_total() {
            return fee_total;
        }

        public void setFee_total(String fee_total) {
            this.fee_total = fee_total;
        }

        public String getService_fee() {
            return service_fee;
        }

        public void setService_fee(String service_fee) {
            this.service_fee = service_fee;
        }

        public String getLoan_amount_fk_zg() {
            return loan_amount_fk_zg;
        }

        public void setLoan_amount_fk_zg(String loan_amount_fk_zg) {
            this.loan_amount_fk_zg = loan_amount_fk_zg;
        }

        public String getDate_ywy() {
            return date_ywy;
        }

        public void setDate_ywy(String date_ywy) {
            this.date_ywy = date_ywy;
        }

        public String getZh_sh_info() {
            return zh_sh_info;
        }

        public void setZh_sh_info(String zh_sh_info) {
            this.zh_sh_info = zh_sh_info;
        }

        public int getUser_id_dcy_info() {
            return user_id_dcy_info;
        }

        public void setUser_id_dcy_info(int user_id_dcy_info) {
            this.user_id_dcy_info = user_id_dcy_info;
        }

        public String getDate_fee_nq_1() {
            return date_fee_nq_1;
        }

        public void setDate_fee_nq_1(String date_fee_nq_1) {
            this.date_fee_nq_1 = date_fee_nq_1;
        }

        public String getDate_fk() {
            return date_fk;
        }

        public void setDate_fk(String date_fk) {
            this.date_fk = date_fk;
        }

        public int getCredit_years() {
            return credit_years;
        }

        public void setCredit_years(int credit_years) {
            this.credit_years = credit_years;
        }

        public String getDate_zhengxin() {
            return date_zhengxin;
        }

        public void setDate_zhengxin(String date_zhengxin) {
            this.date_zhengxin = date_zhengxin;
        }

        public String getLoan_advance_percent() {
            return loan_advance_percent;
        }

        public void setLoan_advance_percent(String loan_advance_percent) {
            this.loan_advance_percent = loan_advance_percent;
        }

        public String getDate_nq() {
            return date_nq;
        }

        public void setDate_nq(String date_nq) {
            this.date_nq = date_nq;
        }

        public String getInterest_bank() {
            return interest_bank;
        }

        public void setInterest_bank(String interest_bank) {
            this.interest_bank = interest_bank;
        }

        public String getDate_zhengxin_finished() {
            return date_zhengxin_finished;
        }

        public void setDate_zhengxin_finished(String date_zhengxin_finished) {
            this.date_zhengxin_finished = date_zhengxin_finished;
        }

        public int getUser_id_ywy() {
            return user_id_ywy;
        }

        public void setUser_id_ywy(int user_id_ywy) {
            this.user_id_ywy = user_id_ywy;
        }
    }
}
