package com.soarsky.car.ui.illegal.query.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.ViolationDealInfo;
import com.soarsky.car.ui.illegal.adapter.IllegalListAdapter;
import com.soarsky.car.ui.illegal.fragment.IllegalUpdateCallBack;
import com.soarsky.car.ui.illegal.query.IllegalQueryActivity;
import com.soarsky.car.ui.violationdeal.ViolationDealSendParam;
import com.soarsky.car.uitl.TimeUtils;
import com.soarsky.car.uitl.ToastUtil;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/29<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：切换车辆违章信息<br>
 * 该类为 IllegalQueryListActivity<br>
 */


public class IllegalQueryListActivity extends BaseActivity<IllegalQueryListPresent,IllegalQueryListModel> implements IllegalQueryListView,View.OnClickListener,IllegalUpdateCallBack{

    /**
     * 车牌号
     */
    private TextView illegalQueryListCarNumTv;
    /**
     * 更新时间
     */
    private TextView illegalDealTimeTv;
    /**
     * 返回
     */
    private LinearLayout illegalBackLay;
    /**
     * 切换车辆
     */
    private TextView illegalChangeCarTv;

    /**
     * 违章数
     */
    private TextView illegaldealNotTv;
    /**
     * 罚款
     */
    private TextView illegaldealFineTv;
    /**
     * 分
     */
    private TextView illegaldealCentTv;
    /**
     * 地址
     */
    private TextView illegalAddressTv;
    /**
     * 列表
     */
    private ListView illegalListView;
    /**
     * 列表适配
     */
    private IllegalListAdapter adapter;
    /**
     * 车牌号
     */
    private String carNum = "";



    @Override
    public int getLayoutId() {
        return R.layout.activity_illegal_query_list;
    }

    @Override
    public void initView() {
        illegalQueryListCarNumTv = (TextView) findViewById(R.id.illegalQueryListCarNumTv);

        illegalDealTimeTv = (TextView) findViewById(R.id.illegalDealTimeTv);

        illegalBackLay = (LinearLayout) findViewById(R.id.illegalBackLay);
        illegalBackLay.setOnClickListener(this);

        illegalChangeCarTv = (TextView) findViewById(R.id.illegalChangeCarTv);
        illegalChangeCarTv.setOnClickListener(this);

        illegalListView = (ListView) findViewById(R.id.illegalListView);
        illegaldealNotTv = (TextView) findViewById(R.id.illegaldealNotTv);
        illegaldealFineTv = (TextView)findViewById(R.id.illegaldealFineTv);
        illegaldealCentTv = (TextView) findViewById(R.id.illegaldealCentTv);
        illegalAddressTv = (TextView)findViewById(R.id.illegalAddressTv);

    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 获取违章数据成功
     * @param param 返回参数
     */
    @Override
    public void showSuccess(ResponseDataBean<ViolationDealInfo> param) {
        if(param != null){
            if(Constants.REQUEST_SUCCESS.equals(param.getStatus())){
                adapter = new IllegalListAdapter(mContext,param.getData().getIlist(),this);

                illegalListView.setAdapter(adapter);

                String last = param.getData().getLastTime();
                if(last == null || "".equals(last.trim())){
                    last = TimeUtils.getCurDateyyyy_MM_dd();
                }
                illegalDealTimeTv.setText(last);
                illegaldealNotTv.setText(""+param.getData().getDealCount());
                illegaldealFineTv.setText(""+param.getData().getCountMoney());
                illegaldealCentTv.setText(""+param.getData().getCountCent());
            }else {
                ToastUtil.show(mContext,param.getMessage());
            }
        }
    }

    /**
     * 获取违章数据失败
     */
    @Override
    public void showError() {

        ToastUtil.show(this,R.string.throwable);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carNum = getIntent().getStringExtra("carnum");
        if(carNum != null){
            illegalQueryListCarNumTv.setText(""+carNum);

            ViolationDealSendParam p = new ViolationDealSendParam();
            p.setCarnum(carNum);

            mPresenter.violationDeal(p);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.illegalChangeCarTv:
                Intent i = new Intent();
                i.setClass(this,IllegalQueryActivity.class);
                startActivity(i);
                break;
            case R.id.illegalBackLay:
                finish();
                break;
        }
    }

    @Override
    public void illegalUpdateCallBack(String id) {

        if (id != null){
            mPresenter.appViolationAdvice(id);
        }
    }

    @Override
    public void appViolationAdviceSuccess(ResponseDataBean<Void> param) {

        if (carNum != null) {
            illegalQueryListCarNumTv.setText("" + carNum);

            ViolationDealSendParam p = new ViolationDealSendParam();
            p.setCarnum(carNum);

            mPresenter.violationDeal(p);
        }

//        if(Constants.REQUEST_SUCCESS.equals(param.getStatus())) {
//            if (carNum != null) {
//                illegalQueryListCarNumTv.setText("" + carNum);
//
//                ViolationDealSendParam p = new ViolationDealSendParam();
//                p.setCarnum(carNum);
//
//                mPresenter.violationDeal(p);
//            }
//        }else {
//            ToastUtil.show(this,param.getMessage());
//        }

    }

    @Override
    public void appViolationAdviceFail() {

    }
}
