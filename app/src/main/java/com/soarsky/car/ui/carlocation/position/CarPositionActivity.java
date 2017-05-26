package com.soarsky.car.ui.carlocation.position;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.carlocation.alarm.CarAlarmActivity;
import com.soarsky.car.ui.carlocation.lovecar.CarLocationActivity;
import com.soarsky.car.uitl.ToastUtil;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：爱车位置选择界面 <br>
 * 该类为 爱车位置 <br>
 */

public class CarPositionActivity extends BaseActivity<CarPositionPresent,CarPositionModel> implements CarPositionView,View.OnClickListener{

    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 防盗报警记录
     */
    private TextView carPositionAgainTv;
    /**
     * 爱车位置
     */
    private TextView carPositionTv;
    /**
     * 闪灯找车
     */
    private TextView carPositionLookTv;



    @Override
    public int getLayoutId() {
        return R.layout.activity_car_position;
    }

    @Override
    public void initView() {

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(R.string.car_position);

        backLay = (LinearLayout)findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        carPositionAgainTv = (TextView) findViewById(R.id.carPositionAgainTv);
        carPositionAgainTv.setOnClickListener(this);

        carPositionTv = (TextView) findViewById(R.id.carPositionTv);
        carPositionTv.setOnClickListener(this);

        carPositionLookTv = (TextView) findViewById(R.id.carPositionLookTv);
        carPositionLookTv.setOnClickListener(this);

    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.carPositionAgainTv:

                Intent in = new Intent();
                in.setClass(this, CarAlarmActivity.class);
                startActivity(in);
                ToastUtil.show(this,"carPositionAgainTv");
                break;
            case R.id.carPositionTv:
                startActivity(new Intent(CarPositionActivity.this, CarLocationActivity.class));
                break;
            case R.id.carPositionLookTv:
                ToastUtil.show(this,"carPositionLookTv");
                break;
        }
    }

}
