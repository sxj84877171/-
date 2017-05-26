package com.soarsky.car.ui.carnews;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.AutomotiveInfo;
import com.soarsky.car.ui.carnews.mycolection.MyColectionActivity;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import static com.soarsky.car.ConstantsUmeng.COLLECTION;

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
 * 程序功能： 汽车资讯页面<br>
 * 该类为<br>
 */

public class CarNewsActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 返回按钮
     */
    private LinearLayout illegalBackLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 我的收藏
     */
    private TextView carNewsCarTv;
    /**
     * 未读消息条数
     */
    private TextView numBar;
    /**
     * Fragment的容器ViewPager，继承自ViewGroup
     */
    private ViewPager viewPager;
    /**
     * 与ViewPager配合使用，完成TabPageIndicator的效果
     */
    private TabLayout tabLayout;
    /**
     * 碎片数据适配器
     */
    private CarNewsFragmentPagerAdapter adapter;

    private ImageView latestReadView;

    /**
     * 最后阅读标识
     */
    private final String READ = "lastRead";

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_news;
    }

    @Override
    public void initView() {
        illegalBackLay = (LinearLayout) findViewById(R.id.illegalBackLay);
        illegalBackLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.car_news));

        carNewsCarTv = (TextView) findViewById(R.id.carNewsCarTv);
        carNewsCarTv.setText(getString(R.string.my_collect));
        carNewsCarTv.setOnClickListener(this);

        latestReadView = (ImageView) findViewById(R.id.latestReadView);
        latestReadView.setOnClickListener(this);


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new CarNewsFragmentPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            Log.d("TAG","size=="+tabLayout.getTabCount()+"i=="+i);
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));

        }



    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.illegalBackLay:
                finish();
                break;
            case R.id.carNewsCarTv:
                MobclickAgent.onEvent(CarNewsActivity.this,COLLECTION);
                startActivity(new Intent(CarNewsActivity.this, MyColectionActivity.class));
                break;
            case R.id.latestReadView:
                String read = SpUtil.get(READ);
                Gson gson = new Gson();
                AutomotiveInfo info = null;
                if(SpUtil.get("info")!= null && !(("").equals(SpUtil.get("info"))))
                    info = gson.fromJson(SpUtil.get("info"),AutomotiveInfo.class);
                if (read.equals("")){
                    ToastUtil.show(mContext,getString(R.string.no_latest));
                }else {

                    Intent intent = new Intent();
                    intent.setClass(mContext, ArticleDetailsActivity.class);
                    intent.putExtra("info", info);
                    intent.putExtra("aid",Integer.parseInt(read));
                    startActivity(intent);
                }
                break;
        }
    }
}
