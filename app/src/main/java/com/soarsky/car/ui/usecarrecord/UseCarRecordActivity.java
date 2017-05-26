package com.soarsky.car.ui.usecarrecord;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.FaultRecordDataBean;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.UseCarRecordParam;
import com.soarsky.car.uitl.ToastUtil;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/5/10<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为用车记录页面<br>
 */

public class UseCarRecordActivity extends BaseActivity<RecordPresent,RecordModel> implements RecordView, View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    private LinearLayout backLay;
    private ListView carRecordListView;
    private UseCarRecordAdapter adapter;
    private App app;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_use_car_record3;
    }

    @Override
    public void initView() {
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText("用车记录");

        app = (App) getApplicationContext();
        carRecordListView = (ListView) findViewById(R.id.carRecordListView);
        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void getCarRecoredsListSuccess(ResponseDataBean<List<UseCarRecordParam>> useCarRecordParam) {
        if(useCarRecordParam != null){

            if(Constants.REQUEST_SUCCESS.equals(useCarRecordParam.getStatus())){
                adapter = new UseCarRecordAdapter(mContext,useCarRecordParam.getData());
                carRecordListView.setAdapter(adapter);
            }else {
                //王松清
                ToastUtil.show(mContext,R.string.get_data_fail);
            }
        }
    }

    @Override
    public void getCarRecoredsListFail() {

    }

    @Override
    public void getFaultSuccess(ResponseDataBean<List<FaultRecordDataBean>> param) {

    }

    @Override
    public void getFaultFail() {

    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getCarRecoredsList(app.getCommonParam().getCarNum());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backLay:
                finish();
                break;
        }
    }
}
