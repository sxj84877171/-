package com.sxj.carloan.product;

/**
 * Created by admin on 2017/8/22.
 */

public class ICBCUsedCarHalf36M0188B extends ProductType {
    /**
     * 获取产品名字
     *
     * @return
     */
    @Override
    public String getProductName() {
        return "工行-二手车半分期36个月18.8包干";
    }

    @Override
    public String getCarType() {
        return "2";
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
     * 该产品不可见
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
        return 18.8;
    }

    /**
     * 首付费率
     * 该产品不可见
     *
     * @return
     */
    @Override
    public double getFeeRateAdvance() {
        return 0;
    }

    /**
     * 首付费率
     * 该产品不可见
     *
     * @return
     */
    @Override
    public boolean isFeeRateAdvanceVisible() {
        return false;
    }

    /**
     * 余额费率
     * 该产品不可见
     *
     * @return 余额费率
     */
    @Override
    public boolean isFeeRateBalanceVisible() {
        return false;
    }

    /**
     * 余额费率
     * 该产品不可见
     *
     * @return
     */
    @Override
    public double getFeeRateBalance() {
        return 0;
    }

    /**
     * 公司贷款
     * 该产品不可见
     *
     * @return
     */
    @Override
    public double getLoanAmountYwyCorp() {
        return 0;
    }

    /**
     * 公司贷款
     * 该产品不可见
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
     * 该产品不可见
     *
     * @return
     */
    @Override
    public double getLoanAmountHigh() {
        return 0;
    }

    /**
     * 银行申报金额
     * 该产品不可见
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
        return true;
    }


    public ICBCUsedCarHalf36M0188B(double loan_amount_ywy) {
        super(loan_amount_ywy);
    }

    /**
     * 银行利息
     * interest_bank=loan_amount_ywy*0.09*10000
     *
     * @return
     */
    @Override
    public double getInterestBank() {
        double interest_bank = getLoan_amount_ywy() * 0.09 * 10000;
        return roundDouble(interest_bank);
    }

    /**
     * 前置利息
     * interest_company=loan_amount_ywy*0.098*10000 - gps_fee - evaluation_fee - mortgage_fee
     *
     * @return
     */
    @Override
    public double getInterestCompany() {
        double interest_company = getLoan_amount_ywy() * 0.098 * 10000 - getGpsFee() - getEvaluationFee() - getMortgageFee();
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
     * deposit=payback_month
     *
     * @return
     */
    @Override
    public double getDeposit() {
        return getPaybackMonth();
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
     * 该产品不可见
     *
     * @return
     */
    @Override
    public double getPaybackMonth12() {
        return 0;
    }

    /**
     * 月供[前12月]
     * 该产品不可见
     *
     * @return
     */
    @Override
    public boolean isPaybackMonth12Visible() {
        return false;
    }

    /**
     * 月供
     * payback_month=loan_amount_ywy * 1.09 / 36 * 10000
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
     *
     * @return
     */
    @Override
    public double getGpsFee() {
        double gps_fee = 4980;
        if (getLoan_amount_ywy() < 20) {
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
     *
     * @return
     */
    @Override
    public double getEarlierFee() {
        return 0;
    }

    /**
     * 返经销商[fee_return_agency]
     */
    @Override
    public boolean isFeeReturnAgency() {
        return false;
    }

    /**
     * 返经销商
     *
     * @return
     */
    @Override
    public double getFeeReturnAgency() {
        return 0;
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
     *fee_total=deposit+interest_company+extras_fee + gps_fee + evaluation_fee + mortgage_fee
     * @return
     */
    @Override
    public double getFeeTotal() {
        double fee_total=getDeposit()+getInterestCompany()+getExtrasFee() + getGpsFee() + getEvaluationFee() + getMortgageFee();
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
