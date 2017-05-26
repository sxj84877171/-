package com.soarsky.car.ui.illegal.advise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.soarsky.car.R;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/2/23<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：适配器<br>
 * 该类为IllegalAdviseAdapter<br>
 */


public class IllegalAdviseAdapter extends BaseAdapter{

    /**
     * 上下文本
     */
    private Context context;
    /**
     * 数据源
     */
    private List<AdviseImage > data;
    /**
     * LayoutInflater
     */
    private LayoutInflater layoutInflater;
    /**
     * RemovePictureListener
     */
    private RemovePictureListener listener;

    /**
     * 其构造函数
     * @param context
     */
    public IllegalAdviseAdapter(Context context){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(data==null){
            return 0;
        }
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
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            view=layoutInflater.inflate(R.layout.dangerimgup_img,null);
            viewHolder.imageShow= (ImageView) view.findViewById(R.id.showimg);
            viewHolder.imageCancle= (ImageView) view.findViewById(R.id.imgdelete);
//            viewHolder.rl_showimg = (RelativeLayout) view.findViewById(R.id.rl_showimg);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }


        setData(i,viewHolder);

        return view;
    }

    public void setData(final int i, ViewHolder holder){
        /**
         * 设置删除图片监听
         */
        holder.imageCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.removePicture(i);
                }
            }
        });

        if (data.get(i).getShowDelete()==0){
            holder.imageCancle.setVisibility(View.GONE);

            Glide.with(context)
                    .load(data.get(i).getImgUrl())
                    .into(holder.imageShow);

            holder.rl_showimg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(data.get(i).getShowDelete()==0) {
                        listener.add();
                    }
                }
            });
            holder.imageShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(data.get(i).getShowDelete()==0) {
                        listener.add();
                    }

                }
            });
        }

        if (data.get(i).getShowDelete()==1) {
            holder.imageCancle.setVisibility(View.VISIBLE);

            Glide.with(context)
                    .load(data.get(i).getImgUrl())
                    .into(holder.imageShow);
        }
    }


    public void setData(List<AdviseImage> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }

    public void setRemovePictureListener(RemovePictureListener listener) {
        this.listener = listener;
    }

    class ViewHolder{
        ImageView imageShow;
        ImageView imageCancle;
        RelativeLayout rl_showimg;
    }

}
