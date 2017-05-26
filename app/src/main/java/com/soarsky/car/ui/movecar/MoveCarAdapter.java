package com.soarsky.car.ui.movecar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.bean.Car;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/30<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为请人移车，附近车辆数据适配器类<br>
 */

public class MoveCarAdapter extends BaseAdapter {
    /**
     * 选中的位置
     */
    private int selectedPosition = -1;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 车的集合
     */
    private List<Car> list;

    /**
     * 初始化上下文
     * @param context 上下文
     */
    public MoveCarAdapter(Context context){
        this.context = context;
    }
    public void setData(List<Car> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public  int getSelectedPosition(){
        return selectedPosition;
    }
    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == view){
            view = View.inflate(context, R.layout.item_local_car,null);
            holder = new ViewHolder();
            holder.iv_selected = (ImageView) view.findViewById(R.id.iv_selected);
            holder.tv_car_um = (TextView) view.findViewById(R.id.tv_car_um);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_car_um.setText(list.get(i).getCarNum());
        setViewColor(view,i);
        return view;
    }
    class ViewHolder{
        TextView tv_car_um;
        ImageView iv_selected;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }
    public void setViewColor(View view,int position){

        ViewHolder holder = (ViewHolder) view.getTag();
        if(selectedPosition == position){
            holder.iv_selected.setImageResource(R.mipmap.selected_gou);
        }else{
            holder.iv_selected.setImageResource(R.mipmap.gou_grey);
        }
    }
}
