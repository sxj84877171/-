package com.soarsky.car.ui.carcheck;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/11/30
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  车辆检测故障页面
 */

public class CarFaultActivity extends BaseActivity implements View.OnClickListener {
    /**
     *标题
     */
    private TextView titleTv;
    /**
     * 关闭页面
     */
    private ImageView closeView;
    @Override
    public int getLayoutId() {
        return R.layout.activity_car_fault;
    }

    @Override
    public void initView() {
        titleTv = (TextView) findViewById(R.id.titleTv);
        titleTv.setText(R.string.car_check);

        closeView = (ImageView) findViewById(R.id.closeView);
        closeView.setOnClickListener(this);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.closeView:
                finish();
                break;
        }
    }
}
