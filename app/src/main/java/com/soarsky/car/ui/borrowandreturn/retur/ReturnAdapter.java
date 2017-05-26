package com.soarsky.car.ui.borrowandreturn.retur;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.bean.BorrowRecords;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/30<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  借车车辆数据适配器类<br>
 */

public class ReturnAdapter extends BaseAdapter {
    /**
     * 上下文
     */
    private Context context;
    /**
     * 选中的位置
     */
    private int selectedPosition = -1;

    public void setList(List<BorrowRecords> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    /**
     * 借车记录的集合
     */
    private List<BorrowRecords> list;

    /**
     * 构造函数
     * 初始化上下文
     * @param context
     */
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

    /**
     * 设置选中和未选中的条目显示的颜色
     * @param view 条目布局
     * @param position 条目的位置
     */
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

    /**
     * 拿到选中的车辆
     * @return 借车记录
     */
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
