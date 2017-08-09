package com.sxj.carloan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.util.FileObject;
import com.sxj.carloan.util.LogUtil;

import rx.Subscriber;

public class MainActivity extends BaseActivity {

    private ItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private RecyclerView listView;
    private SwipeRefreshLayout mSwipeLayout;
    private int count = 30;
    private int index = 0;
    private int max = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_list);
        listView = getViewById(R.id.list);
        mSwipeLayout = getViewById(R.id.id_swipe_ly);
        itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(null, this);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(itemRecyclerViewAdapter);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (index < max) {
                    fromServer();
                }else{
                    mSwipeLayout.setRefreshing(false);
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        index = max = 0 ;
        itemRecyclerViewAdapter.cleanValues();
        fromServer();
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
            intent.putExtra("state", 1);
            intent.setClass(getActivity(), InfomationActivity.class);
            startActivity(intent);
        }
        if (item.getItemId() == 2) {
            FileObject.cleanFile(LOGIN_INFO);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void fromServer() {
        ((BaseActivity) getActivity()).showProcess();
     /*   ServerBean serverBean = model.PageWorkLocal("" + count, "" + index, "1");
        boolean isAdd = false;
        if (serverBean != null) {
            ((BaseActivity) getActivity()).dismiss();
            itemRecyclerViewAdapter.addValues(serverBean.getRows());
            isAdd =  true;
        }
        final boolean isA = isAdd;*/
        model.PageWork("" + count, "" + index, "1").subscribe(new Subscriber<ServerBean>() {
            @Override
            public void onCompleted() {
                mSwipeLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                mSwipeLayout.setRefreshing(false);
                ((BaseActivity) getActivity()).dismiss();
                LogUtil.e(e);
            }

            @Override
            public void onNext(ServerBean serverBean) {
//                List<Loan> list = getLoanList(serverBean);
                ((BaseActivity) getActivity()).dismiss();
                max = serverBean.getTotal();
                index = index + serverBean.getRows().size();
                itemRecyclerViewAdapter.addValues(serverBean.getRows());
            }
        });
    }

}
