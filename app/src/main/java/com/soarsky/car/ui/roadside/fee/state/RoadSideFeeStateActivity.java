package com.soarsky.car.ui.roadside.fee.state;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：支付完成
 * 该类为
 */


public class RoadSideFeeStateActivity extends BaseActivity<RoadSideFeeStatePresent,RoadSideFeeStateModel> implements RoadSideFeeStateView,View.OnClickListener{

    /**
     * 完成
     */
    private LinearLayout finishLay;



    @Override
    public int getLayoutId() {
        return R.layout.activity_road_side_fee_state;
    }

    @Override
    public void initView() {

        finishLay = (LinearLayout) findViewById(R.id.finishLay);
        finishLay.setOnClickListener(this);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.finishLay:
                mPresenter.finishFee();
                break;
        }
    }

    /**
     * 完成支付回调
     */
    @Override
    public void finishFee() {

        finish();
    }
}
