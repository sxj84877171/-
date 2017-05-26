package com.soarsky.car.ui.borrowandreturn.borrowaplication.refuse;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.car.R;

/**
 * Andriod_Car_App
 * 作者： 王松清
 * 时间： 2016/12/1
 * 公司：长沙硕铠电子科技有限公司
 * Email：wangsongqing@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为  拒绝原因适配器类
 */

public class RefuseCauseAdapter extends BaseAdapter {
    private Context context;
    //private LayoutInflater inflater;
    public RefuseCauseAdapter(Context context){
        this.context = context;
        //this.inflater = LayoutInflater.from(context);
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
        View view1;
        ViewHolder holder;
        if (null == view){
            view1 = View.inflate(context,R.layout.cause_item,null);
            holder = new ViewHolder();
            holder.textCause = (TextView) view1.findViewById(R.id.textCause);
            view1.setTag(holder);
        }else {
            view1 = view;
            holder = (ViewHolder) view1.getTag();
        }
        if (i == 1){
            holder.textCause.setText(R.string.cause_er);
        }else if (i == 2){
            holder.textCause.setText(R.string.cause_three);
        }else {
            holder.textCause.setText(R.string.cause_yi);
        }
        return view1;
    }
    class ViewHolder{
        TextView textCause;
    }
}
