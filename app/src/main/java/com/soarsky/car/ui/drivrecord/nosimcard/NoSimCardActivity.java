package com.soarsky.car.ui.drivrecord.nosimcard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.ui.drivrecord.DrivRecordActivity;
import com.soarsky.car.ui.drivrecord.DrivRecordView;
import com.soarsky.car.ui.drivrecord.map.DrivingRecordMapActivity;
import com.soarsky.car.uitl.SpUtil;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能 没SIM卡界面
 * 该类为 NoSimCardActivity
 */
public class NoSimCardActivity extends AppCompatActivity implements View.OnClickListener{
    /**
     * 返回
     */
    private LinearLayout backLay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_sim_card);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);
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
