package com.sxj.carloan.ui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sxj.carloan.bean.Loan;

import java.util.List;

/**
 */
public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {
    List<Loan> mValues;
    private ItemFragment fragment;

    public ItemRecyclerViewAdapter(List<Loan> items,ItemFragment frag) {
        mValues = items;
        fragment = frag;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView view = new TextView(parent.getContext());

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(position == 0){
            holder.textView.setText("申请人");
        }else{
            holder.textView.setText(mValues.get(position-1).getCustNameTmp());
        }
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data",mValues.get(position-1));
                intent.putExtra("state",0);
                fragment.getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mValues != null) {
            return mValues.size() + 1;
        }
        return 1;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(TextView itemView) {
            super(itemView);
            textView = itemView;
        }

        TextView textView;
    }

}
