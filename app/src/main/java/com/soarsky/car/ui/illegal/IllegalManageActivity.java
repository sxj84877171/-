package com.soarsky.car.ui.illegal;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.illegal.query.IllegalQueryActivity;
import com.umeng.analytics.MobclickAgent;

import static com.soarsky.car.ConstantsUmeng.ILLEGAL_CHANGE_CAR;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/28<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：违章管理<br>
 * 该类为 IllegalManageActivity<br>
 */


public class IllegalManageActivity extends BaseActivity<IllegalManagePresent,IllegalManageModel> implements IllegalManageView,View.OnClickListener{
    /**
     * viwePager
     */
    private ViewPager mViewPager;
    /**
     * viewPager适配
     */
    private IllegalManageFragmentPagerAdapter fragmentPagerAdapter;
    /**
     * tab
     */
    private TabLayout mTablayout;
    /**
     * 返回
     */
    private LinearLayout illegalBackLay;
    /**
     * 切换车辆
     */
    private TextView illegalChangeCarTv;
    /**
     * application
     */
    private App app;
    /**
     * 该类的key
     */
    private final static  String TAG = "IllegalManageActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_illegal_manage;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG,this);

        illegalBackLay = (LinearLayout) findViewById(R.id.illegalBackLay);
        illegalBackLay.setOnClickListener(this);

        illegalChangeCarTv = (TextView) findViewById(R.id.illegalChangeCarTv);
        illegalChangeCarTv.setOnClickListener(this);

        mTablayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        String[] mTitles = new String[2];

        mTitles[0] = app.getCommonParam().getRealName()+"("+app.getCommonParam().getIdNo().substring(0,7)+"...)";
        mTitles[1] = app.getCommonParam().getCarNum();

        fragmentPagerAdapter = new IllegalManageFragmentPagerAdapter(getSupportFragmentManager(),this,mTitles);
        mViewPager.setAdapter(fragmentPagerAdapter);


        mTablayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            tab.setCustomView(fragmentPagerAdapter.getTabView(i));
        }

    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.illegalBackLay:
                finish();
                break;
            case R.id.illegalChangeCarTv:
                MobclickAgent.onEvent(IllegalManageActivity.this,ILLEGAL_CHANGE_CAR);
                Intent i = new Intent();
                i.setClass(this,IllegalQueryActivity.class);
                startActivity(i);
                break;
        }
    }
}
