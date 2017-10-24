package com.sxj.carloan.product;

/**
 * Created by admin on 2017/8/22.
 */

public class ICBCUsedCar12Whole36M2448B extends ProductType {
    /**
     * 获取产品名字
     *
     * @return
     */
    @Override
    public String getProductName() {
        return "工行-二手车12全分期36个月24.48返4包干";
    }

    @Override
    public String getCarType() {
        return "1";
    }

    @Override
    public String getCase_type_id() {
        return null;
    }

    /**
     * 获取贷款年限
     *
     * @return
     */
    @Override
    public int getCreditYears() {
        return 3;
    }

    /**
     * 首付比例是否可见
     *
     * @return
     */
    @Override
    public boolean isLoanAdvancePercentVisible() {
        return false;
    }

    /**
     * 获取首付比例
     *
     * @return
     */
    @Override
    public double getLoanAdvancePercent() {
        return 0;
    }

    /**
     * 费率
     *
     * @return
     */
    @Override
    public double getFeeRate() {
        return 24.48;
    }

    /**
     * 首付费率
     *
     * @return
     */
    @Override
    public double getFeeRateAdvance() {
        return 0;
    }

    /**
     * 首付费率
     *
     * @return
     */
    @Override
    public boolean isFeeRateAdvanceVisible() {
        return true;
    }

    /**
     * 余额费率
     *
     * @return 余额费率
     */
    @Override
    public boolean isFeeRateBalanceVisible() {
        return false;
    }

    /**
     * 余额费率
     *
     * @return
     */
    @Override
    public double getFeeRateBalance() {
        return 0;
    }

    /**
     * 公司贷款
     *
     * @return
     */
    @Override
    public double getLoanAmountYwyCorp() {
        return 0;
    }

    /**
     * 公司贷款
     *
     * @return
     */
    @Override
    public boolean isLoanAmountYwyCorpVisible() {
        return false;
    }

    /**
     * 公司贷款
     *
     * @param loanAmountYwyCory
     */
    @Override
    public void setLoanAmountYwyCory(double loanAmountYwyCory) {

    }

    /**
     * 是否可以编辑
     *
     * @return
     */
    @Override
    public boolean isLoanAmountYwyCorpEdit() {
        return false;
    }

    /**
     * 银行申报金额
     *
     * @return
     */
    @Override
    public double getLoanAmountHigh() {
        return 0;
    }

    /**
     * 银行申报金额
     *
     * @return
     */
    @Override
    public boolean isLoanAmountHighVisible() {
        return false;
    }

    /**
     * 银行利息
     *
     * @return
     */
    @Override
    public boolean isInterestBankVisible() {
        return false;
    }

    /**
     * 银行利息
     *
     * @return
     */
    @Override
    public double getInterestBank() {
        return 0;
    }


    public ICBCUsedCar12Whole36M2448B(double loan_amount_ywy) {
        super(loan_amount_ywy);
    }

    /**
     * 前置利息
     * interest_company=loan_amount_ywy*0.1548*10000
     *
     * @return
     */
    @Override
    public double getInterestCompany() {
        double interest_company = getLoan_amount_ywy() * 0.1548 * 10000;
        return roundDouble(interest_company);
    }

    /**
     * 前置利息
     *
     * @return
     */
    @Override
    public boolean isInterestCompanyVisible() {
        return true;
    }

    /**
     * 保证金
     * deposit=payback_month_12
     *
     * @return
     */
    @Override
    public double getDeposit() {
        return getPaybackMonth12();
    }

    /**
     * 保证金
     *
     * @return
     */
    @Override
    public boolean isDepositVisible() {
        return true;
    }

    /**
     * 保证金
     *
     * @param deposit
     */
    @Override
    public void setDeposit(double deposit) {

    }

    /**
     * 保证金是否可以编辑
     *
     * @return
     */
    @Override
    public boolean isDepositEdit() {
        return false;
    }

    /**
     * 月供[前12月]
     * payback_month_12=loan_amount_ywy*0.1548*10000/12+payback_month
     *
     * @return
     */
    @Override
    public double getPaybackMonth12() {
        double payback_month_12 = getLoan_amount_ywy() * 0.1548 * 10000 / 12 + getPaybackMonth();
        return roundDouble(payback_month_12);
    }

    /**
     * 月供[前12月]
     *
     * @return
     */
    @Override
    public boolean isPaybackMonth12Visible() {
        return true;
    }

    /**
     * 月供
     * payback_month=loan_amount_ywy*1.09/36*10000
     *
     * @return
     */
    @Override
    public double getPaybackMonth() {
        double payback_month = getLoan_amount_ywy() * 1.09 / 36 * 10000;
        return roundDouble(payback_month);
    }

    /**
     * 月供
     *
     * @return
     */
    @Override
    public boolean isPaybackMonthVisible() {
        return true;
    }

    /**
     * 调整项
     *
     * @return
     */
    @Override
    public double getExtrasFee() {
        return extrasFee;
    }

    private double extrasFee;

    /**
     * 调整项
     *
     * @param extrasFee
     */
    @Override
    public void setExtrasFee(double extrasFee) {
        this.extrasFee = extrasFee;
    }

    /**
     * 调整项
     *
     * @return
     */
    @Override
    public boolean isExtrasFeeVisible() {
        return true;
    }

    /**
     * 流程保证金及服务费
     *
     * @return
     */
    @Override
    public double getServiceFee() {
        return 0;
    }

    /**
     * 流程保证金及服务费
     *
     * @return
     */
    @Override
    public boolean isServiceFee() {
        return false;
    }

    /**
     * GPS费
     * if(loan_amount_ywy<10) gps_fee=1980;else if( loan_amount_ywy<20) gps_fee=3980; else gps_fee=4980;
     *
     * @return
     */
    @Override
    public double getGpsFee() {
        double gps_fee = 4980;
        if (getLoan_amount_ywy() < 10) {
            gps_fee = 1980;
        } else if (getLoan_amount_ywy() < 20) {
            gps_fee = 3980;
        } else {
            gps_fee = 4980;
        }
        return gps_fee;
    }

    /**
     * GPS费
     *
     * @return
     */
    @Override
    public boolean isGpsFeeVisible() {
        return true;
    }

    /**
     * 抵押费[mortgage_fee]
     */
    @Override
    public boolean isMortagageFeeVisible() {
        return true;
    }

    /**
     * 抵押费
     *
     * @return
     */
    @Override
    public double getMortgageFee() {
        return 500;
    }

    /**
     * 抵押费 是否可以编辑
     *
     * @return
     */
    @Override
    public boolean isMorgageFeeEdit() {
        return false;
    }

    /**
     * 抵押费
     *
     * @param mortgageFee
     */
    @Override
    public void setMortgageFee(double mortgageFee) {

    }

    /**
     * 家访费[home_visit_fee]
     * home_visit_fee=0
     */
    @Override
    public double getHomeVisitFee() {
        return 0;
    }

    /**
     * 家访费[home_visit_fee]
     *
     * @return
     */
    @Override
    public boolean isHomeVisitFeeVisible() {
        return true;
    }

    /**
     * 家访费
     *
     * @param homeVisitFee
     */
    @Override
    public void setHomeVisitFee(double homeVisitFee) {

    }

    /**
     * 家访费
     *
     * @return
     */
    @Override
    public boolean isHomeVisitFeeEdit() {
        return false;
    }

    /**
     * 保险费
     */
    @Override
    public double getBaoXianFee() {
        return 0;
    }

    /***
     * 保险费
     * @return
     */
    @Override
    public boolean isBaoXianFeeVisible() {
        return false;
    }

    /**
     * 保险费
     *
     * @return
     */
    @Override
    public boolean isBaoXianFeeEdit() {
        return false;
    }

    /**
     * 保险费 是否可以编辑
     *
     * @param baoXianFee
     */
    @Override
    public void setBaoXianFee(double baoXianFee) {

    }

    /**
     * 评估费
     */
    @Override
    public double getEvaluationFee() {
        return 700;
    }

    /**
     * 评估费
     *
     * @return
     */
    @Override
    public boolean isEvaluationFeeVisible() {
        return true;
    }

    /**
     * 前期收取费[earlier_fee]
     */
    @Override
    public boolean isEarlierFeeVisible() {
        return false;
    }

    /**
     * 前期收取费
     *earlier_fee=deposit
     * @return
     */
    @Override
    public double getEarlierFee() {
        return getDeposit();
    }

    /**
     * 返经销商[fee_return_agency]
     */
    @Override
    public boolean isFeeReturnAgency() {
        return true;
    }

    /**
     * 返经销商
     *fee_return_agency=loan_amount_ywy*0.04*10000
     * @return
     */
    @Override
    public double getFeeReturnAgency() {
        double fee_return_agency=getLoan_amount_ywy()*0.04*10000;
        return roundDouble(fee_return_agency);
    }

    /**
     * 合计
     *
     * @return
     */
    @Override
    public boolean isFeeTotalVisible() {
        return true;
    }

    /**
     * 合计
     *fee_total=deposit-fee_return_agency+extras_fee
     * @return
     */
    @Override
    public double getFeeTotal() {
        double fee_total=getDeposit()-getFeeReturnAgency() +getExtrasFee();
        return roundDouble(fee_total);
    }

    /**
     * 客户履约后退款fee_return_custom
     */
    @Override
    public boolean isFeeReturnCustomVisible() {
        return false;
    }

    /**
     * 客户履约后退款fee_return_custom
     *
     * @return
     */
    @Override
    public double getFeeReturnCustom() {
        return 0;
    }

    /**
     * 商业险[commercial_insurance]
     */
    @Override
    public boolean isCommercialInsuranceVisible() {
        return false;
    }

    /**
     * 商业险[commercial_insurance]
     *
     * @return
     */
    @Override
    public double getCommercialInsurance() {
        return 0;
    }

    /**
     * 商业险返[commercial_insurance_return]
     */
    @Override
    public boolean isCommercialInsuranceReturnVisible() {
        return false;
    }

    /**
     * 商业险返[commercial_insurance_return]
     *
     * @return
     */
    @Override
    public double getCommercialInsuranceReturn() {
        return 0;
    }

    /**
     * 付经销商合计[payment_for_agency]
     */
    @Override
    public boolean isPaymentForAgencyVisible() {
        return false;
    }

    /**
     * 付经销商合计[payment_for_agency]
     *
     * @return
     */
    @Override
    public double getPaymentForAgency() {
        return 0;
    }
}
