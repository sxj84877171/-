package com.soarsky.car.ui.licenseinformation.cardetails.fragment.carrecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.bean.UseCarRecordParam;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：适配器<br>
 * 该类为 UseCarRecordAdapter<br>
 */


public class UseCarRecordAdapter extends BaseAdapter{

    /**
     * 上下文
     */
    private Context context;
    /**
     * 数据源
     */
    private List<UseCarRecordParam> data;
    /**
     * inflater
     */
    private LayoutInflater inflater;

    public UseCarRecordAdapter(Context context,List<UseCarRecordParam> data){
        super();
        this.context = context;
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
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
            view = inflater.inflate(R.layout.user_car_record_list_item,null);

            holder.driver_tv = (TextView) view.findViewById(R.id.driver_tv);
            holder.license_num_tv = (TextView) view.findViewById(R.id.license_num_tv);
            holder.user_time_tv = (TextView) view.findViewById(R.id.user_time_tv);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        setData(holder,i);
        return view;
    }

    public void setData(ViewHolder holder,int position){

        holder.user_time_tv.setText(""+data.get(position).getStime()+"-"+data.get(position).getEtime());
        holder.license_num_tv.setText(""+data.get(position).getIdno());
        holder.driver_tv.setText(""+data.get(position).getUsername());

    }

    public class ViewHolder{

        TextView driver_tv;

        TextView license_num_tv;

        TextView user_time_tv;
    }

}
