package com.soarsky.car.ui.illegal.advise;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.soarsky.car.R;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/3/1<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：适配<br>
 * 该类为 adapter<br>
 */


public class IllegalAdviseImageAdapter extends BaseAdapter{
    /**
     * 上下文本
     */
    private Context context;
    /**
     * 数据源
     */
    private List<String> images = new ArrayList<>();
    /**
     * RemovePictureListener
     */
    private RemovePictureListener listener;

    /**
     * 其构造函数
     * @param context
     * @param data
     */
    public IllegalAdviseImageAdapter(Context context, List<String> data){
        this.context = context;
        this.images = data;
    }

    @Override
    public int getCount() {
        if(images!=null){
            if(images.size()>=0&&images.size()<=2) {
                return images.size() + 1;
            }else {
                return 3;
            }
        }else {
            return 0;
        }
    }

    @Override
    public Object getItem(int i) {
        return images.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.imageitem, null);
            holder.delImg = (ImageView) convertView.findViewById(R.id.imgdelete);
            holder.showImg = (ImageView) convertView.findViewById(R.id.showimg);
            holder.convertView = convertView;
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }


        setData(holder,position);

        return convertView;
    }

    public void setRemovePictureListener(RemovePictureListener listener) {
        this.listener = listener;
    }


    public void setData(ViewHolder holder,final int position){


        if(images.size()>=0&&images.size()<=2) {
            if (position == getCount() - 1) {
                holder.delImg.setVisibility(View.INVISIBLE);
                holder.convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        listener.add();
                    }
                });
            }
        }else {
            holder.delImg.setVisibility(View.VISIBLE);
            holder.convertView.setEnabled(false);
        }



        holder.delImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.removePicture(position);
            }
        });

        if(position< images.size()){
            Glide.with(context).load(images.get(position)).bitmapTransform(new
                    RoundedCornersTransformation(context, 5, 0,
                    RoundedCornersTransformation.CornerType.ALL)).into
                    (holder.showImg);
        }
    }

    public class ViewHolder{

        ImageView delImg;

        ImageView showImg;

        View convertView;
    }


}
