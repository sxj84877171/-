package com.soarsky.car.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.ui.carnews.carnewssearch.SearchActivity;
import com.soarsky.car.ui.carnews.carnewsfragment.CarcareActivity;
import com.soarsky.car.ui.carnews.carnewsfragment.TrafficActivity;
import com.soarsky.car.ui.carnews.carnewsfragment.TrafficRuleActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/5/10<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为主页页面--汽车资讯<br>
 */

public class NewInformationFragment extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener {
    /**
     * 交通安全
     */
    private RelativeLayout rl_traffic;

    /**
     * 车辆保养
     */
    private RelativeLayout rl_carCare;

    /**
     * 交通法规
     */
    private RelativeLayout rl_trafficRule;

    /**
     * 搜索
     */
    private ImageView search;

    private List<ImageView> imageViewList; // Viewpager的数据
    private ViewPager mViewPager;
    private LinearLayout llPointGroup;

    private int previousPosition = 0; // 前一个被选中的position
    private boolean isStop = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carnws3,container,false);

        rl_traffic = (RelativeLayout) view.findViewById(R.id.rl_traffic);
        rl_traffic.setOnClickListener(this);

        rl_carCare = (RelativeLayout) view.findViewById(R.id.rl_carCare);
        rl_carCare.setOnClickListener(this);

        rl_trafficRule = (RelativeLayout) view.findViewById(R.id.rl_trafficRule);
        rl_trafficRule.setOnClickListener(this);

        search = (ImageView) view.findViewById(R.id.search);
        search.setOnClickListener(this);

        initView(view);

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 每隔5秒钟, 来切换一张图片
                while(!isStop) {

                    // 运行在主线程中的任务
                    getActivity().runOnUiThread(new Runnable() {
                        public void run() {
                            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                        };
                    });
                    SystemClock.sleep(3000);
                }
            }
        }).start();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_traffic:
                startActivity(new Intent(getActivity(), TrafficActivity.class));
                break;
            case R.id.rl_carCare:
                startActivity(new Intent(getActivity(), CarcareActivity.class));
                break;
            case R.id.rl_trafficRule:
                startActivity(new Intent(getActivity(), TrafficRuleActivity.class));
                break;
            case R.id.search:
                Intent i = new Intent();
                i.setClass(getActivity(), SearchActivity.class);
                i.putExtra("type", Constants.TYPE_TRAFFIC_SAFE);
                startActivity(i);
                break;
        }
    }

    private void initView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        llPointGroup = (LinearLayout) view.findViewById(R.id.ll_point_group);

        initData();

        mViewPager.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE/2) % imageViewList.size());

        ViewPagerAdapter mAdapter = new ViewPagerAdapter(imageViewList,mViewPager);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(this);

        // 设置默认选中的点和图片对应的描述信息.
        previousPosition = 0;
        llPointGroup.getChildAt(previousPosition).setEnabled(true);

        // 把ViewPager设置为默认选中Integer.MAX_VALUE / 2;

        int m = (Integer.MAX_VALUE / 2) % imageViewList.size();
        int currentPosition = Integer.MAX_VALUE / 2 - m;
        mViewPager.setCurrentItem(currentPosition);
    }


    private void initData() {

        int[] imageResIDs = {
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
        };

        imageViewList = new ArrayList<ImageView>();

        ImageView iv;
        View v;
        LinearLayout.LayoutParams params;
        for (int i = 0; i < imageResIDs.length; i++) {
            iv = new ImageView(getActivity());
            iv.setBackgroundResource(imageResIDs[i]);
            imageViewList.add(iv);

            // 每循环一次需要向LinearLayout中添加一个点的view对象
            v = new View(getActivity());
            v.setBackgroundResource(R.drawable.point_bg3);
            params = new LinearLayout.LayoutParams(30,30);
            if(i != 0) {
                // 当前不是第一个点, 需要设置左边距
                params.leftMargin = 10;
            }
            v.setLayoutParams(params);
            v.setEnabled(false);
            llPointGroup.addView(v);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    /**
     * 当ViewPager页面被选中时, 触发此方法.
     *
     * @param position 当前被选中的页面的索引
     */
    @Override
    public void onPageSelected(int position) {
        //System.out.println("当前被选中的页面是: " + position);

        int newPosition = position % imageViewList.size();

        // 把当前选中的点给切换了, 还有描述信息也切换
        llPointGroup.getChildAt(previousPosition).setEnabled(false);
        llPointGroup.getChildAt(newPosition).setEnabled(true);

        // 把当前的索引赋值给前一个索引变量, 方便下一次再切换.
        previousPosition = newPosition;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isStop = true;
    }

}
