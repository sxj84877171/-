package com.soarsky.car.ui.borrowandreturn.recorddetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
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
 * 该类为  借车记录详情页
 */

public class RecordDetailsActivity extends BaseActivity<RecordDetailsPresent,RecordDetailsMedol> implements RecordDetailsView,View.OnClickListener {
    private TextView topicTv;
    private ImageView backView;
    /**
     * 车牌号
     */
    private TextView carNum;
    /**
     * 车辆类型
     */
    private TextView type;
    /**
     * 姓名
     */
    private TextView name;
    /**
     * 驾照号
     */
    private TextView idNum;
    /**
     * 联系方式
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
     * 归还时间
     */
    private TextView returnTime;
    private Integer bid;
    private App app;
    private String appPhone;

    /**
     * 返回要改页面展示的布局
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.activity_record_detail;
    }

    /**
     * 初始化控件
     */
    @Override
    public void initView() {

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(R.string.record_details);

        backView = (ImageView) findViewById(R.id.backView);
        backView.setOnClickListener(this);

        carNum = (TextView) findViewById(R.id.carNum);
        type = (TextView) findViewById(R.id.type);
        name = (TextView) findViewById(R.id.name);
        idNum = (TextView) findViewById(R.id.idNum);
        phoneNum = (TextView) findViewById(R.id.phoneNum);
        startTime = (TextView) findViewById(R.id.startTime);
        endTime = (TextView) findViewById(R.id.endTime);
        returnTime = (TextView) findViewById(R.id.returnTime);

        app = (App) getApplication();
        appPhone = app.getCommonParam().getOwerPhone();

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

    /**
     * 请求成功时触发的方法
     * @param detailParm
     */
    @Override
    public void showSuccess(DetailsParm detailParm) {
        if (appPhone.equals(detailParm.getData().getBorrow())){
            name.setText(detailParm.getData().getOwnerUser().getUserName());
            idNum.setText(detailParm.getData().getOwnerUser().getIdcard());
            phoneNum.setText(detailParm.getData().getOwnerUser().getPhone());

        }else {
            name.setText(detailParm.getData().getAppuser().getUserName());
            idNum.setText(detailParm.getData().getAppuser().getIdcard());
            phoneNum.setText(detailParm.getData().getAppuser().getPhone());
        }
        carNum.setText(detailParm.getData().getCarnum());
        type.setText(detailParm.getData().getModel());
        startTime.setText(detailParm.getData().getStime());
        endTime.setText(detailParm.getData().getEtime());
        returnTime.setText(detailParm.getData().getRtime());

    }

    @Override
    public void showFail(DetailsParm detailParm) {
        ToastUtil.show(RecordDetailsActivity.this,detailParm.getMessage());
    }

    @Override
    public void showError(Throwable e) {

    }

    /**
     * 启动当前类时调用的方法
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        bid= bundle.getInt("bId");
        mPresenter.getDetails(bid);

    }


}


