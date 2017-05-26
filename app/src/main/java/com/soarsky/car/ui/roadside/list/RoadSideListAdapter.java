package com.soarsky.car.ui.roadside.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.bean.RoadSideListDataBean;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：救援列表适配器<br>
 * 该类为 RoadSideListAdapter<br>
 */


public class RoadSideListAdapter extends BaseAdapter{

    private Context context;

    private List<RoadSideListDataBean> list;

    private LayoutInflater inflater;

    public RoadSideListAdapter(Context context,List<RoadSideListDataBean> list){
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
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

        RoadSideListAdapter.ViewHolder holder = null;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.road_side_sever_list_item,null);
            holder.roadSideSeverItemView = (ImageView) view.findViewById(R.id.roadSideSeverItemView);
            holder.roadSideSeverListItemFeeTv = (TextView) view.findViewById(R.id.roadSideSeverListItemFeeTv);
            holder.roadSideSeverListItemNameTv = (TextView) view.findViewById(R.id.roadSideSeverListItemNameTv);
            holder.roadSideSeverListItemStateTv = (TextView)view.findViewById(R.id.roadSideSeverListItemStateTv);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        setData(holder,i);
        return view;
    }

    public void setData(ViewHolder holder,int position){

        holder.roadSideSeverListItemFeeTv.setText(list.get(position).getCost()==null?"0":list.get(position).getCost());
        holder.roadSideSeverListItemNameTv.setText(list.get(position).getCompany()== null?"":list.get(position).getCompany());
        String state ="";
        if("0".equals(list.get(position).getStatus())){
            state =context.getResources().getString(R.string.roadsidedeal);
        }else {
            state =context.getResources().getString(R.string.roadsidefinish);
        }
        holder.roadSideSeverListItemStateTv.setText(state);
    }

    public class ViewHolder{

        ImageView roadSideSeverItemView;

        TextView roadSideSeverListItemNameTv;

        TextView roadSideSeverListItemFeeTv;

        TextView roadSideSeverListItemStateTv;


    }
}
