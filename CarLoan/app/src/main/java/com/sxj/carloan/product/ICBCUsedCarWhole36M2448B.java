package com.sxj.carloan.product;

/**
 * Created by admin on 2017/8/21.
 */

public class ICBCUsedCarWhole36M2448B extends ProductType {

    private double loan_amount_ywy;

    public ICBCUsedCarWhole36M2448B(double loan_amount_ywy) {
        this.loan_amount_ywy = loan_amount_ywy;
    }

    /**
     * 获取产品名字
     * 工行-二手车全分期36个月24.48返4包干
     *
     * @return
     */
    @Override
    public String getProductName() {
        return "工行-二手车全分期36个月24.48返4包干";
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

    @Override
    public boolean isLoanAmountYwyCorpVisible() {
        return false;
    }

    /**
     * 银行申报金额
     * loan_amount_high=loan_amount_ywy*1.1548
     *
     * @return
     */
    @Override
    public double getLoanAmountHigh() {
        double loan_amount_high = loan_amount_ywy * 1.1548;
        return roundDouble1(loan_amount_high);
    }

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

    @Override
    public double getInterestBank() {
        return 0;
    }

    /**
     * 前置利息
     * interest_company=loan_amount_ywy*0.1548*10000
     *
     * @return
     */
    @Override
    public double getInterestCompany() {
        double interest_company = loan_amount_ywy * 0.1548 * 10000;

        return roundDouble(interest_company);
    }

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

    @Override
    public boolean isDepositVisible() {
        return true;
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

    private double extrasFee = 0;

    public void setExtrasFee(double extrasFee) {
        this.extrasFee = extrasFee;
    }

    @Override
    public boolean isExtrasFeeVisible() {
        return true;
    }

    /**
     * 流程保证金及服务费
     * service_fee=(loan_amount_high - loan_amount_ywy)*10000 - gps_fee - mortgage_fee - evaluation_fee
     *
     * @return
     */
    @Override
    public double getServiceFee() {
        double service_fee = (getLoanAmountHigh() - loan_amount_ywy) * 10000 - getGpsFee() - getMortgageFee() - getEvaluationFee();
        return roundDouble(service_fee);
    }

    @Override
    public boolean isServiceFee() {
        return true;
    }

    /**
     * GPS费
     *
     * @return
     */
    @Override
    public double getGpsFee() {
        double gps_fee = 4980;
        if (loan_amount_ywy < 10) {
            gps_fee = 1980;
        } else if (loan_amount_ywy < 20) {
            gps_fee = 3980;
        } else {
            gps_fee = 4980;
        }
        return gps_fee;
    }

    @Override
    public boolean isGpsFeeVisible() {
        return true;
    }

    @Override
    public boolean isMortagageFeeVisible() {
        return true;
    }

    @Override
    public double getMortgageFee() {
        return 500;
    }

    /**
     * 家访费[home_visit_fee]
     * home_visit_fee=0
     */
    @Override
    public double getHomeVisitFee() {
        return 0;
    }

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

    @Override
    public boolean isBaoXianFeeVisible() {
        return false;
    }

    /**
     * 评估费
     */
    @Override
    public double getEvaluationFee() {
        return 700;
    }

    @Override
    public boolean isEvaluationFeeVisible() {
        return true;
    }

    /**
     * 前期收取费[earlier_fee]
     * earlier_fee=payback_month
     */
    @Override
    public boolean isEarlierFeeVisible() {
        return true;
    }

    /**
     * earlier_fee=payback_month
     *
     * @return
     */
    @Override
    public double getEarlierFee() {
        return getPaybackMonth();
    }

    /**
     * 返经销商[fee_return_agency]
     */
    @Override
    public boolean isFeeReturnAgency() {
        return true;
    }

    /**
     * fee_return_agency=loan_amount_ywy*0.04*10000
     *
     * @return
     */
    @Override
    public double getFeeReturnAgency() {
        double fee_return_agency = loan_amount_ywy * 0.04 * 10000;
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
     * fee_total= (loan_amount_high-loan_amount_ywy)*10000+ deposit  + extras_fee - fee_return_agency
     *
     * @return
     */
    @Override
    public double getFeeTotal() {
        double fee_total = (getLoanAmountHigh() - loan_amount_ywy) * 10000 + getDeposit() + getExtrasFee() - getFeeReturnAgency();
        return roundDouble(fee_total);
    }

    /**
     * 客户履约后退款fee_return_custom
     */
    @Override
    public boolean isFeeReturnCustomVisible() {
        return false;
    }

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

    @Override
    public double getPaymentForAgency() {
        return 0;
    }
}
