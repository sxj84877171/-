package com.soarsky.car.ui.borrowandreturn.retur.returnsuccess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.DetailsInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.borrowandreturn.recorddetails.RecordDetailsMedol;
import com.soarsky.car.ui.borrowandreturn.recorddetails.RecordDetailsPresent;
import com.soarsky.car.ui.borrowandreturn.recorddetails.RecordDetailsView;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/2<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  还车成功显示页面<br>
 */

public class ReturnSuccessActivity extends BaseActivity<RecordDetailsPresent,RecordDetailsMedol> implements RecordDetailsView,View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回按钮
     */
    private ImageView backView;
    /**
     * 返回按钮
     */
    private LinearLayout backLay;
    /**
     * 车辆类型
     */
    private TextView carType;
    /**
     * 借用车牌
     */
    private TextView carNum;
    /**
     * 车主姓名
     */
    private TextView hostName;
    /**
     * 车主电话
     */
    private TextView hostNum;
    /**
     * 开始时间
     */
    private TextView startTime;
    /**
     * 结束时间
     */
    private TextView endTime;
    /**
     * 借车记录的id
     */
    private Integer id;

    /**
     * 返回要显示的布局
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.activity_return_success;
    }

    /**
     * 初始化控件
     */
    @Override
    public void initView() {
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(R.string.i_return_button);

        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        carType = (TextView) findViewById(R.id.carType);
        carNum = (TextView) findViewById(R.id.carNum);
        hostName = (TextView) findViewById(R.id.hostName);
        hostNum = (TextView) findViewById(R.id.hostNum);
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
        id = bundle.getInt("bId");

    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 点击事件触发的方法
     * @param view  点击的控件
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
        }
    }

    @Override
    public void showSuccess(ResponseDataBean<DetailsInfo> detailParm) {
        carType.setText(detailParm.getData().getModel());
        carNum.setText(detailParm.getData().getCarnum());
        hostName.setText(detailParm.getData().getOwnerUser().getUserName());
        hostNum.setText(detailParm.getData().getOwnerUser().getPhone());
        startTime.setText(detailParm.getData().getStime());
        endTime.setText(detailParm.getData().getEtime());
    }

    @Override
    public void showFail(ResponseDataBean<DetailsInfo> detailParm) {

    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getDetails(id);
    }
}
