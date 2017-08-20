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
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.product.IProductCalc;
import com.sxj.carloan.product.ProductFactroy;
import com.sxj.carloan.util.BeanToMap;
import com.sxj.carloan.util.DateUtil;

import rx.Subscriber;

/**
 * Created by admin on 2017/8/19.
 */

public class BaseInfotmaitionCalcActivity extends BaseActivity {
    private EditText feilv;
    private EditText baozhengjin;
    private EditText yuegong;
    private EditText tiaozhengxiang;
    private EditText gpsfei;
    private EditText diyafei;
    private EditText jiafangfei;
    private EditText heji;
    private EditText kefulvyuetuikuan;

    private TextView cancel_action;
    private TextView save_action;
    private TextView submit_action;

    private ServerBean.RowsBean loan;
    IProductCalc productCalc = null;
    private int ywy = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_result_baseinforamtion);
        Intent intent = getIntent();
        loan = (ServerBean.RowsBean) intent.getSerializableExtra("loan");
        ywy = intent.getIntExtra("ywy",1);
        if (loan != null) {//loan_amount_ywy
            if (ywy != 1) {
                productCalc = ProductFactroy.getInstance().getProductCale(loan.getCase_type_id_1(), loan, Double.parseDouble(loan.getLoan_amount_dcy()));
            } else {
                productCalc = ProductFactroy.getInstance().getProductCale(loan.getCase_type_id_1(), loan, Double.parseDouble(loan.getLoan_amount_ywy()));
            }
        }
        initView();
        initData();
        initListener();
    }

    void initView() {
        feilv = getViewById(R.id.feilv);
        baozhengjin = getViewById(R.id.baozhengjin);
        yuegong = getViewById(R.id.yuegong);
        gpsfei = getViewById(R.id.gpsfei);
        diyafei = getViewById(R.id.diyanfei);
        jiafangfei = getViewById(R.id.jiafangfei);
        heji = getViewById(R.id.heji);
        kefulvyuetuikuan = getViewById(R.id.kefulvyuehoutuikuan);
        tiaozhengxiang = getViewById(R.id.tiaozhengxiang);

        save_action = getViewById(R.id.save_action1);
        submit_action = getViewById(R.id.submit_action1);
        cancel_action = getViewById(R.id.cancel_action1);
    }

    void initData() {
        if (productCalc != null) {
            try {
                double d = productCalc.getFeiLv();
                if (d > 0) {
                    feilv.setText(double2String(d));
                    loan.setFee_rate(double2String(d));
                }

                d = productCalc.getBaoZhengJin();
                if (d > 0) {
                    baozhengjin.setText(double2String(d));
                    loan.setDeposit(double2String(d));
                }

                d = productCalc.getYueGong();
                if (d > 0) {
                    yuegong.setText(double2String(d));
                    loan.setPayback_month(double2String(d));
                }

                d = productCalc.getGpsFei();
                if (d > 0) {
                    gpsfei.setText(double2String(d));
                    loan.setGps_fee(double2String(d));
                }

                d = productCalc.getHeJi();
                heji.setText(double2String(d));
                loan.setFee_total(double2String(d));

                d = productCalc.getKeFuLvYueHouTuiKuan();
                if (d > 0) {
                    kefulvyuetuikuan.setText(double2String(d));
                    loan.setFee_return_custom(double2String(d));
                }

                d = productCalc.getTiaoZhengXiang();
                if (d > 0) {
                    tiaozhengxiang.setText(double2String(d));
                    loan.setExtras_fee(double2String(d));
                } else {
                    tiaozhengxiang.setEnabled(true);
                    tiaozhengxiang.setFocusable(true);
                    tiaozhengxiang.setFocusableInTouchMode(true);
                }

                d = productCalc.getDiYaFei();
                if (d > 0) {
                    diyafei.setText(double2String(d));
                    loan.setMortgage_fee(double2String(d));
                } else {
                    diyafei.setEnabled(true);
                    diyafei.setFocusable(true);
                    diyafei.setFocusableInTouchMode(true);
                }

                d = productCalc.getJiaFangFei();
                if (d > 0) {
                    jiafangfei.setText(double2String(d));
                    loan.setHome_visit_fee(double2String(d));
                } else {
//                    jiafangfei.setEnabled(true);
//                    jiafangfei.setFocusable(true);
//                    jiafangfei.setFocusableInTouchMode(true);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public String double2String(double d) {
        d = d * 1000;
        d = (long) d;
        long t = (long) d % 1000 ;
        if(t < 0){ t = -t;}
        return ((long) d / 1000) + "." + t;
    }

    @Override
    public void onBackPressed() {
        back(2);
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
                toast("计算正在加紧修复中……");
                try {
                    double tiaozh = Double.parseDouble(tiaozhengxiang.getText().toString());
                    productCalc.setTiaoZhengXiang(tiaozh);
                    initData();
                }catch (Exception ex){
                    toast("请正确输入调整项");
                    return;
                }

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
                    }
                });
            }
        });

        submit_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("计算正在加紧修复中……");
                try {
                    double tiaozh = Double.parseDouble(tiaozhengxiang.getText().toString());
                    productCalc.setTiaoZhengXiang(tiaozh);
                    initData();
                }catch (Exception ex){
                    toast("请正确输入调整项");
                    return;
                }


                if(loan.getCase_state_id() == 0 || loan.getCase_state_id() == 101){
                    loan.setCase_state_id(1);
                }else if(loan.getCase_state_id() == 8 ){
                    loan.setCase_state_id(10);
                }else{
                    loan.setCase_state_id(loan.getCase_state_id() % 100);
                }

                loan.setDate_ywy(DateUtil.getWaterDate());
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
                        back(2);
                    }
                });

            }
        });
    }

}
