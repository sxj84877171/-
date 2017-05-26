package com.soarsky.car.ui.carcheck;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;

/**
 * Andriod_Car_App<br>
 * 作者： w王松清<br>
 * 时间： 2016/11/30<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  车辆检测故障页面<br>
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
