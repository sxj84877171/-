package com.soarsky.installationmanual.ui.main.fragment.homefragment;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import com.soarsky.installationmanual.R;
import com.soarsky.installationmanual.base.BaseFragment;
import com.soarsky.installationmanual.bean.BrandBean;
import com.soarsky.installationmanual.view.sortlistview.SortListView;
import com.soarsky.installationmanual.view.sortlistview.TextImageSortAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.functions.Action1;


/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 首页<br>
 */
public class HomeFragment extends BaseFragment<HomeFragmentPresent,HomeFragmentModel> implements HomeFragmentView {
    /**
     * 带A-Z索引的ListView
     */
    private SortListView sortListView;
    /**
     * 热门品牌GridView
     */
    private GridView gridView;
    /**
     * 热门品牌车辆图标
     */
    private int[] imgs = { R.drawable.dazhong, R.drawable.aodi, R.drawable.baoma, R.drawable.fengtian, R.drawable.xuetielong, R.drawable.benchi, R.drawable.xiandai, R.drawable.bieke, R.drawable.fute, R.drawable.sanlin };
    /**
     * 从服务器获取的品牌列表
     */
    private List<BrandBean> brandBeenList;
    /**
     * 热门品牌
     */
    private String[] texts = { "大众", "奥迪", "宝马", "丰田","雪铁龙", "奔驰", "现代", "别克","福特", "三菱" };
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
        addView(R.layout.main_center_toolbar);
        addView(R.layout.main_right_toolbar);
        sortListView = (SortListView) view.findViewById(R.id.sortListView);
        View headerView = View.inflate(getActivity(),R.layout.main_header,null);
        gridView = (GridView) headerView.findViewById(R.id.gridView);
        sortListView.getSortListView().addHeaderView(headerView);
        List<Map<String, Object>> lisItems = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < imgs.length; i++) {
            Map<String, Object> listem = new HashMap<String, Object>();
            listem.put("img", imgs[i]);
            listem.put("text", texts[i]);
            lisItems.add(listem);
        }
        gridView.setAdapter(new SimpleAdapter(getActivity(),lisItems,R.layout.home_gridview_item,new String[] { "img", "text" },
                new int[] {R.id.img,R.id.text}));
        sortListView.setAdapter(new TextImageSortAdapter(getActivity(),SortListView.filledData(brandBeenList)));
    }

    @Override
    protected String getHeaderTitle() {
        return "";
    }

    @Override
    public void showSuccess() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if(brandBeenList == null){
            mPresenter.getBrands();
        }
        return view;

    }

    @Override
    public void showData(List<BrandBean> brandBeanList) {
        this.brandBeenList = brandBeanList;
        sortListView.setAdapter(new TextImageSortAdapter(getActivity(),SortListView.filledData(brandBeenList)));
    }
}
