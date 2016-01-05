package com.mikemonteith.reactnativempchart;

import android.graphics.Color;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class MPChartLineManager extends SimpleViewManager<LineChart> {
    public static final String REACT_CLASS = "MPChartLine";

    private int[] colors;

    @Override
    public String getName(){
        return REACT_CLASS;
    }

    @Override
    protected LineChart createViewInstance(final ThemedReactContext context){
        final LineChart chart = new LineChart(context);
        chart.setDescription("");
        chart.getLegend().setEnabled(false);

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            public void onValueSelected(Entry entry, int dataSetIndex, Highlight highlight) {
                WritableMap event = Arguments.createMap();
                event.putString("type", "pointSelect");
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
                event.putString("type", "pointDeselect");
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
    public void setValues(LineChart chart, ReadableArray values){
        ArrayList<Entry> vals = new ArrayList<Entry>();
        ArrayList<String> xVals = new ArrayList<String>();
        for(int i=0; i<values.size(); i++) {
            vals.add(new Entry((float) values.getDouble(i), i));
            xVals.add("");
        }

        LineDataSet dataSet = new LineDataSet(vals, "");
        if(colors != null){
            dataSet.setColors(this.colors);
        }
        LineData data = new LineData(xVals, dataSet);
        chart.setData(data);

        chart.invalidate();
    }

    @ReactProp(name = "colors")
    public void setColors(LineChart chart, ReadableArray colorStrings){
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
