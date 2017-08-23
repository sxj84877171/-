package com.sxj.carloan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.util.LogUtil;

public class HomeInfoAcitivity extends BaseActivity {

    private ListView listView;
    private HomeInfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_info_acitivity);
        if (loan == null) {
            loan = new ServerBean.RowsBean();
        }
        listView = (ListView) getViewById(R.id.list);

    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(2, 101, 101, "详细信息");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(android.view.Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        LogUtil.i("item " + item.getItemId());
        if (item.getItemId() == 101) {
            Intent intent = new Intent();
            Bundle extras = new Bundle();
            extras.putSerializable("data", loan);
            intent.putExtras(extras);
            intent.setClass(this, HomeInfoItemActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
