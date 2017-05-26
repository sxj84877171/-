package com.soarsky.policeclient.ui.check;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.uitl.CarUtil;

import java.util.List;

/**
 * andriod_police_app<br>
 * 作者： 何明辉<br>
 * 时间： 2016/11/3<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  稽查界面Adapter<br>
 */

public class CheckCarAdapter extends BaseAdapter {
    /**
     * 车辆集合
     */
    private List<Car> list;
    /**
     * 上下文
     */
    private Context context;

    public List<Car> getList() {
        return list;
    }

    public void setList(List<Car> list) {
        this.list = list;
    }

    public CheckCarAdapter(List<Car> list, Context context) {
        setList(list);
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;
        // 如果view为空，则表示第一次显示该条目，需要创建一个view
        view1 = View.inflate(context, R.layout.gridview_item, null);
        //新建一个viewholder对象
        //将findviewbyID的结果赋值给holder对应的成员变量
        TextView tv_item = (TextView) view1.findViewById(R.id.tv_item);
        ImageView iv = (ImageView) view1.findViewById(R.id.iv_item);


        Car car = list.get(i);
        tv_item.setText(car.getCarNum());
        //判断是否是黑名单
        if (car.isBlack()) {
            //holder.iv.setBackgroundResource(R.mipmap.check_black_list);
            iv.setImageResource(R.mipmap.check_black_list);
        } else {
            iv.setImageResource(R.mipmap.check_pai);
        }
        return view1;
    }


    /**
     * 添加车辆
     *
     * @param car 添加的车辆列表
     */
    public void addCar(List<Car> car) {
        setList(car);
        notifyDataSetChanged();//刷新页面
    }

    /**
     * 移除车辆
     *
     * @param car 移除的车辆列表
     */
    public void removeCar(List<Car> car) {
        setList(car);
        notifyDataSetChanged();//刷新页面
    }
}
