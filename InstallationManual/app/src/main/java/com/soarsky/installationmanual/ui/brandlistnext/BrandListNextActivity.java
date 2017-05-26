package com.soarsky.installationmanual.ui.brandlistnext;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseActivity;
import com.soarsky.installationmanual.bean.BrandItemNextBean;

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
 * 该类为 品牌系列下的款式界面<br>
 */

public class BrandListNextActivity extends BaseActivity<BrandListNextPresent,BrandListNextModel> implements BrandListNextView {

    private ListView listView;
    /**
     * 系列
     */
    private String audi;
    /**
     * 品牌
     */
    private String brand;
    private List<String> data = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_area;
    }

    @Override
    public void initView() {
        brand = getIntent().getStringExtra("brand");
        audi = getIntent().getStringExtra("audi");
        listView = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getBrandListNext(brand,audi);
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
    @Override
    protected void initToolbar(Toolbar toolbar) {
        super.initToolbar(toolbar);
        toolbar.setTitle(audi);
    }

    @Override
    public void showData(List<BrandItemNextBean> brandItemNextBeanList) {
        if(brandItemNextBeanList!=null){
            for (BrandItemNextBean brandItemNextBean:brandItemNextBeanList){
                if(!TextUtils.isEmpty(brandItemNextBean.getParameter())){
                    data.add(brandItemNextBean.getParameter());
                }
            }
            listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,data));
        }

    }
}
