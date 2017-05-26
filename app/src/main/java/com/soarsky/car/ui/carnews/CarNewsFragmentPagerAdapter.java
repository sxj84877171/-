package com.soarsky.car.ui.carnews;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.ui.carnews.carnewsfragment.CarCareFragment;
import com.soarsky.car.ui.carnews.carnewsfragment.TrafficRuleFragment;
import com.soarsky.car.ui.carnews.carnewsfragment.TrafficSafeFragment;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 汽车资讯的ViewPager数据适配器类<br>
 */

public class CarNewsFragmentPagerAdapter extends FragmentPagerAdapter {
    /**
     * 上下文
     */
    private Context context;

    String[] mTitles = new String[]{"车辆保养","交通安全","交通法规"};

    public CarNewsFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {
        Log.d("TAG","position=="+position);
        if (position == 1) {
            return new TrafficSafeFragment();
        }else if (position == 2){
            return new TrafficRuleFragment();
        }else {
            return new CarCareFragment();
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    /**
     * 各类资讯头标题的布局View
     * @param position 位置
     * @return 返回头布局的view对象
     */
    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_car_item, null);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText(mTitles[position]);

        return view;
    }

}
