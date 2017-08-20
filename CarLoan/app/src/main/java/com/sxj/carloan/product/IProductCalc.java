package com.sxj.carloan.product;

/**
 * Created by admin on 2017/8/20.
 */

public interface IProductCalc {

    /**
     * 获取产品名称
     * @return
     */
    String getProduceName();

    /**
     * 获取费率
     * @return
     */
    double getFeiLv();

    /**
     * 获取保证金
     * @return
     */
    double getBaoZhengJin();

    /**
     * 获取月供
     * @return
     */
    double getYueGong();

    /**
     * 获取调整项
     * @return
     */
    double getTiaoZhengXiang();

    void setTiaoZhengXiang(double tiaoZhengXiang);

    /**
     * 获取GPS费率
     * @return
     */
    double getGpsFei();

    /**
     * 获取抵押费
     * @return
     */
    double getDiYaFei();

    /**
     * 获取家访费
     * @return
     */
    double getJiaFangFei();

    /**
     * 合计
     * @return
     */
    double getHeJi();

    /***
     * 获取客户履约后退款
     * @return
     */
    double getKeFuLvYueHouTuiKuan();

    /**
     *业务类型[case_type_id]
     * @return
     */
    String getCase_type_id();

    /**
     * 资金来源[funds_source_id]
     * @return
     */
    String getFunds_source_id();

    /**
     * 分期类别[installment_type_id]
     * @return
     */
    String getInstallment_type_id();

    /**
     * 贷款年限[credit_years]
     * @return
     */
    String getCredit_years();

    /**
     * 首付比例[loan_advance_percent]
     * @return
     */
    String getLoan_advance_percent();

    /**
     *费率[fee_rate]
     */
    String getFee_rate();

    /**
     * 首付费率[fee_rate_advance]
     */
    String getFe_rate_advance();

    /**
     * 余额费率[fee_rate_balance]
     */
    String getFee_rate_balance();

    /**
     * 公司贷款[loan_amount_ywy_corp]
     */
    String getLoan_amount_ywy_corp();

    /**
     * 公司贷款公式
     */
    String getCompaionGongShi();

    /**
     * 银行申报金额[loan_amount_high]
     */
    String getLoan_amount_high();

    /**
     * 银行申报金额公式
     */
    String getBankJiSuanGongShi();

    /**
     * 银行利息[interest_bank]
     */
    String getInterest_bank();

    /**
     * 银行利息公式
     */
    String getBankLiXiGongShi();

    /**
     * 前置利息[interest_company]
     */
    String getInterest_company();

    /**
     * 前置利息公式
     */
    String getInterest_companytGongShi();

    /**
     * 保证金[deposit]
     */
    String getDeposit();

    /**
     * 保证金公式
     * @return
     */
    String getDepositGongShi();

    /**
     * 月供[前12月][payback_month_12]
     */
    String getPayback_month_12();

    /**
     * 月供[前12月]公式
     * @return
     */
    String getPayback_month_12GongShi();

    /**
     * 月供[payback_month]
     */
    String getPayBackMonth();

    /**
     *
     */
    String getPayBackMonthGongShi();

    /**
     * 调整项[extras_fee]
     */
    String getExtrasFee();

    /**
     *调整项
     */
    String getExtrasFeeGongShi();

    /**
     * 流程保证金及服务费[service_fee]
     */
    String getServiceFee();

    /**
     * 流程保证金及服务费公式
     */
    String getServiceFeeGongShi();

    /**
     * GPS费[gps_fee]
     */
    String getGpsFee();

    /**
     * GPS费公式
     */
    String getGpsFeeGongShi();

    /**
     * 抵押费[mortgage_fee]
     */
    String getMortgageFee();

    /**
     *抵押费公式
     */
    String getMortgageFeeGongShi();

    /**
     * 家访费[home_visit_fee]
     */
    String getHomeVisitFee();

    /**
     *家访费公式
     */
    String getHomeVisitFeeGongShi();

    /**
     * 保险费[baoxian_fee]
     */
    String getBaoXianFee();

    String getBaoXianFeeGongShi();

    /**
     * 评估费[evaluation_fee]
     */
    String getEvaluationFee();

    /**
     *评估费公式
     */
    String getEvaluationFeeGongShi();

    /**
     *
     前期收取费[earlier_fee]
     */
    String getEarlierFee();

    /**
     * 前期收取费公式
     */
    String getEarlierFeeGongShi();

    /**
     * 返经销商[fee_return_agency]
     */
    String getFeeReturnAgency();

    /**
     *
     */
    String getFeeReturnAgencyGongShi();

    /**
     * 合计[fee_total]
     */
    String getFeeTotal();

    /**
     * 合计公式
     */
    String getFeeTotalGongShi();

    /**
     * 客户履约后退款[fee_return_custom]
     */
    String getFeeReturnCustom();

    /**
     * 客户履约后退款公式
     */
    String getFeeReturnCustomGongShi();

    /**
     * 商业险[commercial_insurance]
     */
    String getCommercialInsturance();

    /**
     *商业险公式
     */
    String getCommercialInsturanceGongShi();

    /**
     * 商业险返[commercial_insurance_return]
     */
    String getCommercialInsturanceReturn();

    /**
     * 商业险返公式
     * @return
     */
    String getCommercialInsturanceReturnGongShi();

    /**
     * 付经销商合计[payment_for_agency]
     */
    String getPaymentForAgency();

    /**
     * 付经销商合计公式
     */
    String getPaymentForAgencyGongShi();



}
