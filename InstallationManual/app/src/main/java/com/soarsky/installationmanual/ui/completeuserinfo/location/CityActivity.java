package com.soarsky.installationmanual.ui.completeuserinfo.location;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.bean.AreaItemBean;
import com.soarsky.installationmanual.ui.completeuserinfo.CompleteUserInfoActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 选择城市界面<br>
 */

public class CityActivity extends BaseActivity<AreaPresent,AreaModel> implements AreaView {

    private ListView listView;
    /**
     * 城市列表
     */
    private List<AreaItemBean> list;
    @Override
    public int getLayoutId() {
        return R.layout.activity_area;
    }

    @Override
    public void initView() {
        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected String getHeaderTitle() {
        return "所在地区";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String itemCode = intent.getStringExtra("itemCode");
        mPresenter.getAera(itemCode,"1");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(CityActivity.this, CompleteUserInfoActivity.class);
                in.putExtra("itemCode",list.get(position).getItemCode());
                in.putExtra("itemName",list.get(position).getItemName());
                startActivity(in);
            }
        });
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showSuccess(List<AreaItemBean> list) {
        this.list = list;
        List<String> dataList = new ArrayList<>();
        for (AreaItemBean areaItemBean : list){
            dataList.add(areaItemBean.getItemName());
        }
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,dataList));
    }
}
