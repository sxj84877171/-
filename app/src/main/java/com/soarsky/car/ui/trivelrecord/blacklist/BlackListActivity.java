package com.soarsky.car.ui.trivelrecord.blacklist;

import android.os.Bundle;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.TravelRecords;


/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2017/3/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  行车记录黑名单界面<br>
 */


public class BlackListActivity extends BaseActivity {

    TravelRecords travelRecords;
    private TextView  carNumTv;
    private TextView   reasonTv;
    private TextView   pTypeTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        travelRecords= (TravelRecords) getIntent().getSerializableExtra("traverlRecords");

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_black_list;
    }

    @Override
    public void initView() {

        carNumTv= (TextView) findViewById(R.id.black_list_carnum);
        reasonTv= (TextView) findViewById(R.id.black_list_reason);
        pTypeTv= (TextView) findViewById(R.id.black_list_ptype);

        if(null!=travelRecords){
        carNumTv.setText(travelRecords.getCarnum());
        pTypeTv.setText(travelRecords.getPtype());
        }


    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }
}
