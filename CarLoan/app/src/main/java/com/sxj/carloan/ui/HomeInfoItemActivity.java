package com.sxj.carloan.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sxj.carloan.R;
import com.sxj.carloan.util.LogUtil;

public class HomeInfoItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_info_item);
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(1, 1, 1, "子女情况");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        LogUtil.i("item " + item.getItemId());
        if(item.getItemId() == 1){
            Intent intent = new Intent();
            intent.setClass(this,HomeInfoAcitivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
