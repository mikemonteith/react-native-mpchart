package com.mikemonteith.reactnativempchart;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ReactProp;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

public class MPChartBarManager extends MPChartBarLineManager<BarChart> {
    public static final String REACT_CLASS = "MPChartBar";

    private ReadableMap leftAxisOptions;

    MPChartBarManager(){
        super(BarChart.class, BarData.class, BarDataSet.class, BarEntry.class);
    }

    @Override
    public String getName(){
        return REACT_CLASS;
    }
}