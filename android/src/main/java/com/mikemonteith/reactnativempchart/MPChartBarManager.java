package com.mikemonteith.reactnativempchart;

import android.graphics.Color;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class MPChartBarManager extends MPChartBaseManager<BarChart> {
    public static final String REACT_CLASS = "MPChartBar";

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
        if(colors != null){
            dataSet.setColors(this.colors);
        }
        BarData data = new BarData(xVals, dataSet);
        chart.setData(data);

        chart.invalidate();
    }

}