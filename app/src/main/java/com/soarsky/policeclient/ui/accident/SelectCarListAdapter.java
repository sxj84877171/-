package com.soarsky.policeclient.ui.accident;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.ui.accident.add.AddAccidentActivity;
import com.soarsky.policeclient.uitl.CarUtil;

import java.util.ArrayList;
/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/26<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为选择车牌号列表Adapter<br>
 */
public class SelectCarListAdapter extends BaseAdapter {
    /**
     * 上下文
     */
    private Context context;
        public SelectCarListAdapter(Context context){
            this.context = context;
        }

    /**
     * 选择车辆列表
     */
    private ArrayList<Car> adapterSelectCarList = new ArrayList<>();

        public void setData(ArrayList<Car> data) {
            this.adapterSelectCarList = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if(adapterSelectCarList!=null){
                return adapterSelectCarList.size();
            }else {
                return 0;
            }

        }

        @Override
        public Object getItem(int i) {
            return adapterSelectCarList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View view1;
            ViewHolder holder;
            if (null == view) {
                // 如果view为空，则表示第一次显示该条目，需要创建一个view
                view1 = View.inflate(context, R.layout.selectcaritem, null);
                //新建一个viewholder对象
                holder = new ViewHolder();
                //将findviewbyID的结果赋值给holder对应的成员变量
                holder.ssid = (TextView) view1.findViewById(R.id.ssid);
                holder.state = (ImageView) view1.findViewById(R.id.state);
                holder.delete = (ImageView) view1.findViewById(R.id.delete);
                // 将holder与view进行绑定
                view1.setTag(holder);
            } else {
                view1 = view;
                holder = (ViewHolder) view1.getTag();
            }

            holder.ssid.setText(adapterSelectCarList.get(i).getCarNum());
            if(adapterSelectCarList.get(i).isHasRead()){
                if(adapterSelectCarList.get(i).isHasData()){
                    holder.state.setImageResource(R.drawable.selectcaritemzhengchang);
                }else {
                    holder.state.setImageResource(R.drawable.selectcaritemyichang);
                }
            }else {
                holder.state.setImageResource(R.drawable.selectcaritemloading);
            }

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adapterSelectCarList.remove(i);
                    notifyDataSetChanged();
                }
            });
            return view1;
        }


        private class ViewHolder {
            public ImageView state;
            public TextView ssid;
            public ImageView delete;
        }

    }