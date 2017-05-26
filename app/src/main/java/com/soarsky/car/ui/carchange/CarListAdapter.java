package com.soarsky.car.ui.carchange;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.bean.LicenseInfo;

import java.util.List;

import static com.soarsky.car.Constants.FRIEND;
import static com.soarsky.car.Constants.PERSONAL;
import static com.soarsky.car.R.mipmap.borrow;

/**
 * Andriod_Car_App <br>
 * 作者： 苏云 <br>
 * 时间： 2016/12/9 <br>
 * 公司：长沙硕铠电子科技有限公司 <br>
 * Email：suyun@soarsky-e.com <br>
 * 公司网址：http://www.soarsky-e.com <br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼 <br>
 * 版本：1.0.0.0 <br>
 * 邮编：410000 <br>
 * 程序功能：车辆展示的适配 <br>
 * 该类为  CarListAdapter <br>
 */


public class CarListAdapter extends BaseAdapter{
    /**
     * 数据源
     */
    private List<LicenseInfo> list;
    /**
     * 上下文本
     */
    private Context context;
    /**
     * LayoutInflater
     */
    private LayoutInflater inflater;
    /**
     * 选中的位置
     */
    private int selectedPosition = -1;

    /**
     * 车牌号
     */
    private String carNum;

    /**
     * 其构造方法
     * @param context 文本
     * @param list 集合
     */
    public CarListAdapter(Context context,List<LicenseInfo> list,String carNum){
        super();
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.carNum = carNum;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
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
            view = inflater.inflate(R.layout.item_switch_car,null);

            holder.iv_whose = (ImageView) view.findViewById(R.id.iv_whose);
            holder.imageView = (ImageView) view.findViewById(R.id.imageView);
            holder.type = (TextView) view.findViewById(R.id.type);
            holder.tv_car_num = (TextView) view.findViewById(R.id.car_num_tv);
            holder.tv_car_color = (TextView) view.findViewById(R.id.tv_car_color);
            holder.tv_whose = (TextView) view.findViewById(R.id.tv_whose);
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
     * @param holder ViewHolder
     * @param position 第几行
     */
    public void setData(ViewHolder holder,int position){
        holder.tv_car_num.setText(""+list.get(position).getPlateno());
        holder.tv_car_color.setText("("+list.get(position).getColor()+")");
        holder.type.setText(""+list.get(position).getModel());
        holder.imageView.setImageResource(R.mipmap.no_selected);

        if (FRIEND == list.get(position).getSign()){
            holder.iv_whose.setImageResource(R.mipmap.friend);
            holder.tv_whose.setText(context.getString(R.string.friend));
            holder.tv_whose.setTextColor(Color.parseColor("#93d150"));
        }else if (PERSONAL == list.get(position).getSign()){
            holder.iv_whose.setImageResource(R.mipmap.personal);
            holder.tv_whose.setText(context.getString(R.string.who_use));
            holder.tv_whose.setTextColor(Color.parseColor("#f8a920"));
        }else {
            holder.iv_whose.setImageResource(borrow);
            holder.tv_whose.setText(context.getString(R.string.borrow));
            holder.tv_whose.setTextColor(Color.parseColor("#708feb"));
        }
        if (position == selectedPosition){
            holder.imageView.setImageResource(R.mipmap.selected_);
            setSelectedPosition(position);
        }
    }

    public  void initChoose(){
        for(int i= 0 ; i < list.size() ;i++){
            if (carNum.equals(list.get(i).getPlateno())){
                selectedPosition = i ;
            }
        }
    }

    /**
     * 选定的颜色切换
     * @param view android view
     * @param position  第几行
     */
    public void setViewColor(View view,int position){

        ViewHolder holder = (ViewHolder) view.getTag();
        if(selectedPosition == position){
            holder.imageView.setImageResource(R.mipmap.selected_);
        }else{
            holder.imageView.setImageResource(R.mipmap.no_selected);
        }
    }

    /**
     * xml对应的实体类
     */
    public class ViewHolder{
        /**
         * 车牌号
         */
        TextView tv_car_num;
        /**
         * 车身颜色
         */
        TextView tv_car_color;
        /**
         * 类型
         */
        TextView type;
        /**
         * 使用人
         */
        TextView tv_whose;
        /**
         * view
         */
        ImageView iv_whose;
        /**
         * icon
         */
        ImageView imageView;

    }

}
