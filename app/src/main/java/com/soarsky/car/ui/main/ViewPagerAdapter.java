package com.soarsky.car.ui.main;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2017/5/18
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class ViewPagerAdapter extends PagerAdapter {
    private List<ImageView> imageViewList; // Viewpager的数据
    private ViewPager mViewPager;

    public  ViewPagerAdapter(List<ImageView> imageViewList,ViewPager mViewPager){
        this.imageViewList = imageViewList;
        this.mViewPager = mViewPager;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 初始化一个条目
     * position 就是当前需要加载条目的索引
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 把position对应位置的ImageView添加到ViewPager中
        ImageView iv = imageViewList.get(position % imageViewList.size());
        ViewGroup parent = (ViewGroup) iv.getParent();
        if (null != parent){
            parent.removeAllViews();
        }
        mViewPager.addView(iv);
        // 把当前添加ImageView返回回去.
        return iv;
    }

    /**
     * 销毁一个条目
     * position 就是当前需要被销毁的条目的索引
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // 把ImageView从ViewPager中移除掉
        mViewPager.removeView(imageViewList.get(position % imageViewList.size()));
    }

}
