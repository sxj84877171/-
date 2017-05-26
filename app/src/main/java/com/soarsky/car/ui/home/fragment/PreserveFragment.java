package com.soarsky.car.ui.home.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseFragment;
import com.soarsky.car.ui.carcheck.carchecklogin.CarCheckActivity;
import com.soarsky.car.ui.danger.start.DangerStartActivity;
import com.soarsky.car.ui.drivrecord.DrivRecordActivity;
import com.soarsky.car.ui.drivrecord.map.DrivingRecordMapActivity;
import com.soarsky.car.ui.drivrecord.nosimcard.NoSimCardActivity;
import com.soarsky.car.ui.login.LoginActivity;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.StringUtils;
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
 * 程序功能：
 * 该类为 维修
 */

public class PreserveFragment extends BaseFragment implements View.OnClickListener{
    /**
     * 维修保养
     */
    private LinearLayout fragmentPreserveLay;
    /**
     * 车辆检测
     */
    private LinearLayout fragmentSafeLay;
    /**
     * 行驶数据
     */
    private LinearLayout fragmentDriveLay;
    /**
     * 保养常识
     */
    private LinearLayout fragmentPreSenseLay;
    /**
     * 保险购买
     */
    private TextView fragmentShopTv;
    /**
     * 出险报警
     */
    private TextView fragmentAramTv;

    private App app;

    public PreserveFragment() {
        // Required empty public constructor
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_preserve;
    }

    @Override
    public void initView(View view) {

        app = (App) mContext.getApplicationContext();

        fragmentPreserveLay = (LinearLayout) view.findViewById(R.id.fragmentPreserveLay);
        fragmentPreserveLay.setOnClickListener(this);

        fragmentSafeLay = (LinearLayout) view.findViewById(R.id.fragmentSafeLay);
        fragmentSafeLay.setOnClickListener(this);

        fragmentDriveLay = (LinearLayout) view.findViewById(R.id.fragmentDriveLay);
        fragmentDriveLay.setOnClickListener(this);

        fragmentPreSenseLay = (LinearLayout) view.findViewById(R.id.fragmentPreSenseLay);
        fragmentPreSenseLay.setOnClickListener(this);

        fragmentShopTv = (TextView) view.findViewById(R.id.fragmentShopTv);
        fragmentShopTv.setOnClickListener(this);

        fragmentAramTv = (TextView) view.findViewById(R.id.fragmentAramTv);
        fragmentAramTv.setOnClickListener(this);

    }

    @Override
    protected String getHeaderTitle() {
        return getString(R.string.preservecar);
    }

    @Override
    public void onClick(View view) {

        if (app.isOnline() == false) {
            ToastUtil.show(mContext, R.string.personaltip);
            Intent i = new Intent();
            i.setClass(mContext, LoginActivity.class);
            startActivity(i);
            return;
        }
        switch (view.getId()){
            case R.id.fragmentPreserveLay:
                ToastUtil.show(mContext,"fragmentPreserveLay");
                break;
            case R.id.fragmentSafeLay:
                startActivity(new Intent(getActivity(), CarCheckActivity.class));
                break;
            case R.id.fragmentDriveLay:
                if(SpUtil.getBoolean(Constants.HAS_SIM_CARD)){
                        if(!StringUtils.isEmpty(App.getApp().getCommonParam().getQueryPwd())){
                            startActivity(new Intent(getActivity(), DrivingRecordMapActivity.class));
                        }else {
                        startActivity(new Intent(getActivity(), DrivRecordActivity.class));
                    }
                }else {
                    startActivity(new Intent(getActivity(), NoSimCardActivity.class));
                }
                break;
            case R.id.fragmentPreSenseLay:
                ToastUtil.show(mContext,"fragmentPreSenseLay");
                break;
            case R.id.fragmentShopTv:
                ToastUtil.show(mContext,"fragmentShopTv");
                break;
            case R.id.fragmentAramTv:
                ToastUtil.show(mContext,"fragmentAramTv");
                startActivity(new Intent(mContext, DangerStartActivity.class));
                break;

        }
    }
}
