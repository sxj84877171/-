package com.soarsky.installationmanual.ui.main.fragment;


import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseFragment;
import com.soarsky.installationmanual.ui.main.fragment.task.SimpleFragmentPagerAdapter;
import com.soarsky.installationmanual.ui.main.fragment.task.detail.TaskDetailActivity;


/**
 * InstallationManual<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 任务页面<br>
 */

public class TaskFragment extends BaseFragment{
    private ViewPager mViewPager;

    private SimpleFragmentPagerAdapter fragmentPagerAdapter;

    private TabLayout mTablayout;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_task;
    }

    @Override
    public void initView(View view) {
        addView(R.layout.task_center_toolbar);
        addView(R.layout.task_right_toolbar);
        TextView taskDetail = (TextView) view.findViewById(R.id.taskDetail);
        taskDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TaskDetailActivity.class));
            }
        });
        mTablayout = (TabLayout) view.findViewById(R.id.tabLayoutFra);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPagerFra);
        fragmentPagerAdapter = new SimpleFragmentPagerAdapter(getActivity().getSupportFragmentManager(),getActivity());
        mViewPager.setAdapter(fragmentPagerAdapter);
        mTablayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            tab.setCustomView(fragmentPagerAdapter.getTabView(i));
        }
    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void showSuccess() {

    }
}
