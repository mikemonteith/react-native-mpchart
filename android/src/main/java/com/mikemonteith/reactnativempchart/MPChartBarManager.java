package com.mikemonteith.reactnativempchart;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ReactProp;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;


import java.util.ArrayList;

public class MPChartBarManager extends MPChartBarLineManager<BarChart> {
    public static final String REACT_CLASS = "MPChartBar";

    private ReadableMap leftAxisOptions;

    MPChartBarManager(){
        super(BarChart.class);
    }

    @Override
    public String getName(){
        return REACT_CLASS;
    }

    @ReactProp(name = "values")
    public void setValues(BarChart chart, ReadableArray values){
        ArrayList<BarEntry> vals = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();
        for(int i=0; i<values.size(); i++) {
            vals.add(new BarEntry((float) values.getDouble(i), i));
            xVals.add("");
        }

        BarDataSet dataSet = new BarDataSet(vals, "");
        dataSet.setDrawValues(drawValuesEnabled);
        if(colors != null){
            dataSet.setColors(this.colors);
        }
        BarData data = new BarData(xVals, dataSet);
        chart.setData(data);

        chart.invalidate();
    }

}