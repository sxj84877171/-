package com.soarsky.car.ui.illegal.query;

import android.content.Intent;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.illegal.query.list.IllegalQueryListActivity;
import com.soarsky.car.ui.illegal.query.set.IllegalQuerySetActivity;
import com.soarsky.car.uitl.EncryptUtils;
import com.soarsky.car.uitl.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import static com.soarsky.car.ConstantsUmeng.ILLEGAL_CHANGE_PWD;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/29<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：切换车辆密码验证<br>
 * 该类为 IllegalQueryActivity<br>
 */


public class IllegalQueryActivity extends BaseActivity<IllegalQueryPresent,IllegalQueryModel> implements IllegalQueryView,View.OnClickListener{

    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 车牌号
     */
    private EditText illegalCarNumEt;
    /**
     * 车牌号删除布局
     */
    private RelativeLayout illegalCarNumLay;
    /**
     * 车牌号删除view
     */
    private ImageView illegalCarNumView;
    /**
     * 查询密码布局
     */
    private RelativeLayout illegalPwdLay;
    /**
     * 密码框
     */
    private EditText illegalPwdEt;
    /**
     * 查询密码view
     */
    private ImageView illegalPwdView;
    /***
     * 确定
     */
    private Button illegalSureBtn;
    /**
     * 密码是否可见的标志
     */
    private boolean isPassword = false;
    /**
     * car省份
     */
    private TextView carProvinceTv;
    /**
     * car城市
     */
    private TextView carCityTv;


    @Override
    public int getLayoutId() {
        return R.layout.activity_illegal_query3;
    }

    @Override
    public void initView() {

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText("切换车辆");

        illegalCarNumEt = (EditText) findViewById(R.id.illegalCarNumEt);
        illegalCarNumLay = (RelativeLayout) findViewById(R.id.illegalCarNumLay);
        illegalCarNumLay.setOnClickListener(this);
        illegalCarNumView = (ImageView) findViewById(R.id.illegalCarNumView);

        illegalPwdLay = (RelativeLayout) findViewById(R.id.illegalPwdLay);
        illegalPwdLay.setOnClickListener(this);
        illegalPwdEt = (EditText) findViewById(R.id.illegalPwdEt);
        illegalPwdView = (ImageView) findViewById(R.id.illegalPwdView);
        illegalSureBtn = (Button) findViewById(R.id.illegalSureBtn);
        illegalSureBtn.setOnClickListener(this);

        carProvinceTv = (TextView) findViewById(R.id.carProvinceTv);
        carProvinceTv.setOnClickListener(this);

        carCityTv = (TextView) findViewById(R.id.carCityTv);
        carCityTv.setOnClickListener(this);

        illegalCarNumEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                illegalCarNumView.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 是否可见
     */
    @Override
    public void isVisiblePwd() {
        if(isPassword == false){
            illegalPwdEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            illegalPwdView.setImageResource(R.mipmap.confrim);
            isPassword = true;
        }else{
            illegalPwdEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            illegalPwdView.setImageResource(R.mipmap.icon);
            isPassword = false;
        }
    }
    /**
     * 验证查询密码成功
     * @param illegalQueryParam 参数
     */
    @Override
    public void getIlleagaInfoSuccess(ResponseDataBean<Object> illegalQueryParam) {

        if(illegalQueryParam != null){
            if(Constants.REQUEST_SUCCESS.equals(illegalQueryParam.getStatus())){
                Intent i = new Intent();
                i.setClass(this, IllegalQueryListActivity.class);
                i.putExtra("carnum",illegalCarNumEt.getText().toString());
                startActivity(i);
                finish();
            }else if("801".equals(illegalQueryParam.getStatus())){

                ToastUtil.show(this,illegalQueryParam.getMessage());
                Intent intent = new Intent(IllegalQueryActivity.this, IllegalQuerySetActivity.class);
                startActivity(intent);

            }else {

                ToastUtil.show(this,illegalQueryParam.getMessage());

            }
        }
    }

    /**
     * 验证查询密码失败
     */
    @Override
    public void getIlleagaInfoFail() {
        ToastUtil.show(this,R.string.throwable);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.illegalSureBtn:
                MobclickAgent.onEvent(IllegalQueryActivity.this,ILLEGAL_CHANGE_PWD);
                if(TextUtils.isEmpty(illegalCarNumEt.getText().toString())||TextUtils.isEmpty(illegalPwdEt.getText().toString())){

                    ToastUtil.show(this,R.string.illegal_info_tip);

                }else {
                    mPresenter.getIlleagaInfo(illegalCarNumEt.getText().toString(), EncryptUtils.encryptMD5ToString(illegalPwdEt.getText().toString()).toLowerCase());
                }
                break;
            case R.id.illegalCarNumLay:
                illegalCarNumEt.setText("");
                break;
            case R.id.illegalPwdLay:
                mPresenter.isVisiblePwd();
                break;
            case R.id.backLay:
                finish();
                break;
        }
    }
}
