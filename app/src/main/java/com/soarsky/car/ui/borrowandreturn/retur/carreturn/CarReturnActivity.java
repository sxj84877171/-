package com.soarsky.car.ui.borrowandreturn.retur.carreturn;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/2
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  车辆已归还页面
 */

public class CarReturnActivity extends BaseActivity implements View.OnClickListener {
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
    private TextView carType;
    /**
     * 借用车牌
     */
    private TextView carNum;
    /**
     * 借车人姓名
     */
    private TextView borrowerName;
    /**
     * 借车人电话
     */
    private TextView borrower_phoneNum;
    /**
     * 借车人驾照
     */
    private TextView borrowerIdNum;
    /**
     * 开始时间
     */
    private TextView startTime;
    /**
     * 结束时间
     */
    private TextView endTime;

    /**
     * 返回要显示的布局
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.activity_car_returned;
    }

    /**
     * 初始化控件
     */
    @Override
    public void initView() {
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(R.string.car_return);

        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);

        carType = (TextView) findViewById(R.id.carType);
        carNum = (TextView) findViewById(R.id.carNum);
        borrowerName = (TextView) findViewById(R.id.borrowerName);
        borrower_phoneNum = (TextView) findViewById(R.id.borrower_phoneNum);
        borrowerIdNum = (TextView) findViewById(R.id.borrowerIdNum);
        startTime = (TextView) findViewById(R.id.startTime);
        endTime = (TextView) findViewById(R.id.endTime);

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
        }
    }
}
