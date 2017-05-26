package com.soarsky.car.ui.carnews.mycolection;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.AutomotiveInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.ArticleCollect;
import com.soarsky.car.ui.carnews.ArticleDetailsModel;
import com.soarsky.car.ui.carnews.ArticleDetailsPresent;
import com.soarsky.car.ui.carnews.ArticleDetailsView;
import com.soarsky.car.uitl.ToastUtil;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/14<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为   在收藏列表点击跳转的文章详情<br>
 */

public class CollectArticleDetailsActivity extends BaseActivity<ArticleDetailsPresent,ArticleDetailsModel> implements ArticleDetailsView,View.OnClickListener  {
    /**
     * 文章详情标题
     */
    private TextView topicTv;
    /**
     * 收藏按钮
     */
    private LinearLayout ll_colect;
    /**
     * 返回按钮
     */
    private LinearLayout backLay;
    /**
     * 收藏key值
     */
    private final String COLLECT = "collect";
    /**
     * 展示文章详情的控件
     */
    private WebView webView;
    /**
     * 文章id
     */
    private int aid;


    @Override
    public int getLayoutId() {
        return R.layout.activity_article_details;
    }

    @Override
    public void initView() {

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.article_details));


        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        ll_colect = (LinearLayout) findViewById(R.id.ll_colect);
        ll_colect.setVisibility(View.GONE);

        webView = (WebView) findViewById(R.id.webView);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
        }
    }


    @Override
    public void showError(Throwable e) {
        ToastUtil.show(this,e.getMessage());
    }

    @Override
    public void showSuccess(ResponseDataBean<AutomotiveInfo> automotiveInfoResponseDataBean) {
        if (automotiveInfoResponseDataBean != null ){
            webView.loadDataWithBaseURL(null,automotiveInfoResponseDataBean.getData().getContent(),
                    "text/html", "utf-8", null);
        }else {

        }
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

    @Override
    public void querySuccess(List<ArticleCollect> articleCollect) {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aid = getIntent().getExtras().getInt("aid");
        mPresenter.getArticle(aid);
    }
}
