package com.soarsky.car.ui.flashlight;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.bean.LicenseInfo;

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
 * 该类为 闪灯找车页面数据适配器类<br>
 */

public class FlahLightAdapter extends BaseAdapter {
    /**
     *  选中的位置
     */
    private int selectedPosition = -1;
    /**
     * 上下文
     */
    private  Context context;
    /**
     * 车牌号
     */
    private String carNum;
    /**
     * 行驶证信息的集合
     */
    private List<LicenseInfo> mList;

    /**
     * 构造函数
     * 初始化参数上下文、mList
     * @param context 上下文
     * @param mList 行驶证信息
     */
    public FlahLightAdapter(Context context,List<LicenseInfo> mList){
        this.context = context;
        this.mList = mList;
    }


    public void setData(List<LicenseInfo> mList){
        this.mList = mList;
    }
    public void setCarNum(String carNum){
        this.carNum = carNum;
    }
    @Override
    public int getCount() {
        if (mList.size()>0){
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == view){
            view = View.inflate(context, R.layout.item_flash_light,null);
            holder = new ViewHolder();
            holder.iv_selected = (ImageView) view.findViewById(R.id.iv_selected);
            holder.tv_car_um = (TextView) view.findViewById(R.id.tv_car_um);
            holder.tv_color = (TextView) view.findViewById(R.id.tv_color);
            holder.tv_type = (TextView) view.findViewById(R.id.tv_type);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tv_car_um.setText(carNum);
        holder.tv_color.setText("( "+mList.get(i).getColor()+" )");
        holder.tv_type.setText(mList.get(i).getModel());
        setViewColor(view,i);
        return view;
    }
    class ViewHolder{
        TextView tv_car_um;
        TextView tv_color;
        TextView tv_type;
        ImageView iv_selected;
    }
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }
    public int getSelectedPosition() {
        return selectedPosition;
    }
    public void setViewColor(View view,int position){

        ViewHolder holder = (ViewHolder) view.getTag();
        if(selectedPosition == position ){
                holder.iv_selected.setImageResource(R.mipmap.selected_gou);

        }else{
            holder.iv_selected.setImageResource(R.mipmap.gou_grey);
        }
    }
}
