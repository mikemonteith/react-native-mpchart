package com.mikemonteith.reactnativempchart;

import android.content.Context;
import android.graphics.Color;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
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

public abstract class MPChartBaseManager<ChartClass extends Chart, ChartDataClass extends ChartData,
        DataSetClass extends DataSet, EntryClass extends Entry> extends SimpleViewManager<ChartClass> {

    private final Constructor<ChartClass> chartConstructor;
    private final Constructor<ChartDataClass> chartDataConstructor;
    private final Constructor<DataSetClass> dataSetConstructor;
    private final Constructor<EntryClass> entryConstructor;

    public int[] colors;
    public boolean drawValuesEnabled = true;

    MPChartBaseManager(Class<ChartClass> chartClass, Class<ChartDataClass> chartDataClass,
                       Class<DataSetClass> dataSetClass, Class<EntryClass> entryClass)
    {
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
    protected ChartClass createViewInstance(final ThemedReactContext context) {
        final ChartClass chart;

        try {
            chart = (ChartClass) chartConstructor.newInstance(context);
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

    public void updateDataSetOptions(DataSetClass dataSet, ReadableMap map){
        if(map.hasKey("values")) {
            ReadableArray valueArray = map.getArray("values");
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

        if(map.hasKey("colors")){
            ReadableArray colorsArray = map.getArray("colors");
            ArrayList<Integer> colors = new ArrayList<>();

            for(int c = 0; c < colorsArray.size(); c++){
                colors.add(Color.parseColor(colorsArray.getString(c)));
            }

            dataSet.setColors(colors);
        }

        if(map.hasKey("drawValues")){
            dataSet.setDrawValues(map.getBoolean("drawValues"));
        }

        //TODO: add other properties to dataSet here

    }

    @ReactProp(name = "data")
    public void setData(ChartClass chart, ReadableMap map){
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
                DataSetClass dataSet;

                try {
                    dataSet = (DataSetClass) this.dataSetConstructor.newInstance(new ArrayList<>(), "Data Set " + i);
                } catch (Exception e) {
                    throw new RuntimeException("DataSet failed to instantiate");
                }

                updateDataSetOptions(dataSet, dataSetMap);
                chartData.addDataSet(dataSet);
            }

            chart.notifyDataSetChanged();
        }

        //TODO: add other properties to data here

        chart.setData(chartData);
        chart.invalidate();
    }

    @ReactProp(name = "touchEnabled", defaultBoolean = true)
    public void setTouchEnabled(ChartClass view, boolean touchEnabled) {
        view.setTouchEnabled(touchEnabled);
        view.invalidate();
    }
}
