package com.soarsky.car.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soarsky.car.R;
import com.soarsky.car.uitl.StatusBarUtil;
import com.soarsky.car.uitl.TUtil;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

/**
 * 车主APP
 * 作者： 孙向锦
 * 时间： 2016/12/6
 * 公司：长沙硕铠电子科技有限公司
 * Email：sunxiangjin@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * Activity基类，负责整个MVP架构逻辑
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity implements BaseView{
    public boolean isNight;
    public T mPresenter;
    public E mModel;
    public Context mContext;
    private Toolbar toolbar;

    private ProgressDialog progressDialog;
    /**
     * 请求数据数据成功标记
     */
    public static final String SUCCESS_FLAG = "200" ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtil.setColorNoTranslucent(this, Color.parseColor("#f8c120"));
        PushAgent.getInstance(mContext).onAppStart();

        setContentView(R.layout.base_layout);
        initBaseView();
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        this.initView();
        if (this instanceof BaseView){
            if(mPresenter != null){
                mPresenter.context = this;
                mPresenter.setVM(this, mModel);
            }
        }
    }

    private void initBaseView() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(getHeaderTitle());
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        initToolbar(toolbar);
        View.inflate(this, getLayoutId(), linearLayout);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    public abstract int getLayoutId();

    public abstract void initView();

    public void showSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
    }

    public void showFail() {
        Toast.makeText(this, "fail", Toast.LENGTH_LONG).show();
    }

    protected abstract String getHeaderTitle();
    protected void initToolbar(Toolbar toolbar){
        toolbar.setVisibility(View.GONE);
    }
    public void addView(int layoutId){
        View.inflate(this,layoutId,toolbar);
    }

    @Override
    public void showProgess() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage(getString(R.string.dialog_loading));
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void stopProgess() {
        if(progressDialog!=null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
