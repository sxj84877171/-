package com.soarsky.policeclient.ui.accident.itemcar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.soarsky.policeclient.R;
import com.soarsky.policeclient.base.BaseActivity;
import com.soarsky.policeclient.bean.AccidentDataBean;
import com.soarsky.policeclient.ui.accident.DataNoSerializable;
import com.soarsky.policeclient.uitl.CarUtil;
import com.soarsky.policeclient.uitl.ToastUtil;

import java.util.ArrayList;
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
 * 该类为事故分析车辆详情界面<br>
 */
public class AccidentCarDetailActivity extends BaseActivity<AccidentCarDetailPresent,AccidentCarDetailModel> implements AccidentCarDetailView{

    /**
     * 车牌号TextView
     */
    private TextView carNum;
    /**
     * 车辆类型TextView
     */
    private TextView carType;
    /**
     * 是否系安全带TextView
     */
    private TextView anquandai;
    /**
     * 事故分析车辆打灯的ListView
     */
    private ListView deng;
    /**
     * 事故分析车辆违章的ListView
     */
    private ListView weizhang;
    /**
     * 事故分析车辆故障的ListView
     */
    private ListView guzhang;
    /**
     * 事故分析车辆打灯的ListView对应的Adapter
     */
    DengListAdapter dengListAdapter;
    /**
     * 返回按钮
     */
    private RelativeLayout back;
    /**
     * 界面标题
     */
    private TextView headerTv;
    /**
     * 事故分析车辆违章的ListView对应的Adapter
     */
    WeizhangListAdapter weizhangListAdapter;
    /**
     * 事故分析车辆故障的ListView对应的Adapter
     */
    GuzhangListAdapter guzhangListAdapter;
    /**
     * 事故分析的车辆对应的数据
     */
    AccidentDataBean.AccidentItemBean.AccidentCarBean accidentCarBean;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(accidentCarBean.getWeizhangItems()==null && accidentCarBean.getCar().getCarNum()!=null){
            showProgress();
            mPresenter.WeizhangQueryFromServer(accidentCarBean.getCar().getCarNum());
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_accident_car_detail;
    }

    @Override
    public void initView() {
        Intent in = getIntent();
        accidentCarBean = DataNoSerializable.getDataNoSerializable().getAccidentCarBean();
        carNum = (TextView) findViewById(R.id.carNum);
        carType = (TextView) findViewById(R.id.carType);
        anquandai = (TextView) findViewById(R.id.anquandai);
        back = (RelativeLayout) findViewById(R.id.listIconLay);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        headerTv = (TextView) findViewById(R.id.headerTv);
        headerTv.setText("事故车辆详情");
        deng = (ListView) findViewById(R.id.deng);
        weizhang = (ListView) findViewById(R.id.weizhang);
        guzhang  = (ListView) findViewById(R.id.guzhang);
        carNum.setText(accidentCarBean.getCar().getCarNum());
        dengListAdapter = new DengListAdapter();
        deng.setAdapter(dengListAdapter);
        dengListAdapter.setData(accidentCarBean.getDengItems());
        weizhangListAdapter = new WeizhangListAdapter();
        weizhang.setAdapter(weizhangListAdapter);
        weizhangListAdapter.setData(accidentCarBean.getWeizhangItems());
        guzhangListAdapter = new GuzhangListAdapter();
        guzhang.setAdapter(guzhangListAdapter);
        guzhangListAdapter.setData(accidentCarBean.getGuzhangItems());
        carType.setText(accidentCarBean.getType());
        anquandai.setText(accidentCarBean.getAnquandai());
        initChart();
    }


    /**
     * 初始化图表
     */
    private void initChart(){
        if(accidentCarBean.getSudus()!=null){
            LineChart chart = (LineChart) findViewById(R.id.chart);
            chart.getLegend().setEnabled(false);
            chart.getDescription().setEnabled(false);
            XAxis xAxis = chart.getXAxis();
            xAxis.setDrawLabels(true);
            xAxis.setDrawGridLines(false);
            xAxis.setAxisMinimum(0);
            xAxis.setAxisMaximum(10);
            xAxis.setLabelCount(10,true);
            xAxis.setDrawAxisLine(true);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            YAxis left = chart.getAxisLeft();
            left.setDrawLabels(true); // no axis labels
            left.setAxisMinimum(0);
            left.setDrawAxisLine(true); // no axis line
            left.setDrawGridLines(false); // no grid lines
            chart.getAxisRight().setEnabled(false); // no right axis
            List<ILineDataSet> lineDataSets = new ArrayList<>();
            for (int i=0;i<accidentCarBean.getSudus().size();i++){
                List<Entry> entries = new ArrayList<Entry>();
                for (int j=0;j<accidentCarBean.getSudus().get(i).getSuduItems().size();j++){
                    entries.add(new Entry(j,Float.parseFloat(accidentCarBean.getSudus().get(i).getSuduItems().get(j).getValue())));
                }
                LineDataSet dataSet = new LineDataSet(entries,null); // add entries to dataset
                lineDataSets.add(dataSet);
            }
            LineData lineData = new LineData(lineDataSets);
            chart.setData(lineData);
            chart.invalidate(); // refresh
        }

    }

    @Override
    public void onError() {
        dismissProgress();
        ToastUtil.show(this,"违章数据获取失败，请检查网络连接");
    }

    @Override
    public void onNoData() {
        dismissProgress();
        ToastUtil.show(this,"暂无违章数据");
    }

    @Override
    public void showData(List<AccidentDataBean.AccidentItemBean.AccidentCarBean.WeizhangItem> weizhangItems) {
        dismissProgress();
        weizhangListAdapter.setData(weizhangItems);
        weizhangListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgess() {

    }

    @Override
    public void stopProgess() {

    }

    /**
     * 事故分析车辆打灯的ListView对应的Adapter
     */
    public class DengListAdapter extends BaseAdapter {
        /**
         * 事故分析车辆打灯的数据列表
         */
        private List<AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem> adapterDengList = new ArrayList<>();

        public void setData(List<AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem> data) {
            this.adapterDengList = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if(adapterDengList!=null){
                return adapterDengList.size();
            }else {
                return 0;
            }

        }

        @Override
        public Object getItem(int i) {
            if(adapterDengList!=null){
                return adapterDengList.get(i);
            }else {
                return null;
            }

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
                view1 = View.inflate(AccidentCarDetailActivity.this, R.layout.dengitem, null);
                //新建一个viewholder对象
                holder = new ViewHolder();
                //将findviewbyID的结果赋值给holder对应的成员变量
                holder.dengTv = (TextView) view1.findViewById(R.id.dengTv);
                holder.dengTimeTv = (TextView) view1.findViewById(R.id.dengTimeTv);
                // 将holder与view进行绑定
                view1.setTag(holder);
            } else {
                view1 = view;
                holder = (ViewHolder) view1.getTag();
            }
            if(adapterDengList!=null && adapterDengList.get(i)!=null){
                holder.dengTv.setText(adapterDengList.get(i).getDeng());
                holder.dengTimeTv.setText(adapterDengList.get(i).getTime());
            }

            return view1;
        }


        private class ViewHolder {
            public TextView dengTv;
            public TextView dengTimeTv;
        }

    }
    /**
     * 事故分析车辆违章的ListView对应的Adapter
     */
    public class WeizhangListAdapter extends BaseAdapter {
        /**
         * 事故分析车辆违章的数据列表
         */
        private List<AccidentDataBean.AccidentItemBean.AccidentCarBean.WeizhangItem> adapterWeizhangList = new ArrayList<>();

        public void setData(List<AccidentDataBean.AccidentItemBean.AccidentCarBean.WeizhangItem> data) {
            this.adapterWeizhangList = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if(adapterWeizhangList!=null){
                return adapterWeizhangList.size();
            }else {
                return 0;
            }

        }

        @Override
        public Object getItem(int i) {
            if(adapterWeizhangList!=null){
                return adapterWeizhangList.get(i);
            }else {
                return null;
            }

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
                view1 = View.inflate(AccidentCarDetailActivity.this, R.layout.weizhangitem, null);
                //新建一个viewholder对象
                holder = new ViewHolder();
                //将findviewbyID的结果赋值给holder对应的成员变量
                holder.weizhangTimeTv = (TextView) view1.findViewById(R.id.weizhangTimeTv);
                holder.weizhangPositionTv = (TextView) view1.findViewById(R.id.weizhangpositionTv);
                holder.weizhangYuanyinTv = (TextView) view1.findViewById(R.id.yuanyin);
                // 将holder与view进行绑定
                view1.setTag(holder);
            } else {
                view1 = view;
                holder = (ViewHolder) view1.getTag();
            }

            holder.weizhangTimeTv.setText(adapterWeizhangList.get(i).getTime());
            holder.weizhangPositionTv.setText(adapterWeizhangList.get(i).getDizhi());
            holder.weizhangYuanyinTv.setText(adapterWeizhangList.get(i).getYuanyin());
            return view1;
        }


        private class ViewHolder {
            public TextView weizhangTimeTv;
            public TextView weizhangPositionTv;
            public TextView weizhangYuanyinTv;
        }

    }


    /**
     * 事故分析车辆故障的ListView对应的Adapter
     */
    public class GuzhangListAdapter extends BaseAdapter {
    /**
     * 事故分析车辆故障的数据列表
     */
        private List<AccidentDataBean.AccidentItemBean.AccidentCarBean.GuzhangItem> adapterGuzhangList = new ArrayList<>();

        public void setData(List<AccidentDataBean.AccidentItemBean.AccidentCarBean.GuzhangItem> data) {
            this.adapterGuzhangList = data;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if(adapterGuzhangList!=null){
                return adapterGuzhangList.size();
            }else {
                return 0;
            }

        }

        @Override
        public Object getItem(int i) {
            return adapterGuzhangList.get(i);
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
                view1 = View.inflate(AccidentCarDetailActivity.this, R.layout.guzhangitem, null);
                //新建一个viewholder对象
                holder = new ViewHolder();
                //将findviewbyID的结果赋值给holder对应的成员变量
                holder.time = (TextView) view1.findViewById(R.id.time);
                holder.guzhang = (TextView) view1.findViewById(R.id.guzhang);
                // 将holder与view进行绑定
                view1.setTag(holder);
            } else {
                view1 = view;
                holder = (ViewHolder) view1.getTag();
            }

            holder.time.setText(adapterGuzhangList.get(i).getTime());
            holder.guzhang.setText(adapterGuzhangList.get(i).getGuzhang());
            return view1;
        }


        private class ViewHolder {
            public TextView time;
            public TextView guzhang;
        }

    }
}
