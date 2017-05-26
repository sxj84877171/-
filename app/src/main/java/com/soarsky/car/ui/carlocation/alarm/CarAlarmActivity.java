package com.soarsky.car.ui.carlocation.alarm;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

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
 * 程序功能：防盗报警
 * 该类为
 */

public class CarAlarmActivity extends BaseActivity<CarAlarmPresent,CarAlarmModel> implements CarAlarmView,View.OnClickListener{

    /**
     * 返回
     */
    private LinearLayout backLay;
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 左边搜索
     */
    private LinearLayout leftSearchLay;
    /**
     * 搜索内容
     */
    private EditText carAlarmSearchEt;
    /**
     * 中间搜索
     */
    private LinearLayout midSearchLay;
    /**
     * 历史记录列表
     */
    private ListView carAlarmListView;

    private RelativeLayout carAlarmLay;
    /**
     * 记录列表适配
     */
    private CarAlarmAdapter adapter;

    private List<CarAlarmParam.DataBean> list;
    /**
     * 应用
     */
    private App app;

    private final static String TAG = "CarAlarmActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_alarm;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG ,this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(R.string.car_alarm);

        leftSearchLay = (LinearLayout) findViewById(R.id.leftSearchLay);
        carAlarmSearchEt = (EditText) findViewById(R.id.carAlarmSearchEt);

        midSearchLay = (LinearLayout) findViewById(R.id.midSearchLay);
        midSearchLay.setOnClickListener(this);

        carAlarmLay = (RelativeLayout) findViewById(R.id.carAlarmLay);
        carAlarmLay.setOnClickListener(this);

        carAlarmListView = (ListView) findViewById(R.id.carAlarmListView);

        carAlarmSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String search = carAlarmSearchEt.getText().toString();
                List<CarAlarmParam.DataBean> _list = new ArrayList<CarAlarmParam.DataBean>();
                for(CarAlarmParam.DataBean bean:list){
                    if(bean.getStimer().contains(search) || bean.getAddress().contains(search)){
                        _list.add(bean);
                    }
                }
                adapter.setList(_list);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        CarAlarmSendParam p = new CarAlarmSendParam();
        p.setCarnum(""+ app.getCommonParam().getCarNum());
        mPresenter.getTheftRecords(p);
    }

    /**
     * 后台返回的数据
     * @param param
     */
    @Override
    public void showSuccess(CarAlarmParam param) {

        if(param != null){
            if("0".equals(param.getStatus())) {
                list = param.getData();
                adapter = new CarAlarmAdapter(this, list);
                carAlarmListView.setAdapter(adapter);
            }else {
                ToastUtil.show(this,""+param.getMessage());
            }
        }


    }

    /***
     * 后台回调网络链接失败
     */
    @Override
    public void showError() {

        ToastUtil.show(this,R.string.throwable);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.backLay:
                finish();
                break;
            case R.id.carAlarmLay:
            case R.id.midSearchLay:
                ToastUtil.show(this,"carAlarmLay");
                midSearchLay.setVisibility(View.GONE);
                leftSearchLay.setVisibility(View.VISIBLE);
                carAlarmSearchEt.requestFocus();
                InputMethodManager imm = (InputMethodManager) carAlarmSearchEt.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);

                break;
        }
    }
}
