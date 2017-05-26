package com.soarsky.car.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.uitl.TUtil;

/**
 * Created by 魏凯 on 2016/11/16.
 */

public abstract class BaseFragment<T extends BasePresenter, E extends BaseModel> extends Fragment implements BaseView{

    public boolean isNight;
    public T mPresenter;
    public E mModel;
    public Context mContext;
    private Toolbar toolbar;

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

    private void initBaseView(View view) {
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.root);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(getHeaderTitle());
        toolbar.setTitle("");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        initToolbar(toolbar);
        View.inflate(getActivity(), getLayoutId(), linearLayout);

    }

    public abstract int getLayoutId();

    public abstract void initView(View view);

    @Override
    public void showProgess() {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setIndeterminate(false);
        progressDialog.setMessage("正在加载...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void stopProgess() {
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    protected abstract String getHeaderTitle();
    protected void initToolbar(Toolbar toolbar){
        toolbar.setVisibility(View.VISIBLE);
    }
    public void addView(int layoutId){
        View.inflate(getActivity(),layoutId,toolbar);
    }

}
