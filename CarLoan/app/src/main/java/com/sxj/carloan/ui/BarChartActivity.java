
package com.sxj.carloan.ui;

import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.components.YAxis.YAxisLabelPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.sxj.carloan.BaseActivity;
import com.sxj.carloan.R;
import com.sxj.carloan.bean.LoanQuery;
import com.sxj.carloan.bean.QueryMonth;
import com.sxj.carloan.bean.ResultListBean;
import com.sxj.carloan.util.DayAxisValueFormatter;
import com.sxj.carloan.util.MyAxisValueFormatter;
import com.sxj.carloan.util.XYMarkerView;

import java.util.ArrayList;

public class BarChartActivity extends BaseActivity implements OnChartValueSelectedListener {

    protected BarChart mChart;
    private TextView tvX, tvY;
    private LoanQuery loanQuery;
    private ResultListBean<QueryMonth> resultListBean;
    private int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_barchart);

        loanQuery = (LoanQuery) getIntent().getParcelableExtra("LoanQuery");//.getSerializableExtra("LoanQuery");
        resultListBean = (ResultListBean<QueryMonth>) getIntent().getSerializableExtra("ResultListBean");
        type = getIntent().getIntExtra("query",0);

        tvX = (TextView) findViewById(R.id.tvXMax);
        tvY = (TextView) findViewById(R.id.tvYMax);

        mChart = (BarChart) findViewById(R.id.chart1);
        mChart.setOnChartValueSelectedListener(this);
        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        mChart.getDescription().setEnabled(false);
        mChart.setMaxVisibleValueCount(60);
        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);

        DayAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(mChart);
        xAxisFormatter.setType(type);
        if(resultListBean != null){
            xAxisFormatter.setQueryMonthList(resultListBean.getRows());
        }
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setLabelCount(7);
        xAxis.setValueFormatter(xAxisFormatter);

        IAxisValueFormatter custom = new MyAxisValueFormatter();
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f);
//        YAxis rightAxis = mChart.getAxisRight();
//        rightAxis.setDrawGridLines(false);
//        rightAxis.setLabelCount(8, false);
//        rightAxis.setValueFormatter(custom);
//        rightAxis.setSpaceTop(15f);
//        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

//        Legend l = mChart.getLegend();
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setForm(LegendForm.SQUARE);
//        l.setFormSize(9f);
//        l.setTextSize(11f);
//        l.setXEntrySpace(4f);

        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
        mv.setChartView(mChart); // For bounds control
        mChart.setMarker(mv); // Set the marker to the chart
        setData();
    }

    private void setData() {

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        int i = 0;
        if(type == 1) {
            yVals1.add(new BarEntry(i++, loanQuery.getYwy_case_num()));
            yVals1.add(new BarEntry(i++, loanQuery.getYwy_qk_num()));
            yVals1.add(new BarEntry(i++, loanQuery.getZhengxin_num()));
            yVals1.add(new BarEntry(i++, loanQuery.getBaoxian_num()));
            yVals1.add(new BarEntry(i++, loanQuery.getDispatch_num()));
            yVals1.add(new BarEntry(i++, loanQuery.getDcy_num()));
            yVals1.add(new BarEntry(i++, loanQuery.getDs_num()));
            yVals1.add(new BarEntry(i++, loanQuery.getFk_num()));
            yVals1.add(new BarEntry(i++, loanQuery.getLoan_cw_num()));
        }else if(type == 2){
            for(QueryMonth queryMonth : resultListBean.getRows()){
                yVals1.add(new BarEntry(i++, queryMonth.getSl()));
            }
        }else{
            return;
        }

        BarDataSet set1;
        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(yVals1);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            if(type == 1){
                set1 = new BarDataSet(yVals1, "角色分类统计");
            }else{
                set1 = new BarDataSet(yVals1, "业务统计");
            }
            set1.setDrawIcons(false);
            set1.setColors(ColorTemplate.MATERIAL_COLORS);
            ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
            data.setBarWidth(0.9f);
            mChart.setData(data);
        }
    }

    protected RectF mOnValueSelectedRectF = new RectF();

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;

        RectF bounds = mOnValueSelectedRectF;
        mChart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = mChart.getPosition(e, AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + mChart.getLowestVisibleX() + ", high: "
                        + mChart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() { }
}
