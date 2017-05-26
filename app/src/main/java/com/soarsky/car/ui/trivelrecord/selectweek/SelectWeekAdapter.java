package com.soarsky.car.ui.trivelrecord.selectweek;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.bean.SelectWeek;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2017/2/24<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<b<br>
 * 程序功能：<br>
 * 该类为乘车等级选择时间数据适配器<br>
 */


public class SelectWeekAdapter extends BaseAdapter{

    private List<SelectWeek> listData;
    private LayoutInflater mInflater;

    public SelectWeekAdapter(Context context,List<SelectWeek> listData){
        mInflater=LayoutInflater.from(context);
        this.listData=listData;
    }

    @Override
    public int getCount() {
        if(listData==null){
            return 0;
        }
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView=mInflater.inflate(R.layout.item_select_week,null);
            viewHolder=new ViewHolder();
            viewHolder.isSelect= (TextView) convertView.findViewById(R.id.item_selectweek_select);
            viewHolder.weekDay= (TextView) convertView.findViewById(R.id.item_selectweek_day);
            convertView.setTag(viewHolder);

        }else{
            viewHolder= (ViewHolder) convertView.getTag();

        }
        viewHolder.weekDay.setText(listData.get(position).getWeekDay());
        if(listData.get(position).isSelect()){
           viewHolder.isSelect.setVisibility(View.VISIBLE);
        }else{
            viewHolder.isSelect.setVisibility(View.GONE);
        }

        return convertView;
    }


    class ViewHolder{
        TextView weekDay;
        TextView isSelect;


    }


    public List<SelectWeek> getListData() {
        return listData;
    }

    public void setListData(List<SelectWeek> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }
}
