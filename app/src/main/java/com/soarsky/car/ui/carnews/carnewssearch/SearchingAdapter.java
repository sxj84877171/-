package com.soarsky.car.ui.carnews.carnewssearch;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.bean.AutomotiveInfo;

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
 * 该类为  搜索功能动态显示的数据适配器<br>
 */

public class SearchingAdapter extends BaseAdapter {
    /**
     * 上下文
     */
    private Context context;
    /**
     * 资讯的集合
     */
    private List<AutomotiveInfo> list;

    /**
     * 构造函数
     * 初始化上下文
     * @param context 上下文
     */
    public SearchingAdapter(Context context){
        this.context = context;
    }
    public void setData(List<AutomotiveInfo> list){
        this.list = list;
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
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == view){
            view = View.inflate(context, R.layout.search_item,null);
            holder = new ViewHolder();
            holder.tv_content = (TextView) view.findViewById(R.id.tv_content);
            holder.tv_date = (TextView) view.findViewById(R.id.tv_date);
            holder.tv_topic = (TextView) view.findViewById(R.id.tv_topic);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_topic.setText(list.get(i).getTitle());
        holder.tv_date.setText(list.get(i).getCtime());
        holder.tv_content.setText(list.get(i).getRemark());
        return view;
    }
    class ViewHolder{
        TextView tv_topic;
        TextView tv_date;
        TextView tv_content;
    }
}
