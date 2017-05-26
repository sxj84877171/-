package com.soarsky.car.ui.borrowandreturn;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.borrowandreturn.borrow.IWantBorrowActivity;
import com.soarsky.car.ui.borrowandreturn.borrowrecord.BorrowRecordActivity;
import com.soarsky.car.ui.borrowandreturn.retur.IWantReturnActivity;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/11/28
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  借车还车页面
 */

public class BorrowCarAndReturnActivity extends BaseActivity implements View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回按钮
     */
    private ImageView backView;
    /**
     * 我要借车
     */
    private ImageView borrowCarTv;
    /**
     * 我要还车
     */
    private ImageView returnCarTv;
    /**
     * 借车记录
     */
    private TextView tv_right;

    /**
     * 返回当前页面要展示的布局
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.borrow_car_and_return;
    }

    /**
     * 初始控件
     */
    @Override
    public void initView() {
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(R.string.borrow_car_and_return);

        tv_right = (TextView) findViewById(R.id.tv_right);
        tv_right.setText(R.string.borrow_car_and_return_right);
        tv_right.setOnClickListener(this);

        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);

        borrowCarTv = (ImageView) findViewById(R.id.borrowCarTv);
        borrowCarTv.setOnClickListener(this);

        returnCarTv = (ImageView) findViewById(R.id.returnCarTv);
        returnCarTv.setOnClickListener(this);
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
        switch (view.getId()){
            case R.id.backView:
                finish();
            break;
            case R.id.borrowCarTv:
                startActivity(new Intent(mContext, IWantBorrowActivity.class));
                break;
            case R.id.returnCarTv:
                startActivity(new Intent(mContext, IWantReturnActivity.class));
                break;
            case R.id.tv_right:
                startActivity(new Intent(mContext, BorrowRecordActivity.class));
                break;
        }
    }
}
