package com.mikemonteith.reactnativempchart;

import com.facebook.react.bridge.ReadableMap;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

public class MPChartLineManager extends MPChartBarLineManager<LineChart, LineData, LineDataSet, Entry> {
    public static final String REACT_CLASS = "MPChartLine";

    MPChartLineManager(){
        super(LineChart.class, LineData.class, LineDataSet.class, Entry.class);
    }

    public void updateDataSetOptions(LineDataSet dataSet, ReadableMap map){
        super.updateDataSetOptions(dataSet, map);

        if(map.hasKey("drawCircles")) {
            dataSet.setDrawCircles(map.getBoolean("drawCircles"));
        }

        if(map.hasKey("drawCubic")) {
            dataSet.setDrawCubic(map.getBoolean("drawCubic"));
        }
    }

    @Override
    public String getName(){
        return REACT_CLASS;
    }
}
