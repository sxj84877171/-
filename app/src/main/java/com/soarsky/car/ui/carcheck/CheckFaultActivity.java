package com.soarsky.car.ui.carcheck;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;

/**
 * Andriod_Car_App
 * 作者： w王松清
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

public class CheckFaultActivity extends BaseActivity implements View.OnClickListener {
    /**
     *标题
     */
    private TextView titleTv;
    /**
     * 关闭页面
     */
    private ImageView closeView;
    /**
     * 故障列表
     */
    private ListView lv_fault;

    /**
     * 故障码
     */
    private String troubleStr;
    @Override
    public int getLayoutId() {
        return R.layout.activity_car_check_fault;
    }

    @Override
    public void initView() {
        troubleStr=getIntent().getStringExtra("troubleStr");

        titleTv = (TextView) findViewById(R.id.titleTv);
        titleTv.setText(R.string.car_check);
        closeView = (ImageView) findViewById(R.id.closeView);
        closeView.setOnClickListener(this);
        lv_fault = (ListView) findViewById(R.id.lv_fault);
        CarFaultAdapter carFaultAdapter = new CarFaultAdapter(CheckFaultActivity.this);
        lv_fault.setAdapter(carFaultAdapter);
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
