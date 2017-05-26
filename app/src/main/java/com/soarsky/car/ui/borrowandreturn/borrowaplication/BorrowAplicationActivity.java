package com.soarsky.car.ui.borrowandreturn.borrowaplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.borrowandreturn.ModifyStatusParm;
import com.soarsky.car.ui.borrowandreturn.borrow.AgreeBorrowActivity;
import com.soarsky.car.ui.borrowandreturn.borrowaplication.refuse.RefuseCauseActivity;
import com.soarsky.car.ui.borrowandreturn.recorddetails.DetailsParm;
import com.soarsky.car.uitl.ToastUtil;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/1
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  收到的借车申请页面
 */

public class BorrowAplicationActivity extends BaseActivity<BorrowAplicationPresent,BorrowAplicationMedol> implements BorrowAplicationView ,View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回
     */
    private ImageView backView;
    /**
     * 车辆类型
     */
    private TextView type;
    /**
     * 借用车牌
     */
    private TextView carNum;
    /**
     * 借车人姓名
     */
    private TextView borrower_name;
    /**
     * 借车人电话
     */
    private TextView phone;
    /**
     * 借车人驾照
     */
    private TextView licen_num;
    /**
     * 开始时间
     */
    private TextView tv_startTime;
    /**
     * 结束时间
     */
    private TextView tv_endTime;
    private Button bt_refuse;
    private Button bt_agree;
    private String carType;
    private String car_num;
    private String name;
    private String phoneNum;
    private String stime;
    private String etime;
    private Integer bid;
    private String car_num1;

    /**
     * 返回要展示的布局
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.borrow_car_application;
    }

    /**
     * 初始化控件
     */
    @Override
    public void initView() {
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(R.string.borrow_aplication);

        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);

        type = (TextView) findViewById(R.id.type);
        carNum = (TextView) findViewById(R.id.carNum);
        borrower_name = (TextView) findViewById(R.id.borrower_name);
        phone = (TextView) findViewById(R.id.phone);
        licen_num = (TextView) findViewById(R.id.licen_num);
        tv_startTime = (TextView) findViewById(R.id.tv_startTime);
        tv_endTime = (TextView) findViewById(R.id.tv_endTime);

        bt_refuse = (Button) findViewById(R.id.bt_refuse);
        bt_refuse.setOnClickListener(this);

        bt_agree = (Button) findViewById(R.id.bt_agree);
        bt_agree.setOnClickListener(this);

    }


    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 点击事件触发的方法
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backView:
                finish();
                break;
            case R.id.bt_refuse:
                Intent i=new Intent();
                i.setClass(BorrowAplicationActivity.this, RefuseCauseActivity.class);//从一个activity跳转到另一个activity
                i.putExtra("Num",car_num1);
                i.putExtra("id",bid);
                i.putExtra("name",App.getApp().getCommonParam().getUserName());

                startActivity(i);
                finish();
                break;
            case R.id.bt_agree:


                mModel.setBid(bid);
                mModel.setCarnum(car_num1);
                mModel.setUsername(App.getApp().getCommonParam().getUserName());
                mPresenter.agree();

                break;
        }
    }

    /**
     * 系统的onStart()方法
     */
    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        bid = bundle.getInt("bId");
        mPresenter.getDetails(bid);


    }

    @Override
    public void showError() {

    }

    @Override
    public void showSuccess(ModifyStatusParm modifyStatusParm) {

    }

    @Override
    public void showFail(ModifyStatusParm modifyStatusParm) {
        ToastUtil.show(BorrowAplicationActivity.this,modifyStatusParm.getMessage());
    }

    /**
     * 同意借车申请
     * @param modifyStatusParm
     */
    @Override
    public void agree(ModifyStatusParm modifyStatusParm) {
        Intent intent=new Intent();
        intent.setClass(BorrowAplicationActivity.this, AgreeBorrowActivity.class);
        intent.putExtra("bId",bid);
        startActivity(intent);
        finish();
    }

    /**
     * 借车申请数据的展示
     * @param detailParm
     */
    @Override
    public void showSuccess(DetailsParm detailParm) {
        car_num1 = detailParm.getData().getCarnum();
        type.setText(detailParm.getData().getModel());
        carNum.setText(car_num1);
        borrower_name.setText(detailParm.getData().getAppuser().getUserName());
        phone.setText(detailParm.getData().getAppuser().getPhone());
        licen_num.setText(detailParm.getData().getAppuser().getIdcard());
        tv_startTime.setText(detailParm.getData().getStime());
        tv_endTime.setText(detailParm.getData().getEtime());
    }

    @Override
    public void showFail(DetailsParm detailParm) {
        ToastUtil.show(BorrowAplicationActivity.this,detailParm.getMessage());
    }

    @Override
    public void showError(Throwable e) {
        ToastUtil.show(BorrowAplicationActivity.this,e.getMessage());
    }
}
