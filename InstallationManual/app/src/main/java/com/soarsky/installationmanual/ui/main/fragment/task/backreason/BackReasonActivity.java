package com.soarsky.installationmanual.ui.main.fragment.task.backreason;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.ui.main.MainActivity;
import com.soarsky.installationmanual.ui.main.fragment.task.nofinish.manualcheck.ManualCheckActivity;

/**
 * InstallationManual<br>
 * 作者： 苏云<br>
 * 时间： 2017/2/28<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 退回任务原因界面<br>
 */


public class BackReasonActivity extends BaseActivity<BackReasonPresent,BackReasonModel> implements BackReasonView,View.OnClickListener{
    private ImageView back;
    /**
     * 拉收布局
     */
    private LinearLayout reasonLay;

    private LinearLayout backReasonLay;
    /**
     * 拉收View
     */
    private ImageView reasonBottomView;
    /**
     * 原因编辑框
     */
    private EditText backReasonEt;
    /**
     * 退回按钮
     */
    private Button backReasonBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_backreason;
    }

    @Override
    public void initView() {
        addView(R.layout.back_left_toolbar);
        back = (ImageView) findViewById(R.id.backBtn);
        reasonLay = (LinearLayout) findViewById(R.id.reasonLay);
        reasonLay.setOnClickListener(this);

        backReasonBtn = (Button) findViewById(R.id.backReasonBtn);
        backReasonBtn.setOnClickListener(this);

        backReasonLay = (LinearLayout) findViewById(R.id.backReasonLay);

        reasonBottomView = (ImageView) findViewById(R.id.reasonBottomView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BackReasonActivity.this,MainActivity.class));
            }
        });
    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.reasonLay:
                if(backReasonLay.getVisibility() == View.VISIBLE){
                    backReasonLay.setVisibility(View.GONE);
                    reasonBottomView.setImageResource(R.drawable.bottom_d);
                }else {
                    backReasonLay.setVisibility(View.VISIBLE);
                    reasonBottomView.setImageResource(R.drawable.bottom_u);
                }
                break;
        }
    }
}
