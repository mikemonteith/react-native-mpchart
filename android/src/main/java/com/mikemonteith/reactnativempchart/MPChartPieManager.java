package com.mikemonteith.reactnativempchart;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

public class MPChartPieManager extends MPChartBaseManager<PieChart> {
    public static final String REACT_CLASS = "MPChartPie";

    MPChartPieManager(){
        super(PieChart.class);
    }

    @Override
    public String getName(){
        return REACT_CLASS;
    }

    protected PieChart createViewInstance(final ThemedReactContext context){
        PieChart chart = (PieChart) super.createViewInstance(context);

        /**
         * set some pie chart defaults
         * TODO: These options should be configurable
         */
        chart.setHoleColorTransparent(true);
        chart.setRotationEnabled(false);

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
        dataSet.setDrawValues(drawValuesEnabled);
        if(colors != null){
            dataSet.setColors(this.colors);
        }
        PieData data = new PieData(xVals, dataSet);
        view.setData(data);

        view.invalidate();
    }

    @ReactProp(name = "holeRadius", defaultFloat = 50f)
    public void setThickness(PieChart view, float holeRadius){
        view.setHoleRadius(holeRadius);
        view.invalidate();
    }

    @ReactProp(name = "rotationAngle", defaultFloat = 0)
    public void setRotationAngle(PieChart view, float angle){
        view.setRotationAngle(angle);
        view.invalidate();
    }
}