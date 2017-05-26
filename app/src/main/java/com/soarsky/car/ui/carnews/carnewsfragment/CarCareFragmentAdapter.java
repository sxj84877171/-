package com.soarsky.car.ui.carnews.carnewsfragment;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.bean.AutomotiveInfo;
import com.soarsky.car.uitl.SpUtil;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/10<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  汽车保养页面数据适配器类<br>
 */

public class CarCareFragmentAdapter extends BaseAdapter {
    /**
     * 资讯集合
     */
    private List<AutomotiveInfo> list;
    /**
     * 上下文
     */
    private Context context;
    /**
     * 资讯id的集合
     */
    private  String [] idList;

    /**
     * 给外界一个传递参数的方法
     * @param list 资讯集合
     */
    public void setData(List<AutomotiveInfo> list){
        this.list = list;
        if(!TextUtils.isEmpty(SpUtil.get("id"))){
            idList=SpUtil.get("id").split("#");
        }
        notifyDataSetChanged();
    }
    public CarCareFragmentAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list == null){
            return 0;
        }
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
        ViewHolder holder;
        if (null == view){
            view = View.inflate(context, R.layout.car_news_item,null);
            holder = new ViewHolder();
            holder.item_carnews_redcricle = (ImageView) view.findViewById(R.id.item_carnews_redcricle);
            holder.tv_content = (TextView) view.findViewById(R.id.tv_content);
            holder.tv_topic = (TextView) view.findViewById(R.id.tv_topic);
            holder.tv_date = (TextView) view.findViewById(R.id.tv_date);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        if (isRead(list.get(i).getId())){
            holder.item_carnews_redcricle.setVisibility(View.GONE);
        }else {
            holder.item_carnews_redcricle.setVisibility(View.VISIBLE);
        }
        holder.tv_topic.setText(list.get(i).getTitle());
        holder.tv_date.setText(list.get(i).getCtime());
        holder.tv_content.setText(list.get(i).getRemark());
        return view;
    }
    class ViewHolder{
        ImageView item_carnews_redcricle;
        TextView tv_topic;
        TextView tv_date;
        TextView tv_content;
    }

    /**
     * 判断是否已读
     */
    private  boolean isRead(int id){

        if(null==idList){
            return  false;
        }
        for(int i=0;i<idList.length;i++){
            if(String.valueOf(id).equals(idList[i])){
                return true;
            }
        }
      return false;
    }

}
