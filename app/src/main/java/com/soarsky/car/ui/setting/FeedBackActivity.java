package com.soarsky.car.ui.setting;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/5/11<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为意见反馈页面<br>
 */

public class FeedBackActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 返回
     */
    private LinearLayout illegalBackLay;

    /**
     * 标题
     */
    private TextView topicTv;
    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback3;
    }

    @Override
    public void initView() {
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.feed_back));

        illegalBackLay = (LinearLayout) findViewById(R.id.illegalBackLay);
        illegalBackLay.setOnClickListener(this);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.illegalBackLay:
                finish();
                break;
        }
    }
}
