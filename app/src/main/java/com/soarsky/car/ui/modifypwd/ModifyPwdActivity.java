package com.soarsky.car.ui.modifypwd;

import android.app.ProgressDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
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
 * 程序功能：
 * 该类为 修改密码
 */

public class ModifyPwdActivity extends BaseActivity<ModifyPwdPresent,ModifyPwdModel> implements ModifyPwdView,View.OnClickListener{


    private TextView topicTv;
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     *  当前密码
     */
    private EditText modifyCurrentpwdEt;

    private RelativeLayout modifyCurrentpwdLay;

    private ImageView modifyCurrentpwdView;
    /**
     * 新密码
     */
    private EditText modifyNowpwdEt;

    private RelativeLayout modifyNowpwdLay;

    private ImageView modifyNowpwdView;
    /**
     * 确认密码
     */
    private EditText modifyConfirmpwdEt;

    private RelativeLayout modifyConfirmpwdLay;

    private ImageView modifyConfirmpwdView;

    private Button modifyBtn;

    private boolean isCurrent = false;
    private boolean isNow = false;
    private boolean isConfirm = false;

    private App app;

    private ProgressDialog progressDialog;

    public final static String TAG = "ModifyPwdActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.modifytitle));

        modifyCurrentpwdEt = (EditText) findViewById(R.id.modifyCurrentpwdEt);
        modifyCurrentpwdLay = (RelativeLayout) findViewById(R.id.modifyCurrentpwdLay);
        modifyCurrentpwdLay.setOnClickListener(this);
        modifyCurrentpwdView = (ImageView) findViewById(R.id.modifyCurrentpwdView);
        modifyCurrentpwdView.setOnClickListener(this);

        modifyNowpwdEt = (EditText) findViewById(R.id.modifyNowpwdEt);
        modifyNowpwdLay = (RelativeLayout) findViewById(R.id.modifyNowpwdLay);
        modifyNowpwdLay.setOnClickListener(this);
        modifyNowpwdView = (ImageView) findViewById(R.id.modifyNowpwdView);
        modifyNowpwdView.setOnClickListener(this);

        modifyConfirmpwdEt = (EditText) findViewById(R.id.modifyConfirmpwdEt);
        modifyConfirmpwdLay = (RelativeLayout) findViewById(R.id.modifyConfirmpwdLay);

        modifyConfirmpwdLay.setOnClickListener(this);
        modifyConfirmpwdView = (ImageView) findViewById(R.id.modifyConfirmpwdView);
        modifyConfirmpwdView.setOnClickListener(this);

        modifyBtn = (Button) findViewById(R.id.modifyBtn);
        modifyBtn.setOnClickListener(this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);


    }

    @Override
    protected String getHeaderTitle() {
        return "修改密码";
    }

    @Override
    public void showProgess() {

        super.showProgess();
    }

    @Override
    public void stopProgess() {

        super.stopProgess();
    }

    @Override
    public void showData(ModifyPwdParam param) {

        if(param != null) {
            ToastUtil.show(this, "" + param.getMessage());
            if("0".equals(param.getStatus())){
                finish();
            }

        }else {
            ToastUtil.show(this,R.string.error);
        }


    }

    @Override
    public void showonError() {
        ToastUtil.show(this,R.string.throwable);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLay:
                finish();
            break;
            case R.id.modifyCurrentpwdLay:
            case R.id.modifyCurrentpwdView:
                if(isCurrent == false){
                    modifyCurrentpwdEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    modifyCurrentpwdView.setImageResource(R.mipmap.confrim);
                    isCurrent = true;
                }else {
                    modifyCurrentpwdEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    modifyCurrentpwdView.setImageResource(R.mipmap.icon);
                    isCurrent = false;
                }

                break;
            case R.id.modifyNowpwdLay:
            case R.id.modifyNowpwdView:

                if(isNow == false){
                    modifyNowpwdEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    modifyNowpwdView.setImageResource(R.mipmap.confrim);
                    isNow = true;
                }else {
                    modifyNowpwdEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    modifyNowpwdView.setImageResource(R.mipmap.icon);
                    isNow = false;
                }

                break;
            case R.id.modifyConfirmpwdLay:
            case R.id.modifyConfirmpwdView:
                if(isConfirm == false){
                    modifyConfirmpwdEt.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    modifyConfirmpwdView.setImageResource(R.mipmap.confrim);
                    isConfirm = true;
                }else {
                    modifyConfirmpwdEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    modifyConfirmpwdView.setImageResource(R.mipmap.icon);
                    isConfirm = false;
                }

                break;
            case R.id.modifyBtn:
                String currentPwd = modifyCurrentpwdEt.getText().toString().trim();
                String modifyNowpwd = modifyNowpwdEt.getText().toString().trim();
                String modifyConfirmpwd = modifyConfirmpwdEt.getText().toString().trim();

                if(modifyNowpwd.equals(modifyConfirmpwd)) {
                    if (TextUtils.isEmpty(currentPwd) || TextUtils.isEmpty(modifyNowpwd) || TextUtils.isEmpty(modifyConfirmpwd)) {

                        ToastUtil.show(this, R.string.modifyisEmpty);

                    } else {

                        mPresenter.modifyPwd(app.getCommonParam().getUserName(), currentPwd, modifyNowpwd);

                    }
                }else {
                       ToastUtil.show(this,R.string.modifyconfirmtip);
                }
                break;
        }
    }
}
