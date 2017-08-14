package com.sxj.bank.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.sxj.bank.BaseActivity;
import com.sxj.bank.R;

public class PersonCreditActivity extends BaseActivity {

    private ListView loan_list ;
    private ListView external_guarantee;

    private BaseLoanListAdapter loanListAdapter ;
    private BaseLoanListAdapter externalGuaranteeAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_credit);

        loan_list = getViewById(R.id.loan_list);
        external_guarantee = getViewById(R.id.external_guarantee);

        loanListAdapter = new BaseLoanListAdapter(this);
        externalGuaranteeAdapter = new BaseLoanListAdapter(this);
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(1, 1, 1, "其他负债");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == 1){

        }
        return super.onOptionsItemSelected(item);
    }
}
