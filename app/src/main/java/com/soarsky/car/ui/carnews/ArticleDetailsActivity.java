package com.soarsky.car.ui.carnews;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.AutomotiveInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.ArticleCollect;
import com.soarsky.car.uitl.ToastUtil;

import java.util.List;


/**
 * Andriod_Car_App <br>
 * 作者： 王松清 <br>
 * 时间： 2017/1/10 <br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  文章详情页面<br>
 */

public class ArticleDetailsActivity extends BaseActivity<ArticleDetailsPresent,ArticleDetailsModel> implements ArticleDetailsView,  View.OnClickListener {
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
     * 展示文章详情的控件
     */
    private WebView webView;
    /**
     * 文章id
     */
    private int aid;
    /**
     * 从上一个页面传递过来的汽车资讯
     */
    private AutomotiveInfo info;
    /**
     * 是否收藏过，默认是已收藏
     */
    private boolean isCollect = true;
    /**
     * 收藏图标
     */
    private ImageView iv_start;
    /**
     * 收藏图标切换的一个标识
     */
    private boolean b;
    private App app;
    private String cUser;


    @Override
    public int getLayoutId() {
        return R.layout.activity_article_details;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.article_details));

        iv_start = (ImageView) findViewById(R.id.iv_start);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        ll_colect = (LinearLayout) findViewById(R.id.ll_colect);
        ll_colect.setOnClickListener(this);

        webView = (WebView) findViewById(R.id.webView);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_colect:
                if (isCollect){
                    ArticleCollect collect = new ArticleCollect();
                    collect.setId(info.getId());
                    collect.setTitle(info.getTitle());
                    collect.setDate(info.getCtime());
                    collect.setUtime(info.getUtime());
                    collect.setRemark(info.getRemark());
                    collect.setCollect(true);
                    collect.setContent(info.getContent()==null?"":info.getContent());
                    collect.setType(info.getType());
                    collect.setRead(info.isRead());
                    collect.setKeyWord(info.getKeyword());
                    collect.setAppUser(app.getCommonParam().getUserName());
                    mPresenter.insertData(collect);

                }else {
                    if (cUser.equals(app.getCommonParam().getUserName())){
                    mPresenter.delete( info.getId());
                    }
                }


                break;
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
        ToastUtil.show(this,getString(R.string.collect_fail));
    }

    @Override
    public void insertSuccess() {
        ToastUtil.show(this,getString(R.string.collect_success));
        iv_start.setImageResource(R.mipmap.start_nei);
        isCollect = false;

        }


    @Override
    public void deleteSuccess() {
        ToastUtil.show(this,getString(R.string.cancel_to_collect));
        iv_start.setImageResource(R.mipmap.start_wai);
        isCollect = true;

    }

    @Override
    public void querySuccess(List<ArticleCollect> articleCollect) {
        for (ArticleCollect art : articleCollect){
            if (art.getAppUser().equals(app.getCommonParam().getUserName()) && art.getId() == aid){
                cUser = art.getAppUser();
                b = art.getIsCollect();
            }
        }


        if (b){
            iv_start.setImageResource(R.mipmap.start_nei);
            isCollect = false;
        }else {
            iv_start.setImageResource(R.mipmap.start_wai);
            isCollect = true;
        }

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter.qurey();
        aid = getIntent().getExtras().getInt("aid");
        info = (AutomotiveInfo) getIntent().getSerializableExtra("info");
        mPresenter.getArticle(aid);

    }


}
