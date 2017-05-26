package com.soarsky.policeclient.ui.record;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.data.local.db.bean.Violation;
import com.soarsky.policeclient.uitl.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/15<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  电子罚单数据适配器类<br>
 */

public class ElectronicAdapter extends BaseAdapter {
    /**
     * 参考CheckCarAdapter
     */
    private List<Violation> list = new ArrayList<>();
    /**
     * 参考CheckCarAdapter
     */
    private Context context;

    public void setList(List<Violation> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ElectronicAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if (list == null) {
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
    public View getView(int i, View v, ViewGroup viewGroup) {
        View view;
        ViewHolder holder;
        if (null == v) {

            // 如果v为空，则表示第一次显示该条目，需要创建一个view
            view = View.inflate(context, R.layout.electronic_item, null);
            //新建一个viewholder对象
            holder = new ViewHolder();
            //将findviewbyID的结果赋值给holder对应的成员变量
            holder.tv_carNum = (TextView) view.findViewById(R.id.tv_carNum);
            holder.tv_didian = (TextView) view.findViewById(R.id.tv_didian);
            holder.tv_yuanyin = (TextView) view.findViewById(R.id.tv_yuanyin);
            holder.tv_shijian = (TextView) view.findViewById(R.id.tv_shijian);
            // 将holder与view进行绑定
            view.setTag(holder);
        } else {
            view = v;
            holder = (ViewHolder) view.getTag();
        }
        Violation info = list.get(i);
        holder.tv_carNum.setText(info.getCarnum());
        holder.tv_didian.setText(info.getAddress());
        holder.tv_yuanyin.setText(info.getInf());
        holder.tv_shijian.setText(TimeUtils.date2String(info.getStime()));

        return view;
    }
    private static class ViewHolder {
        private TextView tv_carNum;
        private TextView tv_didian;
        private TextView tv_yuanyin;
        private TextView tv_shijian;
    }
    public void addList(List<Violation> data){
        list.addAll(data);
        notifyDataSetChanged();
    }
}
