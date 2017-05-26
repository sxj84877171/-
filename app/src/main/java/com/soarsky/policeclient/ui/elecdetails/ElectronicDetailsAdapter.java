package com.soarsky.policeclient.ui.elecdetails;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.soarsky.policeclient.R;
import com.soarsky.policeclient.helper.Utils;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/17<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  电子罚单详情页图片数据适配器类<br>
 */

public class ElectronicDetailsAdapter extends BaseAdapter {
    /**
     * 上下文
     */
    private Context context;
    /**
     * 图片路径
     */
    private List<String> path;
    
    public ElectronicDetailsAdapter(Context context,List<String> path){
        this.context = context;
        this.path = path;
    }
    @Override
    public int getCount() {
        return path.size();
    }

    @Override
    public Object getItem(int i) {
        return path.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;
        ViewHolder holder;
        if (view == null){
            // 如果view为空，则表示第一次显示该条目，需要创建一个view
            view1 = View.inflate(context, R.layout.electronic_gv_item,null);
            //新建一个viewholder对象
            holder = new ViewHolder();
            //将findviewbyID的结果赋值给holder对应的成员变量
            holder.iv_picture = (ImageView) view1.findViewById(R.id.iv_picture);
            // 将holder与view进行绑定
            view1.setTag(holder);
        }else {
            view1 = view;
            holder = (ViewHolder) view1.getTag();
        }

        int width = Utils.dip2px(context, 100f);
        int height = Utils.dip2px(context, 100f);
        Glide.with(context).load(path.get(i)).override(width, height).bitmapTransform(new RoundedCornersTransformation(context,5, 0,
                RoundedCornersTransformation.CornerType.ALL))
                .into(holder.iv_picture);
        return view1;
    }

    private static class ViewHolder {
        private ImageView iv_picture;
    }

}
