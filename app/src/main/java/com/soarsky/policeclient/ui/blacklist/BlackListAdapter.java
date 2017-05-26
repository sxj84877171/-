package com.soarsky.policeclient.ui.blacklist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.data.local.db.bean.BlackCar;

import java.util.ArrayList;
import java.util.List;

import static com.soarsky.policeclient.R.id.tv_item;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/14<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  黑名单列表数据适配器类<br>
 */

public class BlackListAdapter extends BaseAdapter {
    /**
     * 上下文
     */
    private Context context;
    /**
     * 黑名单集合
     */
    private List<BlackCar> blackCarList = new ArrayList<>();

    public BlackListAdapter(Context context){
        /*BlackCar blackCar = new BlackCar();
        blackCar.setReason("1");
        blackCar.setPlatetype("1");
        blackCar.setChangetype("1");
        blackCar.setCarnum("湘A11111");
        blackCarList.add(blackCar);*/
        this.context = context;
    }
    @Override
    public int getCount() {
        if(blackCarList != null){
            int count = blackCarList.size();
            if(count%2!=0){
                count = count + 1;
            }
            return count;
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if(blackCarList != null) {
            return blackCarList.get(i);
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
            view1 =View.inflate(context, R.layout.blacklist_item, null);
            //新建一个viewholder对象
            holder = new ViewHolder();
            //将findviewbyID的结果赋值给holder对应的成员变量
            holder.tv_item = (TextView) view1.findViewById(tv_item);
            holder.enforcement = (TextView) view1.findViewById(R.id.enforcement);
            // 将holder与view进行绑定
            view1.setTag(holder);
        } else {
            view1 = view;
            holder = (ViewHolder) view1.getTag();
        }
        if(i!=blackCarList.size()){
            BlackCar bc = blackCarList.get(i);
            holder.tv_item.setText(bc.getCarnum());
            if("已处理".equals(bc.getReason())){
                holder.enforcement.setVisibility(View.VISIBLE);
            }else {
                holder.enforcement.setVisibility(View.INVISIBLE);
            }
        }else {
            holder.tv_item.setText("");
            ((ImageView)view1.findViewById(R.id.iv_item)).setImageBitmap(null);
            holder.enforcement.setVisibility(View.INVISIBLE);
        }
        return view1;
    }


    private static class ViewHolder {
        private TextView tv_item;
        TextView enforcement;

    }

    public void setBlackCarList(List<BlackCar> blackCarList) {
        this.blackCarList = blackCarList;
        notifyDataSetChanged();//刷新列表
    }
}
