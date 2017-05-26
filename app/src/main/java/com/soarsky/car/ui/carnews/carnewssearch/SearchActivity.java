package com.soarsky.car.ui.carnews.carnewssearch;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.AutomotiveInfo;
import com.soarsky.car.bean.ResponseDataBean;
import com.soarsky.car.ui.carnews.ArticleDetailsActivity;
import com.soarsky.car.uitl.SpUtil;
import com.soarsky.car.uitl.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Andriod_Car_App<br>
 * 作者： 王松清<br>
 * 时间： 2017/1/10<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为   汽车资讯搜索页面<br>
 */

public class SearchActivity extends BaseActivity<SearchPresent,SearchModel> implements SearchView,View.OnClickListener {
    /**
     * 搜索的文本输入框
     */
    private EditText editText;
    /**
     * 搜索中动态呈现的资讯列表
     */
    private ListView lv_searching;
    /**
     * 搜索中数据适配器
     */
    private SearchingAdapter searchingAdapter;
    /**
     * 取消搜索
     */
    private TextView tv_cancel;

    /**
     * 搜索的历史记录
     */
    private LinearLayout ll_record;
    /**
     * 历史记录列表
     */
    private ListView lv_record_item;
    /**
     * 历史记录数据适配器
     */
    private HistoryRecordAdapter historyRecordAdapter;
    /**
     * 没有搜索结果
     */
    private RelativeLayout rl_no_result;
    /**
     * 删除搜索框编辑内容的按钮
     */
    private ImageView iv_delete;
    /**
     * 文章类型
     */
    private int type;
    /**
     * 清空搜索记录
     */
    private ImageView iv_clear;
    /**
     * 搜索记录的集合
     */
    private List<String> rList = new ArrayList<String>();
    /**
     * 搜索关键字
     */
    private String keyword;
    /**
     * 搜索历史关键字保存在本地的key值
     */
    private final String RECORD = "listRecord";
    /**
     * 搜索历史关键字
     */
    private  String [] keywordArray;

    @Override
    public int getLayoutId() {
        return R.layout.activity_car_news_search;
    }

    @Override
    public void initView() {
        iv_delete = (ImageView) findViewById(R.id.iv_delete);
        iv_delete.setOnClickListener(this);

        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);

        ll_record = (LinearLayout) findViewById(R.id.ll_record);


        lv_record_item = (ListView) findViewById(R.id.lv_record_item);
        //rList.add(SpUtil.get(RECORD));

        keywordArray = SpUtil.get(RECORD).split("#");
        for(int i = 0 ; i <keywordArray.length ;i++){
            rList.add(keywordArray[i]);
        }

        //搜索的历史记录
        ll_record.setVisibility(View.VISIBLE);
        if (rList == null){
            return;
        }else {
            historyRecordAdapter = new HistoryRecordAdapter(this);
            historyRecordAdapter.setList(rList);
            lv_record_item.setAdapter(historyRecordAdapter);
        }

        lv_record_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editText.setText(rList.get(i));
            }
        });


        iv_clear = (ImageView) findViewById(R.id.iv_clear);
        iv_clear.setOnClickListener(this);

        rl_no_result = (RelativeLayout) findViewById(R.id.rl_no_result);

        lv_searching = (ListView) findViewById(R.id.listView_search);

        editText = (EditText) findViewById(R.id.editText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                /*ll_record.setVisibility(View.VISIBLE);
                lv_record_item.setAdapter(historyRecordAdapter);*/
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                iv_delete.setVisibility(View.VISIBLE);

                lv_searching.setVisibility(View.VISIBLE);
                ll_record.setVisibility(View.GONE);
                keyword = editText.getText().toString();

                mPresenter.search(type, keyword);
            }

            @Override
            public void afterTextChanged(Editable editable) {

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
            case R.id.tv_cancel:
                finish();
                //startActivity(new Intent(this, CarNewsActivity.class));
                break;
            case R.id.iv_delete:
                editText.getText().clear();
                break;
            case R.id.iv_clear:

                new AlertDialog.Builder(this).setTitle(getString(R.string.weather_to_clear))
                        .setPositiveButton(getString(R.string.sure_to_clear), new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“确认”后的操作
                                rList.clear();
                                //lv_record_item.setVisibility(View.GONE);
                                historyRecordAdapter.notifyDataSetChanged();
                                SpUtil.clear();

                            }
                        })
                        .setNegativeButton(getString(R.string.cancel_to_clear), new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 点击“返回”后的操作,这里不设置没有任何操作
                            }
                        }).show();
                break;
        }

    }

    /**
     * 搜素错误的接口回调
     * @param e 错误信息
     */
    @Override
    public void searchError(Throwable e) {
        ToastUtil.show(this,getString(R.string.no_connet_internet));
    }

    /**
     * 搜索成功的接口回调
     * @param listResponseDataBean 搜索到的资讯
     */
    @Override
    public void searchSuccess(final ResponseDataBean<List<AutomotiveInfo>> listResponseDataBean) {
        if (listResponseDataBean.getData() == null || listResponseDataBean.getData().size() == 0){
            lv_searching.setVisibility(View.GONE);
            rl_no_result.setVisibility(View.VISIBLE);


        }else {
            lv_searching.setVisibility(View.VISIBLE);
            searchingAdapter = new SearchingAdapter(this);
            searchingAdapter.setData(listResponseDataBean.getData());
            lv_searching.setAdapter(searchingAdapter);
            lv_searching.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent();
                    AutomotiveInfo info = listResponseDataBean.getData().get(i);
                    intent.setClass(mContext, ArticleDetailsActivity.class);
                    intent.putExtra("info",info);
                    intent.putExtra("aid",listResponseDataBean.getData().get(i).getId());
                    startActivity(intent);



                    finish();
                }
            });

        }
        if (TextUtils.isEmpty(SpUtil.get(RECORD))){
            SpUtil.save(RECORD ,keyword);
        }else {
            SpUtil.save(RECORD ,SpUtil.get(RECORD)+"#"+keyword);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        type = intent.getExtras().getInt("type");
    }
}
