package com.soarsky.car.ui.car.validation;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.car.CarActivity;
import com.soarsky.car.uitl.ToastUtil;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/8 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能： 机动车验证 <br>
 * 该类为 CarValidationActivity <br>
 */


public class CarValidationActivity extends BaseActivity<CarValidationPresent,CarValidationModel> implements CarValidationView,View.OnClickListener{

    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 输入车牌号
     */
    private EditText carInputTv;
    /**
     * 输入真实姓名
     */
    private EditText carRealNameTv;

    /**
     * 确定
     */
    private Button carValidationSureBtn;



    @Override
    public int getLayoutId() {
        return R.layout.activity_car_validation;
    }

    @Override
    public void initView() {

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.cartitle));

        carInputTv = (EditText) findViewById(R.id.carInputTv);
        carRealNameTv = (EditText) findViewById(R.id.carRealNameTv);

        carValidationSureBtn = (Button) findViewById(R.id.carValidationSureBtn);
        carValidationSureBtn.setOnClickListener(this);
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
            case R.id.carValidationSureBtn:

                String inputNum = carInputTv.getText().toString();
                String carRealName = carRealNameTv.getText().toString();

                if(TextUtils.isEmpty(inputNum) || TextUtils.isEmpty(carRealName)){
                    ToastUtil.show(this,R.string.forgetisEmpty);
                }else {
                    Intent i = new Intent();
                    i.setClass(this, CarActivity.class);
                    i.putExtra("carnum",inputNum);
                    startActivity(i);
                    finish();
                }

                break;
        }
    }
}
