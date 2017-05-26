package com.soarsky.car.ui.licenseinformation.cardetails;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.ui.licenseinformation.cardetails.fragment.faultrecord.FaultRecordFragment;
import com.soarsky.car.ui.licenseinformation.cardetails.fragment.carrecord.UseCarRecordFragment;

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
 * 该类为   汽车详情页面的ViewPager数据适配器类<br>
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context context;

    String[] mTitles = new String[]{"用车记录","故障记录"};

    public SimpleFragmentPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        if (position == 1) {
            return new FaultRecordFragment();
        }

        return new UseCarRecordFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_car_item, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(mTitles[position]);
        return view;
    }
}
