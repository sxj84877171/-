package com.soarsky.car.ui.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.CommonParam;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseFragment;
import com.soarsky.car.bean.CheckVersionBean;
import com.soarsky.car.bean.DrivingLicenseInfo;
import com.soarsky.car.bean.DrivingLicenseInformationDataBean;
import com.soarsky.car.bean.QueryFamilyBean;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.bean.TerminalInfoParam;
import com.soarsky.car.bean.UploadFile;
import com.soarsky.car.bean.ViolationDealInfo;
import com.soarsky.car.server.wifi.WifiTransfer;
import com.soarsky.car.ui.home.main.MainModel;
import com.soarsky.car.ui.home.main.MainPresent;
import com.soarsky.car.ui.home.main.MainView;
import com.soarsky.car.ui.license.DrivLicenseActivity;
import com.soarsky.car.ui.licenseinformation.DrivingLicenseInformationActivity;
import com.soarsky.car.ui.usecarrecord.FaultRecordActivity;
import com.soarsky.car.ui.usecarrecord.UseCarRecordActivity;
import com.soarsky.car.uitl.ConstUtils;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.TimeUtils;
import com.umeng.analytics.MobclickAgent;

import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static anetwork.channel.download.DownloadManager.TAG;
import static com.soarsky.car.ConstantsUmeng.DRIVER_CARD;
import static com.soarsky.car.ConstantsUmeng.ID_CARD;

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
 * 该类为主页面--证照<br>
 */

public class NewLicenseFragment extends BaseFragment<MainPresent, MainModel> implements MainView, View.OnClickListener {
    /**
     * 姓名,驾驶证号，年检期止
     */
    private TextView tv_name,tv_idNum,tv_date;
    private App app;
    /**
     * 驾驶证
     */
    private RelativeLayout rl_driveLicense;

    /**
     * 行驶证
     */
    private RelativeLayout rl_xingShiZheng;
    /**
     * 用车记录
     */
    private RelativeLayout rl_useCar_record;

    /**
     * 故障记录
     */
    private RelativeLayout rl_fault_record;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_electronic3;
    }

    @Override
    public void initView(View view) {

        app = (App) getActivity().getApplication();
        app.addActivity(TAG, getActivity());
        SpUtil.init(getActivity());
        if (TextUtils.isEmpty(SpUtil.get("volume"))) {
            SpUtil.save("volume", "5");
        }
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_idNum = (TextView) view.findViewById(R.id.tv_id_card_num);
        tv_date = (TextView) view.findViewById(R.id.tv_date);

        rl_driveLicense = (RelativeLayout) view.findViewById(R.id.rl_driveLicense);
        rl_driveLicense.setOnClickListener(this);

        rl_xingShiZheng = (RelativeLayout) view.findViewById(R.id.rl_xingShiZheng);
        rl_xingShiZheng.setOnClickListener(this);

        rl_useCar_record = (RelativeLayout) view.findViewById(R.id.rl_useCar_record);
        rl_useCar_record.setOnClickListener(this);

        rl_fault_record = (RelativeLayout) view.findViewById(R.id.rl_fault_record);
        rl_fault_record.setOnClickListener(this);
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    protected void initToolbar(Toolbar toolbar) {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void showFamilySuccess(ResponseDataBean<QueryFamilyBean> param) {

    }

    @Override
    public void showFamilyFail() {

    }

    @Override
    public void deleteAllSuccess() {

    }

    @Override
    public void deleteAllFail() {

    }

    @Override
    public void showError(Throwable e) {

    }

    @Override
    public void showSuccess(ResponseDataBean<ViolationDealInfo> violationDealParam) {

    }

    @Override
    public void showLicenseSuccess(ResponseDataBean<DrivingLicenseInfo> param) {
        tv_idNum.setText(app.getCommonParam().getDrivingLicence());
        tv_name.setText(app.getCommonParam().getRealName());
        try {
            if (app.getCommonParam().getRegisterDriverDate() != null) {
                if (!(TextUtils.isEmpty(app.getCommonParam().getRegisterDriverDate()))) {
                    SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date d = sDateFormat.parse(app.getCommonParam().getRegisterDriverDate());
                    // 车的年检日期
                    Date dd = TimeUtils.addYear(d, 1);
                    long ddd = dd.getTime() - new Date().getTime();
                    tv_date.setText("" + roundCleanDay(TimeUtils.milliseconds2Unit(ddd, ConstUtils.DAY)) + "天");
                } else {
                    tv_date.setText(getString(R.string.nodate));
                }
            } else {
                tv_date.setText(getString(R.string.nodate));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showLicenseError() {

    }

    @Override
    public void getTerminalInfoSuccess(int index, ResponseDataBean<TerminalInfoParam> taerminalInfoParm) {

    }

    @Override
    public void getTerminalInfoFail() {

    }

    @Override
    public void uploadImgSuccess(UploadFile uploadFile) {

    }

    @Override
    public void uploadImgFail() {

    }

    @Override
    public void modifyAppImageSuccess(ResponseDataBean<String> stringResponseDataBean) {

    }

    @Override
    public void modifyAppImageFail() {

    }

    @Override
    public void connectSuccess(WifiTransfer transfer, Socket socket) {

    }

    @Override
    public void connectFail() {

    }

    @Override
    public void checkSuccess(ResponseDataBean<CheckVersionBean> checkVersion) {

    }

    @Override
    public void checkFail(ResponseDataBean<CheckVersionBean> checkVersion) {

    }

    @Override
    public void loadSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {

    }

    @Override
    public void loadFail(Call<ResponseBody> call, Throwable t) {

    }

    @Override
    public void initViewData(CommonParam commonParam) {

    }

    @Override
    public void getDriverListSuccess(ResponseDataBean<List<DrivingLicenseInformationDataBean>> carNumParam) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_driveLicense:
                MobclickAgent.onEvent(getActivity(), ID_CARD);
                //驾驶证详情

                if (SpUtil.get(Constants.LICESE_NUM) != null && !SpUtil.get(Constants.LICESE_NUM).equals("")) {

                    startActivity(new Intent(getActivity(), DrivLicenseActivity.class));
//                startActivity(new Intent(MainActivity.this, LicenseValidationActivity.class));
                } else {
                    new AlertDialog.Builder(mContext).setTitle(R.string.driver_confirm_title)
                            .setNegativeButton(R.string.exit_cancel, new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // 点击“返回”后的操作,这里不设置没有任何操作
                                }
                            }).show();
                }
                break;
            case R.id.rl_xingShiZheng:
                MobclickAgent.onEvent(getActivity(), DRIVER_CARD);
                //行驶证详情
                startActivity(new Intent(getActivity(), DrivingLicenseInformationActivity.class));
                break;
            case R.id.rl_useCar_record:
                startActivity(new Intent(getActivity(), UseCarRecordActivity.class));
                break;
            case R.id.rl_fault_record:
                startActivity(new Intent(getActivity(), FaultRecordActivity.class));
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private long roundCleanDay(long day) {
        while (day < 0) {
            day += 365;
        }

        while (day >= 365) {
            day -= 365;
        }

        return day;
    }
}
