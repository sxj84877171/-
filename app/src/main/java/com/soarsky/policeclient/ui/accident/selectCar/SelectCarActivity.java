package com.soarsky.policeclient.ui.accident.selectCar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.bean.Car;
import com.soarsky.policeclient.server.bluetooth.Blue;
import com.soarsky.policeclient.ui.accident.DataNoSerializable;

import java.util.ArrayList;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/19<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为选择车牌号列表页面，这些车牌号是通过wifi扫描到的<br>
 */
public class SelectCarActivity extends BaseActivity<SelectCarPresent,SelectCarModel> {
    /**
     * 扫描到的车辆列表
     */
    private ArrayList<Car> scanCarList = new ArrayList<>();
    /**
     * 选择的车辆列表
     */
    private ArrayList<Car> selectCarList = new ArrayList<>();
    /**
     * 扫描到的车辆ListView
     */
    private ListView carListView;
    /**
     * 返回按钮
     */
    private RelativeLayout back;
    /**
     * 扫描到的车辆ListView对应的Adapter
     */
    private ScanCarListAdapter scanCarListAdapter;
    /**
     * 确认按钮
     */
    private TextView ok;
    /**
     * 搜索输入框
     */
    private EditText search;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ArrayList<Car> selectCarListTem = DataNoSerializable.getDataNoSerializable().getCars();
        if(selectCarListTem!=null){
            selectCarList = selectCarListTem;
        }
        carListView = (ListView) findViewById(R.id.carListView);
        scanCarListAdapter = new ScanCarListAdapter();
        carListView.setAdapter(scanCarListAdapter);
        ok = (TextView) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent();
                DataNoSerializable.getDataNoSerializable().setCars(selectCarList);
                SelectCarActivity.this.setResult(Activity.RESULT_OK,in);
                finish();
            }
        });
        back = (RelativeLayout) findViewById(R.id.listIconLay);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
                finish();
            }
        });
        blueSaoMiao();
        carListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (selectCarList.contains(scanCarList.get(position))) {
                    selectCarList.remove(scanCarList.get(position));
                } else {
                    selectCarList.add(scanCarList.get(position));
                }
                if (scanCarListAdapter != null) {
                    scanCarListAdapter.notifyDataSetChanged();
                }
            }
        });
        search = (EditText) findViewById(R.id.search);
        search();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_car;
    }

    @Override
    public void initView() {

    }
    private void blueSaoMiao(){
        Blue blue = Blue.getInstance(this);
        blue.setOnBlueScan(onBlueScan);
        blue.startDiscovery();
    }

    private Blue.OnBlueScan onBlueScan = new Blue.OnBlueScan() {
        @Override
        public void onBlueScan(Car car) {
            if(!scanCarList.contains(car)){
                scanCarList.add(car);
                scanCarListAdapter.setData(scanCarList);
            }
        }
    };

    @Override
    public void onBackPressed() {
        back();
        super.onBackPressed();
    }
    private void back() {
        Blue.getInstance(this).stopDiscovery();
    }
    /**
     * 搜索
     */
    private void search() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String edit = search.getText().toString();
                ArrayList<Car> _list = new ArrayList<Car>();
                for (Car bean : scanCarList) {

                    if (bean.getBlueName().contains(edit)) {
                        _list.add(bean);
                    }
                }

                scanCarListAdapter.setData(_list);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    /**
     * 扫描到车辆的Adapter
     */
    public class ScanCarListAdapter extends BaseAdapter {

        private ArrayList<Car> adapterScanCarList = new ArrayList<>();

        public void setData(ArrayList<Car> data) {
            this.adapterScanCarList = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if(adapterScanCarList!=null){
                return adapterScanCarList.size();
            }else {
                return 0;
            }

        }

        @Override
        public Object getItem(int i) {
            return adapterScanCarList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1;
            ViewHolder holder;
            if (null == view) {
                // 如果view为空，则表示第一次显示该条目，需要创建一个view
                view1 = View.inflate(SelectCarActivity.this, R.layout.scancaritem, null);
                //新建一个viewholder对象
                holder = new ViewHolder();
                //将findviewbyID的结果赋值给holder对应的成员变量
                holder.ssid = (TextView) view1.findViewById(R.id.ssid);
                holder.select = (ImageView) view1.findViewById(R.id.select);
                // 将holder与view进行绑定
                view1.setTag(holder);
            } else {
                view1 = view;
                holder = (ViewHolder) view1.getTag();
            }

            holder.ssid.setText(adapterScanCarList.get(i).getCarNum());
            if (selectCarList.contains(adapterScanCarList.get(i))) {
                holder.select.setImageResource(R.drawable.scancarselect);
            } else {
                holder.select.setImageResource(R.drawable.scancarnoselect);
            }
            return view1;
        }


        private class ViewHolder {
            public TextView ssid;
            public ImageView select;
        }

    }

}
