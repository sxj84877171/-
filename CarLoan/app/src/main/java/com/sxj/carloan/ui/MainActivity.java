package com.sxj.carloan.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;

public class MainActivity extends BaseActivity {


    private ViewPager mViewPager;
    private TabLayout tabLayout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        mViewPager.setAdapter(new LoanFragmentPagerAdapter(getSupportFragmentManager()));
    }


}
