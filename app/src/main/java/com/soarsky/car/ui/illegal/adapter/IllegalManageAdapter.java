package com.soarsky.car.ui.illegal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.soarsky.car.R;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/28<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：违章管理适配<br>
 * 该类为 IllegalManageAdapter<br>
 */


public class IllegalManageAdapter extends BaseExpandableListAdapter {
    /**
     * 上下文本
     */
    private Context context;
    /**
     * LayoutInflater
     */
    private LayoutInflater inflater;

    /**
     * 其构造函数
      * @param context 文本
     */
    public IllegalManageAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * 一级菜单
     * @return
     */
    @Override
    public int getGroupCount() {
        return 0;
    }

    /**
     * 二级菜单
     * @param i
     * @return
     */
    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    /**
     * 获取一级菜单item
     * @param i
     * @return
     */
    @Override
    public Object getGroup(int i) {
        return null;
    }

    /**
     * 获取二级菜单item
     * @param i -- group
     * @param i1 -- child
     * @return
     */
    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    /**
     * 获取一级菜单位置
     * @param i
     * @return
     */
    @Override
    public long getGroupId(int i) {
        return 0;
    }

    /**
     * 获取二级菜单位置
     * @param i -- group
     * @param i1 -- child
     * @return
     */
    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

        GroupHolder groupHolder = null;

        if(view == null){

            groupHolder = new GroupHolder();

            view = inflater.inflate(R.layout.illegal_address_list_item,null);
            groupHolder.illegalAddressTv = (TextView) view.findViewById(R.id.illegalAddressTv);
            groupHolder.illegaldealCentTv = (TextView) view.findViewById(R.id.illegalAddressTv);
            groupHolder.illegaldealFineTv = (TextView) view.findViewById(R.id.illegalAddressTv);
            groupHolder.illegaldealNotTv = (TextView) view.findViewById(R.id.illegalAddressTv);

            view.setTag(groupHolder);

        }else {

            groupHolder = (GroupHolder) view.getTag();

        }

        setGroupData(groupHolder,i);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {

        ChildHolder childHolder = null;

        if(view == null ){

            childHolder = new ChildHolder();
            view = inflater.inflate(R.layout.violation_deal_item_list,null);
            childHolder.dealAddresstv = (TextView) view.findViewById(R.id.dealAddresstv);
            childHolder.dealCenttv = (TextView) view.findViewById(R.id.dealCenttv);
            childHolder.dealMoneytv = (TextView) view.findViewById(R.id.dealMoneytv);
            childHolder.dealReasontv = (TextView) view.findViewById(R.id.dealReasontv);
            childHolder.dealTimetv = (TextView) view.findViewById(R.id.dealTimetv);
            childHolder.dealbtn = (Button) view.findViewById(R.id.dealbtn);

            view.setTag(childHolder);

        }else {

            childHolder = (ChildHolder) view.getTag();
        }

        setChildData(childHolder,i,i1);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    /**
     * 设置groupData
     * @param holder
     * @param position
     */
    public void setGroupData(GroupHolder holder,int position){

    }

    /**
     * 设置childData
     * @param holder
     * @param group
     * @param child
     */
    public void setChildData(ChildHolder holder,int group,int child){

    }


    class GroupHolder{
        TextView illegalAddressTv;
        TextView illegaldealNotTv;
        TextView illegaldealFineTv;
        TextView illegaldealCentTv;
    }

    class ChildHolder{
        /**
         * 时间
         */
        TextView dealTimetv;
        /**
         * 地址
         */
        TextView dealAddresstv;
        /**
         * 原因
         */
        TextView dealReasontv;
        /**
         * 分
         */
        TextView dealCenttv;
        /**
         * 钱
         */
        TextView dealMoneytv;

        Button dealbtn;
    }

}
