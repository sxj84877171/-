package com.sxj.carloan.yewuyuan;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.ui.MainActivity;

/**
 * Created by admin on 2017/8/18.
 */

public class YeWuMainPage extends BaseActivity {

    private TextView username;
    private View weiwancheng;
    private View yiwancheng;
    private View yewujiandang;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_diaocha);
        initView();
        initListener();
    }

    private void initView(){
        yewujiandang = getViewById(R.id.yewujiandang);
        weiwancheng = getViewById(R.id.weiwancheng);
        yiwancheng = getViewById(R.id.yiwancheng);

        username = getViewById(R.id.username);
        username.setText(getUsername() + ",您好！");
    }

    private void initListener(){
        weiwancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        yiwancheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        yewujiandang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), YeWuJianDangActivity.class);
                startActivity(intent);
            }
        });
    }
}
