package com.soarsky.installationmanual.ui.main.fragment.collection;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;

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
 * 该类为 我的收藏界面 <br>
 */

public class CollectionActivity extends BaseActivity<CollectionPresent,CollectionModel> implements CollectionView {
    /**
     * 参考BrandItemActivity
     */
    private ExpandableListView expandableListView;
    /**
     * 参考BrandItemActivity
     */
    private List<String> groupArray = new ArrayList<String>();
    /**
     * 参考BrandItemActivity
     */
    private List<List<String>> childArray = new ArrayList<List<String>>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void initView() {
        groupArray.add("奥迪");
        groupArray.add("宝马");
        groupArray.add("奔驰");
        groupArray.add("保时捷");

        for (int i = 0; i < groupArray.size(); i++) {
            List<String> tempArray = new ArrayList<String>();
            for(int j = 1; j <= 5; j++){
                tempArray.add(groupArray.get(i) + " 2017款 3.0TSI 启程型" + j);
            }
            childArray.add(tempArray);
        }
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
    }

    @Override
    protected String getHeaderTitle() {
        return "我的收藏";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CollectionExpandableAdapter collectionExpandableAdapter = new CollectionExpandableAdapter(CollectionActivity.this);
        expandableListView.setAdapter(collectionExpandableAdapter);
        collectionExpandableAdapter.setData(groupArray,childArray);
        for (int i = 0; i < groupArray.size(); i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return true;
            }
        });
    }

    @Override
    public void showSuccess() {

    }

}
