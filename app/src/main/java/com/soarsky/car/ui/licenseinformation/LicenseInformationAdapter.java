package com.soarsky.car.ui.licenseinformation;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.bean.LicenseInfo;
import com.soarsky.car.uitl.ConstUtils;
import com.soarsky.car.uitl.TimeUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.soarsky.car.Constants.FRIEND;
import static com.soarsky.car.Constants.PERSONAL;
import static com.soarsky.car.R.mipmap.borrow;


/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2016/12/28<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  行驶证信息数据适配器类<br>
 */

public class LicenseInformationAdapter extends BaseAdapter {
    private Context context;

    private List<LicenseInfo> mList;
    public LicenseInformationAdapter(Context context){
        this.context = context;
    }

    public void setData(List<LicenseInfo> mList){
        this.mList = mList;
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == view){
            view = View.inflate(context, R.layout.item_license_information,null);
            holder = new ViewHolder();
            holder.iv_whose = (ImageView) view.findViewById(R.id.iv_whose);
            holder.tv_car_num = (TextView) view.findViewById(R.id.tv_car_num);
            holder.tv_car_color = (TextView) view.findViewById(R.id.tv_car_color);
            holder.tv_style = (TextView) view.findViewById(R.id.tv_style);
            holder.tv_capacity = (TextView) view.findViewById(R.id.tv_capacity);
            holder.tv_type = (TextView) view.findViewById(R.id.tv_type);
            holder.tv_day = (TextView) view.findViewById(R.id.tv_day);
            holder.tv_whose = (TextView) view.findViewById(R.id.tv_whose);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        holder.tv_car_num.setText(mList.get(i).getPlateno());
        holder.tv_car_color.setText("("+mList.get(i).getColor()+")");
        holder.tv_style.setText(mList.get(i).getModel());
        /*holder.tv_capacity.setText("1.6L");
        holder.tv_type.setText("自动舒适型");*/

        if(!(TextUtils.isEmpty(mList.get(i).getRegisterdate()))) {
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date d = null;
            try {
                d = sDateFormat.parse(mList.get(i).getRegisterdate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            // 车的年检日期
            Date dd = TimeUtils.addYear(d, 1);
            long ddd = dd.getTime() - new Date().getTime();
            holder.tv_day.setText("" + TimeUtils.milliseconds2Unit(ddd, ConstUtils.DAY));
        }else {
            holder.tv_day.setText(context.getString(R.string.nodate));
        }

        if (FRIEND == mList.get(i).getSign()){
            holder.iv_whose.setImageResource(R.mipmap.friend);
            holder.tv_whose.setText(context.getString(R.string.friend));
            holder.tv_whose.setTextColor(Color.parseColor("#93d150"));
        }else if (PERSONAL == mList.get(i).getSign()){
            holder.iv_whose.setImageResource(R.mipmap.personal);
            holder.tv_whose.setText(context.getString(R.string.who_use));
            holder.tv_whose.setTextColor(Color.parseColor("#f8a920"));
        }else {
            holder.iv_whose.setImageResource(borrow);
            holder.tv_whose.setText(context.getString(R.string.borrow));
            holder.tv_whose.setTextColor(Color.parseColor("#708feb"));
        }

        return view;
    }

    class ViewHolder{
        TextView tv_car_num;//车牌号
        TextView tv_car_color;//车身颜色
        TextView tv_style;//款式
        TextView tv_capacity; //容量
        TextView tv_type; //类型
        TextView tv_day; //年检期止
        TextView tv_whose; //使用人
        ImageView iv_whose;
    }
}
