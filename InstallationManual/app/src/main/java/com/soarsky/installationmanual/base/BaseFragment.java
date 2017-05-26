package com.soarsky.installationmanual.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.util.TUtil;
import com.soarsky.installationmanual.util.ToastUtil;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2017/2/16<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 Fragment基类<br>
 */

public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment implements BaseView{
    /**
     * 参考BaseActivity
     */
    public boolean isNight;
    /**
     * 参考BaseActivity
     */
    public T mPresenter;
    /**
     * 参考BaseActivity
     */
    public E mModel;
    /**
     * 参考BaseActivity
     */
    public Context mContext;
    /**
     * 参考BaseActivity
     */
    private Toolbar toolbar;
    /**
     * 参考BaseActivity
     */

    private ProgressDialog progressDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.base_layout,container,false);
        initBaseView(view);
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        this.initView(view);
        if (this instanceof BaseView){
            if(mPresenter != null){
                mPresenter.context = (Activity) mContext;
                mPresenter.setVM(this, mModel);
            }
        }

        return view;
    }
    /**
     * 参考BaseActivity
     */
    private void initBaseView(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.root);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        toolbar.setTitle(getHeaderTitle());
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        initToolbar(toolbar);
        View.inflate(getActivity(), getLayoutId(), linearLayout);

    }
    /**
     * 参考BaseActivity
     */
    public abstract int getLayoutId();
    /**
     * 参考BaseActivity
     */
    public abstract void initView(View view);

    @Override
    public void showProgess() {
        if(progressDialog == null){
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setCancelable(false);
            progressDialog.setMessage("正在加载...");
        }
        progressDialog.show();
        progressDialog.setOnKeyListener(onKeyListener);
    }
    /**
     * add a keylistener for progress dialog
     */
    /**
     * 参考BaseActivity
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
     * 参考BaseActivity
     */
    public void dismissDialog() {
        if (getActivity().isFinishing()) {
            return;
        }
        if (null != progressDialog && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    /**
     * 参考BaseActivity
     */
    @Override
    public void stopProgess() {
        if(progressDialog != null){
            progressDialog.dismiss();
        }
    }
    /**
     * 参考BaseActivity
     */
    protected abstract String getHeaderTitle();
    /**
     * 参考BaseActivity
     */
    protected void initToolbar(Toolbar toolbar){
        toolbar.setVisibility(View.VISIBLE);
    }
    /**
     * 参考BaseActivity
     */
    public void addView(int layoutId){
        View.inflate(getActivity(),layoutId,toolbar);
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
