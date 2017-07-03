package com.sxj.carloan.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.net.ApiServiceModel;
import com.sxj.carloan.util.LogUtil;

import rx.Subscriber;

public class MainActivity extends BaseActivity {

    private  ItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_list);
        listView = getViewById(R.id.list);
        itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(null,this);
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(1, 1, 1, "业务建档");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(android.view.Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        LogUtil.i("item " + item.getItemId());
        if (item.getItemId() == 1) {
            Intent intent = new Intent();
            intent.putExtra("state",1);
            intent.setClass(getActivity(),InfomationActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    public void fromServer(){
        ((BaseActivity)getActivity()).showProcess();
        new ApiServiceModel().PageWork("30","0","1").subscribe(new Subscriber<ServerBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ((BaseActivity)getActivity()).dismiss();
                LogUtil.e(e);
            }

            @Override
            public void onNext(ServerBean serverBean) {
//                List<Loan> list = getLoanList(serverBean);
                ((BaseActivity)getActivity()).dismiss();
                itemRecyclerViewAdapter.setValues(serverBean.getRows());
            }
        });
    }

}
