package com.soarsky.car.ui.help;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/5/16
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class HelpCustomActivity extends BaseActivity<HelpCustomPresent,HelpCustomModel> implements HelpCustomView,View.OnClickListener{


    private LinearLayout backLay;

    private TextView topicTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_custom;
    }

    @Override
    public void initView() {
        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText("帮助与客户");
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.backLay:
                finish();
                break;
        }
    }
}
