package com.sxj.carloan.ui.investigation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.FuncResponseBean;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.util.BeanToMap;
import com.sxj.carloan.util.DateUtil;
import com.sxj.carloan.yewuyuan.InfomationActivity;

import rx.Subscriber;

/**
 * Created by admin on 2017/8/20.
 */

public class DiaoChaYuanWeiFu extends BaseActivity {

    private TextView daikuan_yewuyuan;
    private TextView daikuan_diaochayuan;
    private TextView daikuan_jielun;
    private TextView daikuan_riqi;
    private View daikuan_jielun_line;
    private View time_line;
    private Button submit_action;
    private View diaocha_line;
    private TextView diaocha_riqi;
    private TextView daikuan_beizhu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weihuxinxi);
        initView();
        initData();
        initListener();
    }

    void initView() {
        daikuan_diaochayuan = getViewById(R.id.daikuan_diaochayuan);
        daikuan_yewuyuan = getViewById(R.id.daikuan_yewuyuan);
        daikuan_jielun = getViewById(R.id.daikuan_jielun);
        daikuan_riqi = getViewById(R.id.daikuan_riqi);
        submit_action = getViewById(R.id.submit_action);
        daikuan_jielun_line = getViewById(R.id.daikuan_jielun_line);
        time_line = getViewById(R.id.time_line);
        diaocha_line = getViewById(R.id.diaocha_line);
        diaocha_riqi = getViewById(R.id.diaocha_riqi);
        daikuan_beizhu = getViewById(R.id.daikuan_beizhu);
    }

    void initData() {
        if (loan != null) {
            daikuan_yewuyuan.setText(loan.getLoan_amount_ywy());//loan_amount_ywy
            daikuan_diaochayuan.setText(loan.getLoan_amount_dcy());
            daikuan_riqi.setText(loan.getDate_dcy_yw());
            daikuan_jielun.setText(args[loan.getDcy_result_id()]);
            daikuan_beizhu.setText(loan.getDcy_info());
            diaocha_riqi.setText(loan.getDate_dcy_info());
        }
    }

    void initListener() {
        submit_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        daikuan_jielun_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createMarrayDialog();
            }
        });

        diaocha_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseLine = 1;
                AlertDialog dialog = createDataTimePick(listener);
                dialog.show();
            }
        });

        time_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseLine = 2;
                AlertDialog dialog = createDataTimePick(listener);
                dialog.show();
            }
        });
    }

    private int chooseLine = 0;
    IDateChooseListener listener = new IDateChooseListener() {
        @Override
        public void onDateChoose(String date, int year, int month, int day) {
            if (chooseLine == 1) {
                diaocha_riqi.setText(date);
            } else if (chooseLine == 2) {
                daikuan_riqi.setText(date);
            }
        }
    };

    private void save() {
        loan.setDate_case(DateUtil.getWaterDate());
        loan.setUser_id_dcy_info(Integer.parseInt(getLoginInfo().getUser_id()));
        loan.setLoan_amount(loan.getLoan_amount_dcy());

        if (TextUtils.isEmpty(daikuan_diaochayuan.getText().toString())) {
            toast("请输入贷款金额");
            return;
        }
        loan.setLoan_amount_dcy(daikuan_diaochayuan.getText().toString());

        if (TextUtils.isEmpty(daikuan_riqi.getText().toString())) {
            toast("请选择判定日期");
            return;
        }
        //判定日期是date_dcy_yw
        loan.setDate_dcy_yw(daikuan_riqi.getText().toString());

        if (TextUtils.isEmpty(daikuan_jielun.getText().toString())) {
            toast("请输入贷款结论");
            return;
        }
        //dcy_info


        if (TextUtils.isEmpty(daikuan_beizhu.getText().toString())) {
            toast("请输入贷款备注");
            return;
        }
        loan.setDcy_info(daikuan_beizhu.getText().toString());

        if (TextUtils.isEmpty(diaocha_riqi.getText().toString())) {
            toast("请选择调查日期");
            return;
        }
        loan.setDate_dcy_info(diaocha_riqi.getText().toString());

        if (loan.getCase_state_id() != 5 && loan.getCase_state_id() != 6
                && loan.getCase_state_id() != 105) {
            toast("当前状态不能再次提交调查结论");
            return;
        }




        model.update(BeanToMap.transRowsBean2Map(loan)).subscribe(new Subscriber<FuncResponseBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                toast("保存失败!");
            }

            @Override
            public void onNext(FuncResponseBean funcResponseBean) {
                if("YES".equals(funcResponseBean.getSuccess())){
                    toast("保存成功");
                    finish();
                }else{
                    toast("保存失败");
                }
            }
        });
    }

    private AlertDialog jielunDialog;
    final String[] args = new String[]{"尚未判定", "未通过", "A级", "B级", "C级", "C+收钥匙"};

    void createMarrayDialog() {
        if (jielunDialog == null) {
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    initMarrayState(which);
                    daikuan_jielun.setText(args[which]);
                    loan.setDcy_result_id(which);
                    jielunDialog.dismiss();
                }
            };
            jielunDialog = createAlertDialog(args, listener);
        }
        jielunDialog.show();
    }


}
