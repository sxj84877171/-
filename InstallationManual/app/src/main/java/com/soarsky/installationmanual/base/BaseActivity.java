package com.soarsky.installationmanual.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.util.TUtil;
import com.soarsky.installationmanual.util.ToastUtil;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * 安装手册<br>
 * 作者： 孙向锦<br>
 * 时间： 2017/02/09<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * Activity基类，负责整个MVP架构逻辑<br>
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends SwipeBackActivity implements BaseView{
    /**
     * 是否是夜间模式
     */
    public boolean isNight;
    /**
     * MVP模式的P类
     */
    public T mPresenter;
    /**
     * MVP模式的M类
     */
    public E mModel;
    /**
     * 上下文
     */
    public Context mContext;
    /**
     * Toolbar
     */
    private Toolbar toolbar;
    /**
     *  对话框字段
     */
    private ProgressDialog progressDialog;
    /**
     * 请求数据数据成功标记
     */
    public static final String SUCCESS_FLAG = "0" ;
    /**
     * activity类的初始化方法
     * @param savedInstanceState 如果activity被重新加载会得到从之前的activity中保存的Bundle数据
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        isNight = SpUtil.isNight();
//        setTheme(isNight ? R.style.AppThemeNight : R.style.AppThemeDay);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        /*this.setContentView(this.getLayoutId());*/
        setContentView(R.layout.base_layout);
        initBaseView();
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        this.initView();
        initToolbar(toolbar);
        if (this instanceof BaseView){
            if(mPresenter != null){
                mPresenter.context = this;
                mPresenter.setVM(this, mModel);
            }
        }
    }

    /**
     * 初始化视图控件
     */
    private void initBaseView() {
       LinearLayout linearLayout = (LinearLayout) findViewById(R.id.root);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getHeaderTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        View.inflate(this, getLayoutId(), linearLayout);

    }


    /**
     * activity销毁时调用的方法
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
    }
    /**
     * activity唤醒时调用的方法
     */
    @Override
    protected void onResume() {
        super.onResume();
//        if (isNight != SpUtil.isNight()) reload();
    }
    /**
     * 重新加载当前界面
     */
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
    /**
     * 配置试图资源文件
     * @return
     */
    public abstract int getLayoutId();
    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 给界面头部添加标题
     * @return
     */
    protected abstract String getHeaderTitle();

    /**
     * 初始化Toolbar控件
     * @param toolbar
     */
    protected void initToolbar(Toolbar toolbar){
        //toolbar.setVisibility(View.GONE);
    }

    /**
     * 添加xml布局到界面中
     * @param layoutId
     */
    public void addView(int layoutId){
        View.inflate(this,layoutId,toolbar);
    }


    /**
     * 弹出进度对话框
     */
    @Override
    public void showProgess() {
        if(progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("正在加载...");
        }
        progressDialog.show();
        progressDialog.setOnKeyListener(onKeyListener);
    }

    /**
     * 进度对话框按键点击事件
     */
    private DialogInterface.OnKeyListener onKeyListener = new DialogInterface.OnKeyListener() {
        @Override
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                dismissDialog();
            }
            return false;
        }
    };

    /**
     * 销毁对话框
     */
    public void dismissDialog() {
        if (isFinishing()) {
            return;
        }
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * 返回键点击回调方法
     */
    @Override
    public void onBackPressed() {
        if (progressDialog != null && progressDialog.isShowing()) {
            dismissDialog();
        } else {
            super.onBackPressed();
        }
    }
    /**
     * 关闭对话框
     */
    @Override
    public void stopProgess() {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showFail() {
        ToastUtil.show(mContext, "网络连接错误");
    }

    @Override
    public void showFail(String o) {
        ToastUtil.show(mContext, o);
    }
}
