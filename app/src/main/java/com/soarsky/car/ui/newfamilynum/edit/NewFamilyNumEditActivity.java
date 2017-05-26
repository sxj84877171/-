package com.soarsky.car.ui.newfamilynum.edit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.blindterm.ClearEditText;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/5/12
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class NewFamilyNumEditActivity extends BaseActivity<NewFamilyNumEditPresent,NewFamilyNumEditModel> implements NewFamilyNumEditView,View.OnClickListener{

    private TextView topicTv;

    private LinearLayout backLay;

    private Button familyNumEditBtn;

    private ClearEditText familyNumPhoneEt;

    private String oldPhone ="";

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_family_num_edit;
    }

    @Override
    public void initView() {

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText("亲情号编辑");

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        familyNumEditBtn = (Button) findViewById(R.id.familyNumEditBtn);
        familyNumEditBtn.setOnClickListener(this);

        familyNumPhoneEt = (ClearEditText) findViewById(R.id.familyNumPhoneEt);


    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        oldPhone = getIntent().getStringExtra("phoneNum");
        familyNumPhoneEt.setText(oldPhone);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.familyNumEditBtn:
                break;
            case R.id.backLay:
                finish();
                break;
        }

    }
}
