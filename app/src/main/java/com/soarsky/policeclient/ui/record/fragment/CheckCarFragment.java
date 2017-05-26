package com.soarsky.policeclient.ui.record.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.soarsky.policeclient.R;
import com.soarsky.policeclient.data.local.db.bean.Check;
import com.soarsky.policeclient.ui.details.DetailsActivity;
import com.soarsky.policeclient.ui.record.CheckCarAdapter;
import com.soarsky.policeclient.ui.record.RecordModel;

import java.util.ArrayList;
import java.util.List;

/**
 * andriod_police_app<br>
 * 作者： 王松清<br>
 * 时间： 2016/11/17<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：wangsongqing@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  历史记录--稽查车辆<br>
 */

public class CheckCarFragment extends Fragment implements ICheckCarFragmentView, View.OnClickListener {
    /**
     * 分页索引
     */
    private int pageIndex = 0;
    /**
     * 每页页数
     */
    public static final int pageSize = 30;
    private String pageIndexAndpageSize = pageIndex+":"+pageSize;

    /**
     * model对象
     */
    private RecordModel model ;
    /**
     * present对象
     */
    private CheckCarFragmentPresent present ;
    /**
     * 数据适配器
     */
    private CheckCarAdapter adapter;
    /**
     * 稽查车辆的集合
     */
    private List<Check> data = new ArrayList<>() ;
    /**
     * 稽查车辆列表gridView
     */
    private PullToRefreshGridView gridView;
    /**
     * 当前fragment的view对象
     */
    private View view;
    /**
     * 搜索功能的的外布局
     */
    private RelativeLayout rl_wai;
    /**
     * 编辑输入框的布局
     */
    private RelativeLayout rl_edit;
    /**
     * 编辑输入框中间文本布局
     */
    private RelativeLayout rl_midle;
    /**
     * 搜索功能的编辑输入框
     */
    private EditText edittext;

    public CheckCarFragment(){
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new RecordModel();
        present = new CheckCarFragmentPresent();
        present.setModel(model);
        present.setView(this);
        present.getRecordViolationParam(null,pageIndex);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_jicha,container,false);

        rl_wai = (RelativeLayout) view.findViewById(R.id.rl_wai);
        rl_wai.setOnClickListener(this);
        rl_edit = (RelativeLayout) view.findViewById(R.id.rl_edit);
        rl_midle = (RelativeLayout) view.findViewById(R.id.rl_midle);
        edittext = (EditText) view.findViewById(R.id.edittext);

        gridView = (PullToRefreshGridView) view.findViewById(R.id.gridview);
        gridView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        gridView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<GridView>() {
            @Override
            public void onRefresh(PullToRefreshBase<GridView> refreshView) {
                pageIndex++;
                present.getRecordViolationParam(null,pageIndex);
            }
        });
        adapter = new CheckCarAdapter(getActivity());
        gridView.setAdapter(adapter); //给gridview设置适配器

        //Gridview条目的点击事件
        //addClick();
        //搜索功能
        search();
        return view;
    }

    /**
     * 搜索
     */
    private void search() {
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String edit = edittext.getText().toString();
                List<Check> _list = new ArrayList<Check>();
                for(Check bean:data){
                    if (bean.getCarnum() == null){
                        return;
                    }else {
                        if(bean.getCarnum().contains(edit)){
                            _list.add(bean);
                        }
                    }
                }
                adapter.setData(_list);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * 给Gridview条目增加点击事件
     */
    /*private void addClick() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //ToastUtil.show(getActivity(),"我被点击了");
                Intent intent=new Intent();
                intent.setClass(getActivity(), DetailsActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("carNum",data.get(i).getCarnum());//给intent添加额外数据，key为“carNum”,key值为"Intent Demo"
                startActivity(intent);
            }
        });
    }*/


    @Override
    public void showData(List<Check> data) {
        this.data.addAll(data);
        adapter.setData(this.data);
        gridView.onRefreshComplete();
    }

    /**
     * 控件点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_wai:
                rl_midle.setVisibility(View.GONE);
                rl_edit.setVisibility(View.VISIBLE);
                edittext.requestFocus();//获取焦点
                InputMethodManager imm = (InputMethodManager) edittext.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED); //隐藏软键盘
                break;
        }
    }
}
