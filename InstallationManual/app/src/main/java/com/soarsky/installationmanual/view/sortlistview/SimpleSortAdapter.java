package com.soarsky.installationmanual.view.sortlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.soarsky.installationmanual.R;

import java.util.List;

public class SimpleSortAdapter extends BaseSortAdapter implements SectionIndexer {


	public SimpleSortAdapter(Context mContext, List<SortModel> list) {
		super(mContext, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		SimpleSortViewHolder viewHolder = null;
		final SortModel mContent = list.get(position);
		if (convertView== null) {
			viewHolder = new SimpleSortViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.sort_listview_item, null);
			viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.title);
			viewHolder.tvLetter = (TextView) convertView.findViewById(R.id.catalog);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (SimpleSortViewHolder) convertView.getTag();
		}
		//根据position获取分类的首字母的Char ascii值
		int section = getSectionForPosition(position);
		//如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
		if (position == getPositionForSection(section)) {
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getSortLetters());
		}else {
			viewHolder.tvLetter.setVisibility(View.GONE);
		}
		viewHolder.tvTitle.setText(this.list.get(position).getName());
		return convertView;
	}
}
