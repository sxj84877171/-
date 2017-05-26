package com.soarsky.installationmanual.ui.main.fragment.task.nofinish.manualcheck;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.ui.main.MainActivity;
import com.soarsky.installationmanual.ui.main.fragment.task.backreason.BackReasonActivity;
import com.soarsky.installationmanual.ui.main.fragment.task.nofinish.autocheck.AutoCheckActivity;
import com.soarsky.installationmanual.ui.main.fragment.task.nofinish.nofinishitem.NoFinishItemActivity;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 手动检测界面<br>
 */

public class ManualCheckActivity extends BaseActivity<ManualCheckPresent,ManualCheckModel> implements ManualCheckView {

    private ImageView nextIv;
    private ImageView back;
    private RelativeLayout yingjianguzhangRl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_manual_check;
    }

    @Override
    public void initView() {
        addView(R.layout.back_left_toolbar);
    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        yingjianguzhangRl = (RelativeLayout) findViewById(R.id.yingjianguzhangRl);
        back = (ImageView) findViewById(R.id.backBtn);
        nextIv = (ImageView) findViewById(R.id.nextIv);
        nextIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManualCheckActivity.this,AutoCheckActivity.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManualCheckActivity.this,MainActivity.class));
            }
        });
        yingjianguzhangRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManualCheckActivity.this, BackReasonActivity.class));
            }
        });
    }

    @Override
    public void showSuccess() {

    }
}
