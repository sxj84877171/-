package com.soarsky.car.ui.danger.responsibility;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.bean.ResponsibilityListDataBean;

import java.util.List;

import static com.soarsky.car.Constants.ALL_DUTY;
import static com.soarsky.car.Constants.NO_DUTY;
import static com.soarsky.car.Constants.REVIEW;
import static com.soarsky.car.Constants.REVIEWED;
import static com.soarsky.car.Constants.SAME_DUTY;

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
 * 程序功能：为RecyclerView添加数据
 * 该类为 RecyclerView适配器
 */


public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private Context context;
    /**
     * 列表点击监听
     */
    private OnItemClickListener mOnItemClickListener;
    /**
     * 车牌号
     */
    private String  myCarNum;
    /**
     * 缓存责任列表集合
     */
    private List<ResponsibilityListDataBean> data;

    public  RecyclerViewAdapter(Context context,List<ResponsibilityListDataBean> data,String myCarNum){
        this.context=context;
        this.data = data;
        this.myCarNum=myCarNum;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myViewHolder holder=new myViewHolder(LayoutInflater.from(context).inflate(R.layout.item_responsibility3,parent,false));

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ((myViewHolder) holder).address.setText(data.get(position).getPosition());
        ((myViewHolder) holder).time.setText(data.get(position).getOccurredTime());
        if(myCarNum.equals(data.get(position).getFirstCar())){
            if(data.get(position).getFstatus().equals(SAME_DUTY)){
                ((myViewHolder) holder).redCricle.setVisibility(View.INVISIBLE);
                /*((myViewHolder) holder).t1.setVisibility(View.GONE);
                ((myViewHolder) holder).t2.setVisibility(View.VISIBLE);*/
            }else{
                ((myViewHolder) holder).redCricle.setVisibility(View.VISIBLE);
                /*((myViewHolder) holder).t1.setVisibility(View.VISIBLE);
                ((myViewHolder) holder).t2.setVisibility(View.GONE);*/
            }
        }else{
            if(data.get(position).getSstatus().equals(SAME_DUTY)){
                ((myViewHolder) holder).redCricle.setVisibility(View.INVISIBLE);
                /*((myViewHolder) holder).t1.setVisibility(View.GONE);
                ((myViewHolder) holder).t2.setVisibility(View.VISIBLE);*/
            }else{
                ((myViewHolder) holder).redCricle.setVisibility(View.VISIBLE);
                /*((myViewHolder) holder).t1.setVisibility(View.VISIBLE);
                ((myViewHolder) holder).t2.setVisibility(View.GONE);*/
            }
        }


        String affirm ="";

        if (myCarNum.equals(data.get(position).getFirstCar()) ){
        if(TextUtils.isEmpty(data.get(position).getFirstFinalAffirm())){
            if(data.get(position).getFirstAffirm().equals(NO_DUTY)){
                affirm = context.getString(R.string.no_duty);
                ((myViewHolder) holder).status.setBackgroundResource(R.mipmap.noduty);
            }else if(data.get(position).getFirstAffirm().equals(SAME_DUTY)){
                affirm = context.getString(R.string.same_duty);;
                ((myViewHolder) holder).status.setBackgroundResource(R.mipmap.sameduty);
            }else {
                affirm = context.getString(R.string.all_duty);
                ((myViewHolder) holder).status.setBackgroundResource(R.mipmap.allduty);
            }
        }else{
            if(data.get(position).getFirstFinalAffirm().equals(NO_DUTY)){
                affirm = context.getString(R.string.no_duty);
                ((myViewHolder) holder).status.setBackgroundResource(R.mipmap.noduty);
            }else if(data.get(position).getFirstFinalAffirm().equals(SAME_DUTY)){
                affirm = context.getString(R.string.same_duty);
                ((myViewHolder) holder).status.setBackgroundResource(R.mipmap.sameduty);
            }else {
                affirm = context.getString(R.string.all_duty);
                ((myViewHolder) holder).status.setBackgroundResource(R.mipmap.allduty);
            }

        }
        }else {
            //王松清 2017/03/28
            if(TextUtils.isEmpty(data.get(position).getFirstFinalAffirm())){
                if(data.get(position).getSecondAffirm().equals(NO_DUTY)){
                    affirm = context.getString(R.string.no_duty);
                    ((myViewHolder) holder).status.setBackgroundResource(R.mipmap.noduty);
                }else if(data.get(position).getSecondAffirm().equals(SAME_DUTY)){
                    affirm = context.getString(R.string.same_duty);;
                    ((myViewHolder) holder).status.setBackgroundResource(R.mipmap.sameduty);
                }else {
                    affirm = context.getString(R.string.all_duty);
                    ((myViewHolder) holder).status.setBackgroundResource(R.mipmap.allduty);
                }
            }else{
                if(data.get(position).getSecondFinalAffirm().equals(NO_DUTY)){
                    affirm = context.getString(R.string.no_duty);
                    ((myViewHolder) holder).status.setBackgroundResource(R.mipmap.noduty);
                }else if(data.get(position).getSecondFinalAffirm().equals(SAME_DUTY)){
                    affirm = context.getString(R.string.same_duty);
                    ((myViewHolder) holder).status.setBackgroundResource(R.mipmap.sameduty);
                }else {
                    affirm = context.getString(R.string.all_duty);
                    ((myViewHolder) holder).status.setBackgroundResource(R.mipmap.allduty);
                }

            }
        }

//        ((myViewHolder) holder).respons.setText(affirm);
        String status ="";
        if(data.get(position).getStatus().equals(NO_DUTY)){
            status = context.getString(R.string.no_deal);

        }
        if(data.get(position).getStatus().equals(SAME_DUTY)){
            status = context.getString(R.string.dealing);

        }
        if(data.get(position).getStatus().equals(ALL_DUTY)){
            status = context.getString(R.string.dealed);
        }
        if(data.get(position).getStatus().equals(REVIEW)){
            status = context.getString(R.string.redeal);
            ((myViewHolder) holder).status.setBackgroundResource(R.mipmap.dealduty);
        }
        if(data.get(position).getStatus().equals(REVIEWED)){
            status = context.getString(R.string.redealed);
        }
//        ((myViewHolder) holder).status.setText(status);

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

    public ResponsibilityListDataBean getDateBean(int position){
        return data.get(position);
    }


    class myViewHolder extends RecyclerView.ViewHolder{
        ImageView redCricle;
        TextView time,address,status,respons/*t1,t2*/;


        public myViewHolder(View itemView) {
            super(itemView);
            redCricle= (ImageView) itemView.findViewById(R.id.item_responsibility_redcricle);
            time= (TextView) itemView.findViewById(R.id.item_responsibility_time);
            address= (TextView) itemView.findViewById(R.id.item_responsibility_address);
            status= (TextView) itemView.findViewById(R.id.item_responsibility_status);
//            respons= (TextView) itemView.findViewById(R.id.item_responsibility_respons);
            /*t1= (TextView) itemView.findViewById(t1);
            t2= (TextView) itemView.findViewById(t2);*/
        }
    }


    public void setData(List<ResponsibilityListDataBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    /**
     * item 点击事件回调
     */
    public interface OnItemClickListener{
        void onClick( int position);
        void onLongClick( int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        this. mOnItemClickListener=onItemClickListener;
    }



}
