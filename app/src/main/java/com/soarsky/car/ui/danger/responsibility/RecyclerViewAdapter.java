package com.soarsky.car.ui.danger.responsibility;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/20
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private OnItemClickListener mOnItemClickListener;

    private List<ResponsibilityListParam.DataBean> data;

    public  RecyclerViewAdapter(Context context,List<ResponsibilityListParam.DataBean> data){
        this.context=context;
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myViewHolder holder=new myViewHolder(LayoutInflater.from(context).inflate(R.layout.item_responsibility,parent,false));

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ((myViewHolder) holder).address.setText(data.get(position).getPosition());
        ((myViewHolder) holder).time.setText(data.get(position).getOccurredTime());
        String affirm ="";
        if(data.get(position).getFirstAffirm().equals("1")){
            affirm = "全责";
        }else if(data.get(position).getFirstAffirm().equals("2")){
            affirm = "同责";
        }else {
            affirm = "无责";
        }
        ((myViewHolder) holder).respons.setText(affirm);
        String status ="";
        if(data.get(position).getFstatus().equals("0")){
            status = "定责中";
        }
        ((myViewHolder) holder).status.setText(status);

        if(mOnItemClickListener!=null){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(position);
            }
        });

        }

        }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public ResponsibilityListParam.DataBean getDateBean(int position){
        return data.get(position);
    }


    class myViewHolder extends RecyclerView.ViewHolder{
        ImageView redCricle;
        TextView time,address,status,respons;


        public myViewHolder(View itemView) {
            super(itemView);
            redCricle= (ImageView) itemView.findViewById(R.id.item_responsibility_redcricle);
            time= (TextView) itemView.findViewById(R.id.item_responsibility_time);
            address= (TextView) itemView.findViewById(R.id.item_responsibility_address);
            status= (TextView) itemView.findViewById(R.id.item_responsibility_status);
            respons= (TextView) itemView.findViewById(R.id.item_responsibility_respons);
        }
    }



    public interface OnItemClickListener{
        void onClick( int position);
        void onLongClick( int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }



}
