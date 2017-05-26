package com.soarsky.policeclient.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.soarsky.policeclient.R;
import com.soarsky.policeclient.uitl.LogUtil;
import com.soarsky.policeclient.uitl.SpUtil;
import com.soarsky.policeclient.uitl.TUtil;

import butterknife.ButterKnife;

/**
 * 警务通<br>
 * 作者： 孙向锦<br>
 * 时间： 2016/12/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：sunxiangjin@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为Activity基类，负责MVP架构的整合，负责一些基础逻辑工作。<br>
 */
public abstract class BaseActivity<T extends BasePresenter, E extends BaseModel> extends AppCompatActivity {
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
     *  对话框字段
     */
    private ProgressDialog progressDialog;

    /**
     * activity类的初始化方法
     * @param savedInstanceState 如果activity被重新加载会得到从之前的activity中保存的Bundle数据
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isNight = SpUtil.isNight();
//        setTheme(isNight ? R.style.AppThemeNight : R.style.AppThemeDay);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.setContentView(this.getLayoutId());
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        this.initView();
        if (this instanceof BaseView) {
            if (mPresenter != null) {
                mPresenter.context = this;
                mPresenter.setVM(this, mModel);
            }
        }
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
        if (isNight != SpUtil.isNight()) reload();
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
     * 查找对应的View 纯功能方法
     * @param resID 控件ID
     * @param <T> 泛型
     * @return
     */
    public <T extends View> T findViewWithID(int resID) {
        return (T) findViewById(resID);
    }

    /**
     * 抛出关于对话框
     */
    public void showAbout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.about);
        PackageInfo packageInfo = getPackageInfo(this);
        String version = getResources().getString(R.string.version_code);
        if (packageInfo != null) {
            version = packageInfo.versionName;
        }
        builder.setMessage(version);
        builder.create().show();
    }

    /**
     * 获取当前应用程序包信息
     * @param context
     * @return
     */
    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;
        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);
            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pi;
    }

    /**
     * 弹出进度对话框
     */
    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            //progressDialog.setIndeterminate(false);
            progressDialog.setMessage(getResources().getString(R.string.loading));
            progressDialog.show();
        }
    }

    /**
     * 关闭对话框
     */
    public void dismissProgress(){
        if(progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null ;
        }
    }
}
