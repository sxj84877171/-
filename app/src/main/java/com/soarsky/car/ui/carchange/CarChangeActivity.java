package com.soarsky.car.ui.carchange;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.TerminalInfoParam;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;

import java.util.List;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：切换车辆 <br>
 * 该类为 CarChangeActivity <br>
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
    /**
     * 车列表的适配
     */
    private CarListAdapter adapter;
    /**
     * application
     */
    private App app;
    /**
     * 该类的key
     */
    private final static String TAG = "CarChangeActivity";
    /**
     * 选中的车牌
     */
    private LicenseInfo bean;

    /**
     * 数据
     */
    private List<LicenseInfo> list;

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
                bean = (LicenseInfo)adapter.getItem(i);
                adapter.setSelectedPosition(i);
                adapter.notifyDataSetChanged();
            }
        });

        /*carListView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){

                }
            }
        });*/

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SpUtil.init(this);
        mPresenter.getAllCarnum(app.getCommonParam().getOwerPhone());
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    /**
     * 获取所有车牌成功的回调
     * @param param 车牌列表参数
     */
    @Override
    public void getAllCarnumSuccess(ResponseDataBean<List<LicenseInfo>> param) {

        if(param!=null) {
            list = param.getData();
            if(Constants.REQUEST_SUCCESS.equals(param.getStatus())) {
                adapter = new CarListAdapter(CarChangeActivity.this,param.getData(),app.getCommonParam().getCarNum());
                adapter.initChoose();
                carListView.setAdapter(adapter);
            }else {
                //将后台的message改成自己的信息显示给用户               王松清
                ToastUtil.show(this,R.string.get_all_fail);
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

    /**
     * 获取终端信息成功
     * @param terminalInfoParam 终端信息参数
     */
    @Override
    public void getTerminalInfoSuccess(ResponseDataBean<TerminalInfoParam> terminalInfoParam) {

        if(terminalInfoParam!= null){
            if(Constants.REQUEST_SUCCESS.equals(terminalInfoParam.getStatus())){
                app.getCommonParam().setAuthCode(terminalInfoParam.getData().getWarrant());
            }
        }
    }

    /**
     * 获取终端信息失败
     */
    @Override
    public void getTerminalInfoFail() {

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
                app.getCommonParam().setRegisterCarDate(bean.getRegisterdate());

                SpUtil.save(Constants.CAR_NUM,bean.getPlateno());
                SpUtil.save(Constants.REGISTER_CAR_DATE,bean.getRegisterdate());
//                mPresenter.getTerminalInfo(app.getCommonParam().getCarNum());
                finish();
                break;
        }
    }
}
