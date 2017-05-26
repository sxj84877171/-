package com.soarsky.car.ui.borrowandreturn.borrowrecord;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;

import java.util.List;



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
 * 该类为  借车记录列表适配器类
 */

public class BorrowRecordAdapter extends BaseAdapter {
    private List<BorrowRecords> list;
    private String appPhone;

    public void setAppPhone(String appPhone) {
        this.appPhone = appPhone;
    }

    public void setList(List<BorrowRecords> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    private Context context;
    public BorrowRecordAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        if (list != null){
            return list.size();
        }
        return 0;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;
        ViewHolder holder;
        if (null == view){
            view1 = View.inflate(context, R.layout.borrow_record_item,null);
            holder = new ViewHolder();
            holder.iv_sign = (ImageView) view1.findViewById(R.id.iv_sign);
            holder.textView_carNum = (TextView) view1.findViewById(R.id.textView_carNum);
            holder.startTime = (TextView) view1.findViewById(R.id.startTime);
            holder.endTime = (TextView) view1.findViewById(R.id.endTime);
            view1.setTag(holder);
        }else {
            view1 = view;
            holder = (ViewHolder) view1.getTag();
        }
        BorrowRecords info = list.get(i);
        if (appPhone.equals(info.getBorrow())){
            holder.iv_sign.setImageResource(R.mipmap.jieru);
        }else {
            holder.iv_sign.setImageResource(R.mipmap.jiechu);
        }
        holder.textView_carNum.setText(list.get(i).getCarnum());
        holder.startTime.setText(list.get(i).getStime());
        holder.endTime.setText(list.get(i).getEtime());
        return view1;
    }
    class ViewHolder{
        ImageView iv_sign;
        TextView textView_carNum;
        TextView startTime;
        TextView endTime;
    }
}
