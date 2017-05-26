package com.soarsky.car.ui.trivelrecord.selectweek;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.soarsky.car.Constants;
import com.soarsky.car.R;
import com.soarsky.car.base.BaseActivity;
import com.soarsky.car.bean.SelectWeek;
import com.soarsky.car.uitl.SpUtil;

import java.util.ArrayList;
import java.util.List;
/**
 * Andriod_Car_App<br>
 * 作者： 何明辉<br>
 * 时间： 2017/3/6<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：heminghui@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为  设置保护时间星期界面<br>
 */

public class SelectWeekActivity extends BaseActivity implements View.OnClickListener{

    /**
     * ListView
     */
    private ListView listView;
    /**
     * ListView填充的数据
     */
    private List<SelectWeek> listData;
    /**
     * ListView的Adapter
     */
    private SelectWeekAdapter selectWeekAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_week;
    }

    @Override
    public void initView() {
        findViewById(R.id.select_blackTv).setOnClickListener(this);

        listView= (ListView) findViewById(R.id.select_listView);
        initData();
        selectWeekAdapter=new SelectWeekAdapter(this,listData);
        listView.setAdapter(selectWeekAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listData.get(position).setSelect(!listData.get(position).isSelect());
                selectWeekAdapter.setListData(listData);
            }
        });
    }

    @Override
    protected String getHeaderTitle() {
        return null;
    }


    /**
     * 初始化日期数据
     */
    private void initData() {
        listData = new ArrayList<>();
        String selectDay = SpUtil.get(Constants.AUTOWEEKDAY);
        if (TextUtils.isEmpty(selectDay)) {
            listData.add(new SelectWeek("每周日", false));
            listData.add(new SelectWeek("每周一", false));
            listData.add(new SelectWeek("每周二", false));
            listData.add(new SelectWeek("每周三", false));
            listData.add(new SelectWeek("每周四", false));
            listData.add(new SelectWeek("每周五", false));
            listData.add(new SelectWeek("每周六", false));
        } else {
            listData.add(new SelectWeek("每周日", selectDay.contains("0")));
            listData.add(new SelectWeek("每周一", selectDay.contains("1")));
            listData.add(new SelectWeek("每周二", selectDay.contains("2")));
            listData.add(new SelectWeek("每周三", selectDay.contains("3")));
            listData.add(new SelectWeek("每周四", selectDay.contains("4")));
            listData.add(new SelectWeek("每周五", selectDay.contains("5")));
            listData.add(new SelectWeek("每周六", selectDay.contains("6")));


        }


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_blackTv:
                Intent intent=getIntent();
                intent.putExtra(Constants.AUTOWEEKDAY,getAutoWeekStr());
                setResult(101,intent);
                finish();
            break;
        }
    }


    /**
     *
     * @return 选择日期的字符串 用0123456表示
     */
    private  String getAutoWeekStr(){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<listData.size();i++){
            if(listData.get(i).isSelect()){
                sb.append(i);
            }
        }
        return  sb.toString();



    }


}
