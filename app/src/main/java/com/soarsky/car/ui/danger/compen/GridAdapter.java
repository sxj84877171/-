package com.soarsky.car.ui.danger.compen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.soarsky.car.R;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

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
 * 程序功能： 为gridview 赋值
 * 该类为 gridView 适配器
 */


public class GridAdapter extends BaseAdapter {
    /**
     * 上下文
     */
    private Context context;
    /**
     * 图片集合
     */
    private List<CompensateImage> data;
    /**
     *
     */
    private LayoutInflater layoutInflater;
    private ImagecancleCallback imagecancleCallback;

    /**
     * 判断是那个View的适配器
     */


    private int type=1;


    public GridAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }



    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int getCount() {
        if (data == null) {
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
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.dangerimgup_img, null);
            viewHolder.imageShow = (ImageView) convertView.findViewById(R.id.showimg);
            viewHolder.imageCancle = (ImageView) convertView.findViewById(R.id.imgdelete);
            viewHolder.baseRl = (RelativeLayout) convertView.findViewById(R.id.rl_baseimg);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }

        /**
         * 设置删除图片监听
         */
        viewHolder.imageCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imagecancleCallback != null) {
                    imagecancleCallback.delete(position,type);
                }
            }
        });
        if (data.get(position).getShowDelete() == 0) {
            viewHolder.imageCancle.setVisibility(View.GONE);
            viewHolder.baseRl.setVisibility(View.VISIBLE);
            viewHolder.imageShow.setVisibility(View.GONE);
            viewHolder.baseRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (data.get(position).getShowDelete() == 0) {
                        imagecancleCallback.add();
                    }

                }
            });
        }

        if (data.get(position).getShowDelete() == 1) {
            viewHolder.imageShow.setVisibility(View.VISIBLE);
            viewHolder.baseRl.setVisibility(View.INVISIBLE);
            viewHolder.imageCancle.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(data.get(position).getImgUrl())
                    .bitmapTransform(new
                            RoundedCornersTransformation(context, 10, 0,
                            RoundedCornersTransformation.CornerType.ALL))
                    .into(viewHolder.imageShow);

        }
        return convertView;
    }


    class ViewHolder {
        ImageView imageShow;
        ImageView imageCancle;
        RelativeLayout baseRl;


    }

    /**
     * 设置删除图片回调监听
     * @param imagecancleCallback
     */
    public void setImagecancleCallback(ImagecancleCallback imagecancleCallback) {
        this.imagecancleCallback = imagecancleCallback;
    }


    /**
     * 设置数据
     * 先判断集合里面是否含有带删除图标的图片  如果有就先删除
     * @param baseData
     */
//    public void setData( List<CompensateImage> baseData) {
//        data=new ArrayList<>();
//
//        for(int i=0;i<data.size();i++){
//            if(data.get(i).getShowDelete()==0){
//                data.remove(i);
//            }
//
//        }
//
//        //身份证正反面照
//        if(type==106){
//            if (baseData.size() < 4) {
//
//                baseData = initData(baseData);
//
//            }
//        }else{
//            if (baseData.size() < 3) {
//
//                baseData = initData(baseData);
//
//            }
//        }
//
//        data = baseData;
//        this.notifyDataSetChanged();
//    }


    /**
     * 设置数据
     * 先判断集合里面是否含有带删除图标的图片  如果有就先删除
     * @param baseData
     */
    public void setData( List<CompensateImage> baseData) {
        data=new ArrayList<>();

        for(int i=0;i<baseData.size();i++){
            if(baseData.get(i).getShowDelete()==0){
                baseData.remove(i);
            }

        }

        //身份证正反面照
        if(type==106){
            if (baseData.size() < 4) {

                baseData = initData(baseData);

            }
        }else{
            if (baseData.size() < 3) {

                baseData = initData(baseData);

            }
        }

        data = baseData;
        this.notifyDataSetChanged();
    }






    /**
     * 初始化数据
     * @param data 本地图片集合
     * @return 带删除图片的图片集合
     */
    public List<CompensateImage> initData(List<CompensateImage> data) {
        CompensateImage compensateImage = new CompensateImage();
        compensateImage.setImgUrl("");
        compensateImage.setShowDelete(0);
        data.add(compensateImage);
        return data;

    }
}