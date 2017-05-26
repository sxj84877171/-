package com.soarsky.car.ui.roadside.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.ui.carchange.CarListAdapter;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 苏云
 * 时间： 2016/12/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：suyun@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：dialog适配器
 * 该类为
 */


public class RoadSideBottomAdapter extends BaseAdapter{
    /**
     * 上下文
     */
    private Context context;
    /**
     * 数据源
     */
    private List<String> list;
    private LayoutInflater inflater;

    public RoadSideBottomAdapter(Context context,List<String> list){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
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

        RoadSideBottomAdapter.ViewHolder holder = null;

        if(view == null){
            holder = new ViewHolder();

            view = inflater.inflate(R.layout.road_side_dialog_bottom_list_item ,null);
            holder.item_dialog_tv = (TextView) view.findViewById(R.id.item_dialog_tv);
            view.setTag(holder);
        }else {

            holder = (ViewHolder) view.getTag();
        }

        holder.item_dialog_tv.setText(""+list.get(i)==null?"":list.get(i));

        return view;
    }

    public class ViewHolder{

        TextView item_dialog_tv;
    }

}
