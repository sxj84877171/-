package com.soarsky.car.ui.illegal.electronic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.CarInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.car.CarSendParam;
import com.soarsky.car.ui.illegal.adapter.IllegalInfo;
import com.soarsky.car.ui.roadside.fee.RoadSideFeeActivity;
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
 * 程序功能：电子告知单<br>
 * 该类为 IllegalElectronicActivity<br>
 */


public class IllegalElectronicActivity extends BaseActivity<IllegalElectronicPresent,IllegalElectronicModel> implements IllegalElectronicView,View.OnClickListener{

    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 车牌号
     */
    private TextView illegalCarNumTv;
    /**
     * 文书编号
     */
    private TextView illegalEleNumTv;
    /**
     * 驾照号
     */
    private TextView illegalEleDriverNoTv;
    /**
     * 车身颜色
     */
    private TextView illegalEleCarColorTv;
    /**
     * 号牌颜色
     */
    private TextView illegalEleDriverColorTv;
    /**
     * 时间
     */
    private TextView illegalEleTimeTv;
    /**
     * 地址
     */
    private TextView illegalEleAddressTv;
    /**
     * 违章原因
     */
    private TextView illegalEleReasonTv;
    /**
     * 分
     */
    private TextView illegalEleCentTv;
    /**
     * 罚款
     */
    private TextView illegalEleFineTv;
    /**
     * 决定书
     */
    private CheckBox decisionCheckBox;
    /**
     * 票据
     */
    private CheckBox ticketCheckBox;
    /**
     * 交罚款
     */
    private Button illegalFeeBtn;
    /**
     * 标识来判断支付状态界面隐藏
     */
    private int flag = -1;
    /**
     * 违章信息对象
     */
    private IllegalInfo info;

    @Override
    public int getLayoutId() {
        return R.layout.activity_illegal_electronic3;
    }

    @Override
    public void initView() {
        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.illegal_elet_tict));

        illegalCarNumTv = (TextView) findViewById(R.id.illegalCarNumTv);
        illegalEleNumTv = (TextView) findViewById(R.id.illegalEleNumTv);
        illegalEleDriverNoTv = (TextView) findViewById(R.id.illegalEleDriverNoTv);
        illegalEleCarColorTv = (TextView) findViewById(R.id.illegalEleCarColorTv);
        illegalEleDriverColorTv = (TextView) findViewById(R.id.illegalEleDriverColorTv);
        illegalEleTimeTv = (TextView) findViewById(R.id.illegalEleTimeTv);
        illegalEleAddressTv = (TextView) findViewById(R.id.illegalEleAddressTv);
        illegalEleReasonTv = (TextView) findViewById(R.id.illegalEleReasonTv);
        illegalEleCentTv = (TextView) findViewById(R.id.illegalEleCentTv);
        illegalEleFineTv = (TextView) findViewById(R.id.illegalEleFineTv);

        decisionCheckBox = (CheckBox) findViewById(R.id.decisionCheckBox);
        ticketCheckBox = (CheckBox) findViewById(R.id.ticketCheckBox);

        illegalFeeBtn = (Button) findViewById(R.id.illegalFeeBtn);
        illegalFeeBtn.setOnClickListener(this);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flag = getIntent().getIntExtra("flag",-1);
        info = (IllegalInfo) getIntent().getSerializableExtra("info");
        if(info != null){
            illegalCarNumTv.setText(info.getCarNum() == null?"":info.getCarNum());
            illegalEleNumTv.setText(info.getDocumentNo()== null?"":info.getDocumentNo());
            illegalEleDriverNoTv.setText(info.getDriverNo()==null?"":info.getDriverNo());
            illegalEleTimeTv.setText(info.getTime()== null?"":info.getTime());
            illegalEleAddressTv.setText(info.getAddress() == null?"":info.getAddress());
            illegalEleReasonTv.setText(info.getIllegalReason()== null?"":info.getIllegalReason());
            illegalEleCentTv.setText(info.getCent());
            illegalEleFineTv.setText(info.getFee());
            if("0".equals(info.getPlateType())){
                illegalEleDriverColorTv.setText(getString(R.string.driver_color1));
            }else if("1".equals(info.getPlateType())) {
                illegalEleDriverColorTv.setText(getString(R.string.driver_color2));
            }

            CarSendParam p = new CarSendParam();
            p.setCarnum(info.getCarNum());
            mPresenter.getCarInfo(p);
        }


    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 获取机动车信息成功回调
     * @param param 返回参数
     */
    @Override
    public void showCarSuccess(ResponseDataBean<CarInfo> param) {

        if(param != null){
            if(Constants.REQUEST_SUCCESS.equals(param.getStatus())){
                illegalEleCarColorTv.setText(param.getData().getColor());
            }else {
                ToastUtil.show(this,param.getMessage());
            }
        }
    }

    /**
     * 获取机动车失败回调
     */
    @Override
    public void showCarError() {

        ToastUtil.show(this,R.string.throwable);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.illegalFeeBtn:
                Intent i = new Intent();
                i.setClass(this, RoadSideFeeActivity.class);
                i.putExtra("flag",flag);
                i.putExtra("info",info);
                startActivity(i);
                break;
        }

    }
}
