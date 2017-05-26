package com.soarsky.car.ui.validdriverlistener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.bean.Car;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者：何明辉
 * 时间： 2016/11/15.
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为 listview 适配器
 */

public class WifiListAdapter extends BaseAdapter{
    private List<Car> carList;
    private Context context;
    private LayoutInflater layoutInflater;

    private int selectedPosition = -1;// 选中的位置

    public WifiListAdapter(Context context,List<Car> carList){
        this.context=context;
        this.carList=carList;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(carList==null){

            return 0;
        }
        return  carList.size();
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
        notifyDataSetChanged();

    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    @Override
    public Object getItem(int position) {
        return carList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            vh=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.item_confirmdriver,null);
            vh.wifiHot= (TextView) convertView.findViewById(R.id.item_wifihot);
            vh.iv_item = (ImageView) convertView.findViewById(R.id.iv_item);
            vh.iv_selected = (ImageView) convertView.findViewById(R.id.iv_selected);
            convertView.setTag(vh);

        }else{
            vh= (ViewHolder) convertView.getTag();

        }
        vh.wifiHot.setText(carList.get(position).getCarNum());
//        vh.wifiHot.setText(WifiHotUtils.getInstance().getCarNum(carList.get(position).getCarNum()));
//        vh.wifiHot.setText(carList.get(position).getCarNum());
        setViewColor(convertView,position);
        return convertView;
    }

    class ViewHolder{
        TextView wifiHot;
        ImageView iv_item;
        ImageView iv_selected;
    }

    public void setViewColor(View view,int position){

        ViewHolder holder = (ViewHolder) view.getTag();
        if(selectedPosition == position){
            holder.iv_item.setImageResource(R.mipmap.car_num_s);
            holder.wifiHot.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.iv_selected.setVisibility(View.VISIBLE);
        }else{
            holder.wifiHot.setTextColor(context.getResources().getColor(R.color.gray));
            holder.iv_item.setImageResource(R.mipmap.car_num_n);
            holder.iv_selected.setVisibility(View.INVISIBLE);
        }
    }

}
