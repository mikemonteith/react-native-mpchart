package com.mikemonteith.reactnativempchart;

import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

public class MPChartPieManager extends MPChartBaseManager<PieChart> {
    public static final String REACT_CLASS = "MPChartPie";

    MPChartPieManager(){
        super(PieChart.class, PieData.class, PieDataSet.class, Entry.class);
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