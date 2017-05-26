package com.soarsky.installationmanual.ui.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.ui.main.fragment.homefragment.HomeFragment;
import com.soarsky.installationmanual.ui.main.fragment.MineFragment;
import com.soarsky.installationmanual.ui.main.fragment.TaskFragment;

import java.util.ArrayList;
/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 首页FragmentPagerAdapter<br>
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    String[] mTitles ;

    private ArrayList<Fragment> fragmentArrayList;

//    int[] imageResId = {R.mipmap.home_u, R.mipmap.car_u,
//            R.mipmap.preserve_u, R.mipmap.personal_u};

    int[] imageResId = {R.drawable.task_selector_bg, R.drawable.home_selector_bg,
            R.drawable.mine_selector_bg};
 
    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
//        fragmentArrayList=al;
        mTitles = context.getResources().getStringArray(R.array.home_title);

    }


    @Override
    public Fragment getItem(int position) {
//        fragmentArrayList.get(position);
        if (position == 1) {
            return new HomeFragment();
        } else if (position == 2) {
            return new MineFragment();
        }
        return new TaskFragment();
    }
 
    @Override
    public int getCount() {
        return mTitles.length;
    }
 

    public View getTabView(int position){

        View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        tv.setText(mTitles[position]);
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(imageResId[position]);
        return view;
    }



}