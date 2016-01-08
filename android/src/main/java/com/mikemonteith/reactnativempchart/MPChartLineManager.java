package com.mikemonteith.reactnativempchart;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

public class MPChartLineManager extends MPChartBarLineManager<LineChart> {
    public static final String REACT_CLASS = "MPChartLine";

    MPChartLineManager(){
        super(LineChart.class, LineData.class, LineDataSet.class, Entry.class);
    }

    @Override
    public String getName(){
        return REACT_CLASS;
    }
}
