package com.sxj.carloan.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * CarLoan
 * 作者： Elvis
 * 时间： 2017/6/26
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class LoanFragmentPagerAdapter extends FragmentPagerAdapter {
    public LoanFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new ItemFragment();
    }

    @Override
    public int getCount() {
        return 1;
    }
}
