package com.soarsky.installationmanual.ui.main.fragment.task.nofinish;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.bean.FinishTaskBean;

import java.util.List;

/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 未完成任务列表Adapter<br>
 */
public class NoFinishListAdapter extends BaseAdapter {

    private Context context;

    private List<FinishTaskBean> dataList;

    public NoFinishListAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        if(dataList != null){
            return dataList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if(dataList != null) {
            return dataList.get(i);
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
            view1 =View.inflate(context, R.layout.no_finish_list_item, null);
            //新建一个viewholder对象
            holder = new ViewHolder();
            //将findviewbyID的结果赋值给holder对应的成员变量
            holder.time = (TextView) view1.findViewById(R.id.time);
            holder.carNum = (TextView)view1.findViewById(R.id.carNum);
            holder.hasRead = (ImageView)view1.findViewById(R.id.hasRead);
            holder.status = (ImageView)view1.findViewById(R.id.status);
            // 将holder与view进行绑定
            view1.setTag(holder);
        } else {
            view1 = view;
            holder = (ViewHolder) view1.getTag();
        }

        FinishTaskBean finishTaskBean = dataList.get(i);
        holder.time.setText(finishTaskBean.getTime());
        holder.carNum.setText(finishTaskBean.getCarNum());
        if(finishTaskBean.isHasRead()){
            holder.hasRead.setVisibility(View.INVISIBLE);
        }
        int status = finishTaskBean.getStatus();
        switch (status){
            case 0:
                holder.status.setImageResource(R.drawable.quanzhuang);
                break;
            case 1:
                holder.status.setImageResource(R.drawable.anzhuangzhong);
                break;
            case 2:
                holder.status.setImageResource(R.drawable.daijiance);
                break;
            case 3:
                holder.status.setImageResource(R.drawable.shangchuanzhaopian);
                break;
        }
        return view1;
    }


    private static class ViewHolder {
        public TextView time;
        public TextView carNum;
        public ImageView hasRead;
        public ImageView status;
    }

    public void setData(List<FinishTaskBean> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();//刷新列表
    }
}
