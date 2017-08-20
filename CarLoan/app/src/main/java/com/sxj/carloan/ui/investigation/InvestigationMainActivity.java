package com.sxj.carloan.ui.investigation;

import android.content.Intent;
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

public class InvestigationMainActivity extends BaseActivity {

    private ItemRecyclerViewAdapter itemRecyclerViewAdapter;
    private RecyclerView listView;
    private SwipeRefreshLayout mSwipeLayout;
    private int count = 30;
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
                if(rowsBean.getCase_state_id() == 5) {
                    Intent intent = new Intent();
                    intent.setClass(InvestigationMainActivity.this, InvestigationFunctionChoose.class);
                    intent.putExtra("loan", rowsBean);
                    intent.putExtra("state", 0);
                    startActivity(intent);
                }else{
                    toast("已完成");
                }
            }
        });
        itemRecyclerViewAdapter.setTitle1("被调查人");
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

    }

    @Override
    protected void onStart() {
        super.onStart();
        index = max = 0;
        itemRecyclerViewAdapter.cleanValues();
        fromServer();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(1, 1, 1, "全部");
        menu.add(1, 2, 2, "未完成");
        menu.add(1, 3, 3, "已完成");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(android.view.Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        LogUtil.i("item " + item.getItemId());
        if (item.getItemId() == 1) {
           type = 0 ;
            fromServer();
        }else if(item.getItemId() == 2){
            type =1 ;
            fromServer();
        }else if(item.getItemId() == 3){
            type = 2;
            fromServer();
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
//                max = serverBean.getTotal();
//                index = index + serverBean.getRows().size();
                List<ServerBean.RowsBean> rows =  serverBean.getRows();
                List<ServerBean.RowsBean> newRows = new ArrayList<ServerBean.RowsBean>();
                for(ServerBean.RowsBean bean:rows){
                    if(getLoginInfo().getUser_id().equals("" + bean.getUser_id_dcy())){
                        if(type == 0){
                            newRows.add(bean);
                        }else if(type == 1){
                            if(bean.getCase_state_id() == 5){
                                newRows.add(bean);
                            }
                        }else{
                            if(bean.getCase_state_id() != 5){
                                newRows.add(bean);
                            }
                        }
                    }
                }
                itemRecyclerViewAdapter.cleanValues();
                itemRecyclerViewAdapter.addValues(newRows);
            }
        });
    }
}
