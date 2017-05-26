package com.soarsky.car.ui.illegal;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.ui.illegal.fragment.driver.IllegalDriverFragment;
import com.soarsky.car.ui.illegal.fragment.personal.IllegalPersonalFragment;

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
 * 程序功能：fragment 切换的adapter<br>
 * 该类为 IllegalManageFragmentPagerAdapter<br>
 */


public class IllegalManageFragmentPagerAdapter extends FragmentPagerAdapter {

    /**
     * 上下文本
     */
    private Context context;
    /**
     * 标题
     */
    public  String[] mTitles ;

    /**
     * 其构造函数
     * @param fm 管理器
     * @param context 文本
     * @param mTitles 标题
     */
    public IllegalManageFragmentPagerAdapter(FragmentManager fm,Context context,String[] mTitles) {
        super(fm);
        this.context = context;
        this.mTitles = mTitles;

    }

    /**
     * item
     * @param position 第几个
     * @return fragment
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return  new IllegalDriverFragment();
        }else {
            return new IllegalPersonalFragment();
        }
    }

    /**
     * tab的个数
     * @return
     */
    @Override
    public int getCount() {
        return mTitles.length;
    }

    /**
     * 获取tab
     * @param position 第几个
     * @return view
     */
    public View getTabView(int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.illegal_tab_item, null);
        TextView tv = (TextView) view.findViewById(R.id.illeagltextView);
        tv.setText(mTitles[position]);
        tv.setTextSize(14);
        return view;
    }

}
