package com.soarsky.car.ui.roadside.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.roadside.list.detail.RoadSideListDetailActivity;
import com.soarsky.car.ui.roadside.list.order.RoadSideListOrderActivity;
import com.soarsky.car.uitl.ToastUtil;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/19
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：救援申请列表
 * 该类为
 */


public class RoadSideListActivity extends BaseActivity<RoadSideListPresent,RoadSideListModel> implements RoadSideListView,View.OnClickListener{


    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 救援列表
     */
    private ListView roadSideListView;
    /**
     * 救援列表适配
     */
    private RoadSideListAdapter adapter;

    private App app;

    private static final String TAG ="RoadSideListActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_road_side_list;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.roadsidelist));

        roadSideListView = (ListView) findViewById(R.id.roadSideListView);

        roadSideListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                RoadSideListParam.DataBean bean = (RoadSideListParam.DataBean)adapter.getItem(i);
                if("0".equals(bean.getStatus())) {
//                   进行中
                    Intent intent = new Intent();
                    intent.setClass(RoadSideListActivity.this, RoadSideListOrderActivity.class);
                    intent.putExtra("id", "" + bean.getId());
                    RoadSideListActivity.this.startActivity(intent);
//                    finish();
                }else {
                    Intent intent = new Intent();
                    intent.setClass(RoadSideListActivity.this, RoadSideListDetailActivity.class);
                    intent.putExtra("id", "" + bean.getId());
                    intent.putExtra("status",""+bean.getStatus());
                    RoadSideListActivity.this.startActivity(intent);
                    finish();
                }
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.getRescueList(app.getCommonParam().getCarNum()==null?"":app.getCommonParam().getCarNum());
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }
    /**
     * 获取故障救援列表成功
     */
    @Override
    public void getRescueListSuccess(RoadSideListParam roadSideListParam) {

        if(roadSideListParam != null){
            if("0".equals(roadSideListParam.getStatus())){

                adapter = new RoadSideListAdapter(this,roadSideListParam.getData());
                roadSideListView.setAdapter(adapter);

            }else {
                ToastUtil.show(this,roadSideListParam.getMessage());
            }
        }
    }
    /**
     * 获取故障救援列表失败
     */
    @Override
    public void getRescueListFail() {

        ToastUtil.show(this,R.string.throwable);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
        }
    }
}
