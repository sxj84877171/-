package com.soarsky.installationmanual.ui.main.fragment.task.nofinish.nofinishitem;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.ui.main.MainActivity;
import com.soarsky.installationmanual.ui.main.fragment.task.backreason.BackReasonActivity;
import com.soarsky.installationmanual.ui.main.fragment.task.nofinish.manualcheck.ManualCheckActivity;
import com.soarsky.installationmanual.ui.main.fragment.task.nofinish.simplecontent.SimpleContentActivity;
import com.soarsky.installationmanual.util.ToastUtil;
import com.soarsky.installationmanual.util.ValidatorUtils;


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
 * 该类为 未完成任务<br>
 */

public class NoFinishItemActivity extends BaseActivity<NoFinishItemPresent,NoFinishItemModel> implements NoFinishItemView {

    private String carNum;
    private ImageView okBtn;
    private ImageView backBtn;
    @Override
    public int getLayoutId() {
        return R.layout.activity_no_finish_item;
    }

    @Override
    public void initView() {
        carNum = getIntent().getStringExtra("carNum");
        okBtn = (ImageView) findViewById(R.id.okBtn);
        backBtn = (ImageView) findViewById(R.id.backBtn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoFinishItemActivity.this, SimpleContentActivity.class));
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoFinishItemActivity.this, BackReasonActivity.class));
            }
        });
    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showSuccess() {

    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        super.initToolbar(toolbar);
        toolbar.setTitle(carNum);
    }
}
