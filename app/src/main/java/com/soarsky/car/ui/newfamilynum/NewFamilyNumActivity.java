package com.soarsky.car.ui.newfamilynum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.FamilyNumIlistBean;
import com.soarsky.car.bean.QueryFamilyBean;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.data.local.db.bean.FamilyNum;
import com.soarsky.car.ui.family.familynum.FamilyNumActivity;
import com.soarsky.car.ui.family.familynumupdate.FamilyNumUpdateActivity;
import com.soarsky.car.ui.newfamilynum.Util.SlidingMenu;
import com.soarsky.car.ui.newfamilynum.add.NewFamilyNumAddActivity;
import com.soarsky.car.ui.newfamilynum.edit.NewFamilyNumEditActivity;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/5/12
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class NewFamilyNumActivity extends BaseActivity<NewFamilyNumPresent,NewFamilyNumModel> implements NewFamilyNumView,View.OnClickListener{


    private LinearLayout backLay;

    private LinearLayout phoneLay;

    private TextView topicTv;

    private SlidingMenu ll_content1,ll_content2,ll_content3;

    private EditText phoneOneEt,phoneTwoEt,phoneThreeEt;

    private TextView deleteOneTv,deleteTwoTv,deleteThreeTv;

    private TextView updateOneTv,updateTwoTv,updateThreeTv;

    private Button addFamilyNumBtn;

    private App app;

    private static final String TAG ="NewFamilyNumActivity";

    private int flag = -1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_family_num;
    }

    @Override
    public void initView() {
        app= (App)getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText("亲情号设置");

        ll_content1 = (SlidingMenu) findViewById(R.id.ll_content1);
        ll_content2 = (SlidingMenu) findViewById(R.id.ll_content2);
        ll_content3 = (SlidingMenu) findViewById(R.id.ll_content3);

        phoneOneEt = (EditText) findViewById(R.id.phoneOneEt);
        phoneTwoEt = (EditText) findViewById(R.id.phoneTwoEt);
        phoneThreeEt = (EditText) findViewById(R.id.phoneThreeEt);

        deleteOneTv = (TextView) findViewById(R.id.deleteOneTv);
        deleteOneTv.setOnClickListener(this);
        deleteTwoTv = (TextView) findViewById(R.id.deleteTwoTv);
        deleteTwoTv.setOnClickListener(this);
        deleteThreeTv = (TextView) findViewById(R.id.deleteThreeTv);
        deleteThreeTv.setOnClickListener(this);

        updateOneTv = (TextView) findViewById(R.id.updateOneTv);
        updateOneTv.setOnClickListener(this);

        updateTwoTv = (TextView) findViewById(R.id.updateTwoTv);
        updateTwoTv.setOnClickListener(this);

        updateThreeTv = (TextView) findViewById(R.id.updateThreeTv);
        updateThreeTv.setOnClickListener(this);

        addFamilyNumBtn = (Button) findViewById(R.id.addFamilyNumBtn);
        addFamilyNumBtn.setOnClickListener(this);

        phoneLay = (LinearLayout) findViewById(R.id.phoneLay);

    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        mPresenter.getLocalFamilyNumList(app.getCommonParam().getCarNum());

        flag = getIntent().getIntExtra("flag",-2);
        if(flag == 1) {
            mPresenter.deleteAll();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.addFamilyNumBtn:
                startActivity(new Intent(this, NewFamilyNumAddActivity.class));
                break;
            case R.id.updateOneTv:
                Intent i = new Intent();
                i.setClass(this, NewFamilyNumEditActivity.class);
                i.putExtra("phoneNum",phoneOneEt.getText().toString());
                startActivity(i);
                break;
            case R.id.updateTwoTv:
                Intent i1 = new Intent();
                i1.setClass(this, NewFamilyNumEditActivity.class);
                i1.putExtra("phoneNum",phoneTwoEt.getText().toString());
                startActivity(i1);
                break;
            case R.id.updateThreeTv:
                Intent i2 = new Intent();
                i2.setClass(this, NewFamilyNumEditActivity.class);
                i2.putExtra("phoneNum",phoneThreeEt.getText().toString());
                startActivity(i2);
                break;
            case R.id.deleteOneTv:
                break;
            case R.id.deleteTwoTv:
                break;
            case R.id.deleteThreeTv:
                break;

        }
    }

    @Override
    public void getLocalFamilyNumData(List<FamilyNum> familyNa) {

        if(familyNa != null){
            List<FamilyNum> familyNa1 = new ArrayList<FamilyNum>();
            for(FamilyNum familyNum:familyNa){
                if(familyNum.getIs_owner()==0){
                    familyNa1.add(familyNum);
                }
            }

            switch (familyNa1.size()){
                case 3:
                    addFamilyNumBtn.setVisibility(View.GONE);
                    phoneOneEt.setText(familyNa1.get(0).getPhone());
                    phoneTwoEt.setText(familyNa1.get(1).getPhone());
                    phoneThreeEt.setText(familyNa1.get(2).getPhone());
                    break;
                case 2:
                    ll_content3.setVisibility(View.GONE);
                    phoneOneEt.setText(familyNa1.get(0).getPhone());
                    phoneTwoEt.setText(familyNa1.get(1).getPhone());
                    break;
                case 1:
                    ll_content3.setVisibility(View.GONE);
                    ll_content2.setVisibility(View.GONE);
                    phoneOneEt.setText(familyNa1.get(0).getPhone());
                    break;
                case 0:
                    ll_content3.setVisibility(View.GONE);
                    ll_content2.setVisibility(View.GONE);
                    ll_content1.setVisibility(View.GONE);
                    phoneLay.setVisibility(View.GONE);
                    break;
            }

        }



    }

    @Override
    public void showFamilyFail() {

        mPresenter.getLocalFamilyNumList(app.getCommonParam().getCarNum());
    }

    @Override
    public void showFamilySuccess(ResponseDataBean<QueryFamilyBean> param) {
        if (param != null) {
            if (Constants.REQUEST_SUCCESS.equals(param.getStatus())){
                //插入数据库
                insertFamilyNum(param.getData().getIlist());
            }

            mPresenter.getLocalFamilyNumList(app.getCommonParam().getCarNum());
        }

    }

    /**
     * 插入数据
     *
     * @param list 数据源
     */
    private void insertFamilyNum(List<FamilyNumIlistBean> list) {
        for (FamilyNumIlistBean bean : list) {
            FamilyNum familyNum = new FamilyNum();
            familyNum.setPhone(bean.getPhone());
            familyNum.setUsername(app.getCommonParam().getUserName());
            familyNum.setCarnum(app.getCommonParam().getCarNum());
            familyNum.setIs_owner(bean.getIsOwner());
            familyNum.setSstate(1);
            familyNum.setTstate(0);
            mPresenter.insertFamilyNum(familyNum);
        }
    }

}
