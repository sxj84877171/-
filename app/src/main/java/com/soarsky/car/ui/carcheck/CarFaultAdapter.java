package com.soarsky.car.ui.carcheck;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.car.R;

import static com.soarsky.car.R.string.fault_cause;

/**
 * picker
 * 作者： 王松清
 * 时间： 2016/11/30
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */

public class CarFaultAdapter extends BaseAdapter {
    private Context context;
    public CarFaultAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return 3;
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
        View v;
        ViewHolder holder;
        if (null == view){
            v = View.inflate(context, R.layout.car_fault_item,null);
            holder = new ViewHolder();
            holder.tv_date = (TextView) v.findViewById(R.id.tv_date);
            holder.tv_cause = (TextView) v.findViewById(R.id.tv_cause);
            v.setTag(holder);
        }else {
            v = view;
            holder = (ViewHolder) v.getTag();
        }
        holder.tv_date.setText(R.string.licenseendtime);
        holder.tv_cause.setText(R.string.fault_cause);
        return v;
    }

    class ViewHolder{
        TextView tv_date;
        TextView tv_cause;
    }
}
