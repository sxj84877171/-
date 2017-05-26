package com.soarsky.car.ui.roadside.finish;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.RoadSideListOrderInfo;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2017/5/11
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：订单已完成详情
 * 该类为
 */


public class RoadSideFinishActivity extends BaseActivity<RoadSideFinishPresent,RoadSideFinishModel> implements RoadSideFinishView,View.OnClickListener{


    private TextView roadSideDetalCloseTv;

    private ImageView finishSeverView;

    private TextView finshSeverTv;
    /**
     * 服务费用
     */
    private TextView finishRescueSeverFeeTv;

    private TextView finishRescueSeverItemTv;

    private TextView finishRescueTelTv;

    private TextView finishRescueCarNumTv;

    private TextView finishRescueCarTypeTv;

    private TextView finishRescueAddrTv;

    /**
     * id
     */
    private String id ="";

    @Override
    public int getLayoutId() {
        return R.layout.activity_road_side_finish3;
    }

    @Override
    public void initView() {

        roadSideDetalCloseTv = (TextView) findViewById(R.id.roadSideDetalCloseTv);
        roadSideDetalCloseTv.setOnClickListener(this);

        finishSeverView = (ImageView) findViewById(R.id.finishSeverView);
        finshSeverTv = (TextView) findViewById(R.id.finshSeverTv);

        finishRescueSeverFeeTv = (TextView) findViewById(R.id.finishRescueSeverFeeTv);
        finishRescueSeverItemTv = (TextView) findViewById(R.id.finishRescueSeverItemTv);
        finishRescueTelTv = (TextView) findViewById(R.id.finishRescueTelTv);
        finishRescueCarNumTv = (TextView) findViewById(R.id.finishRescueCarNumTv);
        finishRescueCarTypeTv = (TextView) findViewById(R.id.finishRescueCarTypeTv);
        finishRescueAddrTv = (TextView) findViewById(R.id.finishRescueAddrTv);

    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getIntent().getStringExtra("id");
        mPresenter.getRescueById(id);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.roadSideDetalCloseTv:
                finish();
                break;
        }
    }

    @Override
    public void getRescueByIdSuccess(ResponseDataBean<RoadSideListOrderInfo> roadSideListOrderParam) {

        if(roadSideListOrderParam != null){
            finishRescueSeverFeeTv.setText(roadSideListOrderParam.getData().getCost());
            finishRescueSeverItemTv.setText(roadSideListOrderParam.getData().getServiceItems());
            finishRescueTelTv.setText(roadSideListOrderParam.getData().getPhone());
            finishRescueCarNumTv.setText(roadSideListOrderParam.getData().getCarNumber());
            finishRescueCarTypeTv.setText(roadSideListOrderParam.getData().getCarType());
            finishRescueAddrTv.setText(roadSideListOrderParam.getData().getAddress());
            finshSeverTv.setText(roadSideListOrderParam.getData().getCompany());
        }

    }

    @Override
    public void getRescueByIdFail() {

    }

    @Override
    public void showError(Throwable e) {

    }
}
