package com.sxj.carloan.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sxj.carloan.ApplicationInfoManager;
import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.ServerBean;
import com.sxj.carloan.util.FileObject;
import com.sxj.carloan.util.LogUtil;
import com.sxj.carloan.yewuyuan.InfomationActivity;
import com.sxj.carloan.yewuyuan.YeWuJianDangActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

public class MainActivity extends BaseActivity {

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
                if (rowsBean.getCase_state_id() == 0 ||
                        rowsBean.getCase_state_id() == 8 ||
                        rowsBean.getCase_state_id() == 101 ||
                        rowsBean.getCase_state_id() == 105 ||
                        rowsBean.getCase_state_id() == 106 ||
                        rowsBean.getCase_state_id() == 107 ||
                        rowsBean.getCase_state_id() == 110 ||
                        rowsBean.getCase_state_id() == 112) {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, YeWuJianDangActivity.class);
                    loan = rowsBean;
                    ApplicationInfoManager.getInstance().setInfo(loan);
                    intent.putExtra("loan", loan);
                    intent.putExtra("state", 0);
                    getActivity().startActivity(intent);
                }else{
                    loan = rowsBean;
                    ApplicationInfoManager.getInstance().setInfo(loan);
                    gotoViewInfo(1);
                }

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

        checkVersion();

    }

    @Override
    protected void onResume() {
        super.onResume();
        index = max = 0;
        itemRecyclerViewAdapter.cleanValues();
        fromServer();
    }

    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        menu.add(1, 1, 1, "业务建档");
        menu.add(2,2,2,"全部");
        menu.add(3,3,3,"建档中");
        menu.add(4,4,4,"已完成");
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
            loan = null;
            ApplicationInfoManager.getInstance().setInfo(loan);
            intent.setClass(getActivity(), YeWuJianDangActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == 2){
            type = 0 ;
            fromServer();
        }

        if(item.getItemId() == 3){
            type = 1;
            fromServer();
        }

        if(item.getItemId() == 4){
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
                itemRecyclerViewAdapter.cleanValues();
                //tmp_role_id
                List<ServerBean.RowsBean> rows = serverBean.getRows();
                List<ServerBean.RowsBean> newRows = new ArrayList<ServerBean.RowsBean>();
                for (ServerBean.RowsBean bean : rows) {
                    if (getLoginInfo().getUser_id().equals("" + bean.getUser_id_ywy())) {
                        if (type == 0) {
                            newRows.add(bean);
                        } else if (type == 1) {
                            //0,8,101,105,106,107,110,111,112,113,119
                            if (bean.getCase_state_id() == 0 ||
                                    bean.getCase_state_id() == 8 ||
                                    bean.getCase_state_id() == 101 ||
                                    bean.getCase_state_id() == 105 ||
                                    bean.getCase_state_id() == 106 ||
                                    bean.getCase_state_id() == 107 ||
                                    bean.getCase_state_id() == 110 ||
                                    bean.getCase_state_id() == 111 ||
                                    bean.getCase_state_id() == 112 ||
                                    bean.getCase_state_id() == 113 ||
                                    bean.getCase_state_id() == 119) {
                                newRows.add(bean);
                            }
                        } else {
                            if (!(bean.getCase_state_id() == 0 ||
                                    bean.getCase_state_id() == 8 ||
                                    bean.getCase_state_id() == 101 ||
                                    bean.getCase_state_id() == 105 ||
                                    bean.getCase_state_id() == 106 ||
                                    bean.getCase_state_id() == 107 ||
                                    bean.getCase_state_id() == 110 ||
                                    bean.getCase_state_id() == 111 ||
                                    bean.getCase_state_id() == 112 ||
                                    bean.getCase_state_id() == 113 ||
                                    bean.getCase_state_id() == 119)) {
                                newRows.add(bean);
                            }
                        }
                    }
                }
                itemRecyclerViewAdapter.addValues(newRows);
            }
        });
    }

}
