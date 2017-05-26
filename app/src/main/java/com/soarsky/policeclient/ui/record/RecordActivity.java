package com.soarsky.policeclient.ui.record;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.bean.RecordViolationDataBean;
import com.soarsky.policeclient.uitl.LogUtil;

import java.util.List;

/*import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;*/

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  历史记录<br>
 */

public class RecordActivity extends BaseActivity<RecordPresent, RecordModel> implements RecordView, View.OnClickListener {
    /**
     * ViewPager
     */
    private ViewPager mViewPager;
    /**
     * SimpleFragmentPagerAdapter
     */
    private SimpleFragmentPagerAdapter fragmentPagerAdapter;
    /**
     * TabLayout
     */
    private TabLayout mTablayout;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;
    private ImageView history_back;

    @Override
    public int getLayoutId() {
        return R.layout.activity_history;
    }

    @Override
    public void initView() {
        history_back = (ImageView) findViewById(R.id.history_back);
        history_back.setOnClickListener(this);
        mTablayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        fragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(),this);
        mViewPager.setAdapter(fragmentPagerAdapter);

        mTablayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTablayout.getTabCount(); i++) {
            TabLayout.Tab tab = mTablayout.getTabAt(i);
            tab.setCustomView(fragmentPagerAdapter.getTabView(i));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void showSuccess(List<RecordViolationDataBean> recordViolationParam ) {
        if(recordViolationParam != null) {
           RecordViolationDataBean param = (RecordViolationDataBean) recordViolationParam;
            LogUtil.d("TAG","=="+ param.toJson());
        }

    }

    @Override
    public void showFail() {

    }

    @Override
    public void showProgess() {

    }

    @Override
    public void stopProgess() {

    }




    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.history_back:
                finish();
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    /*public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Record Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }*/

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       /* client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());*/
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        /*AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();*/
    }



}
