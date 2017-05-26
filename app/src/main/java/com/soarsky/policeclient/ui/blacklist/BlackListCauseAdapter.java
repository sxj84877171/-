package com.soarsky.policeclient.ui.blacklist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.policeclient.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2017/3/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 黑名原因列表数据适配器类<br>
 */

public class BlackListCauseAdapter extends BaseAdapter{
    private Context context;
    private List<String> data = new ArrayList<>();
    private LayoutInflater inflater;

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public BlackListCauseAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
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

        ViewHolder holder;
        if(null == view){
            view = inflater.inflate(R.layout.blacklist_detail_listview_item, null);
//            view1 =View.inflate(context, R.layout.blacklist_detail_listview_item, null);
            holder = new ViewHolder();
            holder.tv_cause = (TextView) view.findViewById(R.id.tv_cause);
            view.setTag(holder);
        }else {

            holder = (ViewHolder) view.getTag();
        }
        holder.tv_cause.setText(getReason(data.get(i)));
        return view;
    }

    class ViewHolder{
        TextView tv_cause;
    }
    private String getReason(String s){
        HashMap<String,String> hashMap = new HashMap();
        hashMap.put("1","累计大量未处理违章");
        hashMap.put("2","套牌车");
        hashMap.put("3","假车牌");
        hashMap.put("4","行驶证注销");
        hashMap.put("5","外部接口录入");
        return hashMap.get(s);
    }
}
