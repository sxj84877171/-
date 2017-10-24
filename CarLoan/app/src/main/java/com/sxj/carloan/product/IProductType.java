package com.sxj.carloan.product;

/**
 * Created by admin on 2017/8/21.
 */

public interface IProductType {

    /**
     * 获取产品名字
     *
     * @return
     */
    public String getProductName();

    public String getCarType();

    public String getCase_type_id();

    /**
     * 获取贷款年限
     *
     * @return
     */
    public int getCreditYears();

    /**
     * 首付比例是否可见
     *
     * @return
     */
    public boolean isLoanAdvancePercentVisible();

    /**
     * 获取首付比例
     *
     * @return
     */
    public double getLoanAdvancePercent();

    /**
     * 费率
     *
     * @return
     */
    public double getFeeRate();

    /**
     * 首付费率
     *
     * @return
     */
    public double getFeeRateAdvance();

    /**
     * 首付费率
     *
     * @return
     */
    public boolean isFeeRateAdvanceVisible();

    /**
     * 余额费率
     *
     * @return 余额费率
     */
    public boolean isFeeRateBalanceVisible();

    /**
     * 余额费率
     *
     * @return
     */
    public double getFeeRateBalance();

    /**
     * 公司贷款
     *
     * @return
     */
    public double getLoanAmountYwyCorp();

    /**
     * 公司贷款
     *
     * @return
     */
    public boolean isLoanAmountYwyCorpVisible();

    /**
     * 公司贷款
     *
     * @param loanAmountYwyCory
     */
    public void setLoanAmountYwyCory(double loanAmountYwyCory);

    /**
     * 是否可以编辑
     *
     * @return
     */
    public boolean isLoanAmountYwyCorpEdit();


    /**
     * 银行申报金额
     *
     * @return
     */
    public double getLoanAmountHigh();

    /**
     * 银行申报金额
     *
     * @return
     */
    public boolean isLoanAmountHighVisible();


    /**
     * 银行利息
     *
     * @return
     */
    boolean isInterestBankVisible();

    /**
     * 银行利息
     *
     * @return
     */
    double getInterestBank();

    /**
     * 前置利息
     *
     * @return
     */
    double getInterestCompany();

    /**
     * 前置利息
     *
     * @return
     */
    boolean isInterestCompanyVisible();


    /**
     * 保证金
     *
     * @return
     */
    double getDeposit();

    /**
     * 保证金
     *
     * @return
     */
    boolean isDepositVisible();

    /**
     * 保证金
     *
     * @param deposit
     */
    public void setDeposit(double deposit);

    /**
     * 保证金是否可以编辑
     *
     * @return
     */
    public boolean isDepositEdit();

    /**
     * 月供[前12月]
     *
     * @return
     */
    double getPaybackMonth12();

    /**
     * 月供[前12月]
     *
     * @return
     */
    boolean isPaybackMonth12Visible();

    /**
     * 月供
     *
     * @return
     */
    double getPaybackMonth();

    /**
     * 月供
     *
     * @return
     */
    boolean isPaybackMonthVisible();

    /**
     * 调整项
     *
     * @return
     */
    double getExtrasFee();

    /**
     * 调整项
     *
     * @param extrasFee
     */
    void setExtrasFee(double extrasFee);

    /**
     * 调整项
     *
     * @return
     */
    boolean isExtrasFeeVisible();

    /**
     * 流程保证金及服务费
     *
     * @return
     */
    double getServiceFee();

    /**
     * 流程保证金及服务费
     *
     * @return
     */
    boolean isServiceFee();

    /**
     * GPS费
     *
     * @return
     */
    double getGpsFee();

    /**
     * GPS费
     *
     * @return
     */
    boolean isGpsFeeVisible();

    /**
     * 抵押费[mortgage_fee]
     */
    boolean isMortagageFeeVisible();

    /**
     * 抵押费
     *
     * @return
     */
    double getMortgageFee();

    /**
     * 抵押费 是否可以编辑
     *
     * @return
     */
    public boolean isMorgageFeeEdit();

    /**
     * 抵押费
     *
     * @param mortgageFee
     */
    public void setMortgageFee(double mortgageFee);

    /**
     * 家访费[home_visit_fee]
     */
    double getHomeVisitFee();

    /**
     * 家访费[home_visit_fee]
     *
     * @return
     */
    boolean isHomeVisitFeeVisible();

    /**
     * 家访费
     *
     * @param homeVisitFee
     */
    public void setHomeVisitFee(double homeVisitFee);


    /**
     * 家访费
     *
     * @return
     */
    public boolean isHomeVisitFeeEdit();

    /**
     * 保险费
     */
    double getBaoXianFee();

    /***
     * 保险费
     * @return
     */
    boolean isBaoXianFeeVisible();

    /**
     * 保险费
     *
     * @return
     */
    public boolean isBaoXianFeeEdit();

    /**
     * 保险费 是否可以编辑
     *
     * @param baoXianFee
     */
    public void setBaoXianFee(double baoXianFee);

    /**
     * 评估费
     */
    double getEvaluationFee();

    /**
     * 评估费
     *
     * @return
     */
    boolean isEvaluationFeeVisible();

    /**
     * 前期收取费[earlier_fee]
     */
    boolean isEarlierFeeVisible();

    /**
     * 前期收取费
     *
     * @return
     */
    double getEarlierFee();

    /**
     * 返经销商[fee_return_agency]
     */
    boolean isFeeReturnAgency();

    /**
     * 返经销商
     *
     * @return
     */
    double getFeeReturnAgency();

    /**
     * 合计
     *
     * @return
     */
    boolean isFeeTotalVisible();

    /**
     * 合计
     *
     * @return
     */
    double getFeeTotal();

    /**
     * 客户履约后退款fee_return_custom
     */
    boolean isFeeReturnCustomVisible();

    /**
     * 客户履约后退款fee_return_custom
     *
     * @return
     */
    double getFeeReturnCustom();

    /**
     * 商业险[commercial_insurance]
     */
    boolean isCommercialInsuranceVisible();

    /**
     * 商业险[commercial_insurance]
     *
     * @return
     */
    double getCommercialInsurance();

    /**
     * 商业险返[commercial_insurance_return]
     */
    boolean isCommercialInsuranceReturnVisible();

    /**
     * 商业险返[commercial_insurance_return]
     *
     * @return
     */
    double getCommercialInsuranceReturn();

    /**
     * 付经销商合计[payment_for_agency]
     */
    boolean isPaymentForAgencyVisible();

    /**
     * 付经销商合计[payment_for_agency]
     *
     * @return
     */
    double getPaymentForAgency();
}
