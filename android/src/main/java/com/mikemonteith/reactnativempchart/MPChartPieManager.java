package com.mikemonteith.reactnativempchart;

import android.graphics.Color;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

public class MPChartPieManager extends MPChartBaseManager<PieChart, PieData, PieDataSet, Entry> {
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

    @ReactProp(name = "transparentCircleRadius", defaultFloat = 50f)
    public void setTransparentCircleRadius(PieChart view, float transparentCircleRadius){
        view.setTransparentCircleRadius(transparentCircleRadius);
        view.invalidate();
    }

    @ReactProp(name = "transparentCircleColor")
    public void setTransparentCircleColor(PieChart view, String transparentCircleColor){
        view.setTransparentCircleColor(Color.parseColor(transparentCircleColor));
        view.invalidate();
    }

    @ReactProp(name = "transparentCircleAlpha")
    public void setTransparentCircleAlpha(PieChart view, float transparentCircleAlpha){
        view.setTransparentCircleAlpha((int) (transparentCircleAlpha * 255));
        view.invalidate();
    }

    @ReactProp(name = "rotationAngle", defaultFloat = 0)
    public void setRotationAngle(PieChart view, float angle){
        view.setRotationAngle(angle);
        view.invalidate();
    }
}