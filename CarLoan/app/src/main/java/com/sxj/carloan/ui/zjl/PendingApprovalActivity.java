package com.sxj.carloan.ui.zjl;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.ui.ItemRecyclerViewAdapter;
import com.sxj.carloan.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class PendingApprovalActivity extends BaseActivity {
    private ItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private RecyclerView listView;
    private SwipeRefreshLayout mSwipeLayout;
    private int count = 1000;
    private int index = 0;
    private int max = 0;
    private int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_list);
        listView = getViewById(R.id.list);
        mSwipeLayout = getViewById(R.id.id_swipe_ly);
        itemRecyclerViewAdapter = new ItemRecyclerViewAdapter(null, this);
        itemRecyclerViewAdapter.setOnItemClickListener(new ItemRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ServerBean.RowsBean rowsBean, int position) {

            }
        });
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(itemRecyclerViewAdapter);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (index < max) {
                    fromServer();
                } else {
                    mSwipeLayout.setRefreshing(false);
                }
            }
        });

        index = max = 0;
        itemRecyclerViewAdapter.cleanValues();
        fromServer();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(android.view.Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        LogUtil.i("item " + item.getItemId());
        if (item.getItemId() == 1) {

        }
        return super.onOptionsItemSelected(item);
    }


    public void fromServer() {
        (getActivity()).showProcess();
        model.PageWork("" + count, "" + index, "1").subscribe(new Subscriber<ServerBean>() {
            @Override
            public void onCompleted() {
                mSwipeLayout.setRefreshing(false);
            }

            @Override
            public void onError(Throwable e) {
                mSwipeLayout.setRefreshing(false);
                ( getActivity()).dismiss();
                LogUtil.e(e);
            }

            @Override
            public void onNext(ServerBean serverBean) {
                getActivity().dismiss();
                itemRecyclerViewAdapter.cleanValues();
                List<ServerBean.RowsBean> rows = serverBean.getRows();
                List<ServerBean.RowsBean> newRows = new ArrayList<ServerBean.RowsBean>();
                for (ServerBean.RowsBean bean : rows) {
                    if(bean.getCase_state_id() == 12){
                        newRows.add(bean);
                    }
                }
                itemRecyclerViewAdapter.addValues(newRows);
            }
        });
    }

}
