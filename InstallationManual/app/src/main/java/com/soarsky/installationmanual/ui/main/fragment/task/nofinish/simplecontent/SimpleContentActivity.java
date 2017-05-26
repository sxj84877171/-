package com.soarsky.installationmanual.ui.main.fragment.task.nofinish.simplecontent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.ui.main.MainActivity;
import com.soarsky.installationmanual.ui.main.fragment.task.backreason.BackReasonActivity;
import com.soarsky.installationmanual.ui.main.fragment.task.nofinish.manualcheck.ManualCheckActivity;
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
 * 该类为 任务简介界面<br>
 */

public class SimpleContentActivity extends BaseActivity<SimpleContentPresent,SimpleContentModel> implements SimpleContentView {
    private ImageView nextIv;
    private ImageView back;
    private ImageView wufaanzhuangBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_simple_content;
    }

    @Override
    public void initView() {
        addView(R.layout.back_left_toolbar);
        addView(R.layout.simple_content_right_toolbar);
    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wufaanzhuangBtn = (ImageView) findViewById(R.id.wufaanzhuangBtn);
        back = (ImageView) findViewById(R.id.backBtn);
        nextIv = (ImageView) findViewById(R.id.nextIv);
        nextIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SimpleContentActivity.this,ManualCheckActivity.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SimpleContentActivity.this,MainActivity.class));
            }
        });
        wufaanzhuangBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SimpleContentActivity.this, BackReasonActivity.class));
            }
        });
    }

    @Override
    public void showSuccess() {

    }
}
