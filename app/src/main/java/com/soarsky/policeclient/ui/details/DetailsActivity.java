package com.soarsky.policeclient.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.bean.CarDetailsDataBean;
import com.soarsky.policeclient.ui.elecdetails.ElectronicDetailsActivity;
import com.soarsky.policeclient.uitl.ToastUtil;

import java.util.List;

/**
 * andriod_police_app<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 车辆详情页面<br>
 */

public class DetailsActivity extends BaseActivity<DetailPresent,DetailModel> implements DetailView,View.OnClickListener {
    /**
     * 详情页车牌号
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
    private ListView listView;
    /**
     * 详情页数据适配器
     */
    private  DetailAdapter detailAdapter = null;
    /**
     * 车牌号
     */
    private String str;
    /**
     * 参数bean类
     */
    CarDetailsDataBean dataBean;
    /**
     * 车辆详情列表
     */
    private List<CarDetailsDataBean.IlistBean> data;

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_details;
    }

    @Override
    public void initView() {
        detail_back = (ImageView) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);

        tv_chepai = (TextView) findViewById(R.id.tv_chepai);
        color = (TextView) findViewById(R.id.tv_color);
        type = (TextView) findViewById(R.id.tv_type);
        num = (TextView) findViewById(R.id.tv_num);
        date = (TextView) findViewById(R.id.tv_date);



        listView = (ListView) findViewById(R.id.listView);

        detailAdapter = new DetailAdapter(DetailsActivity.this);


       Intent intent=getIntent();
       Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据
       //getString()返回指定key的值
       str = bundle.getString("carNum");
      tv_chepai.setText(str);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //VibratorUtils.getInstance(this).cancel();
        //TODO  carnum 自己获取的
        mPresenter.getCarDetail(str);
        //设置请求参数

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.detail_back:
                finish();//关闭页面
                break;
        }
    }

    /**
     * 车辆详情的数据
     * @param detailsCarParam
     */
    @Override
    public void showData(CarDetailsDataBean detailsCarParam) {
        color.setText(detailsCarParam.getColor());
        type.setText(detailsCarParam.getUsercharacter());
        date.setText(detailsCarParam.getIssuedate());
        num.setText(detailsCarParam.getVin());

        data = (List<CarDetailsDataBean.IlistBean>) detailsCarParam.getIlist();
        detailAdapter.setData(data);
        listView.setAdapter(detailAdapter);
        //给listview条目的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent();
                intent.setClass(DetailsActivity.this, ElectronicDetailsActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("carNum",str);//给intent添加额外数据，key为“carNum”,key值为"Intent Demo"
                intent.putExtra("date", data.get(i).getStime());
                intent.putExtra("address", data.get(i).getAddress());
                intent.putExtra("cause", data.get(i).getInf());
                intent.putExtra("score", data.get(i).getCent());
                intent.putExtra("money", data.get(i).getMonery());
                intent.putExtra("image", data.get(i).getImageUrl());
                startActivity(intent);
            }
        });

    }



    @Override
    public void showFail(String msg) {
       ToastUtil.show(DetailsActivity.this,msg);
    }


    @Override
    public void showProgess() {

    }

    @Override
    public void stopProgess() {

    }
}
