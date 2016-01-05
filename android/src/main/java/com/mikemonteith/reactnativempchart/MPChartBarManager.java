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

public class MPChartBarManager extends SimpleViewManager<BarChart> {
    public static final String REACT_CLASS = "MPChartBar";

    private int[] colors;

    @Override
    public String getName(){
        return REACT_CLASS;
    }

    @Override
    protected BarChart createViewInstance(final ThemedReactContext context){
        final BarChart chart = new BarChart(context);
        chart.setDescription("");
        chart.getLegend().setEnabled(false);

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            public void onValueSelected(Entry entry, int dataSetIndex, Highlight highlight) {
                WritableMap event = Arguments.createMap();
                event.putString("type", "valueSelect");
                event.putDouble("value", (double) entry.getVal());
                event.putInt("xIndex", entry.getXIndex());

                context.getJSModule(RCTEventEmitter.class).receiveEvent(
                        chart.getId(),
                        "topSelect",
                        event
                );
            }

            public void onNothingSelected() {
                WritableMap event = Arguments.createMap();
                event.putString("type", "clearSelection");
                context.getJSModule(RCTEventEmitter.class).receiveEvent(
                        chart.getId(),
                        "topSelect",
                        event
                );
            }

        });

        return chart;
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

    @ReactProp(name = "colors")
    public void setColors(BarChart chart, ReadableArray colorStrings){
        int[] colors = new int[colorStrings.size()];
        for(int i=0; i<colorStrings.size(); i++){
            String colorString = colorStrings.getString(i);
            colors[i] = (Color.parseColor(colorString));
        }

        this.colors = colors;
        if(chart.getData() != null && chart.getData().getDataSetByIndex(0) != null) {
            chart.getData().getDataSetByIndex(0).setColors(colors);
        }

        chart.invalidate();
    }
}