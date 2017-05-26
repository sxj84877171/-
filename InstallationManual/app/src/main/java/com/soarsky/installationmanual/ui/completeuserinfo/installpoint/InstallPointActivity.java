package com.soarsky.installationmanual.ui.completeuserinfo.installpoint;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.bean.InPoBean;
import com.soarsky.installationmanual.bean.VeAdBean;
import com.soarsky.installationmanual.ui.completeuserinfo.CompleteUserInfoActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 选择安装点界面<br>
 */

public class InstallPointActivity extends BaseActivity<InstallPointPresent,InstallPointModel> implements InstallPointView {
    /**
     * 安装点ListView
     */
    private ListView listView;
    /**
     * 安装点列表
     */
    private List<InPoBean> list;
    @Override
    public int getLayoutId() {
        return R.layout.activity_vehicle_administration;
    }

    @Override
    public void initView() {
        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected String getHeaderTitle() {
        return "车管所选择";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String itemCode = intent.getStringExtra("itemCode");
        mPresenter.getInstallPoint(itemCode);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent in = new Intent(InstallPointActivity.this, CompleteUserInfoActivity.class);
                in.putExtra("installId",list.get(position).getId());
                in.putExtra("installName",list.get(position).getName());
                startActivity(in);
            }
        });
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showSuccess(List<InPoBean> list) {
        this.list = list;
        List<String> dataList = new ArrayList<>();
        for (InPoBean inPoBean : list){
            dataList.add(inPoBean.getName());
        }
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,dataList));
    }
}
