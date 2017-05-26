package com.soarsky.car.ui.borrowandreturn.retur;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.ui.borrowandreturn.borrowrecord.BorrowRecords;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/11/30
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  借车车辆数据适配器类
 */

public class ReturnAdapter extends BaseAdapter {
    private Context context;
    private int selectedPosition = -1;// 选中的位置

    public void setList(List<BorrowRecords> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private List<BorrowRecords> list;

    public ReturnAdapter(Context context){
        this.context = context;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    @Override
    public int getCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder vh;
        if(convertView==null){
            vh=new ViewHolder();
            convertView=View.inflate(context,R.layout.item_confirmdriver,null);
            vh.wifiHot= (TextView) convertView.findViewById(R.id.item_wifihot);
            vh.iv_item = (ImageView) convertView.findViewById(R.id.iv_item);
            vh.iv_selected = (ImageView) convertView.findViewById(R.id.iv_selected);
            convertView.setTag(vh);

        }else{
            vh= (ViewHolder) convertView.getTag();

        }
        vh.wifiHot.setText(list.get(i).getCarnum());
//        vh.wifiHot.setText(carList.get(position).getCarNum());
        setViewColor(convertView,i);
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

    public BorrowRecords getChooseBorrowRecords(){
        if(list != null && list.size() >= selectedPosition){
            return list.get(selectedPosition);
        }
        return null;
    }


    public  int getSelectedPosition(){
        return selectedPosition;
    }



}
