package com.soarsky.policeclient.ui.accident.listandadd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.soarsky.policeclient.App;
import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.data.local.db.bean.accident.Accident;
import com.soarsky.policeclient.data.local.db.dao.AccidentDao;
import com.soarsky.policeclient.bean.AccidentDataBean;
import com.soarsky.policeclient.ui.accident.DataNoSerializable;
import com.soarsky.policeclient.ui.accident.add.AddAccidentActivity;
import com.soarsky.policeclient.ui.accident.listitem.AccidentListItemActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为事故分析事故列表界面<br>
 */
public class ListAndAddActivity extends BaseActivity<ListAndAddPresent,ListAndAddModel> implements ListAndAddView {
    private ListView listView;
    private TextView headerTv;
    private ImageView addIV;
    private RelativeLayout back;
    AccidentListAdapter accidentListAdapter;
    List<AccidentDataBean.AccidentItemBean> accidentItemBeanList = new ArrayList<>();
    List<Accident> accidents = new ArrayList<>();
    private int clickedPosition;

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_and_add;
    }

    @Override
    public void initView() {
        listView = (ListView) findViewById(R.id.listView);
        headerTv = (TextView) findViewById(R.id.headerTv);
        addIV = (ImageView) findViewById(R.id.addIv);
        back = (RelativeLayout) findViewById(R.id.listIconLay);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        headerTv.setText("事故分析");

        accidentListAdapter = new AccidentListAdapter(this);
        listView.setAdapter(accidentListAdapter);
        addIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ListAndAddActivity.this, AddAccidentActivity.class),0);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                clickedPosition = position;
                Intent i = new Intent(ListAndAddActivity.this, AccidentListItemActivity.class);
                if(accidentItemBeanList.get(position).getId()!=0){
                    i.putExtra("id",accidentItemBeanList.get(position).getId());
                }else {
                    DataNoSerializable.getDataNoSerializable().setAccidentItemBean(accidentItemBeanList.get(position));
                    for (Accident accident :accidents) {
                        Gson gson = new Gson();
                        AccidentDataBean.AccidentItemBean accidentItemBean = gson.fromJson(accident.getData(), AccidentDataBean.AccidentItemBean.class);
                        if(accidentItemBean.getTime()!=null && accidentItemBeanList.get(position).getTime()!=null && accidentItemBean.getTime().equals(accidentItemBeanList.get(position).getTime())){
                            i.putExtra("accidentId",accident.getId());
                        }
                    }
                }
                startActivityForResult(i,1);
            }
        });
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            if(requestCode == 0){
                AccidentDataBean.AccidentItemBean newAccidentItemBean = DataNoSerializable.getDataNoSerializable().getAccidentItemBean1();
                if(newAccidentItemBean!=null){
                    accidentItemBeanList.add(0,newAccidentItemBean);
                    accidentListAdapter.setAccidentList(accidentItemBeanList);
                }
            }else if(requestCode == 1){
                AccidentDataBean.AccidentItemBean updateAccidentItemBean = DataNoSerializable.getDataNoSerializable().getUpdateAccidentItemBean();
                if(updateAccidentItemBean!=null){
                    accidentItemBeanList.get(clickedPosition).setAccidentCarBeanList(updateAccidentItemBean.getAccidentCarBeanList());
                }
            }
        }
    }

    private void initData(){
        accidents = App.getApp().getDaoSession().getAccidentDao().queryBuilder().where(AccidentDao.Properties.HasUpload.eq(false)).list();
        addDataFromDb();
        showProgress();
        mPresenter.getAccidentList();
    }


    @Override
    public void showData(List<AccidentDataBean.AccidentItemBean> data) {
        dismissProgress();
        for (AccidentDataBean.AccidentItemBean accidentItemBean:data) {
            if(!accidentItemBeanList.contains(accidentItemBean)){
                accidentItemBeanList.add(accidentItemBean);
            }
        }
        Collections.sort(accidentItemBeanList,new ComparatorDate());
        accidentListAdapter.setAccidentList(accidentItemBeanList);

    }
    public class ComparatorDate implements Comparator {
        public static final String TAG = "ComparatorDate";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public int compare(Object obj1, Object obj2) {
            AccidentDataBean.AccidentItemBean t1 = (AccidentDataBean.AccidentItemBean) obj1;
            AccidentDataBean.AccidentItemBean t2 = (AccidentDataBean.AccidentItemBean) obj2;

            Date d1, d2;
            try {
                d1 = format.parse(t1.getTime());
                d2 = format.parse(t2.getTime());
            } catch (ParseException e) {
                // 解析出错，则不进行排序
                return 0;
            }
            if (d1.before(d2)) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    private void addDataFromDb(){
        Gson gson = new Gson();
        for (Accident accident :accidents) {
            AccidentDataBean.AccidentItemBean accidentItemBean = gson.fromJson(accident.getData(),AccidentDataBean.AccidentItemBean.class);
            if(!accidentItemBeanList.contains(accidentItemBean)){
                accidentItemBeanList.add(accidentItemBean);
            }
        }
    }

    @Override
    public void onError() {
        dismissProgress();
        accidentListAdapter.setAccidentList(accidentItemBeanList);
    }

    @Override
    public void onNoData() {
        dismissProgress();
        accidentListAdapter.setAccidentList(accidentItemBeanList);
    }

    @Override
    public void showProgess() {

    }

    @Override
    public void stopProgess() {

    }
}
