package com.soarsky.installationmanual.ui.main.fragment.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.bean.AreaItemBean;
import com.soarsky.installationmanual.ui.completeuserinfo.CompleteUserInfoActivity;
import com.soarsky.installationmanual.ui.completeuserinfo.location.AreaView;

import java.util.ArrayList;
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
 * 该类为 历史记录界面<br>
 */

public class HistoryActivity extends BaseActivity<HistoryPresent,HistoryModel> implements HistoryView {

    private ListView listView;
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
        return "浏览历史";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            data.add("途锐 2017款 3.0TSI 启程型");
        }
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,data));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public void showSuccess() {

    }

}
