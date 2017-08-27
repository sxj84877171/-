package com.sxj.carloan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.FuncResponseBean;
import com.sxj.carloan.product.IProductType;
import com.sxj.carloan.product.ProductFactroy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/26.
 */

public class ViewInformation extends BaseActivity {

    private static final String[] PRODUCT_TYPES = new String[]{
            "工行-二手车全分期36个月24.48返4包干",
            "工行-二手车半分期36个月18.8包干",
            "工行-二手车12全分期36个月24.48返4包干",
            "自有资金贷款产品",
            "工行-二手车12全分期36个月24.48返4包干（部分贷款公司自有）",
            "工行-新车全分期36个月18返3",
            "工行-新车半分期36个月13.5",
            "工行-新车半分期24个月10.5"};

    final String[] JUDGE = new String[]{"尚未判定", "未通过", "A级", "B级", "C级", "C+收钥匙"};


    private TextView business_type;
    private View business_type_line;
    private TextView apply_name;
    private TextView sex;
    private View sex_line;
    private View marry_state_line;
    private View is_compension_line;
    private View house_type_line;
    private View home_viste_date_line;
    private TextView id_no;
    private TextView marry_state;
    private TextView phone_num;
    private TextView is_compension;
    private TextView house_type;
    private TextView home_viste_date;
    private TextView house_address;
    private TextView car_typeEditText;
    private TextView transaction_price;
    private TextView loan_time;
    private TextView car_name;
    private TextView car_address;
    private TextView fapiao_jine;
    private TextView daikuan_jine;
    private TextView yinhangshenbao_jine;
    private TextView cancel_action;
    private TextView save_action;
    private TextView submit_action;

    private TextView loan_advance_percent;
    private TextView fee_rate;
    private TextView fee_rate_advance;
    private TextView fee_rate_balance;
    private TextView loan_amount_ywy_corp;
    private TextView loan_amount_high;
    private TextView interest_bank;
    private TextView interest_company;
    private TextView deposit;
    private TextView payback_month_12;
    private TextView payback_month;
    private TextView extras_fee;
    private TextView service_fee;
    private TextView gps_fee;
    private TextView mortgage_fee;
    private TextView home_visit_fee;
    private TextView baoxian_fee;
    private TextView evaluation_fee;
    private TextView earlier_fee;
    private TextView fee_return_agency;
    private TextView fee_total;
    private TextView fee_return_custom;
    private TextView commercial_insurance;
    private TextView commercial_insurance_return;
    private TextView payment_for_agency;

    private View shangchuanshengfenzheng_zheng;
    private View shangchuanshengfenzheng_fan;
    private View shangchuanfuzong;
    private View shangchuanzongjingli;
    private View shangchuancheliangzhaopian;

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

    private TextView daikuan_yewuyuan;
    private TextView daikuan_diaochayuan;
    private TextView daikuan_jielun;
    private TextView daikuan_riqi;
    private TextView diaocha_riqi;
    private TextView daikuan_beizhu;

    private View diaocha_line;
    private View daikuan_yewuyuan_line;
    private View daikuan_diaochayuan_line;
    private View daikuan_jielun_line;
    private View time_line;
    private View diaochayuan_biezhu_line;

    private View rongyimianqian_line;
    private View chakanjiating_line;
    private View chakangongzuo_line;
    private View chakanyinhangliushui_line;
    private View chakanmianqian_line;
    private View chakanchandiao_line;
    private View chakanzhengjian_line;
    private View diaochayuan_zhao_line;
    private View diaochayuan_ye;


    ArrayList<CharSequence> sfzzList = new ArrayList<CharSequence>();
    ArrayList<CharSequence> sfzfList = new ArrayList<CharSequence>();
    ArrayList<CharSequence> fzList = new ArrayList<CharSequence>();
    ArrayList<CharSequence> zjlList = new ArrayList<CharSequence>();
    ArrayList<CharSequence> cheliangList = new ArrayList<CharSequence>();

    ArrayList<CharSequence> lujiaList = new ArrayList<CharSequence>();
    ArrayList<CharSequence> lugongList = new ArrayList<CharSequence>();
    ArrayList<CharSequence> luyinList = new ArrayList<CharSequence>();
    ArrayList<CharSequence> lumianList = new ArrayList<CharSequence>();
    ArrayList<CharSequence> luchanList = new ArrayList<CharSequence>();
    ArrayList<CharSequence> luzhengList = new ArrayList<CharSequence>();

    private View goBack ;

    IProductType productType = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_inforatmion);
        initViewById();
        initData();
        loadData();
        initViewVisible();
        chooseRole();
    }

    private void chooseRole() {

        Intent intent = getIntent();
        int role = 1 ;// 业务员
        if(intent != null){
            role = intent.getIntExtra("role",1);
        }
        if(role == 2){// 调查员
            //需要显示一些控件出来
            diaocha_line.setVisibility(View.VISIBLE);
            daikuan_yewuyuan_line.setVisibility(View.VISIBLE);
            daikuan_diaochayuan_line.setVisibility(View.VISIBLE);
            daikuan_jielun_line.setVisibility(View.VISIBLE);
            time_line.setVisibility(View.VISIBLE);
            diaochayuan_biezhu_line.setVisibility(View.VISIBLE);

            rongyimianqian_line.setVisibility(View.VISIBLE);
            chakanjiating_line.setVisibility(View.VISIBLE);
            chakangongzuo_line.setVisibility(View.VISIBLE);
            chakanyinhangliushui_line.setVisibility(View.VISIBLE);
            chakanmianqian_line.setVisibility(View.VISIBLE);
            chakanchandiao_line.setVisibility(View.VISIBLE);
            chakanzhengjian_line.setVisibility(View.VISIBLE);

            diaochayuan_zhao_line.setVisibility(View.VISIBLE);
            diaochayuan_ye.setVisibility(View.VISIBLE);

        }
    }

    private void initViewById() {
        business_type = getViewById(R.id.business_type);
        business_type_line = getViewById(R.id.business_type_line);
        sex = getViewById(R.id.sex);
        sex_line = getViewById(R.id.sex_line);
        id_no = getViewById(R.id.id_no);
        marry_state = getViewById(R.id.marry_state);
        phone_num = getViewById(R.id.phone_num);
        is_compension = getViewById(R.id.is_compension);
        house_type = getViewById(R.id.house_type);
        home_viste_date = getViewById(R.id.home_viste_date);
        house_address = getViewById(R.id.house_address);

        marry_state_line = getViewById(R.id.marry_state_line);
        is_compension_line = getViewById(R.id.is_compension_line);
        house_type_line = getViewById(R.id.house_type_line);
        home_viste_date_line = getViewById(R.id.home_viste_date_line);

        car_name = getViewById(R.id.car_name);
        car_address = getViewById(R.id.car_address);
        fapiao_jine = getViewById(R.id.fapiao_jine);
        yinhangshenbao_jine = getViewById(R.id.yinhangshenbao_jine);
        daikuan_jine = getViewById(R.id.daikuan_jine);

        car_typeEditText = getViewById(R.id.car_type);
        transaction_price = getViewById(R.id.transaction_price);
        loan_time = getViewById(R.id.loan_time);
        apply_name = getViewById(R.id.apply_name);

        /**********************************************************/
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

        /********************************************************/
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

        /**************************************************************/

        daikuan_diaochayuan = getViewById(R.id.daikuan_diaochayuan);
        daikuan_yewuyuan = getViewById(R.id.daikuan_yewuyuan);
        daikuan_jielun = getViewById(R.id.daikuan_jielun);
        daikuan_riqi = getViewById(R.id.daikuan_riqi);
        submit_action = getViewById(R.id.submit_action);
        diaocha_riqi = getViewById(R.id.diaocha_riqi);
        daikuan_beizhu = getViewById(R.id.daikuan_beizhu);

        diaocha_line = getViewById(R.id.diaocha_line);
        daikuan_yewuyuan_line = getViewById(R.id.daikuan_yewuyuan_line);
        daikuan_diaochayuan_line = getViewById(R.id.daikuan_diaochayuan_line);
        daikuan_jielun_line = getViewById(R.id.daikuan_jielun_line);
        time_line = getViewById(R.id.time_line);
        diaochayuan_biezhu_line = getViewById(R.id.diaochayuan_biezhu_line);

        rongyimianqian_line = getViewById(R.id.rongyimianqian_line);
        chakanjiating_line = getViewById(R.id.chakanjiating_line);
        chakangongzuo_line = getViewById(R.id.chakangongzuo_line);
        chakanyinhangliushui_line = getViewById(R.id.chakanyinhangliushui_line);
        chakanmianqian_line = getViewById(R.id.chakanmianqian_line);
        chakanchandiao_line = getViewById(R.id.chakanchandiao_line);
        chakanzhengjian_line = getViewById(R.id.chakanzhengjian_line);
        diaochayuan_zhao_line = getViewById(R.id.diaochayuan_zhao_line);
        diaochayuan_ye = getViewById(R.id.diaochayuan_ye);

        /*************************************************************/
        goBack = getViewById(R.id.go_back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        shangchuanshengfenzheng_zheng = getViewById(R.id.shangchuanshengfenzheng_zheng);
        shangchuanshengfenzheng_zheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sfzzList.size() > 0){
                    gotoViewPhoto(sfzzList);
                }else{
                    toast("该类型图片未上传.");
                }

            }
        });
        shangchuanshengfenzheng_fan = getViewById(R.id.shangchuanshengfenzheng_fan);
        shangchuanshengfenzheng_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sfzfList.size() > 0){
                    gotoViewPhoto(sfzfList);
                }else{
                    toast("该类型图片未上传.");
                }
            }
        });
        shangchuanfuzong = getViewById(R.id.shangchuanfuzong);
        shangchuanfuzong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fzList.size() > 0){
                    gotoViewPhoto(fzList);
                }else{
                    toast("该类型图片未上传.");
                }
            }
        });

        shangchuanzongjingli = getViewById(R.id.shangchuanzongjingli);
        shangchuanzongjingli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(zjlList.size() > 0){
                    gotoViewPhoto(zjlList);
                }else{
                    toast("该类型图片未上传.");
                }
            }
        });

        shangchuancheliangzhaopian = getViewById(R.id.shangchuancheliangzhaopian);
        shangchuancheliangzhaopian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cheliangList.size() > 0){
                    gotoViewPhoto(cheliangList);
                }else{
                    toast("该类型图片未上传.");
                }
            }
        });
    }

    void initData() {
        if (loan != null) {
            int productId = 1;
            try {
                productId = Integer.parseInt(loan.getProduct_id());
            } catch (Exception ex) {
                loan.setProduct_id("1");
                loan.setCase_type_id_1(1);
            }
            loan_time.setText(loan.getCredit_years() + "");
            if (productId <= PRODUCT_TYPES.length && (productId > 0)) {
                business_type.setText(PRODUCT_TYPES[productId - 1]);
                loan_time.setText(ProductFactroy.getInstance().processProductType(productId, 0).getCreditYears() + "");
            }
            apply_name.setText(getLoanString(loan.getCust_name_tmp()));
            sex.setText(getLoanString(loan.getCust_sex()));
            id_no.setText(getLoanString(loan.getCust_iden()));
            phone_num.setText(getLoanString(loan.getCust_mobile()));
            home_viste_date.setText(loan.getHome_visit_date());
            car_typeEditText.setText(loan.getCar_type());
            house_address.setText(loan.getCust_address());
            transaction_price.setText(loan.getDeal_price() + "");
            car_name.setText(loan.getChehang_name());
            car_address.setText(loan.getChehang_address());
            fapiao_jine.setText(loan.getInvoice_price());
            yinhangshenbao_jine.setText(loan.getLoan_amount_high());
            /********************************************/
            daikuan_jine.setText(loan.getLoan_amount_ywy());
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

            /************************************************/
            daikuan_yewuyuan.setText(loan.getLoan_amount_ywy());//loan_amount_ywy
            daikuan_diaochayuan.setText(loan.getLoan_amount_dcy());
            daikuan_riqi.setText(loan.getDate_dcy_yw());
            daikuan_jielun.setText(JUDGE[loan.getDcy_result_id()]);
            daikuan_beizhu.setText(loan.getDcy_info());
            diaocha_riqi.setText(loan.getDate_dcy_info());
        }
        initChoose();
    }

    public String getLoanString(String str) {
        if (str != null) {
            return str.trim();
        }
        return "";
    }

    void initChoose() {
//        initStageType(loan.getInstallment_type_id() - 1);
        initHouseType(loan.getRoom_type_id() - 1);
        initCompension(loan.getIf_gcr_id());
        initMarrayState(loan.getCust_marriage_id());
        sex.setText(loan.getCust_sex());
        initType(loan.getCase_type_id_1() - 1);
    }

    private void initHouseType(int which) {
        switch (which) {
            case 0:
                house_type.setText("全款商品房");
                break;
            case 1:
                house_type.setText("按揭商品房");
                break;
            case 2:
                house_type.setText("自建房");
                break;
            case 3:
                house_type.setText("租房");
                break;
            case 4:
                house_type.setText("亲属住房");
                break;
        }
    }

    private void initCompension(int which) {
        switch (which) {
            case 0:
                is_compension.setText("否");
                break;
            case 1:
                is_compension.setText("是");
                break;
        }
    }

    private void initMarrayState(int which) {
        switch (which) {
            case 0:
                marry_state.setText("已婚");
                break;
            case 1:
                marry_state.setText("未婚");
                break;
            case 2:
                marry_state.setText("离异");
                break;
            case 3:
                marry_state.setText("丧偶");
                break;
            case 4:
                marry_state.setText("离异再婚");
                break;
        }
    }

    private void initType(int which) {
        if (which < PRODUCT_TYPES.length && which >= 0) {
            business_type.setText(PRODUCT_TYPES[which]);
            loan.setProduct_id("" + (which + 1));
        }
    }

    void loadData(){
        if (loan.getId() > 0) {
            model.FileExisted("photo/ywy/" + loan.getId() + "_1_1.jpg").subscribe(new LoanSubscriber<FuncResponseBean>() {
                @Override
                public void onNext(FuncResponseBean funcResponseBean) {
                    if ("YES".equals(funcResponseBean.getSuccess())) {
                        sfzzList.clear();
                        sfzzList.add("http://carmis.timesly.cn/photo/ywy/{case_id}_1_1.jpg".replace("{case_id}",loan.getId()+""));
                    }
                }
            });
            model.FileExisted("photo/ywy/" + loan.getId() + "_1_2.jpg").subscribe(new LoanSubscriber<FuncResponseBean>() {
                @Override
                public void onNext(FuncResponseBean funcResponseBean) {
                    if ("YES".equals(funcResponseBean.getSuccess())) {
                        sfzfList.clear();
                        sfzfList.add("http://carmis.timesly.cn/photo/ywy/{case_id}_1_2.jpg".replace("{case_id}",loan.getId()+""));
                    }
                }
            });
            model.FileExisted("photo/ywy/" + loan.getId() + "_2.jpg").subscribe(new LoanSubscriber<FuncResponseBean>() {
                @Override
                public void onNext(FuncResponseBean funcResponseBean) {
                    if ("YES".equals(funcResponseBean.getSuccess())) {
                        fzList.clear();
                        fzList.add("http://carmis.timesly.cn/photo/ywy/{case_id}_2.jpg".replace("{case_id}",loan.getId()+""));
                    }
                }
            });
            model.FileExisted("photo/ywy/" + loan.getId() + "_3.jpg").subscribe(new LoanSubscriber<FuncResponseBean>() {
                @Override
                public void onNext(FuncResponseBean funcResponseBean) {
                    if ("YES".equals(funcResponseBean.getSuccess())) {
                        zjlList.clear();
                        zjlList.add("http://carmis.timesly.cn/photo/ywy/{case_id}_3.jpg".replace("{case_id}",loan.getId()+""));
                    }
                }
            });
            model.getCarPhotoName(loan.getId()).subscribe(new LoanSubscriber<FuncResponseBean>() {
                @Override
                public void onNext(FuncResponseBean funcResponseBean) {
                    if ("YES".equals(funcResponseBean.getSuccess())) {
                        if (!TextUtils.isEmpty(funcResponseBean.getMessage())) {
                            cheliangList.clear();
                            String[] files = funcResponseBean.getMessage().split(",");
                            for(String file: files){
                                if(!TextUtils.isEmpty(file)){
                                    cheliangList.add("http://carmis.timesly.cn/photo/car/" + file);
                                }
                            }
                        }
                    }
                }
            });

            model.GetDcyPhotoName(loan.getId()).subscribe(new LoanSubscriber<FuncResponseBean>() {
                @Override
                public void onNext(FuncResponseBean funcResponseBean) {
                    super.onNext(funcResponseBean);
                    if ("YES".equals(funcResponseBean.getSuccess())) {
                        String msg = funcResponseBean.getMessage();
                        if (!TextUtils.isEmpty(msg)) {
                            String[] strs = msg.split(",");
                            for (String str : strs) {
                                if (str.startsWith(loan.getId() + "_1d")) {
                                    lujiaList.add("http://carmis.timesly.cn/photo/dcy/" + str);
                                }
                                if (str.startsWith(loan.getId() + "_2d")) {
                                    lugongList.add("http://carmis.timesly.cn/photo/dcy/" + str);
                                }
                                if (str.startsWith(loan.getId() + "_3d")) {
                                    luyinList.add("http://carmis.timesly.cn/photo/dcy/" + str);
                                }
                                if (str.startsWith(loan.getId() + "_4d")) {
                                    lumianList.add("http://carmis.timesly.cn/photo/dcy/" + str);
                                }
                                if (str.startsWith(loan.getId() + "_5d")) {
                                    luchanList.add("http://carmis.timesly.cn/photo/dcy/" + str);
                                }
                                if (str.startsWith(loan.getId() + "_6d")) {
                                    luzhengList.add("http://carmis.timesly.cn/photo/dcy/" + str);
                                }
                            }
                        }
                    }
                }
            });
        }
    }


    private void initViewVisible() {
        try {
            try {
                productType = ProductFactroy.getInstance().processProductType(Integer.parseInt(loan.getProduct_id()), Double.parseDouble(loan.getLoan_amount_ywy()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
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


    public void rongYiMianQian(View view){
//        if(lujiaList.size() > 0){
//            gotoViewPhoto(lujiaList);
//        }else{
            toast("视频暂时不支持浏览.");
//        }
    }

    public void uploadFamilyPhoto(View view){
        if(lujiaList.size() > 0){
            gotoViewPhoto(lujiaList);
        }else{
            toast("该类型图片未上传.");
        }
    }

    public void uploadWorkAddressPhoto(View view){
        if(lugongList.size() > 0){
            gotoViewPhoto(lugongList);
        }else{
            toast("该类型图片未上传.");
        }
    }

    public void uploadBankPhoto(View view){
        if(luyinList.size() > 0){
            gotoViewPhoto(luyinList);
        }else{
            toast("该类型图片未上传.");
        }
    }

    public void lurumianqian(View view){
        if(lumianList.size() > 0){
            gotoViewPhoto(lumianList);
        }else{
            toast("该类型图片未上传.");
        }
    }

    public void uploadChanPhoto(View view){
        if(luchanList.size() > 0){
            gotoViewPhoto(luchanList);
        }else{
            toast("该类型图片未上传.");
        }
    }

    public void uploadIdPhoto(View view){
        if(luzhengList.size() > 0){
            gotoViewPhoto(luzhengList);
        }else{
            toast("该类型图片未上传.");
        }
    }

}
