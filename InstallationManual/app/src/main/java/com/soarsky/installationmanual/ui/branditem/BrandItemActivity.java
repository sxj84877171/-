package com.soarsky.installationmanual.ui.branditem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.bean.BrandItemBean;
import com.soarsky.installationmanual.ui.brandlistnext.BrandListNextActivity;
import com.soarsky.installationmanual.view.sortlistview.SortModel;

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
 * 该类为 品牌下的子系列界面<br>
 */

public class BrandItemActivity extends BaseActivity<BrandItemPresent,BrandItemModel> implements BrandItemView {
    /**
     * ExpandableListView实例
     */
    private ExpandableListView expandableListView;
    /**
     * 组标题数组
     */
    private List<String> groupArray = new ArrayList<String>();
    /**
     * 子列表
     */
    private List<List<String>> childArray = new ArrayList<List<String>>();
    /**
     * 品牌名称
     */
    private String brand;
    /**
     * ExpandableListView实例对应的Adapter
     */
    private BrandItemExpandableAdapter brandItemExpandableAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void initView() {
        brand = getIntent().getStringExtra("brand");
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getBrandItem(brand);
        brandItemExpandableAdapter = new BrandItemExpandableAdapter(BrandItemActivity.this);
        expandableListView.setAdapter(brandItemExpandableAdapter);
        brandItemExpandableAdapter.setData(groupArray,childArray);
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
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(BrandItemActivity.this, BrandListNextActivity.class);
                intent.putExtra("brand",brand);
                intent.putExtra("audi",childArray.get(groupPosition).get(childPosition));
                startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public void showSuccess() {

    }
    @Override
    protected void initToolbar(Toolbar toolbar) {
        super.initToolbar(toolbar);
        toolbar.setTitle(brand);
    }

    @Override
    public void showData(List<BrandItemBean> brandItemBeenList) {
        if(brandItemBeenList!=null && brandItemBeenList.size()!=0){
            List<String> groupArrayTemp = new ArrayList<>();
            for (BrandItemBean brandItemBean:brandItemBeenList) {
                if(!TextUtils.isEmpty(brandItemBean.getCartype()) && !groupArrayTemp.contains(brandItemBean.getCartype())){
                    groupArrayTemp.add(brandItemBean.getCartype());
                }
            }
            List<List<String>> childArrayTemp = new ArrayList<List<String>>();
            for (String group: groupArrayTemp){
                List<String> childArrayTempTem= new ArrayList<>();
                for (BrandItemBean brandItemBean:brandItemBeenList) {
                    if(!TextUtils.isEmpty(brandItemBean.getAudi()) && group.equals(brandItemBean.getCartype())){
                        childArrayTempTem.add(brandItemBean.getAudi());
                    }
                }
                childArrayTemp.add(childArrayTempTem);
            }
            groupArray = groupArrayTemp;
            childArray = childArrayTemp;
            brandItemExpandableAdapter.setData(groupArray,childArray);
        }

    }
}
