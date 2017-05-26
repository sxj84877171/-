package com.soarsky.car.ui.family.familynumupdate;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.ui.family.familynum.FamilyNumEntry;
import com.soarsky.car.ui.family.familynumconfirm.FamilyNumConfirmActivity;
import com.soarsky.car.ui.family.familynumconfirm.FamilyNumUpdateEntry;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

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
 * 该类为 展示亲情号码已存在
 */

public class FamilyNumUpdateActivity extends BaseActivity<FamilyNumUpdatePresent,FamilyNumUpdateModel> implements FamilyNumUpdateView,View.OnClickListener{
    /**
     * 第一个号码
     */
    private EditText phoneUpdateOneEt;
    /**
     * 第二个号码
     */
    private EditText phoneUpdateTwoEt;
    /**
     * 第三个号码
     */
    private EditText phoneUpdateThreeEt;
    /**
     * 第一个号码更改
     */
    private Button phoneUpdateOneBtn;
    /**
     * 第二个号码更改
     */
    private Button phoneUpdateTwoBtn;
    /**
     * 第三个号码更改
     */
    private Button phoneUpdateThreeBtn;
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 传递确认修改界面的实体数据
     */
    private FamilyNumEntry phone;
    /**
     * 应用
     */
    private App app;

    public final static String TAG = "FamilyNumUpdateActivity";


    @Override
    public int getLayoutId() {
        return R.layout.activity_family_num_update;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        topicTv = (TextView)findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.familynumtitle));

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        phoneUpdateOneEt = (EditText) findViewById(R.id.phoneUpdateOneEt);
        phoneUpdateOneEt.setEnabled(false);

        phoneUpdateTwoEt = (EditText) findViewById(R.id.phoneUpdateTwoEt);
        phoneUpdateTwoEt.setEnabled(false);

        phoneUpdateThreeEt = (EditText) findViewById(R.id.phoneUpdateThreeEt);
        phoneUpdateThreeEt.setEnabled(false);

        phoneUpdateOneBtn = (Button) findViewById(R.id.phoneUpdateOneBtn);
        phoneUpdateOneBtn.setOnClickListener(this);
        phoneUpdateTwoBtn = (Button) findViewById(R.id.phoneUpdateTwoBtn);
        phoneUpdateTwoBtn.setOnClickListener(this);
        phoneUpdateThreeBtn = (Button) findViewById(R.id.phoneUpdateThreeBtn);
        phoneUpdateThreeBtn.setOnClickListener(this);



    }

    @Override
    protected void onStart() {
        super.onStart();

        mPresenter.getAllFamilyNumList();
    }


    /**
     * 获取所有亲情号码表数据成功
     * @param list
     */
    @Override
    public void showSuccess(List<FamilyNum> list) {

        updateData(list);

    }

    /***
     * 获取所有亲情号码表失败
     */
    @Override
    public void showFails() {

        ToastUtil.show(this,R.string.familynumupdatewrong);
    }

    /**
     * 展示获取亲情号码数据
     * @param _list
     */
    public void updateData(List<FamilyNum> _list){

        Log.d("TAG","_list.size=="+_list.size());
        List<FamilyNum> list = new ArrayList<FamilyNum>();
        for (FamilyNum familyNum:_list){
            if (familyNum.getIs_owner() == 0){
                list.add(familyNum);
            }
        }

        if(list != null){
            if(list.size()>=3){
                phoneUpdateOneEt.setText(""+list.get(0).getPhone()==null?"":list.get(0).getPhone());
                phoneUpdateTwoEt.setText(""+list.get(1).getPhone()==null?"":list.get(1).getPhone());
                phoneUpdateThreeEt.setText(""+list.get(2).getPhone()==null?"":list.get(2).getPhone());
            }else if(list.size()==2){
                phoneUpdateOneEt.setText(""+list.get(0).getPhone()==null?"":list.get(0).getPhone());
                phoneUpdateTwoEt.setText(""+list.get(1).getPhone()==null?"":list.get(1).getPhone());
            }else if(list.size() == 1){
                phoneUpdateOneEt.setText(""+list.get(0).getPhone()==null?"":list.get(0).getPhone());
            }
        }





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
            case R.id.phoneUpdateOneBtn:
                StartToActivity(1);
                break;
            case R.id.phoneUpdateTwoBtn:
                StartToActivity(2);
                break;
            case R.id.phoneUpdateThreeBtn:
                StartToActivity(3);
                break;
        }
    }

    /**
     * 跳转activity的封装
     * @param which
     */
    public void StartToActivity(int which){

        switch (which){
            case 1:
                FamilyNumUpdateEntry entry = new FamilyNumUpdateEntry();
                entry.setPhoneNum(""+phoneUpdateOneEt.getText().toString());
                entry.setNum(1);
                FamilyNumUpdateEntry.OtherPhone otherPhone = new FamilyNumUpdateEntry.OtherPhone();
                otherPhone.setOtherPhoneOne(""+phoneUpdateTwoEt.getText().toString());
                otherPhone.setGetOtherPhoneTwo(""+phoneUpdateThreeEt.getText().toString());
                entry.setOtherPhone(otherPhone);
                Intent i = new Intent();
                i.setClass(this, FamilyNumConfirmActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("phoneNum",entry);
                i.putExtras(b);
                startActivity(i);
                break;
            case 2:
                FamilyNumUpdateEntry entry1 = new FamilyNumUpdateEntry();
                entry1.setPhoneNum(""+phoneUpdateTwoEt.getText().toString());
                entry1.setNum(2);
                FamilyNumUpdateEntry.OtherPhone otherPhone1 = new FamilyNumUpdateEntry.OtherPhone();
                otherPhone1.setOtherPhoneOne(""+phoneUpdateOneEt.getText().toString());
                otherPhone1.setGetOtherPhoneTwo(""+phoneUpdateThreeEt.getText().toString());
                entry1.setOtherPhone(otherPhone1);
                Intent in = new Intent();
                in.setClass(this, FamilyNumConfirmActivity.class);
                Bundle b1 = new Bundle();
                b1.putSerializable("phoneNum",entry1);
                in.putExtras(b1);
                startActivity(in);
                break;
            case 3:
                FamilyNumUpdateEntry entry2 = new FamilyNumUpdateEntry();
                entry2.setPhoneNum(""+phoneUpdateThreeEt.getText().toString());
                entry2.setNum(3);
                FamilyNumUpdateEntry.OtherPhone otherPhone2 = new FamilyNumUpdateEntry.OtherPhone();
                otherPhone2.setOtherPhoneOne(""+phoneUpdateOneEt.getText().toString());
                otherPhone2.setGetOtherPhoneTwo(""+phoneUpdateTwoEt.getText().toString());
                entry2.setOtherPhone(otherPhone2);
                Intent it = new Intent();
                it.setClass(this, FamilyNumConfirmActivity.class);
                Bundle b2 = new Bundle();
                b2.putSerializable("phoneNum",entry2);
                it.putExtras(b2);
                startActivity(it);
                break;
        }
    }
}
