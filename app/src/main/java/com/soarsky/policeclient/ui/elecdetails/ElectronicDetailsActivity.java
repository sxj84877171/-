package com.soarsky.policeclient.ui.elecdetails;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.ui.violation.BigPictureActivity;
import com.soarsky.policeclient.ui.violation.ViolationActivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/17<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  电子罚单详情页<br>
 */

public class ElectronicDetailsActivity extends BaseActivity implements View.OnClickListener {
    private ImageView detail_back;
    /**
     * 车牌号
     */

    private TextView carNum;
    /**
     * 日期
     */
    private TextView date;
    /**
     * 地点
     */
    private TextView address;
    /**
     * 违规原因
     */
    private TextView cause;
    /**
     * 扣分
     */
    private TextView score;
    /**
     * 罚款
     */
    private TextView money;

    /**
     * 电子罚单Gridview
     */
    private GridView gv_detail;
    /**
     * 图片路径
     */
    private String path;

    /**
     * 图片路径列表
     */
    private ArrayList<String> images = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.electronic_details;
    }

    @Override
    public void initView() {
        carNum = (TextView) findViewById(R.id.carNum);
        date = (TextView) findViewById(R.id.date);
        address = (TextView) findViewById(R.id.address);
        cause = (TextView) findViewById(R.id.cause);
        score = (TextView) findViewById(R.id.score);
        money = (TextView) findViewById(R.id.money);


        detail_back = (ImageView) findViewById(R.id.detail_back);
        detail_back.setOnClickListener(this);
        //填充数据
        initData();
        initImages();
        gv_detail = (GridView) findViewById(R.id.gv_detail);
        ElectronicDetailsAdapter adapter = new ElectronicDetailsAdapter(ElectronicDetailsActivity.this,images);
        gv_detail.setAdapter(adapter);//给gridview填充数据
        gv_detail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ElectronicDetailsActivity.this,BigPictureActivity.class);
                intent.putExtra("type","2");
                intent.putStringArrayListExtra("pics", images);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化图片
     */
    private void initImages() {
        if(path!=null){
            String[] imagesArray = path.split(",");
            for (String s:imagesArray) {
                images.add(s);
            }
        }

    }

    /**
     * 填充数据
     */
    private void initData() {
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();//.getExtras()得到intent所附带的额外数据
        carNum.setText(bundle.getString("carNum"));
        date.setText(bundle.getString("date"));
        address.setText(bundle.getString("address"));
        cause.setText(bundle.getString("cause"));
        score.setText(bundle.getString("score"));
        money.setText(bundle.getString("money"));
        path = bundle.getString("image");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.detail_back:
                finish();//关闭页面
                break;
        }
    }
}
