package com.sxj.carloan.product;

import com.sxj.carloan.bean.ProductBean;

import java.util.List;

/**
 * Created by admin on 2017/8/21.
 */

public class ProductFactroy {

    private List<ProductBean> productBeanList;

    public void setProductBeanList(List<ProductBean> productBeanList) {
        this.productBeanList = productBeanList;
    }

    public List<ProductBean> getProductBeanList() {
        return productBeanList;
    }

    private static ProductFactroy instance = new ProductFactroy();

    private ProductFactroy() {
    }

    public static ProductFactroy getInstance() {
        return instance;
    }

    public IProductType processProductType(int productId, double ywy) {
//        switch (productId) {
//            case 1:
//                return new ICBCUsedCarWhole36M2448B(ywy);
//            case 2:
//                return new ICBCUsedCarHalf36M0188B(ywy);
//            case 3:
//                return new ICBCUsedCar12Whole36M2448B(ywy);
//            case 4:
//                return new SelfFinancingLoanProduct(ywy);
//            case 5:
//                return new ICBCUsedCar12Whole36M2448BPart(ywy);
//            case 6:
//                return new ICBCNewCarWhole36M18F3(ywy);
//            case 7:
//                return new ICBCNewCarHalf36M1350(ywy);
//            case 8:
//                return new ICBCNewCarHalf24M1050(ywy);
//            default:
//                return new ICBCUsedCarWhole36M2448B(ywy);
//        }

        final ProductBean productBean = findProductById(productId);

        if(productBean != null) {
            return new IProductType() {
                @Override
                public String getProductName() {
                    return productBean.getProduct_name();
                }

                @Override
                public String getCarType() {
                    if(productBean.getCase_type_id() == 2){
                        return "二手车";
                    }else{
                        return "新车";
                    }
                }

                @Override
                public String getCase_type_id() {
                    return productBean.getCase_type_id() + "" ;
                }

                @Override
                public int getCreditYears() {
                    return productBean.getCredit_years();
                }

                @Override
                public boolean isLoanAdvancePercentVisible() {
                    return productBean.getLoan_advance_percent() >= 0;
                }

                @Override
                public double getLoanAdvancePercent() {
                    return productBean.getLoan_advance_percent();
                }

                @Override
                public double getFeeRate() {
                    return productBean.getFee_rate();
                }

                @Override
                public double getFeeRateAdvance() {
                    return productBean.getFee_rate_advance();
                }

                @Override
                public boolean isFeeRateAdvanceVisible() {
                    return productBean.getFee_rate_advance() >= 0;
                }

                @Override
                public boolean isFeeRateBalanceVisible() {
                    return productBean.getFee_rate_balance() >= 0;
                }

                @Override
                public double getFeeRateBalance() {
                    return productBean.getFee_rate_balance();
                }

                @Override
                public double getLoanAmountYwyCorp() {
                    return productBean.getLoan_amount_ywy_corp();
                }

                @Override
                public boolean isLoanAmountYwyCorpVisible() {
                    return productBean.getLoan_amount_ywy_corp() >= 0;
                }

                @Override
                public void setLoanAmountYwyCory(double loanAmountYwyCory) {
                }

                @Override
                public boolean isLoanAmountYwyCorpEdit() {
                    return true;
                }

                @Override
                public double getLoanAmountHigh() {
                    return productBean.getLoan_amount_high();
                }

                @Override
                public boolean isLoanAmountHighVisible() {
                    return productBean.getLoan_amount_high() >= 0;
                }

                @Override
                public boolean isInterestBankVisible() {
                    return productBean.getInterest_bank() >= 0;
                }

                @Override
                public double getInterestBank() {
                    return productBean.getInterest_bank();
                }

                @Override
                public double getInterestCompany() {
                    return productBean.getInterest_company();
                }

                @Override
                public boolean isInterestCompanyVisible() {
                    return productBean.getInterest_company() >= 0;
                }

                @Override
                public double getDeposit() {
                    return productBean.getDeposit();
                }

                @Override
                public boolean isDepositVisible() {
                    return productBean.getDeposit() >= 0;
                }

                @Override
                public void setDeposit(double deposit) {

                }

                @Override
                public boolean isDepositEdit() {
                    return true;
                }

                @Override
                public double getPaybackMonth12() {
                    return 0;
                }

                @Override
                public boolean isPaybackMonth12Visible() {
                    return productBean.getPayback_month_12() == 0 ;
                }

                @Override
                public double getPaybackMonth() {
                    return 0;
                }

                @Override
                public boolean isPaybackMonthVisible() {
                    return productBean.getPayback_month() >= 0 ;
                }

                @Override
                public double getExtrasFee() {
                    return productBean.getExtras_fee();
                }

                @Override
                public void setExtrasFee(double extrasFee) {

                }

                @Override
                public boolean isExtrasFeeVisible() {
                    return productBean.getExtras_fee() >= 0;
                }

                @Override
                public double getServiceFee() {
                    return productBean.getEarlier_fee();
                }

                @Override
                public boolean isServiceFee() {
                    return productBean.getService_fee() >= 0;
                }

                @Override
                public double getGpsFee() {
                    return productBean.getGps_fee();
                }

                @Override
                public boolean isGpsFeeVisible() {
                    return true;
                }

                @Override
                public boolean isMortagageFeeVisible() {
                    return productBean.getMortgage_fee() >= 0;
                }

                @Override
                public double getMortgageFee() {
                    return productBean.getMortgage_fee();
                }

                @Override
                public boolean isMorgageFeeEdit() {
                    return productBean.getMortgage_fee_fl() != null;
                }

                @Override
                public void setMortgageFee(double mortgageFee) {

                }

                @Override
                public double getHomeVisitFee() {
                    return productBean.getHome_visit_fee();
                }

                @Override
                public boolean isHomeVisitFeeVisible() {
                    return productBean.getHome_visit_fee() >= 0;
                }

                @Override
                public void setHomeVisitFee(double homeVisitFee) {

                }

                @Override
                public boolean isHomeVisitFeeEdit() {
                    return productBean.getHome_visit_fee() >= 0;
                }

                @Override
                public double getBaoXianFee() {
                    return productBean.getBaoxian_fee();
                }

                @Override
                public boolean isBaoXianFeeVisible() {
                    return productBean.getBaoxian_fee() >= 0;
                }

                @Override
                public boolean isBaoXianFeeEdit() {
                    return productBean.getBaoxian_fee_fl() != null;
                }

                @Override
                public void setBaoXianFee(double baoXianFee) {

                }

                @Override
                public double getEvaluationFee() {
                    return productBean.getEvaluation_fee();
                }

                @Override
                public boolean isEvaluationFeeVisible() {
                    return productBean.getEvaluation_fee() >= 0;
                }

                @Override
                public boolean isEarlierFeeVisible() {
                    return productBean.getEarlier_fee() >= 0;
                }

                @Override
                public double getEarlierFee() {
                    return productBean.getEarlier_fee();
                }

                @Override
                public boolean isFeeReturnAgency() {
                    return productBean.getFee_return_agency_fl() != null;
                }

                @Override
                public double getFeeReturnAgency() {
                    return productBean.getFee_return_agency();
                }

                @Override
                public boolean isFeeTotalVisible() {
                    return productBean.getFee_total() >= 0;
                }

                @Override
                public double getFeeTotal() {
                    return productBean.getFee_total();
                }

                @Override
                public boolean isFeeReturnCustomVisible() {
                    return productBean.getFee_return_custom() >= 0;
                }

                @Override
                public double getFeeReturnCustom() {
                    return productBean.getFee_return_custom();
                }

                @Override
                public boolean isCommercialInsuranceVisible() {
                    return productBean.getCommercial_insurance() >= 0;
                }

                @Override
                public double getCommercialInsurance() {
                    return productBean.getCommercial_insurance();
                }

                @Override
                public boolean isCommercialInsuranceReturnVisible() {
                    return productBean.getCommercial_insurance_return() >= 0;
                }

                @Override
                public double getCommercialInsuranceReturn() {
                    return productBean.getCommercial_insurance_return();
                }

                @Override
                public boolean isPaymentForAgencyVisible() {
                    return productBean.getPayment_for_agency() >= 0;
                }

                @Override
                public double getPaymentForAgency() {
                    return productBean.getPayment_for_agency();
                }
            };
        }else {
            return null;
        }
    }

    private ProductBean findProductById(int id){
        if(productBeanList != null){
            for(ProductBean productBean : productBeanList){
                if(productBean.getId() == id){
                    return productBean;
                }
            }
        }
        return null;
    }
}
