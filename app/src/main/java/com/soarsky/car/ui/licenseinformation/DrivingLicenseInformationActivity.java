package com.soarsky.car.ui.licenseinformation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.DrivingLicenseInformationDataBean;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.blindterm.BlindTermActivity;
import com.soarsky.car.ui.licenseinformation.cardetails.CarDetailsActivity;
import com.soarsky.car.uitl.ToastUtil;

import java.util.List;


/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/28<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 行驶证信息页面<br>
 */

public class DrivingLicenseInformationActivity extends BaseActivity<DrivingLicenseInformationPresent,DrivingLicenseInformationModel> implements DrivingLicenseInformationView,View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回按钮
     */
    private LinearLayout backLay;
    private ListView listView;
    private LicenseInformationAdapter adapter;
    private App app;
    private List<LicenseInfo> list;
    private ImageView tv_right;
    private RelativeLayout rightLay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_license_information;
    }

    @Override
    public void initView() {

        app = (App) getApplication();

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.license_information));

        tv_right = (ImageView) findViewById(R.id.tv_right);
        tv_right.setImageResource(R.mipmap.click_to_add);
        tv_right.setOnClickListener(this);

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        rightLay = (RelativeLayout) findViewById(R.id.rightLay);
        rightLay.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.lv_license_information);


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
            case R.id.tv_right:
            case R.id.rightLay:
                startActivity(new Intent(DrivingLicenseInformationActivity.this, BlindTermActivity.class));
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mPresenter.getDriverList(app.getCommonParam().getIdNo());

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getAllCarnum(app.getCommonParam().getOwerPhone());
    }

    @Override
    public void getDriverListFail(Throwable e) {
        ToastUtil.show(this,e.getMessage());
    }

    /**
     * 获取车牌号成功的回调
     * @param carNumParam
     */
    @Override
    public void getDriverListSuccess(ResponseDataBean<List<DrivingLicenseInformationDataBean>> carNumParam) {
//        if (carNumParam != null){
//            list = carNumParam.getData();
//            adapter = new LicenseInformationAdapter(this);
//            adapter.setData(list);
//            listView.setAdapter(adapter);
//
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                    Intent intent = new Intent();
//                    intent.setClass(DrivingLicenseInformationActivity.this, AuthenticationActivity.class);
//                    DrivingLicenseInformationParm.DataBean bean = (DrivingLicenseInformationParm.DataBean)adapter.getItem(i);
//                    intent.putExtra("carnum",""+bean.getPlateno());
//                    startActivity(intent);
//                }
//            });
//        }
    }

    @Override
    public void getAllCarnumSuccess(ResponseDataBean<List<LicenseInfo>> param) {
        if (param != null){
            if(Constants.REQUEST_SUCCESS.equals(param.getStatus())) {
                list = param.getData();
                adapter = new LicenseInformationAdapter(this);
                adapter.setData(list);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //因为暂时没有接口，所有把验证身份的页面去掉    王松清   2017/03/28
                        /*Intent intent = new Intent();
                        intent.setClass(DrivingLicenseInformationActivity.this, AuthenticationActivity.class);
                        LicenseInfo bean = (LicenseInfo) adapter.getItem(i);
                        intent.putExtra("carnum", "" + bean.getPlateno());
                        startActivity(intent);*/

                        Intent intent = new Intent();
                        intent.setClass(DrivingLicenseInformationActivity.this, CarDetailsActivity.class);
                        LicenseInfo bean = (LicenseInfo) adapter.getItem(i);
                        intent.putExtra("carnum",bean.getPlateno());
                        startActivity(intent);
                    }
                });
            }else {
                //将后台的message改成自己的信息显示给用户               王松清
                ToastUtil.show(this,R.string.get_carnum_fail);
            }
        }
    }

    @Override
    public void getAllCarnumFail() {

        ToastUtil.show(this,R.string.throwable);

    }
}
