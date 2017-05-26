package com.soarsky.car.ui.danger.responsibilitydetails.haveobjection;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.danger.responsibilitydetails.ResponsibilityParam;
import com.soarsky.car.ui.home.HomeActivity;
import com.soarsky.car.uitl.ToastUtil;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/23
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 填写异议理由的界面
 */

public class HaveObjectionActivity extends BaseActivity<HaveObjectionPresent,HaveObjectionModel> implements HaveObjectionView,View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回按钮
     */
    private ImageView backView;

    private LinearLayout backLay;

    private ResponsibilityParam param;
    /**
     * 车牌号
     */
    private TextView objectionCarNumTv;
    /**
     * 驾驶证号
     */
    private TextView objectionDriverTv;
    /**
     * 原因
     */
    private EditText objectionEt;
    /**
     * 己方责任
     */
    private RadioGroup radioGroup;
    /**
     * 全责
     */
    private RadioButton radio_button1;
    /**
     * 同责
     */
    private RadioButton radio_button2;
    /**
     * 无责
     */
    private RadioButton radio_button3;
    /**
     * 对方车牌号
     */
    private TextView objectionOtherCarNumTv;
    /**
     * 对方驾驶证
     */
    private TextView objectionOtherDriverTv;
    /**
     * 对方责任
     */
    private RadioGroup otherRadioGroup;
    /**
     * 全责
     */
    private RadioButton other_radio_button1;
    /**
     * 同责
     */
    private RadioButton other_radio_button2;
    /**
     * 无责
     */
    private RadioButton other_radio_button3;
    /**
     * 再次上传
     */
    private Button objectionUpLoadBtn;

    /**
     * 己方的责任
     */
    private String faffirm ="1";
    /**
     * 对方的责任
     */
    private String saffirm ="3";



    @Override
    public int getLayoutId() {
        return R.layout.activity_have_objection;
    }

    @Override
    public void initView() {
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.rending));

        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        objectionCarNumTv = (TextView) findViewById(R.id.objectionCarNumTv);
        objectionDriverTv = (TextView) findViewById(R.id.objectionDriverTv);
        objectionEt = (EditText) findViewById(R.id.objectionEt);

        objectionUpLoadBtn = (Button) findViewById(R.id.objectionUpLoadBtn);
        objectionUpLoadBtn.setOnClickListener(this);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radio_button1 = (RadioButton) findViewById(R.id.radio_button1);
        radio_button2 = (RadioButton) findViewById(R.id.radio_button2);
        radio_button3 = (RadioButton) findViewById(R.id.radio_button3);

        objectionOtherCarNumTv = (TextView) findViewById(R.id.objectionOtherCarNumTv);
        objectionOtherDriverTv = (TextView) findViewById(R.id.objectionOtherDriverTv);
        otherRadioGroup = (RadioGroup) findViewById(R.id.otherRadioGroup);
        other_radio_button1 = (RadioButton) findViewById(R.id.other_radio_button1);
        other_radio_button2 = (RadioButton) findViewById(R.id.other_radio_button2);
        other_radio_button3 = (RadioButton) findViewById(R.id.other_radio_button3);

        radio_button1.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == radio_button1.getId()){
                    faffirm = "1";

                }else if(i == radio_button2.getId()){
                    faffirm = "2";
                }else if(i == radio_button3.getId()){
                    faffirm = "3";
                }
            }
        });

        other_radio_button3.setChecked(true);
        otherRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == other_radio_button1.getId()){
                    saffirm = "1";

                }else if(i == other_radio_button2.getId()){
                    saffirm = "2";
                }else if(i == other_radio_button3.getId()){
                    saffirm = "3";
                }
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        param = (ResponsibilityParam) getIntent().getSerializableExtra("param");
        if(param != null){
            objectionCarNumTv.setText(param.getCarnum());
            objectionDriverTv.setText(param.getDriverLicense());
            objectionOtherCarNumTv.setText(param.getOthercarnum());
            objectionOtherDriverTv.setText(param.getOtherdriverLicense());
        }
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }
    /**
     * 提交有异议接口成功
     * @param fastDissentParam
     */
    @Override
    public void fastDissentSuccess(FastDissentParam fastDissentParam) {

        if(fastDissentParam != null){
            if ("0".equals(fastDissentParam.getStatus())){

                ToastUtil.show(this,fastDissentParam.getMessage());
                final Dialog dialog = new Dialog(this);
                View view1 = View.inflate(HaveObjectionActivity.this,R.layout.dialog_wait_to_check,null);
                Button positiveButton = (Button) view1.findViewById(R.id.positiveButton);
                dialog.setContentView(view1);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                        finish();
                        startActivity(new Intent(HaveObjectionActivity.this, HomeActivity.class));
                    }
                });
                dialog.show();

            }else {
                ToastUtil.show(this,fastDissentParam.getMessage());
            }
        }
    }
    /**
     * 提交有异议接口失败
     */
    @Override
    public void fastDissentFail() {

        ToastUtil.show(this,R.string.throwable);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backView:
            case R.id.backLay:
                finish();
                break;
            case R.id.objectionUpLoadBtn:
                if(TextUtils.isEmpty(objectionEt.getText().toString())){
                    ToastUtil.show(this,R.string.danger_haveobjectionreason);
                }else {
                    FastDissentSendParam p = new FastDissentSendParam();
                    p.setId(param.getId());
                    p.setCarnum(objectionCarNumTv.getText().toString());
                    p.setFaffirm(faffirm);
                    p.setSaffirm(saffirm);
                    p.setReason(objectionEt.getText().toString());
                    mPresenter.fastDissent(p);
                }
                break;
        }
    }
}
