package com.soarsky.car.ui.danger.responsibility;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ResponsibilityListDataBean;
import com.soarsky.car.ui.danger.responsibilitydetails.ResponsibilityDetailsActivity;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：责任认定界面
 * 该类为
 */



public class ResponsibilityListActivty extends BaseActivity<ResponsibilityListPresent,ResponsibilityListModel> implements ResponsibilityListView, View.OnClickListener {

    /**
     * 存放责任列表控件
     */
    private RecyclerView mRecyclerView;
    /**
     * 适配器
     */
    private RecyclerViewAdapter recyclerViewAdapter;
    /**
     * 标题
     */
    private TextView titleTv;
    /**
     * 返回
     */
	private ImageView backView;
    /**
     * 返回
     */
    private LinearLayout backLay;

    private App app;

    /**
     * 定义缓存责任列表集合
     */
    private List<ResponsibilityListDataBean> list=new ArrayList<>();

    private static final String TAG = "ResponsibilityListActivty";
    @Override
    public int getLayoutId() {
        return R.layout.activity_responsibilitylist3;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG,this);

        mRecyclerView= (RecyclerView) findViewById(R.id.responsibility_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        titleTv= (TextView) findViewById(R.id.topicTv);
        titleTv.setText(getString(R.string.zeren_rending));

        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        recyclerViewAdapter = new RecyclerViewAdapter(mContext,list,app.getCommonParam().getCarNum());
        mRecyclerView.setAdapter(recyclerViewAdapter);

        recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener(){

            @Override
            public void onClick(int position) {
                Intent i = new Intent();
                i.setClass(ResponsibilityListActivty.this, ResponsibilityDetailsActivity.class);
                i.putExtra("id",""+recyclerViewAdapter.getDateBean(position).getId());
                mPresenter.modifyUnreadFast(app.getCommonParam().getCarNum(),recyclerViewAdapter.getDateBean(position).getId());
                startActivity(i);
//                if( list.get(position).getFirstCar().equals(app.getCommonParam().getCarNum())){
//                    list.get(position).setFstatus("1");
//                }else{
//                    list.get(position).setSstatus("1");
//                }
//                recyclerViewAdapter.setData(list);

            }

            @Override
            public void onLongClick(int position) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getFastList(app.getCommonParam().getCarNum()==null?"":app.getCommonParam().getCarNum());
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void getFastListSuccess(ResponseDataBean<List<ResponsibilityListDataBean>> responsibilityListParam) {
        if(responsibilityListParam != null){
            if(Constants.REQUEST_SUCCESS.equals(responsibilityListParam.getStatus())){
                list=new ArrayList<>(responsibilityListParam.getData());
                recyclerViewAdapter.setData(list);
            }else {
                // 将后台的message转换成我们自己的信息显示给用户          王松清
                ToastUtil.show(this,R.string.get_data_fail);
            }
        }
    }





    @Override
    public void getFastListFail() {

        ToastUtil.show(this,R.string.throwable);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backView:
            case R.id.backLay:
                finish();
                break;
        }
    }
}
