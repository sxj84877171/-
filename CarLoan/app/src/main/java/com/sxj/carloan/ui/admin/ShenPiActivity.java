package com.sxj.carloan.ui.admin;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.LoanQuery;
import com.sxj.carloan.bean.QueryMonth;
import com.sxj.carloan.bean.ResultListBean;
import com.sxj.carloan.ui.BarChartActivity;

import java.util.Date;

import rx.Subscriber;

/**
 * Created by admin on 2017/9/18.
 */

public class ShenPiActivity extends BaseActivity {

    private View begin_date;
    private TextView beginDateText ;
    private TextView endDateText ;
    private View end_date;

    private View queryMonth;
    private View query_role;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.query_tojing);
        initViewId();
        initListener();
    }

    private void initListener() {
        begin_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createStartPickDialog(1);
            }
        });

        end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createStartPickDialog(2);
            }
        });

        Date date = new Date();
        beginDateText.setText((1900 + date.getYear()) + "-" + roundMonth(date.getMonth()) + "-" + date.getDate());
        endDateText.setText((1900 + date.getYear()) + "-" + roundMonth(date.getMonth() + 1) + "-" + date.getDate());
        query_role.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bgn_date = beginDateText.getText().toString();
                if(TextUtils.isEmpty(bgn_date)){
                    toast("请输入开始日期");
                    return;
                }
                if(TextUtils.isEmpty(endDateText.getText().toString())){
                    toast("请输入结束日期");
                    return;
                }
                String end_date = endDateText.getText().toString() + "A";
                String sql = "select count(case when date_ywy between '" + bgn_date + "' and '" + end_date + "' then 1 end) as ywy_case_num," +
                        " count(case when date_ywy_qk between '" + bgn_date + "' and '" + end_date + "' then 1 end) as ywy_qk_num," +
                        " count(case when date_zhengxin_finished between '" + bgn_date + "' and '" + end_date + "' then 1 end) as zhengxin_num," +
                        " count(case when date_baoxian_finished between '" + bgn_date + "' and '" + end_date + "' then 1 end) as baoxian_num," +
                        " count(case when date_dispatch_finished between '" + bgn_date + "' and '" + end_date + "' then 1 end) as dispatch_num," +
                        " count(case when date_dcy_yw between '" + bgn_date + "' and '" + end_date + "' then 1 end) as dcy_num," +
                        " count(case when date_ds_finished between '" + bgn_date + "' and '" + end_date + "' then 1 end) as ds_num," +
                        " count(case when date_fk between '" + bgn_date + "' and '" + end_date + "' then 1 end) as fk_num," +
                        " count(case when date_loan_cw_finished between '" + bgn_date + "' and '" + end_date + "' then 1 end) as loan_cw_num " +
                        " from t_case" ;
                model.searchTableBySQL(sql).subscribe(new Subscriber<ResultListBean<LoanQuery>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResultListBean<LoanQuery> loanQueryResultListBean) {
                        if(loanQueryResultListBean.getRows() != null){
                            Intent intent = new Intent(getActivity(), BarChartActivity.class);
                            intent.putExtra("LoanQuery",loanQueryResultListBean.getRows().get(0));
                            intent.putExtra("query",1);
                            startActivity(intent);
                        }
                    }
                });


            }
        });

        queryMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bgn_date = beginDateText.getText().toString();
                if(TextUtils.isEmpty(bgn_date)){
                    toast("请输入开始日期");
                    return;
                }
                String end_date = endDateText.getText().toString();
                if(TextUtils.isEmpty(end_date)){
                    toast("请输入结束日期");
                    return;
                }
                bgn_date = bgn_date.substring(0,bgn_date.lastIndexOf("-"));
                end_date = end_date.substring(0,end_date.lastIndexOf("-"));
                String tmp_sql = "select  COUNT(*) as sl, " +
                        " LEFT(date_ywy,7) as a_date from t_case where id>0 ";

                tmp_sql += " and LEFT(date_ywy,7) between '" + bgn_date + "' and '" + end_date + "' ";
                tmp_sql += " group by LEFT(date_ywy,7)";

                model.searchTableBySQLByMonth(tmp_sql).subscribe(new Subscriber<ResultListBean<QueryMonth>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ResultListBean<QueryMonth> queryMonthResultListBean) {
                        Intent intent = new Intent(getActivity(), BarChartActivity.class);
                        intent.putExtra("ResultListBean",queryMonthResultListBean);
                        intent.putExtra("query",2);
                        startActivity(intent);
                    }
                });

            }
        });
    }

    private void initViewId() {
        begin_date = findViewById(R.id.begin_date);
        end_date = findViewById(R.id.end_date);
        beginDateText = (TextView)findViewById(R.id.begin_date_text);
        endDateText = (TextView)findViewById(R.id.end_date_text);

        queryMonth = findViewById(R.id.query_month);
        query_role = findViewById(R.id.query_role);
    }


    private AlertDialog datePickDialog;

    void createStartPickDialog(int what) {
        DataChooseListener listener = new DataChooseListener();
        listener.what = what;
        datePickDialog = createDataTimePick(listener);
        datePickDialog.show();
    }

    private class DataChooseListener implements IDateChooseListener{

        public int what ;

        @Override
        public void onDateChoose(String date, int year, int month, int day) {
            if(what == 1){
                beginDateText.setText(date);
            }else{
                endDateText.setText(date);
            }
        }
    }

    public String roundMonth(int month){
        if(month > 9){
            return "" + month ;
        }
        return  "0" + month;
    }
}
