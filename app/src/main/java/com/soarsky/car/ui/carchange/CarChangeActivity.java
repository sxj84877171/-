package com.soarsky.car.ui.carchange;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.login.CarNumParam;
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
 * 程序功能：切换车辆
 * 该类为
 */


public class CarChangeActivity extends BaseActivity<CarChangePresent,CarChangeModel> implements CarChangeView,View.OnClickListener{
    /**
     * 切换确定
     */
    private TextView carChangeSureTv;
    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 车的列表
     */
    private ListView carListView;

    private CarListAdapter adapter;

    private App app;

    private final static String TAG = "CarChangeActivity";
    /**
     * 选中的车牌
     */
    private CarNumParam.DataBean bean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_change;
    }

    @Override
    public void initView() {

        app = (App)getApplication();
        app.addActivity(TAG,this);

        carChangeSureTv = (TextView) findViewById(R.id.carChangeSureTv);
        carChangeSureTv.setOnClickListener(this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        carListView = (ListView) findViewById(R.id.carListView);

        carListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                bean = (CarNumParam.DataBean)adapter.getItem(i);
                adapter.setSelectedPosition(i);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter.getAllCarnum(app.getCommonParam().getOwerPhone());
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 获取所有车牌成功的回调
     * @param param
     */
    @Override
    public void getAllCarnumSuccess(CarNumParam param) {

        if(param!=null) {
            if("0".equals(param.getStatus())) {
                adapter = new CarListAdapter(this, param.getData());
                carListView.setAdapter(adapter);
            }else {
                ToastUtil.show(this,param.getMessage());
            }
        }
    }

    /**
     * 获取机动车失败的回调
     */
    @Override
    public void getAllCarnumFail() {

        ToastUtil.show(this,R.string.throwable);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.carChangeSureTv:
                if(bean == null){
                    ToastUtil.show(this,R.string.carselected);
                    return;
                }
                app.getCommonParam().setCarNum(bean.getPlateno());
                app.getCommonParam().setAuthCode(bean.getVin());

                finish();
                break;
        }
    }
}
