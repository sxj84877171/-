package com.sxj.carloan.yewuyuan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.product.IProductCalc;
import com.sxj.carloan.product.ProductFactroy;

/**
 * Created by admin on 2017/8/19.
 */

public class BaseInfotmaitionCalcActivity extends BaseActivity {

    private ServerBean.RowsBean bean;

    private EditText feilv;
    private EditText baozhengjin;
    private EditText yuegong;
    private EditText tiaozhengxiang;
    private EditText gpsfei;
    private EditText diyafei;
    private EditText jiafangfei;
    private EditText heji;
    private EditText kefulvyuetuikuan;

    private Button cancel;
    private Button save ;
    private Button sumbit ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_result_baseinforamtion);
        initView();
        initData();
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

        save = getViewById(R.id.save_action);
        sumbit = getViewById(R.id.submit_action);
        cancel = getViewById(R.id.cancel_action);
    }

    void initData() {
        Intent intent = getIntent();
        ServerBean.RowsBean loan = (ServerBean.RowsBean) intent.getSerializableExtra("loan");
        IProductCalc productCalc = null;
        if (loan != null) {
            productCalc = ProductFactroy.getInstance().getProductCale(loan.getCase_type_id_1(), loan);
        }

        if (productCalc != null) {
            try {
                double d = productCalc.getFeiLv();
                if(d > 0){
                    feilv.setText(double2String(d));
                    loan.setFee_rate(double2String(d));
                }

                d = productCalc.getBaoZhengJin();
                if(d > 0){
                    baozhengjin.setText(double2String(d));
                    loan.setDeposit(double2String(d));
                }

                d = productCalc.getYueGong();
                if(d > 0){
                    yuegong.setText(double2String(d));
                    loan.setPayback_month(double2String(d));
                }

                d = productCalc.getGpsFei();
                if(d > 0){
                    gpsfei.setText(double2String(d));
                    loan.setGps_fee(double2String(d));
                }

                d = productCalc.getHeJi();
                if(d > 0){
                    heji.setText(double2String(d));
                    loan.setFee_total(double2String(d));
                }

                d = productCalc.getKeFuLvYueHouTuiKuan();
                if(d > 0){
                    kefulvyuetuikuan.setText(double2String(d));
                    loan.setFee_return_custom(double2String(d));
                }

                d = productCalc.getTiaoZhengXiang();
                if(d > 0){
                    tiaozhengxiang.setText(double2String(d));
                    loan.setExtras_fee(double2String(d));
                }else{
//                    tiaozhengxiang.setVisibility(View.GONE);
                }

                d = productCalc.getDiYaFei();
                if(d > 0){
                    diyafei.setText(double2String(d));
                    loan.setMortgage_fee(double2String(d));
                }else{
//                    diyafei.setVisibility(View.GONE);
                }

                d = productCalc.getJiaFangFei();
                if(d > 0){
                    jiafangfei.setText(double2String(d));
                    loan.setHome_visit_fee(double2String(d));
                }else{
//                    jiafangfei.setVisibility(View.GONE);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public String double2String(double d) {
        d = d * 1000 ;
        d = (long)d;
        return ((long)d/ 1000) + "." + ((long)d%1000);
    }

    @Override
    public void onBackPressed() {
        back(2);
    }


    public void initListener(){
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(2);
            }
        });

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back(2);
            }
        });
    }

}
