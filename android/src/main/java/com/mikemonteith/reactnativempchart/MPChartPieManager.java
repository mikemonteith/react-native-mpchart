package com.mikemonteith.reactnativempchart;

import android.graphics.Color;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

public class MPChartPieManager extends SimpleViewManager<PieChart> {
    public static final String REACT_CLASS = "MPChartPie";

    private ThemedReactContext context;

    @Override
    public String getName(){
        return REACT_CLASS;
    }

    @Override
    protected PieChart createViewInstance(final ThemedReactContext context) {
        this.context = context;
        final PieChart chart = new PieChart(context);

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener(){

            public void onValueSelected(Entry entry, int dataSetIndex, Highlight highlight){
                WritableMap event = Arguments.createMap();
                event.putString("type", "segmentSelect");
                event.putDouble("value", (double) entry.getVal());
                event.putInt("xIndex", entry.getXIndex());

                context.getJSModule(RCTEventEmitter.class).receiveEvent(
                        chart.getId(),
                        "topSelect",
                        event
                );
            }

            public void onNothingSelected(){
                WritableMap event = Arguments.createMap();
                event.putString("type", "segmentDeselect");
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