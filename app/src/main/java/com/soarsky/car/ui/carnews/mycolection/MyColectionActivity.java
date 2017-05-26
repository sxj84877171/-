package com.soarsky.car.ui.carnews.mycolection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.AutomotiveInfo;
import com.soarsky.car.data.local.db.bean.ArticleCollect;
import com.soarsky.car.ui.carnews.ArticleDetailsActivity;
import com.soarsky.car.ui.carnews.ArticleDetailsModel;
import com.soarsky.car.ui.carnews.ArticleDetailsPresent;
import com.soarsky.car.ui.carnews.ArticleDetailsView;

import java.util.ArrayList;
import java.util.List;

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
 * 该类为  汽车资讯--我的收藏页面<br>
 */

public class MyColectionActivity extends BaseActivity<ArticleDetailsPresent,ArticleDetailsModel> implements ArticleDetailsView, View.OnClickListener,SlideView.OnSlideListener {
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回按钮
     */
    private LinearLayout illegalBackLay;
    /**
     * 收藏页文章列表
     */
    private ListView listView;
    /**
     * 收藏记录
     */
    private List<CollectionParam> mList = new ArrayList<CollectionParam>();
    /**
     * 收藏列表数据适配器类
     */
    private MyColectionAdapter myColectionAdapter;
    /**
     * 侧滑删除收藏文章
     */
    private SlideView mLastSlideViewWithStatusOn;

    private List<AutomotiveInfo> list = new ArrayList<>();
    private App app;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_colection;
    }

    @Override
    public void initView() {

        app = (App) getApplication();

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.my_collect));

        illegalBackLay = (LinearLayout) findViewById(R.id.illegalBackLay);
        illegalBackLay.setOnClickListener(this);
        listView = (ListViewCompat) findViewById(R.id.lv);

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
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();

        /*IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action.refreshList");
        registerReceiver(mRefreshBroadcastReceiver, intentFilter);*/
        mPresenter.qurey();
        if (myColectionAdapter != null){
            myColectionAdapter.notifyDataSetChanged();
        }
   }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void showSuccess(AutomotiveInfo p) {

    }

    @Override
    public void insertFail() {

    }

    @Override
    public void insertSuccess() {

    }

    @Override
    public void deleteSuccess() {

    }

    /**
     * 查询收藏记录成功
     */
    @Override
    public void querySuccess(List<ArticleCollect> articleCollect) {
        mList.clear();
        for (ArticleCollect art : articleCollect){
            if (art.getAppUser().equals(app.getCommonParam().getUserName())){
            CollectionParam info = new CollectionParam();
            info.setId(art.getId());
            info.setTitle(art.getTitle());
            info.setCtime(art.getDate());
            info.setRemark(art.getRemark());
            info.setContent(art.getContent());
            info.setRead(true);
            mList.add(info);
            }

            AutomotiveInfo motiveInfo = new AutomotiveInfo();
            motiveInfo.setId(art.getId());
            motiveInfo.setTitle(art.getTitle());
            motiveInfo.setCtime(art.getDate());
            motiveInfo.setRemark(art.getRemark());
            motiveInfo.setContent(art.getContent());
            motiveInfo.setRead(art.isRead());
            motiveInfo.setCollect(art.isCollect());
            motiveInfo.setUtime(art.getUtime());
            motiveInfo.setKeyword(art.getKeyWord());
            motiveInfo.setType(art.getType());
            list.add(motiveInfo);
        }


       myColectionAdapter = new MyColectionAdapter(MyColectionActivity.this,this);
       myColectionAdapter.setData(mList);
       listView.setAdapter(myColectionAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent();
                intent.setClass(mContext, ArticleDetailsActivity.class);
                intent.putExtra("info",list.get(i));
                intent.putExtra("aid",mList.get(i).getId());
                startActivity(intent);

            }
        });


    }

    @Override
    public void onSlide(View view, int status) {
        if (mLastSlideViewWithStatusOn != null && mLastSlideViewWithStatusOn != view) {
            mLastSlideViewWithStatusOn.shrink();
        }
        if (status == SLIDE_STATUS_ON) {
            mLastSlideViewWithStatusOn = (SlideView) view;
        }
    }

    /*private BroadcastReceiver mRefreshBroadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("action.refreshList"))
            {
                myColectionAdapter.notifyDataSetChanged();
            }
        }
    };*/

   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mRefreshBroadcastReceiver);
    }*/
}
