package com.mikemonteith.reactnativempchart;

import android.graphics.Color;

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
        ArrayList<String> xVals = new ArrayList<String>();
        for(int i=0; i<values.size(); i++) {
            vals.add(new Entry((float) values.getDouble(i), i));
            xVals.add("");
        }

        PieDataSet dataSet = new PieDataSet(vals, "");
        PieData data = new PieData(xVals, dataSet);
        view.setData(data);

        view.invalidate();
    }

    @ReactProp(name = "colors")
    public void setColors(PieChart view, ReadableArray colorStrings){
        int[] colors = new int[colorStrings.size()];
        for(int i=0; i<colorStrings.size(); i++){
            String colorString = colorStrings.getString(i);
            colors[i] = (Color.parseColor(colorString));
        }
        view.getData().getDataSet().setColors(colors);

        view.invalidate();
    }
}