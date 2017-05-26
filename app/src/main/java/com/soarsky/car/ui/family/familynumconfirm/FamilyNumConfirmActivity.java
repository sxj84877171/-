package com.soarsky.car.ui.family.familynumconfirm;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.ui.family.familynum.FamilyNumParam;
import com.soarsky.car.ui.family.familynum.FamilyNumSendParam;
import com.soarsky.car.uitl.ToastUtil;

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
 * 该类为 亲情号码更改
 */

public class FamilyNumConfirmActivity extends BaseActivity<FamilyNumConfirmPresent,FamilyNumConfirmModel> implements FamilyNumConfirmView,View.OnClickListener{
    /**
     * 返回
     */
    private LinearLayout backLay;

    private TextView topicTv;
    /**
     * 确认
     */
    private Button familyNumConfirmBtn;
    /**
     * 亲情号码
     */
    private EditText phoneConfirmOneEt;
    /**
     * 确认亲情号码
     */
    private EditText phoneConfirmTwoEt;
    /**
     * 更换号码的实体
     */
    private FamilyNumUpdateEntry phoneNum;

    private App app;

    public final static String TAG = "FamilyNumConfirmActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_family_num_confirm;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        topicTv = (TextView)findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.familynumberconfirm));

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        familyNumConfirmBtn = (Button) findViewById(R.id.familyNumConfirmBtn);
        familyNumConfirmBtn.setOnClickListener(this);

        phoneConfirmOneEt = (EditText) findViewById(R.id.phoneConfirmOneEt);
        phoneConfirmTwoEt = (EditText) findViewById(R.id.phoneConfirmTwoEt);

        phoneNum = (FamilyNumUpdateEntry)getIntent().getSerializableExtra("phoneNum");

    }

    @Override
    public void showSuccess(FamilyNumConfirmParam p) {

        if(p != null ){

            if("0".equals(p.getStatus())){
                /**
                 * 设置成功 进行本地数据库的更改
                 */
                mPresenter.getFamilyNumListByCarNumAndPhone(app.getCommonParam().getCarNum(),phoneNum.getPhoneNum());
            }else {
                ToastUtil.show(this,""+p.getMessage());
            }
        }
    }

    @Override
    public void showError() {

        ToastUtil.show(this,R.string.throwable);

    }

    /**
     * 设置亲情号码成功回调
     * @param familyNumParam
     */
    @Override
    public void setFamilyNumSuccess(FamilyNumParam familyNumParam) {

        if(familyNumParam != null){
            ToastUtil.show(this,familyNumParam.getMessage());
            if("0".equals(familyNumParam.getStatus())){

                FamilyNum familyNum = new FamilyNum();
                familyNum.setIs_owner(0);
                familyNum.setCarnum(app.getCommonParam().getCarNum());
                familyNum.setPhone(phoneConfirmOneEt.getText().toString());
                familyNum.setUsername(app.getCommonParam().getUserName());
                familyNum.setSstate(1);
                familyNum.setTstate(0);
                mPresenter.insertFamilyNum(familyNum);
            }
        }
    }

    /**
     * 设置亲情号码失败回调
     */
    @Override
    public void setFamilyNumFail() {

        ToastUtil.show(this,R.string.throwable);
    }

    /**
     * 插入数据成功回调
     */
    @Override
    public void insertFamilyNumSuccess() {

        ToastUtil.show(this,R.string.familynumupdatevictory);
        finish();
    }

    /**
     * 插入数据失败回调
     */
    @Override
    public void insertFamilyNumFail() {

        ToastUtil.show(this,R.string.familynumupdatewrong);
    }

    @Override
    public void getFamilyNumListByCarNumAndPhoneSuccess(List<FamilyNum> familyNa) {


        if(familyNa.size()>0){
            FamilyNum familyNum = familyNa.get(0);
            familyNum.setPhone(phoneConfirmOneEt.getText().toString());
            familyNum.setId(familyNa.get(0).getId());
            mPresenter.updateFamilyNumData(familyNum);
        }

    }

    @Override
    public void getFamilyNumListByCarNumAndPhoneFail() {

        ToastUtil.show(this,R.string.familynumupdatewrong);
    }

    @Override
    public void updateFamilyNumSuccess(FamilyNum familyNum) {
        ToastUtil.show(this,R.string.familynumupdatevictory);
        finish();
    }

    @Override
    public void updateFamilyNumFail() {
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
            case R.id.familyNumConfirmBtn:
                String newphone = phoneConfirmOneEt.getText().toString();
                String confirmPhone = phoneConfirmTwoEt.getText().toString();
                //校验手机号
                if(!TextUtils.isEmpty(newphone)) {
                    if (!isMobile(newphone)) {
                        ToastUtil.show(this, R.string.mobile_wrong);
                        return;
                    }
                }

                if(!TextUtils.isEmpty(confirmPhone)) {
                    if (!isMobile(confirmPhone)) {
                        ToastUtil.show(this, R.string.mobile_wrong);
                        return;
                    }
                }

                if(TextUtils.isEmpty(newphone) || TextUtils.isEmpty(confirmPhone)){

                    ToastUtil.show(this,R.string.modifyisEmpty);

                }else {
                    if(newphone.equals(confirmPhone)){

                        if(phoneNum.getPhoneNum() != null && !("".equals(phoneNum.getPhoneNum()))) {
                            FamilyNumConfirmSendParam p = new FamilyNumConfirmSendParam();
                            p.setUsername(app.getCommonParam().getUserName());
                            p.setCarnum(app.getCommonParam().getCarNum());
                            p.setPhone(phoneNum.getPhoneNum());
                            p.setNewPhone(newphone);
                            mPresenter.updateFirendPhone(p);
                        }else {
                            //设置亲情号码
                            FamilyNumSendParam param = new FamilyNumSendParam();
                            param.setCarnum(app.getCommonParam().getCarNum());

                            switch (phoneNum.getNum()){
                                case 1:
                                    param.setPhone1(newphone);
                                    param.setPhone2(""+phoneNum.getOtherPhone().getOtherPhoneOne());
                                    param.setPhone3(""+phoneNum.getOtherPhone().getGetOtherPhoneTwo());
                                    break;
                                case 2:
                                    param.setPhone1(""+phoneNum.getOtherPhone().getOtherPhoneOne());
                                    param.setPhone2(""+newphone);
                                    param.setPhone3(""+phoneNum.getOtherPhone().getGetOtherPhoneTwo());
                                    break;
                                case 3:
                                    param.setPhone1(""+phoneNum.getOtherPhone().getOtherPhoneOne());
                                    param.setPhone2(""+phoneNum.getOtherPhone().getGetOtherPhoneTwo());
                                    param.setPhone3(""+newphone);
                                    break;
                            }

                            param.setUsername(app.getCommonParam().getUserName());
                            mPresenter.setFirendPhone(param);
                        }

                    }else {
                        ToastUtil.show(this,R.string.familynumberwrong);
                    }

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


}
