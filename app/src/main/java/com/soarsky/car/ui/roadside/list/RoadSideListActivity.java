package com.soarsky.car.ui.roadside.list;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideListDataBean;
import com.soarsky.car.ui.roadside.finish.RoadSideFinishActivity;
import com.soarsky.car.ui.roadside.list.detail.RoadSideListDetailActivity;
import com.soarsky.car.ui.roadside.list.order.RoadSideListOrderActivity;
import com.soarsky.car.uitl.ToastUtil;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/19<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：救援申请列表<br>
 * 该类为 RoadSideListActivity<br>
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
    /**
     * application
     */
    private App app;
    /**
     * 该类的key
     */
    private static final String TAG ="RoadSideListActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_road_side_list3;
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
                RoadSideListDataBean bean = (RoadSideListDataBean)adapter.getItem(i);
                if(Constants.ROADSIDE_RECORED_STATUS.equals(bean.getStatus())) {
//                   进行中
                    Intent intent = new Intent();
                    intent.setClass(RoadSideListActivity.this, RoadSideListOrderActivity.class);
                    intent.putExtra("id", "" + bean.getId());
                    RoadSideListActivity.this.startActivity(intent);
//                    finish();
                }else if(Constants.ROADSIDE_FINISH_STATUS.equals(bean.getStatus())){
                    Intent intent = new Intent();
                    intent.setClass(RoadSideListActivity.this, RoadSideFinishActivity.class);
                    intent.putExtra("id", "" + bean.getId());
                    RoadSideListActivity.this.startActivity(intent);
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
     * @param roadSideListParam  故障救援列表
     */
    @Override
    public void getRescueListSuccess(ResponseDataBean<List<RoadSideListDataBean>> roadSideListParam) {

        if(roadSideListParam != null){
            if(Constants.REQUEST_SUCCESS.equals(roadSideListParam.getStatus())){

                adapter = new RoadSideListAdapter(this,roadSideListParam.getData());
                roadSideListView.setAdapter(adapter);

            }else {
                //将后台的message转换成我们自己的内容显示给用户               王松清
                ToastUtil.show(this,R.string.get_rescueList_fail);
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
