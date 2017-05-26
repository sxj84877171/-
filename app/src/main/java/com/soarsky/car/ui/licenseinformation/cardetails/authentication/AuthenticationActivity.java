package com.soarsky.car.ui.licenseinformation.cardetails.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.blindterm.ClearEditText;
import com.soarsky.car.ui.licenseinformation.cardetails.CarDetailsActivity;
import com.soarsky.car.uitl.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import static com.soarsky.car.ConstantsUmeng.CHECK_SHENFEN;


/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/29<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  身份验证页面<br>
 */

public class AuthenticationActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回按钮
     */
    private LinearLayout backLay;
    /**
     * 确定按钮
     */
    private Button bt_sure;
    /**
     * 密码编辑输入框
     */
    private EditText et_pwd;
    /**
     * 车牌号编辑输入框
     */
    private ClearEditText et_carNum;
    /**
     * 密码可视图标
     */
    private ImageView iv_pwd;
    /**
     * 是否输入了密码
     */
    private Boolean isPassword = false;

    private String carnum = "";
    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication;
    }

    @Override
    public void initView() {
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.authentication));

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        bt_sure = (Button) findViewById(R.id.bt_sure);
        bt_sure.setOnClickListener(this);
        et_pwd = (EditText) findViewById(R.id.et_pwd);

        et_carNum = (ClearEditText) findViewById(R.id.et_carNum);
        et_carNum.setOnClickListener(this);


        iv_pwd = (ImageView) findViewById(R.id.iv_pwd);
        iv_pwd.setOnClickListener(this);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carnum = getIntent().getStringExtra("carnum");
        et_carNum.setText(carnum);
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
            case R.id.et_carNum:
                et_carNum.setFocusableInTouchMode(true);
                break;
            case R.id.bt_sure:
                MobclickAgent.onEvent(AuthenticationActivity.this,CHECK_SHENFEN);
                String pwd = et_pwd.getText().toString();
                String carnum = et_carNum.getText().toString();
                if(TextUtils.isEmpty(pwd) || TextUtils.isEmpty(carnum)){

                    ToastUtil.show(this,R.string.illegal_query_set_empty);
                }else {
                    Intent intent = new Intent();
                    intent.setClass(AuthenticationActivity.this, CarDetailsActivity.class);
                    intent.putExtra("carnum",carnum);
                    startActivity(intent);
                }
                break;

            case R.id.iv_pwd:
                if(isPassword == false){
                    et_pwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    iv_pwd.setImageResource(R.mipmap.confrim);
                    isPassword = true;
                }else{
                    et_pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iv_pwd.setImageResource(R.mipmap.icon);
                    isPassword = false;
                }
                break;
        }
    }
}
