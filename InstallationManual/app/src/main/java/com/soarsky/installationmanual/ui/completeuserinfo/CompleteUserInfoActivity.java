package com.soarsky.installationmanual.ui.completeuserinfo;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.installationmanual.Constants;
import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.ui.completeuserinfo.installpoint.InstallPointActivity;
import com.soarsky.installationmanual.ui.completeuserinfo.location.AreaActivity;
import com.soarsky.installationmanual.ui.completeuserinfo.vehicleadministration.VehicleAdministrationActivity;
import com.soarsky.installationmanual.ui.main.MainActivity;
import com.soarsky.installationmanual.ui.main.fragment.updatephone.UpdatePhoneActivity;
import com.soarsky.installationmanual.ui.register.RegisterActivity;
import com.soarsky.installationmanual.util.ToastUtil;


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
 * 该类为 完善用户信息界面<br>
 */

public class CompleteUserInfoActivity extends BaseActivity<CompleteUserInfoPresent,CompleteUserInfoModel> implements CompleteUserInfoView {
    /**
     * 真实姓名
     */
    private TextView name;
    /**
     * 性别
     */
    private TextView sex;
    /**
     * 电话
     */
    private TextView phone;
    /**
     * 身份证号
     */
    private TextView idCard;
    /**
     * 地点选择
     */
    private RelativeLayout location;
    /**
     * 车管所选择
     */
    private RelativeLayout cheguansuo;
    /**
     * 安装点选择
     */
    private RelativeLayout anzhuangdian;
    /**
     * 选择的城市代码
     */
    private String itemCode;
    /**
     * 选择的车管所id
     */
    private String cheguosuoId;
    /**
     * 选择的车管所代码
     */
    private String cheguosuoItemCode;
    /**
     * 选择的安装点id
     */
    private String installItemId;
    /**
     * 确认按钮
     */
    private Button okBtn;
    /**
     * 选择的地点
     */
    private TextView locationTv;
    /**
     * 车管所选择
     */
    private TextView cheguansuoTv;
    /**
     * 安装点选择
     */
    private TextView anzhuangdianTv;
    /**
     * 修改电话textview
     */
    private TextView updatePhoneTv;
    /**
     * 修改电话图片
     */
    private ImageView updatePhoneImg;
    /**
     * 修改电话
     */
    private RelativeLayout phoneRl;
    @Override
    public int getLayoutId() {
        return R.layout.activity_complete_user_info;
    }

    @Override
    public void initView() {
        okBtn = (Button) findViewById(R.id.okBtn);
        name = (TextView) findViewById(R.id.nameEt);
        sex = (TextView) findViewById(R.id.sexEt);
        phone = (TextView) findViewById(R.id.phoneEt);
        idCard = (TextView) findViewById(R.id.idCardEt);
        location = (RelativeLayout) findViewById(R.id.location);
        cheguansuo = (RelativeLayout) findViewById(R.id.cheguansuo);
        anzhuangdian = (RelativeLayout) findViewById(R.id.anzhuangdian);
        locationTv = (TextView) findViewById(R.id.locationTv);
        cheguansuoTv = (TextView) findViewById(R.id.cheguansuoTv);
        anzhuangdianTv = (TextView) findViewById(R.id.anzhuangdianTv);
        updatePhoneTv = (TextView) findViewById(R.id.updatePhoneTv);
        updatePhoneImg = (ImageView) findViewById(R.id.updatePhoneImg);
        phoneRl = (RelativeLayout) findViewById(R.id.phoneRl);
        final Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        sex.setText(intent.getStringExtra("sex"));
        phone.setText(intent.getStringExtra("phone"));
        idCard.setText(intent.getStringExtra("idCard"));
        String type = intent.getStringExtra("type");
        if("1".equals(type)){
            phone.setVisibility(View.VISIBLE);
            updatePhoneTv.setVisibility(View.GONE);
            updatePhoneImg.setVisibility(View.GONE);
        }else {
            phone.setVisibility(View.GONE);
            updatePhoneTv.setVisibility(View.VISIBLE);
            updatePhoneImg.setVisibility(View.VISIBLE);
            phoneRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(CompleteUserInfoActivity.this, UpdatePhoneActivity.class);
                    in.putExtra(Constants.PHONE,intent.getStringExtra("phone"));
                    startActivity(in);
                }
            });

        }
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompleteUserInfoActivity.this, AreaActivity.class));
            }
        });
        cheguansuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(itemCode)){
                    ToastUtil.show(CompleteUserInfoActivity.this,"请先选择地区");
                    return;
                }
                Intent in = new Intent(CompleteUserInfoActivity.this, VehicleAdministrationActivity.class);
                in.putExtra("itemCode",itemCode);
                startActivity(in);
            }
        });
        anzhuangdian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(itemCode)){
                    ToastUtil.show(CompleteUserInfoActivity.this,"请先选择地区");
                    return;
                }
                if(TextUtils.isEmpty(cheguosuoItemCode)){
                    ToastUtil.show(CompleteUserInfoActivity.this,"请先选择车管所");
                    return;
                }
                Intent in = new Intent(CompleteUserInfoActivity.this, InstallPointActivity.class);
                in.putExtra("itemCode",cheguosuoItemCode);
                startActivity(in);
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(locationTv.getText().toString()) || locationTv.getText().toString().equals("请选择")){
                    ToastUtil.show(CompleteUserInfoActivity.this,"请选择地区");
                    return;
                }
                if(TextUtils.isEmpty(cheguansuoTv.getText().toString()) || cheguansuoTv.getText().toString().equals("请选择")){
                    ToastUtil.show(CompleteUserInfoActivity.this,"请选择车管所");
                    return;
                }
                if(TextUtils.isEmpty(anzhuangdianTv.getText().toString()) || anzhuangdianTv.getText().toString().equals("请选择")){
                    ToastUtil.show(CompleteUserInfoActivity.this,"请选择安装点");
                    return;
                }
                String mSex = null;
                if(sex.getText().equals("男")){
                    mSex = "0";
                }else {
                    mSex = "1";
                }
                mPresenter.perfectUser(mSex,idCard.getText().toString(),itemCode,cheguosuoId,installItemId);
            }
        });
    }

    @Override
    protected String getHeaderTitle() {
        return "完善资料";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void showSuccess() {
        ToastUtil.show(CompleteUserInfoActivity.this,"提交成功");
        startActivity(new Intent(CompleteUserInfoActivity.this, MainActivity.class));
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String itemCodeStr = intent.getStringExtra("itemCode");
        String cheguosuoIdStr = intent.getStringExtra("cheguansuoId");
        String cheguosuoItemCodeStr = intent.getStringExtra("cheguansuoItemCode");
        String installItemIdStr = intent.getStringExtra("installItemId");
        String cheguansuoItemName = intent.getStringExtra("cheguansuoItemName");
        String installName = intent.getStringExtra("installName");
        String itemName = intent.getStringExtra("itemName");
        if(!TextUtils.isEmpty(itemCodeStr)){
            itemCode = itemCodeStr;
            locationTv.setText(itemName);
        }
        if(!TextUtils.isEmpty(cheguosuoIdStr)){
            cheguosuoId = cheguosuoIdStr;
        }
        if(!TextUtils.isEmpty(cheguosuoItemCodeStr)){
            cheguosuoItemCode = cheguosuoItemCodeStr;
            cheguansuoTv.setText(cheguansuoItemName);
        }
        if(!TextUtils.isEmpty(installItemIdStr)){
            installItemId = installItemIdStr;
            anzhuangdianTv.setText(installName);
        }
    }
}
