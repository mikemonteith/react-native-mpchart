package com.mikemonteith.reactnativempchart;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ReactProp;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

public class MPChartLineManager extends MPChartBarLineManager<LineChart> {
    public static final String REACT_CLASS = "MPChartLine";

    MPChartLineManager(){
        super(LineChart.class);
    }

    @Override
    public String getName(){
        return REACT_CLASS;
    }

    @ReactProp(name = "values")
    public void setValues(LineChart chart, ReadableArray values){
        ArrayList<Entry> vals = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();
        for(int i=0; i<values.size(); i++) {
            vals.add(new Entry((float) values.getDouble(i), i));
            xVals.add("");
        }

        LineDataSet dataSet = new LineDataSet(vals, "");
        dataSet.setDrawValues(drawValuesEnabled);
        if(colors != null){
            dataSet.setColors(this.colors);
        }
        LineData data = new LineData(xVals, dataSet);
        chart.setData(data);

        chart.invalidate();
    }
}
