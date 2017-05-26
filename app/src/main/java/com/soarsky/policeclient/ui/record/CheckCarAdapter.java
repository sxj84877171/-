package com.soarsky.policeclient.ui.record;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.data.local.db.bean.Check;
import com.soarsky.policeclient.ui.details.DetailsActivity;

import java.util.List;

import static com.soarsky.policeclient.R.id.tv_item;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/16<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  稽查车辆数据适配器类<br>
 */

public class CheckCarAdapter extends BaseAdapter {

    /**
     * 上下文
     */
    private Context context;
    /**
     * 稽查数据列表
     */
    private List<Check> data;

    public void setData(List<Check> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public CheckCarAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
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
            view1 = View.inflate(context, R.layout.gridview_item, null);
            //新建一个viewholder对象
            holder = new ViewHolder();
            //将findviewbyID的结果赋值给holder对应的成员变量
            holder.tv_item = (TextView) view1.findViewById(tv_item);
            holder.iv = (ImageView) view1.findViewById(R.id.iv_item);
            // 将holder与view进行绑定
            view1.setTag(holder);
        } else {
            view1 = view;
            holder = (ViewHolder) view1.getTag();
        }

        Check dataBean = data.get(i);

        holder.tv_item.setText(dataBean.getCarnum());
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(context, DetailsActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("carNum",data.get(i).getCarnum());//给intent添加额外数据，key为“carNum”,key值为"Intent Demo"
                context.startActivity(intent);
            }
        });

        if (dataBean.getIsBlackList().equals("1")) {
            holder.iv.setImageResource(R.drawable.check_black_list);
            view1.setBackgroundResource(R.color.check_bg);
        } else {
            holder.iv.setImageResource(R.drawable.check_pai);
            view1.setBackgroundResource(R.color.white);
        }
        return view1;
    }
    private static class ViewHolder {
        private TextView tv_item;
        private ImageView iv;
    }
}
