package com.soarsky.policeclient.ui.details;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.bean.CarDetailsDataBean;

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
 * 该类为 车辆详情页面对应的Adapter<br>
 */
public class DetailAdapter extends BaseAdapter {
    /**
     * 上下文
     */
    private  Context context;//上下文
    /**
     * 数据集合
     */
    private List<CarDetailsDataBean.IlistBean> data;//数据集合


    public DetailAdapter(DetailsActivity context/*, List<DetailsCarParam.DataBean.IlistBean> data*/) {
        this.context = context;
        //this.data=data;
    }


    @Override
    public int getCount() {
        if(data==null){
            return 0;
        }
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        View view;
        if(v==null){
            view =View.inflate(context, R.layout.detail_listview_item, null);
        }else {
            view = v;
        }

        TextView tv_shijian = (TextView) view.findViewById(R.id.tv_shijian);
        TextView tv_didian = (TextView) view.findViewById(R.id.tv_didian);
        TextView tv_yuanyin = (TextView) view.findViewById(R.id.tv_yuanyin);
        CarDetailsDataBean.IlistBean info = data.get(i);
        //设置数据
        tv_shijian.setText(info.getStime());
        tv_didian.setText(info.getAddress());
        tv_yuanyin.setText(info.getInf());

        return view;
    }

    public void setData(List<CarDetailsDataBean.IlistBean> data){
        this.data = data;
        notifyDataSetChanged();
    }
}
