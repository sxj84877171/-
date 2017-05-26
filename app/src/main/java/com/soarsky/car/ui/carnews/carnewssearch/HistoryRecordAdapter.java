package com.soarsky.car.ui.carnews.carnewssearch;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.uitl.ToastUtil;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/10<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  搜索历史记录数据适配器<br>
 */

public class HistoryRecordAdapter extends BaseAdapter {
    /**
     * 上下文
     */
    private Context context;
    /**
     * 搜索历史记录集合
     */
    private List<String> list;
    /**
     * 搜索历史关键字
     */
    private  String [] keywordArray;
    /**
     * 搜索历史关键字保存在本地的key值
     */
    private final String RECORD = "listRecord";
    public HistoryRecordAdapter(Context context){
        this.context = context;
    }

    public void setList(List<String> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if (list.size() == 0){
            return 0;
        }
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == view){
            view = View.inflate(context, R.layout.history_record_item,null);
            holder = new ViewHolder();
            holder.iv_delete = (ImageView) view.findViewById(R.id.iv_delete);
            holder.record_topic = (TextView) view.findViewById(R.id.record_topic);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        holder.iv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.show(context,"删除此条搜索记录？");
                list.remove(i);
                notifyDataSetChanged();
                /*keywordArray = SpUtil.get(RECORD).split("#");
                List<String> nList = new ArrayList<String>();

                for(String key : keywordArray){
                    if (list.contains(key)){
                        nList.add(key);
                    }
                }
                String[] result = {};
                newArray = nList.toArray(result);
                String value = null;
                for(int i = 0 ; i <newArray.length ;i++){
                    String keyword = newArray[i];

                }*/
            }
        });
        holder.record_topic.setText(list.get(i));
        return view;
    }
    class ViewHolder{
        TextView record_topic;
        ImageView iv_delete;
    }
}
