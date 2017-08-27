package com.sxj.carloan.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.view.PhotoView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/8/26.
 */

public class ViewPagerActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        Intent intent = getIntent();
        List<String> pathList = intent.getStringArrayListExtra("path");
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        if(pathList != null){
            viewPager.setAdapter(new SamplePagerAdapter(pathList));
        }
    }


    class SamplePagerAdapter extends PagerAdapter {

        private List<String> filePathList = new ArrayList<>();

         public SamplePagerAdapter(List<String> filePathList) {
             this.filePathList = filePathList;
         }

         @Override
        public int getCount() {
            return filePathList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            photoView.setImageResource(R.drawable.ic_launcher);
            Glide.with(getActivity()).load(filePathList.get(position)).into(photoView);
            container.addView(photoView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

    }
}
