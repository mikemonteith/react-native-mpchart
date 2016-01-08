package com.mikemonteith.reactnativempchart;

import android.content.Context;
import android.graphics.Color;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public abstract class MPChartBaseManager<T extends Chart> extends SimpleViewManager<Chart> {
    private final Constructor<? extends Chart> chartConstructor;
    private final Constructor<? extends ChartData> chartDataConstructor;
    private final Constructor<? extends DataSet> dataSetConstructor;
    private final Constructor<? extends Entry> entryConstructor;

    public int[] colors;
    public boolean drawValuesEnabled = true;

    MPChartBaseManager(Class<? extends Chart> chartClass, Class<? extends ChartData> chartDataClass, Class<? extends DataSet> dataSetClass, Class<? extends Entry> entryClass) {
        super();
        try {
            this.chartConstructor = chartClass.getConstructor(Context.class);
            this.chartDataConstructor = chartDataClass.getConstructor();
            this.dataSetConstructor = dataSetClass.getConstructor(List.class, String.class);
            this.entryConstructor = entryClass.getConstructor(float.class, int.class);
        }catch(Exception e){
            //TODO: Don't just catch all exceptions
            throw new RuntimeException("Chart manager failed to instantiate");
        }
    }

    @Override
    protected Chart createViewInstance(final ThemedReactContext context) {
        final Chart chart;

        try {
            chart = chartConstructor.newInstance(context);
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

    @ReactProp(name = "data")
    public void setData(T chart, ReadableMap map){
        ChartData chartData;
        try{
            chartData = chartDataConstructor.newInstance();
        }catch(Exception e){
            throw new RuntimeException("ChartData failed to instantiate");
        }

        if(map.hasKey("xValues")){
            ReadableArray xValuesArray = map.getArray("xValues");
            for(int k = 0; k < xValuesArray.size(); k++){
                chartData.addXValue(xValuesArray.getString(k));
            }
        }

        if(map.hasKey("dataSets")) {
            ReadableArray dataSetsArray = map.getArray("dataSets");

            for (int i = 0; i < dataSetsArray.size(); i++) {
                ReadableMap dataSetMap = dataSetsArray.getMap(i);
                DataSet<? extends Entry> dataSet;

                try {
                    dataSet = this.dataSetConstructor.newInstance(new ArrayList<>(), "Data Set " + i);
                } catch (Exception e) {
                    throw new RuntimeException("DataSet failed to instantiate");
                }

                if(dataSetMap.hasKey("values")) {
                    ReadableArray valueArray = dataSetMap.getArray("values");
                    for (int j = 0; j < valueArray.size(); j++) {
                        Entry entry;
                        try {
                            entry = entryConstructor.newInstance((float) valueArray.getDouble(j), j);
                        } catch (Exception e) {
                            throw new Error("Entry failed to instantiate");
                        }

                        dataSet.addEntry(entry);
                    }
                }

                if(dataSetMap.hasKey("colors")){
                    ReadableArray colorsArray = dataSetMap.getArray("colors");
                    ArrayList<Integer> colors = new ArrayList<>();

                    for(int c = 0; c < colorsArray.size(); c++){
                        colors.add(Color.parseColor(colorsArray.getString(c)));
                    }

                    dataSet.setColors(colors);
                }

                if(dataSetMap.hasKey("drawValues")){
                    dataSet.setDrawValues(dataSetMap.getBoolean("drawValues"));
                }

                //TODO: add other properties to dataSet here

                chartData.addDataSet(dataSet);
                chart.notifyDataSetChanged();

            }

            chart.setData(chartData);
        }

        //TODO: add other properties to data here

        chart.invalidate();
    }

    @ReactProp(name = "touchEnabled", defaultBoolean = true)
    public void setTouchEnabled(T view, boolean touchEnabled) {
        view.setTouchEnabled(touchEnabled);
        view.invalidate();
    }
}
