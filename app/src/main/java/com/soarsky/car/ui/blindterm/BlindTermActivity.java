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
import android.widget.Toast;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.blindterm.validation.BlindTermValidationActivity;
import com.soarsky.car.ui.car.CarParam;
import com.soarsky.car.ui.car.CarSendParam;
import com.soarsky.car.uitl.ToastUtil;

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
 * 程序功能：绑定智能终端同步
 * 该类为 :绑定智能终端
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
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 查询我的机动车成功的回调
     * @param param
     */
    @Override
    public void showSuccess(CarParam param) {
        if(param != null){
            if("0".equals(param.getStatus())) {
                Intent i = new Intent();
                i.setClass(this, BlindTermValidationActivity.class);
                startActivity(i);
                finish();
            }else {
                ToastUtil.show(this,param.getMessage());
                setDialog();
            }
        }

    }

    /**
     *查询我的机动车失败的回调
     */
    @Override
    public void showError() {

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
                if(TextUtils.isEmpty(carnum)|TextUtils.isEmpty(identy)){

                    ToastUtil.show(this,R.string.blind_info_empty);

                }else {
                    CarSendParam p = new CarSendParam();
                    p.setCarnum(carnum);
                    mPresenter.getCarInfo(p);
                }

                break;
        }
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
