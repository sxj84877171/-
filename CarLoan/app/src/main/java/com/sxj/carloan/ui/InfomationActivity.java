package com.sxj.carloan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.Loan;

public class InfomationActivity extends BaseActivity {

    private EditText business_type;

    private EditText apply_name;
    private EditText sex;
    private EditText id_no;
    private EditText marry_state;
    private EditText phone_num;
    private EditText is_compension;
    private EditText house_type;
    private EditText home_viste_date;
    private EditText house_address;
    private EditText car_type;
    private EditText transaction_price;
    private EditText loan_time;
    private EditText staget_type;

    private EditText person_id;
    private EditText build_date;
    private EditText business_state;

    private Button modify;
    private Button save;

    private Loan loan;

    /**
     * 0 view
     * 1 modify
     * 2 modify
     */
    private int state = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        initViewById();
        Intent intent = getIntent();
        loan = (Loan) intent.getSerializableExtra("data");
        state = intent.getIntExtra("state", 0);

        initViewById();
        displayState();
        initListener();
    }

    private void initListener() {
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = 1 ;
                changeState();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //put to net
            }
        });
    }


    private void initViewById() {
        business_type = getViewById(R.id.business_type);
        sex = getViewById(R.id.sex);
        id_no = getViewById(R.id.id_no);
        marry_state = getViewById(R.id.marry_state);
        phone_num = getViewById(R.id.phone_num);
        is_compension = getViewById(R.id.is_compension);
        house_type = getViewById(R.id.house_type);
        home_viste_date = getViewById(R.id.home_viste_date);
        house_address = getViewById(R.id.house_address);
        car_type = getViewById(R.id.car_type);
        transaction_price = getViewById(R.id.transaction_price);
        loan_time = getViewById(R.id.loan_time);
        staget_type = getViewById(R.id.staget_type);
        person_id = getViewById(R.id.person_id);
        build_date = getViewById(R.id.build_date);
        business_state = getViewById(R.id.business_state);
        modify = getViewById(R.id.add);
        save = getViewById(R.id.save);
    }


    private void displayState() {
        changeState();
        if (state == 0) {
            modify.setVisibility(View.VISIBLE);
            save.setVisibility(View.GONE);
            modify.setText("修改");
        } else {
            modify.setVisibility(View.GONE);
            save.setVisibility(View.VISIBLE);
        }
    }

    private void changeState() {
        boolean stateBoolean = state != 0;
        business_type.setFocusable(stateBoolean);
        business_type.setFocusableInTouchMode(stateBoolean);

        sex.setFocusable(stateBoolean);
        sex.setFocusableInTouchMode(stateBoolean);
        id_no.setFocusable(stateBoolean);
        id_no.setFocusableInTouchMode(stateBoolean);
        marry_state.setFocusable(stateBoolean);
        marry_state.setFocusableInTouchMode(stateBoolean);
        phone_num.setFocusable(stateBoolean);
        phone_num.setFocusableInTouchMode(stateBoolean);
        is_compension.setFocusable(stateBoolean);
        is_compension.setFocusableInTouchMode(stateBoolean);
        house_type.setFocusable(stateBoolean);
        house_type.setFocusableInTouchMode(stateBoolean);
        home_viste_date.setFocusable(stateBoolean);
        home_viste_date.setFocusableInTouchMode(stateBoolean);
        car_type.setFocusable(stateBoolean);
        car_type.setFocusableInTouchMode(stateBoolean);

        transaction_price.setFocusable(stateBoolean);
        transaction_price.setFocusableInTouchMode(stateBoolean);

        loan_time.setFocusable(stateBoolean);
        loan_time.setFocusableInTouchMode(stateBoolean);

        staget_type.setFocusable(stateBoolean);
        staget_type.setFocusableInTouchMode(stateBoolean);

        person_id.setFocusable(stateBoolean);
        person_id.setFocusableInTouchMode(stateBoolean);

        build_date.setFocusable(stateBoolean);
        build_date.setFocusableInTouchMode(stateBoolean);

        business_state.setFocusable(stateBoolean);
        business_state.setFocusableInTouchMode(stateBoolean);
    }

}
