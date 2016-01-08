package com.mikemonteith.reactnativempchart;

import android.graphics.Color;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ReactProp;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleData;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.data.Entry;

public abstract class MPChartBarLineManager<ChartClass extends BarLineChartBase, ChartDataClass extends BarLineScatterCandleBubbleData,
        DataSetClass extends BarLineScatterCandleBubbleDataSet, EntryClass extends Entry>
        extends MPChartBaseManager<ChartClass, ChartDataClass, DataSetClass, EntryClass>{
    MPChartBarLineManager(Class<ChartClass> chartClass,
                          Class<ChartDataClass> dataClass,
                          Class<DataSetClass> dataSetClass,
                          Class<EntryClass> entryClass
    ){
        super(chartClass, dataClass, dataSetClass, entryClass);
    }

    private static void updateAxisOptions(AxisBase axis, ReadableMap map) {
        if (map.hasKey("enabled")) {
            axis.setEnabled(map.getBoolean("enabled"));
        }

        if (map.hasKey("drawAxisLine")) {
            axis.setEnabled(map.getBoolean("drawAxisLine"));
        }

        if (map.hasKey("drawGridLines")) {
            axis.setDrawGridLines(map.getBoolean("drawGridLines"));
        }
    }

    private static void updateYAxisOptions(YAxis axis, ReadableMap map){
        updateAxisOptions(axis, map);

        if(map.hasKey("minValue")){
            axis.setAxisMinValue((float) map.getDouble("minValue"));
        }

        if (map.hasKey("maxValue")){
            axis.setAxisMaxValue((float) map.getDouble("maxValue"));
        }

        if(map.hasKey("inverted")){
            axis.setInverted(map.getBoolean("inverted"));
        }
    }

    private static void updateXAxisOptions(XAxis axis, ReadableMap map){
        /**
         * For now, X axis options are all available to y axis too,
         * so they are set in the generic updateAxisOptions function
         */
        updateAxisOptions(axis, map);

        if(map.hasKey("position")){
            XAxis.XAxisPosition position = null;
            switch(map.getString("position")){
                case "bottom":
                    position = XAxis.XAxisPosition.BOTTOM;
                    break;
                case "top":
                    position = XAxis.XAxisPosition.TOP;
                    break;
                case "bothSided":
                    position = XAxis.XAxisPosition.BOTH_SIDED;
                    break;
            }
            if(position != null){
                axis.setPosition(position);
            }
        }
    }

    @ReactProp(name = "leftAxis")
    public void setLeftAxis(BarLineChartBase chart, ReadableMap map){
        updateYAxisOptions(chart.getAxisLeft(), map);
        chart.invalidate();
    }

    @ReactProp(name = "rightAxis")
    public void setRightAxis(BarLineChartBase chart, ReadableMap map) {
        updateYAxisOptions(chart.getAxisRight(), map);
        chart.invalidate();
    }

    @ReactProp(name = "xAxis")
    public void setXAxis(BarLineChartBase chart, ReadableMap map){
        updateXAxisOptions(chart.getXAxis(), map);
        chart.invalidate();
    }

    @ReactProp(name = "gridBackgroundColor")
    public void setGridBackgroundColor(BarLineChartBase chart, String gridBackgroundColor){
        chart.setGridBackgroundColor(Color.parseColor(gridBackgroundColor));
        chart.invalidate();
    }
}
