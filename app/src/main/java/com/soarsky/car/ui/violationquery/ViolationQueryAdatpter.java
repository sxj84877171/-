package com.soarsky.car.ui.violationquery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.car.R;

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
 * 程序功能：
 * 该类为
 */

public class ViolationQueryAdatpter extends BaseAdapter{


    private Context context;

    private List<ViolationQueryParam.DataBean.IlistBean> list;

    private LayoutInflater inflater;

    public ViolationQueryAdatpter(Context context,List<ViolationQueryParam.DataBean.IlistBean> list){
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
            view = inflater.inflate(R.layout.violation_query_item_list, null);
            holder.querytimetv = (TextView) view.findViewById(R.id.querytimetv);
            holder.queryAddresstv = (TextView) view.findViewById(R.id.queryAddresstv);
            holder.queryCenttv = (TextView) view.findViewById(R.id.queryCenttv);
            holder.queryMoneytv = (TextView) view.findViewById(R.id.queryMoneytv);
            holder.queryreasontv = (TextView) view.findViewById(R.id.queryreasontv);

            view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag();
        }
        setData(holder,i);
        return view;
    }

    public void setData(ViewHolder holder,int position){
        holder.querytimetv.setText(""+list.get(position).getStime()==null?"":list.get(position).getStime());
        holder.queryAddresstv.setText(""+list.get(position).getAddress()==null?"":list.get(position).getAddress());
        holder.queryMoneytv.setText(""+list.get(position).getMonery()==null?"":list.get(position).getMonery());
        holder.queryCenttv.setText(""+list.get(position).getCent()== null?"":list.get(position).getCent());
        holder.queryreasontv.setText(""+list.get(position).getInf()==null?"":list.get(position).getInf());
    }

    public class ViewHolder{

        TextView querytimetv;

        TextView queryCenttv;

        TextView queryAddresstv;

        TextView queryreasontv;

        TextView queryMoneytv;

    }

}
