package com.soarsky.car.ui.flashlight;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.uitl.ToastUtil;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/30<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 闪灯找车页面<br>
 */

public class FlashLightActivity extends BaseActivity<FlashLightPresent,FlashLightMedol> implements FlashLightView,View.OnClickListener {
    /**
     * 标题
     */
    private TextView topicTv;
    /**
     * 返回按钮
     */
    private LinearLayout backLay;
    /**
     * 我的车辆列表
     */
    private ListView listView;
    /**
     * 车辆列表数据适配器
     */
    private FlahLightAdapter adapter;
    /**
     * 闪灯按钮
     */
    private ImageView iv_light;
    /**
     * 获取全局变量实例
     */
    private App app;
    /**
     * 车牌号
     */
    private String caeNum;
    /**
     * 驾驶证信息的集合
     */
    private List<LicenseInfo> mList;
    /**
     * 是否选中车辆的标识，true选中，false未选中
     */
    private boolean isSelected = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_flash_light;
    }

    @Override
    public void initView() {

        app = (App) getApplication();

        topicTv = (TextView) findViewById(R.id.topicTv);
        topicTv.setText(getString(R.string.flash_light));

        backLay = (LinearLayout) findViewById(R.id.backLay);
        backLay.setOnClickListener(this);

        iv_light = (ImageView) findViewById(R.id.iv_light);
        iv_light.setOnClickListener(this);
        listView  = (ListView) findViewById(R.id.listView);

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_light:
                if (adapter != null){
                if (adapter.getSelectedPosition() == -1){
                    ToastUtil.show(this,getString(R.string.flash_choose_car));
                }else {
                    mPresenter.flashLight(caeNum,null,null);

                }
                }
                break;
            case R.id.backLay:
                finish();
                break;
        }
    }

    @Override
    public void getAllCarnumSuccess(ResponseDataBean<List<LicenseInfo>> param) {
        if (param != null){
            mList = param.getData();
            caeNum = app.getCommonParam().getCarNum();
            adapter = new FlahLightAdapter(this,mList);
            listView.setAdapter(adapter);
            adapter.setCarNum(caeNum);
            adapter.setData(mList);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if (isSelected){
                        adapter.setSelectedPosition(i);
                        isSelected = false;
                    }else {
                        adapter.setSelectedPosition(-1);
                        isSelected = true;
                    }



                    adapter.notifyDataSetInvalidated();
                }
            });
        }else {

        }


    }

    @Override
    public void getAllCarnumFail() {

    }

    @Override
    public void showError(Throwable e) {
       // ToastUtil.show(this,e.getMessage());
        ToastUtil.show(this,R.string.port_error);
    }

    /**
     * 闪车成功的回调
     */
    @Override
    public void flashedSuccess(ResponseDataBean<String> flashLightParm) {
        final Dialog dialog = new Dialog(this);
                View view1 = View.inflate(FlashLightActivity.this,R.layout.dialog_flashlight,null);
                ImageView imageView = (ImageView) view1.findViewById(R.id.title);
                imageView.setImageResource(R.mipmap.correct);
                TextView textView1 = (TextView) view1.findViewById(R.id.message1);
                TextView textView2 = (TextView) view1.findViewById(R.id.message2);
                textView1.setText(getString(R.string.flash_success));
                textView2.setText(getString(R.string.flash_success_notice));
                Button positiveButton = (Button) view1.findViewById(R.id.positiveButton);
                dialog.setContentView(view1);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                        finish();
                    }
                });
                dialog.show();
    }
    /**
     * 连接失败回调
     */
    @Override
    public void connetFail() {
        final Dialog dialog = new Dialog(this);
                View view1 = View.inflate(FlashLightActivity.this,R.layout.dialog_no_objection,null);
                TextView textView = (TextView) view1.findViewById(R.id.message);
                textView.setText(getString(R.string.no_connet_car));
                Button positiveButton = (Button) view1.findViewById(R.id.positiveButton);
                dialog.setContentView(view1);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialog.dismiss();
                        finish();
                    }
                });
                dialog.show();
    }

    /**
     * 未安装SIM的回调
     */
    @Override
    public void noSim() {
        final Dialog dialog = new Dialog(this);
        View view1 = View.inflate(FlashLightActivity.this,R.layout.dialog_flashlight,null);
        TextView textView1 = (TextView) view1.findViewById(R.id.message1);
        TextView textView2 = (TextView) view1.findViewById(R.id.message2);
        textView1.setText(getString(R.string.flash_no_sim));
        textView2.setText(getString(R.string.no_sim_to_use));
        Button positiveButton = (Button) view1.findViewById(R.id.positiveButton);
        dialog.setContentView(view1);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }
}
