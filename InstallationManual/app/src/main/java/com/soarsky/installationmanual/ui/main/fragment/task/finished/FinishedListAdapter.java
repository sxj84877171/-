package com.soarsky.installationmanual.ui.main.fragment.task.finished;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * 该类为 完成的任务的Adapter<br>
 */
class FinishedListAdapter extends RecyclerView.Adapter<FinishedListAdapter.MyViewHolder> {
    /**
     * 完成的任务的列表
     */
    private List<FinishTaskBean> dataList;
    private Context mContext;
    private LayoutInflater inflater;

    public FinishedListAdapter(Context context){
        this. mContext=context;
        inflater=LayoutInflater. from(mContext);
    }

    public void setData(List<FinishTaskBean> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();//刷新列表
    }

    @Override
    public int getItemCount() {

        return dataList.size();
    }

    /**
     * 填充onCreateViewHolder方法返回的holder中的控件
     * @param holder
     * @param position
     */
    //填充onCreateViewHolder方法返回的holder中的控件
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        FinishTaskBean finishTaskBean = dataList.get(position);
        holder.carNum.setText(finishTaskBean.getCarNum());
        holder.fenPeiShiJian.setText(finishTaskBean.getFenPeiShiJian());
        holder.kaiShiShiJian.setText(finishTaskBean.getKaiShiShiJian());
        holder.wanChengShiJian.setText(finishTaskBean.getWanChengShiJian());
        int status = finishTaskBean.getFinishStatus();
        switch (status){
            case 0:
                holder.finishStatus.setText("(已完成)");
                break;
            case 1:
                holder.finishStatus.setText("(已退回)");
                break;
        }
    }

    /**
     * 重写onCreateViewHolder方法，返回一个自定义的ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    //重写onCreateViewHolder方法，返回一个自定义的ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout. finished_list_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView fenPeiShiJian;
        public TextView kaiShiShiJian;
        public TextView wanChengShiJian;
        public TextView carNum;
        public TextView finishStatus;

        public MyViewHolder(View view) {
            super(view);
            fenPeiShiJian=(TextView) view.findViewById(R.id.fenPeiShiJian);
            kaiShiShiJian=(TextView) view.findViewById(R.id.kaiShiShiJian);
            wanChengShiJian=(TextView) view.findViewById(R.id.wanChengShiJian);
            carNum=(TextView) view.findViewById(R.id.carNum);
            finishStatus=(TextView) view.findViewById(R.id.finishStatus);
        }

    }
}