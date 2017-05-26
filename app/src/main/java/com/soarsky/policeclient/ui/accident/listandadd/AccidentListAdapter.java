package com.soarsky.policeclient.ui.accident.listandadd;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.bean.AccidentDataBean;

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
 * 该类为事故分析事故列表Adapter<br>
 */
public class AccidentListAdapter extends BaseAdapter {

    /**
     * 上下文
     */
    private Context context;
    /**
     * 事故分析列表
     */
    private List<AccidentDataBean.AccidentItemBean> accidentList;

    public AccidentListAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        if(accidentList != null){
            return accidentList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if(accidentList != null) {
            return accidentList.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;
        ViewHolder holder;
        if (null == view) {
            // 如果view为空，则表示第一次显示该条目，需要创建一个view
            view1 =View.inflate(context, R.layout.accidentitem, null);
            //新建一个viewholder对象
            holder = new ViewHolder();
            //将findviewbyID的结果赋值给holder对应的成员变量
            holder.time = (TextView) view1.findViewById(R.id.time);
            holder.position = (TextView)view1.findViewById(R.id.position);
            // 将holder与view进行绑定
            view1.setTag(holder);
        } else {
            view1 = view;
            holder = (ViewHolder) view1.getTag();
        }

        AccidentDataBean.AccidentItemBean accidentItem = accidentList.get(i);
        holder.time.setText(accidentItem.getTime());
        holder.position.setText(accidentItem.getWeizhi());
        return view1;
    }


    private static class ViewHolder {
        public TextView time;
        public TextView position;
    }

    public void setAccidentList(List<AccidentDataBean.AccidentItemBean> accidentList) {
        this.accidentList = accidentList;
        notifyDataSetChanged();//刷新列表
    }
}
