package com.soarsky.car.ui.roadside;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.roadside.list.RoadSideListActivity;
import com.soarsky.car.ui.roadside.rescue.RoadSideRescueActivity;
import com.umeng.analytics.MobclickAgent;

import static com.soarsky.car.ConstantsUmeng.ROADSIDE_APPLY;

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
 * 程序功能：道路救援<br>
 * 该类为 RoadSideActivity<br>
 */


public class RoadSideActivity extends BaseActivity<RoadSidePresent,RoadSideModel> implements RoadSideView,View.OnClickListener{

    /**
     * 救援申请
     */
    private TextView roadSideApplyTv;

    /**
     * 申请列表
     */
    private TextView roadSideApplyListTv;
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;


    @Override
    public int getLayoutId() {
        return R.layout.activity_roadside;
    }

    @Override
    public void initView() {

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.roadsidetopic));

        roadSideApplyTv = (TextView) findViewById(R.id.roadSideApplyTv);
        roadSideApplyTv.setOnClickListener(this);

        roadSideApplyListTv = (TextView) findViewById(R.id.roadSideApplyListTv);
        roadSideApplyListTv.setOnClickListener(this);

    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 跳转救援申请
     */
    @Override
    public void gotoRoadSideApply() {

        Intent intent = new Intent();
        intent.setClass(this, RoadSideRescueActivity.class);
        startActivity(intent);

    }

    /**
     * 跳转申请列表
     */
    @Override
    public void gotoRoadSideApplyList() {

        Intent intent = new Intent();
        intent.setClass(this, RoadSideListActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.roadSideApplyTv:
                MobclickAgent.onEvent(RoadSideActivity.this,ROADSIDE_APPLY);
                mPresenter.gotoRoadSideApply();
                break;
            case R.id.roadSideApplyListTv:
                mPresenter.gotoRoadSideApplyList();
                break;
        }
    }
}
