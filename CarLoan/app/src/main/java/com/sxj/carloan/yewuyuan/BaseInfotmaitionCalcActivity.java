package com.sxj.carloan.yewuyuan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.FuncResponseBean;
import com.sxj.carloan.product.IProductType;
import com.sxj.carloan.product.ProductFactroy;
import com.sxj.carloan.util.BeanToMap;

import java.text.DecimalFormat;

import rx.Subscriber;

/**
 * Created by admin on 2017/8/19.
 */

public class BaseInfotmaitionCalcActivity extends BaseActivity {

    private TextView cancel_action;
    private TextView save_action;
    private TextView submit_action;
    IProductType productType = null;
    private int ywy = 1;

    private TextView product_name;
    private EditText loan_advance_percent;
    private EditText fee_rate;
    private EditText fee_rate_advance;
    private EditText fee_rate_balance;
    private EditText loan_amount_ywy_corp;
    private EditText loan_amount_high;
    private EditText interest_bank;
    private EditText interest_company;
    private EditText deposit;
    private EditText payback_month_12;
    private EditText payback_month;
    private EditText extras_fee;
    private EditText service_fee;
    private EditText gps_fee;
    private EditText mortgage_fee;
    private EditText home_visit_fee;
    private EditText baoxian_fee;
    private EditText evaluation_fee;
    private EditText earlier_fee;
    private EditText fee_return_agency;
    private EditText fee_total;
    private EditText fee_return_custom;
    private EditText commercial_insurance;
    private EditText commercial_insurance_return;
    private EditText payment_for_agency;

    private View loan_advance_percent_line;
    private View fee_rate_line;
    private View fee_rate_advance_line;
    private View fee_rate_balance_line;
    private View loan_amount_ywy_corp_line;
    private View loan_amount_high_line;
    private View interest_bank_line;
    private View interest_company_line;
    private View deposit_line;
    private View payback_month_12_line;
    private View payback_month_line;
    private View extras_fee_line;
    private View service_fee_line;
    private View gps_fee_line;
    private View mortgage_fee_line;
    private View home_visit_fee_line;
    private View baoxian_fee_line;
    private View evaluation_fee_line;
    private View earlier_fee_line;
    private View fee_return_agency_line;
    private View fee_total_line;
    private View fee_return_custom_line;
    private View commercial_insurance_line;
    private View commercial_insurance_return_line;
    private View payment_for_agency_line;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_result_baseinforamtion);
        Intent intent = getIntent();
        ywy = intent.getIntExtra("ywy", 1);
        if (loan != null) {
            if (ywy != 1) {
                try {
                    productType = ProductFactroy.getInstance().processProductType(Integer.parseInt(loan.getProduct_id()), Double.parseDouble(loan.getLoan_amount_dcy()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                try {
                    productType = ProductFactroy.getInstance().processProductType(Integer.parseInt(loan.getProduct_id()), Double.parseDouble(loan.getLoan_amount_ywy()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            finish();
            return;
        }
        initView();
        initViewVisible();
        initFirstData();
        initData();
        initEdit();
        initListener();
    }

    private void initFirstData() {
        if (productType != null) {
            if (productType.isDepositEdit()) {
                deposit.setText(loan.getDeposit());
            }

            if (productType.isMorgageFeeEdit()) {
                mortgage_fee.setText(loan.getMortgage_fee());
            }
            if (productType.isHomeVisitFeeEdit()) {
                home_visit_fee.setText(loan.getHome_visit_fee());
            }
            if (productType.isBaoXianFeeEdit()) {
                baoxian_fee.setText(loan.getBaoxian_fee());
            }
            if (productType.isLoanAmountYwyCorpEdit()) {
                loan_amount_ywy_corp.setText(loan.getLoan_amount_ywy_corp());
            }

            extras_fee.setText(loan.getExtras_fee());
        }
    }

    void initView() {

        product_name = getViewById(R.id.product_name);
        loan_advance_percent = getViewById(R.id.loan_advance_percent);
        fee_rate = getViewById(R.id.fee_rate);
        fee_rate_advance = getViewById(R.id.fee_rate_advance);
        fee_rate_balance = getViewById(R.id.fee_rate_balance);
        loan_amount_ywy_corp = getViewById(R.id.loan_amount_ywy_corp);
        loan_amount_high = getViewById(R.id.loan_amount_high);
        interest_bank = getViewById(R.id.interest_bank);
        interest_company = getViewById(R.id.interest_company);
        deposit = getViewById(R.id.deposit);
        payback_month_12 = getViewById(R.id.payback_month_12);
        payback_month = getViewById(R.id.payback_month);
        extras_fee = getViewById(R.id.extras_fee);
        service_fee = getViewById(R.id.service_fee);
        gps_fee = getViewById(R.id.gps_fee);
        mortgage_fee = getViewById(R.id.mortgage_fee);
        home_visit_fee = getViewById(R.id.home_visit_fee);
        baoxian_fee = getViewById(R.id.baoxian_fee);
        evaluation_fee = getViewById(R.id.evaluation_fee);
        earlier_fee = getViewById(R.id.earlier_fee);
        fee_return_agency = getViewById(R.id.fee_return_agency);
        fee_total = getViewById(R.id.fee_total);
        fee_return_custom = getViewById(R.id.fee_return_custom);
        commercial_insurance = getViewById(R.id.commercial_insurance);
        commercial_insurance_return = getViewById(R.id.commercial_insurance_return);
        payment_for_agency = getViewById(R.id.payment_for_agency);

        loan_advance_percent_line = getViewById(R.id.loan_advance_percent_line);
        fee_rate_line = getViewById(R.id.fee_rate_line);
        fee_rate_advance_line = getViewById(R.id.fee_rate_advance_line);
        fee_rate_balance_line = getViewById(R.id.fee_rate_balance_line);
        loan_amount_ywy_corp_line = getViewById(R.id.loan_amount_ywy_corp_line);
        loan_amount_high_line = getViewById(R.id.loan_amount_high_line);
        interest_bank_line = getViewById(R.id.interest_bank_line);
        interest_company_line = getViewById(R.id.interest_company_line);
        deposit_line = getViewById(R.id.deposit_line);
        payback_month_12_line = getViewById(R.id.payback_month_12_line);
        payback_month_line = getViewById(R.id.payback_month_line);
        extras_fee_line = getViewById(R.id.extras_fee_line);
        service_fee_line = getViewById(R.id.service_fee_line);
        gps_fee_line = getViewById(R.id.gps_fee_line);
        mortgage_fee_line = getViewById(R.id.mortgage_fee_line);
        home_visit_fee_line = getViewById(R.id.home_visit_fee_line);
        baoxian_fee_line = getViewById(R.id.baoxian_fee_line);
        evaluation_fee_line = getViewById(R.id.evaluation_fee_line);
        earlier_fee_line = getViewById(R.id.earlier_fee_line);
        fee_return_agency_line = getViewById(R.id.fee_return_agency_line);
        fee_total_line = getViewById(R.id.fee_total_line);
        fee_return_custom_line = getViewById(R.id.fee_return_custom_line);
        commercial_insurance_line = getViewById(R.id.commercial_insurance_line);
        commercial_insurance_return_line = getViewById(R.id.commercial_insurance_return_line);
        payment_for_agency_line = getViewById(R.id.payment_for_agency_line);

        save_action = getViewById(R.id.save_action1);
        submit_action = getViewById(R.id.submit_action1);
        cancel_action = getViewById(R.id.cancel_action1);
    }

    void initData() {
        if (productType != null) {
            initEditData();

            initEditTextContent();
        }
    }

    private void initViewVisible() {
        try {
            if (productType != null) {
                loan_advance_percent_line.setVisibility(productType.isLoanAdvancePercentVisible() ? View.VISIBLE : View.GONE);
                fee_rate_line.setVisibility(View.VISIBLE);
                fee_rate_advance_line.setVisibility(productType.isFeeRateAdvanceVisible() ? View.VISIBLE : View.GONE);
                fee_rate_balance_line.setVisibility(productType.isFeeRateBalanceVisible() ? View.VISIBLE : View.GONE);
                loan_amount_ywy_corp_line.setVisibility(productType.isLoanAmountYwyCorpVisible() ? View.VISIBLE : View.GONE);
                loan_amount_high_line.setVisibility(productType.isLoanAmountHighVisible() ? View.VISIBLE : View.GONE);
                interest_bank_line.setVisibility(productType.isInterestBankVisible() ? View.VISIBLE : View.GONE);
                interest_company_line.setVisibility(productType.isInterestCompanyVisible() ? View.VISIBLE : View.GONE);
                deposit_line.setVisibility(productType.isDepositVisible() ? View.VISIBLE : View.GONE);
                payback_month_12_line.setVisibility(productType.isPaybackMonth12Visible() ? View.VISIBLE : View.GONE);
                payback_month_line.setVisibility(productType.isPaybackMonthVisible() ? View.VISIBLE : View.GONE);
                extras_fee_line.setVisibility(productType.isExtrasFeeVisible() ? View.VISIBLE : View.GONE);
                service_fee_line.setVisibility(productType.isServiceFee() ? View.VISIBLE : View.GONE);
                gps_fee_line.setVisibility(productType.isGpsFeeVisible() ? View.VISIBLE : View.GONE);
                mortgage_fee_line.setVisibility(productType.isMortagageFeeVisible() ? View.VISIBLE : View.GONE);
                home_visit_fee_line.setVisibility(productType.isHomeVisitFeeVisible() ? View.VISIBLE : View.GONE);
                baoxian_fee_line.setVisibility(productType.isBaoXianFeeVisible() ? View.VISIBLE : View.GONE);
                evaluation_fee_line.setVisibility(productType.isEvaluationFeeVisible() ? View.VISIBLE : View.GONE);
                earlier_fee_line.setVisibility(productType.isEarlierFeeVisible() ? View.VISIBLE : View.GONE);
                fee_return_agency_line.setVisibility(productType.isFeeReturnAgency() ? View.VISIBLE : View.GONE);
                fee_total_line.setVisibility(productType.isFeeTotalVisible() ? View.VISIBLE : View.GONE);
                fee_return_custom_line.setVisibility(productType.isFeeReturnCustomVisible() ? View.VISIBLE : View.GONE);
                commercial_insurance_line.setVisibility(productType.isCommercialInsuranceVisible() ? View.VISIBLE : View.GONE);
                commercial_insurance_return_line.setVisibility(productType.isCommercialInsuranceReturnVisible() ? View.VISIBLE : View.GONE);
                payment_for_agency_line.setVisibility(productType.isPaymentForAgencyVisible() ? View.VISIBLE : View.GONE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initEditTextContent() {
        try {
            if (productType != null) {
//                product_name.setText(productType.getProductName());
                loan_advance_percent.setText(loan.getLoan_advance_percent());
                fee_rate.setText(loan.getFee_rate());
                fee_rate_advance.setText(loan.getFee_rate_advance());
                fee_rate_balance.setText(loan.getFee_rate_balance());
                loan_amount_ywy_corp.setText(loan.getLoan_amount_ywy_corp());
                loan_amount_high.setText(loan.getLoan_amount_high());
                interest_bank.setText(loan.getInterest_bank());
                interest_company.setText(loan.getInterest_company());
                deposit.setText(loan.getDeposit());
                payback_month_12.setText(loan.getPayback_month_12());
                payback_month.setText(loan.getPayback_month());
                extras_fee.setText(loan.getExtras_fee());
                service_fee.setText(loan.getService_fee());
                gps_fee.setText(loan.getGps_fee());
                mortgage_fee.setText(loan.getMortgage_fee());
                home_visit_fee.setText(loan.getHome_visit_fee());
                baoxian_fee.setText(loan.getBaoxian_fee());
                evaluation_fee.setText(loan.getEvaluation_fee());
                earlier_fee.setText(loan.getEarlier_fee());
                fee_return_agency.setText(loan.getFee_return_agency());
                fee_total.setText(loan.getFee_total());
                fee_return_custom.setText(loan.getFee_return_custom());
                commercial_insurance.setText(loan.getCommercial_insurance());
                commercial_insurance_return.setText(loan.getCommercial_insurance_return());
                payment_for_agency.setText(loan.getPayment_for_agency());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initEditData() {
        double extras_fee_money = 0;
        try {
            extras_fee_money = Double.parseDouble(extras_fee.getText().toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        productType.setExtrasFee(extras_fee_money);

        if (productType.isDepositEdit()) {
            double depositValue = 0;
            try {
                depositValue = Double.parseDouble(deposit.getText().toString());
            } catch (Exception ex) {

            }
            productType.setDeposit(depositValue);
        }
        if (productType.isMorgageFeeEdit()) {
            double mortgageFeeValue = 0;
            try {
                mortgageFeeValue = Double.parseDouble(mortgage_fee.getText().toString());
            } catch (Exception e) {

            }
            productType.setMortgageFee(mortgageFeeValue);
        }
        if (productType.isHomeVisitFeeEdit()) {
            double homeVisitFeeValue = 0;
            try {
                homeVisitFeeValue = Double.parseDouble(home_visit_fee.getText().toString());
            } catch (Exception ex) {

            }
            productType.setHomeVisitFee(homeVisitFeeValue);
        }

        if (productType.isBaoXianFeeEdit()) {
            double baoxianFeeValue = 0;
            try {
                baoxianFeeValue = Double.parseDouble(baoxian_fee.getText().toString());
            } catch (Exception e) {

            }
            productType.setBaoXianFee(baoxianFeeValue);
        }
        if (productType.isLoanAmountYwyCorpEdit()) {
            double loanAmountYwyCorp = 0;
            try {
                loanAmountYwyCorp = Double.parseDouble(loan_amount_ywy_corp.getText().toString());
            } catch (Exception e) {

            }
            productType.setLoanAmountYwyCory(loanAmountYwyCorp);
        }
    }

    public String double2String(double d) {
        return double2String(d, false);
    }

    public String double2String(double d, boolean flag) {
        DecimalFormat df = null;
        if (flag) {
            df = new DecimalFormat("0.0");
        } else {
            df = new DecimalFormat("0");
        }
        return df.format(d);
    }

    public void initListener() {

        cancel_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    initData();
                } catch (Exception ex) {
                    toast("请正确输入调整项");
                    return;
                }
            }
        });

        submit_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    initData();
                } catch (Exception ex) {
                    toast("请正确输入调整项");
                    return;
                }
                saveData2Memory();
                model.update(BeanToMap.transRowsBean2Map(loan)).subscribe(new Subscriber<FuncResponseBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        toast("fail.");
                    }

                    @Override
                    public void onNext(FuncResponseBean funcResponseBean) {
                        toast("Success!");
                        finish();
                    }
                });

            }
        });
    }


    public void saveData2Memory() {
        loan.setLoan_advance_percent(loan_advance_percent.getText().toString());
        loan.setFee_rate(fee_rate.getText().toString());
        loan.setFee_rate_advance(fee_rate_advance.getText().toString());
        loan.setFee_rate_balance(fee_rate_balance.getText().toString());
        loan.setLoan_amount_ywy_corp(loan_amount_ywy_corp.getText().toString());
        loan.setLoan_amount_high(loan_amount_high.getText().toString());
        loan.setInterest_bank(interest_bank.getText().toString());
        loan.setInterest_company(interest_company.getText().toString());
        loan.setDeposit(deposit.getText().toString());
        loan.setPayback_month_12(payback_month_12.getText().toString());
        loan.setPayback_month(payback_month.getText().toString());
        loan.setExtras_fee(extras_fee.getText().toString());
        loan.setService_fee(service_fee.getText().toString());
        loan.setGps_fee(gps_fee.getText().toString());
        loan.setMortgage_fee(mortgage_fee.getText().toString());
        loan.setHome_visit_fee(home_visit_fee.getText().toString());
        loan.setBaoxian_fee(baoxian_fee.getText().toString());
        loan.setEvaluation_fee(evaluation_fee.getText().toString());
        loan.setEarlier_fee(earlier_fee.getText().toString());
        loan.setFee_return_agency(fee_return_agency.getText().toString());
        loan.setFee_total(fee_total.getText().toString());
        loan.setFee_return_custom(fee_return_custom.getText().toString());
        loan.setCommercial_insurance(commercial_insurance.getText().toString());
        loan.setCommercial_insurance_return(commercial_insurance_return.getText().toString());
        loan.setPayment_for_agency(payment_for_agency.getText().toString());
        loan.setBaoxian_fee(baoxian_fee.getText().toString());
    }

    public void initEdit() {
        if(productType != null) {
            if (productType.isDepositEdit()) {
                deposit.setFocusable(true);
                deposit.setEnabled(true);
                deposit.setFocusableInTouchMode(true);
            }
            if (productType.isMorgageFeeEdit()) {
                mortgage_fee.setFocusable(true);
                mortgage_fee.setEnabled(true);
                mortgage_fee.setFocusableInTouchMode(true);
            }
            if (productType.isHomeVisitFeeEdit()) {
                home_visit_fee.setFocusable(true);
                home_visit_fee.setEnabled(true);
                home_visit_fee.setFocusableInTouchMode(true);
            }
            if (productType.isBaoXianFeeEdit()) {
                baoxian_fee.setFocusable(true);
                baoxian_fee.setEnabled(true);
                baoxian_fee.setFocusableInTouchMode(true);
            }
            if (productType.isLoanAmountYwyCorpEdit()) {
                loan_amount_ywy_corp.setFocusable(true);
                loan_amount_ywy_corp.setEnabled(true);
                loan_amount_ywy_corp.setFocusableInTouchMode(true);
            }
        }else{
            finish();
        }
    }
}
