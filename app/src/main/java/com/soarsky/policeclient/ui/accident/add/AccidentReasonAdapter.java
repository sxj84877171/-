package com.soarsky.policeclient.ui.accident.add;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.policeclient.R;

import java.util.List;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为事故原因列表Adapter<br>
 */

public class AccidentReasonAdapter extends BaseAdapter {
    /**
     * 上下文
     */
    private Context context;
    /**
     * 事故原因列表
     */
    private List<String> list;
    /**
     * 布局加载器
     */
    private LayoutInflater inflater;

    public AccidentReasonAdapter(Context context, List<String> list) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
    }

    /**
     * 数量
     * @return 数量
     */
    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /**
     * 根据position获取item
     * @param position 在list中的位置
     * @return 获取当的item
     */
    @Override
    public Object getItem(int position) {
        if(list!=null){
            return list.get(position);
        }else {
            return null;
        }

    }

    /**
     * 获取item的id
     * @param position 越秀公司
     * @return item的id
     */
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_accident_reason, null);
            viewHolder.carNo = (TextView) convertView.findViewById(R.id.reason);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        if(list!=null){
            viewHolder.carNo.setText(list.get(position));
        }


        return convertView;
    }

    /**
     * viewholder
     */
    static class ViewHolder {
        public TextView carNo;

    }

}
