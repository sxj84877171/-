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
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.data.local.db.bean.Violation;
import com.soarsky.policeclient.ui.elecdetails.ElectronicDetailsActivity;
import com.soarsky.policeclient.ui.record.ElectronicAdapter;
import com.soarsky.policeclient.ui.record.RecordModel;
import com.soarsky.policeclient.uitl.TimeUtils;

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
 * 该类为  历史记录--电子罚单界面<br>
 */

public class ElectronicFragment extends Fragment implements IElectronicFragmentView,View.OnClickListener{
    /**
     * 数据适配器
     */
    private ElectronicAdapter adapter;
    /**
     * model对象
     */
    private RecordModel model;
    /**
     * present对象
     */
    private ElectronicFragmentPresent present;
    /**
     * 当前fragment的view对象
     */
    private View view;
    /**
     * 罚单记录列表listView
     */
    private ListView listView;
    /**
     * 电子罚单记录的集合
     */
    
    private List<Violation> mList;
    /**
     * 搜索功能的外布局
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
    private EditText edittext2;


    public ElectronicFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new RecordModel();
        present = new ElectronicFragmentPresent();
        present.setModel(model);
        present.setView(this);
        present.init();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_record, container, false);

        rl_wai = (RelativeLayout) view.findViewById(R.id.rl_wai);
        rl_wai.setOnClickListener(this);

        rl_edit = (RelativeLayout) view.findViewById(R.id.rl_edit);
        rl_midle = (RelativeLayout) view.findViewById(R.id.rl_midle);
        edittext2 = (EditText) view.findViewById(R.id.edittext2);

        //给listview设置适配器
        listView = (ListView) view.findViewById(R.id.carrecord_listView);
        adapter = new ElectronicAdapter(getActivity());
        listView.setAdapter(adapter);

        //罚单记录添de点击事件
        addClick();

        //搜索
        search();
        /*edittext2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                rl_midle.setVisibility(View.VISIBLE);
                rl_edit.setVisibility(View.GONE);
            }
        });*/
        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rl_midle.setVisibility(View.VISIBLE);
                rl_edit.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager)
                        getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });*/
        return view;
    }

    /**
     * 搜索
     */
    private void search() {
        //编辑输入框的监听
        edittext2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String searchTxt = edittext2.getText().toString();
       
                List<Violation> _list = new ArrayList<Violation>();
                for(Violation bean:mList){
                    if(bean.getAddress().contains(searchTxt) || TimeUtils.date2String(bean.getStime()).contains(searchTxt)
                            || bean.getInf().contains(searchTxt)){
                        _list.add(bean);
                    }
                }
                adapter.setList(_list);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void showSearch(View view){
        rl_midle.setVisibility(View.GONE);
        rl_edit.setVisibility(View.VISIBLE);
    }
    /**
     * 给罚单记录添加点击事件
     */
    private void addClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent();
                intent.setClass(getActivity(), ElectronicDetailsActivity.class);//从一个activity跳转到另一个activity
                intent.putExtra("carNum",mList.get(i).getCarnum());//给intent添加额外数据，key为“carNum”,key值为"Intent Demo"
                intent.putExtra("date", TimeUtils.date2String(mList.get(i).getStime()));
                intent.putExtra("address",mList.get(i).getAddress());
                intent.putExtra("cause",mList.get(i).getInf());
                intent.putExtra("score",mList.get(i).getCent()+"");
                intent.putExtra("money",mList.get(i).getMoney()+"");
                intent.putExtra("image",mList.get(i).getLocalimgurlurl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void showData(List<Violation> list) {
        mList= list;
        adapter.setList(list);
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
            edittext2.requestFocus();
            InputMethodManager imm = (InputMethodManager) edittext2.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            break;
    }
    }
}
