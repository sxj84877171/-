package com.soarsky.car.ui.family.familynum;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.CommonParam;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.ui.family.familynumupdate.FamilyNumUpdateActivity;
import com.soarsky.car.ui.home.fragment.personal.QueryFamilyParam;
import com.soarsky.car.ui.register.RegisterActivity;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
 * 该类为 亲情号设置
 */

public class FamilyNumActivity extends BaseActivity<FamilyNumPresent,FamilyNumModel> implements FamilyNumView,View.OnClickListener{
    /**
     * 返回
     */
    private LinearLayout backLay;

    private TextView topicTv;
    /**
     * 亲情号码一
     */
    private EditText phoneOneEt;
    /**
     * 亲情号码二
     */
    private EditText phoneTwoEt;
    /**
     * 亲情号码三
     */
    private EditText phoneThreeEt;
    /**
     * 确定
     */
    private Button familyNumBtn;

    private String phone1 ="";

    private String phone2 = "";

    private String phone3 = "";

    private App app;

    public final static String TAG = "FamilyNumActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_family_num;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.familynumtitle));

        phoneOneEt = (EditText) findViewById(R.id.phoneOneEt);
        phoneTwoEt = (EditText) findViewById(R.id.phoneTwoEt);
        phoneThreeEt = (EditText) findViewById(R.id.phoneThreeEt);

        familyNumBtn = (Button) findViewById(R.id.familyNumBtn);
        familyNumBtn.setOnClickListener(this);

    }

    @Override
    public void showSuccess(FamilyNumParam param) {

        if(param != null){


            if("0".equals(param.getStatus())){

                List<String> list = new ArrayList<String>();
                list.add(phoneOneEt.getText().toString());
                list.add(phoneTwoEt.getText().toString());
                list.add(phoneThreeEt.getText().toString());
                insertFamilyNum(list);

                finish();

            }else{
                ToastUtil.show(this,param.getMessage());
            }

        }
    }

    @Override
    public void showError() {

        ToastUtil.show(this,R.string.throwable);
    }

    @Override
    public void insertSuccess() {


    }

    @Override
    public void insertFail() {
        ToastUtil.show(this,R.string.familynumupdatewrong);
    }



    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.familyNumBtn:

                if(app.isOnline() == false) {
                    ToastUtil.show(this,R.string.personaltip);
                    return;
                }
                phone1 = phoneOneEt.getText().toString();
                phone2 = phoneTwoEt.getText().toString();
                phone3 = phoneThreeEt.getText().toString();


                if(!TextUtils.isEmpty(phone1)||!TextUtils.isEmpty(phone2) || !TextUtils.isEmpty(phone3)) {

                    //校验手机号
                    if(!TextUtils.isEmpty(phone1)) {
                        if (!isMobile(phone1)) {
                            ToastUtil.show(this, R.string.mobile_wrong);
                            return;
                        }
                    }
                    if(!TextUtils.isEmpty(phone2)) {
                        if (!isMobile(phone2)) {
                            ToastUtil.show(this, R.string.mobile_wrong);
                            return;
                        }
                    }

                    if(!TextUtils.isEmpty(phone3)) {
                        if (!isMobile(phone3)) {
                            ToastUtil.show(this, R.string.mobile_wrong);
                            return;
                        }
                    }

                    String owerPhone = app.getCommonParam().getOwerPhone();

                    if(phone1.equals(owerPhone)||phone2.equals(owerPhone)||phone3.equals(owerPhone)){
                        ToastUtil.show(this,R.string.phone_ower_tip);
                        return;
                    }
                    FamilyNumSendParam p = new FamilyNumSendParam();
                    p.setPhone1(phone1);
                    p.setPhone2(phone2);
                    p.setPhone3(phone3);
                    p.setCarnum(app.getCommonParam().getCarNum());
                    p.setUsername(""+app.getCommonParam().getUserName());
                    mPresenter.setFirendPhone(p);
                }else{

                    ToastUtil.show(this,R.string.modifyisEmpty);
                }


                break;
        }
    }

    /**
     * 校验手机号
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public  boolean isMobile(String mobile) {
        String REGEX_MOBILE = "^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 插入数据库
     * @param list
     */
    private void insertFamilyNum(List<String> list){
        for (String phone:list){
            FamilyNum familyNum = new FamilyNum();
            familyNum.setPhone(phone);
            familyNum.setUsername(app.getCommonParam().getUserName());
            familyNum.setCarnum(app.getCommonParam().getCarNum());
            familyNum.setIs_owner(0);
            familyNum.setSstate(1);
            familyNum.setTstate(0);
            mPresenter.insertFamilyNum(familyNum);
        }
    }
}
