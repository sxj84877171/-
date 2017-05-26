package com.soarsky.policeclient.ui.blacklist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.policeclient.App;
import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.bean.CarDetailsDataBean;
import com.soarsky.policeclient.data.local.db.bean.BlackCar;
import com.soarsky.policeclient.data.local.db.dao.BlackCarDao;
import com.soarsky.policeclient.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2017/3/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 黑名单车辆详情页面<br>
 */

public class BlackListCarDetailsActivity extends BaseActivity<BlackListCarDetailsPresent,BlackListCarDetailsModel> implements View.OnClickListener,BlackListCarDetailsView {
    /**
     * 标题
     */
    private TextView topic;
    /**
     * 黑名单车牌号
     */
    private TextView tv_chepai;
    /**
     * 车身颜色
     */
    private TextView color;
    /**
     * 车辆类型
     */
    private TextView type;
    /**
     * 车架号
     */
    private TextView num;
    /**
     * 年检日期
     */
    private TextView date;
    /**
     * 返回按钮
     */
    private ImageView detail_back;
    /**
     * 黑名单原因列表
     */
    private ListView listView;
    /**
     * 处理按钮
     */
    private TextView dealTv;
    /**
     * 原因列表数据适配器
     */
    private BlackListCauseAdapter adapter;
    private String ptype;
    /**
     * 车牌号
     */
    private String str;

    @Override
    public int getLayoutId() {
        return R.layout.activity_blacklist_car_details;
    }

    @Override
    public void initView() {
        topic = (TextView) findViewById(R.id.topic);
        topic.setText(getString(R.string.bl_topic));
        color = (TextView) findViewById(R.id.tv_color);
        type = (TextView) findViewById(R.id.tv_type);
        num = (TextView) findViewById(R.id.tv_num);
        date = (TextView) findViewById(R.id.tv_date);
        dealTv = (TextView) findViewById(R.id.dealTv);
        dealTv.setOnClickListener(this);
        tv_chepai = (TextView) findViewById(R.id.tv_chepai);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new BlackListCauseAdapter(BlackListCarDetailsActivity.this);
        listView.setAdapter(adapter);
        Intent intent=getIntent();
        //getString()返回指定key的值
        str = intent.getStringExtra("carNum");
        ptype = intent.getStringExtra("ptype");
        String reason = intent.getStringExtra("reason");
        if("已处理".equals(reason)){
            dealTv.setVisibility(View.INVISIBLE);
        }else {
            dealTv.setVisibility(View.VISIBLE);
        }
        tv_chepai.setText(str);

        detail_back = (ImageView) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);
        tv_chepai = (TextView) findViewById(R.id.tv_chepai);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showProgress();
        mPresenter.getCarDetail(str);
        List<String> reasons = new ArrayList<>();
        List<BlackCar> blackCars = App.getApp().getDaoSession().getBlackCarDao().queryBuilder().where(BlackCarDao.Properties.Carnum.eq(str)).list();
        for (BlackCar blackCar:blackCars){
            reasons.add(blackCar.getReason());
        }
        adapter.setData(reasons);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.detail_back:
                finish();//关闭页面
                break;
            case R.id.dealTv://处理
                new AlertDialog.Builder(this).setTitle("确定处理吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“确认”后的操作
                                showProgress();
                                mPresenter.dealBlack(str,ptype, App.getApp().getUserName());

                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“返回”后的操作,这里不设置没有任何操作
                            }
                        }).show();
                break;
        }
    }

    @Override
    public void showProgess() {

    }

    @Override
    public void stopProgess() {

    }

    @Override
    public void onError() {
        dismissProgress();
        ToastUtil.show(this,"网络连接失败");
    }

    @Override
    public void onFail(String s) {
        dismissProgress();
        ToastUtil.show(this,s);
    }

    @Override
    public void onSuccess() {
        dismissProgress();
        ToastUtil.show(this,"提交成功");
        setResult(1);
        finish();
    }

    /**
     * 车辆详情的数据
     * @param detailsCarParam
     */
    @Override
    public void showData(CarDetailsDataBean detailsCarParam) {
        dismissProgress();
        color.setText(detailsCarParam.getColor());
        type.setText(detailsCarParam.getUsercharacter());
        date.setText(detailsCarParam.getIssuedate());
        num.setText(detailsCarParam.getVin());
    }
}
