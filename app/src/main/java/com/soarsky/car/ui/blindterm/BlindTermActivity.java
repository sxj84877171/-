package com.soarsky.car.ui.blindterm;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.BlindTermQueryPwdInfo;
import com.soarsky.car.bean.CarInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.blindterm.validation.BlindTermValidTimeCount;
import com.soarsky.car.ui.blindterm.validation.BlindTermValidationActivity;
import com.soarsky.car.uitl.ToastUtil;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：绑定智能终端同步 <br>
 * 该类为 :绑定智能终端 <br>
 */


public class BlindTermActivity extends BaseActivity<BlindTermPresent,BlindTermModel> implements BlindTermView,View.OnClickListener{

    /**
     * 车牌号
     */
    private ClearEditText blind_term_carnumEt;
    /**
     * 车辆识别码
     */
    private EditText blind_term_identyEt;
    /**
     * 确定
     */
    private Button blind_term_sureBtn;
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 应用
     */
    private App app;

    private final static String TAG = "BlindTermActivity";
    /**
     * 手机号码
     */
    private EditText blind_term_phoneEt;
    /**
     * 发送验证码
     */
    private TextView sendBlindPhoneTv;
    /**
     * 验证码
     */
    private EditText blind_term_verCodeEt;

    private BlindTermValidTimeCount timeCount;

    @Override
    public int getLayoutId() {
        return R.layout.activity_blind_term;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.blind_term_title));

        blind_term_carnumEt = (ClearEditText) findViewById(R.id.blind_term_carnumEt);
        blind_term_identyEt = (EditText) findViewById(R.id.blind_term_identyEt);

        blind_term_sureBtn = (Button) findViewById(R.id.blind_term_sureBtn);
        blind_term_sureBtn.setOnClickListener(this);

        blind_term_phoneEt = (EditText) findViewById(R.id.blind_term_phoneEt);

        sendBlindPhoneTv = (TextView) findViewById(R.id.sendBlindPhoneTv);
        sendBlindPhoneTv.setOnClickListener(this);

        blind_term_verCodeEt = (EditText) findViewById(R.id.blind_term_verCodeEt);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }


    /**
     * 绑定智能终端成功
     * @param param 绑定终端的信息参数
     */
    @Override
    public void bindIllegalSuccess(ResponseDataBean<String> param) {
        if(param != null){
            if(Constants.REQUEST_SUCCESS.equals(param.getStatus())) {
               finish();
            }else {
                //将后台的message改成自己的信息显示给用户               王松清
                ToastUtil.show(this,R.string.bind_term_fail);
            }
        }
    }

    /**
     * 绑定智能终端失败
     */
    @Override
    public void bindIllegalFail() {
        ToastUtil.show(this,R.string.throwable);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.blind_term_sureBtn:
                String carnum = blind_term_carnumEt.getText().toString();
                String identy = blind_term_identyEt.getText().toString();
                String vcode = blind_term_verCodeEt.getText().toString();
                String phone = blind_term_phoneEt.getText().toString();
                String code = blind_term_verCodeEt.getText().toString();
                if(TextUtils.isEmpty(carnum)|TextUtils.isEmpty(identy)||TextUtils.isEmpty(vcode) || TextUtils.isEmpty(phone)||TextUtils.isEmpty(code)){

                    ToastUtil.show(this,R.string.blind_info_empty);

                }else {

//                    mPresenter.bindIllegal(carnum,app.getCommonParam().getUserName(),vcode,phone,code,"1");
                    mPresenter.bindTermianl(carnum,phone,identy,vcode,app.getCommonParam().getUserName());
                }

                break;
            case R.id.sendBlindPhoneTv:
                if(TextUtils.isEmpty(blind_term_phoneEt.getText().toString())){
                    ToastUtil.show(this,R.string.phonetip);
                    return;
                }
                timeCount = new BlindTermValidTimeCount(60000, 1000,this,sendBlindPhoneTv);
                timeCount.start();

                mPresenter.sendsms(blind_term_phoneEt.getText().toString(),"2");
                break;
        }
    }

    /**
     * 获取验证码成功
     * @param bean 验证码信息
     */
    @Override
    public void sendSmsSuccess(ResponseDataBean<Void> bean) {

        if(Constants.REQUEST_SUCCESS.equals(bean.getStatus())){

        }else {
            ToastUtil.show(this,bean.getMessage());
        }
    }

    /**
     * 获取验证码失败
     */
    @Override
    public void sendSmsFail() {

    }

    /**
     * 获取失败重试
     */
    public void setDialog(){

        Dialog dialog = new AlertDialog.Builder(this).setMessage(getString(R.string.blind_fail_tip))
                .setPositiveButton(getString(R.string.cancle), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                    }
                }).setNegativeButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                    }
                }).create();
        dialog.show();

    }

}
