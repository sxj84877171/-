package com.soarsky.policeclient.ui.violation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.uitl.CarUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * android_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/21<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 开电子罚单车辆列表Adapter<br>
 */
public class ViolationCarAdapter extends BaseAdapter {
    private Context context;
    /**
     * 车辆列表数据
     */
    private List<String> list;
    /**
     * 视图加载器
     */
    private LayoutInflater inflater;

    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    /**
     * 类型
     */


    private int type;

    public ViolationCarAdapter(Context context,List<String> list,int type) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.list = list;
        this.type = type;
    }

    @Override
    public int getCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_violation_car, null);
            viewHolder.carNo = (TextView) convertView.findViewById(R.id.item_violation_carno);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        if(type == 1){
            viewHolder.carNo.setText(CarUtil.fromSsidToCarNum(list.get(position)));
        }
        else {
            viewHolder.carNo.setText(list.get(position));
        }
        return convertView;
    }


    static class ViewHolder {
        public TextView carNo;

    }

}
