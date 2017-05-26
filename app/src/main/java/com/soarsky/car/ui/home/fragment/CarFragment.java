package com.soarsky.car.ui.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseFragment;
import com.soarsky.car.ui.car.CarActivity;
import com.soarsky.car.ui.car.validation.CarValidationActivity;
import com.soarsky.car.ui.license.DrivLicenseActivity;
import com.soarsky.car.ui.license.validation.LicenseValidationActivity;
import com.soarsky.car.ui.login.LoginActivity;
import com.soarsky.car.ui.violationdeal.ViolationDealActivity;
import com.soarsky.car.ui.violationquery.ViolationQueryActivity;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;


/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：车辆页面<br>
 * 该类为 CarFragment<br>
 */

public class CarFragment extends BaseFragment implements View.OnClickListener {

    private String tag = "CarFragment";

    /**
     * 上下文本
     */
    private Context mContext;

    /**
     * 违章查询
     */
    private TextView carViolationTv;
    /**
     * 违章处理
     */
    private TextView carViolationProTv;
    /**
     * 我的机动车
     */
    private TextView fragmentCarTv;
    /**
     * 我的驾驶证
     */
    private TextView fragmentLienseTv;
    /**
     * 交通法规知识
     */
    private TextView fragmentCarTraffTv;


    /**
     * 声音设置滑块
     */
    private SeekBar volumeSb;

    private App app;



    public CarFragment() {
        // Required empty public constructor

    }


    /**
     * @param view
     */
    @Override
    public void initView(View view) {

        app = (App) mContext.getApplicationContext();

        carViolationTv = (TextView) view.findViewById(R.id.carViolationTv);
        carViolationTv.setOnClickListener(this);

        carViolationProTv = (TextView) view.findViewById(R.id.carViolationProTv);
        carViolationProTv.setOnClickListener(this);

        fragmentCarTv = (TextView) view.findViewById(R.id.fragmentCarTv);
        fragmentCarTv.setOnClickListener(this);

        fragmentLienseTv = (TextView) view.findViewById(R.id.fragmentLienseTv);
        fragmentLienseTv.setOnClickListener(this);

        fragmentCarTraffTv = (TextView) view.findViewById(R.id.fragmentCarTraffTv);
        fragmentCarTraffTv.setOnClickListener(this);


        volumeSb = (SeekBar) view.findViewById(R.id.id_seekBar);
        volumeSb.setOnSeekBarChangeListener(new SeekBarListener());
    }

    @Override
    protected String getHeaderTitle() {
        return getString(R.string.cartopic);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        SpUtil.init(context);
    }


    /**
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.fragment_car;
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

        switch (view.getId()) {
            case R.id.carViolationTv:
                Intent i = new Intent();
                i.setClass(mContext, ViolationQueryActivity.class);
                startActivity(i);
                break;
            case R.id.carViolationProTv:

                Intent in = new Intent();
                in.setClass(mContext, ViolationDealActivity.class);
                startActivity(in);
                break;
            case R.id.fragmentCarTv:
                if (app.getCommonParam().getCarNum() != null) {
                    Intent it = new Intent();
                    it.setClass(mContext, CarActivity.class);
                    startActivity(it);
                } else {
                    Intent im = new Intent();
                    im.setClass(mContext, CarValidationActivity.class);
                    startActivity(im);
                }
                break;
            case R.id.fragmentLienseTv:
                if(app.getCommonParam().getDrivingLicence() != null) {
                    Intent im = new Intent();
                    im.setClass(mContext, DrivLicenseActivity.class);
                    startActivity(im);
                } else {
                    Intent im = new Intent();
                    im.setClass(mContext, LicenseValidationActivity.class);
                    startActivity(im);
                }
                break;
            case R.id.fragmentCarTraffTv:
                ToastUtil.show(mContext, "fragmentCarTraffTv");
                break;
        }
    }


    /**
     * 滑块监听事件
     */

    public class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

        private int progress1;

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (progress == 0) {
                progress1 = 1;
            } else {
                progress1 = progress;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (progress1 < 10) {
                SpUtil.save("volume", "0" + progress1);
            } else {
                SpUtil.save("volume", progress1 + "");
            }

        }
    }
}
