package com.soarsky.installationmanual.ui.completeuserinfo.location;

import android.app.Dialog;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
 * 该类为 选择地区界面<br>
 */

public class AreaActivity extends BaseActivity<AreaPresent,AreaModel> implements AreaView {
    /**
     * 显示地区列表的ListView
     */
    private ListView listView;
    /**
     * 地区列表
     */
    private List<AreaItemBean> data = new ArrayList<>();
    /**
     * 显示地区列表的ListView对应的Adapter
     */
    private AreaAdapter areaAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_area;
    }

    @Override
    public void initView() {

        listView = (ListView) findViewById(R.id.listView);
        areaAdapter = new AreaAdapter(this,data);
        listView.setAdapter(areaAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AreaActivity.this,CityActivity.class);
                intent.putExtra("itemCode",data.get(position).getItemCode());
                startActivity(intent);
            }
        });
    }

    @Override
    protected String getHeaderTitle() {
        return "所在地区";
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.getAera("","0");
    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void showSuccess(List<AreaItemBean> list) {
        data = list;
        areaAdapter.setData(list);
    }

    /**
     * 地区列表对应的Adapter
     */
    public class AreaAdapter extends BaseAdapter{

        private Context context;
        /**
         * 地区列表
         */
        private List<AreaItemBean> data;
        public AreaAdapter(Context context, List<AreaItemBean> data){
            this.context = context;
            this.data = data;
        }

        public void setData(List<AreaItemBean> data) {
            this.data = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if(view == null){
                view = View.inflate(context,R.layout.area_item,null);
            }
            TextView textView = (TextView) view.findViewById(R.id.location);
            textView.setText(data.get(position).getItemName());
            return view;
        }
    }
}
