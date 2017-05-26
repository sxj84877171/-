package com.soarsky.car.ui.illegal.detail;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.ViolationDealIlist;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/2/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：违章详情<br>
 * 该类为 IllegalDetailActivity<br>
 */


public class IllegalDetailActivity extends BaseActivity<IllegalDetailPresent,IllegalDetailModel> implements IllegalDetailView,View.OnClickListener{

    /**
     * 违章时间
     */
    private TextView detailTimeTv;
    /**
     * 违章地点
     */
    private TextView detailAddrTv;
    /**
     * 扣分
     */
    private TextView detailCentTv;
    /**
     * 罚款金额
     */
    private TextView detailMoneyTv;
    /**
     * 违章原因
     */
    private TextView detailReasonTv;
    /**
     * 审核结果
     */
    private TextView detailResultTv;
    /**
     * 结果原因
     */
    private TextView detailReasonRTv;

    private LinearLayout backLay;

    private TextView closeTv;

    private App app;

    private final static String TAG = "IllegalDetailActivity";

    private ViolationDealIlist bean;


    @Override
    public int getLayoutId() {
        return R.layout.activity_illegal_detail;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);
        closeTv = (TextView) findViewById(R.id.closeTv);
        closeTv.setOnClickListener(this);


        detailTimeTv = (TextView) findViewById(R.id.detailTimeTv);
        detailAddrTv = (TextView) findViewById(R.id.detailAddrTv);
        detailCentTv = (TextView)findViewById(R.id.detailCentTv);

        detailMoneyTv = (TextView)findViewById(R.id.detailMoneyTv);
        detailReasonTv = (TextView)findViewById(R.id.detailReasonTv);
        detailResultTv = (TextView)findViewById(R.id.detailResultTv);
        detailReasonRTv = (TextView)findViewById(R.id.detailReasonRTv);

    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bean = (ViolationDealIlist) getIntent().getSerializableExtra("bean");

        if(bean != null){
            detailTimeTv.setText(""+bean.getStime());
            detailAddrTv.setText(""+bean.getAddress());
            detailCentTv.setText(""+bean.getCent());
            detailMoneyTv.setText(""+bean.getMonery());
            detailReasonTv.setText(""+bean.getInf());
            if(bean.getStatus().equals("5")){
                detailResultTv.setText("撤销中");
                detailReasonRTv.setText("撤销中");
            }else if(bean.getStatus().equals("6")){
                detailResultTv.setText("已撤销");
                detailReasonRTv.setText("已撤销");
            }

        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
            case R.id.closeTv:
                finish();
                break;
        }
    }
}
