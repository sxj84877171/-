package com.soarsky.car.ui.trivelrecord.carlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.bean.Car;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2017/2/24
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为车辆选择数据适配器<br>
 */


public class CarlistAdapter extends BaseAdapter{
    private LayoutInflater layoutInflater;
    private List<Car> carList;
    public CarlistAdapter(Context context,List<Car> carList){
        layoutInflater=LayoutInflater.from(context);
        this.carList=carList;




    }

    @Override
    public int getCount() {
        if(carList==null){
        return 0;}
        return carList.size();
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
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=layoutInflater.inflate(R.layout.item_carlist,null);
            viewHolder=new ViewHolder();
            viewHolder.carNum= (TextView) convertView.findViewById(R.id.item_carList_carNum);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.carNum.setText(carList.get(position).getCarNum());
        return convertView;
    }


    class  ViewHolder{
        TextView carNum;
    }


    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
        notifyDataSetChanged();
    }
}
