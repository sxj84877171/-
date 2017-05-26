package com.soarsky.car.ui.licenseinformation.cardetails.fragment.carrecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.soarsky.car.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/29<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  可扩展的listview数据适配器是<br>
 */

public class UseRecordExpandadapter extends BaseExpandableListAdapter {
    private Context context;
    // 准备一级列表中显示的数据
    private List<List<String>> accountChild = new ArrayList<List<String>>();

    public UseRecordExpandadapter(Context context, List<List<String>> accountChild){
        this.context = context;
        this.accountChild = accountChild;
    }
    @Override
    public int getGroupCount() {
        return accountChild.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return accountChild.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return accountChild.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return accountChild.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view =inflater.inflate(R.layout.car_details_item, null);
        }
        //一级列表中显示的标题
        TextView txtAddDate = (TextView) view.findViewById(R.id.month);

        /*txtAddDate.setText("本月");*/
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.use_car_record_item, null);
        }
        TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
        TextView tv_cause = (TextView) view.findViewById(R.id.tv_cause);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
