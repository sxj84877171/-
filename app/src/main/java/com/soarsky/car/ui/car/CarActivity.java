package com.soarsky.car.ui.car;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.CarInfoParam;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.CarInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.uitl.CarTypeUtil;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;


/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/8  <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：机动车的详情 <br>
 * 该类为 我的机动车 <br>
 */
public class CarActivity extends BaseActivity<CarPresent,CarModel> implements CarView ,View.OnClickListener{
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 车身颜色
     */
    private TextView carColorTv;
    /**
     * 车辆类型
     */
    private TextView carClassesTv;
    /**
     * 车辆识别码
     */
    private TextView carIdentifyTv;
    /**
     * 发动机号码
     */
    private TextView carEngineTv;
    /**
     * 车辆所有人
     */
    private TextView carPersonTv;
    /**
     * 联系方式
     */
    private TextView carTelTv;
    /**
     * 行驶证状态
     */
    private TextView carStatusTv;
    /**
     * 年检期止
     */
    private TextView carPeriodTv;
    /**
     * 车辆号码
     */
    private TextView carLienseTv;
    /**
     * application
     */
    private App app;
    /**
     * 该类的key
     */
    public final static String TAG = "CarActivity";
    /**
     * 车牌号
     */
    private String carnum;

    @Override
    public int getLayoutId() {
        return R.layout.activity_car;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);
        SpUtil.init(this);

        backLay = (LinearLayout)findViewById(R.id.backLay);
        backLay.setOnClickListener(this);
        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.cartitle));

        carLienseTv = (TextView) findViewById(R.id.carLienseTv);
        carPersonTv = (TextView) findViewById(R.id.carPersonTv);
        carClassesTv = (TextView) findViewById(R.id.carClassesTv);
        carColorTv = (TextView) findViewById(R.id.carColorTv);
        carIdentifyTv = (TextView) findViewById(R.id.carIdentifyTv);
        carEngineTv = (TextView) findViewById(R.id.carEngineTv);
        carTelTv = (TextView) findViewById(R.id.carTelTv);
        carStatusTv = (TextView) findViewById(R.id.carStatusTv);
        carPeriodTv = (TextView) findViewById(R.id.carPeriodTv);

        carnum = getIntent().getStringExtra("carnum");

    }

    @Override
    protected void onResume() {
        super.onResume();
//        requestCarInfo();
    }

    /**
     * 获取机动车的请求
     */
    private void requestCarInfo(){
        CarSendParam p = new CarSendParam();
        if(carnum == null) {
            p.setCarnum(app.getCommonParam().getCarNum());
        }else {
            p.setCarnum(carnum);
        }
        mPresenter.getCarInfo(p);
    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }


    @Override
    public void initCarData(CarInfoParam param) {
        carLienseTv.setText("" + param.getCarNum());
        carPersonTv.setText("" + param.getOwerName());
        carClassesTv.setText("" +param.getCarType());
        carColorTv.setText("" + param.getCarColor());
        carIdentifyTv.setText("" + param.getCarIdenty());
        carEngineTv.setText("" + param.getCarEngineNo());
        carTelTv.setText(""+param.getTelePhone());
        carStatusTv.setText("" + param.getCarDriviStatus());
        carPeriodTv.setText("" + param.getCarDate());
    }

    /**
     * 成功获取机动车的信息回调
     * @param param 车详细信息参数
     */
    @Override
    public void showSuccess(ResponseDataBean<CarInfo> param) {

        if(param != null){
            if(Constants.REQUEST_SUCCESS.equals(param.getStatus())) {
                carLienseTv.setText("" + param.getData().getPlateno());
                carPersonTv.setText("" + param.getData().getName());
                carClassesTv.setText("" + CarTypeUtil.getCarType(param.getData().getVehicletype()));
                carColorTv.setText("" + param.getData().getColor());
                carIdentifyTv.setText("" + param.getData().getVin());
                carEngineTv.setText("" + param.getData().getEnginno());
                carTelTv.setText(param.getData().getPhone()==null?getString(R.string.nodate):param.getData().getPhone());
                carStatusTv.setText("" + param.getData().getStatus());
                carPeriodTv.setText("" + param.getData().getIssuedate());
            }else {
                ToastUtil.show(this,param.getMessage());
                finish();
            }
        }
    }

    /**
     *获取机动车的信息失败回调
     */
    @Override
    public void showError() {
        ToastUtil.show(this,R.string.throwable);
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
