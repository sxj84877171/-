package com.sxj.carloan.rongyi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sxj.carloan.R;

import java.util.List;
import java.util.Map;

/**
 * Created by miaojun on 16/11/3.
 */

public class VideoListAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, String>> list;
    private OnItemClickListener onItemClickListener;

    public VideoListAdapter(Context context, List<Map<String, String>> list) {
        this.list = list;
        this.context = context;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = View.inflate(parent.getContext(), R.layout.item_video_list, null);
        VideoHolder holder = new VideoHolder(v);
        holder.nameTV.setText(list.get(position).get("name"));
        holder.nameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(v, position);
            }
        });
        holder.nameTV.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onItemClickListener.onLongClick(view, position);
                return false;
            }
        });

        return v;
    }


    class VideoHolder {
        TextView nameTV;

        public VideoHolder(View itemView) {
            nameTV = (TextView) itemView.findViewById(R.id.video_name);
        }
    }


}
