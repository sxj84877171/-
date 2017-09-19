package com.sxj.carloan.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;

/**
 * Created by admin on 2017/8/11.
 */

public class OtherRoleActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_content);
        checkVersion();
    }
}
