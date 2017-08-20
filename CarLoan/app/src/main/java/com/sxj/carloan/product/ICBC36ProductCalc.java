package com.sxj.carloan.product;

import com.sxj.carloan.bean.ServerBean;

/**
 * Created by admin on 2017/8/20.
 * 工行-二手车全分期36个月24.48返4包干
 */

public class ICBC36ProductCalc implements IProductCalc {

    private ServerBean.RowsBean bean;

    public ICBC36ProductCalc() {
    }

    public ICBC36ProductCalc(ServerBean.RowsBean bean) {
        this.bean = bean;
    }

    /**
     * 获取产品名称
     *
     * @return
     */
    @Override
    public String getProduceName() {
        return "工行-二手车全分期36个月24.48返4包干";
    }

    /**
     * 获取费率
     *
     * @return
     */
    @Override
    public double getFeiLv() {
        return Double.parseDouble(getFee_rate());
    }

    /**
     * 获取保证金
     *loan_amount_high*1.09/36*10000
     * @return
     */
    @Override
    public double getBaoZhengJin() {
        double loan_amount_high = Double.parseDouble(bean.getLoan_amount_high());
        return loan_amount_high*1.09/36*10000;
    }

    /**
     * 获取月供
     *
     * @return
     */
    @Override
    public double getYueGong() {
        return Double.parseDouble(bean.getLoan_amount_high()) * 1.09 * 10000 / 36;
    }

    /**
     * 获取调整项
     *
     * @return
     */
    @Override
    public double getTiaoZhengXiang() {
        return 0;
    }

    /**
     * 获取GPS费率
     *
     * @return
     */
    @Override
    public double getGpsFei() {
        double loan_amount_ywy = getLoanAmountYwy();
        double gps_fee = 1980;
        if (loan_amount_ywy < 10) {
            gps_fee = 1980;
        } else if (loan_amount_ywy < 20) {
            gps_fee = 3980;
        } else {
            gps_fee = 4980;
        }
        return gps_fee;
    }

    /**
     * 获取抵押费
     *
     * @return
     */
    @Override
    public double getDiYaFei() {
        return 500;
    }

    /**
     * 获取家访费
     *
     * @return
     */
    @Override
    public double getJiaFangFei() {
        return 0 ;
    }

    /**
     * 合计
     *
     * @return
     */
    @Override
    public double getHeJi() {
        Double loan_amount_high = Double.parseDouble(bean.getLoan_amount_high());
        Double loan_amount_ywy = getLoanAmountYwy();
        Double deposit = getYueGong();
        Double extras_fee = getTiaoZhengXiang();
        Double fee_return_agency = Double.parseDouble(getFeeReturnAgency());
        return ((loan_amount_high - loan_amount_ywy) * 10000 + deposit + extras_fee - fee_return_agency);

    }

    /***
     * 获取客户履约后退款
     * @return
     */
    @Override
    public double getKeFuLvYueHouTuiKuan() {
        return Double.parseDouble(getFeeReturnCustom());
    }

    /**
     * 业务类型[case_type_id]
     *
     * @return
     */
    @Override
    public String getCase_type_id() {
        return "二手车";
    }

    /**
     * 资金来源[funds_source_id]
     *
     * @return
     */
    @Override
    public String getFunds_source_id() {
        return null;
    }

    /**
     * 分期类别[installment_type_id]
     *
     * @return
     */
    @Override
    public String getInstallment_type_id() {
        return null;
    }

    /**
     * 贷款年限[credit_years]
     *
     * @return
     */
    @Override
    public String getCredit_years() {
        return "3";
    }

    /**
     * 首付比例[loan_advance_percent]
     *
     * @return
     */
    @Override
    public String getLoan_advance_percent() {
        return "-100";
    }

    /**
     * 费率[fee_rate]
     */
    @Override
    public String getFee_rate() {
        return "24.48";
    }

    /**
     * 首付费率[fee_rate_advance]
     */
    @Override
    public String getFe_rate_advance() {
        return "-100";
    }

    /**
     * 余额费率[fee_rate_balance]
     */
    @Override
    public String getFee_rate_balance() {
        return "-100";
    }

    /**
     * 公司贷款[loan_amount_ywy_corp]
     */
    @Override
    public String getLoan_amount_ywy_corp() {
        return "-100";
    }

    /**
     * 公司贷款公式
     */
    @Override
    public String getCompaionGongShi() {
        return null;
    }

    /**
     * 银行申报金额[loan_amount_high]
     */
    @Override
    public String getLoan_amount_high() {
        Double doubule = Double.parseDouble(bean.getLoan_amount_ywy());
        doubule = doubule * 1.1548;
        return "" + doubule;
    }

    /**
     * 银行申报金额公式
     */
    @Override
    public String getBankJiSuanGongShi() {
        return "loan_amount_high=loan_amount_ywy*1.1548";
    }

    /**
     * 银行利息[interest_bank]
     */
    @Override
    public String getInterest_bank() {
        return null;
    }

    /**
     * 银行利息公式
     */
    @Override
    public String getBankLiXiGongShi() {
        return "";
    }

    /**
     * 前置利息[interest_company]
     * interest_company=loan_amount_ywy*0.1548*10000
     */
    @Override
    public String getInterest_company() {
        Double doubule = Double.parseDouble(bean.getLoan_amount_ywy());
        doubule = doubule * 0.1548 * 10000;
        return "" + doubule;
    }

    /**
     * 前置利息公式
     */
    @Override
    public String getInterest_companytGongShi() {
        return null;
    }

    /**
     * 保证金[deposit]
     */
    @Override
    public String getDeposit() {
        return bean.getPayback_month();
    }

    /**
     * 保证金公式
     *
     * @return
     */
    @Override
    public String getDepositGongShi() {
        return null;
    }

    /**
     * 月供[前12月][payback_month_12]
     */
    @Override
    public String getPayback_month_12() {
        return "";
    }

    /**
     * 月供[前12月]公式
     *
     * @return
     */
    @Override
    public String getPayback_month_12GongShi() {
        return null;
    }

    /**
     * 月供[payback_month]
     */
    @Override
    public String getPayBackMonth() {
        // loan_amount_high*1.09/36*10000
        Double d = Double.parseDouble(bean.getLoan_amount_high()) * 1.09 * 10000 / 36;
        return "" + d;
    }

    /**
     *
     */
    @Override
    public String getPayBackMonthGongShi() {
        return "loan_amount_high*1.09/36*10000";
    }

    /**
     * 调整项[extras_fee]
     */
    @Override
    public String getExtrasFee() {
        return "0";
    }

    /**
     * 调整项
     */
    @Override
    public String getExtrasFeeGongShi() {
        return null;
    }

    /**
     * 流程保证金及服务费[service_fee]
     * service_fee=(loan_amount_high - loan_amount_ywy)*10000 - gps_fee - mortgage_fee - evaluation_fee
     */
    @Override
    public String getServiceFee() {
        Double loan_amount_high = Double.parseDouble(bean.getLoan_amount_high());
        Double loan_amount_ywy = Double.parseDouble(bean.getLoan_amount_ywy());
        Double gps_fee = Double.parseDouble(getGpsFee());
        Double mortgage_fee = Double.parseDouble(getMortgageFee());
        Double evaluation_fee = Double.parseDouble(getEvaluationFee());

        return "" + ((loan_amount_high - loan_amount_ywy) * 10000 - gps_fee - mortgage_fee - evaluation_fee);
    }

    /**
     * 流程保证金及服务费公式
     */
    @Override
    public String getServiceFeeGongShi() {
        return "service_fee=(loan_amount_high - loan_amount_ywy)*10000 - gps_fee - mortgage_fee - evaluation_fee";
    }

    /**
     * GPS费[gps_fee]
     */
    @Override
    public String getGpsFee() {
        double loan_amount_ywy = getLoanAmountYwy();
        double gps_fee = 1980;
        if (loan_amount_ywy < 10) {
            gps_fee = 1980;
        } else if (loan_amount_ywy < 20) {
            gps_fee = 3980;
        } else {
            gps_fee = 4980;
        }
        return "" + gps_fee;
    }

    private double getLoanAmountYwy() {
        return Double.parseDouble(bean.getLoan_amount_ywy());
    }

    /**
     * GPS费公式
     * if(loan_amount_ywy<10) gps_fee=1980;else if( loan_amount_ywy<20) gps_fee=3980; else gps_fee=4980;
     */
    @Override
    public String getGpsFeeGongShi() {
        return null;
    }

    /**
     * 抵押费[mortgage_fee]
     */
    @Override
    public String getMortgageFee() {
        return "500";
    }

    /**
     * 抵押费公式
     */
    @Override
    public String getMortgageFeeGongShi() {
        return null;
    }

    /**
     * 家访费[home_visit_fee]
     */
    @Override
    public String getHomeVisitFee() {
        return "0";
    }

    /**
     * 家访费公式
     * home_visit_fee=0
     */
    @Override
    public String getHomeVisitFeeGongShi() {
        return "home_visit_fee=0";
    }

    /**
     * 保险费[baoxian_fee]
     */
    @Override
    public String getBaoXianFee() {
        return "-100";
    }

    @Override
    public String getBaoXianFeeGongShi() {
        return null;
    }

    /**
     * 评估费[evaluation_fee]
     */
    @Override
    public String getEvaluationFee() {
        return "700";
    }

    /**
     * 评估费公式
     */
    @Override
    public String getEvaluationFeeGongShi() {
        return null;
    }

    /**
     * 前期收取费[earlier_fee]
     */
    @Override
    public String getEarlierFee() {
        return getPayBackMonth();
    }

    /**
     * 前期收取费公式
     * earlier_fee=payback_month
     */
    @Override
    public String getEarlierFeeGongShi() {
        return "earlier_fee=payback_month";
    }

    /**
     * 返经销商[fee_return_agency]
     * fee_return_agency=loan_amount_ywy*0.04*10000
     */
    @Override
    public String getFeeReturnAgency() {
        return "" + (getLoanAmountYwy() * 0.04 * 10000);
    }

    /**
     *
     */
    @Override
    public String getFeeReturnAgencyGongShi() {
        return "fee_return_agency=loan_amount_ywy*0.04*10000";
    }

    /**
     * 合计[fee_total]
     * fee_total= (loan_amount_high-loan_amount_ywy)*10000+ deposit  + extras_fee - fee_return_agency
     */
    @Override
    public String getFeeTotal() {
        Double loan_amount_high = Double.parseDouble(bean.getLoan_amount_high());
        Double loan_amount_ywy = getLoanAmountYwy();
        Double deposit = Double.parseDouble(getDeposit());
        Double extras_fee = Double.parseDouble(getExtrasFee());
        Double fee_return_agency = Double.parseDouble(getFeeReturnAgency());
        return "" + ((loan_amount_high - loan_amount_ywy) * 10000 + deposit + extras_fee - fee_return_agency);
    }

    /**
     * 合计公式
     * fee_total= (loan_amount_high-loan_amount_ywy)*10000+ deposit  + extras_fee - fee_return_agency
     */
    @Override
    public String getFeeTotalGongShi() {
        return "fee_total= (loan_amount_high-loan_amount_ywy)*10000+ deposit  + extras_fee - fee_return_agency";
    }

    /**
     * 客户履约后退款[fee_return_custom]
     */
    @Override
    public String getFeeReturnCustom() {
        return "-100";
    }

    /**
     * 客户履约后退款公式
     */
    @Override
    public String getFeeReturnCustomGongShi() {
        return null;
    }

    /**
     * 商业险[commercial_insurance]
     */
    @Override
    public String getCommercialInsturance() {
        return "-100";
    }

    /**
     * 商业险公式
     */
    @Override
    public String getCommercialInsturanceGongShi() {
        return null;
    }

    /**
     * 商业险返[commercial_insurance_return]
     */
    @Override
    public String getCommercialInsturanceReturn() {
        return "-100";
    }

    /**
     * 商业险返公式
     *
     * @return
     */
    @Override
    public String getCommercialInsturanceReturnGongShi() {
        return null;
    }

    /**
     * 付经销商合计[payment_for_agency]
     */
    @Override
    public String getPaymentForAgency() {
        return "-100";
    }

    /**
     * 付经销商合计公式
     */
    @Override
    public String getPaymentForAgencyGongShi() {
        return null;
    }
}
