package com.soarsky.car.base;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.ui.login.LoginActivity;
import com.soarsky.car.uitl.TUtil;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        isNight = SpUtil.isNight();
//        setTheme(isNight ? R.style.AppThemeNight : R.style.AppThemeDay);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*this.setContentView(this.getLayoutId());*/
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
//        if (isNight != SpUtil.isNight()) reload();
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
        progressDialog.setMessage("正在加载...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void stopProgess() {
        progressDialog.dismiss();
    }



}
