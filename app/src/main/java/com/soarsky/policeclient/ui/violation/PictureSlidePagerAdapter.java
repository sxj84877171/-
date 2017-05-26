package com.soarsky.policeclient.ui.violation;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  viewpager适配器类<br>
 */

public class PictureSlidePagerAdapter extends PagerAdapter {
    /**
     * ImageView列表
     */
    private List<ImageView> imageViewList;
    private int cPosition;


    public PictureSlidePagerAdapter(){
        /*this.imageViewList = imageViewList;*/

    }

    public void setImageViewList(List<ImageView> imageViewList){
        this.imageViewList = imageViewList;
    }
    /**
     * 返回的int的值, 会作为ViewPager的总长度来使用.
     */
    @Override
    public int getCount() {
        return imageViewList.size();
    }

    /**
     * 判断是否使用缓存, 如果返回的是true, 使用缓存. 不去调用instantiateItem方法创建一个新的对象
     */
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
        ImageView iv = imageViewList.get(position);
//        ((ViewGroup)(iv.getParent())).removeView(iv);
        container.addView(iv);
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
        if(position < imageViewList.size()){
            container.removeView(imageViewList.get(position));
        }
    }
    public void setCurrentItem(int cPosition){
        this.cPosition = cPosition;
    }
}
