package com.soarsky.installationmanual.ui.main.fragment.task;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;


import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.ui.main.fragment.task.finished.FinishedFragment;
import com.soarsky.installationmanual.ui.main.fragment.task.nofinish.NoFinishFragment;

import java.util.List;

/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/11/16<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  任务页面FragmentPagerAdapter类<br>
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    /**
     * 标题
     */
    String[] mTitles = new String[]{"未完成","已完成"};

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new FinishedFragment();
        }

        return new NoFinishFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    public View getTabView(int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.task_tab_item, null);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        TextView num = (TextView) view.findViewById(R.id.num);
        if(position==1){
            num.setVisibility(View.GONE);
        }
        ////// TODO: 这里需要添加对num的setText方法
        tv.setText(mTitles[position]);
        return view;
    }
}
