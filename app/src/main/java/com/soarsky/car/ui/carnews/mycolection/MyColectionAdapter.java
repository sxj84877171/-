package com.soarsky.car.ui.carnews.mycolection;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.data.local.db.dao.ArticleCollectDao;

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
 * 该类为  收藏列表数据适配器类<br>
 */

public class MyColectionAdapter extends BaseAdapter {
    /**
     * 上下文
     */
    private Context context;
    /**
     * 收藏的文章的集合
     */
    private List<CollectionParam> mList;
    /**
     * 侧滑删除的监听
     */
    private SlideView.OnSlideListener listener;

    /**
     * 构造函数
     *
     * @param context  上下文
     * @param listener 侧滑监听
     */
    public MyColectionAdapter(Context context, SlideView.OnSlideListener listener) {
        super();
        this.context = context;
        this.listener = listener;
    }

    public void setData(List<CollectionParam> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        ViewHolder holder;

        SlideView slideView = (SlideView) convertView;

        if (slideView == null) {
            View itemView = View.inflate(context, R.layout.item_list, null);

            slideView = new SlideView(context);
            slideView.setContentView(itemView);

            slideView.setOnSlideListener(listener);
            holder = new ViewHolder();
            holder.title = (TextView) itemView.findViewById(R.id.tv_name);
            holder.date = (TextView) itemView.findViewById(R.id.tv_date);
            holder.remark = (TextView) itemView.findViewById(R.id.tv_content);
            holder.deleteHolder = (ViewGroup) slideView.findViewById(R.id.holder);
            slideView.setTag(holder);
        } else {
            holder = (ViewHolder) slideView.getTag();
        }
        final CollectionParam info = mList.get(position);
        info.slideView = slideView;
        info.slideView.shrink();
        holder.title.setText(info.getTitle());
        holder.date.setText(info.getCtime());
        holder.remark.setText(info.getRemark());

        holder.deleteHolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getApp().getDaoSession().getArticleCollectDao()
                        .delete(App.getApp().getDaoSession().getArticleCollectDao()
                                .queryBuilder().where(ArticleCollectDao.Properties.Id.eq(info.getId())).unique());
                mList.remove(info);
                notifyDataSetChanged();
            }
        });

        return slideView;
    }

    class ViewHolder {
        TextView title;
        TextView date;
        TextView remark;
        ViewGroup deleteHolder;
    }
}
