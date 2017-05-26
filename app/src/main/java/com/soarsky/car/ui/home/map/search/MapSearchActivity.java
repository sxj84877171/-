package com.soarsky.car.ui.home.map.search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.soarsky.car.ui.home.map.route.MapRouteActivity;

import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云<br>
 * 时间： 2017/1/10<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：搜索<br>
 * 该类为 MapSearchActivityv<br>
 */


public class MapSearchActivity extends BaseActivity<MapSearchPresent,MapSearchModel> implements MapSearchView,View.OnClickListener,OnGetSuggestionResultListener {

    /**
     * 搜索框
     */
    private EditText mainMapSearchEt;
    /**
     * 取消
     */
    private RelativeLayout mapSearchCancelLay;
    /**
     * mapSearchMainLay
     */
    private LinearLayout mapSearchMainLay;
    /**
     * listview
     */
    private ListView mapSearchListView;
    /**
     * 适配器
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
    private static final String TAG = "MapSearchActivity";
    /**
     * 删除
     */
    private ImageView iv_delete;


    @Override
    public int getLayoutId() {
        return R.layout.activity_map_search;
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

        iv_delete = (ImageView) findViewById(R.id.iv_delete);
        iv_delete.setOnClickListener(this);



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                iv_delete.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("TAG","city="+city);
                //showProgess();
                if(TextUtils.isEmpty(mainMapSearchEt.getText().toString())){
                    mapSearchMainLay.setVisibility(View.GONE);

                    stopProgess();
                    return;
                }
                mapSearchMainLay.setVisibility(View.VISIBLE);

                /**
                 * 使用建议搜索服务获取建议列表，结果在onSuggestionResult()中更新
                 */
                mSuggestionSearch
                        .requestSuggestion((new SuggestionSearchOption())
                                .keyword(mainMapSearchEt.getText().toString()).city(city==null?"":" "));
            }
        });

        mapSearchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent in = new Intent(MapSearchActivity.this, MapRouteActivity.class);
                SuggestionResult.SuggestionInfo suggestionInfo =(SuggestionResult.SuggestionInfo) adapter.getItem(i);
                in.putExtra("suggestInfo",suggestionInfo);
                startActivity(in);
            }
        });
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.mapSearchCancelLay:
                finish();
                break;
            case R.id.iv_delete:
                mainMapSearchEt.getText().clear();
                break;

        }
    }

    /**
     * 搜索结果回调
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
