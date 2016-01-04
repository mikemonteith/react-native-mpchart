package com.mikemonteith.reactnativempchart;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

public class MPChartPieManager extends SimpleViewManager<PieChart> {
    public static final String REACT_CLASS = "MPChartPie";

    @Override
    public String getName(){
        return REACT_CLASS;
    }

    @Override
    protected PieChart createViewInstance(ThemedReactContext context) {
        return new PieChart(context);
    }

    @ReactProp(name = "values")
    public void setValues(PieChart view, ReadableArray values) {
        ArrayList<Entry> vals = new ArrayList<Entry>();
        vals.add(new Entry(1, 0));
        vals.add(new Entry(2, 1));
        vals.add(new Entry(3, 2));

        PieDataSet dataSet = new PieDataSet(vals, "Data set 1");

        System.out.println("Setting values on a pie chart");

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("");
        xVals.add("");
        xVals.add("");

        PieData data = new PieData(xVals, dataSet);
        view.setData(data);
        view.invalidate();
    }
}