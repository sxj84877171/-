package com.soarsky.installationmanual.ui.main.fragment.updatephone;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.installationmanual.Constants;
import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.ui.main.MainActivity;
import com.soarsky.installationmanual.ui.register.RegisterActivity;
import com.soarsky.installationmanual.util.SpUtil;
import com.soarsky.installationmanual.util.ToastUtil;
import com.soarsky.installationmanual.util.ValidatorUtils;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 修改手机号界面<br>
 */

public class UpdatePhoneActivity extends BaseActivity<UpdatePhonePresent,UpdatePhoneModel> implements UpdatePhoneView {
    private Button okBtn;
    private TextView jiu;
    private EditText xin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_update_phone;
    }

    @Override
    public void initView() {
        okBtn = (Button) findViewById(R.id.okBtn);
        jiu = (TextView) findViewById(R.id.jiuEt);
        xin = (EditText) findViewById(R.id.xinEt);
        String phone = getIntent().getStringExtra(Constants.PHONE);
        jiu.setText(phone);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jiuStr = jiu.getText().toString();
                String xinStr = xin.getText().toString();

                if(TextUtils.isEmpty(xinStr)){
                    ToastUtil.show(UpdatePhoneActivity.this,"手机号不能为空");
                    return;
                }
                if(!ValidatorUtils.validatorPhone(xinStr)){
                    ToastUtil.show(UpdatePhoneActivity.this,"手机号错误");
                    return;
                }

                mPresenter.modifyPhone(SpUtil.get(Constants.USERNAME),jiuStr,xinStr);

            }
        });
    }

    @Override
    protected String getHeaderTitle() {
        return "手机号码";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void showSuccess() {

        ToastUtil.show(UpdatePhoneActivity.this,"修改成功");
        finish();
    }
}
