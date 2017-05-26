package com.soarsky.policeclient.ui.accident.add;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.soarsky.policeclient.R;
import com.soarsky.policeclient.bean.AccidentDataBean;
import com.soarsky.policeclient.ui.violation.BigPictureActivity;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 图片适配器<br>
 */
public class ImageAdapter extends BaseAdapter {

    /**
     * 上下文
     */
    private Context context;
    /**
     * 新增事故拍摄照片的图片列表
     */
    private List<AccidentDataBean.AccidentItemBean.Image> images = new ArrayList<>();
    /**
     * 新增事故拍摄照片的图片位置列表
     */
    private ArrayList<String> imageStrs = new ArrayList<>();
    /**
     * Adapter对应的GridView
     */
    private GridView gridView;

    public ImageAdapter(Context context, List<AccidentDataBean.AccidentItemBean.Image> data, GridView gridView) {
        this.context = context;
        this.gridView = gridView;
        this.images = data;
        imageStrs.clear();
        for (AccidentDataBean.AccidentItemBean.Image image:images){
            imageStrs.add(image.getMyPath());
        }
    }
    @Override
    public int getCount() {
        if(images!=null){
            return images.size() + 1;
        }else {
            return 0;
        }

    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (null == convertView) {
            convertView = View.inflate(context,R.layout.accidentimageitem, null);
            ImageView delImg = (ImageView) convertView.findViewById(R.id.imgdelete);
            if(position==getCount()-1){
                delImg.setVisibility(View.INVISIBLE);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        ((Activity)context).startActivityForResult(intent, 1);
                    }
                });
            }else {
                ImageView showImg = (ImageView) convertView.findViewById(R.id.showimg);
                Glide.with(context).load(images.get(position).getMyPath()).bitmapTransform(new
                        RoundedCornersTransformation(context,5, 0,
                        RoundedCornersTransformation.CornerType.ALL)).into
                        (showImg);
                showImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context,BigPictureActivity.class);
                        intent.putExtra("type","1");
                        intent.putStringArrayListExtra("pics", imageStrs);
                        intent.putExtra("position",position);
                        context.startActivity(intent);
                    }
                });
                delImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        images.remove(position);
                        imageStrs.clear();
                        for (AccidentDataBean.AccidentItemBean.Image image:images){
                            imageStrs.add(image.getMyPath());
                        }
                        gridView.setAdapter(new ImageAdapter(context,images,gridView));
                    }
                });
            }

        }

        return convertView;
    }

}