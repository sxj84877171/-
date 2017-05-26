package com.soarsky.installationmanual.ui.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.util.ToastUtil;


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
 * 该类为 主Activity<br>
 */

public class MainActivity extends BaseActivity<MainPresent,MainModel> implements MainView {
    private long exitTime = 0;
    /**
     *切换的viewgroup
     */
    private ViewPager mViewPager;
    /**
     * viewpager的适配
     */
    private SimpleFragmentPagerAdapter fragmentPagerAdapter;
    /**
     * 下列表展示
     */
    private TabLayout mTablayout;
    private ImageView tabImage;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setSwipeBackEnable(false);
        mTablayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        tabImage = (ImageView) findViewById(R.id.tabImage);
        fragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mTablayout.setupWithViewPager(mViewPager);

        mViewPager.setCurrentItem(1);

        for (int i = 0; i < mTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            tab.setCustomView(fragmentPagerAdapter.getTabView(i));
        }
        mTablayout.getTabAt(1).getCustomView().setVisibility(View.INVISIBLE);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==1){
                    tabImage.setVisibility(View.VISIBLE);
                    mTablayout.getTabAt(1).getCustomView().setVisibility(View.INVISIBLE);
                }else {
                    tabImage.setVisibility(View.INVISIBLE);
                    mTablayout.getTabAt(1).getCustomView().setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 连着两次按返回键退出应用
     */
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
        ToastUtil.show(this,"再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
