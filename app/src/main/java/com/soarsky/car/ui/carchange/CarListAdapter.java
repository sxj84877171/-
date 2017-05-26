package com.soarsky.car.ui.carchange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.ui.login.CarNumParam;

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
 * 程序功能：车辆展示的适配
 * 该类为
 */


public class CarListAdapter extends BaseAdapter{

    private List<CarNumParam.DataBean> list;

    private Context context;

    private LayoutInflater inflater;
    /**
     * 选中的位置
     */
    private int selectedPosition = -1;

    public CarListAdapter(Context context,List<CarNumParam.DataBean> list){
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

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder = null;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.car_change_list_item,null);

            holder.leftIconView = (ImageView) view.findViewById(R.id.leftIconView);
            holder.car_num_tv = (TextView)view.findViewById(R.id.car_num_tv);
            holder.car_identify_tv = (TextView) view.findViewById(R.id.car_identify_tv);
            holder.rightIconView = (ImageView) view.findViewById(R.id.rightIconView);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        setData(holder,i);
        setViewColor(view,i);
        return view;
    }

    /**
     * 展示获取数据源
     * @param holder
     * @param position
     */
    public void setData(ViewHolder holder,int position){
        holder.car_num_tv.setText(""+list.get(position).getPlateno());
        holder.car_identify_tv.setText(""+list.get(position).getVin());
    }

    /**
     * 选定的颜色切换
     * @param view
     * @param position
     */
    public void setViewColor(View view,int position){

        ViewHolder holder = (ViewHolder) view.getTag();

        if(selectedPosition == position){
            holder.rightIconView.setVisibility(View.VISIBLE);
            holder.leftIconView.setVisibility(View.VISIBLE);
        }else{
            holder.rightIconView.setVisibility(View.GONE);
            holder.leftIconView.setVisibility(View.GONE);
        }
    }

    /**
     * xml对应的实体类
     */
    public class ViewHolder{

        public ImageView leftIconView;

        public TextView car_num_tv;

        public TextView car_identify_tv;

        public ImageView rightIconView;

    }

}
