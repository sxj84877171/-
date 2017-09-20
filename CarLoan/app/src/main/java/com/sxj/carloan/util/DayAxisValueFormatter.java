package com.sxj.carloan.util;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.sxj.carloan.bean.QueryMonth;

import java.util.List;

/**
 * Created by philipp on 02/06/16.
 */
public class DayAxisValueFormatter implements IAxisValueFormatter
{

    protected String[] mMonths = new String[]{
            "提交", "请款", "征信", "评估", "派单", "调查", "电审", "风控", "放款"
    };

    int type = 1;

    public void setType(int type) {
        this.type = type;
    }

    private List<QueryMonth> queryMonthList;


    public void setmMonths(String[] mMonths) {
        this.mMonths = mMonths;
    }

    public void setQueryMonthList(List<QueryMonth> queryMonthList) {
        this.queryMonthList = queryMonthList;
    }

    public DayAxisValueFormatter(BarLineChartBase<?> chart) {
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        if(type == 1) {
            int v = (int) value;
            return mMonths[v % mMonths.length];
        }else{
            int v = (int) value;
            return queryMonthList.get(v % queryMonthList.size()).getA_date();
        }
    }


}
