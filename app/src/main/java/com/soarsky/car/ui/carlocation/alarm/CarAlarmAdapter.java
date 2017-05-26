package com.soarsky.car.ui.carlocation.alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.car.R;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/9
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：防盗记录列表适配
 * 该类为
 */

public class CarAlarmAdapter extends BaseAdapter{
    /**
     * 上下文本
     */
    public Context context;

    private LayoutInflater inflater;
    /**
     * 数据源
     */
    private List<CarAlarmParam.DataBean> list;

    /**
     * 构造函数
     * @param context
     * @param list
     */
    public CarAlarmAdapter(Context context,List<CarAlarmParam.DataBean> list){
        super();
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);

    }

    /**
     * 数据重构
     * @param _list
     */
    public void setList(List<CarAlarmParam.DataBean> _list){
        this.list = _list;
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;

        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.car_alarm_list_item,null);
            holder.car_alarm_item_license_num_tv = (TextView) view.findViewById(R.id.car_alarm_item_license_num_tv);
            holder.car_alarm_item_position_tv = (TextView) view.findViewById(R.id.car_alarm_item_position_tv);
            holder.car_alarm_item_time_tv = (TextView) view.findViewById(R.id.car_alarm_item_time_tv);

            view.setTag(holder);

        }else {
            holder = (ViewHolder)view.getTag();
        }

        setData(holder,i);

        return view;
    }

    /**
     * 数据源展示
     * @param holder
     * @param position
     */
    private void setData(ViewHolder holder,int position){

        holder.car_alarm_item_time_tv.setText(""+list.get(position).getStimer());
        holder.car_alarm_item_position_tv.setText(""+list.get(position).getAddress());
        holder.car_alarm_item_license_num_tv.setText(""+list.get(position).getCarnum());

    }

    public class ViewHolder{
        TextView car_alarm_item_time_tv;
        TextView car_alarm_item_position_tv;
        TextView car_alarm_item_license_num_tv;
    }
}
