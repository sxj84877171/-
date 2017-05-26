package com.soarsky.car.ui.danger.compen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.soarsky.car.R;

import java.util.List;

/**
 * Andriod_Car_App
 * 作者： 何明辉
 * 时间： 2016/12/21
 * 公司：长沙硕铠电子科技有限公司
 * Email：heminghui@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */


public class GridAdapter extends BaseAdapter{
    private Context context;
    private List<CompensateImage > data;
    private LayoutInflater layoutInflater;
    private ImagecancleCallback imagecancleCallback;

    public GridAdapter(Context context){
        this.context=context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if(data==null){
            return 0;
        }
        return data.size();

    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
             viewHolder=new ViewHolder();
            convertView=layoutInflater.inflate(R.layout.dangerimgup_img,null);
            viewHolder.imageShow= (ImageView) convertView.findViewById(R.id.showimg);
            viewHolder.imageCancle= (ImageView) convertView.findViewById(R.id.imgdelete);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();

        }

        /**
         * 设置删除图片监听
         */
        viewHolder.imageCancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                if(imagecancleCallback!=null){
                    imagecancleCallback.delete(position);
                }
                }
            });
        if (data.get(position).getShowDelete()==0){
            viewHolder.imageCancle.setVisibility(View.GONE);

            Glide.with(context)
                    .load(data.get(position).getImgUrl())
                    .into(viewHolder.imageShow);


            viewHolder.imageShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   if(data.get(position).getShowDelete()==0) {
                       imagecancleCallback.add();
                   }

                }
            });
        }

        if (data.get(position).getShowDelete()==1) {
            viewHolder.imageCancle.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(data.get(position).getImgUrl())
                    .into(viewHolder.imageShow);

        }
        return convertView;
    }


    class ViewHolder{
        ImageView imageShow;
        ImageView imageCancle;
    }


    public void setImagecancleCallback(ImagecancleCallback imagecancleCallback) {
        this.imagecancleCallback = imagecancleCallback;
    }


    public void setData(List<CompensateImage> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }
}
