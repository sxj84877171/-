package com.soarsky.installationmanual.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.soarsky.installationmanual.R;

import java.util.ArrayList;
import java.util.List;
/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2017/2/16<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 ExpandableListView的Adapter基类<br>
 */
public class BaseExpandableAdapter extends BaseExpandableListAdapter {

    public void setData(List<String> groupArray, List<List<String>> childArray) {
        this.groupArray = groupArray;
        this.childArray = childArray;
        notifyDataSetChanged();
    }

    /**
     * 组标题数组
     */
    private List<String> groupArray = new ArrayList<>();
    /**
     * 子列表
     */
    private List<List<String>> childArray = new ArrayList<>();
    Context context;

    public BaseExpandableAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childArray.get(groupPosition).get(childPosition);
    }
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    @Override
    public int getChildrenCount(int groupPosition) {
        return childArray.get(groupPosition).size();
    }
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        String childStr = childArray.get(groupPosition).get(childPosition);
        convertView = View.inflate(context, R.layout.child, null);
        TextView childTv = (TextView) convertView;
        childTv.setText(childStr);
        return convertView;
    }
    @Override
    // group method stub
    public Object getGroup(int groupPosition) {
        return groupArray.get(groupPosition);
    }
    @Override
    public int getGroupCount() {
        return groupArray.size();
    }
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String groupStr = groupArray.get(groupPosition);
        convertView = View.inflate(context, R.layout.group, null);
        TextView childTv = (TextView) convertView;
        childTv.setText(groupStr);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}