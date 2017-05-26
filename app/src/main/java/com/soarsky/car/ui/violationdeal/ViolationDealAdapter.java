package com.soarsky.car.ui.violationdeal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.ui.violationquery.ViolationQueryAdatpter;

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
 * 程序功能：违章信息列表的适配
 * 该类为
 */

public class ViolationDealAdapter extends BaseAdapter{

    private Context context;

    private List<ViolationDealParam.DataBean.IlistBean> list;

    private LayoutInflater inflater;

    public ViolationDealAdapter(Context context,List<ViolationDealParam.DataBean.IlistBean> list){
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

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){

            holder = new ViewHolder();

            view = inflater.inflate(R.layout.violation_deal_item_list, null);

            holder.dealAddresstv = (TextView) view.findViewById(R.id.dealAddresstv);
            holder.dealCenttv = (TextView) view.findViewById(R.id.dealCenttv);
            holder.dealMoneytv = (TextView) view.findViewById(R.id.dealMoneytv);
            holder.dealReasontv = (TextView) view.findViewById(R.id.dealReasontv);
            holder.dealTimetv = (TextView) view.findViewById(R.id.dealTimetv);
            holder.dealbtn = (Button) view.findViewById(R.id.dealbtn);

            view.setTag(holder);

        }else {

            holder = (ViewHolder) view.getTag();
        }

        setData(holder,i);

        return view;
    }

    public void setData(ViewHolder holder, int position){
        holder.dealTimetv.setText(""+list.get(position).getStime()==null?"":list.get(position).getStime());
        holder.dealAddresstv.setText(""+list.get(position).getAddress()==null?"":list.get(position).getAddress());
        holder.dealMoneytv.setText(""+list.get(position).getMonery()== null?"":list.get(position).getMonery());
        holder.dealCenttv.setText(""+list.get(position).getCent()== null?"":list.get(position).getCent());
        holder.dealReasontv.setText(""+list.get(position).getInf()==null?"":list.get(position).getInf());
    }


    public class ViewHolder{

        TextView dealTimetv;

        TextView dealAddresstv;

        TextView dealReasontv;

        TextView dealCenttv;

        TextView dealMoneytv;

        Button dealbtn;

    }

}
