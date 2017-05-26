package com.soarsky.car.ui.modifypwd;

import android.app.ProgressDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.ModifyPwdInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.uitl.ToastUtil;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：修改密码<br>
 * 该类为 ModifyPwdActivity<br>
 */

public class ModifyPwdActivity extends BaseActivity<ModifyPwdPresent,ModifyPwdModel> implements ModifyPwdView,View.OnClickListener{

    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     *  当前密码
     */
    private EditText modifyCurrentpwdEt;
    /**
     * 当前密码lay
     */
    private RelativeLayout modifyCurrentpwdLay;
    /**
     * icon
     */
    private ImageView modifyCurrentpwdView;
    /**
     * 新密码
     */
    private EditText modifyNowpwdEt;
    /**
     * 新密码Lay
     */
    private RelativeLayout modifyNowpwdLay;
    /**
     * 新密码View
     */
    private ImageView modifyNowpwdView;
    /**
     * 确认密码
     */
    private EditText modifyConfirmpwdEt;
    /**
     * 确认密码lay
     */
    private RelativeLayout modifyConfirmpwdLay;
    /**
     * 确认密码View
     */
    private ImageView modifyConfirmpwdView;
    /**
     * 修改
     */
    private Button modifyBtn;
    /**
     * 当前是否可见
     */
    private boolean isCurrent = false;
    /**
     * 新密码是否可见
     */
    private boolean isNow = false;
    /**
     * 确认密码是否可见
     */
    private boolean isConfirm = false;
    /**
     * application
     */
    private App app;
    /**
     * 进度
     */
    private ProgressDialog progressDialog;
    /**
     * 该类的key
     */
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
        return getString(R.string.modifytitle);
    }

    @Override
    public void showProgess() {

        super.showProgess();
    }

    @Override
    public void stopProgess() {

        super.stopProgess();
    }

    /**
     * 修改成功
     * @param param 参数
     */
    @Override
    public void showData(ResponseDataBean<ModifyPwdInfo> param) {

        if(param != null) {
            if(Constants.REQUEST_SUCCESS.equals(param.getStatus())){
                ToastUtil.show(this, getString(R.string.change_success));
                finish();
            }else if (Constants.CURRENT_PWD_ERROE.equals(param.getStatus())){
                ToastUtil.show(this,R.string.check_current_pwd);
            }else {
                ToastUtil.show(this,R.string.modify_pwd_fail);
            }

        }else {
            ToastUtil.show(this,R.string.error);
        }


    }

    /**
     * 修改失败
     */
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

                if((modifyNowpwd.length()<6||modifyNowpwd.length()>12)){
                    ToastUtil.show(this,R.string.modifyisTip);
                    return;
                }

                if((modifyConfirmpwd.length()<6||modifyConfirmpwd.length()>12)){
                    ToastUtil.show(this,R.string.modifyisTip);
                    return;
                }

                if(modifyNowpwd.equals(modifyConfirmpwd)) {
                    if (TextUtils.isEmpty(currentPwd) || TextUtils.isEmpty(modifyNowpwd) || TextUtils.isEmpty(modifyConfirmpwd)) {

                        ToastUtil.show(this, R.string.modifyisEmpty);

                    }else if (currentPwd.equals(modifyNowpwd)){
                        ToastUtil.show(this, R.string.new_old_same);
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
