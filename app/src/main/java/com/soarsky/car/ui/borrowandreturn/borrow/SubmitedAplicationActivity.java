package com.soarsky.car.ui.borrowandreturn.borrow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.borrowandreturn.BorrowCarAndReturnActivity;

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
 * 该类为  已提交的借车申请页面
 */

public class SubmitedAplicationActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回
     */
    private ImageView backView;
    /**
     * 借用车牌
     */
    private TextView carNum;
    /**
     * 车主电话
     */
    private TextView phoneNum;
    /**
     * 开始时间
     */
    private TextView startTime;
    /**
     * 结束时间
     */
    private TextView endTime;

    /**
     * 返回要展示的布局文件
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.submit_aplication;
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

        carNum = (TextView) findViewById(R.id.carNum);
        phoneNum = (TextView) findViewById(R.id.phoneNum);
        startTime = (TextView) findViewById(R.id.startTime);
        endTime = (TextView) findViewById(R.id.endTime);
        //填充数据
        initData();
    }

    /**
     * 填充数据
     */
    private void initData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据
        carNum.setText(bundle.getString("carNum"));
        phoneNum.setText(bundle.getString("ownerPhone"));
        startTime.setText(bundle.getString("startTime"));
        endTime.setText(bundle.getString("endTime"));
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 点击事件重写的方法
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backView:
                finish();
                startActivity(new Intent(SubmitedAplicationActivity.this, BorrowCarAndReturnActivity.class));
                break;
        }
    }
}
