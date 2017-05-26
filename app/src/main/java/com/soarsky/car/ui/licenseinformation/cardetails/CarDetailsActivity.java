package com.soarsky.car.ui.licenseinformation.cardetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.car.CarActivity;
import com.umeng.analytics.MobclickAgent;

import static com.soarsky.car.ConstantsUmeng.CAR_DETAILS;


/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/28<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  车况详情页面<br>
 */

public class CarDetailsActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_right;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回按钮
     */
    private LinearLayout backLay;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    /**
     * 数据适配器
     */
    private SimpleFragmentPagerAdapter pagerAdapter;
    private App app;

    private String carnum = "";

    private static final String TAG = "CarDetailsActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_details;
    }

    @Override
    public void initView() {
        app = (App) getApplication();
        app.addActivity(TAG,this);
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.car_details));

        tv_right = (TextView) findViewById(R.id.tv_right);
        tv_right.setText(getString(R.string.car_detail));
        tv_right.setOnClickListener(this);


        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(pagerAdapter);


        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(pagerAdapter.getTabView(i));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carnum = getIntent().getStringExtra("carnum");
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_right:
                MobclickAgent.onEvent(CarDetailsActivity.this,CAR_DETAILS);
                Intent intent = new Intent();
                intent.setClass(CarDetailsActivity.this, CarActivity.class);
                intent.putExtra("carnum",carnum);
                startActivity(intent);
                break;
            case R.id.backLay:
                finish();
                break;
        }
    }
}
