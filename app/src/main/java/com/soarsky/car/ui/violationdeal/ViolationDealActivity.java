package com.soarsky.car.ui.violationdeal;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.uitl.TimeUtils;
import com.soarsky.car.uitl.ToastUtil;

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
 * 程序功能：违章处理
 * 该类为
 */

public class ViolationDealActivity extends BaseActivity<ViolationDealPresent,ViolationDealModel> implements ViolationDealView,View.OnClickListener{

    /**
     * 违章列表
     */
    private ListView deal_listView;
    /**
     * 一键处理
     */
    private Button violationDealBtn;

    private TextView topicTv;
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 后台请求更新时间
     */
    private TextView violationDealTimeTv;
    /**
     * 罚款
     */
    private TextView dealFineTv;
    /**
     * 未处理的违章
     */
    private TextView dealNotTv;
    /**
     * 记分
     */
    private TextView dealCentTv;
    /**
     * 适配器
     */
    private ViolationDealAdapter adapter;

    private App app;

    public final static String TAG = "ViolationDealActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_violation_deal;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        deal_listView = (ListView) findViewById(R.id.deal_listView);
        violationDealBtn = (Button) findViewById(R.id.violationDealBtn);
        violationDealBtn.setOnClickListener(this);
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(app.getCommonParam().getCarNum()==null?"":app.getCommonParam().getCarNum());

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        violationDealTimeTv = (TextView)findViewById(R.id.violationDealTimeTv);
        dealFineTv = (TextView) findViewById(R.id.dealFineTv);
        dealNotTv = (TextView) findViewById(R.id.dealNotTv);
        dealCentTv = (TextView) findViewById(R.id.dealCentTv);

    }

    @Override
    protected void onResume() {
        super.onResume();
        requestViolationDeal();
    }

    //从后台获取违章信息
    public void requestViolationDeal(){
        String carNum = App.getApp().getCommonParam().getCarNum() ;
        if(carNum != null && !"".equals(carNum.trim())) {
            ViolationDealSendParam p = new ViolationDealSendParam();
            p.setCarnum(app.getCommonParam().getCarNum());
            mPresenter.violationDeal(p);
        }else{
            ToastUtil.show(this,R.string.no_car);
            finish();
        }
    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }




    @Override
    public void showSuccess(ViolationDealParam param) {

        if(param != null){
            adapter = new ViolationDealAdapter(this,param.getData().getIlist());
            deal_listView.setAdapter(adapter);
            String last = param.getData().getLastTime();
            if(last == null || "".equals(last.trim())){
                last = TimeUtils.getCurDateyyyy_MM_dd();
            }
            violationDealTimeTv.setText(last);
            dealFineTv.setText(""+param.getData().getCountMoney());
            dealNotTv.setText(""+param.getData().getDealCount());

            dealCentTv.setText(""+param.getData().getCountCent());
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
        }
    }
}
