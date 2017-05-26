package com.soarsky.installationmanual.view.sortlistview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.bean.BrandBean;
import com.soarsky.installationmanual.ui.branditem.BrandItemActivity;
import com.soarsky.installationmanual.util.SizeUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * SortListView-master
 * 作者： 魏凯
 * 时间： 2017/2/14
 * 公司：长沙硕铠电子科技有限公司
 * Email：weikai@soarsky-e.com
 * 公司网址：http://www.soarsky-e.com
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼
 * 版本：1.0.0.0
 * 邮编：410000
 * 程序功能：
 * 该类为
 */
public class SortListView extends RelativeLayout {
    private ListView sortListView;
    private SideBar sideBar;
    private BaseSortAdapter adapter;

    ////TODO 添加点击事件
    private AdapterView.OnItemClickListener onItemClickListener;

    /**
     * 根据拼音来排列ListView里面的数据类
     */
    public SortListView(Context context) {
        this(context, null);
    }

    public SortListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SortListView(final Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        sortListView = new ListView(context);
        sortListView.setDivider(null);
        sortListView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        sortListView.setBackgroundColor(Color.rgb(255, 255, 255));
        addView(sortListView);
        sideBar = new SideBar(context);
        LayoutParams layoutParams = new LayoutParams(SizeUtils.dp2px(context,30f),SizeUtils.dp2px(context,500f));
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        sideBar.setLayoutParams(layoutParams);
        addView(sideBar);
        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    sortListView.setSelection(position);
                }

            }
        });
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //这里要利用adapter.getItem(position)来获取当前position所对应的对象

                //Toast.makeText(context, ((SortModel)adapter.getItem(position-1)).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), BrandItemActivity.class);
                intent.putExtra("brand",((SortModel)adapter.getItem(position-1)).getName());
                getContext().startActivity(intent);
            }
        });
    }


    public void setAdapter(BaseSortAdapter adapter) {
        this.adapter = adapter;
        sortListView.setAdapter(adapter);
    }

    public static List<SortModel> filledData(List<BrandBean> brandBeanList){
        List<SortModel> mSortList = new ArrayList<SortModel>();
        if(brandBeanList!=null){
            CharacterParser characterParser = CharacterParser.getInstance();
            for(int i=0; i<brandBeanList.size(); i++){
                SortModel sortModel = new SortModel();
                sortModel.setImageUrl(brandBeanList.get(i).getImageurl());
                sortModel.setName(brandBeanList.get(i).getBrand());
                //汉字转换成拼音
                String pinyin = characterParser.getSelling(brandBeanList.get(i).getBrand());
                String sortString = pinyin.substring(0, 1).toUpperCase();

                // 正则表达式，判断首字母是否是英文字母
                if(sortString.matches("[A-Z]")){
                    sortModel.setSortLetters(sortString.toUpperCase());
                }else{
                    sortModel.setSortLetters("#");
                }

                mSortList.add(sortModel);
            }
            PinyinComparator pinyinComparator = new PinyinComparator();
            Collections.sort(mSortList, pinyinComparator);
        }

        return mSortList;

    }

    public ListView getSortListView() {
        return sortListView;
    }
}
