package com.soarsky.car.ui.main;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.soarsky.car.R;

import java.util.ArrayList;

public class NewFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    String[] mTitles ;

    private ArrayList<Fragment>fragmentArrayList;

//    int[] imageResId = {R.mipmap.home_u, R.mipmap.car_u,
//            R.mipmap.preserve_u, R.mipmap.personal_u};

    int[] imageResId = {R.drawable.new_home_selector_bg, R.drawable.new_car_selector_bg,
            R.drawable.new_preserve_selector_bg, R.drawable.new_personal_selector_bg};

    public NewFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
//        fragmentArrayList=al;
        mTitles = context.getResources().getStringArray(R.array.new_home_title);
    }


    @Override
    public Fragment getItem(int position) {
//        fragmentArrayList.get(position);
        if (position == 1) {
            return new NewLicenseFragment();
        } else if (position == 2) {
            return new NewInformationFragment();
        } else if (position == 3) {
            return new NewMineFragment();
        }
        return new NewMainFragment();
    }
 
    @Override
    public int getCount() {
        return mTitles.length;
    }
 

    public View getTabView(int position){

        View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);
        TextView tv= (TextView) view.findViewById(R.id.textView);
        tv.setText(mTitles[position]);
        ImageView img = (ImageView) view.findViewById(R.id.imageView);
        img.setImageResource(imageResId[position]);
        return view;
    }



}