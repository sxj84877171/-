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

public class ServerBean implements Serializable {

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
        private int case_type_id_1 = -1;
        private String date_dcy_info;
        private int installment_type_id_1 = -1;
        private String extras_fee;
        private int case_state_id = -1;
        private String dcy_info;
        private String product_id;
        private int id = -1;
        private String commercial_insurance_return;
        private double deal_price;
        private String home_visit_fee;
        private int room_type_id = -1;
        private String cust_name_tmp;
        private String gps_fee;
        private String commercial_insurance;
        private String deposit;
        private String fee_return_agency;
        private String evaluation_fee;
        private String chehang_name;
        private String cust_iden;
        private String cust_sex;
        private String loan_amount_ywy_corp;
        private String cust_address;
        private String home_visit_date;
        private String chehang_address;
        private String invoice_price;
        private String loan_amount_dcy;
        private String cust_mobile;
        private String car_type;
        private String date_dcy_yw;
        private int cust_marriage_id = -1;
        private int dcy_result_id = -1;
        private String fee_rate_balance;
        private String loan_amount;
        private String payment_for_agency;
        private String fee_rate;
        private String interest_company;
        private String payback_month_12;
        private String payback_month;
        private String mortgage_fee;
        private int user_id_dcy = -1;
        private String earlier_fee;
        private String loan_amount_ywy;
        private String fee_rate_advance;
        private int if_gcr_id = -1;
        private String loan_amount_high;
        private String fee_total;
        private String service_fee;
        private String date_ywy;
        private int credit_years = -1;
        private String loan_advance_percent;
        private String interest_bank;
        private int user_id_ywy = -1;
        private String fee_return_custom;

        public void setFee_return_custom(String fee_return_custom) {
            this.fee_return_custom = fee_return_custom;
        }

        public String getFee_return_custom() {
            return fee_return_custom;
        }

        public int getCase_type_id_1() {
            return case_type_id_1;
        }

        public void setCase_type_id_1(int case_type_id_1) {
            this.case_type_id_1 = case_type_id_1;
        }


        public String getDate_dcy_info() {
            return date_dcy_info;
        }

        public void setDate_dcy_info(String date_dcy_info) {
            this.date_dcy_info = date_dcy_info;
        }


        public int getInstallment_type_id_1() {
            return installment_type_id_1;
        }

        public void setInstallment_type_id_1(int installment_type_id_1) {
            this.installment_type_id_1 = installment_type_id_1;
        }


        public String getExtras_fee() {
            return extras_fee;
        }

        public void setExtras_fee(String extras_fee) {
            this.extras_fee = extras_fee;
        }


        public int getCase_state_id() {
            return case_state_id;
        }

        public void setCase_state_id(int case_state_id) {
            this.case_state_id = case_state_id;
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


        public String getCommercial_insurance_return() {
            return commercial_insurance_return;
        }

        public void setCommercial_insurance_return(String commercial_insurance_return) {
            this.commercial_insurance_return = commercial_insurance_return;
        }


        public double getDeal_price() {
            return deal_price;
        }

        public void setDeal_price(double deal_price) {
            this.deal_price = deal_price;
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

        public void setPayback_month_12(String payback_month_12) {
            this.payback_month_12 = payback_month_12;
        }

        public String getPayback_month_12() {
            return payback_month_12;
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

        public String getFee_return_agency() {
            return fee_return_agency;
        }

        public void setFee_return_agency(String fee_return_agency) {
            this.fee_return_agency = fee_return_agency;
        }

        public String getEvaluation_fee() {
            return evaluation_fee;
        }

        public void setEvaluation_fee(String evaluation_fee) {
            this.evaluation_fee = evaluation_fee;
        }

        public String getChehang_name() {
            return chehang_name;
        }

        public void setChehang_name(String chehang_name) {
            this.chehang_name = chehang_name;
        }


        public String getCust_iden() {
            return cust_iden;
        }

        public void setCust_iden(String cust_iden) {
            this.cust_iden = cust_iden;
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


        public String getCust_address() {
            return cust_address;
        }

        public void setCust_address(String cust_address) {
            this.cust_address = cust_address;
        }


        public String getHome_visit_date() {
            return home_visit_date;
        }

        public void setHome_visit_date(String home_visit_date) {
            this.home_visit_date = home_visit_date;
        }


        public String getChehang_address() {
            return chehang_address;
        }

        public void setChehang_address(String chehang_address) {
            this.chehang_address = chehang_address;
        }

        public String getInvoice_price() {
            return invoice_price;
        }

        public void setInvoice_price(String invoice_price) {
            this.invoice_price = invoice_price;
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

        public String getCar_type() {
            return car_type;
        }

        public void setCar_type(String car_type) {
            this.car_type = car_type;
        }


        public String getDate_dcy_yw() {
            return date_dcy_yw;
        }

        public void setDate_dcy_yw(String date_dcy_yw) {
            this.date_dcy_yw = date_dcy_yw;
        }


        public int getCust_marriage_id() {
            return cust_marriage_id;
        }

        public void setCust_marriage_id(int cust_marriage_id) {
            this.cust_marriage_id = cust_marriage_id;
        }

        public int getDcy_result_id() {
            return dcy_result_id;
        }

        public void setDcy_result_id(int dcy_result_id) {
            this.dcy_result_id = dcy_result_id;
        }


        public String getFee_rate_balance() {
            return fee_rate_balance;
        }

        public void setFee_rate_balance(String fee_rate_balance) {
            this.fee_rate_balance = fee_rate_balance;
        }


        public String getLoan_amount() {
            return loan_amount;
        }

        public void setLoan_amount(String loan_amount) {
            this.loan_amount = loan_amount;
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


        public String getInterest_company() {
            return interest_company;
        }

        public void setInterest_company(String interest_company) {
            this.interest_company = interest_company;
        }


        public String getPayback_month() {
            return payback_month;
        }

        public void setPayback_month(String payback_month) {
            this.payback_month = payback_month;
        }

        public String getMortgage_fee() {
            return mortgage_fee;
        }

        public void setMortgage_fee(String mortgage_fee) {
            this.mortgage_fee = mortgage_fee;
        }


        public int getUser_id_dcy() {
            return user_id_dcy;
        }

        public String getEarlier_fee() {
            return earlier_fee;
        }

        public void setEarlier_fee(String earlier_fee) {
            this.earlier_fee = earlier_fee;
        }

        public String getLoan_amount_ywy() {
            return loan_amount_ywy;
        }

        public void setLoan_amount_ywy(String loan_amount_ywy) {
            this.loan_amount_ywy = loan_amount_ywy;
        }

        public String getFee_rate_advance() {
            return fee_rate_advance;
        }

        public void setFee_rate_advance(String fee_rate_advance) {
            this.fee_rate_advance = fee_rate_advance;
        }


        public int getIf_gcr_id() {
            return if_gcr_id;
        }

        public void setIf_gcr_id(int if_gcr_id) {
            this.if_gcr_id = if_gcr_id;
        }

        public String getLoan_amount_high() {
            return loan_amount_high;
        }

        public void setLoan_amount_high(String loan_amount_high) {
            this.loan_amount_high = loan_amount_high;
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

        public String getDate_ywy() {
            return date_ywy;
        }

        public void setDate_ywy(String date_ywy) {
            this.date_ywy = date_ywy;
        }

        public void setUser_id_dcy_info(int user_id_dcy_info) {
            this.user_id_dcy_info = user_id_dcy_info;
        }

        public int getUser_id_dcy_info() {
            return user_id_dcy_info;
        }

        private int user_id_dcy_info;

        public int getCredit_years() {
            return credit_years;
        }

        public void setCredit_years(int credit_years) {
            this.credit_years = credit_years;
        }


        public String getLoan_advance_percent() {
            return loan_advance_percent;
        }

        public void setLoan_advance_percent(String loan_advance_percent) {
            this.loan_advance_percent = loan_advance_percent;
        }


        public String getInterest_bank() {
            return interest_bank;
        }

        public void setInterest_bank(String interest_bank) {
            this.interest_bank = interest_bank;
        }


        public int getUser_id_ywy() {
            return user_id_ywy;
        }

        public void setUser_id_ywy(int user_id_ywy) {
            this.user_id_ywy = user_id_ywy;
        }
    }
}
