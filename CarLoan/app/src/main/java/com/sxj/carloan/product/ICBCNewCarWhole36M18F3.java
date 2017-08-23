package com.sxj.carloan.product;

/**
 * Created by admin on 2017/8/22.
 */

public class ICBCNewCarWhole36M18F3 extends ProductType {
    /**
     * 获取产品名字
     *
     * @return
     */
    @Override
    public String getProductName() {
        return "工行-新车全分期36个月18返3";
    }

    @Override
    public String getCarType() {
        return "1";
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
        return 18;
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
        return false;
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


    public ICBCNewCarWhole36M18F3(double loan_amount_ywy) {
        super(loan_amount_ywy);
    }

    /**
     * 银行申报金额
     * loan_amount_high=loan_amount_ywy*1.09
     *
     * @return
     */
    @Override
    public double getLoanAmountHigh() {
        double loan_amount_high = getLoan_amount_ywy() * 1.09;
        return roundDouble(loan_amount_high);
    }

    /**
     * 银行申报金额
     *
     * @return
     */
    @Override
    public boolean isLoanAmountHighVisible() {
        return true;
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

    /**
     * 前置利息
     * interest_company=loan_amount_ywy*0.09*10000
     *
     * @return
     */
    @Override
    public double getInterestCompany() {
        double interest_company = getLoan_amount_ywy() * 0.09 * 10000;
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
     *
     * @return
     */
    @Override
    public double getPaybackMonth12() {
        return 0;
    }

    /**
     * 月供[前12月]
     *
     * @return
     */
    @Override
    public boolean isPaybackMonth12Visible() {
        return false;
    }

    /**
     * 月供
     * payback_month=loan_amount_high*1.09/36*10000
     *
     * @return
     */
    @Override
    public double getPaybackMonth() {
        double payback_month = getLoanAmountHigh() * 1.09 / 36 * 10000;
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
    public boolean isExtrasFeeVisible() {
        return true;
    }

    /**
     * 流程保证金及服务费
     * service_fee=(loan_amount_high-loan_amount_ywy)*10000-gps_fee-home_visit_fee-mortgage_fee
     *
     * @return
     */
    @Override
    public double getServiceFee() {
        double service_fee = (getLoanAmountHigh() - getLoan_amount_ywy()) * 10000 - getGpsFee() - getHomeVisitFee() - getMortgageFee();
        return roundDouble(service_fee);
    }

    /**
     * 流程保证金及服务费
     *
     * @return
     */
    @Override
    public boolean isServiceFee() {
        return true;
    }

    /**
     * GPS费
     * if( loan_amount_ywy<10) gps_fee=500; else gps_fee=1980;
     *
     * @return
     */
    @Override
    public double getGpsFee() {
        double gps_fee = 1980;
        if (getLoan_amount_ywy() < 10) {
            gps_fee = 500;
        } else {
            gps_fee = 1980;
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
        return mortgageFee;
    }

    private double mortgageFee;

    public void setMortgageFee(double mortgageFee) {
        this.mortgageFee = mortgageFee;
    }

    public boolean isMorgageFeeEdit() {
        return true;
    }

    /**
     * 家访费[home_visit_fee]
     */
    @Override
    public double getHomeVisitFee() {
        return homeVisitFee;
    }

    private double homeVisitFee;

    public void setHomeVisitFee(double homeVisitFee) {
        this.homeVisitFee = homeVisitFee;
    }

    public boolean isHomeVisitFeeEdit() {
        return true;
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
        return 0;
    }

    /**
     * 评估费
     *
     * @return
     */
    @Override
    public boolean isEvaluationFeeVisible() {
        return false;
    }

    /**
     * 前期收取费[earlier_fee]
     */
    @Override
    public boolean isEarlierFeeVisible() {
        return true;
    }

    /**
     * 前期收取费
     * earlier_fee=gps_fee+home_visit_fee+mortgage_fee+payback_month
     *
     * @return
     */
    @Override
    public double getEarlierFee() {
        double earlier_fee = getGpsFee() + getHomeVisitFee() + getMortgageFee() + getPaybackMonth();
        return roundDouble(earlier_fee);
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
     * fee_return_agency=loan_amount_ywy*0.03*10000
     *
     * @return
     */
    @Override
    public double getFeeReturnAgency() {
        double fee_return_agency = getLoan_amount_ywy() * 0.03 * 10000;
        return roundDouble(fee_return_agency);
    }

    /**
     * 合计
     * fee_total=earlier_fee+payback_month-fee_return_agency+extras_fee
     *
     * @return
     */
    @Override
    public boolean isFeeTotalVisible() {
        return true;
    }

    /**
     * 合计
     * fee_total=earlier_fee+payback_month-fee_return_agency+extras_fee
     *
     * @return
     */
    @Override
    public double getFeeTotal() {
        double fee_total = getEarlierFee() + getPaybackMonth() - getFeeReturnAgency() + getExtrasFee();
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
