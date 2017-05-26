package com.soarsky.car.ui.home.map.route.plan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.soarsky.car.R;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/11<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：适配<br>
 * 该类为 MapRoutePlanAdapter<br>
 */


public class MapRoutePlanAdapter extends BaseAdapter{

    /**
     * 数据源
     */
    private List<DrivingRouteLine> list;
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
     * @param list 集合
     */
    public MapRoutePlanAdapter(Context context,List<DrivingRouteLine> list){
        super();
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
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
            view = inflater.inflate(R.layout.map_route_plan_list_item,null);
            holder.map_route_tv = (TextView) view.findViewById(R.id.map_route_tv);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        setData(holder,i);

        return view;
    }

    /**
     * 设置数据
     * @param holder ViewHolder
     * @param position 第几个
     */
    public void setData(ViewHolder holder,int position){

        String route ="线路" + (position + 1)+":"+"红绿灯数：" + list.get(position).getLightNum()+"个，"+"拥堵距离为：" + list.get(position).getCongestionDistance() + "米";

        holder.map_route_tv.setText(""+route);

    }

    public class ViewHolder{

        TextView map_route_tv;

    }
}
