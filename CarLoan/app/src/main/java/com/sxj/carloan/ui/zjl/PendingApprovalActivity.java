package com.sxj.carloan.ui.zjl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.FuncResponseBean;
import com.sxj.carloan.ui.admin.ShenPiActivity;

import java.util.Date;

import rx.Subscriber;

public class PendingApprovalActivity extends BaseActivity {

    private View pifujiegou;
    private TextView pifujiegouText;
    private View pifuriqi;
    private TextView pifuriqiText;
    private EditText pifuyijian;
    private Button queren ;

    private AlertDialog pifu ;

    private int pifuId = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shenpi);

        pifujiegou = getViewById(R.id.pifujiegou);
        pifujiegouText = getViewById(R.id.pifujiegou_text);
        pifuriqi = getViewById(R.id.pifuriqi);
        pifuriqiText = getViewById(R.id.pifuriqi_text);

        pifuyijian = getViewById(R.id.pifuyijian_text);
        queren = getViewById(R.id.queren);

        pifujiegouText.setText("通过");
        Date date = new Date();
        pifuriqiText.setText((1900 + date.getYear()) + "-" + roundMonth(date.getMonth() + 1) + "-" + date.getDate());

        pifujiegou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        pifuriqi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createStartPickDialog();
            }
        });

        queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = pifuriqiText.getText().toString();
                loan.getId();
                String caseId = "13" ;
                if(pifuId == 1){
                    caseId = "112" ;
                }
                if(pifuId != 0) {
                    model.UpdateTableZongJingLi("" + loan.getId(), pifuyijian.getText().toString(), caseId, time).subscribe(new Subscriber<FuncResponseBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(FuncResponseBean funcResponseBean) {
                            toast("提交成功");
                            finish();
                        }
                    });
                }else {
                    finish();
                }
            }
        });
    }


    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(1, 1, 1, "查看详情");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if(item.getItemId() == 1){
            gotoViewInfo(2);
        }
        return super.onOptionsItemSelected(item);
    }


    public void showDialog(){
        if (pifu == null) {
            final String[] args = new String[]{"尚未批复", "未通过", "通过"};
            DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    pifuId = which;
                    pifujiegouText.setText(args[which]);
                    pifu.dismiss();
                }
            };
            pifu = createAlertDialog(args, listener);
        }
        pifu.show();
    }

    private AlertDialog datePickDialog;

    void createStartPickDialog() {
        DataChooseListener listener = new DataChooseListener();
        datePickDialog = createDataTimePick(listener);
        datePickDialog.show();
    }

    private class DataChooseListener implements IDateChooseListener{

        @Override
        public void onDateChoose(String date, int year, int month, int day) {
            pifuriqiText.setText(date);
        }
    }

    public String roundMonth(int month){
        if(month > 9){
            return "" + month ;
        }
        return  "0" + month;
    }
}
