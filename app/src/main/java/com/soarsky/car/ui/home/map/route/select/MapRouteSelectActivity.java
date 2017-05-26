package com.soarsky.car.ui.home.map.route.select;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.soarsky.car.App;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.ui.home.map.route.point.MapRoutePointActivity;
import com.soarsky.car.ui.home.map.route.set.MapRouteSetActivity;
import com.soarsky.car.ui.home.map.search.MapSearchAdapter;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/12<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：更改起始点<br>
 * 该类为 MapRouteSelectActivity<br>
 */


public class MapRouteSelectActivity extends BaseActivity<MapRouteSelectPresent,MapRouteSelectModel> implements MapRouteSelectView,View.OnClickListener,OnGetSuggestionResultListener {

    /**
     * 搜索框
     */
    private EditText mainMapSearchEt;
    /**
     * 取消
     */
    private RelativeLayout mapSearchCancelLay;
    /**
     * 地图选点
     */
    private LinearLayout mapRouteSelectLay;
    /**
     * mapSearchMainLay
     */
    private LinearLayout mapSearchMainLay;
    /**
     * ListView
     */
    private ListView mapSearchListView;
    /**
     * 其适配器
     */
    private MapSearchAdapter adapter;
    /**
     * 城市
     */
    private String city = "";
    /**
     * SuggestionSearch
     */
    private SuggestionSearch mSuggestionSearch = null;
    /**
     * application
     */
    private App app;
    /**
     * 该类的key
     */
    private static final String TAG ="MapRouteSelectActivity";
    /**
     * 改变起始位置的信息 1--起点 2--终点
     */
    private int flag = -1;
    @Override
    public int getLayoutId() {
        return R.layout.activity_map_route_select;
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void initView() {

        app = (App) getApplication();
        app.addActivity(TAG,this);

        mainMapSearchEt = (EditText) findViewById(R.id.mainMapSearchEt);

        mapSearchCancelLay = (RelativeLayout) findViewById(R.id.mapSearchCancelLay);
        mapSearchCancelLay.setOnClickListener(this);

        mapSearchMainLay = (LinearLayout) findViewById(R.id.mapSearchMainLay);

        mapSearchListView = (ListView) findViewById(R.id.mapSearchListView);

        mapRouteSelectLay = (LinearLayout) findViewById(R.id.mapRouteSelectLay);
        mapRouteSelectLay.setOnClickListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        flag = getIntent().getIntExtra("flag",-1);
        city = app.getCity();
        // 初始化建议搜索模块，注册建议搜索事件监听
        mSuggestionSearch = SuggestionSearch.newInstance();
        mSuggestionSearch.setOnGetSuggestionResultListener(this);

        mainMapSearchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("TAG","city="+city);
                showProgess();
                if(TextUtils.isEmpty(editable.toString())){
                    mapSearchMainLay.setVisibility(View.GONE);
                    return;
                }
                mapSearchMainLay.setVisibility(View.VISIBLE);
                /**
                 * 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
                 */
                mSuggestionSearch
                        .requestSuggestion((new SuggestionSearchOption())
                                .keyword(mainMapSearchEt.getText().toString()).city(city));
            }
        });

        mapSearchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SuggestionResult.SuggestionInfo suggestionInfo =(SuggestionResult.SuggestionInfo) adapter.getItem(i);
                Intent in = new Intent();
                in.setClass(MapRouteSelectActivity.this, MapRouteSetActivity.class);
                in.putExtra("type",2);
                if(flag ==1){
                    in.putExtra("flag",1);
                    app.getRouteParam().setStartAddress(suggestionInfo.key);
                    app.getRouteParam().setStartLocation(suggestionInfo.pt);
                }else if(flag ==2){
                    in.putExtra("flag",2);
                    app.getRouteParam().setEndAddress(suggestionInfo.key);
                    app.getRouteParam().setEndLocation(suggestionInfo.pt);
                }
                MapRouteSelectActivity.this.startActivity(in);

            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.mapRouteSelectLay:
                Intent i = new Intent(this, MapRoutePointActivity.class);
                if(flag == 1) {
                    i.putExtra("flag",1);
                }else if(flag == 2){
                    i.putExtra("flag",2);
                }
                startActivity(i);
                break;
            case R.id.mapSearchCancelLay:
                finish();
                break;
        }
    }

    /**
     * 搜索结果的回调
     * @param res 结果
     */
    @Override
    public void onGetSuggestionResult(SuggestionResult res) {
        stopProgess();
        if (res == null || res.getAllSuggestions() == null) {
            return;
        }

        List<SuggestionResult.SuggestionInfo> list = res.getAllSuggestions();
        if(list != null){
            adapter = new MapSearchAdapter(this,list);
            mapSearchListView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onDestroy() {
        mSuggestionSearch.destroy();
        super.onDestroy();
    }

}
