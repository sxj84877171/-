package com.sxj.carloan.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sxj.carloan.R;

import java.util.List;

/**
 * Created by admin on 2017/7/2.
 */

public class BaseLoanListAdapter extends BaseAdapter {

    private List<BaseLoanListBean> datas;
    private Context context;

    public static interface BaseLoanListBean {
        String getFristData();

        String getSecondData();

        String getFristLab();

        String getSecondLab();
    }

    BaseLoanListAdapter(Context context){
        this.context = context;
    }

    public void setDatas(List<BaseLoanListBean> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (datas != null) {
            return datas.size() + 1;
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (datas != null && position > 0) {
            return datas.get(position - 1);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.base_loan_list_item_view,null);
            viewHolder = new ViewHolder();
            viewHolder.lab1 =(TextView) convertView.findViewById(R.id.lab1);
            viewHolder.lab2 = (TextView)convertView.findViewById(R.id.lab2);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(position == 0){

        }else{
            viewHolder.lab1.setText(datas.get(position-1).getFristData());
            viewHolder.lab2.setText(datas.get(position-1).getSecondData());
        }

        return convertView;
    }


    class ViewHolder {
        TextView lab1;
        TextView lab2;
    }
}
