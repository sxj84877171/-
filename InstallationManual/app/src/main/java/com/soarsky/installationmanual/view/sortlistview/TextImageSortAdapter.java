package com.soarsky.installationmanual.view.sortlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.soarsky.installationmanual.R;

import java.util.List;

public class TextImageSortAdapter extends BaseSortAdapter implements SectionIndexer {


	public TextImageSortAdapter(Context mContext, List<SortModel> list) {
		super(mContext, list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextImageSortViewHolder viewHolder = null;
		final SortModel mContent = list.get(position);
		if (convertView== null) {
			viewHolder = new TextImageSortViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.text_image_sort_listview_item, null);
			viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.title);
			viewHolder.tvLetter = (TextView) convertView.findViewById(R.id.catalog);
			viewHolder.imageView = (ImageView) convertView.findViewById(R.id.img);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (TextImageSortViewHolder) convertView.getTag();
		}
		int section = getSectionForPosition(position);
		if (position == getPositionForSection(section)) {
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getSortLetters());
		}else {
			viewHolder.tvLetter.setVisibility(View.GONE);
		}
		viewHolder.tvTitle.setText(this.list.get(position).getName());
		Glide.with(mContext).load(list.get(position).getImageUrl()).into(viewHolder.imageView);
		return convertView;
	}
}
