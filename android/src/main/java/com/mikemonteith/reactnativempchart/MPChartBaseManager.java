package com.mikemonteith.reactnativempchart;

import android.content.Context;
import android.graphics.Color;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.lang.reflect.Constructor;

public abstract class MPChartBaseManager<T extends Chart> extends SimpleViewManager<Chart> {
    private final Constructor<? extends Chart> ctor;

    public int[] colors;
    public boolean drawValuesEnabled = true;

    MPChartBaseManager(Class<? extends Chart> cls) {
        super();
        try {
            this.ctor = cls.getConstructor(Context.class);
        }catch(Exception e){
            //TODO: Don't just catch all exceptions
            throw new RuntimeException("Chart manager failed to instantiate");
        }
    }

    @Override
    protected Chart createViewInstance(final ThemedReactContext context) {
        final Chart chart;

        try {
            chart = ctor.newInstance(context);
        }catch(Exception e){
            //TODO: Don't just catch all exceptions!
            throw new RuntimeException("Chart failed to instantiate");
        }

        //TODO: These options should be configurable
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

    @ReactProp(name = "colors")
    public void setColors(T view, ReadableArray colorStrings){
        int[] colors = new int[colorStrings.size()];
        for(int i=0; i<colorStrings.size(); i++){
            String colorString = colorStrings.getString(i);
            colors[i] = (Color.parseColor(colorString));
        }

        this.colors = colors;
        if(view.getData() != null && view.getData().getDataSetByIndex(0) != null) {
            view.getData().getDataSetByIndex(0).setColors(colors);
        }

        view.invalidate();
    }

    @ReactProp(name = "drawValues", defaultBoolean = true)
    public void setDrawValues(T view, boolean drawValuesEnabled){
        this.drawValuesEnabled = drawValuesEnabled;

        if(view.getData() != null){
            view.getData().setDrawValues(drawValuesEnabled);
        }

        view.invalidate();
    }

    @ReactProp(name = "touchEnabled", defaultBoolean = true)
    public void setTouchEnabled(T view, boolean touchEnabled) {
        view.setTouchEnabled(touchEnabled);
        view.invalidate();
    }
}
