package com.soarsky.car.ui.home.map.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.mapapi.search.sug.SuggestionResult;
import com.soarsky.car.R;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/10<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：其适配<br>
 * 该类为 MapSearchAdapter<br>
 */


public class MapSearchAdapter extends BaseAdapter{
    /**
     * 上下文本
     */
    private Context context;
    /**
     * LayoutInflater
     */
    private LayoutInflater inflater;
    /**
     * 数据源
     */
    List<SuggestionResult.SuggestionInfo> list;

    /**
     * 其构造函数
     * @param context
     * @param list
     */
    public MapSearchAdapter(Context context,List<SuggestionResult.SuggestionInfo> list){
        super();
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
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

        MapSearchAdapter.ViewHolder holder = null;
        if(view == null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.map_search_list_item,null);

            holder.mapSearchAddresstv = (TextView)view.findViewById(R.id.mapSearchAddresstv);
            holder.mapSearchCitytv = (TextView) view.findViewById(R.id.mapSearchCitytv);

            view.setTag(holder);
        }else {

            holder = (ViewHolder) view.getTag();

        }
        setData(holder,i);

        return view;
    }


    public void setData(ViewHolder holder,int position){

        holder.mapSearchCitytv.setText(""+list.get(position).key);
        holder.mapSearchAddresstv.setText(""+list.get(position).city+""+list.get(position).district);

    }

    public class ViewHolder{
        /**
         * 城市
         */
        TextView mapSearchCitytv;
        /**
         * 地点
         */
        TextView mapSearchAddresstv;

    }
}
